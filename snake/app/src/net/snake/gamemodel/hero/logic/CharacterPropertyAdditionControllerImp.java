package net.snake.gamemodel.hero.logic;

import net.snake.ai.formula.Formula;
import net.snake.consts.EffectType;
import net.snake.consts.PropertyAdditionType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.map.bean.SceneObj;
//import net.snake.script.ScriptEventCenter;
import net.snake.commons.script.SPropertyEntity;
import org.apache.log4j.Logger;

/**
 * 人物属性容器
 * 
 * @author serv_dev
 * 
 */
public class CharacterPropertyAdditionControllerImp extends PropertyAdditionControllerImp {

	public CharacterPropertyAdditionControllerImp(VisibleObject vo) {
		super(vo);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CharacterPropertyAdditionControllerImp.class);

	private final PropertyEntityManager jingmai = new PropertyEntityManager();// 经脉
	private final PropertyEntityManager zhuangbei = new PropertyEntityManager();// 装备
	private final PropertyEntityManager xinfa = new PropertyEntityManager();// 心法
	private final PropertyEntityManager chenghao = new PropertyEntityManager();// 称号
	// 放入到这里的攻击力\防御力为百分比
	private final PropertyEntityManager zhengfa = new PropertyEntityManager();// 队伍阵法
	// 放入到这里的攻击力\防御力\攻击速度\移动速度为百分比
	private final PropertyEntityManager zuoqi = new PropertyEntityManager();// 坐骑
	private final PropertyEntityManager buff = new PropertyEntityManager();// buff

	private final PropertyEntityManager zuoqiJineng = new PropertyEntityManager();// 坐骑技能放入到这里的
	// 攻击力\防御力为百分比
	private final PropertyEntityManager taoZhuang = new PropertyEntityManager();// 套装
	private final PropertyEntityManager chuzhanzuoqiJineng = new PropertyEntityManager();// 出战坐骑技能//百分比数值
	private final PropertyEntityManager addPoint = new PropertyEntityManager();// 加点计算
	private final PropertyEntityManager specialDrug = new PropertyEntityManager();// 特殊药品对整体的加层
	private final PropertyEntityManager bangpai = new PropertyEntityManager();// 帮派
																				// 1
	private final PropertyEntityManager youlong = new PropertyEntityManager();// 游龙之刃
	private final PropertyEntityManager shouhuyoulong = new PropertyEntityManager();// 守护游龙
	private final PropertyEntityManager lianti = new PropertyEntityManager();// 炼体
	private final PropertyEntityManager anqi = new PropertyEntityManager();// 暗器
	private final PropertyEntityManager bow = new PropertyEntityManager();// 弓
	private final PropertyEntityManager dantian = new PropertyEntityManager();// 丹田

	private final PropertyEntityManager xuanyuanbangzhu = new PropertyEntityManager();// 轩辕帮主
	private final PropertyEntityManager xuanyuanbangzhu_kangzhu = new PropertyEntityManager();// 轩辕帮主抗主
	private final PropertyEntityManager xuanyuanbangzhong = new PropertyEntityManager();// 轩辕帮众
	private final PropertyEntityManager dugujiujian = new PropertyEntityManager();// 独孤九剑

	@Override
	protected PropertyEntityManager getPropertyEntityManager(PropertyAdditionType type) {
		switch (type) {
		case buff:
			return buff;
		case chenghao:
			return chenghao;
		case jingmai:
			return jingmai;
		case xinfa:
			return xinfa;
		case zhengfa:
			return zhengfa;
		case zhuangbei:
			return zhuangbei;
		case zuoqi:
			return zuoqi;
		case zuoqiJineng:
			return zuoqiJineng;
		case taoZhuang:
			return taoZhuang;
		case chuzhanzuoqiJineng:
			return chuzhanzuoqiJineng;
		case addpoint:
			return addPoint;
		case specialYJ:
			return specialDrug;
		case bangpai:
			return bangpai;
		case youlong:
			return youlong;
		case shouhuyoulong:
			return shouhuyoulong;
		case lianti:
			return lianti;
		case anqi:
			return anqi;
		case bow:
			return bow;
		case dantian:
			return dantian;
		case xuanyuanbangzhu:
			return xuanyuanbangzhu;
		case xuanyuanbangzhu_kangzhu:
			return xuanyuanbangzhu_kangzhu;
		case xuanyuanbangzhong:
			return xuanyuanbangzhong;
		case dugujiujian:
			return dugujiujian;
		default:
			return null;
		}
	}

