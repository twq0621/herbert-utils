package net.snake.ai.fight.upgrade.response;

import org.apache.log4j.Logger;

import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;


/**
 * 查询突破明细
 * @author serv_dev
 *
 */
public class QuerySkillPoInfoMsg10292 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(QuerySkillPoInfoMsg10292.class);
	public QuerySkillPoInfoMsg10292(CharacterSkill characterskill) {
		setMsgCode(10292);
		SkillUpgradeExp skillUpgradeExp = characterskill.upgradeNeedZhenqi();
		try {
			writeInt(characterskill.getSkillId());
			writeUTF(skillUpgradeExp.getTargetGoods() == null ? "" : skillUpgradeExp.getTargetGoods());
			writeInt(skillUpgradeExp.getPoNeedZhenqi() == null ? 0 : skillUpgradeExp.getPoNeedZhenqi());
			writeInt(skillUpgradeExp.getPoNeedCopper() == null ? 0 : skillUpgradeExp.getPoNeedCopper());
			writeInt(skillUpgradeExp.getPoCooltime() == null ? 0 : skillUpgradeExp.getPoCooltime());
			writeInt(skillUpgradeExp.getPoNeedLv()== null ? 0 : skillUpgradeExp.getPoNeedLv() );
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
}
