package net.snake.gamemodel.faction.response.factioncity;

import net.snake.netio.ServerResponse;


/**
 * 襄阳城城主(或帮会)变化
 * 
 * @author serv_dev
 * 
 */
public class FactionCtMsg51140 extends ServerResponse {

//	public FactionCtMsg51140(FactionCity factionCity, Hero character1) {
//		this.setMsgCode(51140);
//			if (factionCity == null) {
//				this.writeInt(0);
//				this.writeInt(0);
//			} else if (factionCity.getFactionId() < 1) {
//				if (GongchengTsMap.isGongchenging) {
//					if (MyFactionCityZhengDuoManager.monster == null) {
//						this.writeInt(0);
//						this.writeInt(0);
//					} else {
//						Hero character = MyFactionCityZhengDuoManager.monster
//								.getYoulongCharacter();
//						if (character == null) {
//							this.writeInt(0);
//							this.writeInt(0);
//						} else {
//							this.writeInt(character
//									.getMyFactionManager().getFactionId());
//							this.writeInt(character.getId());
//						}
//					}
//				} else {
//					this.writeInt(0);
//					this.writeInt(0);
//				}
//			} else {
//				this.writeInt(factionCity.getFactionId());
//				FactionController factionController = FactionManager
//						.getInstance().getFactionControllerByFactionID(
//								factionCity.getFactionId());
//				this.writeInt(factionController.getBangzhu()
//						.getCharacterId());
//			}
//	}
//
//	/**
//	 * 守成时发送
//	 * 
//	 * @param character
//	 */
//	public FactionCtMsg51140(Hero character) {
//		this.setMsgCode(51140);
////		try {
//			if (character == null) {
//				this.writeInt(0);
//				this.writeInt(0);
//			} else {
//				this.writeInt(character.getMyFactionManager()
//						.getFactionId());
//				this.writeInt(character.getId());
//			}
////		} catch (Exception e) {
////			logger.error(e.getMessage(),e);
////		}
//	}
}
