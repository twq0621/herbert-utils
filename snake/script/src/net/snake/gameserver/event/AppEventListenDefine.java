package net.snake.gameserver.event;

import net.snake.ai.formula.DrugFormula;
import net.snake.ai.formula.EquipmentFormula;
import net.snake.ai.formula.FightFormula;
import net.snake.ai.formula.SkillFormula;
import net.snake.ai.formula.TaskFormula;
import net.snake.commons.script.IAppEventListeners;
import net.snake.gameserver.event.copy.Babaililianying_MonsterDie;
import net.snake.gameserver.event.copy.Babaililianying_SceneExchange;
import net.snake.gameserver.event.copy.Babaililianying_SceneInit;
import net.snake.gameserver.event.copy.Bingguishensu_MonsterDie;
import net.snake.gameserver.event.copy.Bingguishensu_SceneInit;
import net.snake.gameserver.event.copy.Dazhaiguiying_SceneLoop;
import net.snake.gameserver.event.copy.Dazhaishinei_MonsterDie;
import net.snake.gameserver.event.copy.Dazhaishinei_SceneLoop;
import net.snake.gameserver.event.copy.Dijundaying_MonsterDie;
import net.snake.gameserver.event.copy.Dijundaying_SceneInit;
import net.snake.gameserver.event.copy.Dijundaying_SceneLoop;
import net.snake.gameserver.event.copy.Dujie_MonsterDie;
import net.snake.gameserver.event.copy.Dujie_SceneInit;
import net.snake.gameserver.event.copy.Ebayindi_MonsterDie;
import net.snake.gameserver.event.copy.Ebayindi_SceneInit;
import net.snake.gameserver.event.copy.Ebayingdi_SceneLoop;
import net.snake.gameserver.event.copy.FuZiZhen_HpChange;
import net.snake.gameserver.event.copy.FuZiZhen_MonsterDie;
import net.snake.gameserver.event.copy.FuZiZhen_SceneEnter;
import net.snake.gameserver.event.copy.FuZiZhen_SceneInit;
import net.snake.gameserver.event.copy.Guiyu_MonsterDie;
import net.snake.gameserver.event.copy.Guiyu_SceneInit;
import net.snake.gameserver.event.copy.Guiyu_SceneLoop;
import net.snake.gameserver.event.copy.JianlaoDazhaishinei_OutTeam;
import net.snake.gameserver.event.copy.Jianlao_MonsterDie;
import net.snake.gameserver.event.copy.Jianlao_SceneInit;
import net.snake.gameserver.event.copy.Jianlao_SceneLoop;
import net.snake.gameserver.event.copy.Juantuchonglai_SceneInit;
import net.snake.gameserver.event.copy.Kunlungumu_MonsterDie;
import net.snake.gameserver.event.copy.Kunlungumu_SceneInit;
import net.snake.gameserver.event.copy.LiangYiZhen_HpChange;
import net.snake.gameserver.event.copy.LiangYiZhen_MonsterDie;
import net.snake.gameserver.event.copy.LiangYiZhen_SceneInit;
import net.snake.gameserver.event.copy.Lianzhan_MonsterDie;
import net.snake.gameserver.event.copy.Lianzhan_SceneInit;
import net.snake.gameserver.event.copy.Lianzhan_SceneLoop;
import net.snake.gameserver.event.copy.NianLun_MonsterDie;
import net.snake.gameserver.event.copy.NianLun_SceneExcange;
import net.snake.gameserver.event.copy.NianLun_SceneInit;
import net.snake.gameserver.event.copy.Shouhuxuxian_SceneLoop;
import net.snake.gameserver.event.copy.SiShengShou_MonsterDie;
import net.snake.gameserver.event.copy.SiShengShou_SceneInit;
import net.snake.gameserver.event.copy.YaoTa_MonsterDie;
import net.snake.gameserver.event.copy.YaoTa_SceneInit;
import net.snake.gameserver.event.copy.Yongchuangdifu_MonsterDie;
import net.snake.gameserver.event.copy.Yongchuangdifu_SceneInit;
import net.snake.gameserver.event.copy.Zhubaodong_MonsterDie;
import net.snake.gameserver.event.copy.Zhubaodong_SceneInit;
import net.snake.gameserver.event.goods.GoodUse3400;
import net.snake.gameserver.event.goods.GoodUse3401;
import net.snake.gameserver.event.goods.GoodUse7033;
import net.snake.gameserver.event.goods.GoodUse7043;
import net.snake.gameserver.event.goods.GoodUse7084;
import net.snake.gameserver.event.goods.GoodUse7314;
import net.snake.gameserver.event.goods.MonsterGoodDrop1001;
import net.snake.gameserver.event.goods.MonsterGoodDrop1002;
import net.snake.gameserver.event.goods.MonsterGoodDrop1003;
import net.snake.gameserver.event.monster.Baiyuanwang_Zhuxian_Die;
import net.snake.gameserver.event.monster.Baiyuanwang_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.DuZhuWang_Zhuxian_Die;
import net.snake.gameserver.event.monster.DuZhuWang_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.FanYiWen_Yewai_Die;
import net.snake.gameserver.event.monster.FanYiWen_Yewai_HpChange;
import net.snake.gameserver.event.monster.FanYiWen_Zhuxian_Die;
import net.snake.gameserver.event.monster.FanYiWen_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.GongSunZhi_Yewai_Die;
import net.snake.gameserver.event.monster.GongSunZhi_Yewai_HpChange;
import net.snake.gameserver.event.monster.GongSunZhi_Zhuxian_Die;
import net.snake.gameserver.event.monster.GongSunZhi_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.HongLinBo_Yewai_Die;
import net.snake.gameserver.event.monster.HongLinBo_Yewai_HpChange;
import net.snake.gameserver.event.monster.HongLinBo_Zhuxian_Die;
import net.snake.gameserver.event.monster.HongLinBo_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.HuoDu_Yewai_Die;
import net.snake.gameserver.event.monster.HuoDu_Yewai_HpChange;
import net.snake.gameserver.event.monster.HuoDu_Zhuxian_Die;
import net.snake.gameserver.event.monster.HuoDu_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.JinlunFawang_Yewai_Die;
import net.snake.gameserver.event.monster.JinlunFawang_Yewai_HpChange;
import net.snake.gameserver.event.monster.JueqingguDizi_Zhuxian_Die;
import net.snake.gameserver.event.monster.JueqingguDizi_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.JueqingguShouwei_Zhuxian_Die;
import net.snake.gameserver.event.monster.JueqingguShouwei_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.LiMoChou_Yewai_Die;
import net.snake.gameserver.event.monster.LiMoChou_Yewai_HpChange;
import net.snake.gameserver.event.monster.LiuWuBian_zhiXian_Die;
import net.snake.gameserver.event.monster.LiuWuBian_zhiXian_HpChange;
import net.snake.gameserver.event.monster.MengguLishi_Yewai_Die;
import net.snake.gameserver.event.monster.MengguLishi_Yewai_HpChange;
import net.snake.gameserver.event.monster.MenggudaJiang_Yewai_Die;
import net.snake.gameserver.event.monster.MenggudaJiang_Yewai_HpChange;
import net.snake.gameserver.event.monster.MenggudaJiang_Zhuxian_Die;
import net.snake.gameserver.event.monster.MenggudaJiang_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.NiMoXing_Yewai_Die;
import net.snake.gameserver.event.monster.NiMoXing_Yewai_HpChange;
import net.snake.gameserver.event.monster.NiMoXing_Zhuxian_Die;
import net.snake.gameserver.event.monster.NiMoXing_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.ShoujianTongzi_Zhuxian_Die;
import net.snake.gameserver.event.monster.ShoujianTongzi_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.ShouyaoTongzi_Zhuxian_Die;
import net.snake.gameserver.event.monster.ShouyaoTongzi_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.XiaoXiangZi_Yewai_Die;
import net.snake.gameserver.event.monster.XiaoXiangZi_Yewai_HpChange;
import net.snake.gameserver.event.monster.XiaoXiangZi_Zhuxian_Die;
import net.snake.gameserver.event.monster.XiaoXiangZi_Zhuxian_HpChange;
import net.snake.gameserver.event.monster.ZhaoZhiJing_Yewai_Die;
import net.snake.gameserver.event.monster.ZhaoZhiJing_Yewai_HpChange;
import net.snake.gameserver.event.monster.ZhaoZhiJing_Zhuxian_Die;
import net.snake.gameserver.event.monster.ZhaoZhiJing_Zhuxian_HpChange;
import net.snake.gameserver.event.normalscene.BaoZangShiZhe_ShenDiaoGu_SceneUpdate;
import net.snake.gameserver.event.normalscene.XiangyangZC_SceneUpdate;

