package net.snake.gamemodel.map.logic;

import java.util.HashMap;
import java.util.HashSet;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;

/**
 * 用于检查是否第一次到达某个地点
 * @author serv_dev
 *
 */
public class LocationMonitor {
	// key="x_y_w_h" value="id_type" type=0角色，type=1怪物
	HashMap<String, HashSet<String>> con = new HashMap<String, HashSet<String>>();
	public void clearHistory(){
		con.clear();
	}
	public boolean isFirstEnterArea(VisibleObject obj, int x, int y, int w,
			int h) {
		String key = getKey(x, y, w, h);
		HashSet<String> t = con.get(key);
		if (t == null) {
			t = new HashSet<String>();
			con.put(key, t);
		}
		String value=getValue(obj);
		if (t.contains(value)) {
			return false;// 已经进去过了
		}
		int nowx = obj.getX();
		int nowy = obj.getY();
		if (nowx >= x && nowy >= y && nowx < x + w && nowy < y + h) {
			t.add(value);
			return true;
		}
		return false;

	}

	private String getValue(VisibleObject obj) {
		if (obj.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			return obj.getId() + "_" + 0;
		} else {
			return obj.getId() + "＿" + 1;
		}
	}

	private String getKey(int x, int y, int w, int h) {
		return x + "_" + y + "_" + w + "_" + h;
	}
}
