package net.snake.gamemodel.heroext.dantian.bean;

import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.heroext.dantian.persistence.DantianModelCacheManager;


/**
 * 丹田
 *@author serv_dev
 */
public class DanTian extends CharacterDanTian implements PropertyChangeListener {

	public DantianModel getModel(){
		return DantianModelCacheManager.getInstance().getModelById(getDantianid());
	}
	
	/**
	 * 获取技能对应的丹田武学层数加成
	 * @param skillid
	 * @return
	 */
	public int getWuXueGrade(int skillid){
		if(skillid==0){
			return 0;
		}
		String skillitem = getModel().getMengpaiSkillitem();
		if(skillitem!=null&&!skillitem.equals("")&&skillitem.contains(skillid+"")){
			return getModel().getAddMengpaiWuxue();
		}
		
		skillitem=getModel().getJianhuSkillitem();
		if(skillitem!=null&&!skillitem.equals("")&&skillitem.contains(skillid+"")){
			return getModel().getAddJianghuWuxue();
		}
		skillitem=getModel().getQitaSkillitem();
		if(skillitem!=null&&!skillitem.equals("")&&skillitem.contains(skillid+"")){
			return getModel().getAddQitaJuexue();
		}
		return 0;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.dantian;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		DantianModel model = getModel();
		PropertyEntity pe= new PropertyEntity();
		pe.addExtraProperty(Property.attack,model.getAddAttack());
		pe.addExtraProperty(Property.defence,model.getAddDefence());
	    pe.addExtraProperty(Property.crt,model.getAddCrt());
	    pe.addExtraProperty(Property.dodge,model.getAddDodge());
	    pe.addExtraProperty(Property.maxHp,model.getAddMaxhp());
		return pe;
	}
}
