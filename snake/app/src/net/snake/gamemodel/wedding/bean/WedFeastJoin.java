package net.snake.gamemodel.wedding.bean;

import net.snake.commons.program.IntId;

/**
 * @author serv_dev
 */
public class WedFeastJoin extends FeastJoin {
	private final static IntId intID = new IntId();
	private int tempId = intID.getNextId();

	public int getTempId() {
		return tempId;
	}

}
