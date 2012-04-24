package net.snake.gamemodel.hero.logic;

import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.hero.bean.Hero;


/**
 * 加点管理器
 * @author serv_dev
 *
 */
public class AddPointManager implements PropertyChangeListener {

	private Hero character;
	public AddPointManager(Hero character) {
		this.character = character;
	}
	
	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.addpoint;
	}

	/**
	 * 一级属性	二级属性
			攻击	    2点攻击
			　     	10点生命值
						5点mp
			防御	    2点防御
					　	15点生命值
						5点mp
			爆发     1点暴击
					　	1点攻击
						5点mp
			身法		1点命中
					　	1点闪避
						5点mp
	 */
	@Override
	public PropertyEntity getPropertyEntity() {
		PropertyEntity propertyEntity = new PropertyEntity();

		propertyEntity.addExtraProperty(Property.attack, character.getAttackAddpoint()*2);
		propertyEntity.addExtraProperty(Property.maxHp, character.getAttackAddpoint()*10);
		propertyEntity.addExtraProperty(Property.maxMp, character.getAttackAddpoint()*5);
		
		propertyEntity.addExtraProperty(Property.defence, character.getDefenceAddpoint()*2);
		propertyEntity.addExtraProperty(Property.maxHp, character.getDefenceAddpoint()*15);
		propertyEntity.addExtraProperty(Property.maxMp, character.getDefenceAddpoint()*5);
		
		propertyEntity.addExtraProperty(Property.crt, character.getStrongAddpoint());
		propertyEntity.addExtraProperty(Property.attack, character.getStrongAddpoint());
		propertyEntity.addExtraProperty(Property.maxMp, character.getStrongAddpoint()*5);
		
		propertyEntity.addExtraProperty(Property.hit, character.getLightAddpoint());
		propertyEntity.addExtraProperty(Property.dodge, character.getLightAddpoint());
		propertyEntity.addExtraProperty(Property.maxMp, character.getLightAddpoint()*5);
		return propertyEntity;
	}

}
