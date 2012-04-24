package net.snake.gamemodel.dujie.logic;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.api.IHurtListener;
import net.snake.consts.ArrowWay;
import net.snake.consts.EffectType;
import net.snake.gamemodel.dujie.response.DujieSucResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.Skill;

/**
 * 渡劫场景中伤害的特殊逻辑
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieHurtListener implements IHurtListener {
	@Override
	public boolean beforeHurt(VisibleObject whoAttackMe, VisibleObject behurted, FighterVO fighterVO, int reduceHp, boolean nohurt) {
		if (behurted.getSceneObjType()!=SceneObj.SceneObjType_Hero) {
			return true;
		}
		Skill skill = fighterVO.getSkill();
		Hero hero = (Hero)behurted;
		boolean nojump = (skill != null && skill.getArrowWay() == ArrowWay.point_one_nojump);// 属于诱导箭
		
		InstanceController instance = behurted.getSceneRef().getInstanceController();
		if ((!behurted.isJumping() || nojump) && !nohurt) {// 不在跳跃的状态
			
			int zhenyuan = hero.getZhenqi();
			if (zhenyuan ==0) {//那就扣血吧
				int nowHp = hero.getNowHp();
				if (reduceHp > nowHp) {
					reduceHp = nowHp;
				}
				
				int changeHp=hero.changeNowHp(-reduceHp);//改变血量并发送消息
				FightMsgSender.directHurtBroadCase( null, hero, 0, 0);
				hero.getEnmityManager().addHurtValue(fighterVO.getFighter(), -changeHp);
				
				if (hero.getNowHp() ==0) {
					
					if (instance.getAttribute("shentong")==null) {
						dieInInstance(behurted.getSceneRef().getInstanceController(),hero);
						
						DujieSucResp sucResp = new DujieSucResp(0, hero.getDujieCtrlor().getHeroDujieData().getProcess());
						hero.sendMsg(sucResp);
						behurted.getSceneRef().getInstanceController().setAttribute("sucComp", Boolean.TRUE);
					}else {
						if ((Integer)instance.getAttribute("shentong")==3) {
							hero.getShenTongSkillManager().freeFuhuo();
							instance.removeAttribute("shentong");
						}else {
							dieInInstance(behurted.getSceneRef().getInstanceController(),hero);
							
							DujieSucResp sucResp = new DujieSucResp(0, hero.getDujieCtrlor().getHeroDujieData().getProcess());
							hero.sendMsg(sucResp);
							behurted.getSceneRef().getInstanceController().setAttribute("sucComp", Boolean.TRUE);
						}
					}
		
				}
				return false;
			}
			//有真元就扣真元吧
			if (reduceHp > zhenyuan) {
				reduceHp = zhenyuan;
			}
			hero.setZhenqi(zhenyuan - reduceHp);
			CharacterOneAttributeMsg49990 oneProp = new CharacterOneAttributeMsg49990(hero, EffectType.zhenqi, zhenyuan - reduceHp);
			hero.sendMsg(oneProp);
			
			hero.getEquipmentController().fightInfluenceEquitment();
			hero.getEnmityManager().addEnmityValue(fighterVO.getFighter(), fighterVO.getEnmityValue());
			
		} else {
			FightMsgSender.directHurtBroadCase(skill == null ? 0 : skill.getId(), whoAttackMe, behurted, 2, fighterVO.getBaoji());
			hero.getMyCharacterAchieveCountManger().getCharacterOtherCount().tiaoshanCount(1);
		}
		
		
		
		if (instance.getAttribute("shentong")!=null) {
			if ((Integer)instance.getAttribute("shentong")==1) {
				hero.getShenTongSkillManager().wudiBuff();
			}
		}
		return false;
	}

	public void onBehurted(VisibleObject killer, VisibleObject behurted, FighterVO vo, int hurtValue) {
	}
	
	private void dieInInstance(InstanceController fuben,Hero behurted) {
		behurted.setObjectState(VisibleObjectState.Idle);
		behurted.getMoveController().stopMove();
//		((VisibleObject)behurted).die(null);
		behurted.getEffectController().clearEffectListAndRemoveBuffOnBody();
		
		DujieSucResp result = new DujieSucResp(0, behurted.getDujieCtrlor().currentTianjieProc());
		behurted.sendMsg(result);
		
		behurted.getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		behurted.getCharacterHorseController().onOwnerDie();
		behurted.getFightController().releaseFightStatus();
		behurted.getEnmityManager().clearEnmityList();
		behurted.getEnmityManager().clearWhosEnmityisMe();
		behurted.setTarget(null);
		
	}
}
