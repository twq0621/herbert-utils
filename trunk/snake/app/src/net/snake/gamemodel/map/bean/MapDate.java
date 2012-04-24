package net.snake.gamemodel.map.bean;

import net.snake.ibatis.IbatisEntity;

public class MapDate implements IbatisEntity {

	/**
	 * 场景ID t_map.f_map_id
	 */
	private Integer mapId;
	/**
	 * 场景名子,l同文件名,不带扩展 t_map.f_map_name
	 */
	private String mapName;
	/**
	 * 地图显示名子 t_map.f_file_name
	 */
	private String fileName;
	/**
	 * 大于0 则为副本场景ID,0为世界场景 t_map.f_instance_id
	 */
	private Integer instanceId;
	/**
	 * 视野上限 0表示无限制 t_map.f_eyeshot_limit
	 */
	private Integer eyeshotLimit;
	/**
	 * t_map.f_width
	 */
	private Integer width;
	/**
	 * t_map.f_height
	 */
	private Integer height;
	/**
	 * 入口点,用于[新手村][副本的第一个场景][世界场景传送] t_map.f_enter_x
	 */
	private Integer enterX;
	/**
	 * 入口点,用于[新手村][副本的第一个场景][世界场景传送] t_map.f_enter_y
	 */
	private Integer enterY;
	/**
	 * 复活到的地图ID t_map.f_relive_map_id
	 */
	private Integer reliveMapId;
	/**
	 * 复活到的x座标 t_map.f_relive_x
	 */
	private Integer reliveX;
	/**
	 * 复活到的y座标 t_map.f_relive_y
	 */
	private Integer reliveY;
	/**
	 * 1允许PVP 0不允许PVP t_map.f_permit_pvp
	 */
	private Integer permitPvp;
	/**
	 * 1允许跳跃 0不允许 t_map.f_permit_jump
	 */
	private Integer permitJump;
	/**
	 * 是否允许骑乘 1允许,0不允许 t_map.f_permit_ride
	 */
	private Integer permitRide;
	/**
	 * 是否允许展示坐骑 1允许 0不允许 t_map.f_permit_show
	 */
	private Integer permitShow;
	/**
	 * 进入等级限制 t_map.f_enter_level_limit
	 */
	private Integer enterLevelLimit;
	/**
	 * 刷怪描述 t_map.f_monster_desc
	 */
	private String monsterDesc;
	/**
	 * 练级等级描述 t_map.f_exercise_desc
	 */
	private String exerciseDesc;
	/**
	 * 刷新BOSS 描述 t_map.f_boss_desc
	 */
	private String bossDesc;
	/**
	 * boss刷新时间描述 t_map.f_boss_time_desc
	 */
	private String bossTimeDesc;
	/**
	 * boss产出物品描述 t_map.f_boss_drop_goods
	 */
	private String bossDropGoods;
	/**
	 * 根据f_block判读是否为空 1为有数据，0为空 t_map.f_block_estimate
	 */
	private Integer blockEstimate;
	/**
	 * 根据f_movies判读是否为空 1为有数据，0为空 t_map.f_movies_estimate
	 */
	private Integer moviesEstimate;
	/**
	 * t_map.f_bgsound
	 */
	private Integer bgsound;
	/**
	 * 死亡pk保护 0保护 1不保护 默认为0 t_map.f_pk_protect
	 */
	private Byte pkProtect;
	/**
	 * 0保护1不保护（pk等级差保护） t_map.f_pk_grade_protect
	 */
	private Byte pkGradeProtect;
	/**
	 * 0不清除身上的pk保护1清除身上的pk保护 t_map.f_clear_pk_protect
	 */
	private Byte clearPkProtect;
	/**
	 * 攻城地图类别0表示非攻城地图 1表示用于攻城的地图 t_map.f_gongcheng_type
	 */
	private Integer gongchengType;
	/**
	 * 场景名子,l同文件名,不带扩展国际化 t_map.f_map_name_i18n
	 */
	private String mapNameI18n;
	/**
	 * 刷怪描述国际化 t_map.f_monster_desc_i18n
	 */
	private String monsterDescI18n;
	/**
	 * 练级等级描述国际化 t_map.f_exercise_desc_i18n
	 */
	private String exerciseDescI18n;
	/**
	 * 刷新BOSS 描述国际化 t_map.f_boss_desc_i18n
	 */
	private String bossDescI18n;
	/**
	 * boss产出物品描述国际化 t_map.f_boss_drop_goods_i18n
	 */
	private String bossDropGoodsI18n;
	/**
	 * 地图的障碍和透明层信息 t_map.f_block
	 */
	private String block;

