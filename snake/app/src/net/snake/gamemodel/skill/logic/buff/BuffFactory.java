package net.snake.gamemodel.skill.logic.buff;

import net.snake.consts.EffectType;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.addprop.FTSH;
import net.snake.gamemodel.skill.logic.buff.addprop.GJL;
import net.snake.gamemodel.skill.logic.buff.addprop.HSFY;
import net.snake.gamemodel.skill.logic.buff.addprop.SHJM;
import net.snake.gamemodel.skill.logic.buff.arrow.Arrow_Tip;
import net.snake.gamemodel.skill.logic.buff.arrow.Nodefence;
import net.snake.gamemodel.skill.logic.buff.arrow.Nodirection;
import net.snake.gamemodel.skill.logic.buff.arrow.RagingFire;
import net.snake.gamemodel.skill.logic.buff.arrow.Sleep;
import net.snake.gamemodel.skill.logic.buff.base.BaseAffectPropertyBuf;
import net.snake.gamemodel.skill.logic.buff.dantian.JingmaiInvalid;
import net.snake.gamemodel.skill.logic.buff.dantian.NingXueLiHun;
import net.snake.gamemodel.skill.logic.buff.dantian.PoXueKuangSha;
import net.snake.gamemodel.skill.logic.buff.dantian.ShiMing;
import net.snake.gamemodel.skill.logic.buff.dantian.TanZhiShenTong;
import net.snake.gamemodel.skill.logic.buff.drug.FYYaoJi;
import net.snake.gamemodel.skill.logic.buff.drug.HorseHuoLiDan;
import net.snake.gamemodel.skill.logic.buff.drug.HuiTiDan;
import net.snake.gamemodel.skill.logic.buff.drug.JTYaoJi;
import net.snake.gamemodel.skill.logic.buff.drug.JuFaDan;
import net.snake.gamemodel.skill.logic.buff.drug.KGYaoJi;
import net.snake.gamemodel.skill.logic.buff.drug.LongQiDan;
import net.snake.gamemodel.skill.logic.buff.drug.QSYaoJi;
import net.snake.gamemodel.skill.logic.buff.drug.XuMingDan;
import net.snake.gamemodel.skill.logic.buff.drug.ZhenqiDan;
import net.snake.gamemodel.skill.logic.buff.equip.ContinuumKillSuit;
import net.snake.gamemodel.skill.logic.buff.lianti.Free_roushentuotaihuangu;
import net.snake.gamemodel.skill.logic.buff.special.AddHpMpSp;
import net.snake.gamemodel.skill.logic.buff.special.AttackDefenceBuff;
import net.snake.gamemodel.skill.logic.buff.special.ChiHuan;
import net.snake.gamemodel.skill.logic.buff.special.Double10ExpY;
import net.snake.gamemodel.skill.logic.buff.special.Double5ExpY;
import net.snake.gamemodel.skill.logic.buff.special.DoubleExpY;
import net.snake.gamemodel.skill.logic.buff.special.DoubleHorseExpY;
import net.snake.gamemodel.skill.logic.buff.special.DoubleZhenqiY;
import net.snake.gamemodel.skill.logic.buff.special.Du;
import net.snake.gamemodel.skill.logic.buff.special.FengMp;
import net.snake.gamemodel.skill.logic.buff.special.FengSp;
import net.snake.gamemodel.skill.logic.buff.special.Immb;
import net.snake.gamemodel.skill.logic.buff.special.InfluenceDefend;
import net.snake.gamemodel.skill.logic.buff.special.InfluenceWeapon;
import net.snake.gamemodel.skill.logic.buff.special.JiTui;
import net.snake.gamemodel.skill.logic.buff.special.JuejiBuff;
import net.snake.gamemodel.skill.logic.buff.special.MaMu;
import net.snake.gamemodel.skill.logic.buff.special.MaNai;
import net.snake.gamemodel.skill.logic.buff.special.PkProtect;
import net.snake.gamemodel.skill.logic.buff.special.ReduceAttackSpeed;
import net.snake.gamemodel.skill.logic.buff.special.ReduceCrt;
import net.snake.gamemodel.skill.logic.buff.special.ReduceDodge;
import net.snake.gamemodel.skill.logic.buff.special.ReduceMoveSpeed;
import net.snake.gamemodel.skill.logic.buff.special.UnAttackDefenceBuff;
import net.snake.gamemodel.skill.logic.buff.special.UnHpBuff;
import net.snake.gamemodel.skill.logic.buff.special.UnWithstand;
import net.snake.gamemodel.skill.logic.buff.special.XiXue;
import net.snake.gamemodel.skill.logic.buff.special.Zuoqilonglin;
import net.snake.gamemodel.skill.logic.buff.special.Zuoqizhufu;
import net.snake.gamemodel.skill.logic.buff.spouse.BiYiShuangFei;
import net.snake.gamemodel.skill.logic.buff.spouse.FengYuTongJi;
import net.snake.gamemodel.skill.logic.buff.spouse.Guard;
import net.snake.gamemodel.skill.logic.buff.spouse.HongYan;
import net.snake.gamemodel.skill.logic.buff.tip.RT_up_lucktime_Tip;



public class BuffFactory {

