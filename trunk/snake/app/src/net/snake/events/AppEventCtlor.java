package net.snake.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.snake.ai.formula.DrugFormula;
import net.snake.ai.formula.EquipmentFormula;
import net.snake.ai.formula.FightFormula;
import net.snake.ai.formula.SkillFormula;
import net.snake.ai.formula.TaskFormula;
import net.snake.commons.script.EvtDrugFormula;
import net.snake.commons.script.EvtEquipmentFormula;
import net.snake.commons.script.EvtFightFormula;
import net.snake.commons.script.EvtSkillFormula;
import net.snake.commons.script.EvtTaskFormula;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.script.ScriptApiImpl;

public class AppEventCtlor {
	// public static final int Evt_SceneLoop = 1;
	// public static final int Evt_InstanceLoop = 2;
	// public static final int Evt_MonsterDie = 3;
	// public static final int Evt_MonsterHPChange = 4;
	// public static final int Evt_RoleDie = 5;
	// public static final int Evt_RoleLoop = 6;
	// public static final int Evt_UseGoods = 7;
	// public static final int Evt_InstanceDestory = 8;
	// public static final int Evt_InstanceInit = 9;
	// public static final int Evt_InstanceSceneLoop = 10;
	// public static final int Evt_InstanceSceneInit = 11;
	// public static final int Evt_InstanceSceneEnter = 12;
	// public static final int Evt_InstanceSceneExit = 13;
	// public static final int Evt_InstanceMonsterDie = 14;
	// public static final int Evt_InstanceSceneExChange = 15;
	// public static final int Evt_MonsterGoodDrop = 16;
	// public static final int Evt_WorldUpdate = 17;
	// public static final int Evt_HeroUpGrade = 18;

	private static AppEventCtlor instance = new AppEventCtlor();

	public static AppEventCtlor getInstance() {
		return instance;
	}

	private SApi gameapi = new ScriptApiImpl();

	private volatile ArrayList<IEventListener> evtSceneLoops = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceInits = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceDestorys = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceLoops = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceSceneLoops = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceSceneInit = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceSceneEnter = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceSceneExit = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceMonsterDie = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtInstanceSceneExChange = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtMonsterDies = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtMonsterHPChanges = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtMonsterGoodDrops = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtRoleDies = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtRoleLoops = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtUseGoods = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtWorldUpdate = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtUpGrade = new ArrayList<IEventListener>();
	private volatile ArrayList<IEventListener> evtOutOfTeam = new ArrayList<IEventListener>();

	private volatile EvtFightFormula evtFightFormula;
	private volatile EvtEquipmentFormula evtEquipmentFormula;
	private volatile EvtTaskFormula evtTaskFormula;
	private volatile EvtSkillFormula evtSkillFormula;
	private volatile EvtDrugFormula evtDrugFormula;

	public void publishEvent(int event, Object[] args) {
		List<IEventListener> listeners = null;
		switch (event) {
		case IEventListener.Evt_SceneLoop:
			listeners = evtSceneLoops;
			break;
		case IEventListener.Evt_InstanceLoop:
			listeners = evtInstanceLoops;
			break;
		case IEventListener.Evt_MonsterDie:
			listeners = evtMonsterDies;
			break;
		case IEventListener.Evt_MonsterHPChange:
			listeners = evtMonsterHPChanges;
			break;
		case IEventListener.Evt_RoleDie:
			listeners = evtRoleDies;
			break;
		case IEventListener.Evt_RoleLoop:
			listeners = evtRoleLoops;
			break;
		case IEventListener.Evt_UseGoods:
			listeners = evtUseGoods;
			break;
		case IEventListener.Evt_InstanceDestory:
			listeners = evtInstanceDestorys;
			break;
		case IEventListener.Evt_InstanceInit:
			listeners = evtInstanceInits;
			break;
		case IEventListener.Evt_InstanceSceneLoop:
			listeners = evtInstanceSceneLoops;
			break;
		case IEventListener.Evt_InstanceSceneInit:
			listeners = evtInstanceSceneInit;
			break;
		case IEventListener.Evt_InstanceSceneEnter:
			listeners = evtInstanceSceneEnter;
			break;
		case IEventListener.Evt_InstanceSceneExit:
			listeners = evtInstanceSceneExit;
			break;
		case IEventListener.Evt_InstanceMonsterDie:
			listeners = evtInstanceMonsterDie;
			break;
		case IEventListener.Evt_InstanceSceneExChange:
			listeners = evtInstanceSceneExChange;
			break;
		case IEventListener.Evt_MonsterGoodDrop:
			listeners = evtMonsterGoodDrops;
			break;
		case IEventListener.Evt_WorldUpdate:
			listeners = evtWorldUpdate;
			break;
		case IEventListener.Evt_HeroUpGrade:
			listeners = evtUpGrade;
			break;
		case IEventListener.Evt_OutOfTeam:
			listeners = evtOutOfTeam;
		default:
			break;
		}

		if (listeners == null || listeners.isEmpty()) {
			return;
		}
		for (Iterator<IEventListener> iterator = listeners.iterator(); iterator.hasNext();) {
			IEventListener listener = iterator.next();
			listener.handleEvent(gameapi, args);
		}
	}

