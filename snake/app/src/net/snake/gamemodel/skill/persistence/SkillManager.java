package net.snake.gamemodel.skill.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.EffectType;
import net.snake.consts.Popsinger;
import net.snake.consts.WugongType;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.CharacterSkill;

import org.apache.log4j.Logger;

/**
 * 玩家技能管理
 * 
 * @author jack
 */

public class SkillManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(SkillManager.class);
	private static SkillDao dao = new SkillDao();
	// 单例实现=====================================
	private static SkillManager instance;

	public static int FengyuTongJiSkillId = 6001;
	public static int HeHeShuangXiuJiSkillId = 6002;
	private Skill wudiBuffST;

	private Skill speedUpSkillCoolST;

	private Skill freeFuhuoST;

	private SkillManager() {
	}

	public static SkillManager getInstance() {
		if (instance == null) {
			instance = new SkillManager();
		}
		return instance;
	}

	// 单例实现========================================

	private Map<Integer, Skill> cacheSkillMap = new HashMap<Integer, Skill>();
	private Map<Integer, Skill> cacheFuQiSkillMap = new HashMap<Integer, Skill>();
	private Map<Integer, Integer> sumSkillGradeLimit = new HashMap<Integer, Integer>();
	private Map<Integer, Skill> commonskillmap = new HashMap<Integer, Skill>();

	Map<Integer, Skill> skillMapDaoshi = new HashMap<Integer, Skill>();
	Map<Integer, Skill> skillMapZhanshi = new HashMap<Integer, Skill>();
	Map<Integer, Skill> skillMapYueshi = new HashMap<Integer, Skill>();
	Map<Integer, Skill> skillMapFashi = new HashMap<Integer, Skill>();
	List<Skill> horseSkills = new ArrayList<Skill>();
	List<Skill> horseShenjiSkills = new ArrayList<Skill>();

	private Skill fengYuTongJiSkill;
	private Skill shuangXiuHeJiSkill;

	// private Skill commonSkill = null;
	public Skill getSkillById(int skillId) {
		return this.cacheSkillMap.get(skillId);
	}

	public Map<Integer, Skill> getCacheSkillMap() {
		return cacheSkillMap;
	}

	public int getTotalwugongjingjie(int Popsinger) {
		return sumSkillGradeLimit.get(Popsinger);
	}

	protected SkillDao getEntityDao() {
		return dao;
	}

	/**
	 * 根据门派获得创建角色时的技能
	 * 
	 * @param popsinger
	 * @return
	 */
	public Collection<Skill> getCreateLearnSkills(int popsinger) {
		Collection<Skill> skills = null;
		switch (popsinger) {
		case 1:
			skills = skillMapDaoshi.values();
			break;
		case 2:
			skills = skillMapZhanshi.values();
			break;
		case 3:
			skills = skillMapYueshi.values();
			break;
		case 4:
			skills = skillMapFashi.values();
			break;
		default:
			break;
		}
		return skills;
	}

	/**
	 * 获得所有的夫妻技能
	 * 
	 * @return
	 */
	public Collection<Skill> getAllFuQiSkills() {
		return cacheFuQiSkillMap.values();
	}

	public List<Skill> getHorseSkills() {
		return horseSkills;
	}

	public List<Skill> getHorseShenjiSkills() {
		return horseShenjiSkills;
	}

	private Map<Integer, Skill> getSkillMap() {
		return dao.getSkillMap();
	}

	/**
	 * 和合双休
	 * 
	 * @return
	 */
	public Skill getShuangXiuHeJiSkill() {
		return shuangXiuHeJiSkill;
	}

	/**
	 * 风雨同济
	 * 
	 * @return
	 */
	public Skill getFengYuTongJiSkill() {
		return fengYuTongJiSkill;
	}

	private void fillterToMengPai(Map<Integer, Skill> skillmap) {
		clearPopsingerSkill();
		if (skillmap == null) {
			return;
		}

		for (Entry<Integer, Skill> entry : skillmap.entrySet()) {
			Skill skill = entry.getValue();
			if (skill.getSkilltype() == 1) {
				horseSkills.add(skill);
				continue;
			}
			if (skill.getSkilltype() == 4) {
				horseShenjiSkills.add(skill);
				continue;
			}
			if (skill.getPopsinger() == 5) {
				int type = skill.getEffect().getType();
				switch (type) {
				case EffectType.wudi:
					wudiBuffST = skill;
					break;
				case EffectType.attackspeed:
					speedUpSkillCoolST = skill;
					break;
				case EffectType.freeFuHuo:
					freeFuhuoST = skill;
					break;
				}
				continue;
			}
			if (WugongType.getSkillType(skill.getWugongType()) == WugongType.FU_QI) {
				if (skill.getId() == FengyuTongJiSkillId) {
					fengYuTongJiSkill = skill;
				}
				if (skill.getId() == HeHeShuangXiuJiSkillId) {
					shuangXiuHeJiSkill = skill;
				}
				cacheFuQiSkillMap.put(skill.getId(), skill);
				continue;
			}
			if (skill.getSkilltype() != 2&&skill.getSkilltype() != 5) {
				continue;
			}
			String createLearn = skill.getCreateLearn();
			if (createLearn != null && !createLearn.isEmpty()) {
				createLearn = createLearn.trim();
			}
			if (skill.getPopsinger() == 0 || skill.getPopsinger() == Popsinger.guming.getPopsinger()) {
				if (createLearn.equals("1"))
					skillMapDaoshi.put(skill.getId(), skill);
				if (skill.isGeneral()) {
					commonskillmap.put(Popsinger.guming.getPopsinger(), skill);
				}
				dealGradeLimit(Popsinger.guming.getPopsinger(), skill);
				if (skill.getSkilltype() == 5) {
					skillMapDaoshi.put(skill.getId(), skill);
				}
			}

			if (skill.getPopsinger() == 0 || skill.getPopsinger() == Popsinger.futu.getPopsinger()) {
				if (createLearn.equals("1"))
					skillMapZhanshi.put(skill.getId(), skill);
				if (skill.isGeneral()) {
					commonskillmap.put(Popsinger.futu.getPopsinger(), skill);
				}
				dealGradeLimit(Popsinger.futu.getPopsinger(), skill);
				if (skill.getSkilltype() == 5) {
					skillMapZhanshi.put(skill.getId(), skill);
				}
			}
			if (skill.getPopsinger() == 0 || skill.getPopsinger() == Popsinger.yunyue.getPopsinger()) {
				if (createLearn.equals("1"))
					skillMapYueshi.put(skill.getId(), skill);
				if (skill.isGeneral()) {
					commonskillmap.put(Popsinger.yunyue.getPopsinger(), skill);
				}
				dealGradeLimit(Popsinger.yunyue.getPopsinger(), skill);
				if (skill.getSkilltype() == 5) {
					skillMapYueshi.put(skill.getId(), skill);
				}
			}
			if (skill.getPopsinger() == 0 || skill.getPopsinger() == Popsinger.miaoyu.getPopsinger()) {
				if (createLearn.equals("1"))
					skillMapFashi.put(skill.getId(), skill);
				if (skill.isGeneral()) {
					commonskillmap.put(Popsinger.miaoyu.getPopsinger(), skill);
				}
				dealGradeLimit(Popsinger.miaoyu.getPopsinger(), skill);
				if (skill.getSkilltype() == 5) {
					skillMapFashi.put(skill.getId(), skill);
				}
			}

		}

	}

	private void clearPopsingerSkill() {
		skillMapDaoshi.clear();
		skillMapZhanshi.clear();
		skillMapYueshi.clear();
		skillMapFashi.clear();
		horseSkills.clear();
		sumSkillGradeLimit.clear();
	}

	void dealGradeLimit(int popsinger, Skill skill) {
		if (skill.getSkilltype() == 2) {
			Integer integer = sumSkillGradeLimit.get(popsinger);
			if (integer == null) {
				integer = 0;
			}

			if (skill.getWugongTypeConsts() != WugongType.BANG_PAI && skill.getWugongTypeConsts() != WugongType.MENG_PAI
					&& skill.getWugongTypeConsts() != WugongType.JIANG_HU_JUE_XUE) {
				return;
			}

			if (skill.isGeneral())
				return;

			sumSkillGradeLimit.put(popsinger, integer + skill.getGradeLimit());
		}
	}

	@Override
	public void reload() {
		try {
			BeanTool.addOrUpdate(cacheSkillMap, getSkillMap(), "id");
			fillterToMengPai(cacheSkillMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "skill";
	}

	public Skill getCommonSkill(int popsinger) {
		return commonskillmap.get(popsinger);
	}

	public List<Skill> getCanLearnSkills(Hero character) {
		List<Skill> skills = new ArrayList<Skill>();
		int popsinger = character.getPopsinger();
		int grade = character.getGrade();
		Iterator<Skill> itskills = this.cacheSkillMap.values().iterator();
		while (itskills.hasNext()) {
			Skill skill = itskills.next();
			if (skill.getSkilltype() != 2) {
				continue;
			}
			int skillpo = skill.getCharLevel();
			if ((skill.getPopsinger() == popsinger || skill.getPopsinger() == 0) && skillpo <= grade) {
				CharacterSkill cs = character.getCharacterSkillManager().getCharacterSkillById(skill.getId());
				if (cs == null) {
					skills.add(skill);
				}
			}
		}
		return skills;
	}

	public Skill getWudiBuffST() {
		return wudiBuffST;
	}

	public Skill getSpeedUpSkillCoolST() {
		return speedUpSkillCoolST;
	}

	public Skill getFreeFuhuoST() {
		return freeFuhuoST;
	}

}
