package net.snake.gamemodel.equipment.bean;
import net.snake.consts.Property;
import net.snake.ibatis.IbatisEntity;

/**
 * 装备强化属性表
 * 
 */

public class EquipmentStrengthen implements IbatisEntity {

	private Integer goodmodelId ;//装备id
	private String goodmodelName ;//装备name
	private Integer grade ;//强化等级
	private Integer jingangshi ;//需要金刚石id  (装备升星强化使用)
	private Integer jingangshiNum ;//需要金刚石数量  (装备升星强化使用)
	private Integer zhenqi ;//需要真气
	private Integer probability ;//成功的概率（1/10000）
	private int minCount; // 概率成功最低次数 0 0
	private int maxCount; // 概率成功上限 0 0
	private Integer attack ;//影响攻击值
	private Integer defend ;//影响防御值
	private Integer crt ;//影响暴击值
	private Integer dodge ;//影响闪避值
	private Integer hp ;//影响生命上限
	private Integer mp ;//影响法力上限
	private Integer sp ;//影响体力值
	private Integer hit ;//影响命中值
	private Integer movespeed ;//影响移动速度
	private Integer attackspeed ;//影响攻击速度
	private Integer copper;//需要多少铜币
	
	public int getStrengthenValue(Property property){
		switch (property) {
		case attack:
			return getAttack();
		case defence:
			return getDefend();
		case crt:
			return getCrt();
		case dodge:
			return getDodge();
		case maxHp:
			return getHp();
		case maxSp:
			return getSp();
		case maxMp:
			return getMp();
		case movespeed:
			return getMovespeed();
		case attackspeed:
			return getAttackspeed();
		case hit:
			return getHit();
			
		default:
			return 0;
		}
	}
	
	public Integer getCopper() {
		return copper;
	}

	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 装备id
	 * @param Integer goodmodelId 
	 */
	public  void setGoodmodelId(Integer goodmodelId){
		this.goodmodelId = goodmodelId;
	}
	
	/**
	 * 装备id
	 * @return Integer
	 */
	public  Integer getGoodmodelId(){
		return this.goodmodelId;
	}
	/**
	 * 装备name
	 * @param String goodmodelName 
	 */
	public  void setGoodmodelName(String goodmodelName){
		this.goodmodelName = goodmodelName;
	}
	
	/**
	 * 装备name
	 * @return String
	 */
	public  String getGoodmodelName(){
		return this.goodmodelName;
	}
	/**
	 * 强化等级
	 * @param Integer grade 
	 */
	public  void setGrade(Integer grade){
		this.grade = grade;
	}
	
	/**
	 * 强化等级
	 * @return Integer
	 */
	public  Integer getGrade(){
		return this.grade;
	}
	/**
	 * 需要金刚石id  (装备升星强化使用)
	 * @param Integer jingangshi 
	 */
	public  void setJingangshi(Integer jingangshi){
		this.jingangshi = jingangshi;
	}
	
	/**
	 * 需要金刚石id  (装备升星强化使用)
	 * @return Integer
	 */
	public  Integer getJingangshi(){
		return this.jingangshi;
	}
	/**
	 * 需要金刚石数量  (装备升星强化使用)
	 * @param Integer jingangshiNum 
	 */
	public  void setJingangshiNum(Integer jingangshiNum){
		this.jingangshiNum = jingangshiNum;
	}
	
	/**
	 * 需要金刚石数量  (装备升星强化使用)
	 * @return Integer
	 */
	public  Integer getJingangshiNum(){
		return this.jingangshiNum;
	}
	/**
	 * 需要真气
	 * @param Integer zhenqi 
	 */
	public  void setZhenqi(Integer zhenqi){
		this.zhenqi = zhenqi;
	}
	
	/**
	 * 需要真气
	 * @return Integer
	 */
	public  Integer getZhenqi(){
		return this.zhenqi;
	}
	/**
	 * 成功的概率（1/10000）
	 * @param Integer probability 
	 */
	public  void setProbability(Integer probability){
		this.probability = probability;
	}
	
	/**
	 * 成功的概率（1/10000）
	 * @return Integer
	 */
	public  Integer getProbability(){
		return this.probability;
	}
	/**
	 * 影响攻击值
	 * @param Integer attack 
	 */
	public  void setAttack(Integer attack){
		this.attack = attack;
	}
	
	/**
	 * 影响攻击值
	 * @return Integer
	 */
	public  Integer getAttack(){
		return this.attack;
	}
	/**
	 * 影响防御值
	 * @param Integer defend 
	 */
	public  void setDefend(Integer defend){
		this.defend = defend;
	}
	
	/**
	 * 影响防御值
	 * @return Integer
	 */
	public  Integer getDefend(){
		return this.defend;
	}
	/**
	 * 影响暴击值
	 * @param Integer crt 
	 */
	public  void setCrt(Integer crt){
		this.crt = crt;
	}
	
	/**
	 * 影响暴击值
	 * @return Integer
	 */
	public  Integer getCrt(){
		return this.crt;
	}
	/**
	 * 影响闪避值
	 * @param Integer dodge 
	 */
	public  void setDodge(Integer dodge){
		this.dodge = dodge;
	}
	
	/**
	 * 影响闪避值
	 * @return Integer
	 */
	public  Integer getDodge(){
		return this.dodge;
	}
	/**
	 * 影响生命上限
	 * @param Integer hp 
	 */
	public  void setHp(Integer hp){
		this.hp = hp;
	}
	
	/**
	 * 影响生命上限
	 * @return Integer
	 */
	public  Integer getHp(){
		return this.hp;
	}
	/**
	 * 影响法力上限
	 * @param Integer mp 
	 */
	public  void setMp(Integer mp){
		this.mp = mp;
	}
	
	/**
	 * 影响法力上限
	 * @return Integer
	 */
	public  Integer getMp(){
		return this.mp;
	}
	/**
	 * 影响体力值
	 * @param Integer sp 
	 */
	public  void setSp(Integer sp){
		this.sp = sp;
	}
	
	/**
	 * 影响体力值
	 * @return Integer
	 */
	public  Integer getSp(){
		return this.sp;
	}
	/**
	 * 影响命中值
	 * @param Integer hit 
	 */
	public  void setHit(Integer hit){
		this.hit = hit;
	}
	
	/**
	 * 影响命中值
	 * @return Integer
	 */
	public  Integer getHit(){
		return this.hit;
	}
	/**
	 * 影响移动速度
	 * @param Integer movespeed 
	 */
	public  void setMovespeed(Integer movespeed){
		this.movespeed = movespeed;
	}
	
	/**
	 * 影响移动速度
	 * @return Integer
	 */
	public  Integer getMovespeed(){
		return this.movespeed;
	}
	/**
	 * 影响攻击速度
	 * @param Integer attackspeed 
	 */
	public  void setAttackspeed(Integer attackspeed){
		this.attackspeed = attackspeed;
	}
	
	/**
	 * 影响攻击速度
	 * @return Integer
	 */
	public  Integer getAttackspeed(){
		return this.attackspeed;
	}

	public int getMinCount() {
		return minCount;
	}

	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	
	
}
