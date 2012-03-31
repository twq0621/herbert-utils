package lion.core;

import org.jboss.netty.channel.Channel;

public interface IGameService {

	public void channelClose(Channel channel, ChannelClose_C2S dto);

	public void security(Channel channel, Security_C2S securityDto);
}
