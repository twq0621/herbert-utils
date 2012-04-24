package net.snake.commons.script;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 该类声明服务器可以提供一个在脚本内部访问全局功能API
 * 
 * @author serv_dev
 */
public interface SApi {
	
	public SScene getScene(SRole role,int sceneId);
	/**
	 * 将角色传送到场景
	 * 
	 * @param role
	 * @param sceneid
	 * @param x
	 * @param y
	 */
	public void transTo(SRole role, int sceneid, int x, int y);

	/**
	 * 在场景特定位置周围的一个随机点创建一个怪物,并指定是否为主动攻击怪,指定怪物的攻击范围
	 * 
	 * @param scene
	 * @param x
	 * @param y
	 * @param radius
	 *            坐标周围多大距离
	 * @param monstermodelid
	 * @param relive
	 *            创建的怪物死亡后是否重生
	 * @param initiativeAttack
	 *            是否主动攻击型怪物
	 * @param pursuitScope
	 *            怪物的追击范围
	 * @param lifetime
	 *            存活时间 -1表示永远存活，除非被打死, 其他值表示要存活的毫秒数
	 * @return
	 */
	public SMonster createMonsterAroundPoint(SScene scene, int x, int y, int radius, int monstermodelid, boolean relive, boolean initiativeAttack, int pursuitScope, int lifetime);

	/**
	 * 怪物说话
	 * 
	 * @param monster
	 * @param content
	 */
	public void monsterSay(SMonster monster, String content);

	/**
	 * monster攻击role,攻击duration时间，在这个时间内，忽略仇恨值
	 * 
	 * @param monster
	 * @param role
	 * @param duration
	 */
	public void monsterAttackRoleIgnoreEnmity(SMonster monster, SRole role, int duration);

	/**
	 * 在怪物的仇恨列表中随机找到一个角色进行攻击,攻击duration时间，在这个时间内，忽略仇恨值
	 * 
	 * @param monster
	 * @param duration
	 */
	public void monsterAttackRandomRoleIgnoreEnmity(SMonster monster, int duration);

	/**
	 * 临时性[怪物死亡将重置] 的设置技能的触发概率,并且排到技能选择的首位
	 * 
	 * @param monster
	 * @param skillid
	 * @param skillgrade
	 * @param gaiLv
	 *            技能触发的概率,按万分比的比值
	 * @param usedCnt
	 *            使用后多少次移除
	 */
	public void setMonsterSkill(SMonster monster, int skillid, int skillgrade, int gaiLv, int usedCnt);

	/**
	 * 恢复怪物HP
	 * 
	 * @param monster
	 * @param hp
	 */
	public void restoreMonsterHp(SMonster monster, int hp);

	/**
	 * 按百分比恢复怪物HP
	 * 
	 * @param monster
	 * @param hp
	 */
	public void restoreMonsterHpPercent(SMonster monster, int hp);

	/**
	 * 增强怪物攻击力的值,持续 duration毫秒,时间到了,自动去除此值
	 * 
	 * @param monster
	 * @param addAttackValue
	 * @param duration
	 */
	public void addMonsterAttack(SMonster monster, int addAttackValue, int duration);

	/**
	 * 增强怪物防御力值,持续 duration毫秒,时间到了,自动去除此值
	 * 
	 * @param monster
	 * @param addAttackValue
	 * @param duration
	 */
	public void addMonsterDefence(SMonster monster, int addDefenceValue, int duration);

	/**
	 * 增强怪物闪避值,持续duration毫秒,时间到了,自动去除此值
	 * 
	 * @param monster
	 * @param addDefenceValue
	 * @param duration
	 */
	public void addMonsterDogde(SMonster monster, int addDogdeValue, int duration);

	/**
	 * 像角色发送消息
	 * 
	 * @param role
	 * @param msg
	 */
	public void sendMsg(SRole role, byte type, String msg);

	/**
	 * 打开地图隐藏传送点
	 * 
	 * @return
	 */
	public boolean openHideTeleport(SScene scene);
	
	public boolean closeTeleport(SScene scene);

	/**
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param grade
	 * @param attack
	 * @param defence
	 * @param crt
	 * @param dodge
	 * @param atkColdtime
	 * @param moveSpeed
	 * @param exp
	 * @param isrelive
	 *            false 不能复活
	 * @param scene
	 * @return
	 */
	public SMonster createMonsterToScene(final int modelid, final int x, final int y, short grade, int attack, int defence, int crt, int dodge, int atkColdtime, int moveSpeed,
			int exp, final boolean isrelive, final SScene scene);

	/**
	 * 创建 指定名字 指定等级的怪物 并指定延迟多少秒后添加到场景中 怪物是不可复活的
	 * 
	 * @param modelid
	 * @param name
	 * @param scene
	 * @param delaySecond
	 * @return
	 */
	public SMonster createMonsterToScene(int modelid, boolean isRelive, int x, int y, String name, SScene scene, int delaySecond, short grate);

	/**
	 * 根据密集点随机一个随机点
	 * 
	 * @param scene
	 * @param x
	 * @param y
	 * @param radius
	 * @param monstermodelid
	 * @param relive
	 * @param lifetime
	 * @return
	 */
	public SMonster createMonsterAroundPoint(SScene scene, int x, int y, int radius, int monstermodelid, boolean relive, int lifetime);

