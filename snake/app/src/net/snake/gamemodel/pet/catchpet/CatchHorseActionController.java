package net.snake.gamemodel.pet.catchpet;
/**
 * 
 * 人物马管理
 * @author serv_dev
 */
import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.SceneMonster;


public class CatchHorseActionController implements Updatable {
	// 捕获开始计时的时间
	private SafeTimer safetime=new SafeTimer();
	// 要捕获的目标在捕获前都是怪物
	private SceneMonster target;
	private Hero character;
	private boolean isStart;
	public void destory(){
		target=null;
	}
	public void startTime(SceneMonster target){
		if(isStart){
			return;
		}
		isStart=true;		
		this.target=target;
		int horseid =target.getMonsterModel().getHorseModelId();
		if(horseid != 0){
			//增加捕获的人
			target.getWhoCatchMeManager().addCatcher(character);
			safetime.start(5000);
			//开始时间
			character.sendMsg(new CatchHorseDelay50044(target.getId(), 5));
			character.getUpdateObjManager().addFrameUpdateObject(this);
		}
	}
	public CatchHorseActionController(Hero character) {
		this.character =character;
	}

	// 被别的模块调用，打断
	public void breakCatch() {
		if(isStart==false){
			return;
		}
		character.sendMsg(new CatchHorseFail50046(target.getId(), 40031));
		target.getWhoCatchMeManager().removeCatcher(character);
		isStart=false;

	}

	@Override
	public void update(long now) {
		//第一次时间到了
		if(safetime.isFirstOK(now)){
			//发送捕获请求
			target.getWhoCatchMeManager().catchTimeOK(character);
			//移除时间
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			isStart=false;
		}
	}

}
