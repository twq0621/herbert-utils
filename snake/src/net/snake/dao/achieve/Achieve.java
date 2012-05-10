package net.snake.dao.achieve;

public class Achieve {

	/**
	 * t_achieve.f_id
	 */
	private Integer id;
	/**
	 * 绉板彿name t_achieve.f_title
	 */
	private String title;
	/**
	 * 绫诲埆锛�澶╅亾閰嫟锛�姹熸箹鍘嗙粌锛�寮鸿�涔嬭矾,3绁炲叺绁為獞,4娴磋娌欏満锛�t_achieve.f_kind
	 */
	private Integer kind;
	/**
	 * 瀛愬垎绫�t_achieve.f_child_kind
	 */
	private Integer childKind;
	/**
	 * 鎴愬氨鎻忚堪 t_achieve.f_desc
	 */
	private String desc;
	/**
	 * 瀹屾垚杩涘害璁℃暟锛堟弧瓒虫潯浠讹級 t_achieve.f_achieve_count
	 */
	private Integer achieveCount;
	/**
	 * 澧炲姞鐐规暟 t_achieve.f_point
	 */
	private Integer point;
	/**
	 * 濉啓瀛愬垎绫诲娉�t_achieve.f_beizhu
	 */
	private String beizhu;
	/**
	 * 鍥炬爣缂栧彿 t_achieve.f_ico
	 */
	private Integer ico;
	/**
	 * 濂栧姳绀煎寘 t_achieve.f_good_id
	 */
	private Integer goodId;
	/**
	 * 鏄惁鍏憡 0涓嶅叕鍛�1鍏憡 t_achieve.f_isnotice
	 */
	private Integer isnotice;
	/**
	 * 鎴愬氨鍚嶇О t_achieve.f_name
	 */
	private String name;
	/**
	 * 0鍑绘潃鎬墿 锛�浠诲姟 锛�瑁呭 锛�鍧愰獞锛�5娲诲姩锛�鍓湰锛�绀句氦锛堝ソ鍙嬶紝甯細, 缁勯槦锛�8鐜╁姝讳骸璁℃暟锛�鍏朵粬锛堣繛鏂╋紝姝﹀姛锛屽０鏈涳紝闈欒剦锛岀瓑绾э紝璺宠穬锛�绋嬪簭妯″潡鍒嗙被 t_achieve.f_model_type
	 */
	private Integer modelType;
	/**
	 * 绉板彿name鍥介檯鍖�t_achieve.f_title_i18n
	 */
	private String titleI18n;
	/**
	 * 鎴愬氨鎻忚堪鍥介檯鍖�t_achieve.f_desc_i18n
	 */
	private String descI18n;
	/**
	 * 濉啓瀛愬垎绫诲娉ㄥ浗闄呭寲 t_achieve.f_beizhu_i18n
	 */
	private String beizhuI18n;
	/**
	 * 鎴愬氨鍚嶇О鍥介檯鍖�t_achieve.f_name_i18n
	 */
	private String nameI18n;

	/**
	 * t_achieve.f_id
	 * @return  the value of t_achieve.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_achieve.f_id
	 * @param id  the value for t_achieve.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 绉板彿name t_achieve.f_title
	 * @return  the value of t_achieve.f_title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 绉板彿name t_achieve.f_title
	 * @param title  the value for t_achieve.f_title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 绫诲埆锛�澶╅亾閰嫟锛�姹熸箹鍘嗙粌锛�寮鸿�涔嬭矾,3绁炲叺绁為獞,4娴磋娌欏満锛�t_achieve.f_kind
	 * @return  the value of t_achieve.f_kind
	 */
	public Integer getKind() {
		return kind;
	}

	/**
	 * 绫诲埆锛�澶╅亾閰嫟锛�姹熸箹鍘嗙粌锛�寮鸿�涔嬭矾,3绁炲叺绁為獞,4娴磋娌欏満锛�t_achieve.f_kind
	 * @param kind  the value for t_achieve.f_kind
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}

	/**
	 * 瀛愬垎绫�t_achieve.f_child_kind
	 * @return  the value of t_achieve.f_child_kind
	 */
	public Integer getChildKind() {
		return childKind;
	}

	/**
	 * 瀛愬垎绫�t_achieve.f_child_kind
	 * @param childKind  the value for t_achieve.f_child_kind
	 */
	public void setChildKind(Integer childKind) {
		this.childKind = childKind;
	}

	/**
	 * 鎴愬氨鎻忚堪 t_achieve.f_desc
	 * @return  the value of t_achieve.f_desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 鎴愬氨鎻忚堪 t_achieve.f_desc
	 * @param desc  the value for t_achieve.f_desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 瀹屾垚杩涘害璁℃暟锛堟弧瓒虫潯浠讹級 t_achieve.f_achieve_count
	 * @return  the value of t_achieve.f_achieve_count
	 */
	public Integer getAchieveCount() {
		return achieveCount;
	}

	/**
	 * 瀹屾垚杩涘害璁℃暟锛堟弧瓒虫潯浠讹級 t_achieve.f_achieve_count
	 * @param achieveCount  the value for t_achieve.f_achieve_count
	 */
	public void setAchieveCount(Integer achieveCount) {
		this.achieveCount = achieveCount;
	}

