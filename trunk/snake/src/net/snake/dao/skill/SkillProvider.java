package net.snake.dao.skill;

import net.snake.dao.skillupgradeexp.SkillupgradeExp;

import java.util.List;

public interface SkillProvider {
	Skill getSkillByID(int id);
	List<Skill> getSkillList();
	List<SkillupgradeExp> getSkillupgradeExp();
	
}
