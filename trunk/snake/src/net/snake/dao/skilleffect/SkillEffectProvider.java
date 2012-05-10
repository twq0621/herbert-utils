package net.snake.dao.skilleffect;

import java.util.List;

public interface SkillEffectProvider {
	
	SkillEffect getSkillEffectByID(int id);
	List<SkillEffect> getSkilleEffectsList();
}