	/**
	 * 场景ID t_map.f_map_id
	 * 
	 * @return the value of t_map.f_map_id
	 */
	public Integer getMapId() {
		return mapId;
	}

	/**
	 * 场景ID t_map.f_map_id
	 * 
	 * @param mapId
	 *            the value for t_map.f_map_id
	 */
	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	/**
	 * 场景名子,l同文件名,不带扩展 t_map.f_map_name
	 * 
	 * @return the value of t_map.f_map_name
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * 场景名子,l同文件名,不带扩展 t_map.f_map_name
	 * 
	 * @param mapName
	 *            the value for t_map.f_map_name
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * 地图显示名子 t_map.f_file_name
	 * 
	 * @return the value of t_map.f_file_name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 地图显示名子 t_map.f_file_name
	 * 
	 * @param fileName
	 *            the value for t_map.f_file_name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 大于0 则为副本场景ID,0为世界场景 t_map.f_instance_id
	 * 
	 * @return the value of t_map.f_instance_id
	 */
	public Integer getInstanceId() {
		return instanceId;
	}

	/**
	 * 大于0 则为副本场景ID,0为世界场景 t_map.f_instance_id
	 * 
	 * @param instanceId
	 *            the value for t_map.f_instance_id
	 */
	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 视野上限 0表示无限制 t_map.f_eyeshot_limit
	 * 
	 * @return the value of t_map.f_eyeshot_limit
	 */
	public Integer getEyeshotLimit() {
		return eyeshotLimit;
	}

	/**
	 * 视野上限 0表示无限制 t_map.f_eyeshot_limit
	 * 
	 * @param eyeshotLimit
	 *            the value for t_map.f_eyeshot_limit
	 */
	public void setEyeshotLimit(Integer eyeshotLimit) {
		this.eyeshotLimit = eyeshotLimit;
	}

	/**
	 * t_map.f_width
	 * 
	 * @return the value of t_map.f_width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * t_map.f_width
	 * 
	 * @param width
	 *            the value for t_map.f_width
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * t_map.f_height
	 * 
	 * @return the value of t_map.f_height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * t_map.f_height
	 * 
	 * @param height
	 *            the value for t_map.f_height
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * 入口点,用于[新手村][副本的第一个场景][世界场景传送] t_map.f_enter_x
	 * 
	 * @return the value of t_map.f_enter_x
	 */
	public Integer getEnterX() {
		return enterX;
	}

	/**
	 * 入口点,用于[新手村][副本的第一个场景][世界场景传送] t_map.f_enter_x
	 * 
	 * @param enterX
	 *            the value for t_map.f_enter_x
	 */
	public void setEnterX(Integer enterX) {
		this.enterX = enterX;
	}

	/**
	 * 入口点,用于[新手村][副本的第一个场景][世界场景传送] t_map.f_enter_y
	 * 
	 * @return the value of t_map.f_enter_y
	 */
	public Integer getEnterY() {
		return enterY;
	}

	/**
	 * 入口点,用于[新手村][副本的第一个场景][世界场景传送] t_map.f_enter_y
	 * 
	 * @param enterY
	 *            the value for t_map.f_enter_y
	 */
	public void setEnterY(Integer enterY) {
		this.enterY = enterY;
	}

	/**
	 * 复活到的地图ID t_map.f_relive_map_id
	 * 
	 * @return the value of t_map.f_relive_map_id
	 */
	public Integer getReliveMapId() {
		return reliveMapId;
	}

	/**
	 * 复活到的地图ID t_map.f_relive_map_id
	 * 
	 * @param reliveMapId
	 *            the value for t_map.f_relive_map_id
	 */
	public void setReliveMapId(Integer reliveMapId) {
		this.reliveMapId = reliveMapId;
	}

	/**
	 * 复活到的x座标 t_map.f_relive_x
	 * 
	 * @return the value of t_map.f_relive_x
	 */
	public Integer getReliveX() {
		return reliveX;
	}