	public void registFormula(Object[] formulas) {
		if (formulas == null) {
			evtFightFormula = null;
			evtTaskFormula = null;
			evtEquipmentFormula = null;
			evtSkillFormula = null;
			evtDrugFormula = null;
			return;
		}
		for (int i = 0; i < formulas.length; i++) {
			Object s = formulas[i];
			if (s instanceof EvtFightFormula) {
				evtFightFormula = (EvtFightFormula) s;
			} else if (s instanceof EvtTaskFormula) {
				evtTaskFormula = (EvtTaskFormula) s;
			} else if (s instanceof EvtEquipmentFormula) {
				evtEquipmentFormula = (EvtEquipmentFormula) s;
			} else if (s instanceof EvtSkillFormula) {
				evtSkillFormula = (EvtSkillFormula) s;
			} else if (s instanceof EvtDrugFormula) {
				evtDrugFormula = (EvtDrugFormula) s;
			}
		}

	}

	public EvtDrugFormula getEvtDrugFormula() {
		if (evtDrugFormula == null) {
			evtDrugFormula = new DrugFormula();
		}
		return evtDrugFormula;
	}

	public EvtFightFormula getEvtFightFormula() {
		if (evtFightFormula == null) {
			evtFightFormula = new FightFormula();
		}
		return evtFightFormula;
	}

	public EvtEquipmentFormula getEvtEquipmentFormula() {
		if (evtEquipmentFormula == null) {
			evtEquipmentFormula = new EquipmentFormula();
		}
		return evtEquipmentFormula;
	}

	public EvtTaskFormula getEvtTaskFormula() {
		if (evtTaskFormula == null) {
			evtTaskFormula = new TaskFormula();
		}
		return evtTaskFormula;
	}

	public EvtSkillFormula getEvtSkillFormula() {
		if (evtSkillFormula == null) {
			evtSkillFormula = new SkillFormula();
		}
		return evtSkillFormula;
	}

	public void registEventListener(IEventListener[] listeners) {
		if (listeners == null) {
			evtSceneLoops.clear();
			evtInstanceInits.clear();
			evtInstanceDestorys.clear();
			evtInstanceLoops.clear();
			evtMonsterDies.clear();
			evtMonsterHPChanges.clear();
			evtRoleDies.clear();
			evtRoleLoops.clear();
			evtUseGoods.clear();
			evtWorldUpdate.clear();
			evtInstanceSceneLoops.clear();
			evtInstanceSceneInit.clear();
			evtInstanceSceneEnter.clear();
			evtInstanceSceneExit.clear();
			evtInstanceMonsterDie.clear();
			evtInstanceSceneExChange.clear();
			evtMonsterGoodDrops.clear();
			evtUpGrade.clear();
			evtOutOfTeam.clear();
			evtFightFormula = null;
			evtTaskFormula = null;
			evtEquipmentFormula = null;
			evtSkillFormula = null;
			return;
		}

		for (IEventListener s : listeners) {

			int event = s.getInterestEvent();
			switch (event) {
			case IEventListener.Evt_SceneLoop:
				evtSceneLoops.add(s);
				break;
			case IEventListener.Evt_InstanceLoop:
				evtInstanceLoops.add(s);
				break;
			case IEventListener.Evt_MonsterDie:
				evtMonsterDies.add(s);
				break;
			case IEventListener.Evt_MonsterHPChange:
				evtMonsterHPChanges.add(s);
				break;
			case IEventListener.Evt_RoleDie:
				evtRoleDies.add(s);
				break;
			case IEventListener.Evt_RoleLoop:
				evtRoleLoops.add(s);
				break;
			case IEventListener.Evt_UseGoods:
				evtUseGoods.add(s);
				break;
			case IEventListener.Evt_InstanceDestory:
				evtInstanceDestorys.add(s);
				break;
			case IEventListener.Evt_InstanceInit:
				evtInstanceInits.add(s);
				break;
			case IEventListener.Evt_InstanceSceneLoop:
				evtInstanceSceneLoops.add(s);
				break;
			case IEventListener.Evt_InstanceSceneInit:
				evtInstanceSceneInit.add(s);
				break;
			case IEventListener.Evt_InstanceSceneEnter:
				evtInstanceSceneEnter.add(s);
				break;
			case IEventListener.Evt_InstanceSceneExit:
				evtInstanceSceneExit.add(s);
				break;
			case IEventListener.Evt_InstanceMonsterDie:
				evtInstanceMonsterDie.add(s);
				break;
			case IEventListener.Evt_InstanceSceneExChange:
				evtInstanceSceneExChange.add(s);
				break;
			case IEventListener.Evt_MonsterGoodDrop:
				evtMonsterGoodDrops.add(s);
				break;
			case IEventListener.Evt_WorldUpdate:
				evtWorldUpdate.add(s);
				break;
			case IEventListener.Evt_HeroUpGrade:
				evtUpGrade.add(s);
				break;
			case IEventListener.Evt_OutOfTeam:
				evtOutOfTeam.add(s);
				break;
			default:
				break;
			}
		}
	}

}
