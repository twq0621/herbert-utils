package net.snake.dao.avatar;




import java.util.List;

import net.snake.dao.effect.Effect;
import net.snake.dao.effect.EffectDAO;
import net.snake.dao.effect.EffectExample;
import net.snake.dao.sound.Sound;
import net.snake.dao.sound.SoundDAO;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author serv_dev 
 * 
 * 
 */
public class AvatarProviderImpl implements AvatarProvider,InitializingBean{

	private List<Avatar> listAvatar;
	private List<Effect> listEffect;
	private AvatarDAO avatarDAO;
	private EffectDAO effectDAO;
	private SoundDAO soundDAO;
	
	public SoundDAO getSoundDAO() {
		return soundDAO;
	}

	public void setSoundDAO(SoundDAO soundDAO) {
		this.soundDAO = soundDAO;
	}

	public EffectDAO getEffectDAO() {
		return effectDAO;
	}

	public void setEffectDAO(EffectDAO effectDAO) {
		this.effectDAO = effectDAO;
	}

	public AvatarDAO getAvatarDAO() {
		return avatarDAO;
	}

	public void setAvatarDAO(AvatarDAO avatarDAO) {
		this.avatarDAO = avatarDAO;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		listAvatar = avatarDAO.getAvatar_avatar();
//		listEffect = effectDAO.getAvatar_effect();
//System.out.println("listAvatar------------"+listAvatar.size());
//System.out.println("listEffect------------"+listEffect.size());
	}

	@Override
	public List<Avatar> getAvatar_Avatars() {
		return avatarDAO.getAvatar_avatar();
	}

	@Override
	public List<Effect> getAvatar_Effect() {
		return  effectDAO.getAvatar_effect();
	}

	@Override
	public List<Avatar> getAvatar_AvatarsByid(int id) {
		Avatar avatar = new Avatar();
		avatar.setId(id);
		return avatarDAO.getAvatar_avatarByid(avatar);
	}

	@Override
	public List<Effect> getAvatar_EffectByid(int id) {
		Effect effect= new Effect();
		effect.setId(id);
		return effectDAO.getAvatar_effectByid(effect);
	}

	@Override
	public List<Avatar> getAvatar_Avatars2(AvatarExample example) {
		
		return avatarDAO.selectByExample(example);
	}

	@Override
	public List<Effect> getAvatar_Effect2(EffectExample example) {
		return effectDAO.selectByExample(example);
	}

	@Override
	public List<Sound> getSound() {
		return soundDAO.getSoundList();
	}

	@Override
	public List<Sound> getSoundMP3() {
		return soundDAO.getSoundMP3();
	}

}
