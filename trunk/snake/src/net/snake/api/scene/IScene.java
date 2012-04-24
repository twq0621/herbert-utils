package net.snake.api.scene;

import net.snake.api.IReuseable;

public interface IScene extends IReuseable {
	public short getInPositionX();
	public short getInPositionY();
	public IScene newAnother() throws CloneNotSupportedException;
	public short[] randomPosition(short x, short y, int distence);
}
