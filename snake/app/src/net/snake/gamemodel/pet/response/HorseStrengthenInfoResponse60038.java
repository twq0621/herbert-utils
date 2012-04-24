package net.snake.gamemodel.pet.response;

import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.netio.ServerResponse;

/**
 * 灵宠强化消耗信息
 * 
 * @author serv_dev
 * 
 */
public class HorseStrengthenInfoResponse60038 extends ServerResponse {
	/**
	 * 不可以强化标识(byte)(0不可以)
	 */
	public HorseStrengthenInfoResponse60038() {
		setMsgCode(60038);
		writeByte(0);
	}

	/**
	 * 可以强化标识(byte)(1可以), 1可以强化{消耗真元(int),消耗铜币(int),成功几率(int)}
	 * 
	 * @param character
	 * @param horse
	 * @param addCharacterHorse
	 */
	public HorseStrengthenInfoResponse60038(HorseModel horseModel,int xyval) {
		setMsgCode(60038);
		writeByte(1);
		// writeInt(horse.getId());
		writeInt(horseModel.getJinjieZhenqi());
		writeInt(horseModel.getJinjieCopper());
		writeInt((int)(horseModel.getJinjieSuccessProbability()*0.01));
		writeInt(xyval);
		writeInt(horseModel.getJinjieMaxCount());
		writeInt(horseModel.getJinjieGoodsId());
		writeByte(horseModel.getJinjieGoodsCount());
	}
}
