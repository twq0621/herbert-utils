package net.snake.gamemodel.heroext.channel.response;

import net.snake.commons.StringUtil;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.logic.ChannelJiSuanTools;
import net.snake.netio.ServerResponse;

public class ChannelResponse50216 extends ServerResponse {
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
	public ChannelResponse50216(Hero character) {
		setMsgCode(50216);
		// "筑龙境加成
		writeInt(character.getId());
		writeInt(character.getMyChannelManager().getAttack() + character.getMyChannelManager().getAttackZhenLong());
		writeInt(character.getMyChannelManager().getDefence() + character.getMyChannelManager().getDefenceZhenLong());
		writeInt(character.getMyChannelManager().getCrt() + character.getMyChannelManager().getCrtZhenLong());
		writeInt(character.getMyChannelManager().getDodge() + character.getMyChannelManager().getDodgeZhenLong());
		writeInt(character.getMyChannelManager().getHp() + character.getMyChannelManager().getHpZhenlong());
		writeInt(character.getMyChannelManager().getMp() + character.getMyChannelManager().getMpZhenlong());
		writeInt(character.getMyChannelManager().getSp() + character.getMyChannelManager().getSpZhenLong());

		writeInt(character.getChannelBeidongExp());
		writeInt(character.getMyChannelManager().getChannelZhenlong().getChannelExp());

		// -----------------------------------------------经脉总揽
		ChannelJiSuanTools channelJiSuanTools = character.getMyChannelManager().getJiSuanTools();
		ChannelJiSuanTools channeljiSuanZheLongTools = character.getMyChannelManager().getJiSuanZhenLongTools();
		String weizhijinmaiString;

		String zhenlongString = character.getChannelRealdragon();
		if (zhenlongString.length() < 3 || StringUtil.isEmpty(zhenlongString)) {
			// 普通
			writeByte(0);
			weizhijinmaiString = channelJiSuanTools.getJinmaiLieBiao();
		} else {
			// 真龙
			writeByte(1);
			weizhijinmaiString = channeljiSuanZheLongTools.getJinmaiLieBiao();
		}

		if ("".equals(weizhijinmaiString)) {
			writeByte(0);
		} else {

			String weizhi[] = weizhijinmaiString.split(";");
			writeByte(weizhi.length);
			for (int i = 0; i < weizhi.length; i++) {
				String[] string = weizhi[i].split(",");
				writeByte(Byte.parseByte(string[0]));
				writeShort(Short.valueOf(string[1]));

			}
		}

	}

}
