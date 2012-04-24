package net.snake.commons.script;

import java.util.List;


/**
 * 表示角色对像
 *
 */
public interface SRole extends SVO, PropertiesSupport {
	/**
	 * 获得角色ID
	 * @return
	 */
	public Integer getId() ;
	/**
	 * 获得x坐标
	 * @return
	 */
	public short getX();
	/**
	 * 获得Y坐标
	 * @return
	 */
	public short getY();
	/**
	 * 获得当前所在的场景
	 * @return
	 */
	public SScene getSceneRef();
	/**
	 * 获得当前职业
	 * @return
	 */
	public int getPopsinger();
	/**
	 * 获得铜钱
	 * @return
	 */
	public int getCopper();

	/**
	 * 是否是男性玩家
	 * @return
	 */
	public boolean isMale();
	
	public int getCount(int type);
//	/**
//	 * 获得元宝
//	 * @return
//	 */
//	public int getIngot();
	/**
	 * 获取当前副本层数
	 * @return
	 */
	public int getInstanceflushGrade();
	/**
	 * 获取与其组队玩家中等级最高的 为组队时 获取自己等级
	 * @return
	 */
	public int getMaxTeamerGrade();
	
	public int getContinuumKillHate();

	/**
	 * 多倍药丹经验系数
	 * @return
	 */
	public int getDoubExpNum();
	
	public boolean isContinuumKill();
	
	public float getContinuumExp();
	
	public int getTeamNum();
	
	public int getDaguaiExp();
	
	public SCharacterGoodController getSCharacterGoodController();
	public int getBadGoodCountByGoodId(int goodId);
	public int getStorageGoodCountByGoodId(int goodId);
	public int getStallGoodCountByGoodId(int goodId);
	public SHorse getMaxBagPinjieHorse();
	public SHorse getMaxStoragePinjieHorse();
	/**
	 * 玩家当前所骑乘的坐骑
	 * @return
	 */
	public SHorse getCurrRidingHorse();
	public int getBanghuiJiacheng();
	public int xiangyangFactionJiacheng();
	/***
	 * 获得 穿上装备和骑上宠物后的 攻击值
	 * @return
	 */
	public int getExpAttack();
	  /**
	   * 获得 穿上装备和骑上宠物后的 防御值
	   * @return
	   */
	public int getExtDefence();
	 /**
	   * 获得 穿上装备和骑上宠物后的 暴击值
	   * @return
	   */
	public int getExtCrt();
	 /**
	   * 获得 穿上装备和骑上宠物后的 闪避值
	   * @return
	   */
	public int getExtDodge();
	
	/***
	 * 获得 穿上装备和骑上宠物后的 攻击速度
	 * @return
	 */
	public int getExtAttackSpeed();
	/***
	 * 获得 穿上装备和骑上宠物后的移动速度
	 * @return
	 */
	public int getExtMoveSpeed();
	/***
	 * 得到队伍中的角色
	 * @return
	 */
	public List<SRole> getTeamCharacters();
	
	
	/**
	 * 是否有风雨同济的buff
	 * @return
	 */
	public boolean hasFengYuTongJiBuff();
	
	/**
	 * 是否是所有
	 * @return
	 */
	public boolean isAllBornEquip();
	
	public int getCharacterHiddenWeaponGrade();
	
	/**
	 * 获得反沉迷衰减系数
	 */
	public float getAntiAddictedSystemPlusScale();
	/**
	 * 获取丹田等级
	 */
	public int getDantianGrade();
}
