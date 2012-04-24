package net.snake.gamemodel.skill.bow.processor;


import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 弓箭信息查询
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 53031, accessLimit = 100)
public class BowInfomation53031 extends CharacterMsgProcessor {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(BowInfomation53031.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// int bowid = request.getInt();
		// BowModel bowModel = null;
		// Bow bow = character.getBowController().getBow();
		// if (bowid == -1) {
		// if (bow == null) {
		// bowModel = BowModelCacheManager.getInstance().getBowModelById(1);
		// } else {
		// bowModel = bow.getModel();
		// }
		// } else {
		// bowModel = BowModelCacheManager.getInstance().getBowModelById(bowid);
		// }
		// if (bowModel != null) {
		// character.sendMsg(new BowInfomation53032(bowModel, bow));
		// } else {
		// logger.warn("参数错误 弓箭模型为空 bowid=" + bowid + " roleid=" + character.getId());
		// }

	}

}