	/**
	 * 生命值1、内力值2、怒气值3,、吸血5、内力值上限8、生命值上限9、会心11、
	 * 闪避12、命中13、攻击力14、防御力15、封内力16、封体力17、
	 * (门派绝学2)点穴18、(门派绝学3)中毒19、打落武器20、打落防具21、(门派绝学1)击退22、马奶23、连斩24
	 * @param effect
	 * @return
	 */
	public static Buff getDef(EffectInfo effect){
		int type = effect.getType();
		switch (type) {
		case EffectType.nothing:
			return new BuffNull(effect);
		case EffectType.hp:
		case EffectType.sp:
		case EffectType.mp:
			return new AddHpMpSp(effect);
		case EffectType.xixue:
			return new XiXue(effect);
		case EffectType.attack:
		case EffectType.defence:
		case EffectType.attackspeed:
		case EffectType.movespeed:
		case EffectType.maxHp:
		case EffectType.maxMp:
		case EffectType.maxSp:
		case EffectType.dodge:
		case EffectType.crt:
		case EffectType.hit:
			return new BaseAffectPropertyBuf(effect);
		case EffectType.zhenqi:
			return new ZhenqiDan(effect);
		case EffectType.fengMp:
			return new FengMp(effect);
		case EffectType.fengSp:
			return new FengSp(effect);
		case EffectType.dianxue:
			return new Immb(effect);
		case EffectType.du:
			return new Du(effect);
		case EffectType.fengwuqi:
			return new InfluenceWeapon(effect);
		case EffectType.fengfanju:
			return new InfluenceDefend(effect);
		case EffectType.jitui:
			return new JiTui(effect);
		case EffectType.manai:
			return new MaNai(effect);
		case EffectType.lianzhan: //连斩系统buffer
			return new ContinuumKillSuit(effect);
		case EffectType.doubleexp:
			return new DoubleExpY(effect);
		case EffectType.doublezhenqi:
			return new DoubleZhenqiY(effect);
		case EffectType.doublezuoqiexp:
			return new DoubleHorseExpY(effect);
		case EffectType.pkprotect:
			return new PkProtect(effect);
		case EffectType.xumingDan:
			return new XuMingDan(effect);
		case EffectType.huitiDan:
			return new HuiTiDan(effect);
		case EffectType.horsehuoliDan:
			return new HorseHuoLiDan(effect);
		case EffectType.jufaDan:
			return new JuFaDan(effect);
		case EffectType.fyYJ:
			return new FYYaoJi(effect);
		case EffectType.jtYJ:
			return new JTYaoJi(effect);
		case EffectType.qsYJ:
			return new QSYaoJi(effect);
		case EffectType.kgYJ:
			return new KGYaoJi(effect);
		case EffectType.chihuan:
			return new ChiHuan(effect);
		case EffectType.mamu:
			return new MaMu(effect);
		case EffectType.zuoqizhufu:
			return new Zuoqizhufu(effect);
		case EffectType.reduceAttackSpeed:
			return new ReduceAttackSpeed(effect);
		case EffectType.reduceMoveSpeed:
			return new ReduceMoveSpeed(effect);
		case EffectType.reduceCrt:
			return new ReduceCrt(effect);
		case EffectType.reduceDodge:
			return new ReduceDodge(effect);
		case EffectType.wudi:
			return new UnWithstand(effect);
		case EffectType.double5exp:
			return new Double5ExpY(effect);
		case EffectType.double10exp:
			return new Double10ExpY(effect);
		case EffectType.fengYuTongJi:
			return new FengYuTongJi(effect);
		case EffectType.biyishuangfei:
			return new BiYiShuangFei(effect);
		case EffectType.guard:
			return new Guard(effect);
		case EffectType.hongyan:
			return new HongYan(effect);
		case EffectType.longlindan:
		case EffectType.yangtianmiji:
			return new Zuoqilonglin(effect);
		case EffectType.nodefence:
			return new Nodefence(effect);
		case EffectType.nodirection:
			return new Nodirection(effect);
		case EffectType.sleep:
			return new Sleep(effect);
		case EffectType.liehuo:
			return new RagingFire(effect);
		case EffectType.gjl:
			return new GJL(effect);
		case EffectType.ftsh:
			return new FTSH(effect);
		case EffectType.hsfy:
			return new HSFY(effect);
		case EffectType.shjm:
			return new SHJM(effect);
		case EffectType.jianzhi_tip:
			return new Arrow_Tip(effect);
		case EffectType.rt_up_time_tip:
			return new RT_up_lucktime_Tip(effect);
		case EffectType.shi_ming:
			return new ShiMing(effect);
		case EffectType.po_xue_kuang_sha:
			return new PoXueKuangSha(effect);
		case EffectType.ning_xue_li_hun:
			return new NingXueLiHun(effect);
		case EffectType.tan_zhi_shen_tong:
			return new TanZhiShenTong(effect);
		case EffectType.jingmai_invalid:
			return new JingmaiInvalid(effect);
		case EffectType.long_qi_dan:
			return new LongQiDan(effect);
		case EffectType.free_roushentuotaihuangu:
			return new Free_roushentuotaihuangu(effect);
		case EffectType.unattackdefence:
			return new UnAttackDefenceBuff(effect);
		case EffectType.attackdefence:
			return new AttackDefenceBuff(effect);
		case EffectType.unhpbuff:
			return new UnHpBuff(effect);
		case EffectType.jueji:
			return new JuejiBuff(effect);
		default:
			return new BuffNull2(effect);
		}
	
	}
	
}
