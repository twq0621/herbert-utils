package net.snake.stsnet;

import net.snake.gmtool.net.Msg;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import org.apache.log4j.Logger;

public class VSTSDecoder extends CumulativeProtocolDecoder {
	private static Logger logger = Logger.getLogger(VSTSDecoder.class);
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		 if (in.remaining()>= 6) { //功能号short+长度int+内容   6=功能号+长度int
				int oldposition=in.position();
			 	short tfun = in.getShort();
	            int length = in.getInt();	
	            in.position(oldposition);       
	            if (length < 0 || length > 1024*1024*50) {
	            	if (logger.isDebugEnabled()) {
						logger.debug("消息太长:fun:"+tfun+" len:" + length);
					}
	                
	            	session.close(true);	                
	            }
	            if (in.remaining() >= 6 + length) { //取到数据了
	            	//in.flip();
	            	Msg tkmessage = new Msg();
	                tkmessage.setFunction(in.getShort());	                
	                length = in.getInt();
	                if (length > 0) {
	                    byte[] t = new byte[length];
	                    in.get(t);
	            		tkmessage.setContent(t);
	                }
	                out.write(tkmessage);
//	                if(logger.isDebugEnabled()){
//	                	logger.debug(tkmessage.showBytes());
//	                }
	                return true;
	            } 
	        }
		return false;
	}
	
}
