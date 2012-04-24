package net.snake.across.character;

import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.fight.bean.XuanyuanBufferJiacheng;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.skill.bean.EffectInfo;


/**
 *  轩辕剑属性加成
 */

public class XuanyuanjianBufferProperty implements PropertyChangeListener{
	private PropertyEntity pe;
	private PropertyAdditionType propertyControllerType;
	private EffectInfo buffer;
	public XuanyuanjianBufferProperty(XuanyuanBufferJiacheng xuanyuanBuffer,PropertyAdditionType type){
		this.propertyControllerType=type;
		buffer=xuanyuanBuffer.getBuffer();
		creatPropertyEntity(xuanyuanBuffer);
	}
	private void creatPropertyEntity(XuanyuanBufferJiacheng xuanyuanBuffer){
		 pe=new PropertyEntity();
		 pe.setAttack(xuanyuanBuffer.getAtk());
		 pe.setDefend(xuanyuanBuffer.getDef());
		 pe.setCrt(xuanyuanBuffer.getCrt());
		 pe.setDodge(xuanyuanBuffer.getDodge());
		 pe.setMaxHp(xuanyuanBuffer.getHp());
		 pe.setMaxMp(xuanyuanBuffer.getMp());
		 pe.setMaxSp(xuanyuanBuffer.getSp());
		 pe.setMoveSpeed(xuanyuanBuffer.getMoveSpeed());
		 pe.setAttackSpeed(pe.getAttackSpeed());
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return propertyControllerType;
	}


	@Override
	public PropertyEntity getPropertyEntity() {
		return pe;
	}
	public EffectInfo getBuffer() {
		return buffer;
	}
	public void setBuffer(EffectInfo buffer) {
		this.buffer = buffer;
	}

}
