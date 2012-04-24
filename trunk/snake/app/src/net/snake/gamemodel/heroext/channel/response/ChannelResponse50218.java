package net.snake.gamemodel.heroext.channel.response;
/**
 * 
 * 
 * @author serv_dev
 */
import net.snake.commons.StringUtil;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.bean.Channel;
import net.snake.gamemodel.heroext.channel.bean.ChannelRealdragon;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.netio.ServerResponse;


public class ChannelResponse50218 extends ServerResponse{

	public ChannelResponse50218(Hero character){
		setMsgCode(50218);
//		try {
		if(StringUtil.isEmpty(character.getMyChannelManager().getFailChannelId())){
			writeByte(0);
		}else {
			String idString[] = character.getMyChannelManager().getFailChannelId().split(",");
			writeByte(idString.length);
			for (String string : idString) {
				writeByte(Byte.valueOf(string.substring(0,1)));
				long time =0 ;
				if(character.getMyChannelManager().getDatongjinmai() <= 8 && StringUtil.isEmpty(character.getChannelRealdragon())){
					Channel c = ChannelManager.getInstance().getCharactergradeMap().get(Integer.valueOf(string));
					time = character.getEffectController().getBuffRemainTime(c.getDebuffId());
				}else if(character.getMyChannelManager().getDatongjinmaiZhenLong() > 0){
					ChannelRealdragon c = ChannelRealdragonManager.getInstance().getCharactergradeMap().get(Integer.valueOf(string));
					time = character.getEffectController().getBuffRemainTime(c.getDebuffId());
				}
				writeInt((int)time/1000);
			}
		}
			
//		} catch (Exception e) {
//			logger.error(e.getMessage(),e);
//		}
	}
	
}
