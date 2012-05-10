package net.snake.db;
import java.sql.*;

import org.apache.log4j.Logger;

public class data{
	
	private static final Logger logger = Logger.getLogger(data.class);
String sDBDriver = "org.gjt.mm.mysql.Driver";
String sConnStr = "jdbc:mysql://192.168.1.110:3306/";
//String sDBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//String sConnStr = PropUtils.getProperties("dburl");
String rootname ="root";
String rootpawd = "abc123";
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;


public data()
{
try
{Class.forName(sDBDriver).newInstance();
}
catch(java.lang.ClassNotFoundException e)
{
System.err.println("sql_data(): " + e.getMessage());
}
catch (InstantiationException e) {
			logger.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		}
}


public void executeInsert(String sql)
{
try
{
conn = DriverManager.getConnection(sConnStr,rootname,rootpawd);
stmt = conn.createStatement();
stmt.executeUpdate(sql);
stmt.close();
conn.close();
}
catch(SQLException ex)
{System.err.println("sql_data.executeUpdate:"+ex.getMessage());
}
}

public ResultSet executeQuery(String sql)
{
try
{
	conn = DriverManager.getConnection(sConnStr,rootname,rootpawd);
	stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
    rs = stmt.executeQuery(sql);


}
catch(SQLException ex)
{
System.err.println("sql_data.executeQuery:"+ex.getMessage());
}
return rs;
}

public void executeUpdate(String sql)
{
    try {
    conn = DriverManager.getConnection(sConnStr,rootname,rootpawd);
    stmt = conn.createStatement();
    stmt.executeUpdate(sql);
	stmt.close();
    conn.close();
    }
    catch(SQLException ex) {
      System.err.println("aq.executeQuery: " + ex.getMessage());
    }
}

public void executeDelete(String sql)
{
try
{
	conn = DriverManager.getConnection(sConnStr,rootname,rootpawd);
	stmt = conn.createStatement();
	stmt.executeUpdate(sql);
	stmt.close();
    conn.close();
}
catch(SQLException ex)
{
System.err.println("sql_data.executeDelete:"+ex.getMessage());
}
}

public void closeRS(){
    try{
      rs.close();
     }
    catch(SQLException e){
      logger.error(e.getMessage(),e);
     }
  }

public void closeStmt(){
    try{
      stmt.close();
     }
    catch(SQLException e){
      logger.error(e.getMessage(),e);
     }
  }

public void closeConn(){
    try{
      conn.close();
     }
    catch(SQLException e){
    	e.printStackTrace();
      //logger.error(e.getMessage(),e);
     }
  }



}
