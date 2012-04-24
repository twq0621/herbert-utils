package net.snake.across.character;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.fight.response.CatchYoulongDelay51124;
import net.snake.gamemodel.fight.response.CatchYoulongOK51126;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
/**
 * 
 * 人物马管理
 * @author serv_dev
 */
public class CatchXuanyuanJianActionController implements Updatable {
	// 捕获开始计时的时间
	private SafeTimer safetime=new SafeTimer();
	// 要捕获的目标在捕获前都是怪物
	private SceneXuanyuanMonster target;
	private Hero character;
	private boolean isStart;
	
	public void startTime(SceneMonster target){
		if(isStart){
			return;
		}
		isStart=true;		
			//增加捕获的人
			SceneXuanyuanMonster monster=(SceneXuanyuanMonster)target;
			this.target=monster;
		     monster.getWhoCatchXuanyuanManager().addCatcher(character);
			 safetime.start(10000);
			//开始时间
			character.sendMsg(new CatchYoulongDelay51124(target.getId(), 10));
			character.getUpdateObjManager().addFrameUpdateObject(this);
	}
	public CatchXuanyuanJianActionController(Hero character) {
		this.character =character;
	}

	// 被别的模块调用，打断
	public void breakCatch() {
		if(!isStart){
			return;
		}
		isStart=false;
		character.sendMsg(new CatchYoulongOK51126(target.getId(), 14510));
		target.getWhoCatchXuanyuanManager().removeCatcher(character);
		character.getUpdateObjManager().removeFrameUpdateObject(this);
		isStart=false;
	}

	@Override
	public void update(long now) {
		//第一次时间到了
		if(safetime.isFirstOK(now)){
			//发送捕获请求
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			target.getWhoCatchXuanyuanManager().catchTimeOK(character);
			this.isStart=false;
			//移除时间
		}
	}
	/**
	 * 玩家处于拔剑状态
	 * @return
	 */
    public boolean isCatchYoulongState(){
    	return isStart;
    }
}
