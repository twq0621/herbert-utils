package net.snake.gamemodel.npc.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10801
 * 
 * npc 修理装备 // 1.用计算公式如下： // 修理费用=（装备耐久-当前装备耐久）×修理单价 // 总修理费用为各个单件装备修理费用的总合。 //
 * 耐久值由策划填写，数值必须大于100. // 2.玩家每承受一下攻击耐久值-1，闪避攻击不会减少耐久。 //
 * 3.装备上耐久值不直接显示策划填写数据，而是现实换算后数值，具体如下： // 耐久上限=ＩＮＴ（耐久值÷１００） //
 * 当前耐久＝ＩＮＴ（当前耐久值÷１００） // 例：装备耐久值为１００００，当前耐久为８０００，则装备中显示如下 // 耐久：８０／１００。
 * 
 * 新需求（沉默 2010.8.30 ） 普通修理: (最大耐久度 - 当前耐久度) * 修理单价 其中最大耐久度和修理单价均为物品数据库中的配置字段
 * 普通修理对装备最大耐久度的耗损计算公式： 最大耐久度/当前耐久度/2 最多减10点耐久 特殊修理： (最大牛就度 - 当前耐久度) * 修理单价 * 3
 * 
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 10801, accessLimit = 200)
public class NpcRepairEquipmentProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		Short npcid = request.getShort();// npcid (以后验证使用)
		if(npcid<0){
			return;
		}
		byte reqairMethod = request.getByte();// TODO 修理类型（普通修理0、特殊修理1）
		byte num = request.getByte();// 修理的装备数量
		if (num == 0){
			return;
		}
		short positions[] = new short[num];// 修理的物品索引
		for (byte i = 0; i < num; i++) {
			short position = request.getShort();// 玩家包裹位置
			positions[i] = position;
		}
		character.getEquipmentController().repair(num, positions, reqairMethod);
	}

}
