package com.ibatis.common.jdbc.logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.ibatis.common.beans.ClassInfo;

/**
 * Statement proxy to add logging
 */
public class StatementLogProxy extends BaseLogProxy implements InvocationHandler {

	private static final Logger logger = Logger.getLogger("SQLLog");

	private Statement statement;
	private static final String select = "select";

	private StatementLogProxy(Statement stmt) {
		super();
		this.statement = stmt;
	}

	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		try {
			if (EXECUTE_METHODS.contains(method.getName())) {

				String sql = (String) params[0];
				if (!sql.trim().toLowerCase().startsWith(select)) {
					logger.info("Statement: " + removeBreakingWhitespace(sql));
					// logger.info("Parameters: " + getValueString());
					// logger.info("{pstm-" + id + "} Types: " + getTypeString());
				}
				if ("executeQuery".equals(method.getName())) {
					ResultSet rs = (ResultSet) method.invoke(statement, params);
					if (rs != null) {
						return ResultSetLogProxy.newInstance(rs);
					} else {
						return null;
					}
				} else {
					return method.invoke(statement, params);
				}
			} else if ("getResultSet".equals(method.getName())) {
				ResultSet rs = (ResultSet) method.invoke(statement, params);
				if (rs != null) {
					return ResultSetLogProxy.newInstance(rs);
				} else {
					return null;
				}
			} else if ("equals".equals(method.getName())) {
				Object ps = params[0];
				if (ps instanceof Proxy) {
					return new Boolean(proxy == ps);
				}
				return new Boolean(false);
			} else if ("hashCode".equals(method.getName())) {
				return new Integer(proxy.hashCode());
			} else {
				return method.invoke(statement, params);
			}
		} catch (Throwable t) {
			throw ClassInfo.unwrapThrowable(t);
		}
	}

	/**
	 * Creates a logging version of a Statement
	 * 
	 * @param stmt
	 *            - the statement
	 * @return - the proxy
	 */
	public static Statement newInstance(Statement stmt) {
		InvocationHandler handler = new StatementLogProxy(stmt);
		ClassLoader cl = Statement.class.getClassLoader();
		return (Statement) Proxy.newProxyInstance(cl, new Class[] { Statement.class }, handler);
	}

	/**
	 * return the wrapped statement
	 * 
	 * @return the statement
	 */
	public Statement getStatement() {
		return statement;
	}

}
