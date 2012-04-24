package net.snake.gamemodel.dujie.logic;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.GenerateProbability;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.consts.EffectType;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.bean.Hufazhen;
import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.persistence.HufazhenDataTbl;
import net.snake.gamemodel.dujie.persistence.TianshenDataTbl;
import net.snake.gamemodel.dujie.response.DujieSucResp;
import net.snake.gamemodel.dujie.response.FightingHufaUpdateResp;
import net.snake.gamemodel.dujie.response.FightingHufazhenResp;
import net.snake.gamemodel.dujie.response.HufaCurrencyResp;
import net.snake.gamemodel.dujie.response.HufaPageListResp;
import net.snake.gamemodel.dujie.response.StartCountdownResp;
import net.snake.gamemodel.dujie.response.SystemAttack;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.logic.InstancePlugin;
import net.snake.gamemodel.map.logic.Scene;

public class DujiePlugin implements InstancePlugin {

	private long preTimestamp = 0;
	private long interval = 400;

	private long beginTimestamp = 0;
	private static long readyTime = 300 * 1000;

	// private boolean sucComp = false;

	private static short[] disx = { -3, -3, -3, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -2, -2, -2, -1, 0, 1,
			2, 2, 2, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 3, 3, 3 };
	private static short[] disy = { -3, -3, -3, -11, -10, -9, -8, -7, -6, -5, -2, -2, -2, -1, 0, 1, 2, 2, 2, 5, 6, 7, 8, 9, 10, 11, 3, 3, 3 };
	private static Random rdm = new Random();

	private int hufazhenId = -1;
	private GuardImg[] guardImgs = new GuardImg[4];
	private long complTimestamp = 0;

