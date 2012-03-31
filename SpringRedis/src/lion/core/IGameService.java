package lion.core;

import org.jboss.netty.channel.Channel;

public interface IGameService {

	public void channelClose(Channel channel, ChannelClose_C2S dto);
}
