package net.snake.api.log.udp;

import net.snake.api.log.MaintainStat;
import net.snake.api.log.MaintainStatBody;
import net.snake.api.log.MaintainStatHead;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class UdpMaintainEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		MaintainStat stat = (MaintainStat) message;
		MaintainStatBody body = stat.getBody();
		MaintainStatHead head = stat.getHead();
		int bodySize = body.getSize();
		head.setnPackageLength(bodySize + head.getSize());
		IoBuffer buf = IoBuffer.allocate(bodySize + head.getSize() + 1);
		buf.put(head.getHttpHead());
		buf.putInt(head.getnPackageLength());
		int headUid = MaintainBuffHelper.getInstance().uidToInt(head.getnUID());
		buf.putInt(headUid);
		buf.putShort((short) head.getShFlag());
		buf.putShort((short) head.getShOptionalLen());
		buf.putShort(head.getShHeaderLen());
		buf.putShort((short) head.getShMessageID());
		buf.putShort((short) head.getShMessageType());
		buf.putShort((short) head.getShVersionType());
		buf.putShort((short) head.getShVersion());
		buf.putInt((int) head.getnPlayerID());
		buf.putInt((int) head.getnSequence());
		buf.putInt(headUid);
		buf.putShort(body.getTableType());
		MaintainBuffHelper.encodeString(buf, body.getMsgContent());
		out.write(buf);
	}
}
