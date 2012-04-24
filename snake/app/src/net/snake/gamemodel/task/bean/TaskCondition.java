package net.snake.gamemodel.task.bean;

import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.ibatis.IbatisEntity;

public class TaskCondition implements IbatisEntity {

	/**
	 * 任务编号 t_task_condition.f_condition_id
	 * 
	 */
	private Integer conditionId;
	/**
	 * 1刺杀怪物 2寻找NPC 3收集8捕获坐骑 t_task_condition.f_functionType
	 * 
	 */
	private Byte functiontype;
	/**
	 * 玩家等级区间min t_task_condition.f_grade_min
	 * 
	 */
	private Integer gradeMin;
	/**
	 * 玩家等级区间MAX t_task_condition.f_grade_max
	 * 
	 */
	private Integer gradeMax;
	/**
	 * 所杀怪物名称 t_task_condition.f_monster_name
	 * 
	 */
	private String monsterName;
	/**
	 * 所杀怪物模型ID t_task_condition.f_monster_model_id
	 * 
	 */
	private Integer monsterModelId;
	/**
	 * 要求杀怪数量 t_task_condition.f_monster_num
	 * 
	 */
	private Integer monsterNum;
	/**
	 * 搜集物品名称 t_task_condition.f_good_name
	 * 
	 */
	private String goodName;
	/**
	 * 搜集物品模型ID t_task_condition.f_good_id
	 * 
	 */
	private Integer goodId;
	/**
	 * 要求物品数量 t_task_condition.f_good_num
	 * 
	 */
	private Integer goodNum;
	/**
	 * 寻访NPC名称 t_task_condition.f_npc_name
	 * 
	 */
	private String npcName;
	/**
	 * NPC-ID t_task_condition.f_npc_id
	 * 
	 */
	private Integer npcId;
	/**
	 * 抓捕坐骑名称 t_task_condition.f_horse_name
	 * 
	 */
	private String horseName;
	/**
	 * 坐骑ID t_task_condition.f_horse_id
	 * 
	 */
	private Integer horseId;
	/**
	 * 任务难度级别 t_task_condition.f_difficulty_degree
	 * 
	 */
	private Integer difficultyDegree;
	/**
	 * 所杀怪物坐标（格式：x_y） t_task_condition.f_monster_zuobiao
	 * 
	 */
	private String monsterZuobiao;
	/**
	 * 离所杀怪物坐标的距离 t_task_condition.f_monster_juli
	 * 
	 */
	private Integer monsterJuli;
	/**
	 * 搜集物品坐标（格式：x_y） t_task_condition.f_good_zuobiao
	 * 
	 */
	private String goodZuobiao;
	/**
	 * 离搜集物品的距离 t_task_condition.f_good_juli
	 * 
	 */
	private Integer goodJuli;
	/**
	 * 抓捕坐骑坐标（格式：x_y） t_task_condition.f_horse_zuobiao
	 * 
	 */
	private String horseZuobiao;
	/**
	 * 离抓捕坐骑的距离 t_task_condition.f_horse_juli
	 * 
	 */
	private Integer horseJuli;
	/**
	 * 所杀怪物场景 t_task_condition.f_monster_scene
	 * 
	 */
	private Integer monsterScene;
	/**
	 * 搜集物品所在场景 t_task_condition.f_good_scene
	 * 
	 */
	private Integer goodScene;
	/**
	 * 坐骑所在场景 t_task_condition.f_horse_scene
	 * 
	 */
	private Integer horseScene;
	/**
	 * 0日环任务1周环任务2.升级环任务 t_task_condition.f_type
	 * 
	 */
	private Byte type;
	/**
	 * 所杀怪物名称 t_task_condition.f_monster_name_i18n
	 * 
	 */
	private String monsterNameI18n;
	/**
	 * 搜集物品名称 t_task_condition.f_good_name_i18n
	 * 
	 */
	private String goodNameI18n;
	/**
	 * 寻访NPC名称 t_task_condition.f_npc_name_i18n
	 * 
	 */
	private String npcNameI18n;
	/**
	 * 抓捕坐骑名称 t_task_condition.f_horse_name_i18n
	 * 
	 */
	private String horseNameI18n;