	/**
	 * 澧炲姞鐐规暟 t_achieve.f_point
	 * @return  the value of t_achieve.f_point
	 */
	public Integer getPoint() {
		return point;
	}

	/**
	 * 澧炲姞鐐规暟 t_achieve.f_point
	 * @param point  the value for t_achieve.f_point
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	/**
	 * 濉啓瀛愬垎绫诲娉�t_achieve.f_beizhu
	 * @return  the value of t_achieve.f_beizhu
	 */
	public String getBeizhu() {
		return beizhu;
	}

	/**
	 * 濉啓瀛愬垎绫诲娉�t_achieve.f_beizhu
	 * @param beizhu  the value for t_achieve.f_beizhu
	 */
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	/**
	 * 鍥炬爣缂栧彿 t_achieve.f_ico
	 * @return  the value of t_achieve.f_ico
	 */
	public Integer getIco() {
		return ico;
	}

	/**
	 * 鍥炬爣缂栧彿 t_achieve.f_ico
	 * @param ico  the value for t_achieve.f_ico
	 */
	public void setIco(Integer ico) {
		this.ico = ico;
	}

	/**
	 * 濂栧姳绀煎寘 t_achieve.f_good_id
	 * @return  the value of t_achieve.f_good_id
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 濂栧姳绀煎寘 t_achieve.f_good_id
	 * @param goodId  the value for t_achieve.f_good_id
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 鏄惁鍏憡 0涓嶅叕鍛�1鍏憡 t_achieve.f_isnotice
	 * @return  the value of t_achieve.f_isnotice
	 */
	public Integer getIsnotice() {
		return isnotice;
	}

	/**
	 * 鏄惁鍏憡 0涓嶅叕鍛�1鍏憡 t_achieve.f_isnotice
	 * @param isnotice  the value for t_achieve.f_isnotice
	 */
	public void setIsnotice(Integer isnotice) {
		this.isnotice = isnotice;
	}

	/**
	 * 鎴愬氨鍚嶇О t_achieve.f_name
	 * @return  the value of t_achieve.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 鎴愬氨鍚嶇О t_achieve.f_name
	 * @param name  the value for t_achieve.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 0鍑绘潃鎬墿 锛�浠诲姟 锛�瑁呭 锛�鍧愰獞锛�5娲诲姩锛�鍓湰锛�绀句氦锛堝ソ鍙嬶紝甯細, 缁勯槦锛�8鐜╁姝讳骸璁℃暟锛�鍏朵粬锛堣繛鏂╋紝姝﹀姛锛屽０鏈涳紝闈欒剦锛岀瓑绾э紝璺宠穬锛�绋嬪簭妯″潡鍒嗙被 t_achieve.f_model_type
	 * @return  the value of t_achieve.f_model_type
	 */
	public Integer getModelType() {
		return modelType;
	}

	/**
	 * 0鍑绘潃鎬墿 锛�浠诲姟 锛�瑁呭 锛�鍧愰獞锛�5娲诲姩锛�鍓湰锛�绀句氦锛堝ソ鍙嬶紝甯細, 缁勯槦锛�8鐜╁姝讳骸璁℃暟锛�鍏朵粬锛堣繛鏂╋紝姝﹀姛锛屽０鏈涳紝闈欒剦锛岀瓑绾э紝璺宠穬锛�绋嬪簭妯″潡鍒嗙被 t_achieve.f_model_type
	 * @param modelType  the value for t_achieve.f_model_type
	 */
	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	/**
	 * 绉板彿name鍥介檯鍖�t_achieve.f_title_i18n
	 * @return  the value of t_achieve.f_title_i18n
	 */
	public String getTitleI18n() {
		return titleI18n;
	}

	/**
	 * 绉板彿name鍥介檯鍖�t_achieve.f_title_i18n
	 * @param titleI18n  the value for t_achieve.f_title_i18n
	 */
	public void setTitleI18n(String titleI18n) {
		this.titleI18n = titleI18n;
	}

	/**
	 * 鎴愬氨鎻忚堪鍥介檯鍖�t_achieve.f_desc_i18n
	 * @return  the value of t_achieve.f_desc_i18n
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 鎴愬氨鎻忚堪鍥介檯鍖�t_achieve.f_desc_i18n
	 * @param descI18n  the value for t_achieve.f_desc_i18n
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 濉啓瀛愬垎绫诲娉ㄥ浗闄呭寲 t_achieve.f_beizhu_i18n
	 * @return  the value of t_achieve.f_beizhu_i18n
	 */
	public String getBeizhuI18n() {
		return beizhuI18n;
	}

	/**
	 * 濉啓瀛愬垎绫诲娉ㄥ浗闄呭寲 t_achieve.f_beizhu_i18n
	 * @param beizhuI18n  the value for t_achieve.f_beizhu_i18n
	 */
	public void setBeizhuI18n(String beizhuI18n) {
		this.beizhuI18n = beizhuI18n;
	}

	/**
	 * 鎴愬氨鍚嶇О鍥介檯鍖�t_achieve.f_name_i18n
	 * @return  the value of t_achieve.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 鎴愬氨鍚嶇О鍥介檯鍖�t_achieve.f_name_i18n
	 * @param nameI18n  the value for t_achieve.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}
}