public class AppEventListenDefine implements IAppEventListeners {
	private String[] listeners = null;
	private String[] formulas = null;

	@Override
	public String[] getRuntimeListeners() {
		if (listeners == null) {
			listeners = new String[] {
					Babaililianying_MonsterDie.class.getName(),
					Babaililianying_SceneExchange.class.getName(),
					Babaililianying_SceneInit.class.getName(),
					FuZiZhen_HpChange.class.getName(),
					FuZiZhen_MonsterDie.class.getName(),
					FuZiZhen_SceneEnter.class.getName(),
					FuZiZhen_SceneInit.class.getName(),
					LiangYiZhen_HpChange.class.getName(),
					LiangYiZhen_MonsterDie.class.getName(),
					LiangYiZhen_SceneInit.class.getName(),
					Lianzhan_MonsterDie.class.getName(),
					Lianzhan_SceneInit.class.getName(),
					Lianzhan_SceneLoop.class.getName(),
					// MiZongZhen_SceneEnter.class.getName(),
					// MiZongZhen_SceneExcange.class.getName(),
					// MiZongZhen_SceneExit.class.getName(),
					// MiZongZhen_SceneInit.class.getName(),
					SiShengShou_MonsterDie.class.getName(),
					SiShengShou_SceneInit.class.getName(),
					// Tianguan_MonsterDie.class.getName(),
					// Tianguan_SceneExit.class.getName(),
					// Tianguan_SceneInit.class.getName(),

					// XunBaoShu_World.class.getName(),

					GoodUse3400.class.getName(), GoodUse3401.class.getName(), GoodUse7033.class.getName(), GoodUse7043.class.getName(), GoodUse7084.class.getName(),
					GoodUse7314.class.getName(), MonsterGoodDrop1001.class.getName(), MonsterGoodDrop1002.class.getName(), MonsterGoodDrop1003.class.getName(),
					BaoZangShiZhe_ShenDiaoGu_SceneUpdate.class.getName(), XiangyangZC_SceneUpdate.class.getName(), Baiyuanwang_Zhuxian_Die.class.getName(),
					Baiyuanwang_Zhuxian_HpChange.class.getName(), DuZhuWang_Zhuxian_Die.class.getName(), DuZhuWang_Zhuxian_HpChange.class.getName(),
					FanYiWen_Yewai_Die.class.getName(), FanYiWen_Yewai_HpChange.class.getName(), FanYiWen_Zhuxian_Die.class.getName(), FanYiWen_Zhuxian_HpChange.class.getName(),
					GongSunZhi_Yewai_Die.class.getName(), GongSunZhi_Yewai_HpChange.class.getName(), GongSunZhi_Zhuxian_Die.class.getName(),
					GongSunZhi_Zhuxian_HpChange.class.getName(), HongLinBo_Yewai_Die.class.getName(), HongLinBo_Yewai_HpChange.class.getName(),
					HongLinBo_Zhuxian_Die.class.getName(), HongLinBo_Zhuxian_HpChange.class.getName(), HuoDu_Yewai_Die.class.getName(), HuoDu_Yewai_HpChange.class.getName(),
					HuoDu_Zhuxian_Die.class.getName(), HuoDu_Zhuxian_HpChange.class.getName(), JinlunFawang_Yewai_Die.class.getName(), JinlunFawang_Yewai_HpChange.class.getName(),
					JueqingguDizi_Zhuxian_Die.class.getName(), JueqingguDizi_Zhuxian_HpChange.class.getName(), JueqingguShouwei_Zhuxian_Die.class.getName(),
					JueqingguShouwei_Zhuxian_HpChange.class.getName(), LiMoChou_Yewai_Die.class.getName(), LiMoChou_Yewai_HpChange.class.getName(),
					LiuWuBian_zhiXian_Die.class.getName(), LiuWuBian_zhiXian_HpChange.class.getName(), MenggudaJiang_Yewai_Die.class.getName(),
					MenggudaJiang_Yewai_HpChange.class.getName(), MenggudaJiang_Zhuxian_Die.class.getName(), MenggudaJiang_Zhuxian_HpChange.class.getName(),
					MengguLishi_Yewai_Die.class.getName(), MengguLishi_Yewai_HpChange.class.getName(), NiMoXing_Yewai_Die.class.getName(), NiMoXing_Yewai_HpChange.class.getName(),
					NiMoXing_Zhuxian_Die.class.getName(), NiMoXing_Zhuxian_HpChange.class.getName(), ShoujianTongzi_Zhuxian_Die.class.getName(),
					ShoujianTongzi_Zhuxian_HpChange.class.getName(), ShouyaoTongzi_Zhuxian_Die.class.getName(), ShouyaoTongzi_Zhuxian_HpChange.class.getName(),
					XiaoXiangZi_Yewai_Die.class.getName(), XiaoXiangZi_Yewai_HpChange.class.getName(), XiaoXiangZi_Zhuxian_Die.class.getName(),
					XiaoXiangZi_Zhuxian_HpChange.class.getName(), ZhaoZhiJing_Yewai_Die.class.getName(), ZhaoZhiJing_Yewai_HpChange.class.getName(),
					ZhaoZhiJing_Zhuxian_Die.class.getName(), ZhaoZhiJing_Zhuxian_HpChange.class.getName(), BaoZangShiZhe_ShenDiaoGu_SceneUpdate.class.getName(),
					XiangyangZC_SceneUpdate.class.getName(), Dujie_MonsterDie.class.getName(), Dijundaying_SceneInit.class.getName(), Dijundaying_SceneLoop.class.getName(),
					Dijundaying_MonsterDie.class.getName(), Jianlao_SceneInit.class.getName(), Jianlao_SceneLoop.class.getName(), Jianlao_MonsterDie.class.getName(),
					JianlaoDazhaishinei_OutTeam.class.getName(), Dazhaishinei_SceneLoop.class.getName(), Dazhaishinei_MonsterDie.class.getName(),
					Ebayindi_SceneInit.class.getName(), Ebayindi_MonsterDie.class.getName(), Ebayingdi_SceneLoop.class.getName(), Guiyu_SceneInit.class.getName(),
					Guiyu_SceneLoop.class.getName(), Guiyu_MonsterDie.class.getName(), Zhubaodong_SceneInit.class.getName(), Zhubaodong_MonsterDie.class.getName(),
					Dujie_SceneInit.class.getName(), NianLun_MonsterDie.class.getName(), NianLun_SceneExcange.class.getName(), NianLun_SceneInit.class.getName(),
					YaoTa_MonsterDie.class.getName(), YaoTa_SceneInit.class.getName(), Shouhuxuxian_SceneLoop.class.getName(), Dazhaiguiying_SceneLoop.class.getName(),
					Juantuchonglai_SceneInit.class.getName(), Bingguishensu_MonsterDie.class.getName(), Bingguishensu_SceneInit.class.getName(),
					Yongchuangdifu_SceneInit.class.getName(), Yongchuangdifu_MonsterDie.class.getName(), Kunlungumu_SceneInit.class.getName(),
					Kunlungumu_MonsterDie.class.getName()
			// Wushuang_SceneLoop.class.getName(),
			// Wushuang_MonsterDie.class.getName(),
			};
		}
		return listeners;
	}

	@Override
	public String[] getRuntimeFormulas() {
		if (formulas == null) {
			formulas = new String[] {

			EquipmentFormula.class.getName(),// 装备公式
					FightFormula.class.getName(),// 战斗公式
					SkillFormula.class.getName(),// 技能公式
					TaskFormula.class.getName(),// 任务公式
					DrugFormula.class.getName() // 药品公式
			};
		}
		return listeners;
	}

}