	/**
	 * 任务编号 t_task_condition.f_condition_id
	 * 
	 * @return the value of t_task_condition.f_condition_id
	 */
	public Integer getConditionId() {
		return conditionId;
	}

	/**
	 * 任务编号 t_task_condition.f_condition_id
	 * 
	 * @param conditionId
	 *            the value for t_task_condition.f_condition_id
	 */
	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	/**
	 * 1刺杀怪物 2寻找NPC 3收集8捕获坐骑 t_task_condition.f_functionType
	 * 
	 * @return the value of t_task_condition.f_functionType
	 */
	public Byte getFunctiontype() {
		return functiontype;
	}

	/**
	 * 1刺杀怪物 2寻找NPC 3收集8捕获坐骑 t_task_condition.f_functionType
	 * 
	 * @param functiontype
	 *            the value for t_task_condition.f_functionType
	 */
	public void setFunctiontype(Byte functiontype) {
		this.functiontype = functiontype;
	}

	/**
	 * 玩家等级区间min t_task_condition.f_grade_min
	 * 
	 * @return the value of t_task_condition.f_grade_min
	 */
	public Integer getGradeMin() {
		return gradeMin;
	}

	/**
	 * 玩家等级区间min t_task_condition.f_grade_min
	 * 
	 * @param gradeMin
	 *            the value for t_task_condition.f_grade_min
	 */
	public void setGradeMin(Integer gradeMin) {
		this.gradeMin = gradeMin;
	}

	/**
	 * 玩家等级区间MAX t_task_condition.f_grade_max
	 * 
	 * @return the value of t_task_condition.f_grade_max
	 */
	public Integer getGradeMax() {
		return gradeMax;
	}

	/**
	 * 玩家等级区间MAX t_task_condition.f_grade_max
	 * 
	 * @param gradeMax
	 *            the value for t_task_condition.f_grade_max
	 */
	public void setGradeMax(Integer gradeMax) {
		this.gradeMax = gradeMax;
	}

	/**
	 * 所杀怪物名称 t_task_condition.f_monster_name
	 * 
	 * @return the value of t_task_condition.f_monster_name
	 */
	public String getMonsterName() {
		return monsterName;
	}

	/**
	 * 所杀怪物名称 t_task_condition.f_monster_name
	 * 
	 * @param monsterName
	 *            the value for t_task_condition.f_monster_name
	 */
	public void setMonsterName(String monsterName) {
		this.monsterName = monsterName;
	}

	/**
	 * 所杀怪物模型ID t_task_condition.f_monster_model_id
	 * 
	 * @return the value of t_task_condition.f_monster_model_id
	 */
	public Integer getMonsterModelId() {
		return monsterModelId;
	}

	/**
	 * 所杀怪物模型ID t_task_condition.f_monster_model_id
	 * 
	 * @param monsterModelId
	 *            the value for t_task_condition.f_monster_model_id
	 */
	public void setMonsterModelId(Integer monsterModelId) {
		this.monsterModelId = monsterModelId;
	}

	/**
	 * 要求杀怪数量 t_task_condition.f_monster_num
	 * 
	 * @return the value of t_task_condition.f_monster_num
	 */
	public Integer getMonsterNum() {
		return monsterNum;
	}

	/**
	 * 要求杀怪数量 t_task_condition.f_monster_num
	 * 
	 * @param monsterNum
	 *            the value for t_task_condition.f_monster_num
	 */
	public void setMonsterNum(Integer monsterNum) {
		this.monsterNum = monsterNum;
	}

	/**
	 * 搜集物品名称 t_task_condition.f_good_name
	 * 
	 * @return the value of t_task_condition.f_good_name
	 */
	public String getGoodName() {
		return goodName;
	}

