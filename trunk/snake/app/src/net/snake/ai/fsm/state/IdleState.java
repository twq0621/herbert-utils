package net.snake.ai.fsm.state;

import java.util.Collection;
import java.util.Random;

import net.snake.ai.fight.response.MonsterSpeak10098;
import net.snake.ai.formula.DistanceFormula;
import net.snake.ai.fsm.FSMState;
import net.snake.ai.fsm.MonsterFSM;
import net.snake.commons.program.FrameIgnoreCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 空闲状态
 * 
 * @author serv_dev
 * 
 */
public class IdleState extends FSMState {
	private static Random random = new Random();
	// 一秒30桢，空闲状态10桢判断一次
	private FrameIgnoreCount frameignore = new FrameIgnoreCount(15);
	private Interval speekIterval;
	private Interval patrolIterval;
	public IdleState(MonsterFSM fsm, SceneMonster monster) {
		super(fsm, monster);
		
		speekIterval=new Interval(30000);
		patrolIterval=new Interval(60000);
	}

	private void alreadyToGo(long now) {
		if(patrolIterval.getIntervalCount(now)==1){
			sceneMonster.setObjectState(VisibleObjectState.Patrol);
		}
	}

	@Override
	public void onBegin() {		
		sceneMonster.getMoveController().stopMove();
		long now=System.currentTimeMillis();
		//speekIterval=new Interval(60000, now+random.nextInt(60000));
		speekIterval.starttime = now+random.nextInt(30000);
		//patrolIterval=new Interval(60000, now+random.nextInt(60000));
		patrolIterval.starttime = now+random.nextInt(60000);
	}

	@Override
	public void onUpdate(long now) {
		if (frameignore.isNeedReturn()) {
			return;
		}
		speak(now);
		idleAction(now);
	}

	protected void idleAction(long now) {
		
		boolean fireAttack = false;
		if (sceneMonster.isInitiativeAttack()) {// 主动攻击模式
			short x = sceneMonster.getX();
			short y = sceneMonster.getY();
			Collection<Hero> players = sceneMonster.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
			Hero _character = null;
			int minDistance = 0;
			for (Hero character : players) {
				int distance = DistanceFormula.getDistanceRound(x, y, character.getX(),
						character.getY());
				if (distance <= sceneMonster.getAlert(character)) {
					if (distance > minDistance) {
						minDistance = distance;
						_character = character;
					}
				}
			}
			
			if (_character != null) {
				sceneMonster.fireAttacking(_character);
				fireAttack = true;
				_character = null;
				return;
			}
		}
		if (!fireAttack) {
			alreadyToGo(now);
		}
	}

	private void speak(long now) {
		if(speekIterval.getIntervalCount(now)==1){
			MonsterModel model = sceneMonster.getMonsterModel();
			String speakBody[] = model.getSpeakBody();
			byte temp = (byte) (random.nextInt(speakBody.length) + 1);
			sceneMonster.getEyeShotManager().sendMsg(
					new MonsterSpeak10098(sceneMonster.getId(), temp));
		}
	}
	
	public static class Interval{
		int interval;
		long starttime;

		public Interval(int interval) {
			this.interval=interval;
		}
		
		public int getIntervalCount(long now){
			int esleape=(int)(now-starttime);
			int t=esleape/interval;
			if(t>1){//2倍以上含2倍
				starttime=now-(int)(esleape)%interval;
			}else if(t==1){//1倍至2倍
				starttime=now-(esleape-interval);
			}
			return t;
		}
	}
}