	public DujiePlugin() {
		for (int i = 0; i < guardImgs.length; i++) {
			guardImgs[i] = new GuardImg();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void flush(long now, InstanceController instance) {
		if (instance.getAttribute("sucComp") != null) {
			if (complTimestamp == 0) {
				complTimestamp = System.currentTimeMillis()+60000;
			} 
			else {
//				long diff = System.currentTimeMillis() - complTimestamp;
				if (System.currentTimeMillis() >= complTimestamp) {
					Collection<Hero> heros = instance.getInstanceAllCharacters();
					for (Iterator<Hero> iterator = heros.iterator(); iterator.hasNext();) {
						Hero hero = iterator.next();
						hero.getMyCharacterInstanceManager().exitInstance2World(hero.worldPos[0], hero.worldPos[1], hero.worldPos[2]);
					}
				}
			}

			return;
		}

		if (beginTimestamp == 0) {// 副本开始的时间
			beginTimestamp = now;
			init(instance);
		}

		Object flushed = instance.getAttribute("flushed");

		if (flushed != null) {// 刷出了怪物
			long diff = now - preTimestamp;
			if (diff >= interval) {// 一秒劈一次
				boolean compl = checkMonsterTag(instance);
				if (compl) {
					return;
				}
				preTimestamp = now;
				skyFire(instance);
			}
			return;
		}

		long diff = now - beginTimestamp;
		if (diff < readyTime) {// 没有过准备时间
			return;
		}

		for (int i = 0; i < guardImgs.length; i++) {
			guardImgs[i].id = -1;
		}
		hufazhenId = -1;
		FightingHufazhenResp resp = new FightingHufazhenResp();
		try {
			resp.setData(guardImgs, hufazhenId);
			Collection<Hero> hero = instance.getInstanceAllCharacters();
			if (!hero.isEmpty()) {
				Hero character = hero.iterator().next();
				character.sendMsg(resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		flushMonster(instance);
		instance.setAttribute("flushed", Boolean.TRUE);
	}

	@Override
	public InstancePlugin newAnother() {
		return new DujiePlugin();
	}

	@SuppressWarnings("unchecked")
	private void init(InstanceController instance) {
		Collection<SRole> roles = instance.getInstanceAllCharacters();
		StartCountdownResp countdownResp = new StartCountdownResp(300000,1);
		HufaCurrencyResp currencyResp = new HufaCurrencyResp();
		for (SRole role : roles) {
			Hero character = (Hero) role;
			character.sendMsg(countdownResp);
			character.sendMsg(currencyResp);

			CharacterPropertyManager.changeNowSp(character, 0 - character.getNowSp());
		}
		
		List<Tianshen> ts = TianshenDataTbl.getInstance().getPagedTianshens(1);
		HufaPageListResp resp = new HufaPageListResp(1);
		resp.setTianshens(GuardImg.Guards_Super, ts);
		
		for (SRole role : roles) {
			Hero character = (Hero) role;
			character.sendMsg(resp);
		}
		for (SRole role : roles) {
			Hero character = (Hero) role;
			HeroDujieData data =character.getDujieCtrlor().getHeroDujieData();
			data.setProcess(data.getProcess()+1);
		}
	}

	@SuppressWarnings("unchecked")
	private void skyFire(InstanceController instance) {
		Collection<SRole> roles = instance.getInstanceAllCharacters();
		for (SRole role : roles) {
			Hero hero = (Hero) role;

			int modelID = hero.getDujieCtrlor().currentTianjieNum();
			Dujie dujie = DujieDataTbl.getInstance().getDujie(modelID);
			List<int[]> allHurts = dujie.getAllHurtData();
			for (int i = 0; i < 4; i++) {
				attack(role, allHurts, instance);
			}
		}

	}

	public void flushMonster(InstanceController instance) {
		@SuppressWarnings("unchecked")
		Collection<Hero> hero = instance.getInstanceAllCharacters();
		if (hero.isEmpty()) {
			return;
		}
		Hero character = hero.iterator().next();
		Scene scenet = character.getSceneRef();
		
		instance.flushMonster(scenet, 10000, 10000);
		Collection<SMonster> monsters=scenet.getAllCurrentSceneMonster();
		for (Iterator<SMonster> iterator = monsters.iterator();iterator.hasNext();) {
			SMonster monster=iterator.next();
			if (monster.getType()==3||monster.getType() ==10) {
				instance.setAttribute("bossModel", Integer.valueOf(monster.getModel()));
				break;
			}
		}
	}

	@Override
	public void onCompleted(InstanceController instance, Hero character) {
		int jie=character.getDujieCtrlor().currentTianjieNum();
		int need = DujieDataTbl.getInstance().getDujie(jie).getZhenyuan();
		DujieSucResp sucResp = new DujieSucResp(1, need);
		character.sendMsg(sucResp);

	}

	public boolean addGuard(int type, Tianshen shen) {
		for (int i = 0; i < guardImgs.length; i++) {
			if (guardImgs[i].id == -1) {
				guardImgs[i].type = type;
				guardImgs[i].id = shen.getId();
				guardImgs[i].name = shen.getName();
				guardImgs[i].gs = shen.getGs();
				guardImgs[i].fee = shen.getFee();
				guardImgs[i].mxZhenyuan = shen.getZhenyuan();
				guardImgs[i].currZhenyuan = shen.getZhenyuan();
				return true;
			}
		}
		return false;
	}

	public boolean addGuard(int type, GuardImg guard, int fee) {
		for (int i = 0; i < guardImgs.length; i++) {
			if (guardImgs[i].id == -1) {
				guardImgs[i].type = type;
				guardImgs[i].id = guard.id;
				guardImgs[i].name = guard.name;
				guardImgs[i].gs = guard.gs;
				guardImgs[i].fee = fee;
				guardImgs[i].mxZhenyuan = guard.mxZhenyuan;
				guardImgs[i].currZhenyuan = guard.mxZhenyuan;
				guardImgs[i].headImg = guard.headImg;
				return true;
			}
		}
		return false;
	}

	private List<GuardImg> normalGuards = null;

	public void normalGuards(List<GuardImg> guards) {
		normalGuards = guards;
	}

	public List<GuardImg> normalGuards() {
		return normalGuards;
	}

	public boolean delGuard(int type, int guardId) {
		for (int i = 0; i < guardImgs.length; i++) {
			if (guardImgs[i].id == guardId && guardImgs[i].type == type) {
				guardImgs[i].id = -1;
				return true;
			}
		}
		return false;
	}

	public GuardImg[] getAllGuardImgs() {
		return guardImgs;
	}

	public Hufazhen getHufazhen() {
		if (hufazhenId == -1) {
			return null;
		}

		return HufazhenDataTbl.getInstance().getHufaData(hufazhenId);
	}

	public void setHufazhen(int id) {
		this.hufazhenId = id;
	}

	private boolean checkMonsterTag(InstanceController instanceController) {
		Object complObject = instanceController.getAttribute("dujieComplete");
		if (complObject == null) {
			return false;
		}

		@SuppressWarnings("unchecked")
		Collection<SRole> roles = instanceController.getInstanceAllCharacters();
		Hero hero = (Hero) roles.iterator().next();
		int dujieLvl = hero.getDujieCtrlor().currentTianjieNum();
		Dujie dujie = DujieDataTbl.getInstance().getDujie(dujieLvl);

		hero.getDujieCtrlor().getHeroDujieData().setProcess(dujie.getZhenyuan());
		this.onCompleted(instanceController, hero);

		instanceController.setAttribute("sucComp", Boolean.TRUE);
		return true;

	}

	private void hurt(int hurtValue, Hero hero, InstanceController instance) {
		// 有没有护法可以为我挡一挡
		int imgs = 0;
		GuardImg[] gurads = getAllGuardImgs();
		for (int i = 0; i < gurads.length; i++) {// 统计还有多少护法
			if (guardImgs[i].id == -1) {
				continue;
			}
			if (guardImgs[i].currZhenyuan == 0) {
				continue;
			}
			imgs++;
		}

		if (imgs != 0) {
			int hurtp = (int)(hurtValue*0.3) / imgs;
			for (int i = 0; i < gurads.length; i++) {
				if (guardImgs[i].id == -1) {
					continue;
				}
				if (guardImgs[i].currZhenyuan == 0) {
					continue;
				}
				guardImgs[i].currZhenyuan -= hurtp;// 护法受到伤害
				if (guardImgs[i].currZhenyuan <= 0) {
					guardImgs[i].currZhenyuan = 0;
				}
				FightingHufaUpdateResp hufaUpdateResp = new FightingHufaUpdateResp();// 护法伤害消息
				hufaUpdateResp.setData(guardImgs[i].id, guardImgs[i].type, guardImgs[i].currZhenyuan);
				hero.sendMsg(hufaUpdateResp);

				if (guardImgs[i].currZhenyuan != 0) {// 要不要去掉阵法buff
					continue;
				}
				Object buffObject = instance.getAttribute("zhenBuff");
				if (buffObject == null) {
					continue;
				}
				hero.getPropertyAdditionController().removeChangeListener((PropertyChangeListener) buffObject);
				instance.removeAttribute("zhenBuff");
				instance.removeAttribute("shentong");
				FightingHufazhenResp fightingHufazhenResp = new FightingHufazhenResp();
				fightingHufazhenResp.only4Zhenfa(hufazhenId == -1 ? 0 : hufazhenId, 0);
				hufazhenId = -1;
				hero.sendMsg(fightingHufazhenResp);
			}
//			return;
		}

		// 单刀赴会
		// 真元够不够
		int zhenyuan = hero.getZhenqi();
		if (zhenyuan == 0) {// 那就扣血吧
			//hurtValue = hurtValue << 2;
			hurtValue = (int)(hurtValue *0.7);
			int nowHp = hero.getNowHp();
			if (hurtValue > nowHp) {
				hurtValue = nowHp;
			}

			hero.changeNowHp(-hurtValue);// 改变血量并发送消息
			FightMsgSender.directHurtBroadCase(null, hero, 0, 0);
			if (hero.getNowHp() == 0) {
				if (instance.getAttribute("shentong") == null) {
					DujieSucResp sucResp = new DujieSucResp(0, hero.getDujieCtrlor().getHeroDujieData().getProcess());
					hero.sendMsg(sucResp);
					instance.setAttribute("sucComp", Boolean.TRUE);// 不再循环了
				} else {
					if ((Integer) instance.getAttribute("shentong") == 3) {
						hero.die(hero);
						hero.getShenTongSkillManager().freeFuhuo();
						instance.removeAttribute("shentong");
					} else {
						DujieSucResp sucResp = new DujieSucResp(0, hero.getDujieCtrlor().getHeroDujieData().getProcess());
						hero.sendMsg(sucResp);
						instance.setAttribute("sucComp", Boolean.TRUE);// 不再循环了
					}
				}

			}
			return;
		}
//		hurtValue = hurtValue << 2;
		hurtValue = (int)(hurtValue *0.7);
		// 有真元就扣真元吧
		if (hurtValue > zhenyuan) {
			hurtValue = zhenyuan;
		}
		hero.setZhenqi(zhenyuan - hurtValue);
		CharacterOneAttributeMsg49990 oneProp = new CharacterOneAttributeMsg49990(hero, EffectType.zhenqi, zhenyuan - hurtValue);
		hero.sendMsg(oneProp);
	}

	private void attack(SRole role, List<int[]> allHurts, InstanceController instance) {
		int skillIndex = rdm.nextInt(allHurts.size());
		int skill = allHurts.get(skillIndex)[0];

		int xoff = disx[rdm.nextInt(59)];
		int yoff = disy[rdm.nextInt(29)];

		int x = role.getX() + xoff;
		int y = role.getY() + yoff;

		SystemAttack attack = new SystemAttack();
		attack.attackInfo(skill, x, y, role);
		((Hero) role).sendMsg(attack);

		if (Math.abs(xoff) <= 3 && Math.abs(yoff) <= 3) {
			int hurtValue = GenerateProbability.randomIntValue(allHurts.get(skillIndex)[1], allHurts.get(skillIndex)[2]);
			hurt(hurtValue, (Hero) role, instance);
		}

	}

	private List<GuardImg> advanceGuards = null;

	@Override
	public void advanceGuards(List<GuardImg> guards) {
		advanceGuards = guards;
	}

	@Override
	public List<GuardImg> advanceGuards() {
		return advanceGuards;
	}
}