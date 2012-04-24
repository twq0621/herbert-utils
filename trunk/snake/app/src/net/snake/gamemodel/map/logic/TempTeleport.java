package net.snake.gamemodel.map.logic;

public class TempTeleport implements Teleport{
	int targetsceneid;
	String targetSceneName;
	short[] point;
	public TempTeleport(int targetsceneid,String targetSceneName,short[] point) {
		this.targetsceneid=targetsceneid;
		this.targetSceneName=targetSceneName;
		this.point=point;
	}

	@Override
	public String getName() {
		return targetSceneName;
	}

	@Override
	public int getTargetSceneID() {
		return targetsceneid;
	}
	@Override
	public short getTargetX() {
		return point[0];
	}
	@Override
	public short getTargetY() {
		return point[1];
	}
	@Override
	public boolean isHideTelePort() {
		return false;
	}
	@Override
	public short getX() {
		return 0;
	}
	@Override
	public short getY() {
		return 0;
	}

	@Override
	public int getId() {
		return 0;
	}


}
