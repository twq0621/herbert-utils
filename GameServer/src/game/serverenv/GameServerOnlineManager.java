package game.serverenv;

import game.entity.Hero;

import java.util.Collection;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 在线用户缓存管理
 * 
 * @author hexuhui
 * 
 */
public class GameServerOnlineManager {

	private volatile Map<Integer, Hero> onlineCharacterMap = new ConcurrentHashMap<Integer, Hero>();// characterid,character
	private volatile Queue<Hero> onlineHeros = new ConcurrentLinkedQueue<Hero>();
	private volatile Map<Integer, Hero> onlineAccountMap = new ConcurrentHashMap<Integer, Hero>();// accountid,character
	private volatile Map<String, Hero> onlineCharacteNameMap = new ConcurrentHashMap<String, Hero>();// charactername,character
	private volatile int playercount = 0;

	public GameServerOnlineManager() {
	}

	public int getplayercount() {
		return playercount;
	}

	public void addCharacter(Hero character) {
		onlineCharacterMap.put(character.getId(), character);
		onlineAccountMap.put(character.getAccountId(), character);
		onlineCharacteNameMap.put(character.getViewName(), character);
		playercount = onlineCharacterMap.size();
		onlineHeros.add(character);
	}

	/**
	 * 移除在线的角色和角色所属的帐号
	 * 
	 * @param characterID
	 * @return
	 */
	public Hero removeCharacter(int characterID) {
		Hero character = onlineCharacterMap.remove(characterID);
		if (character != null) {
			onlineAccountMap.remove(character.getAccountId());
			onlineCharacteNameMap.remove(character.getViewName());
		}
		playercount = onlineCharacterMap.size();
		onlineHeros.remove(character);
		return character;
	}

	public Hero getByID(int characterID) {
		return onlineCharacterMap.get(characterID);
	}

	public Hero getByName(String name) {
		return onlineCharacteNameMap.get(name);
	}

	public Hero getByAccountID(int accountid) {
		return onlineAccountMap.get(accountid);
	}

	public Collection<Hero> getCharacterList() {
		// return onlineCharacterMap.values();
		return onlineHeros;
	}

	// public LinkedHeroNode getHerosOnline(){
	// return beginHeroNode;
	// }

	public static class LinkedHeroNode {
		public Hero hero = null;
		public LinkedHeroNode pre = null;
		public LinkedHeroNode next = null;

		public LinkedHeroNode() {
		}
	}

}
