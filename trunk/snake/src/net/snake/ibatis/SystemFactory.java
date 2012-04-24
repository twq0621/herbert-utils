package net.snake.ibatis;

import java.io.InputStream;
import java.sql.Connection;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * 一个工厂类，用来得到iBATIS的SqlMapClient
 * 
 * @author serv_dev
 * 
 */
public class SystemFactory {
	private static Logger logger = Logger.getLogger(SystemFactory.class);

	private static SqlMapClient characterSqlMapClient;// 角色数据
	private static SqlMapClient gamedataSqlMapClient;
	private static SqlMapClient accountSqlMapClient;
	// private static SqlMapClient logSqlMapClient;// 日志
	private static SqlMapClient rechargeSqlMapClient;// 支付

	private static SqlMapClient accountTrantsSqlMapClient;
	private static SqlMapClient characterTrantsSqlMapClient;//

	// public static void initLogSqlMapClient() throws Exception {
	// if (logSqlMapClient == null) {
	// logSqlMapClient = getSqlMapClient("/gameserverconfig/game_log_sqlmapconfig.xml");
	// }
	// }

	public static void initRechargeSqlMapClient() throws Exception {
		if (rechargeSqlMapClient == null) {
			rechargeSqlMapClient = getSqlMapClient("/game_recharge_sqlmapconfig.xml");
		}
	}

	public static void initAccountSQLMapClient() throws Exception {

		if (accountSqlMapClient == null) {
			accountSqlMapClient = getSqlMapClient("/gameserverconfig/game_account_sqlmapconfig.xml");
		}

	}

	public static void initCharacterSQLMapClient() throws Exception {

		if (characterSqlMapClient == null) {
			characterSqlMapClient = getSqlMapClient("/gameserverconfig/game_character_sqlmapconfig.xml");
		}

	}

	public static void initGameDataSQLMapClient() throws Exception {
		if (gamedataSqlMapClient == null) {
			gamedataSqlMapClient = getSqlMapClient("/gameserverconfig/game_gamedata_sqlmapconfig.xml");
		}
	}

	public static void initAccountTrantsSQLMapClient() throws Exception {
		if (accountTrantsSqlMapClient == null) {
			accountTrantsSqlMapClient = getSqlMapClient("/gameserverconfig/game_account_sqlmapconfig_trants.xml");
		}
	}

	public static void initCharacterTrantsSQLMapClient() throws Exception {
		if (characterTrantsSqlMapClient == null) {
			characterTrantsSqlMapClient = getSqlMapClient("/gameserverconfig/game_character_sqlmapconfig_trants.xml");
		}
	}

	private static SqlMapClient getSqlMapClient(String ibatisconfig) throws Exception {
		InputStream dbMapConfigFile = SystemFactory.class.getResourceAsStream(ibatisconfig);
		SqlMapClient sqlMapperclient = SqlMapClientBuilder.buildSqlMapClient(dbMapConfigFile);
		Connection con = sqlMapperclient.getDataSource().getConnection();
		con.close();
		logger.debug(ibatisconfig + " init");
		return sqlMapperclient;
	}

	/**
	 * 初始化iBATIS配置文件，得到SqlMapClient
	 * 
	 * @return
	 */
	public static SqlMapClient getCharacterSqlMapClient() {
		return characterSqlMapClient;
	}

	public static SqlMapClient getGamedataSqlMapClient() {
		return gamedataSqlMapClient;
	}

	public static SqlMapClient getAccountSqlMapClient() {
		return accountSqlMapClient;
	}

	public static SqlMapClient getAccountTrantsSqlMapClient() {
		return accountTrantsSqlMapClient;
	}

	public static SqlMapClient getRechargeSqlMapClient() {
		return rechargeSqlMapClient;
	}

	public static SqlMapClient getCharacterTrantsSqlMapClient() {
		return characterTrantsSqlMapClient;
	}

	public static void initialSQLMpClient() throws Exception {
		initAccountSQLMapClient();
		initCharacterSQLMapClient();
		initGameDataSQLMapClient();
		initAccountTrantsSQLMapClient();
		initCharacterTrantsSQLMapClient();
	}
}
