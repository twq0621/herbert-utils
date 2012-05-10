package net.snake.dao.skilleffect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.common.BeanTool;

import org.springframework.beans.factory.InitializingBean;

public class SkillEffectProviderImpl implements SkillEffectProvider, InitializingBean {

	private List<SkillEffect> listSkillEffects;
	private SkillEffectDAO skillEffectDAO;
	private Map<Integer, SkillEffect> mapSkillEffect = new HashMap<Integer, SkillEffect>();

	public SkillEffectDAO getSkillEffectDAO() {
		return skillEffectDAO;
	}

	public void setSkillEffectDAO(SkillEffectDAO skillEffectDAO) {
		this.skillEffectDAO = skillEffectDAO;
	}

	@Override
	public SkillEffect getSkillEffectByID(int id) {
		return mapSkillEffect.get(id);
	}

	@Override
	public List<SkillEffect> getSkilleEffectsList() {
		return listSkillEffects;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		SkillEffectExample example = new SkillEffectExample();
		example.setOrderByClause("f_id");
		listSkillEffects = this.skillEffectDAO.selectByExample(example);
		System.out.println("listSkillEffects-------" + listSkillEffects.size());
		mapSkillEffect = BeanTool.listToMap(listSkillEffects, "id");

	}

}
