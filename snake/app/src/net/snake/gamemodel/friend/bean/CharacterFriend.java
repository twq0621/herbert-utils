package net.snake.gamemodel.friend.bean;

import java.util.Date;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.FriendFavorUpdateMsg10316;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;

public class CharacterFriend implements IbatisEntity {

	/**
	 * t_character_friend.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 角色id，关联t_character表 t_character_friend.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 好友角色id，关联t_character表 t_character_friend.f_friend_id
	 * 
	 */
	private Integer friendId;
	/**
	 * 玩家角色间关系，0 好友， 1黑名单，2仇人 3最近联系人, 4夫妻 t_character_friend.f_relation_type
	 * 
	 */
	private Byte relationType;
	/**
	 * 好感度 t_character_friend.f_favor
	 * 
	 */
	private Integer favor;
	/**
	 * 添加好友时间 t_character_friend.f_add_date
	 * 
	 */
	private Date addDate;
	/**
	 * t_character_friend.f_name
	 * 
	 */
	private String name;
	/**
	 * 仇恨值/如果是夫妻关系的化表示现在拥有的bufferId t_character_friend.f_hate_value
	 * 
	 */
	private Integer hateValue;

	/**
	 * t_character_friend.f_id
	 * 
	 * @return the value of t_character_friend.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_friend.f_id
	 * 
	 * @param id
	 *            the value for t_character_friend.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id，关联t_character表 t_character_friend.f_character_id
	 * 
	 * @return the value of t_character_friend.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id，关联t_character表 t_character_friend.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_friend.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 好友角色id，关联t_character表 t_character_friend.f_friend_id
	 * 
	 * @return the value of t_character_friend.f_friend_id
	 * 
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * 好友角色id，关联t_character表 t_character_friend.f_friend_id
	 * 
	 * @param friendId
	 *            the value for t_character_friend.f_friend_id
	 * 
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	/**
	 * 玩家角色间关系，0 好友， 1黑名单，2仇人 3最近联系人, 4夫妻 t_character_friend.f_relation_type
	 * 
	 * @return the value of t_character_friend.f_relation_type
	 * 
	 */
	public Byte getRelationType() {
		return relationType;
	}

	/**
	 * 玩家角色间关系，0 好友， 1黑名单，2仇人 3最近联系人, 4夫妻 t_character_friend.f_relation_type
	 * 
	 * @param relationType
	 *            the value for t_character_friend.f_relation_type
	 * 
	 */
	public void setRelationType(Byte relationType) {
		this.relationType = relationType;
	}

	/**
	 * 好感度 t_character_friend.f_favor
	 * 
	 * @return the value of t_character_friend.f_favor
	 * 
	 */
	public Integer getFavor() {
		if (favor == null) {
			favor = 0;
		}
		return favor;
	}

	/**
	 * 好感度 t_character_friend.f_favor
	 * 
	 * @param favor
	 *            the value for t_character_friend.f_favor
	 * 
	 */
	public void setFavor(Integer favor) {
		this.favor = favor;
	}

	/**
	 * 添加好友时间 t_character_friend.f_add_date
	 * 
	 * @return the value of t_character_friend.f_add_date
	 * 
	 */
	public Date getAddDate() {
		return addDate;
	}

	/**
	 * 添加好友时间 t_character_friend.f_add_date
	 * 
	 * @param addDate
	 *            the value for t_character_friend.f_add_date
	 * 
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	/**
	 * t_character_friend.f_name
	 * 
	 * @return the value of t_character_friend.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * t_character_friend.f_name
	 * 
	 * @param name
	 *            the value for t_character_friend.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 仇恨值/如果是夫妻关系的化表示现在拥有的bufferId t_character_friend.f_hate_value
	 * 
	 * @return the value of t_character_friend.f_hate_value
	 * 
	 */
	public Integer getHateValue() {
		return hateValue;
	}

	/**
	 * 仇恨值/如果是夫妻关系的化表示现在拥有的bufferId t_character_friend.f_hate_value
	 * 
	 * @param hateValue
	 *            the value for t_character_friend.f_hate_value
	 * 
	 */
	public void setHateValue(Integer hateValue) {
		this.hateValue = hateValue;
	}

	public int teamFavor = 0; // 组队急啥怪物累计等价好感度技术
	private byte isOnline = 0;
	private byte lineNum = 0;
	private byte isHunyan = 0;
	private byte isBaitan = 0;

	public byte getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(byte isOnline) {
		this.isOnline = isOnline;
	}

	public byte getLineNum() {
		if (this.isOnline == 0) {
			this.lineNum = 0;
		}
		return lineNum;
	}

	public void setLineNum(byte lineNum) {
		this.lineNum = lineNum;
	}

	public byte getIsHunyan() {
		return isHunyan;
	}

	public void setIsHunyan(byte isHunyan) {
		this.isHunyan = isHunyan;
	}

	public byte getIsBaitan() {
		return isBaitan;
	}

	public void setIsBaitan(byte isBaitan) {
		this.isBaitan = isBaitan;
	}

	public byte getIconHunyanOrBaitan() {
		if (isHunyan == 1) {
			return isHunyan;
		} else {
			return isBaitan;
		}
	}

	public int getTeamFavor() {
		return teamFavor;
	}

	public void setTeamFavor(int teamFavor) {
		this.teamFavor = teamFavor;
	}

	public void updateTeamFavor(Hero character, int teamFavor) {
		if (teamFavor >= 40) {
			this.teamFavor = 0;
			this.favor = getFavor() + 1;
			character.sendMsg(new FriendFavorUpdateMsg10316(this));
			CharacterFriendManager.getInstance().asynUpdateCharacterFriend(character, this);
		} else {
			this.teamFavor = teamFavor;
		}
	}

	public static CharacterFriend createCharacterFriend(int characterId, int friendId, String fname, byte relationType) {
		CharacterFriend cf = new CharacterFriend();
		cf.setCharacterId(characterId);
		cf.setFriendId(friendId);
		cf.setName(fname);
		cf.setFavor(0);
		cf.setHateValue(0);
		cf.setAddDate(new Date());
		cf.setRelationType(relationType);
		return cf;
	}

	private short friendGrade;

	public short getFriendGrade() {
		return friendGrade;
	}

	public void setFriendGrade(short friendGrade) {
		this.friendGrade = friendGrade;
	}

	public void updateInfo(Hero friend) {
		this.setIsOnline(CommonUseNumber.byte1);
		this.setLineNum((byte) friend.getVlineserver().getLineid());
		this.setIsBaitan((byte) friend.getCharacterStatus());
	}

	private CharacterCacheEntry cce;

	public CharacterCacheEntry getCce() {
		if (this.cce == null) {
			this.cce = CharacterCacheManager.getInstance().getCharacterCacheEntryById(this.friendId);
		}
		return cce;
	}

	public String getViewName() {
		CharacterCacheEntry cce = getCce();
		if (cce == null) {
			return "";
		}
		return getCce().getViewName();
	}
}