	/**
	 * 复活到的x座标 t_map.f_relive_x
	 * 
	 * @param reliveX
	 *            the value for t_map.f_relive_x
	 */
	public void setReliveX(Integer reliveX) {
		this.reliveX = reliveX;
	}

	/**
	 * 复活到的y座标 t_map.f_relive_y
	 * 
	 * @return the value of t_map.f_relive_y
	 */
	public Integer getReliveY() {
		return reliveY;
	}

	/**
	 * 复活到的y座标 t_map.f_relive_y
	 * 
	 * @param reliveY
	 *            the value for t_map.f_relive_y
	 */
	public void setReliveY(Integer reliveY) {
		this.reliveY = reliveY;
	}

	/**
	 * 1允许PVP 0不允许PVP t_map.f_permit_pvp
	 * 
	 * @return the value of t_map.f_permit_pvp
	 */
	public Integer getPermitPvp() {
		return permitPvp;
	}

	/**
	 * 1允许PVP 0不允许PVP t_map.f_permit_pvp
	 * 
	 * @param permitPvp
	 *            the value for t_map.f_permit_pvp
	 */
	public void setPermitPvp(Integer permitPvp) {
		this.permitPvp = permitPvp;
	}

	/**
	 * 1允许跳跃 0不允许 t_map.f_permit_jump
	 * 
	 * @return the value of t_map.f_permit_jump
	 */
	public Integer getPermitJump() {
		return permitJump;
	}

	/**
	 * 1允许跳跃 0不允许 t_map.f_permit_jump
	 * 
	 * @param permitJump
	 *            the value for t_map.f_permit_jump
	 */
	public void setPermitJump(Integer permitJump) {
		this.permitJump = permitJump;
	}

	/**
	 * 是否允许骑乘 1允许,0不允许 t_map.f_permit_ride
	 * 
	 * @return the value of t_map.f_permit_ride
	 */
	public Integer getPermitRide() {
		return permitRide;
	}

	/**
	 * 是否允许骑乘 1允许,0不允许 t_map.f_permit_ride
	 * 
	 * @param permitRide
	 *            the value for t_map.f_permit_ride
	 */
	public void setPermitRide(Integer permitRide) {
		this.permitRide = permitRide;
	}

	/**
	 * 是否允许展示坐骑 1允许 0不允许 t_map.f_permit_show
	 * 
	 * @return the value of t_map.f_permit_show
	 */
	public Integer getPermitShow() {
		return permitShow;
	}

	/**
	 * 是否允许展示坐骑 1允许 0不允许 t_map.f_permit_show
	 * 
	 * @param permitShow
	 *            the value for t_map.f_permit_show
	 */
	public void setPermitShow(Integer permitShow) {
		this.permitShow = permitShow;
	}

	/**
	 * 进入等级限制 t_map.f_enter_level_limit
	 * 
	 * @return the value of t_map.f_enter_level_limit
	 */
	public Integer getEnterLevelLimit() {
		return enterLevelLimit;
	}

	/**
	 * 进入等级限制 t_map.f_enter_level_limit
	 * 
	 * @param enterLevelLimit
	 *            the value for t_map.f_enter_level_limit
	 */
	public void setEnterLevelLimit(Integer enterLevelLimit) {
		this.enterLevelLimit = enterLevelLimit;
	}

	/**
	 * 刷怪描述 t_map.f_monster_desc
	 * 
	 * @return the value of t_map.f_monster_desc
	 */
	public String getMonsterDesc() {
		return monsterDesc;
	}

	/**
	 * 刷怪描述 t_map.f_monster_desc
	 * 
	 * @param monsterDesc
	 *            the value for t_map.f_monster_desc
	 */
	public void setMonsterDesc(String monsterDesc) {
		this.monsterDesc = monsterDesc;
	}

	/**
	 * 练级等级描述 t_map.f_exercise_desc
	 * 
	 * @return the value of t_map.f_exercise_desc
	 */
	public String getExerciseDesc() {
		return exerciseDesc;
	}

	/**
	 * 练级等级描述 t_map.f_exercise_desc
	 * 
	 * @param exerciseDesc
	 *            the value for t_map.f_exercise_desc
	 */
	public void setExerciseDesc(String exerciseDesc) {
		this.exerciseDesc = exerciseDesc;
	}

	/**
	 * 刷新BOSS 描述 t_map.f_boss_desc
	 * 
	 * @return the value of t_map.f_boss_desc
	 */
	public String getBossDesc() {
		return bossDesc;
	}

