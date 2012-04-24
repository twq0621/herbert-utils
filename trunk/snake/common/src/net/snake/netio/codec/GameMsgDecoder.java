package net.snake.netio.codec;

import java.io.UnsupportedEncodingException;

import net.snake.netio.ClientRequestII;
import net.snake.netio.message.RequestMsg;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.log4j.Logger;

/**
 * 消息解码器。将连续的字节按照协议规范分割成完整的消息包，并包装成RequestMsg。
 * 
 * @author wutao
 */
public class GameMsgDecoder extends CumulativeProtocolDecoder {

	private static Logger logger = Logger.getLogger(GameMsgDecoder.class);

	private static final short BEGINTAG_MSG = 127;
	private int maxPackLength = 1024 * 5;
	private int packHeadLength = 2 + 2;
	private String xml = null;
	private byte[] xmlcontent;

	public GameMsgDecoder() {
		StringBuilder policeFile = new StringBuilder();
		policeFile.append("<cross-domain-policy>\n").append("<site-control permitted-cross-domain-policies=\"all\"/>\n");
		policeFile.append("<allow-access-from domain=\"*\" to-ports=\"*\" />\n").append("</cross-domain-policy>\0");
		xml = policeFile.toString();
		try {
			xmlcontent = xml.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public int getMaxLineLength() {
		return maxPackLength;
	}

	public void setMaxLineLength(int maxLineLength) {
		if (maxLineLength <= 0) {
			throw new IllegalArgumentException("maxLineLength: " + maxLineLength);
		}
		this.maxPackLength = maxLineLength;
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer iobuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
		iobuffer.mark();
		byte cmd = iobuffer.get();
		if (cmd == 60) {
			iobuffer.flip();
			IoBuffer wb = IoBuffer.allocate(xmlcontent.length + 1);
			wb.put(xmlcontent);
			wb.put((byte) 0x0);
			wb.flip();
			session.write(wb);
			return false;
		} else {
			iobuffer.reset();

//			Object http = session.getAttribute("http");
//			if (http == null) {
//				boolean tag = passHttp(iobuffer);
//				if (tag) {
//					session.setAttribute("http", Boolean.TRUE);
//				}
//				return false;
//			}
			Object http=session.getAttribute("http");
			if (http==null) {
				iobuffer.mark();
				short beginTag = iobuffer.getShort();
				iobuffer.reset();
				if (beginTag != BEGINTAG_MSG) {
					boolean tag =passHttp(iobuffer);
					if (tag) {
						session.setAttribute("http", Boolean.TRUE);
					}
				
					return false;	
				}
			}
			
			if (iobuffer.remaining() < packHeadLength) {
				logger.info("buff no message head ");
				return false;
			}
			iobuffer.mark();
			// 读取消息头部分
			short beginTag = iobuffer.getShort();
			if (beginTag != BEGINTAG_MSG) {
				logger.debug(beginTag + "is not start tag");
				session.close(false);
				return false;
			}
			short length = iobuffer.getShort();
			// 检查读取的包头是否正常，不正常的话清空buffer
			if (length < 0 || length > maxPackLength) {
				return false;
			}
			// 读取正常的消息包，并写入输出流中，以便IoHandler进行处理
			else if (length >= packHeadLength && length - packHeadLength <= iobuffer.remaining()) {
				// 整个缓冲区的限制指针
				int oldLimit2 = iobuffer.limit();
				// 在一个完整的消息结尾处重设限制指针
				iobuffer.limit(iobuffer.position() + length - packHeadLength);
				int bodyLen = iobuffer.remaining();
				byte[] body = new byte[bodyLen];
				iobuffer.get(body);
				// 在整个缓冲区的结尾重设限制指针
				iobuffer.limit(oldLimit2);
				RequestMsg request = new ClientRequestII(body);
				// IOStat.getInstance().addRead(request.getTotalBytes());
				protocolDecoderOutput.write(request);
				return true;
			} else {
				// logger.debug("消息头{消息长度域}的值小于消息头的长度，或者剩余数据不是完整的消息体");
				// 如果消息包不完整， 将指针重新移动消息头的起始位置
				iobuffer.reset();
				return false;
			}
		}
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
	}

	public void dispose(IoSession session) throws Exception {
	}

	private boolean passHttp(IoBuffer buffer) {
		buffer.mark();
		int len = buffer.remaining();
		byte[] cont = new byte[len];
		buffer.get(cont);

		int nlCnt = 0;
		for (int i = 0; i < len; i++) {
			if (cont[i] == '\n') {
				nlCnt++;
				if (nlCnt == 3) {
					buffer.position(i+1);
					return true;
				}
			}
		}
		buffer.reset();
		return false;
	}
}