	/**
	 * 搜集物品名称 t_task_condition.f_good_name
	 * 
	 * @param goodName
	 *            the value for t_task_condition.f_good_name
	 */
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	/**
	 * 搜集物品模型ID t_task_condition.f_good_id
	 * 
	 * @return the value of t_task_condition.f_good_id
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 搜集物品模型ID t_task_condition.f_good_id
	 * 
	 * @param goodId
	 *            the value for t_task_condition.f_good_id
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 要求物品数量 t_task_condition.f_good_num
	 * 
	 * @return the value of t_task_condition.f_good_num
	 */
	public Integer getGoodNum() {
		return goodNum;
	}

	/**
	 * 要求物品数量 t_task_condition.f_good_num
	 * 
	 * @param goodNum
	 *            the value for t_task_condition.f_good_num
	 */
	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	/**
	 * 寻访NPC名称 t_task_condition.f_npc_name
	 * 
	 * @return the value of t_task_condition.f_npc_name
	 */
	public String getNpcName() {
		return npcName;
	}

	/**
	 * 寻访NPC名称 t_task_condition.f_npc_name
	 * 
	 * @param npcName
	 *            the value for t_task_condition.f_npc_name
	 */
	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}

	/**
	 * NPC-ID t_task_condition.f_npc_id
	 * 
	 * @return the value of t_task_condition.f_npc_id
	 */
	public Integer getNpcId() {
		return npcId;
	}

	/**
	 * NPC-ID t_task_condition.f_npc_id
	 * 
	 * @param npcId
	 *            the value for t_task_condition.f_npc_id
	 */
	public void setNpcId(Integer npcId) {
		this.npcId = npcId;
	}

	/**
	 * 抓捕坐骑名称 t_task_condition.f_horse_name
	 * 
	 * @return the value of t_task_condition.f_horse_name
	 */
	public String getHorseName() {
		return horseName;
	}

	/**
	 * 抓捕坐骑名称 t_task_condition.f_horse_name
	 * 
	 * @param horseName
	 *            the value for t_task_condition.f_horse_name
	 */
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	/**
	 * 坐骑ID t_task_condition.f_horse_id
	 * 
	 * @return the value of t_task_condition.f_horse_id
	 */
	public Integer getHorseId() {
		return horseId;
	}

	/**
	 * 坐骑ID t_task_condition.f_horse_id
	 * 
	 * @param horseId
	 *            the value for t_task_condition.f_horse_id
	 */
	public void setHorseId(Integer horseId) {
		this.horseId = horseId;
	}

	/**
	 * 任务难度级别 数越大难度越高t_task_condition.f_difficulty_degree
	 * 
	 * @return the value of t_task_condition.f_difficulty_degree
	 */
	public Integer getDifficultyDegree() {
		return difficultyDegree;
	}

	/**
	 * 任务难度级别 t_task_condition.f_difficulty_degree
	 * 
	 * @param difficultyDegree
	 *            the value for t_task_condition.f_difficulty_degree
	 */
	public void setDifficultyDegree(Integer difficultyDegree) {
		this.difficultyDegree = difficultyDegree;
	}

	/**
	 * 所杀怪物坐标（格式：x_y） t_task_condition.f_monster_zuobiao
	 * 
	 * @return the value of t_task_condition.f_monster_zuobiao
	 */
	public String getMonsterZuobiao() {
		return monsterZuobiao;
	}

	/**
	 * 所杀怪物坐标（格式：x_y） t_task_condition.f_monster_zuobiao
	 * 
	 * @param monsterZuobiao
	 *            the value for t_task_condition.f_monster_zuobiao
	 */
	public void setMonsterZuobiao(String monsterZuobiao) {
		this.monsterZuobiao = monsterZuobiao;
	}

	/**
	 * 离所杀怪物坐标的距离 t_task_condition.f_monster_juli
	 * 
	 * @return the value of t_task_condition.f_monster_juli
	 */
	public Integer getMonsterJuli() {
		return monsterJuli;
	}

	/**
	 * 离所杀怪物坐标的距离 t_task_condition.f_monster_juli
	 * 
	 * @param monsterJuli
	 *            the value for t_task_condition.f_monster_juli
	 */
	public void setMonsterJuli(Integer monsterJuli) {
		this.monsterJuli = monsterJuli;
	}

	/**
	 * 搜集物品坐标（格式：x_y） t_task_condition.f_good_zuobiao
	 * 
	 * @return the value of t_task_condition.f_good_zuobiao
	 */
	public String getGoodZuobiao() {
		return goodZuobiao;
	}

	/**
	 * 搜集物品坐标（格式：x_y） t_task_condition.f_good_zuobiao
	 * 
	 * @param goodZuobiao
	 *            the value for t_task_condition.f_good_zuobiao
	 */
	public void setGoodZuobiao(String goodZuobiao) {
		this.goodZuobiao = goodZuobiao;
	}

	/**
	 * 离搜集物品的距离 t_task_condition.f_good_juli
	 * 
	 * @return the value of t_task_condition.f_good_juli
	 */
	public Integer getGoodJuli() {
		return goodJuli;
	}

	/**
	 * 离搜集物品的距离 t_task_condition.f_good_juli
	 * 
	 * @param goodJuli
	 *            the value for t_task_condition.f_good_juli
	 */
	public void setGoodJuli(Integer goodJuli) {
		this.goodJuli = goodJuli;
	}

	/**
	 * 抓捕坐骑坐标（格式：x_y） t_task_condition.f_horse_zuobiao
	 * 
	 * @return the value of t_task_condition.f_horse_zuobiao
	 */
	public String getHorseZuobiao() {
		return horseZuobiao;
	}

	/**
	 * 抓捕坐骑坐标（格式：x_y） t_task_condition.f_horse_zuobiao
	 * 
	 * @param horseZuobiao
	 *            the value for t_task_condition.f_horse_zuobiao
	 */
	public void setHorseZuobiao(String horseZuobiao) {
		this.horseZuobiao = horseZuobiao;
	}

	/**
	 * 离抓捕坐骑的距离 t_task_condition.f_horse_juli
	 * 
	 * @return the value of t_task_condition.f_horse_juli
	 */
	public Integer getHorseJuli() {
		return horseJuli;
	}

	/**
	 * 离抓捕坐骑的距离 t_task_condition.f_horse_juli
	 * 
	 * @param horseJuli
	 *            the value for t_task_condition.f_horse_juli
	 */
	public void setHorseJuli(Integer horseJuli) {
		this.horseJuli = horseJuli;
	}

	/**
	 * 所杀怪物场景 t_task_condition.f_monster_scene
	 * 
	 * @return the value of t_task_condition.f_monster_scene
	 */
	public Integer getMonsterScene() {
		return monsterScene;
	}

	/**
	 * 所杀怪物场景 t_task_condition.f_monster_scene
	 * 
	 * @param monsterScene
	 *            the value for t_task_condition.f_monster_scene
	 */
	public void setMonsterScene(Integer monsterScene) {
		this.monsterScene = monsterScene;
	}

	/**
	 * 搜集物品所在场景 t_task_condition.f_good_scene
	 * 
	 * @return the value of t_task_condition.f_good_scene
	 */
	public Integer getGoodScene() {
		return goodScene;
	}

	/**
	 * 搜集物品所在场景 t_task_condition.f_good_scene
	 * 
	 * @param goodScene
	 *            the value for t_task_condition.f_good_scene
	 */
	public void setGoodScene(Integer goodScene) {
		this.goodScene = goodScene;
	}

	/**
	 * 坐骑所在场景 t_task_condition.f_horse_scene
	 * 
	 * @return the value of t_task_condition.f_horse_scene
	 */
	public Integer getHorseScene() {
		return horseScene;
	}

	/**
	 * 坐骑所在场景 t_task_condition.f_horse_scene
	 * 
	 * @param horseScene
	 *            the value for t_task_condition.f_horse_scene
	 */
	public void setHorseScene(Integer horseScene) {
		this.horseScene = horseScene;
	}

	/**
	 * 0日环任务1周环任务2.升级环任务 t_task_condition.f_type
	 * 
	 * @return the value of t_task_condition.f_type
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 0日环任务1周环任务2.升级环任务 t_task_condition.f_type
	 * 
	 * @param type
	 *            the value for t_task_condition.f_type
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 所杀怪物名称 t_task_condition.f_monster_name_i18n
	 * 
	 * @return the value of t_task_condition.f_monster_name_i18n
	 */
	public String getMonsterNameI18n() {
		return monsterNameI18n;
	}

	/**
	 * 所杀怪物名称 t_task_condition.f_monster_name_i18n
	 * 
	 * @param monsterNameI18n
	 *            the value for t_task_condition.f_monster_name_i18n
	 */
	public void setMonsterNameI18n(String monsterNameI18n) {
		this.monsterNameI18n = monsterNameI18n;
	}

	/**
	 * 搜集物品名称 t_task_condition.f_good_name_i18n
	 * 
	 * @return the value of t_task_condition.f_good_name_i18n
	 */
	public String getGoodNameI18n() {
		return goodNameI18n;
	}

	/**
	 * 搜集物品名称 t_task_condition.f_good_name_i18n
	 * 
	 * @param goodNameI18n
	 *            the value for t_task_condition.f_good_name_i18n
	 */
	public void setGoodNameI18n(String goodNameI18n) {
		this.goodNameI18n = goodNameI18n;
	}

	/**
	 * 寻访NPC名称 t_task_condition.f_npc_name_i18n
	 * 
	 * @return the value of t_task_condition.f_npc_name_i18n
	 */
	public String getNpcNameI18n() {
		return npcNameI18n;
	}

	/**
	 * 寻访NPC名称 t_task_condition.f_npc_name_i18n
	 * 
	 * @param npcNameI18n
	 *            the value for t_task_condition.f_npc_name_i18n
	 */
	public void setNpcNameI18n(String npcNameI18n) {
		this.npcNameI18n = npcNameI18n;
	}

	/**
	 * 抓捕坐骑名称 t_task_condition.f_horse_name_i18n
	 * 
	 * @return the value of t_task_condition.f_horse_name_i18n
	 */
	public String getHorseNameI18n() {
		return horseNameI18n;
	}

	/**
	 * 抓捕坐骑名称 t_task_condition.f_horse_name_i18n
	 * 
	 * @param horseNameI18n
	 *            the value for t_task_condition.f_horse_name_i18n
	 */
	public void setHorseNameI18n(String horseNameI18n) {
		this.horseNameI18n = horseNameI18n;
	}

	/**
	 * 怪物描述字符串
	 * 
	 * @return
	 */
	public String getMonsterStr() {
		return (getMonsterModelId() + "#" + getMonsterNum() + "#"
				// + getMonsterNameI18n()
				+ MonsterModelManager.getInstance().getFromCache(getMonsterModelId()).getNameI18n().split("_")[0] + "#" + getMonsterScene() + "_"
				+ ("".equals(getMonsterZuobiao()) ? "0_0" : getMonsterZuobiao()) + "_" + getMonsterJuli())+"#"+MonsterModelManager.getInstance().getFromCache(getMonsterModelId()).getSubtype();
	}

	/**
	 * 坐骑描述字符串
	 * 
	 * @return
	 */
	public String getHorseStr() {
		return (getHorseId() + "#" + 1 + "#"
				// + getHorseNameI18n()
				+ HorseModelDataProvider.getInstance().getHorseModelByID(getHorseId()).getNameI18n() + "#" + getHorseScene() + "_"
				+ ("".equals(getHorseZuobiao()) ? "0_0" : getHorseZuobiao()) + "_" + getHorseJuli());
	}

	/**
	 * 物品描述字符串
	 * 
	 * @return
	 */
	public String getGoodStr() {
		return (getGoodId() + "#" + getGoodNum() + "#"
		// + getGoodNameI18n()
				+ GoodmodelManager.getInstance().get(getGoodId()).getNameI18n() + "#" + getGoodScene() + "_" + ("".equals(getGoodZuobiao()) ? "0_0" : getGoodZuobiao()) + "_" + getGoodJuli());
	}

	/**
	 * npc描述字符串
	 * 
	 * @return
	 */
	public String getNpcStr() {
		return "" + getNpcId();
	}

	public static final int ConditionFloorLevel = 1;
}