	/**
	 * 刷新BOSS 描述 t_map.f_boss_desc
	 * 
	 * @param bossDesc
	 *            the value for t_map.f_boss_desc
	 */
	public void setBossDesc(String bossDesc) {
		this.bossDesc = bossDesc;
	}

	/**
	 * boss刷新时间描述 t_map.f_boss_time_desc
	 * 
	 * @return the value of t_map.f_boss_time_desc
	 */
	public String getBossTimeDesc() {
		return bossTimeDesc;
	}

	/**
	 * boss刷新时间描述 t_map.f_boss_time_desc
	 * 
	 * @param bossTimeDesc
	 *            the value for t_map.f_boss_time_desc
	 */
	public void setBossTimeDesc(String bossTimeDesc) {
		this.bossTimeDesc = bossTimeDesc;
	}

	/**
	 * boss产出物品描述 t_map.f_boss_drop_goods
	 * 
	 * @return the value of t_map.f_boss_drop_goods
	 */
	public String getBossDropGoods() {
		return bossDropGoods;
	}

	/**
	 * boss产出物品描述 t_map.f_boss_drop_goods
	 * 
	 * @param bossDropGoods
	 *            the value for t_map.f_boss_drop_goods
	 */
	public void setBossDropGoods(String bossDropGoods) {
		this.bossDropGoods = bossDropGoods;
	}

	/**
	 * 根据f_block判读是否为空 1为有数据，0为空 t_map.f_block_estimate
	 * 
	 * @return the value of t_map.f_block_estimate
	 */
	public Integer getBlockEstimate() {
		return blockEstimate;
	}

	/**
	 * 根据f_block判读是否为空 1为有数据，0为空 t_map.f_block_estimate
	 * 
	 * @param blockEstimate
	 *            the value for t_map.f_block_estimate
	 */
	public void setBlockEstimate(Integer blockEstimate) {
		this.blockEstimate = blockEstimate;
	}

	/**
	 * 根据f_movies判读是否为空 1为有数据，0为空 t_map.f_movies_estimate
	 * 
	 * @return the value of t_map.f_movies_estimate
	 */
	public Integer getMoviesEstimate() {
		return moviesEstimate;
	}

	/**
	 * 根据f_movies判读是否为空 1为有数据，0为空 t_map.f_movies_estimate
	 * 
	 * @param moviesEstimate
	 *            the value for t_map.f_movies_estimate
	 */
	public void setMoviesEstimate(Integer moviesEstimate) {
		this.moviesEstimate = moviesEstimate;
	}

	/**
	 * t_map.f_bgsound
	 * 
	 * @return the value of t_map.f_bgsound
	 */
	public Integer getBgsound() {
		return bgsound;
	}

	/**
	 * t_map.f_bgsound
	 * 
	 * @param bgsound
	 *            the value for t_map.f_bgsound
	 */
	public void setBgsound(Integer bgsound) {
		this.bgsound = bgsound;
	}

	/**
	 * 死亡pk保护 0保护 1不保护 默认为0 t_map.f_pk_protect
	 * 
	 * @return the value of t_map.f_pk_protect
	 */
	public Byte getPkProtect() {
		return pkProtect;
	}

	/**
	 * 死亡pk保护 0保护 1不保护 默认为0 t_map.f_pk_protect
	 * 
	 * @param pkProtect
	 *            the value for t_map.f_pk_protect
	 */
	public void setPkProtect(Byte pkProtect) {
		this.pkProtect = pkProtect;
	}

	/**
	 * 0保护1不保护（pk等级差保护） t_map.f_pk_grade_protect
	 * 
	 * @return the value of t_map.f_pk_grade_protect
	 */
	public Byte getPkGradeProtect() {
		return pkGradeProtect;
	}

	/**
	 * 0保护1不保护（pk等级差保护） t_map.f_pk_grade_protect
	 * 
	 * @param pkGradeProtect
	 *            the value for t_map.f_pk_grade_protect
	 */
	public void setPkGradeProtect(Byte pkGradeProtect) {
		this.pkGradeProtect = pkGradeProtect;
	}

	/**
	 * 0不清除身上的pk保护1清除身上的pk保护 t_map.f_clear_pk_protect
	 * 
	 * @return the value of t_map.f_clear_pk_protect
	 */
	public Byte getClearPkProtect() {
		return clearPkProtect;
	}