	public PropertyEntity getDugujiujian() {
		return dugujiujian;
	}

	public PropertyEntity getJingmai() {
		return jingmai;
	}

	public PropertyEntity getBuff() {
		return buff;
	}

	public PropertyEntity getZhuangbei() {
		return zhuangbei;
	}

	public PropertyEntity getXinfa() {
		return xinfa;
	}

	public PropertyEntity getChenghao() {
		return chenghao;
	}

	public PropertyEntity getZhengfa() {
		return zhengfa;
	}

	public PropertyEntity getZuoqi() {
		return zuoqi;
	}

	public PropertyEntity getZuoqiJineng() {
		return zuoqiJineng;
	}

	public PropertyEntity getTaoZhuang() {
		return taoZhuang;
	}

	public PropertyEntity getChuZhanZuoqiJineng() {
		return chuzhanzuoqiJineng;
	}

	public PropertyEntity getSpecialDrug() {
		return specialDrug;
	}

	public PropertyEntity getBangpai() {
		return bangpai;
	}

	public PropertyEntity getYoulong() {
		return youlong;
	}

	public PropertyEntity getShouhuyoulong() {
		return shouhuyoulong;
	}

	public PropertyEntity getAddPoint() {
		return addPoint;
	}

	public PropertyEntity getLianTi() {
		return lianti;
	}

	public PropertyEntity getAnqi() {
		return anqi;
	}

	public PropertyEntity getBow() {
		return bow;
	}

	public SPropertyEntity getDantian() {
		return dantian;
	}

	public PropertyEntityManager getXuanyuanbangzhu() {
		return xuanyuanbangzhu;
	}

	public PropertyEntityManager getXuanyuanbangzhu_kangzhu() {
		return xuanyuanbangzhu_kangzhu;
	}

	public PropertyEntityManager getXuanyuanbangzhong() {
		return xuanyuanbangzhong;
	}

