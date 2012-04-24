package net.snake.commons.script;


/**
 * 怪物的仇恨列表
 * 
 * @author serv_dev
 * 
 */
public interface SEnmityManager {
	/**
	 * 是否包含男性角色
	 * 
	 * @return
	 */
	public boolean isContainMaleRole();

	/**
	 * 是否包含女性角色
	 * 
	 * @return
	 */
	public boolean isContainFemaleRole();

	/**
	 * 是否包含某职业
	 * 
	 * @param t
	 * @return
	 */
	public boolean isContainPopsinger(int t);

	/**
	 * 找到攻击力最强的角色
	 * 
	 * @return
	 */
	public SRole getAtkMaxRole();

	/**
	 * 找到血量最大的角色
	 * 
	 * @return
	 */
	public SRole getHpMaxRole();

	/**
	 * 找到防御最强的角色
	 * 
	 * @return
	 */
	public SRole getDefMaxRole();

	/**
	 * 找到当前我最仇恨的角色
	 * 
	 * @return
	 */
	public SRole getHatesetRole();

	/**
	 * 增加对某一角色的仇恨 enmityValue
	 * 
	 * @param role
	 * @param enmityValue
	 */
	public void addEnmityValueToRole(SRole role, int enmityValue);

	/**
	 * 返回仇恨
	 * 
	 * @param role
	 * @return
	 */
	public SEnmity getSEnmity(SRole role);
}
