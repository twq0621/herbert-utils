package net.snake.gamemodel.heroext.channel.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.bean.Channel;
import net.snake.gamemodel.heroext.channel.bean.ChannelRealdragon;
import net.snake.gamemodel.heroext.channel.logic.ChannelJiSuanTools;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50206;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 50203)
public class ChannelProcess50203 extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		
		//跨服判断
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		
		// character 人物角色
		// 要打通穴位id
		final Short channelid = request.getShort();
		// 斤脉铜人
		final byte jingmaitongren = request.getByte();
		
		//验证是突破瓶颈还是充真龙脉
		if(character.getChannelRealdragon().length()>3){
			//真龙充穴
			// 取的当前人物物品
			ChannelRealdragon c = ChannelRealdragonManager.getInstance().getCharactergradeMap().get((int) channelid);
			if(c.getGoodmodelId()!=0){
				int	countJMTR= character.getCharacterGoodController().getBagGoodsCountByModelID(c.getGoodmodelId());
				if(countJMTR< c.getGoodmodelCount().intValue()){
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,60004)); //充真龙脉所需物品不够
					return;
				}
			}
			final ChannelJiSuanTools tools = character.getMyChannelManager().getJiSuanZhenLongTools();
			// 真气判断
			if (character.getZhenqi() < c.getNeedZhenqi().intValue()) {
				ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid,
						1081);
				character.sendMsg(response50206);
				return;
			}
			// 验证当前穴位是不是已经冲通过了
			if (tools.getXueWeiZaiBuZaiJingMaiLi(channelid.toString())) {
				character.sendMsg(new ChannelResponse50206(character.getId(), channelid,1082));
				return;
			} 
			character.getMyChannelManager().addChongXue(channelid, jingmaitongren, c);
			return;
			}
		
		// 取的当前人物有多少斤脉铜人
		int countJMTR = character.getCharacterGoodController().getBagGoodsCountByModelID(
				GoodItemId.JinMaiTongRen);

		final ChannelJiSuanTools tools = character.getMyChannelManager().getJiSuanTools();

		final Channel c = ChannelManager.getInstance().getCharactergradeMap().get((int) channelid);
		if (countJMTR < jingmaitongren) {
			ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid,1080);
			character.sendMsg(response50206);
			return;
		}
		// 真气判断
		if (character.getZhenqi() < c.getNeedZhenqi().intValue()) {
			ChannelResponse50206 response50206 = new ChannelResponse50206(character.getId(), channelid,
					1081);
			character.sendMsg(response50206);
			return;
		}
		// 验证当前穴位是不是已经冲通过了
		if (tools.getXueWeiZaiBuZaiJingMaiLi(channelid.toString())) {
			character.sendMsg(new ChannelResponse50206(character.getId(), channelid,1082));
			return;
		} 

		character.getMyChannelManager().addChongXue(channelid, jingmaitongren, c);
		

	}


}
