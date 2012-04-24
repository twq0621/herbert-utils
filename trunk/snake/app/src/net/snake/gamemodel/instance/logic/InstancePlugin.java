package net.snake.gamemodel.instance.logic;

import java.util.List;

import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.Hufazhen;
import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.gamemodel.hero.bean.Hero;

public interface InstancePlugin {
	public void onCompleted(InstanceController instance,Hero character);
	public void flush(long now, InstanceController instance);
	public InstancePlugin newAnother();
	
	public boolean addGuard(int type,Tianshen shen);
	public boolean addGuard(int type,GuardImg guard,int tianjieNum);
	public boolean delGuard(int type,int guardId);
	
	public GuardImg[] getAllGuardImgs();
	
	public Hufazhen getHufazhen();
	public void setHufazhen(int id);
	
	public void normalGuards(List<GuardImg> guards);
	public List<GuardImg> normalGuards();
	public void advanceGuards(List<GuardImg> guards);
	public List<GuardImg> advanceGuards();
	public void flushMonster(InstanceController instance);
}