	@Override
	public void recompute() {
		// clearData();
		// TODO 属性加成
		/*
		 * (人物自身成长带来的生命值+人物加点带来的生命值
		 * +（当前佩戴的装备带来的基础生命值+强化装备带来的生命值+装备附加属性带来的生命值+宝石带来的生命固定值）*（1+套装加成百分比）
		 * +BUFF类药品带来的生命值
		 * +心法带来的生命值+人物冲穴经脉带来的生命值)*(1+阵法带来的百分比+称号带来的百分比)+坐骑本身成长的生命值
		 * +坐骑本身成长的生命值*坐骑技能带来的生命成长的百分比+技能带来的基础值
		 * ----------------------------------
		 * ------------------------------------
		 * ----------------------------------
		 * -----------------------------------------------------------
		 * (人物自身成长带来的生命值+人物加点带来的生命值
		 * +（当前佩戴的装备带来的基础生命值+强化装备带来的生命值+装备附加属性带来的生命值+宝石带来的生命固定值）*（1+套装加成百分比）
		 * +BUFF类药品带来的生命值
		 * +心法带来的生命值+人物冲穴经脉带来的生命值)*(1+阵法带来的百分比+称号带来的百分比)+坐骑本身成长的生命值
		 * +坐骑本身成长的生命值*坐骑技能带来的生命成长的百分比+坐骑技能带来的基础生命值+成就带来的生命值(new)
		 */

		int maxhp = (int) (((vo.getMaxHp() + addPoint.getMaxHp() + lianti.getMaxHp() + (zhuangbei.getMaxHp() + bow.getMaxHp() + dantian.getMaxHp() + dugujiujian.getMaxHp())
				* (1 + taoZhuang.getMaxHp() / Formula.Wan) + buff.getMaxHp() + xinfa.getMaxHp() + jingmai.getMaxHp())
				* (1 + zhengfa.getMaxHp() / Formula.Wan + chenghao.getMaxHp() / Formula.Wan) + zuoqi.getMaxHp()
		// * (1 + zuoqiJineng.getMaxHp() / Formula.Wan));
		+ zuoqiJineng.getMaxHp()) * (1 + (specialDrug.getMaxHp() + bangpai.getMaxHp()) / Formula.Wan));// 这里改变时，同时考虑战斗公式里伤害公式的改变
		/*
		 * 当前内力值上限=
		 * (人物自身成长带来的内力值+人物加点带来的内力值+（当前佩戴的装备带来的基础内力值+强化装备带来的内力值+装备附加属性带来的内力值
		 * +宝石带来的内力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的内力值+心法带来的内力值+人物冲穴经脉带来的内力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的内力值*(1+坐骑技能带来的内力成长的百分比)
		 * ----------------------------
		 * ------------------------------------------
		 * ----------------------------------------------------
		 * (人物自身成长带来的内力值+人物加点带来的内力值+（当前佩戴的装备带来的基础内力值+强化装备带来的内力值+装备附加属性带来的内力值
		 * +宝石带来的内力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的内力值+心法带来的内力值+人物冲穴经脉带来的内力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的内力值+坐骑本身成长的内力值*坐骑技能带来的内力成长的百分比+坐骑技能带来的内力值基础值(new)
		 */
		int maxmp = (int) (((vo.getMaxMp() + addPoint.getMaxMp() + lianti.getMaxMp()
				+ ((zhuangbei.getMaxMp() + bow.getMaxMp() + dantian.getMaxMp() + dugujiujian.getMaxMp()) * (1 + taoZhuang.getMaxMp() / Formula.Wan)) + buff.getMaxMp()
				+ xinfa.getMaxMp() + jingmai.getMaxMp())
				* (1 + zhengfa.getMaxMp() / Formula.Wan + chenghao.getMaxMp() / Formula.Wan) + zuoqi.getMaxMp()
		// * (1 + zuoqiJineng.getMaxMp() / Formula.Wan));
		+ zuoqiJineng.getMaxMp())
				* (1 + specialDrug.getMaxMp() / Formula.Wan) * (1 + taoZhuang.getMaxMp() / Formula.Wan));

		/*
		 * 当前体力值上限=
		 * (人物自身成长带来的体力值+人物加点带来的体力值+（当前佩戴的装备带来的基础体力值+强化装备带来的体力值+装备附加属性带来的体力值
		 * +宝石带来的体力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的体力值+心法带来的体力值+人物冲穴经脉带来的体力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的体力值*(1+坐骑技能带来的体力成长的百分比)
		 * ----------------------------
		 * ------------------------------------------
		 * --------------------------------------------------
		 * (人物自身成长带来的体力值+人物加点带来的体力值+（当前佩戴的装备带来的基础体力值+强化装备带来的体力值+装备附加属性带来的体力值
		 * +宝石带来的体力固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的体力值+心法带来的体力值+人物冲穴经脉带来的体力值)*(1+阵法带来的百分比
		 * +称号带来的百分比)+坐骑本身成长的体力值+坐骑本身成长的体力值*坐骑技能带来的体力成长的百分比+坐骑技能带来的体力基础值(new)
		 */
		int maxsp = (int) ((vo.getMaxSp() + addPoint.getMaxSp() + lianti.getMaxSp()
				+ ((zhuangbei.getMaxSp() + bow.getMaxSp() + dantian.getMaxSp() + dugujiujian.getMaxSp()) * (1 + taoZhuang.getMaxSp() / Formula.Wan)) + buff.getMaxSp()
				+ xinfa.getMaxSp() + jingmai.getMaxSp())
				* (1 + zhengfa.getMaxSp() / Formula.Wan + chenghao.getMaxSp() / Formula.Wan) + zuoqi.getMaxSp()
				// * (1 + zuoqiJineng.getMaxSp() / Formula.Wan));
				+ zuoqiJineng.getMaxSp() + specialDrug.getMaxSp());

		/*
		 * 人物属性面板显示的攻击值=
		 * (人物自身成长带来的攻击值+人物加点带来的攻击值+（当前佩戴的装备带来的基础攻击值+强化装备带来的攻击值+装备附加属性带来的攻击值
		 * +宝石带来的攻击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的攻击值+心法带来的攻击值+人物冲穴经脉带来的攻击值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的攻击值*(1+坐骑技能带来的攻击成长的百分比)
		 * ------------
		 * ----------------------------------------------------------
		 * ------------
		 * -------------------------------------------------------------
		 * (人物自身成长带来的攻击值+人物加点带来的攻击值+（当前佩戴的装备带来的基础攻击值+强化装备带来的攻击值+装备附加属性带来的攻击值
		 * +宝石带来的攻击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的攻击值+心法带来的攻击值+人物冲穴经脉带来的攻击值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的攻击值+坐骑本身成长的攻击值*坐骑技能带来的攻击成长的百分比+坐骑技能带来的基础攻击值
		 * (new)
		 */
		int atk = (int) (((vo.getAttack() + addPoint.getAttack() + lianti.getAttack()
				+ ((zhuangbei.getAttack() + bow.getAttack() + dantian.getAttack() + anqi.getAttack() + dugujiujian.getAttack()) * (1 + taoZhuang.getAttack() / Formula.Wan))
				+ buff.getAttack() + xinfa.getAttack() + jingmai.getAttack())
				* (1 + zhengfa.getAttack() / Formula.Wan + chenghao.getAttack() / Formula.Wan + chuzhanzuoqiJineng.getAttack() / Formula.Wan) + zuoqi.getAttack()
		// * (1 + zuoqiJineng.getAttack() / Formula.Wan));
		+ zuoqiJineng.getAttack()) * (1 + (specialDrug.getAttack() + bangpai.getAttack()) / Formula.Wan))
				+ youlong.getAttack() + xuanyuanbangzhu.getAttack() + xuanyuanbangzhong.getAttack();

		/*
		 * 人物属性面板显示的防御值=
		 * (人物自身成长带来的防御值+人物加点带来的防御值+（当前佩戴的装备带来的基础防御值+强化装备带来的防御值+装备附加属性带来的防御值
		 * +宝石带来的防御固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的防御值+心法带来的防御值+人物冲穴经脉带来的防御值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的防御值*(1+坐骑技能带来的防御成长的百分比)
		 * ------------
		 * ----------------------------------------------------------
		 * ------------
		 * ---------------------------------------------------------------
		 * (人物自身成长带来的防御值+人物加点带来的防御值+（当前佩戴的装备带来的基础防御值+强化装备带来的防御值+装备附加属性带来的防御值
		 * +宝石带来的防御固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的防御值+心法带来的防御值+人物冲穴经脉带来的防御值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的防御值+坐骑本身成长的防御值*坐骑技能带来的防御成长的百分比+坐骑技能带来的防御基础值
		 * (new)
		 */
		int def = (int) (((vo.getDefence() + addPoint.getDefend() + lianti.getDefend()
				+ ((zhuangbei.getDefend() + bow.getDefend() + dantian.getDefend() + dugujiujian.getDefend()) * (1 + taoZhuang.getDefend() / Formula.Wan)) + buff.getDefend()
				+ xinfa.getDefend() + jingmai.getDefend())
				* (1 + zhengfa.getDefend() / Formula.Wan + chenghao.getDefend() / Formula.Wan + chuzhanzuoqiJineng.getDefend() / Formula.Wan) + zuoqi.getDefend()
		// * (1 + zuoqiJineng.getDefend() / Formula.Wan));
		+ zuoqiJineng.getDefend()) * (1 + (specialDrug.getDefend() + bangpai.getDefend()) / Formula.Wan))
				+ youlong.getDefend() + xuanyuanbangzhu.getDefend() + xuanyuanbangzhong.getDefend();

		def = (int) (def + def * ((getShouhuyoulong().getDefend() + xuanyuanbangzhu_kangzhu.getDefend()) / Formula.Wan));

		if (vo.getEffectController().isNodefence()) {
			def = 0;
		}

		/*
		 * ID人物属性面板显示的暴击值=
		 * (人物自身成长带来的暴击值+人物加点带来的暴击值+（当前佩戴的装备带来的基础暴击值+强化装备带来的暴击值+装备附加属性带来的暴击值
		 * +宝石带来的暴击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的暴击值+心法带来的暴击值+人物冲穴经脉带来的暴击值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的暴击值*(1+坐骑技能带来的暴击成长的百分比)
		 * 
		 * ----------------------------------------------------------------------
		 * --
		 * --------------------------------------------------------------------
		 * -- (人物自身成长带来的暴击值+人物加点带来的暴击值+（当前佩戴的装备带来的基础暴击值+强化装备带来的暴击值+装备附加属性带来的暴击值
		 * +宝石带来的暴击固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的暴击值+心法带来的暴击值+人物冲穴经脉带来的暴击值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的暴击值+坐骑本身成长的暴击值*坐骑技能带来的暴击成长的百分比+坐骑技能带来的基础暴击值
		 * (new)
		 */
		int crt = (int) (((vo.getCrt() + addPoint.getCrt() + lianti.getCrt()
				+ ((zhuangbei.getCrt() + bow.getCrt() + dantian.getCrt() + dugujiujian.getCrt()) * (1 + taoZhuang.getCrt() / Formula.Wan)) + buff.getCrt() + xinfa.getCrt() + jingmai
					.getCrt()) * (1 + zhengfa.getCrt() / Formula.Wan + chenghao.getCrt() / Formula.Wan + chuzhanzuoqiJineng.getCrt() / Formula.Wan) + zuoqi.getCrt()
		// * (1 + zuoqiJineng.getCrt() / Formula.Wan));
		+ zuoqiJineng.getCrt()) * (1 + specialDrug.getCrt() / Formula.Wan));

		if (vo.getEffectController().isReduceCrtBuff()) {
			crt = (int) (crt * (1 - AppEventCtlor.getInstance().getEvtSkillFormula().getReduceCrtPrecent_QiShaZhang() / Formula.Wan));
		}

		crt = crt + youlong.getCrt() + xuanyuanbangzhu.getCrt() + xuanyuanbangzhong.getCrt();

		/*
		 * 人物属性面板显示的闪避值=
		 * (人物自身成长带来的闪避值+人物加点带来的闪避值+（当前佩戴的装备带来的基础闪避值+强化装备带来的闪避值+装备附加属性带来的闪避值
		 * +宝石带来的闪避固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的闪避值+心法带来的闪避值+人物冲穴经脉带来的闪避值)*(1+阵法带来的百分比
		 * +称号带来的百分比+出战坐骑技能带来的百分比BUFF)+坐骑本身成长的闪避值*(1+坐骑技能带来的闪避成长的百分比)
		 * ------------
		 * ----------------------------------------------------------
		 * ------------
		 * -------------------------------------------------------------
		 * (人物自身成长带来的闪避值+人物加点带来的闪避值+（当前佩戴的装备带来的基础闪避值+强化装备带来的闪避值+装备附加属性带来的闪避值
		 * +宝石带来的闪避固定值
		 * ）*（1+套装加成百分比）+BUFF类药品带来的闪避值+心法带来的闪避值+人物冲穴经脉带来的闪避值)*(1+阵法带来的百分比
		 * +称号带来的百分比
		 * +出战坐骑技能带来的百分比BUFF)+坐骑本身成长的闪避值+坐骑本身成长的闪避值*坐骑技能带来的闪避成长的百分比+坐骑技能带来的闪避基础值
		 * (new)
		 */
		int dodge = (int) (((vo.getDodge() + addPoint.getDodge() + lianti.getDodge()
				+ ((zhuangbei.getDodge() + bow.getDodge() + dantian.getDodge() + dugujiujian.getDodge()) * (1 + taoZhuang.getDodge() / Formula.Wan)) + buff.getDodge()
				+ xinfa.getDodge() + jingmai.getDodge())
				* (1 + zhengfa.getDodge() / Formula.Wan + chenghao.getDodge() / Formula.Wan + chuzhanzuoqiJineng.getDodge() / Formula.Wan) + zuoqi.getDodge()
		// * (1 + zuoqiJineng.getDodge() / Formula.Wan));
		+ zuoqiJineng.getDodge()) * (1 + specialDrug.getDodge() / Formula.Wan));

		if (vo.getEffectController().isReduceDodgeBuff()) {
			dodge = (int) (dodge * (1 - AppEventCtlor.getInstance().getEvtSkillFormula().getReduceDodgePrecent_QianHuanZhang() / Formula.Wan));
		}

		dodge = dodge + youlong.getDodge() + xuanyuanbangzhu.getDodge() + xuanyuanbangzhong.getDodge();

		/*
		 * 攻击速度值=(人物初始攻击速度+人物升级成长带来的攻击速度+人物冲穴带来的攻击速度+装备带来的攻击速度+BUFF类药品带来的攻击速度+
		 * 技能BUFF带来的攻击速度
		 * +人物坐骑带来的攻击速度+宝石带来的攻击速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF)
		 * 
		 * ----------------------------------------------------------------------
		 * -----------------------------------------------------
		 * (人物初始攻击速度+人物升级成长带来的攻击速度+人物冲穴带来的攻击速度+装备带来的攻击速度+BUFF类药品带来的攻击速度+
		 * 技能BUFF带来的攻击速度
		 * +人物坐骑带来的攻击速度+宝石带来的攻击速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF) +
		 * 坐骑本身成长的攻击速度值*坐骑技能带来的攻击速度成长的百分比+坐骑技能带来的攻击速度基础值(new)
		 */
		int atkspeed = (int) (((vo.getAtkColdtime() + addPoint.getAttackSpeed() + lianti.getAttackSpeed() + jingmai.getAttackSpeed()
				+ (zhuangbei.getAttackSpeed() + bow.getAttackSpeed() + dantian.getAttackSpeed() + dugujiujian.getAttackSpeed()) * (1 + taoZhuang.getAttackSpeed() / Formula.Wan)
				+ buff.getAttackSpeed() + zuoqi.getAttackSpeed())
				* (1 + zhengfa.getAttackSpeed() / Formula.Wan + chuzhanzuoqiJineng.getAttackSpeed() / Formula.Wan) + zuoqiJineng.getAttackSpeed()) * (1 + taoZhuang
				.getAttackSpeed() / Formula.Wan));

		/*
		 * ID人物属性面板显示的移动速度值=
		 * (人物初始移动速度+人物升级成长带来的移动速度+人物冲穴带来的移动速度+装备带来的移动速度+BUFF类药品带来的移动速度
		 * +技能BUFF带来的移动速度
		 * +人物坐骑带来的移动速度+宝石带来的移动速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF)
		 * ----------
		 * ------------------------------------------------------------
		 * ------------------------------------------------------
		 * (人物初始移动速度+人物升级成长带来的移动速度+人物冲穴带来的移动速度+装备带来的移动速度+BUFF类药品带来的移动速度
		 * +技能BUFF带来的移动速度
		 * +人物坐骑带来的移动速度+宝石带来的移动速度固定值)*(1+阵法带来的攻击速度百分比+出战坐骑技能带来的百分比BUFF)
		 * +坐骑本身成长的移动速度值*坐骑技能带来的移动速度成长的百分比+坐骑技能带来的移动速度基础值(new)
		 */
		int movespeed = (int) ((vo.getMoveSpeed() + addPoint.getMoveSpeed() + lianti.getMoveSpeed() + jingmai.getMoveSpeed()
				+ (zhuangbei.getMoveSpeed() + bow.getMoveSpeed() + dantian.getMoveSpeed() + dugujiujian.getMoveSpeed()) * (1 + taoZhuang.getMoveSpeed() / Formula.Wan)
				+ buff.getMoveSpeed() + zuoqi.getMoveSpeed()) * (1 + zhengfa.getMoveSpeed() / Formula.Wan + chuzhanzuoqiJineng.getMoveSpeed() / Formula.Wan))
				+ zuoqiJineng.getMoveSpeed();

		int hit = (int) (((vo.getHit() + addPoint.getHit() + lianti.getHit()
				+ ((zhuangbei.getHit() + bow.getHit() + dantian.getHit() + dugujiujian.getHit()) * (1 + taoZhuang.getHit() / Formula.Wan)) + buff.getHit() + xinfa.getHit() + jingmai
					.getHit()) * (1 + zhengfa.getHit() / Formula.Wan + chenghao.getHit() / Formula.Wan + chuzhanzuoqiJineng.getHit() / Formula.Wan) + zuoqi.getHit()
		// * (1 + zuoqiJineng.getHit() / Formula.Wan));
		+ zuoqiJineng.getHit()) * (1 + specialDrug.getHit() / Formula.Wan));

		hit = hit + youlong.getHit() + xuanyuanbangzhu.getHit() + xuanyuanbangzhong.getHit();
		// 开始设置新的值====================================================

		if (atk != totalValue.getAttack()) {
			totalValue.setAttack(atk);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.attack, getExtraAttack()));
			}
		}

		if (atkspeed < 0)
			atkspeed = 0;
		if (atkspeed != totalValue.getAttackSpeed()) {
			if (!vo.getEffectController().isReduceAttackSpeedBuff()) {
				totalValue.setAttackSpeed(atkspeed);
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.attackspeed, atkspeed));
				}
			}
		}
		if (crt != totalValue.getCrt()) {
			totalValue.setCrt(crt);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.crt, crt));
			}
		}

		if (vo.getEffectController().isNodefence()) {
			totalValue.setDefend(0);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.defence, def));
			}
		} else {
			if (def != totalValue.getDefend()) {
				totalValue.setDefend(def);
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.defence, def));
				}
			}
		}

		if (dodge != totalValue.getDodge()) {
			totalValue.setDodge(dodge);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.dodge, dodge));
			}
		}
		if (hit != totalValue.getHit()) {
			totalValue.setHit(hit);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.hit, hit));
			}
		}
		// 下面几个值的改变需要进行通知的
		if (maxhp != totalValue.getMaxHp()) {
			totalValue.setMaxHp(maxhp);
			// if (vo instanceof Character) {
			vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.maxHp, maxhp));
			// }
		}
		if (maxmp != totalValue.getMaxMp()) {
			totalValue.setMaxMp(maxmp);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.maxMp, maxmp));
			}
		}
		if (maxsp != totalValue.getMaxSp()) {
			totalValue.setMaxSp(maxsp);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.maxSp, maxsp));
			}
		}

		if (movespeed < 0)
			movespeed = 0;
		if (movespeed != totalValue.getMoveSpeed()) {
			if (!vo.getEffectController().isReduceMoveSpeedBuff()) {
				totalValue.setMoveSpeed(movespeed);
				// if (vo instanceof Character) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.movespeed, movespeed));
				// }
			}
		}

		if (vo.getNowHp() > totalValue.getMaxHp()) {
			vo.setNowHp(totalValue.getMaxHp());
			// if (vo instanceof Character) {
			vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.hp, vo.getNowHp()));
			// }
		}
		if (vo.getNowMp() > totalValue.getMaxMp()) {
			vo.setNowMp(totalValue.getMaxMp());
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.mp, vo.getNowMp()));
			}
		}
		if (vo.getNowSp() > totalValue.getMaxSp()) {
			vo.setNowSp(totalValue.getMaxSp());
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterOneAttributeMsg49990(vo, EffectType.sp, vo.getNowSp()));
			}
		}

		int gjl = getZhuangbei().getGjl() + getBow().getGjl() + getBuff().getGjl();
		totalValue.setGjl(gjl);
		/**
		 * 反弹伤害
		 */
		int ftsh = getZhuangbei().getFtsh() + getLianTi().getFtsh() + getBuff().getFtsh();
		totalValue.setFtsh(ftsh);
		/**
		 * 忽视防御
		 */
		int hsfy = getZhuangbei().getHsfy() + getBow().getHsfy() + getBuff().getHsfy();
		totalValue.setHsfy(hsfy);
		/**
		 * 伤害减免
		 */
		int shjm = getZhuangbei().getShjm() + getLianTi().getShjm() + getBuff().getShjm();
		totalValue.setShjm(shjm);

		// if (vo instanceof Character) {
		// vo.sendMsg(new CharacterAttributesMsg49992((Character)vo));
		// }
		// if(vo instanceof Character){
		// CharacterFormula.sendCharacterProperties((Character)vo);
		// vo.sendMsg(msg)
		// }

	}

	@Override
	public float getFightAttack() {
		float attAttack = ((vo.getAttack() + getAddPoint().getAttack() + getLianTi().getAttack() + getXinfa().getAttack() + getJingmai().getAttack() + getBuff().getAttack() + (getZhuangbei()
				.getAttack() + getBow().getAttack() + getDantian().getAttack() + getDugujiujian().getAttack() + getAnqi().getAttack())
				* (1 + getTaoZhuang().getAttack() / Formula.Wan))
				* (1 + getChenghao().getAttack() / Formula.Wan + getZhengfa().getAttack() / Formula.Wan + getChuZhanZuoqiJineng().getAttack() / Formula.Wan) + getZuoqi()
				.getAttack() * (1 + getZuoqiJineng().getAttack() / Formula.Wan))
				* (1 + (getSpecialDrug().getAttack() + getBangpai().getAttack()) / Formula.Wan);
		;

		attAttack = attAttack + getYoulong().getAttack() + xuanyuanbangzhu.getAttack() + xuanyuanbangzhong.getAttack();
		return attAttack;
	}

	@Override
	public float getFightDefence() {
		float affDef = ((vo.getDefence() + getAddPoint().getDefend() + getLianTi().getDefend() + getXinfa().getDefend() + getJingmai().getDefend() + getBuff().getDefend() + (getZhuangbei()
				.getDefend() + getBow().getDefend() + getDantian().getDefend() + getDugujiujian().getDefend())
				* (1 + getTaoZhuang().getDefend() / Formula.Wan))
				* (1 + getChenghao().getDefend() / Formula.Wan + getZhengfa().getDefend() / Formula.Wan + getChuZhanZuoqiJineng().getDefend() / Formula.Wan) + getZuoqi()
				.getDefend() * (1 + getZuoqiJineng().getDefend() / Formula.Wan))
				* (1 + (getSpecialDrug().getDefend() + getBangpai().getDefend()) / 10000f);
		affDef = affDef + getYoulong().getDefend() + xuanyuanbangzhu.getDefend() + xuanyuanbangzhong.getDefend();
		affDef = affDef + affDef * ((getShouhuyoulong().getDefend() + xuanyuanbangzhu_kangzhu.getDefend()) / 10000f);
		return affDef;
	}

	@Override
	public void destory() {
		super.destory();
		jingmai.destroy();
		zhuangbei.destroy();
		chenghao.destroy();
		zhengfa.destroy();
		zuoqi.destroy();
		zuoqiJineng.destroy();
		taoZhuang.destroy();
		chuzhanzuoqiJineng.destroy();
		addPoint.destroy();
		specialDrug.destroy();
		bangpai.destroy();
		youlong.destroy();
		shouhuyoulong.destroy();
		lianti.destroy();
		anqi.destroy();
		bow.destroy();
		dantian.destroy();
		xuanyuanbangzhong.destroy();
		xuanyuanbangzhu.destroy();
		xuanyuanbangzhu_kangzhu.destroy();
	}

}
