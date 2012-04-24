package net.snake.gamemodel.skill.bow.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class BowUpGradeQueryResult53034 extends ServerResponse {

	public BowUpGradeQueryResult53034(Bow bow, Hero c) {
		setMsgCode(53034);
		try {
			writeInt(bow.getModel().getId());
			writeUTF(bow.getModel().getNameI18n());
			writeInt(bow.getNextModel() != null ? bow.getNextModel().getId() : -1);
			writeUTF(bow.getNextModel() != null ? bow.getNextModel().getNameI18n() : "");
			writeInt(bow.getFaildcount());
			writeInt(bow.getModel().getUpconsumeCopper());
			writeInt(bow.getModel().getUpconsumeGoodid());
			writeInt(bow.getModel().getUpconsumeGoodcount());
			writeInt(bow.getModel().getUpProbabilityShow());
			writeInt(bow.getNowLuck());
			// 弓箭ID（int）,当前弓箭(utf),弓箭ID（int）,可进阶为(utf),幸运值(int),进阶消耗铜币(int),进阶材料ID(int),进阶材料数量(int),进阶成功几率(int)
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
