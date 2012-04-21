package cn.hxh.udplog;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class UdpMaintainEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		ChannelBuffer buf;
		MaintainStat stat = (MaintainStat) msg;
		MaintainStatBody body = stat.getBody();
		MaintainStatHead head = stat.getHead();
		int bodySize = body.getSize();
		head.setnPackageLength(bodySize + head.getSize());
		// buf = ChannelBuffers.dynamicBuffer();
		buf = ChannelBuffers.buffer(bodySize + head.getSize() + 1);
		// write head
		buf.writeByte(head.getHttpHead());
		buf.writeInt(head.getnPackageLength());
		int headUid = MaintainBuffHelper.getInstance().uidToInt(head.getnUID());
		System.out.println(headUid);
		buf.writeInt(headUid);
		buf.writeShort(head.getShFlag());
		buf.writeShort(head.getShOptionalLen());
		// buf.writeShort(head.getLpbyOptional());
		buf.writeShort(head.getShHeaderLen());
		buf.writeShort(head.getShMessageID());
		buf.writeShort(head.getShMessageType());
		buf.writeShort(head.getShVersionType());
		buf.writeShort(head.getShVersion());
		buf.writeInt((int) head.getnPlayerID());
		buf.writeInt((int) head.getnSequence());
		// write body
		int bodyUid = MaintainBuffHelper.getInstance().uidToInt(body.getUid());
		buf.writeInt(bodyUid);
		buf.writeShort(body.getTableType());
		MaintainBuffHelper.getInstance()
				.encodeString(buf, body.getMsgContent());
		return buf;
	}
}
