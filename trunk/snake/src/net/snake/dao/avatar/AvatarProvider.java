package net.snake.dao.avatar;



import java.util.List;

import net.snake.dao.effect.Effect;
import net.snake.dao.effect.EffectExample;
import net.snake.dao.sound.Sound;

/**
 * @author serv_dev
 *
 */
public interface AvatarProvider {

	List<Avatar> getAvatar_Avatars();
	List<Effect> getAvatar_Effect();
	List<Avatar> getAvatar_Avatars2(AvatarExample example);
	List<Effect> getAvatar_Effect2(EffectExample example);
	List<Avatar> getAvatar_AvatarsByid(int id);
	List<Effect> getAvatar_EffectByid(int id);
	List<Sound> getSound();
	List<Sound> getSoundMP3();
}
