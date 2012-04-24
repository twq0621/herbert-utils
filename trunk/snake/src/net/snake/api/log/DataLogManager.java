package net.snake.api.log;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author jack
 * 
 */
public class DataLogManager {

	private static Logger logger = Logger.getLogger("UDPLog");

	public static DataLogManager mInstance;

	UdpManager udpManager;
	private ExecutorService runner = null;

	public DataLogManager(Properties config) {
		this(config.getProperty("server.logaddress"), Integer.parseInt(config.getProperty("server.logserverport")), Integer.parseInt(config.getProperty("server.logthreadsize")));
	}

	public DataLogManager(String ip, int port, int poolSize) {
		runner = Executors.newFixedThreadPool(poolSize);
		udpManager = new BlankUdpManager(ip, port);// MinaUdpManager(address, port); // NettyUdpManager();//
	}

	public void sendUdpLog(final short tableType, final String uid, final String content) {
		runner.execute(new Runnable() {
			public void run() {
				MaintainStatHead head = new MaintainStatHead();
				head.setnUID(uid);
				MaintainStatBody body = new MaintainStatBody();
				body.setUid(uid);
				body.setMsgContent(content);
				body.setTableType(tableType);
				MaintainStat stat = new MaintainStat();
				stat.setHead(head);
				stat.setBody(body);
				udpManager.write(stat);
			}
		});
	}

	public void stop() {
		udpManager.stop();
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
	 * 使用人民币购买道具 manager.logRMBItem("1", "25", "20", 100, 10010, "");
	 * 
	 * @param category1
	 *            一次性=1，时效性=2，永久性=3
	 * @param goodsId
	 *            道具唯一id
	 * @param unitPrice
	 *            道具单价
	 * @param count
	 *            购买的数量
	 * @param uid
	 *            角色id
	 * @param userInfo
	 *            角色名
	 */
	public void logRMBBuyItem(String category1, int goodsId, int unitPrice, int count, String uid, String userInfo) {
		logRMBItem(StatConstants.RMB_ITEM_OPERATIONS.ADD, category1, goodsId, unitPrice, StringUtils.EMPTY, count, uid, userInfo);
	}

	/**
	 * 使用人民币消费道具 manager.logRMBItem("1", "25", "20", 100, 10010, "");
	 * 
	 * @param category1
	 *            一次性=1，时效性=2，永久性=3
	 * @param goodsId
	 *            道具唯一id
	 * @param unitPrice
	 *            道具单价
	 * @param time
	 *            单位秒，失效时间
	 * @param count
	 *            消耗数量
	 * @param uid
	 *            角色id
	 * @param userInfo
	 *            角色名
	 */
	public void logRMBUseItem(String category1, int goodsId, int unitPrice, long time, int count, String uid, String userInfo) {
		logRMBItem(StatConstants.RMB_ITEM_OPERATIONS.SUB, category1, goodsId, unitPrice, String.valueOf(time), count, uid, userInfo);
	}

	/**
	 * 人民币道具的使用，包括购买和消费 manager.logRMBItem(StatConstants.RMB_ITEM_OPERATIONS.SUB, "add001", "25", "20", 100, 10010, "");
	 * 
	 * @param operateType
	 * @param category1
	 *            一次性=1，时效性=2，永久性=3
	 * @param goodsId
	 *            道具唯一id
	 * @param unitPrice
	 *            道具单价
	 * @param count
	 * @param uid
	 * @param userInfo
	 */
	private void logRMBItem(StatConstants.RMB_ITEM_OPERATIONS operateType, String category1, int goodsId, int unitPrice, String time, int count, String uid, String userInfo) {
		logMsg(operateType.toString(), category1, String.valueOf(goodsId), String.valueOf(unitPrice), time, StringUtils.EMPTY, StatConstants.TABLE_ID_PROPS, count, uid, userInfo);
	}

	/**
	 * 支付 manager.logPay("RMB", "150.5", "200", "ooxx", 12084);
	 * 
	 * @param cashType
	 *            货币单位
	 * @param cashTotal
	 *            付费金额
	 * @param virtualMoneyTotal
	 *            虚拟币数量
	 * @param orderId
	 *            支付套餐号
	 * @param uid
	 *            角色id
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
	 *            {"1002":31,"1006":4} 所有道具的json字符串,格式为道具id:数量
	 * @param uid
	 */
	public void logInventory(String itemInfo, String uid) {
		logMsg(itemInfo, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, StatConstants.TABLE_ID_INVENTORY, 0, uid, StringUtils.EMPTY);
	}

	public void logMsg(String str1, String str2, String str3, String str4, String str5, String str6, short tableType, int count, String uid, String extraMsg) {
		StringBuilder sb = new StringBuilder();
		sb.append(str1);
		sb.append(SEPARATOR_OR);
		sb.append(str2);
		sb.append(SEPARATOR_OR);
		sb.append(str3);
		sb.append(SEPARATOR_OR);
		sb.append(str4);
		sb.append(SEPARATOR_OR);
		sb.append(str5);
		sb.append(SEPARATOR_OR);
		sb.append(str6);
		sb.append(SEPARATOR_OR);
		sb.append(count);
		sb.append(SEPARATOR_OR);
		sb.append(extraMsg);
		String logInfo = sb.toString();
		logger.info("tableType=" + tableType + ",content=" + logInfo);
		sendUdpLog(tableType, uid, logInfo);
	}

	public static final String SEPARATOR_OR = "|";

	public static void main(String[] args) throws Exception {
		DataLogManager manager = new DataLogManager("127.0.0.1", 1122, 1);
		Thread.sleep(1000);
		manager.logRMBItem(StatConstants.RMB_ITEM_OPERATIONS.SUB, "add001", 25, 20, "10000", 100, "1111111", "");
		manager.logRefer("1111111", "refer from facebook");
		manager.sendUdpLog(StatConstants.TABLE_ID_LOGIN, "1111111", "herbert|1|||||login");
		manager.logLogin("1111111", "灰机哥 login!");
		Thread.sleep(15000);
		manager.logGuide("create_house", "huijige", "1111111");
		manager.logCustomEvents("love", "send8002", "1111111");
		Thread.sleep(500);
		// manager.stop();
		manager.runner.shutdown();
	}

}
