package net.snake.gamemodel.dujie.processor;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.bean.DujieAdd;
import net.snake.gamemodel.dujie.bean.Guards;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.persistence.DujieAddDataTbl;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.response.DujieAllStateResp;
import net.snake.gamemodel.dujie.response.LeavDujieFuben;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 主动要求离开副本,外带着获取收益。
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode = 60309)
public class DujieLeavPro extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);

		
		final DujieCtrlor ctrlor = hero.getDujieCtrlor();
		Dujie dujie=DujieDataTbl.getInstance().getDujie(ctrlor.currentTianjieNum());
		int need = dujie.getZhenyuan();
		int proc = ctrlor.getHeroDujieData().getProcess();
		if (proc >= need) {
			propAdd2Hero(hero, ctrlor.getHeroDujieData().getNum());
			ctrlor.finishDujie();
			if (hero.getDujieCtrlor().getHeroDujieData().getIsguard()==1) {
				Guards.updateElement(hero);
			}
			
			GameServer.executorServiceForDB.execute(new Runnable() {

				@Override
				public void run() {
					try {
						ctrlor.save2DB();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
			
		}
		
		int currentLvl = ctrlor.currentTianjieNum();
		int currentStat = ctrlor.currentTianjieStat();
		int currentProc = ctrlor.currentTianjieProc();

		DujieAllStateResp resp = new DujieAllStateResp();
		resp.writeAllState(currentLvl, currentStat, ctrlor.getHeroDujieData().getRoushen(),currentProc,ctrlor.getHeroDujieData().getIsguard());
		hero.sendMsg(resp);
		
		CharacterPropertyManager.changeNowHp(hero, hero.getMaxHp());
		
		
		if (proc >= need) {
			LeavDujieFuben leavDujieFuben = new LeavDujieFuben(1);
			hero.sendMsg(leavDujieFuben);
		}else {
			LeavDujieFuben leavDujieFuben = new LeavDujieFuben(0);
			hero.sendMsg(leavDujieFuben);
		}	
		
		InstanceController controller = hero.getMyCharacterInstanceManager().getInstanceController();
		if(controller == null){
			return ;
		}
		
		hero.getMyCharacterInstanceManager().endInstanceToXiangyang();

		
		
	}
	
	/**
	 * 奖励
	 * 
	 * @param hero
	 * @param dujieLvl
	 */
	private void propAdd2Hero(Hero hero, int dujieLvl) {
		DujieAdd add = DujieAddDataTbl.getInstance().getDujieAdd();
		Dujie dujie = DujieDataTbl.getInstance().getDujie(dujieLvl);
		int popsigner = hero.getPopsinger();

		float hp = dujie.getAdd_hp() * add.getAddhpByPopsigner(popsigner);
		float mp = dujie.getAdd_mp() * add.getAddmpByPopsigner(popsigner);
		float atk = dujie.getAdd_attack() * add.getAddattackByPopsigner(popsigner);
		float dfn = dujie.getAdd_defence() * add.getAdddefenceByPopsigner(popsigner);
		float sp = dujie.getAdd_sp();
		float as = dujie.getAdd_as();
		float ms = dujie.getAdd_ms();
		float crt = dujie.getAdd_crt() * add.getAddcrtByPopsigner(popsigner);
		float dodge = dujie.getAdd_dodge() * add.getAdddefenceByPopsigner(popsigner);
		float hit = dujie.getAdd_hit() * add.getAddhitByPopsigner(popsigner);
		float exp = dujie.getAdd_exp();
		float copp = dujie.getAdd_copper();

		CharacterPropertyManager.changeMaxHp(hero, (int) hp);
		CharacterPropertyManager.changeMaxMp(hero, (int) mp);
		CharacterPropertyManager.changeMaxSp(hero, (int) sp);
		CharacterPropertyManager.changeAttack(hero, (int) atk);
		CharacterPropertyManager.changeDefence(hero, (int) dfn);
		CharacterPropertyManager.changeMoveSpeed(hero, (int) ms);
		CharacterPropertyManager.changeCrt(hero, (int) crt);
		CharacterPropertyManager.changeDodge(hero, (int) dodge);
		CharacterPropertyManager.changeAtkColdtime(hero, (int) as);
		CharacterPropertyManager.changeHit(hero, (int) hit);
		CharacterPropertyManager.changeCopper(hero, (int) copp, CopperAction.ADD_TASKORINSTANCE);
		CharacterFormula.experienceProcess(hero, (int) exp);
		hero.getPropertyAdditionController().recompute();
		
		int[] skills = dujie.getSkillUpdate(popsigner);
		if (skills == null) {
			return ;
		}
		CharacterSkillManager csm = hero.getCharacterSkillManager();
		for (int i = 0; i < skills.length; i = i+2) {
			CharacterSkill characterskill = csm.getCharacterSkillById(skills[i]);
			if (characterskill != null) {
				csm.characterSkillUpgrade(skills[i], characterskill.getGrade() + skills[i+1]);
			}
		}

	}
}
