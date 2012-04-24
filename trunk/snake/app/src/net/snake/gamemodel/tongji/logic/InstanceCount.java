package net.snake.gamemodel.tongji.logic;

import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.instance.logic.InstanceController;
/**
 * 角色副本行为统计
 * @author serv_dev
 *
 */
public class InstanceCount {
	MyCharacterAchieveCountManger myAchieveManger;
	public final  static int tianguanType=29;
	public final  static int tianguanyicengType=17;
	public final  static int fuziZhenfaType=18;
	public final  static int mizongzhenType=19;
	public InstanceCount(MyCharacterAchieveCountManger myAchieveManger) {
		this.myAchieveManger = myAchieveManger;
	}
	/**
	 * 天关通关统计
	 * @param instance
	 */
	private void tianguanCount(InstanceController instance){
		if(instance.getInstanceId()!=2){
			return;
		}
		myAchieveManger.characterAchieveToDBCount(tianguanType,1);
	}
	public void tianguanYicengCount(InstanceController instance){
		if(instance.getInstanceId()!=2){
			return;
		}
		myAchieveManger.characterAchieveToDBCount(tianguanyicengType,1);
	}
	/**
	 * 阵法通关统计
	 * @param instance
	 */
	private void fuziZhenfaCount(InstanceController instance){
		if(instance.getInstanceId()!=4){
			return;
		}
		myAchieveManger.characterAchieveToDBCount(fuziZhenfaType,1);
	}
	/**
	 * 迷踪阵成功闯过
	 * @param instance
	 */
	private void mizongzhenCount(InstanceController instance){
		if(instance.getInstanceId()!=6){
			return;
		}
		myAchieveManger.characterAchieveToDBCount(mizongzhenType,1);
		
	}
	/**
	 * 副本通关统计
	 * @param instance
	 */
   public void instanceFinishiCount(InstanceController instance){
	   tianguanCount(instance);
	   fuziZhenfaCount(instance);
	   mizongzhenCount(instance);
   }
}
