package net.snake.serverenv.vline.imp;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


import net.snake.GameServer;
import net.snake.across.serverenv.vline.impl.VLineServerAcrossImp;
import net.snake.commons.NetTool;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.DownLineRoleInstanceController;
import net.snake.gamemodel.line.bean.LineServerEntry;
import net.snake.gamemodel.line.persistence.LineServerDataManager;
import net.snake.gamemodel.map.logic.SceneManager;
import net.snake.netio.message.ResponseMsg;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.serverenv.vline.VLineServer;
import net.snake.serverenv.vline.VLineServerManager;
import net.snake.shell.Options;

public class VLineServerManagerImp implements VLineServerManager {
	private static Logger logger = Logger.getLogger(VLineServerManagerImp.class);
	private volatile Map<Integer, VLineServer> vlineserverMap = new ConcurrentHashMap<Integer, VLineServer>();

	public VLineServerManagerImp() {
	}

	public void shutDown() {
		for (VLineServer vlineserver : vlineserverMap.values()) {
			vlineserver.shutDown();
		}
	}

	@Override
	public VLineServer getVLineServerByLineID(int lineid) {
		return vlineserverMap.get(lineid);
	}

	@Override
	public Collection<VLineServer> getLineServersList() {
		return vlineserverMap.values();
	}

	@Override
	public void addVLineServer(VLineServer vlineserver) {
		vlineserverMap.put(vlineserver.getLineid(), vlineserver);
	}

	@Override
	public void removeCharacterById(int characterid) {
		for (VLineServer vlineserver : vlineserverMap.values()) {
			vlineserver.getOnlineManager().removeCharacter(characterid);
		}
	}

	@Override
	public Hero getCharacterById(int id) {
		Hero c = null;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			c = vlineserver.getOnlineManager().getByID(id);
			if (c != null) {
				return c;
			}
		}
		return null;
	}

	@Override
	public DownLineRoleInstanceController getDownLineRoleInstanceControllerByCharacterId(int roleId) {
		DownLineRoleInstanceController c = null;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			c = vlineserver.getRuningInstanceManager().getDownLineRoleInstanceController(roleId);
			if (c != null) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Hero getCharacterByAccountId(int id) {
		Hero c = null;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			c = vlineserver.getOnlineManager().getByAccountID(id);
			if (c != null) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void runToOnlineCharacter(CharacterRun run) {
		for (VLineServer vlineserver : vlineserverMap.values()) {
			vlineserver.runToOnlineCharacter(run);
		}
	}

	@Override
	public int getOnlineCount() {
		int i = 0;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			i += vlineserver.getOnlineManager().getplayercount();
		}
		return i;
	}

	@Override
	public void sendMsgToAllLineServer(ResponseMsg serverresponse) {
		for (VLineServer vlineserver : vlineserverMap.values()) {
			NetTool.sendToCharacters(vlineserver.getOnlineManager().getCharacterList(), serverresponse);
		}
	}

	/**
	 * 为跨服所作的更改 方法需要指定vLineServer的实现类
	 * 
	 * @param sid
	 * @param lineDataManager
	 * @param sceneManager
	 * @param vLineImmpClass
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private void reload(int sid, LineServerDataManager lineDataManager, SceneManager sceneManager, Class<? extends VLineServer> vLineImmpClass) throws SQLException,
			InstantiationException, IllegalAccessException {
		List<LineServerEntry> serverlist = lineDataManager.getLineServerEntryList(sid);

		for (LineServerEntry lineserverEntry : serverlist) {
			// 分线id=
			int lineid = lineserverEntry.getNum();
			// TODO 在实例内部为什么要依赖一个在外部访问的引用？
			VLineServer vLineServer = GameServer.vlineServerManager.getVLineServerByLineID(lineid);
			if (vLineServer == null) {
				VLineServer vsi = vLineImmpClass.newInstance();
				vsi.setSceneManager((SceneManager) (sceneManager.clone()));
				vsi.setLineid(lineid);
				vsi.setLinename(lineserverEntry.getName());
				vsi.startUp();
				addVLineServer(vsi);
			}
		}
	}

	@Override
	public void reload() {
		try {
			LineServerDataManager lineServerDataManager = GameServer.lineServerDataManager;
			SceneManager shareSceneManager = GameServer.shareSceneManager;
			// 防止加载顺序错误
			if (lineServerDataManager != null && shareSceneManager != null) {
				if (Options.IsCrossServ) {
					reload(Options.ServerId, lineServerDataManager, shareSceneManager, VLineServerAcrossImp.class);
				} else {
					reload(Options.ServerId, lineServerDataManager, shareSceneManager, VLineServerImp.class);
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (InstantiationException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "linelist";
	}

	public int getAllInstanceCharacterCount() {
		int i = 0;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			i = i + vlineserver.getRuningInstanceManager().getOnlineCount();
		}
		return i;
	}

	/**
	 * 获得副本掉线玩家个数（5分钟延时保留副本）
	 * 
	 * @return
	 */
	@Override
	public int getAllInstanceDownLineCharacterCount() {
		int i = 0;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			i = i + vlineserver.getRuningInstanceManager().getInstanceDownLineRoleCount();
		}
		return i;
	}

	@Override
	public int getAllInstanceCount() {
		int i = 0;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			i = i + vlineserver.getRuningInstanceManager().getInstanceCollection().size();
		}
		return i;
	}

	@Override
	public Hero getCharacterByName(String name) {
		Hero c = null;
		for (VLineServer vlineserver : vlineserverMap.values()) {
			c = vlineserver.getOnlineManager().getByName(name);
			if (c != null) {
				return c;
			}
		}
		return c;
	}
}
