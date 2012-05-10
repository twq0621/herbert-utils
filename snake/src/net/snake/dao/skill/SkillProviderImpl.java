package net.snake.dao.skill;



import net.snake.common.BeanTool;
import net.snake.dao.skillupgradeexp.SkillupgradeExp;
import net.snake.dao.skillupgradeexp.SkillupgradeExpDAO;
import net.snake.dao.skillupgradeexp.SkillupgradeExpExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

public class SkillProviderImpl implements SkillProvider ,InitializingBean{

	private List<Skill> listSkills;
	private SkillDAO skillDAO;
	private Map<Integer, Skill> mapSkill = new HashMap<Integer, Skill>();

	private SkillupgradeExpDAO skillupgradeExpDAO;
	
	public SkillupgradeExpDAO getSkillupgradeExpDAO() {
		return skillupgradeExpDAO;
	}

	public void setSkillupgradeExpDAO(SkillupgradeExpDAO skillupgradeExpDAO) {
		this.skillupgradeExpDAO = skillupgradeExpDAO;
	}

	public SkillDAO getSkillDAO() {
		return skillDAO;
	}

	public void setSkillDAO(SkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}

	@Override
	public Skill getSkillByID(int id) {
		return mapSkill.get(id);
	}

	@Override
	public List<Skill> getSkillList() {
		return listSkills;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		SkillExample example = new SkillExample();
		example.setOrderByClause("f_id");
		listSkills=this.skillDAO.selectByExample(example);
		mapSkill=BeanTool.listToMap(listSkills, "id");
		
	}

	@Override
	public List<SkillupgradeExp> getSkillupgradeExp() {
		SkillupgradeExpExample example = new SkillupgradeExpExample();
		example.setOrderByClause("Id");
		return skillupgradeExpDAO.selectByExample(example);
	}

}
