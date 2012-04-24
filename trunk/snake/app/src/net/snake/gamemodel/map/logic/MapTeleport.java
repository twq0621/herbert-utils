package net.snake.gamemodel.map.logic;

import net.snake.gamemodel.map.bean.TransportDate;

/**
 * 地图传送点
 * 
 * @author serv_dev
 * 
 */
public class MapTeleport implements Teleport {
	private int id;// 传送点id ***
	private int scenId;
	private short x; // 传送点x坐标
	private short y; // 传送点y坐标
	private String name; // 目标地图的名称
	private int targetSceneID; // 目标地图的id
	private short targetX;
	private short targetY;
	public byte isHide=0;


	public MapTeleport() {
	}
/**
 * 
 * @param td
 * @param isHide ==0不隐藏 ==1隐藏
 */
	public MapTeleport(TransportDate td,byte isHide) {
		this.scenId = td.getSceneId();
		this.x = td.getX().shortValue();
		this.y = td.getY().shortValue();
		this.targetSceneID = td.getTargetSceneId();
		this.targetX = td.getTargetX().shortValue();
		this.targetY = td.getTargetY().shortValue();
		this.id=td.getTransportId();
		this.isHide=isHide;
		
	}
	public short getTargetX() {
		return targetX;
	}
	public short getTargetY() {
		return targetY;
	}
	public int getScenId() {
		return scenId;
	}
	/**
	 * 返回1 表示是隐藏传送点
	 * @return
	 */
    public boolean isHideTelePort(){
    	return this.isHide==1;
    }
	public void setScenId(int scenId) {
		this.scenId = scenId;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	public short getX() {
		return x;
	}

	public short getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public void setX(short x) {
		this.x = x;
	}

	public void setY(short y) {
		this.y = y;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTargetSceneID(int id) {
		this.targetSceneID = id;
	}

	public boolean isTeleport(short x, short y) {
		return x == this.x && y == this.y;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapTeleport other = (MapTeleport) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int getTargetSceneID() {
		return targetSceneID;
	}

}
