package net.snake.gamemodel.hero.logic;

import java.util.Date;

import net.snake.commons.CertificationUtil;
import net.snake.gamemodel.auth.persistence.AuthConfigManager;
import net.snake.gamemodel.hero.bean.CharacterPhoto;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterPhotoManager;

/**
 * 角色图片管理器
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-4-2 下午01:33:31
 */
public class MyCharacterPhotoManager {
	private Hero character;
	private String updateLingPai;
	private long againTime = 60000; // 人物再次上传限制 1分钟
	private long longTimeUpdate = 600000; // 上传令牌验证时间 10分钟有效
	private long startUpLoadTime = 0;
	private long stopUpLoadTime = 0;

	public String getUpdateLingPai() {
		return updateLingPai;
	}

	public long getStartUpLoadTime() {
		return startUpLoadTime;
	}

	public void setStartUpLoadTime(long startUpLoadTime) {
		this.startUpLoadTime = startUpLoadTime;
	}

	public long getStopUpLoadTime() {
		return stopUpLoadTime;
	}

	public void setStopUpLoadTime(long stopUpLoadTime) {
		this.stopUpLoadTime = stopUpLoadTime;
	}

	/**
	 * 验证结果 0 没有请求令牌1令牌超时2在一次请求过快3成功
	 * 
	 * @param stopUpLoadTime
	 * @return
	 */
	public int YanZheng(long stopUpLoadTime) {
		if (getStartUpLoadTime() == 0) {
			return 0;
		}
		long time = stopUpLoadTime - startUpLoadTime;
		if (time > longTimeUpdate) {
			setStartUpLoadTime(0);
			return 1;
		}
		if (time < againTime) {
			return 2;
		}
		return 3;
	}

	public CharacterPhoto getCharacterPhoto() {
		return characterPhoto;
	}

	private CharacterPhoto characterPhoto;

	public MyCharacterPhotoManager(Hero character) {
		this.character = character;
	}

	public void setCharacterPhoto(CharacterPhoto characterPhoto) {
		this.characterPhoto = characterPhoto;
	}

	public void init() {
		CharacterPhoto characterPhoto = CharacterPhotoManager.getInstance().getCharacterPhoto(character.getId());
		if (null == characterPhoto) {
			characterPhoto = new CharacterPhoto();
			characterPhoto.setCharacterTime(new Date(System.currentTimeMillis()));
			characterPhoto.setCharacterId(character.getId());
			CharacterPhotoManager.getInstance().addCharacterPhoto(characterPhoto);
			this.characterPhoto = characterPhoto;
		} else {
			this.characterPhoto = characterPhoto;
		}
	}

	public String getLingPai() {
		StringBuilder sb = new StringBuilder();
		String md5 = "";
		String key = AuthConfigManager.getInstance().getMd5Key();
		Date date = new Date();
		md5 = String.valueOf(date.getTime());
		md5 = md5 + "," + character.getId();
		md5 = CertificationUtil.encodeBase64(md5);
		sb.append(md5);
		sb.append(",");
		sb.append(CertificationUtil.md5(md5 + key));
		return sb.toString();
	}

	public void save() {
		CharacterPhotoManager.getInstance().updateCharacterPhoto(characterPhoto);
	}
}
