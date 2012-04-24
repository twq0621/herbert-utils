package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.activities.bean.ActivationCard;
import net.snake.gamemodel.activities.bean.CharacterActivationCard;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * @description 账号领取兑换码信息
 * @author serv_dev
 */
public class CharacterActivationCardManager {

	private static Logger logger = Logger.getLogger(CharacterActivationCardManager.class);

	private CharacterActivationCardManager() {
	}

	public static CharacterActivationCardManager getInstance() {
		if (instance == null) {
			instance = new CharacterActivationCardManager();
		}
		return instance;
	}

	private static CharacterActivationCardManager instance;

	/**
	 * 服务器类型卡领取记录
	 * <p>
	 * key为“服务器ID_卡类型_卡号”拼接ID
	 * 
	 * 每个服务器的卡编号唯一，但不同不服务器可能有卡号重复 所以编写三层map
	 * </p>
	 */
	private Map<String, CharacterActivationCard> characterActivationCardMap = new ConcurrentHashMap<String, CharacterActivationCard>();

	/**
	 * 服务器类型卡用户记录
	 * <p>
	 * key为“服务器ID_卡类型_用户账号ID”拼接ID
	 * 
	 * 相同类型的激活码一个帐号一个区只能激活一次 所以编写三层map
	 * </p>
	 */
	private Map<String, CharacterActivationCard> accountActivationCardMap = new ConcurrentHashMap<String, CharacterActivationCard>();

	private CharacterActivationCardDAO characterActivationCardDAO = new CharacterActivationCardDAO(SystemFactory.getCharacterSqlMapClient());

	/**
	 * @description 初始化角色领取兑换码列表
	 */
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			List<CharacterActivationCard> characterActivationCardList = characterActivationCardDAO.select();
			if (characterActivationCardList == null || characterActivationCardList.isEmpty()) {
				return;
			}
			this.initCharacterActivationCardMap(characterActivationCardList);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @description 初始化兑换码领取信息集合
	 * @param characterActivationCardList
	 */
	private void initCharacterActivationCardMap(List<CharacterActivationCard> characterActivationCardList) {
		characterActivationCardMap.clear();
		accountActivationCardMap.clear();
		for (CharacterActivationCard characterActivationCard : characterActivationCardList) {
			String key = this.appendKeyForCardMap(characterActivationCard.getSid(), characterActivationCard.getCardType(), characterActivationCard.getCardNo());

			characterActivationCardMap.put(key, characterActivationCard);

			String key1 = this.appendKeyForAccountMap(characterActivationCard.getSid(), characterActivationCard.getCardType(), characterActivationCard.getAccountId());

			accountActivationCardMap.put(key1, characterActivationCard);
		}
	}

	/**
	 * @description 兑换卡是否被领取
	 * @param character
	 *            角色对象
	 * @param card
	 *            兑换码对象
	 * @return true为已领取，false为未领取
	 */
	public boolean isExchangeForCard(Integer sid, ActivationCard card) {
		String key = this.appendKeyForCardMap(sid, card.getType(), card.getCardNo());
		return characterActivationCardMap.containsKey(key);
	}

	/**
	 * @description 账号是否领取过兑换卡
	 * @param character
	 *            角色对象
	 * @param card
	 *            兑换码对象
	 * @return true为已领取，false为未领取
	 */
	public boolean isExchangeForAccount(Hero character, ActivationCard card) {
		String key = this.appendKeyForAccountMap(character.getOriginalSid(), card.getType(), character.getAccountInitiallyId());

		return accountActivationCardMap.containsKey(key);
	}

	/**
	 * @description 完成激活码兑换
	 * @param character
	 *            兑换激活码的角色
	 * @param card
	 *            激活码对象
	 * @return null则说明兑换失败
	 */
	public CharacterActivationCard doExchange(Hero character, ActivationCard card) {
		// 判断卡片在此服务器是否已经被领取
		if (this.isExchangeForCard(character.getOriginalSid(), card)) {
			return null;
		}
		if (this.isExchangeForAccount(character, card)) {
			return null;
		}
		List<CharacterGoods> list = card.getGoodlist(character.getPopsinger());
		if (list != null && !list.isEmpty()) {
			// 添加物品到背包（判断背包空格放在prosecc里）
			if (!character.getCharacterGoodController().getBagGoodsContiner().addGoods(list)) {
				return null;
			}
		}

		// 添加铜币
		if (card.getCopper() != null && card.getCopper().intValue() > 0) {
			CharacterPropertyManager.changeCopper(character, card.getCopper().intValue(), CopperAction.ADD_OTHER);
		}
		// 添加礼金
		if (card.getLijin() != null && card.getLijin().intValue() > 0) {
			CharacterPropertyManager.changeLijin(character, card.getLijin().intValue());
		}

		CharacterActivationCard characterActivationCard = this.createCharacterActivationCard(character, card);

		this.addCharacterActivationCard(characterActivationCard);

		return characterActivationCard;
	}

	/**
	 * @description 创建新领取信息
	 * @param character
	 *            角色对象
	 * @param card
	 *            卡对象
	 * @return
	 */
	private CharacterActivationCard createCharacterActivationCard(Hero character, ActivationCard card) {
		CharacterActivationCard characterActivationCard = new CharacterActivationCard();
		characterActivationCard.setAccountId(character.getAccountInitiallyId());
		characterActivationCard.setCardNo(card.getCardNo());
		characterActivationCard.setCardType(card.getType());
		characterActivationCard.setCharacterGrade((int) character.getGrade());
		characterActivationCard.setCharacterId(character.getCharacterInitiallyId());
		characterActivationCard.setIp(character.getLastip());
		characterActivationCard.setSid(character.getOriginalSid());
		characterActivationCard.setTime(new Date());

		return characterActivationCard;
	}

	/**
	 * @description 添加新领取信息
	 * @param card
	 *            领取信息对象
	 */
	private void addCharacterActivationCard(CharacterActivationCard card) {

		// 添加内存领取记录
		String key = appendKeyForCardMap(card.getSid(), card.getCardType(), card.getCardNo());
		characterActivationCardMap.put(key, card);

		String key1 = this.appendKeyForAccountMap(card.getSid(), card.getCardType(), card.getAccountId());

		accountActivationCardMap.put(key1, card);
		// 添加新领取记录
		insertAsyncCharacterActivationCard(card);
	}

	/**
	 * @description 异步添加领取记录
	 * @param card
	 *            领取信息对象
	 */
	private void insertAsyncCharacterActivationCard(final CharacterActivationCard card) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					characterActivationCardDAO.insert(card);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * @description 拼接mapkey规则 ： “服务器ID_卡类型_卡号”
	 * @param sid
	 *            服务器ID
	 * @param cardType
	 *            卡类型
	 * @param cardNo
	 *            卡号
	 * @return
	 */
	private String appendKeyForCardMap(Integer sid, byte cardType, String cardNo) {
		String key = "";
		key += sid + "_";
		key += cardType + "_";
		key += cardNo;

		return key;
	}

	/**
	 * @description 拼接mapkey规则 ： “服务器ID_卡类型_运营ID”
	 * @param sid
	 *            服务器ID
	 * @param cardType
	 *            卡类型
	 * @param accountId
	 *            账号ID
	 * @return
	 */
	private String appendKeyForAccountMap(Integer sid, byte cardType, Integer accountId) {
		String key = "";
		key += sid + "_";
		key += cardType + "_";
		key += accountId;

		return key;
	}

}
