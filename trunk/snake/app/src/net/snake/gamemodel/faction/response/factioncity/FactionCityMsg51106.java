package net.snake.gamemodel.faction.response.factioncity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.fight.bean.DayGongchengDateList;
import net.snake.gamemodel.fight.bean.GongchengDate;
import net.snake.gamemodel.fight.bean.GongchengDayComparator;
import net.snake.netio.ServerResponse;

/**
 * 返回攻城日程查询
 * 
 * @author serv_dev
 * 
 */
public class FactionCityMsg51106 extends ServerResponse {
	public FactionCityMsg51106(Collection<DayGongchengDateList> collection) {
		this.setMsgCode(51106);
		List<DayGongchengDateList> list = new ArrayList<DayGongchengDateList>();
		list.addAll(collection);
		Collections.sort(list, new GongchengDayComparator());
		FactionCity factionC = FactionCityManager.getInstance().getFactionCity();
		try {
			this.writeByte(list.size());
			for (int i = 0; i < list.size(); i++) {
				DayGongchengDateList gongchengList = list.get(i);
				this.writeDouble(gongchengList.getGongchengDate().getTime());
				this.writeDouble(gongchengList.getGongchengDate().getTime());
				if (i == 0) {
					if (factionC != null && factionC.getFactionId() > 0) {
						this.writeByte(1);
						this.writeInt(factionC.getFactionId());
						FactionController factionController = FactionManager.getInstance().getFactionControllerByFactionID(factionC.getFactionId());
						this.writeUTF(factionController.getFaction().getViewName());
					} else {
						this.writeByte(0);
					}
				} else {
					this.writeByte(0);
				}
				List<GongchengDate> gongchengL = gongchengList.getList();
				this.writeShort(gongchengL.size());
				for (GongchengDate gongcheng : gongchengL) {
					this.writeInt(gongcheng.getFactionId());
					FactionController factionController = FactionManager.getInstance().getFactionControllerByFactionID(gongcheng.getFactionId());
					if (factionController != null) {
						this.writeUTF(factionController.getFaction().getViewName());
					} else {
						this.writeUTF("");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
}
