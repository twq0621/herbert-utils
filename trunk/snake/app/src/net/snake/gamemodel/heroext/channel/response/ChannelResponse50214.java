package net.snake.gamemodel.heroext.channel.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class ChannelResponse50214 extends ServerResponse {
	/**
	 * "筑龙境加成【攻击(int)，防御(int)，爆击(int)，闪避(int)，生命上限(int)，内力上限(int)，体力上限(int)】，<br/>
	 * 真龙境加成【攻击(int)，防御(int)，爆击(int)，闪避(int)，生命上限(int)，内力上限(int)，体力上限(int)】，<br/>
	 * 所需龙珠数量(int)，真气消耗(int)，成功几率(int,例如23%的成功率则该值为2300)"<br/>
	 * attack(1), // 攻击力<br/>
	 * defence(2), // 防御<br/>
	 * crt(3), // 暴击<br/>
	 * dodge(4), // 闪避<br/>
	 * maxHp(5), // 生命值上限<br/>
	 * maxSp(6),// 体力值上限<br/>
	 * maxMp(7),//内力上限
	 */
	public ChannelResponse50214(Hero character) {
		setMsgCode(50214);
		// "筑龙境加成
		writeInt(character.getMyChannelManager().getAttack() + character.getMyChannelManager().getAttackZhenLong());
		writeInt(character.getMyChannelManager().getDefence() + character.getMyChannelManager().getDefenceZhenLong());
		writeInt(character.getMyChannelManager().getCrt() + character.getMyChannelManager().getCrtZhenLong());
		writeInt(character.getMyChannelManager().getDodge() + character.getMyChannelManager().getDodgeZhenLong());
		writeInt(character.getMyChannelManager().getHp() + character.getMyChannelManager().getHpZhenlong());
		writeInt(character.getMyChannelManager().getMp() + character.getMyChannelManager().getMpZhenlong());
		writeInt(character.getMyChannelManager().getSp() + character.getMyChannelManager().getSpZhenLong());
		writeInt(character.getChannelBeidongExp());
		writeInt(character.getMyChannelManager().getChannelZhenlong().getChannelExp());
	}

}
