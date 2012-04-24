package net.snake.gamemodel.hero.logic;

import java.util.HashSet;
import java.util.Set;

import net.snake.consts.PropertyAdditionType;

public class PropertyEntityManager extends PropertyEntity {
	private Set<PropertyChangeListener> propertyEntitys = new HashSet<PropertyChangeListener>();
	private PropertyAdditionType propertyControllerType;

	public void destroy() {
		if (propertyEntitys != null) {
			propertyEntitys.clear();
			// propertyEntitys=null;
		}
		propertyControllerType = null;
	}

	public PropertyEntityManager() {
	}

	public PropertyEntityManager(PropertyAdditionType propertyControllerType) {
		setPropertyControllerType(propertyControllerType);
	}

	public void setPropertyControllerType(PropertyAdditionType propertyControllerType) {
		this.propertyControllerType = propertyControllerType;
	}

	public PropertyAdditionType getPropertyControllerType() {
		return propertyControllerType;
	}

	protected void addPropertiesChangeListener(PropertyChangeListener listener) {
		propertyEntitys.add(listener);
	}

	public void recompute() {
		clearData();
		for (PropertyChangeListener li : propertyEntitys) {
			addPropertyEntity(li.getPropertyEntity());
		}
	}

	protected void removePropertiesChangeListener(PropertyChangeListener propertyentity) {
		propertyEntitys.remove(propertyentity);
	}
}
