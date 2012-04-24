package net.snake.gamemodel.heroext.channel.response;

import net.snake.gamemodel.hero.bean.Breakthrough;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.BreakthroughManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.netio.ServerResponse;

public class ChannelResponse50212 extends ServerResponse {
	/**
	 * "筑龙境加成【攻击(int)，防御(int)，爆击(int)，闪避(int)，生命上限(int)，内力上限(int)，体力上限(int)】，<br/>
	 * 真龙境加成【攻击(int)，防御(int)，爆击(int)，闪避(int)，生命上限(int)，内力上限(int)，体力上限(int)】，<br/>
	 * 所需龙珠数量(int)，真气消耗(int)，成功几率(int,例如23%的成功率则该值为2300)"
	 */
	public ChannelResponse50212(Hero character) {
		setMsgCode(50212);
		// try {
		// "筑龙境加成
		writeInt(ChannelManager.getInstance().getAttack());
		writeInt(ChannelManager.getInstance().getDefence());
		writeInt(ChannelManager.getInstance().getCrt());
		writeInt(ChannelManager.getInstance().getdodge());
		writeInt(ChannelManager.getInstance().getHp());
		writeInt(ChannelManager.getInstance().getMp());
		writeInt(ChannelManager.getInstance().getSp());
		// 真龙境加成
		writeInt(ChannelRealdragonManager.getInstance().getAttackZhenLong());
		writeInt(ChannelRealdragonManager.getInstance().getDefenceZhenLong());
		writeInt(ChannelRealdragonManager.getInstance().getCrtZhenLong());
		writeInt(ChannelRealdragonManager.getInstance().getDodgeZhenLong());
		writeInt(ChannelRealdragonManager.getInstance().getHpZhenlong());
		writeInt(ChannelRealdragonManager.getInstance().getMpZhenlong());
		writeInt(ChannelRealdragonManager.getInstance().getSpZhenLong());
		// 所需龙珠
		Breakthrough breakthrough = BreakthroughManager.getInstance().getBreakthroughMap().get(1);// 1经脉突破瓶颈需求
																									// 是用type区分的
		writeInt(Integer.valueOf(breakthrough.getGoomodeIdCount().split("[*]")[1]));
		writeInt(breakthrough.getNeedZhenqi());
		writeInt(breakthrough.getOdds());

		// } catch (IOException e) {
		// logger.error(e.getMessage(),e);
		// }

	}

}
