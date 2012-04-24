package net.snake.gamemodel.faction.response.factioncity;

import net.snake.netio.ServerResponse;


public class FactionCtMsg51130 extends ServerResponse {

//	public FactionCtMsg51130() {
//		this.setMsgCode(51130);
////		try {
//			if (!GongchengTsMap.isGongchenging) {
//				this.writeByte(0);
//			} else {
//				DayGongchengDateList ddList = GongchengDateManager
//						.getInstance().getTodayGongChengDateList();
//				FactionCity factionCity = FactionCityManager.getInstance()
//						.getFactionCity();
//				if (ddList == null || ddList.getList().size() == 0) {
//					this.writeShort(0);
//					return;
//				}
//				int count = 0;
//				if (factionCity != null && factionCity.getYdFactionId() > 0) {
//					count = 1;
//				}
//				this.writeByte(1);
//				List<GongchengDate> list = ddList.getList();
//				this.writeShort(ddList.getList().size() + count);
//				if (count > 0) {
//					this.writeInt(factionCity.getYdFactionId());
//				}
//				for (GongchengDate gongcheng : list) {
//					this.writeInt(gongcheng.getFactionId());
//				}
//			}
////		} catch (IOException e) {
////			logger.error(e.getMessage(),e);
////		}
//	}
}
