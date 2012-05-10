package net.snake.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class I18nSQL {
	
	public static void execI18nSQL() {
		Connection connection=null;
		Statement statement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=			DriverManager.getConnection("jdbc:mysql://192.168.1.110:3306/snake_gamedata?autoReconnect=true","root","abc123");
			statement=connection.createStatement();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			
			statement.executeUpdate("update t_goodmodel set f_name_i18n=f_name,f_desc_i18n=f_desc  ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("update t_npc set f_name_i18n=f_name  ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("update t_monster_model set f_name_i18n=f_name,f_speaks_i18n=f_speaks,f_desc_i18n=f_desc  ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("update t_task set f_name_i18n=f_name,f_taskTargetDes_i18n=f_taskTargetDes  ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.executeUpdate("update t_skill set f_name_i18n=f_name,f_desc_i18n=f_desc,f_upgrade_desc_i18n=f_upgrade_desc  ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
