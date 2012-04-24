package net.snake.gamemodel.hero.logic;
import net.snake.consts.PropertyAdditionType;

public interface PropertyChangeListener {

	public PropertyAdditionType getPropertyControllerType();
	
	public PropertyEntity getPropertyEntity();
}
