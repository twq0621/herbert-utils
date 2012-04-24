package net.snake.gamemodel.faction.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.faction.bean.FcByPositionComparator;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.persistence.FactionFlagManager;
import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */
public class FactionOtherInfoMsg51034 extends ServerResponse {

	public FactionOtherInfoMsg51034(FactionController factionController, int isTiaoguo) {
		this.setMsgCode(51034);
		try {
			FactionFlag flag = factionController.getFaction().getFactionFlag();
			this.writeInt(factionController.getFaction().getId());
			this.writeByte(flag.getfGrade());
			this.writeUTF(factionController.getFaction().getBangqiName());
			this.writeByte(factionController.getFaction().getIcoId());
			this.writeUTF(factionController.getFaction().getIcoStr());
			this.writeInt(factionController.getFaction().getFactionFlag().getBufferId());
			this.writeShort(flag.getAttack());
			this.writeShort(flag.getDefence());
			this.writeShort(flag.getHp());
			this.writeShort(flag.getMaxAttackLimite());
			this.writeShort(flag.getMaxDefenceLimite());
			this.writeShort(flag.getMaxHpLimite());
			if (flag.getfGrade() >= 3) {
				this.writeShort(-1);
				this.writeShort(-1);
				this.writeShort(-1);
			} else {
				int grade = flag.getfGrade() + 1;
				FactionFlag nextFlag = FactionFlagManager.getInstance().getFactionFlagByGrade(grade);
				this.writeShort(nextFlag.getAttack());
				this.writeShort(nextFlag.getDefence());
				this.writeShort(nextFlag.getHp());
			}
			this.writeShort(factionController.getOnlineCount());

			this.writeUTF(factionController.getFaction().getViewName());
			this.writeDouble(factionController.getFaction().getCreateDate().getTime());
			this.writeUTF(factionController.getBangzhu().getCce().getViewName());
			this.writeShort(factionController.getFactionCharacterSize());
			this.writeInt(factionController.updateFactionGrade());
			this.writeInt(factionController.getBosskill());
			this.writeUTF(factionController.getFaction().getFactionNotice());
			this.writeUTF(factionController.getFaction().getBangzhanNotice());
			this.writeUTF(factionController.getFaction().getGongchengNotice());
			Collection<FactionCharacter> gugan = factionController.getGuganCollection();
			List<FactionCharacter> list = new ArrayList<FactionCharacter>();
			list.addAll(gugan);
			Collections.sort(list, new FcByPositionComparator());
			this.writeByte(gugan.size());
			for (FactionCharacter fc : list) {
				this.writeInt(fc.getCce().getId());
				this.writeUTF(fc.getCce().getViewName());
				this.writeShort(fc.getCce().getGrade());
				this.writeByte(fc.getPosition());
				this.writeUTF(fc.getName());
				this.writeInt(fc.getContribution());
			}
			this.writeByte(isTiaoguo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
