package net.snake.gamemodel.hero.logic;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;


import org.apache.mina.util.ConcurrentHashSet;
import org.apache.log4j.Logger;
public class DynamicUpdateObjManager implements Updatable {
	private static Logger logger = Logger.getLogger(DynamicUpdateObjManager.class);
	// 先加入的先判断,先执行

	private ConcurrentHashSet<Updatable> updateobj = new ConcurrentHashSet<Updatable>();
	private ConcurrentLinkedQueue<LaterTask> latertasklist = new ConcurrentLinkedQueue<LaterTask>();

	public void destory() {
		updateobj.clear();
		latertasklist.clear();
	}

	public void addFrameUpdateObject(Updatable object) {
		updateobj.add(object);
	}

	public void removeFrameUpdateObject(Updatable object) {
		updateobj.remove(object);
	}

	public void addFrameUpdateLaterTask(Runnable run, int milliseconds) {
		latertasklist.offer(new LaterTask(run, milliseconds));
	}

	private class LaterTask {
		public Runnable run;
		public SafeTimer st;

		public LaterTask(Runnable run, int milliseconds) {
			this.run = run;
			st = new SafeTimer(milliseconds);
		}
	}

	public void update(long now) {
		for (Updatable update : updateobj) {
			update.update(now);
		}
		for (Iterator<LaterTask> it = latertasklist.iterator(); it.hasNext();) {
			LaterTask task = it.next();
			if (task.st.isFirstOK(now)) {
				try {
					task.run.run();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				it.remove();
			}
		}
	}
	
	public void clearEffectFromOther(){
		latertasklist.clear();
	}
}