	/**
	 * 0不清除身上的pk保护1清除身上的pk保护 t_map.f_clear_pk_protect
	 * 
	 * @param clearPkProtect
	 *            the value for t_map.f_clear_pk_protect
	 */
	public void setClearPkProtect(Byte clearPkProtect) {
		this.clearPkProtect = clearPkProtect;
	}

	/**
	 * 攻城地图类别0表示非攻城地图 1表示用于攻城的地图 t_map.f_gongcheng_type
	 * 
	 * @return the value of t_map.f_gongcheng_type
	 */
	public Integer getGongchengType() {
		return gongchengType;
	}

	/**
	 * 攻城地图类别0表示非攻城地图 1表示用于攻城的地图 t_map.f_gongcheng_type
	 * 
	 * @param gongchengType
	 *            the value for t_map.f_gongcheng_type
	 */
	public void setGongchengType(Integer gongchengType) {
		this.gongchengType = gongchengType;
	}

	/**
	 * 场景名子,l同文件名,不带扩展国际化 t_map.f_map_name_i18n
	 * 
	 * @return the value of t_map.f_map_name_i18n
	 */
	public String getMapNameI18n() {
		return mapNameI18n;
	}

	/**
	 * 场景名子,l同文件名,不带扩展国际化 t_map.f_map_name_i18n
	 * 
	 * @param mapNameI18n
	 *            the value for t_map.f_map_name_i18n
	 */
	public void setMapNameI18n(String mapNameI18n) {
		this.mapNameI18n = mapNameI18n;
	}

	/**
	 * 刷怪描述国际化 t_map.f_monster_desc_i18n
	 * 
	 * @return the value of t_map.f_monster_desc_i18n
	 */
	public String getMonsterDescI18n() {
		return monsterDescI18n;
	}

	/**
	 * 刷怪描述国际化 t_map.f_monster_desc_i18n
	 * 
	 * @param monsterDescI18n
	 *            the value for t_map.f_monster_desc_i18n
	 */
	public void setMonsterDescI18n(String monsterDescI18n) {
		this.monsterDescI18n = monsterDescI18n;
	}

	/**
	 * 练级等级描述国际化 t_map.f_exercise_desc_i18n
	 * 
	 * @return the value of t_map.f_exercise_desc_i18n
	 */
	public String getExerciseDescI18n() {
		return exerciseDescI18n;
	}

	/**
	 * 练级等级描述国际化 t_map.f_exercise_desc_i18n
	 * 
	 * @param exerciseDescI18n
	 *            the value for t_map.f_exercise_desc_i18n
	 */
	public void setExerciseDescI18n(String exerciseDescI18n) {
		this.exerciseDescI18n = exerciseDescI18n;
	}

	/**
	 * 刷新BOSS 描述国际化 t_map.f_boss_desc_i18n
	 * 
	 * @return the value of t_map.f_boss_desc_i18n
	 */
	public String getBossDescI18n() {
		return bossDescI18n;
	}

	/**
	 * 刷新BOSS 描述国际化 t_map.f_boss_desc_i18n
	 * 
	 * @param bossDescI18n
	 *            the value for t_map.f_boss_desc_i18n
	 */
	public void setBossDescI18n(String bossDescI18n) {
		this.bossDescI18n = bossDescI18n;
	}

	/**
	 * boss产出物品描述国际化 t_map.f_boss_drop_goods_i18n
	 * 
	 * @return the value of t_map.f_boss_drop_goods_i18n
	 */
	public String getBossDropGoodsI18n() {
		return bossDropGoodsI18n;
	}

	/**
	 * boss产出物品描述国际化 t_map.f_boss_drop_goods_i18n
	 * 
	 * @param bossDropGoodsI18n
	 *            the value for t_map.f_boss_drop_goods_i18n
	 */
	public void setBossDropGoodsI18n(String bossDropGoodsI18n) {
		this.bossDropGoodsI18n = bossDropGoodsI18n;
	}

	/**
	 * 地图的障碍和透明层信息 t_map.f_block
	 * 
	 * @return the value of t_map.f_block
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * 地图的障碍和透明层信息 t_map.f_block
	 * 
	 * @param block
	 *            the value for t_map.f_block
	 */
	public void setBlock(String block) {
		this.block = block;
	}
}
