package net.snake.gamemodel.hero.response;

import net.snake.gamemodel.panel.bean.RoleUpgradeDesc;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

/**
 * 升级消息提提示
 * 
 * @author serv_dev
 */
public class CharacterUpGradeDescMsg50676 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(CharacterUpGradeDescMsg50676.class);

	public CharacterUpGradeDescMsg50676(RoleUpgradeDesc desc) {
		this.setMsgCode(50676);
		try {
			this.writeUTF(desc.getGradeDescI18n());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
