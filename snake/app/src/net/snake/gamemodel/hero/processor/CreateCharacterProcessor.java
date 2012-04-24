package net.snake.gamemodel.hero.processor;

import java.util.List;
import java.util.regex.Pattern;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.SessionKey;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.CharacterForList;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterFactory;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.response.CreateCharacterFailResponse10016;
import net.snake.gamemodel.hero.response.CreateCharacterResponse10016;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;


/**
 * 创建角色处理
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10015, accessLimit = 100)
public class CreateCharacterProcessor extends MsgProcessor implements IThreadProcessor {

//	private static Logger logger = Logger.getLogger(CreateCharacterProcessor.class);

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		int accountId = session.getAccountid();
		String preName = request.getString();

		byte sect = request.getByte();

		byte ico = request.getByte();

		byte sex = request.getByte();
//		if (logger.isDebugEnabled()) {
//			logger.debug("昵称：{}", preName);
//			logger.debug("门派：{}", sect);
//			logger.debug("头像 {}", ico);
//			logger.debug("性别 {}", sex);
//		}
		CharacterManager characterManager = CharacterManager.getInstance();
		// 判断是否超过最大角色数限制=======================
		int sid = Integer.parseInt((String) session.getAttribute(SessionKey.sid));
		List<CharacterForList> characterlist = null;
		if (sid == 0) {// 测试用
			characterlist = characterManager.getCharacterByAccountId(accountId);
		} else {
			// 正式用查询该服的角色数据
			characterlist = characterManager.getCharacterByAccountIdAndSid(accountId, sid);
		}

		if (characterlist.size() >= 5) {
			session.sendMsg(new CreateCharacterFailResponse10016(40005));
			return;
		}
		// 验证名子合法性=================================
		int msgKey = checkCharacterInfoRightful(accountId, preName, sect, ico, sex);
		if (msgKey != 0) {
			session.sendMsg(new CreateCharacterFailResponse10016(msgKey));
			return;
		}
		// 设置默认值===========================
		CharacterManager cm = CharacterManager.getInstance();
		if (cm.isExistName(preName)) {
			session.sendMsg(new CreateCharacterFailResponse10016(40006));
			return;
		}

		Hero character = CharacterFactory.createCharacter(accountId, preName, sect, ico, sex, session);

		if (character == null) {
			return;
		}
		session.sendMsg(new CreateCharacterResponse10016(character));

		// 同步各线服务器角色数据===================================================
		CharacterCacheManager.getInstance().getCharacterMap().put(character.getId(), new CharacterCacheEntry(character));
		System.out.println(CharacterCacheManager.getInstance().getCharacterMap().size());
		System.out.println(CharacterCacheManager.getInstance().getCharacterCacheEntryById(character.getId()));
	}

	/**
	 * 验证信息的完整性
	 * 
	 * @param accountId
	 *            账号
	 * @param preName
	 *            昵称
	 * @param sect
	 *            门派
	 * @param ico
	 *            头像
	 * @param sex
	 *            性别
	 * @return
	 */
	private int checkCharacterInfoRightful(int accountId, String preName, byte sect, byte ico, byte sex) {
		int msg = 0;
		int nameLen = preName.toCharArray().length;
		if (nameLen > 6) {
			msg = 40007;
			return msg;
		}
		if (nameLen < 2) {
			msg = 40008;
		}
		Pattern pattern = Pattern.compile(Options.ChineseChars_6);
		boolean tf = pattern.matcher(preName).matches();
		if (!tf) {
			msg = 40009;
			return msg;
		}
		if (BadWordsFilter.getInstance().hashBadWords(preName)) {
			msg = 40010;
			return msg;
		}
		return msg;

	}

}
