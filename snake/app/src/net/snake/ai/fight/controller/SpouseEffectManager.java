package net.snake.ai.fight.controller;

import java.util.Collection;
import java.util.Iterator;

import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.team.logic.Team;

import org.apache.log4j.Logger;


/**
 * 夫妻效果管理器
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class SpouseEffectManager {
	private static final Logger logger = Logger.getLogger(SpouseEffectManager.class);
	public SpouseEffectManager() {
	}
	/**
	 * 增加风雨同济buff
	 * @param effectController
	 */
	private void addFengYuTongJiBuff(EffectController effectController){
		
		VisibleObject vo = effectController.getVo();
		if (!(vo.getSceneObjType() == SceneObj.SceneObjType_Hero)) {
			return ;
		}
		
		Hero character = (Hero)vo;
		//且自己的等级高于30级，//--暂时注释掉  jack2011 12 02
//		if (character.getGrade() < SkillManager.getInstance().getFengYuTongJiSkill().getCharLevel()) {
//			return;
//		}
		
		RoleWedingManager rwMgr = character.getMyFriendManager().getRoleWedingManager();
		if (!rwMgr.isWedding()){
			return ;
		}
		
		//该配偶与自己所处同一地图，
		Scene scene = character.getSceneRef();
		Hero wedder =  scene.getSceneObj(SceneObj.SceneObjType_Hero, rwMgr.getWedderId());
		if (wedder == null) {
			return ;
		}
		//检测同队伍中如果存在自己的配偶，
		if (!character.getMyTeamManager().isInSameTeamWith(rwMgr.getWedderId())) {
			return;
		}
		
		if (!effectController.isFengYuTongJi()) {
			SkillEffect skillEffect = SkillManager.getInstance().getFengYuTongJiSkill().getEffect();
			EffectInfo effectInfo = new EffectInfo(skillEffect);
			effectInfo.setAffecter(effectController.getVo());
			if (effectController.addEffect(effectInfo)) {
				effectController.setFengYuTongJi(effectInfo);
			}
		}
	}
	
	/**
	 * 移除风雨同济buff
	 * @param effectController
	 */
	private void removeFengYuTongJiBuff(EffectController effectController){
		
		if (effectController.isFengYuTongJi()) {
			try {
				effectController.removeEffect(effectController.getFengYuTongJiBuff());
				effectController.setFengYuTongJi(null);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
	
	/**
	 * 当加入、离开一个队伍检测
	 * 当切换场景、下线的时候检测
	 * 升级的时候检测
	 * @param team
	 */
	public void teamReload(Team team){
		
		if (team == null) return;
		Collection<Hero> col = team.getCharacterPlayers();
		for (Iterator<Hero> iterator = col.iterator(); iterator.hasNext();) {
			Hero c = iterator.next();
			RoleWedingManager rwMgr = c.getMyFriendManager().getRoleWedingManager();
			if (!rwMgr.isWedding()){
				removeFengYuTongJiBuff(c.getEffectController());
				continue ;
			}
			int wedderId = rwMgr.getWedderId();
			if (c.getSceneRef() == null) {
				removeFengYuTongJiBuff(c.getEffectController());
				return;
			}
			Hero wedder = c.getSceneRef().getSceneObj(SceneObj.SceneObjType_Hero, wedderId);
			if (wedder == null) {
				removeFengYuTongJiBuff(c.getEffectController());
			} else {
				if (team.getCharacter(wedderId) == null) {
					removeFengYuTongJiBuff(c.getEffectController());
					removeFengYuTongJiBuff(wedder.getEffectController());
				} else {
					addFengYuTongJiBuff(c.getEffectController());
				}
			}
			
		}
	}
	
}
