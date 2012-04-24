package net.snake.gamemodel.auth.bean;

import net.snake.ibatis.IbatisEntity;

public class AuthConfig implements IbatisEntity {
	/**
	 * 
	 * This field corresponds to the database column auth_config.fid
	 * 
	 * 
	 */
	private Integer fid;

	/**
	 * 
	 * This field corresponds to the database column auth_config.md5key
	 * 
	 * 
	 */
	private String md5key;

	/**
	 * 
	 * This method returns the value of the database column auth_config.fid
	 * 
	 * @return the value of auth_config.fid
	 * 
	 * 
	 */
	public Integer getFid() {
		return fid;
	}

	/**
	 * 
	 * This method sets the value of the database column auth_config.fid
	 * 
	 * @param fid
	 *            the value for auth_config.fid
	 * 
	 * 
	 */
	public void setFid(Integer fid) {
		this.fid = fid;
	}

	/**
	 * 
	 * This method returns the value of the database column auth_config.md5key
	 * 
	 * @return the value of auth_config.md5key
	 * 
	 * 
	 */
	public String getMd5key() {
		return md5key;
	}

	/**
	 * 
	 * This method sets the value of the database column auth_config.md5key
	 * 
	 * @param md5key
	 *            the value for auth_config.md5key
	 * 
	 * 
	 */
	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}
}
