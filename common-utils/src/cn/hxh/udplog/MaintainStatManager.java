package cn.hxh.udplog;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.DatagramChannelFactory;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaintainStatManager {

	private static Logger logger = LoggerFactory.getLogger(MaintainStatManager.class);

	public static MaintainStatManager mInstance;

	private DatagramChannel channel;
	private DatagramChannelFactory factory;

	public static MaintainStatManager getInstace() {
		if (mInstance == null) {
			mInstance = new MaintainStatManager();
		}
		return mInstance;
	}

	public MaintainStatManager() {
		factory = new NioDatagramChannelFactory(Executors.newCachedThreadPool());

		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(factory);

		// Configure the pipeline factory.
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new StringEncoder(CharsetUtil.UTF_8), new UdpMaintainEncoder(), new MaintainUdpClientHandler());
			}
		});
		bootstrap.setOption("reuseAddress", true);

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("broadcast", false);
		bootstrap.setOption("sendBufferSize", 65536);
		bootstrap.setOption("receiveBufferSize", 8192);
		channel = (DatagramChannel) bootstrap.bind(new InetSocketAddress(StatConstants.MAINTAIN_LOG_IP, StatConstants.MAINTAIN_LOG_PORT));
	}

	public void sendUdpLog(short tableType, String uid, String content) {
		MaintainStatHead head = new MaintainStatHead();
		head.setnUID(uid);
		MaintainStatBody body = new MaintainStatBody();
		body.setUid(uid);
		body.setMsgContent(content);
		body.setTableType(tableType);
		MaintainStat stat = new MaintainStat();
		stat.setHead(head);
		stat.setBody(body);
		logger.info("send udp request!tableType={},content={}", tableType, content);
		// ChannelFuture future = channel
		channel.write(stat);
		// future.addListener(new ChannelFutureListener() {
		// @Override
		// public void operationComplete(ChannelFuture cf) throws Exception {
		// logger.info("Message sent!");
		// }
		// });
	}

	/**
	 * 登陆
	 * 
	 * @param uid
	 *            用户id
	 * @param userInfo
	 *            用户的必要信息
	 */
	public void logLogin(String uid, String userInfo) {
		logMsg(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_LOGIN, 1, uid, userInfo);
	}

	/**
	 * 新安装
	 * 
	 * @param uid
	 *            用户id
	 * @param userInfo
	 *            用户的必要信息
	 */
	public void logRefer(String uid, String userInfo) {
		logMsg(StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_REFER, 1, uid, userInfo);
	}

	/**
	 * 人民币道具的使用，包括购买和消费
	 * manager.logRMBItem(StatConstants.RMB_ITEM_OPERATIONS.SUB, "add001", "25",
	 * "20", 100, 10010, "");
	 * 
	 * @param operateType
	 * @param category1
	 * @param systemItemId
	 * @param unitPrice
	 * @param count
	 * @param uid
	 * @param userInfo
	 */
	public void logRMBItem(StatConstants.RMB_ITEM_OPERATIONS operateType, String category1, String systemItemId, String unitPrice, int count, String uid, String userInfo) {
		logMsg(operateType.toString(), category1, systemItemId, unitPrice, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_PROPS, count, uid, userInfo);
	}

	/**
	 * 支付 manager.logPay("RMB", "150.5", "200", "ooxx", 12084);
	 * 
	 * @param cashType
	 * @param cashTotal
	 * @param virtualMoneyTotal
	 * @param orderId
	 * @param uid
	 */
	public void logPay(String cashType, String cashTotal, String virtualMoneyTotal, String orderId, String uid) {
		logMsg(cashType, cashTotal, virtualMoneyTotal, orderId, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_PAY, 0, uid, StringUtils.EMPTY);
	}

	/**
	 * 自定义事件
	 * 
	 * @param category
	 * @param subCategory
	 * @param uid
	 */
	public void logCustomEvents(String category, String subCategory, String uid) {
		logMsg(category, subCategory, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_ACT, 0, uid, StringUtils.EMPTY);
	}

	/**
	 * 新手引导记录
	 * 
	 * @param category
	 * @param subCategory
	 * @param uid
	 */
	public void logGuide(String category, String subCategory, String uid) {
		logMsg(category, subCategory, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_GUIDE, 0, uid, StringUtils.EMPTY);
	}

	/**
	 * 用来统计用户道具存量
	 * 
	 * @param itemInfo
	 * @param uid
	 */
	public void logInventory(String itemInfo, String uid) {
		logMsg(itemInfo, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_INVENTORY, 0, uid, StringUtils.EMPTY);
	}

	public void logMsg(String str1, String str2, String str3, String str4, String str5, String str6, short tableType, int count, String uid, String extraMsg) {
		StringBuilder sb = new StringBuilder();
		sb.append(str1);
		String separatorOr = "|";
		sb.append(separatorOr);
		sb.append(str2);
		sb.append(separatorOr);
		sb.append(str3);
		sb.append(separatorOr);
		sb.append(str4);
		sb.append(separatorOr);
		sb.append(str5);
		sb.append(separatorOr);
		sb.append(str6);
		sb.append(separatorOr);
		sb.append(count);
		sb.append(separatorOr);
		sb.append(extraMsg);
		String logInfo = sb.toString();
		sendUdpLog(tableType, uid, logInfo);
	}

	public void stop() {
		channel.close();
		factory.releaseExternalResources();
	}

	public static void main(String[] args) throws Exception {
		MaintainStatManager manager = new MaintainStatManager();
		// manager.logRMBItem(StatConstants.RMB_ITEM_OPERATIONS.SUB, "add001",
		// "25", "20", 100, 10010, "");
		// manager.logRefer(12491, "refer from facebook");
		// manager.sendUdpLog(StatConstants.TABLE_ID_LOGIN, 10011,
		// "herbert|1|||||login");
		// manager.logLogin(11987, "灰机哥 login!");
		// manager.logGuide("create_house", "huijige", 12098);
		manager.logCustomEvents("miss1", "send8002", "d893slfj");
		Thread.sleep(500);
		manager.stop();
	}

}
