package net.snake.commons.script;

public interface SVO {

	public SPropertyAdditionController getPropertyAdditionController();
	
	public int getUnKnockbackGrade();

	public int getUnhitvitalpointGrade();
	public short getGrade() ;

	public int getUnpoisoningGrade();

	public int getUnFengMpGrade();

	public int getUnFengSpGrade();

	public int getUnXiXueGrade() ;

	public int getUnHujuGrade();
	
	public int getUnWuqiGrade();
	
	public int getAtkColdtime() ;
	public int getAttack();
	public int getDefence() ;
	public int getMoveSpeed() ;
	
	public int getHit();
	
	public int getDodge() ;
	
	public int getCrt();
	/**
	 * 获得当前血量
	 * @return
	 */
	public int getNowHp();
	public int getCurrentStat();
}
