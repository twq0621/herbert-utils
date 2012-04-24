package net.snake.serverenv.vline;

import java.util.Collection;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.DownLineRoleInstanceController;
import net.snake.netio.message.ResponseMsg;

/**
 * 分线服务器管理器
 * @author serv_dev
 *
 */
public interface VLineServerManager extends CacheUpdateListener{
	/**
	 * 添加分线服务器
	 * @param vlineserver
	 */
	public void addVLineServer(VLineServer vlineserver);
	/**
	 * 根据分线ID获得分线服务器
	 * @param lineid
	 * @return
	 */
	public VLineServer getVLineServerByLineID(int lineid);
	/**
	 * 获得分线服务器列表
	 * @return
	 */
	public Collection<VLineServer> getLineServersList();
	/**
	 * 从各分线中移除缓存
	 */
	public void removeCharacterById(int characterid);
	
	/**
	 * 全服搜索查看在线玩家
	 */
	public Hero getCharacterById(int characterid);
	public Hero getCharacterByName(String charactername);
	/**
	 * 根据帐号ID获得角色
	 * @param id
	 * @return
	 */
	public Hero getCharacterByAccountId(int accountid);
	/**
	 * 获得总在线数
	 * @return
	 */
	public int getOnlineCount();
	/**
	 * 对所有角色执行一项CharacterRun
	 * 使用说明
	 * vlineServerManager.runToOnlineCharacter(
	 * new CharacterRun() {
			@Override
			public void run(Character character) {
				character.getPlayer().logout();				
			}
		});		
	 */
	public void runToOnlineCharacter(CharacterRun run);
	/**
	 * 向所有角色发送消息
	 * @param serverresponse
	 */
	public void sendMsgToAllLineServer(ResponseMsg serverresponse);
	/**
	 * 关闭所有分线
	 */
	public void shutDown();
	
	/*
	 * 关闭所有分线
	
	public void stopAllNetListener(); */

	public int getAllInstanceCharacterCount();
	public int getAllInstanceCount();
	/**
	 * 获取角色上次下线时的副本信息
	 * @param roleId
	 * @return
	 */
	public DownLineRoleInstanceController getDownLineRoleInstanceControllerByCharacterId(int roleId);
	public int getAllInstanceDownLineCharacterCount();
}