	/**
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param defenceJiacheng
	 *            防御加成
	 * @param expJiacheng
	 *            经验加成
	 * @param isrelive
	 * @param scene
	 * @return
	 */
	public SMonster createMonsterToScene(final int modelid, final int x, final int y, int radius, float defenceJiacheng, float expJiacheng, final boolean isrelive,
			final SScene scene);

	/**
	 * 副本boss刷新变化 count 为变化值，为正数则是增加值 ，负数 则为减少至
	 * 
	 * @param count
	 */
	public void changeInstanceBossflushCount(SInstance instance, int count);

	/**
	 * 副本monster刷新变化 count 为变化值，为正数则是增加值 ，负数 则为减少至
	 * 
	 * @param count
	 */
	public void changeInstanceMonsterflushCount(SInstance instance, int count);

	/**
	 * 怪物添加技能
	 * 
	 * @param monster
	 * @param skillId
	 * @param probability
	 *            技能触发几率 单位10000
	 */
	public void addMonsterSkill(SMonster monster, int skillId, int probability);

	public SMonster createTimerMonster(final int modelid, final int x, final int y, int radius, float defenceJiacheng, float expJiacheng, final boolean isrelive,
			final SScene scene, int yachiTime);

	/**
	 * 添加怪物副本刷新级别
	 * 
	 * @param monster
	 * @param count
	 */
	public void changeInstanceSceneMonsterflushCount(SMonster monster, int count);

	/**
	 * 出事后副本怪物
	 * 
	 * @param scene
	 */
	public void initInstanceSceneMonster(SScene scene);

	/**
	 * 更改角色当前副本层数层数
	 * 
	 * @param instanceflushGrade
	 */
	public void setInstanceflushGrade(SRole role, int instanceflushGrade);

	/**
	 * 设置进入副本某地图是否满足可以进入的条件 isAbleEnter 是否可以进入
	 */
	public void setInstanceEnterMsg(SRole role, String msg, boolean isAbleEnter);

	/**
	 * 获取角色包裹某物品数量
	 * 
	 * @param role
	 * @param goodId
	 * @return
	 */
	public int getSRoleBadGoodCountByGoodId(SRole role, int goodId);

	/**
	 * 获取角色删除包裹某物品
	 * 
	 * @param role
	 * @param goodId
	 * @param count
	 *            删除数量
	 * @return
	 */
	public boolean deleteSRoleBadGoodCountByGoodId(SRole role, int goodId, int count);

	/**
	 * 初始化对boss怪物有加成属性的场景怪物
	 * 
	 * @param scene
	 * @param dropJiacheng
	 *            单位是10000
	 * @param shuxingJiacheng
	 *            单位是10000
	 */
	public void initInstanceSceneMonster(SScene scene, int dropJiacheng, int shuxingJiacheng);

	/**
	 * 添加某物品 删除某物品
	 * 
	 * @param role
	 * @param removepostions
	 * @param addlist
	 * @return
	 */
	public boolean removeAndAddGoods(SRole role, Collection<SGoods> removepostions, Collection<SGoods> addlist);

	public SGoods createCharacterGood(int goodId, int count, byte bind);

	public SGoods createCharacterGood(int goodId, int count, int loop, int bind, Date date);

	public boolean addCharacterGoods(SRole role, SGoods good);

	public void sendScripGoodToBadMsg(SRole role, int goodId);

	public List<Integer> getEquiteCollectionByGradeAndMenpai(int grade, byte menpai);

	public List<Integer> getHorseEquiteCollectionByGradeAndMenpai(int grade);

	public SGoods getNewSGoods(SGoods good, int count);

	/**
	 * 副本通关
	 */
	public void finishiInstance(SInstance instance);

	/**
	 * 副本通关
	 */
	public void finishiInstance(SInstance instance, SRole role);

	public void characterCount(SRole role, int type, int amount);

	/**
	 * 副本内地图传送
	 * 
	 * @param role
	 * @param instance
	 * @param sceneid
	 * @param x
	 * @param y
	 */
	public void transToInstanceScene(SRole role, SInstance instance, int sceneid, int x, int y);

	/**
	 * 全服发送提示消息
	 * 
	 * @param position
	 * @param msgkey
	 * @param vars
	 */
	public void sendMsgToAll(byte position, int msgkey, String... vars);

	/**
	 * 获取怪物名字根据怪物id
	 * 
	 * @return
	 */
	public String getMonsterNameById(int monsterId);

	/**
	 * 传送点改名
	 * 
	 * @param sRole
	 * @param scene
	 * @param sTeleport
	 * @param exChangeParamToString
	 */
	public void updateTeleportName(SRole sRole, SScene scene, STeleport sTeleport, String exChangeParamToString);

	/**
	 * 更改NPC提示
	 * 
	 * @param role
	 * @param npcId
	 * @param str
	 */
	public void updateNpcTip(SRole role, int npcId, String str);
	
	public void backWorld(SRole hero);
	
	public void sendCountDownMsg(SRole role,int ms,boolean dujie);
	public void sendCountDownMsg(Collection<SRole> roles,int ms,boolean dujie);
	
	public void sendDujieEndMsg(SRole role,boolean suc);
	
	public void clearSceneMonster(SScene scene);
}
