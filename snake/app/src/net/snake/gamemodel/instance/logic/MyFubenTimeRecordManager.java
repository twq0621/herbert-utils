package net.snake.gamemodel.instance.logic;

import java.util.Date;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.instance.persistence.FubenrankingManager;

/**
 * 副本通关时间更新管理器
 * @author serv_dev
 *
 */
public class MyFubenTimeRecordManager {
	private Hero character;
	// TODAY日统计的日期// 副本日统计的次数 副本模型id(key) 副本日统计(value)
	private List<Fubenranking> list;
	public MyFubenTimeRecordManager(Hero character) {
		this.character = character;
	}
	public void destroy(){
		if(list!=null){
			list.clear();
			list=null;
		}
	}
	/**
	 * 玩家副本通关时间记入初始化
	 */
	public void init(){
		list= FubenrankingManager.getInstance().selecteFubenListByCharacterId(character.getId());
	}
	/**
	 * 根据副本id获取玩家该副本通关记入
	 * @param instanceId
	 * @return
	 */
	public Fubenranking getFubenrankingByInstanceId(int instanceId){
		if(list==null){
			return null;
		}
		for(Fubenranking fuben:list){
			if(fuben.getFubenId()==instanceId){
				if(fuben.getPreviousTime()==0){
					fuben.setPreviousTime(fuben.getFubenTime());
				}
				return fuben;
			}
		}
		return null;
	}
	/**
	 * 添加或更新玩家副本通关计入
	 * @param instanceId
	 * @param fubenTime
	 * @return
	 */
	public Fubenranking addOrUpdateFubenRanking(int instanceId,int fubenTime){
		Fubenranking fuben= getFubenrankingByInstanceId(instanceId);
		if(fuben==null){
			fuben=new Fubenranking();
			fuben.setCharacterId(character.getId());
			fuben.setCharacterGrade((int)character.getGrade());
			fuben.setFubenId(instanceId);
			fuben.setFubenTime(fubenTime);
			fuben.setPreviousTime(fubenTime);
			fuben.setFubenDate(new Date());
			this.list.add(fuben);
			FubenrankingManager.getInstance().addFubenRanking(fuben);
		}else{
			if(fuben.getFubenTime()>fubenTime){
				fuben.setFubenTime(fubenTime);
			}
			fuben.setPreviousTime(fubenTime);
			FubenrankingManager.getInstance().updateByTime(fuben);
		}
		return fuben;
	}
	
}
