package net.snake.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.snake.common.Constants;
import net.snake.common.StringUtil;
import net.snake.dao.achieve.Achieve;
import net.snake.dao.activity.Activity;
import net.snake.dao.avatar.Avatar;
import net.snake.dao.avatar.AvatarExample;
import net.snake.dao.avatar.AvatarProvider;
import net.snake.dao.channel.Channel;
import net.snake.dao.channel.channelrealdragon.ChannelRealdragon;
import net.snake.dao.dgwd.Dgwd;
import net.snake.dao.dujie.Dujie;
import net.snake.dao.dujie.DujieAdd;
import net.snake.dao.dujie.Hufa;
import net.snake.dao.effect.Effect;
import net.snake.dao.effect.EffectExample;
import net.snake.dao.equipmentplayconfig.EquipmentPlayconfig;
import net.snake.dao.goodmodelv2.GoodModelDataProvider;
import net.snake.dao.goodmodelv2.GoodModelV2;
import net.snake.dao.goodsdc.Goodsdc;
import net.snake.dao.hiddenweapons.HiddenWeapons;
import net.snake.dao.horsemodel.HorseModel;
import net.snake.dao.horsemodel.HorseModelProvider;
import net.snake.dao.instance.Instance;
import net.snake.dao.language.Language;
import net.snake.dao.languageflash.LanguageFlash;
import net.snake.dao.lianti.Lianti;
import net.snake.dao.map.Map;
import net.snake.dao.map.MapDataProvider;
import net.snake.dao.map2.Map2;
import net.snake.dao.map2.Map2DataProvider;
import net.snake.dao.monstermodel.MonsterModel;
import net.snake.dao.monstermodel.MonsterModelDataProvider;
import net.snake.dao.npc.NPC;
import net.snake.dao.npc.NPCDataProvider;
import net.snake.dao.publicjava.PublicJavaProvider;
import net.snake.dao.resicon.ResIconProvider;
import net.snake.dao.resicon.Resicon;
import net.snake.dao.sceneMonster.SceneMonster;
import net.snake.dao.sceneMonster.SceneMonsterProvider;
import net.snake.dao.shopshengwang.ShopShengWang;
import net.snake.dao.skill.Skill;
import net.snake.dao.skill.SkillProvider;
import net.snake.dao.skilleffect.SkillEffect;
import net.snake.dao.skilleffect.SkillEffectProvider;
import net.snake.dao.skillupgradeexp.SkillupgradeExp;
import net.snake.dao.sound.Sound;
import net.snake.dao.task.Task;
import net.snake.dao.task.TaskDataProvider;
import net.snake.dao.taskdialog.Taskdialog;
import net.snake.dao.taskdialog.TaskdialogDataProvider;
import net.snake.dao.totem.Totem;
import net.snake.dao.transport2.Transpert2DataProvider;
import net.snake.dao.transport2.Transport2;
import net.snake.util.IOTool;
import net.snake.util.NoteOperate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author serv_dev
 * 
 */
public class ShengChengVm {
	private static final Logger logger = Logger.getLogger(ShengChengVm.class);
	private HorseModelProvider horseModelProvider;
	private GoodModelDataProvider goodModelDataProvider;
	private MonsterModelDataProvider monsterModelDataProvider;
	private SkillProvider skillProvider;
	private SkillEffectProvider skillEffectProvider;
	private AvatarProvider avatarProvider;
	private NPCDataProvider npcDataProvider;
	private TaskDataProvider taskDataProvider;
	private MapDataProvider mapDataProvider;
	private Transpert2DataProvider transpert2DataProvider;
	private Map2DataProvider map2DataProvider;
	private TaskdialogDataProvider taskdialogDataProvider;
	private ResIconProvider resIconProvider;
	private SceneMonsterProvider sceneMonsterProvider;
	private PublicJavaProvider publicJavaProvider;

	private Format format = Format.getPrettyFormat();

	public PublicJavaProvider getPublicJavaProvider() {
		return publicJavaProvider;
	}

	public void setPublicJavaProvider(PublicJavaProvider publicJavaProvider) {
		this.publicJavaProvider = publicJavaProvider;
	}

	public SceneMonsterProvider getSceneMonsterProvider() {
		return sceneMonsterProvider;
	}

	public void setSceneMonsterProvider(SceneMonsterProvider sceneMonsterProvider) {
		this.sceneMonsterProvider = sceneMonsterProvider;
	}

	public ResIconProvider getResIconProvider() {
		return resIconProvider;
	}

	public void setResIconProvider(ResIconProvider resIconProvider) {
		this.resIconProvider = resIconProvider;
	}

	public TaskdialogDataProvider getTaskdialogDataProvider() {
		return taskdialogDataProvider;
	}

	public void setTaskdialogDataProvider(TaskdialogDataProvider taskdialogDataProvider) {
		this.taskdialogDataProvider = taskdialogDataProvider;
	}

	public Map2DataProvider getMap2DataProvider() {
		return map2DataProvider;
	}

	public void setMap2DataProvider(Map2DataProvider map2DataProvider) {
		this.map2DataProvider = map2DataProvider;
	}

	public MapDataProvider getMapDataProvider() {
		return mapDataProvider;
	}

	public void setMapDataProvider(MapDataProvider mapDataProvider) {
		this.mapDataProvider = mapDataProvider;
	}

	public Transpert2DataProvider getTranspert2DataProvider() {
		return transpert2DataProvider;
	}

	public void setTranspert2DataProvider(Transpert2DataProvider transpert2DataProvider) {
		this.transpert2DataProvider = transpert2DataProvider;
	}

	public NPCDataProvider getNpcDataProvider() {
		return npcDataProvider;
	}

	public void setNpcDataProvider(NPCDataProvider npcDataProvider) {
		this.npcDataProvider = npcDataProvider;
	}

	public TaskDataProvider getTaskDataProvider() {
		return taskDataProvider;
	}

	public void setTaskDataProvider(TaskDataProvider taskDataProvider) {
		this.taskDataProvider = taskDataProvider;
	}

	public AvatarProvider getAvatarProvider() {
		return avatarProvider;
	}

	public void setAvatarProvider(AvatarProvider avatarProvider) {
		this.avatarProvider = avatarProvider;
	}

	public SkillEffectProvider getSkillEffectProvider() {
		return skillEffectProvider;
	}

	public void setSkillEffectProvider(SkillEffectProvider skillEffectProvider) {
		this.skillEffectProvider = skillEffectProvider;
	}

	public HorseModelProvider getHorseModelProvider() {System.out.println("ShengChengVm.getHorseModelProvider");
		return horseModelProvider;
	}

	public void setHorseModelProvider(HorseModelProvider horseModelProvider) {System.out.println("ShengChengVm.setHorseModelProvider");
		this.horseModelProvider = horseModelProvider;
	}

	public GoodModelDataProvider getGoodModelDataProvider() {System.out.println("ShengChengVm.getGoodModelDataProvider");
		return goodModelDataProvider;
	}

	public void setGoodModelDataProvider(GoodModelDataProvider goodModelDataProvider) {System.out.println("ShengChengVm.setGoodModelDataProvider");
		this.goodModelDataProvider = goodModelDataProvider;
	}

	public MonsterModelDataProvider getMonsterModelDataProvider() {System.out.println("ShengChengVm.getMonsterModelDataProvider");
		return monsterModelDataProvider;
	}

	public void setMonsterModelDataProvider(MonsterModelDataProvider monsterModelDataProvider) {System.out.println("ShengChengVm.setMonsterModelDataProvider");
		this.monsterModelDataProvider = monsterModelDataProvider;
	}

	public SkillProvider getSkillProvider() {System.out.println("ShengChengVm.getSkillProvider");
		return skillProvider;
	}

	public void setSkillProvider(SkillProvider skillProvider) {System.out.println("ShengChengVm.setSkillProvider");
		this.skillProvider = skillProvider;
	}
	public void startmonstermodel() {System.out.println("ShengChengVm.startmonstermodel");
		List<MonsterModel> listMonsterModel = monsterModelDataProvider.getMonsterModelList();
//		List<MonsterModel> listMonsterModel2 = new ArrayList<MonsterModel>(listMonsterModel.size());
//		for (MonsterModel monsterModel : listMonsterModel) {
//			monsterModel = (MonsterModel) TiHuan(monsterModel);
//			listMonsterModel2.add(monsterModel);
//		}
		VelocityContext context = new VelocityContext();
		context.put("list", listMonsterModel);
		try {
			velocityFileWriter("net/snake/service/template/monstermodel.vm", context, "monstermodel.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startskill() {
		List<Skill> listSkill = skillProvider.getSkillList();
		VelocityContext context = new VelocityContext();
		context.put("list", listSkill);
		try {
			velocityFileWriter("net/snake/service/template/skill.vm", context, "skill.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

		List<SkillupgradeExp> listSkillupgradeExps = skillProvider.getSkillupgradeExp();
		VelocityContext context2 = new VelocityContext();
		context2.put("list", listSkillupgradeExps);
		try {
			velocityFileWriter("net/snake/service/template/skillupgradeexp.vm", context2,
					"skillupgradeexp.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
	public void starticobig() {System.out.println("ShengChengVm.starticobig");
		List<Resicon> listResicons = resIconProvider.getResiconsBig();

		List<Resicon> resiconsnewlist = new ArrayList<Resicon>();
		if (listResicons.size() > 0) {
			for (Resicon resicon : listResicons) {
				StringBuilder stringBuffer = new StringBuilder();
				stringBuffer.append("ico");
				stringBuffer.append(resicon.getId().toString());
				String aString = resicon.getName().substring(resicon.getName().lastIndexOf("."));
				stringBuffer.append(aString);
				resicon.setFilename(stringBuffer.toString());
				resiconsnewlist.add(resicon);
			}
		}
		VelocityContext context = new VelocityContext();
		context.put("list", resiconsnewlist);
		try {
			velocityFileWriter("net/snake/service/template/icotobig.vm", context, "icotobig.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void starticobignew() {System.out.println("ShengChengVm.starticobignew");
	List<Resicon> listResicons = resIconProvider.getResiconsBig();

	List<Resicon> resiconsnewlist = new ArrayList<Resicon>();
	if (listResicons.size() > 0) {
		for (Resicon resicon : listResicons) {
			StringBuilder stringBuffer = new StringBuilder();
			stringBuffer.append("ico");
			stringBuffer.append(resicon.getId().toString());
			String aString = resicon.getName().substring(resicon.getName().lastIndexOf("."));
			stringBuffer.append(aString);
			resicon.setFilename(stringBuffer.toString());
			resiconsnewlist.add(resicon);
		}
	}
	VelocityContext context = new VelocityContext();
	context.put("list", resiconsnewlist);
	try {
		velocityFileWriter("net/snake/service/template/newIconBig.vm", context, "newIconBig.xml");
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
	public void starttaskdialog() {
		List<Taskdialog> listTaskdialogs = taskdialogDataProvider.getTaskdialogList();
		java.util.Map<String, List<Taskdialog>> map = taskdialogDataProvider.getMap();
		Element root = new Element("root");
		Document Doc = new Document(root);
		Element di = new Element("dialogs");
		for (String str : map.keySet()) {

			Element element = new Element("dialog");
			String[] string = str.split("_");
			element.setAttribute("taskId", string[0]);
			element.setAttribute("dialogType", string[1]);
			element.setAttribute("npcId", string[2]);
			for (Taskdialog taskdialog : map.get(str)) {
				Element element2 = new Element("talk");
				element2.setAttribute("talkType", taskdialog.getTalkType().toString());
				if (taskdialog.getTalknpcId() != -1) {
					element2.setAttribute("talkNpc", taskdialog.getTalknpcId().toString());
				}
				element2.setText(taskdialog.getTalkContentI18n());
				element.addContent(element2);
			}

			di.addContent(element);
		}
		root.addContent(di);

		format.setIndent(" ");

		XMLOutputter XMLOut = new XMLOutputter(format);
		try {

			XMLOut.output(Doc, new FileOutputStream("task_dialog.xml"));
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

	}
	public void startLanguageFlash() {
System.out.println("ShengChengVm.startLanguageFlash");
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getLanguageFlash");
		List<LanguageFlash> listFlashs = new ArrayList<LanguageFlash>(list.size());
		for (Object object : list) {
			LanguageFlash languageFlash = (LanguageFlash) object;
			listFlashs.add(languageFlash);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", listFlashs);
		try {
			velocityFileWriter("net/snake/service/template/languageflash.vm", context, "lan_text.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startLianti() {
		System.out.println("ShengChengVm.startLianti");
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getLianTi");
		List<Lianti> liantiList = new ArrayList<Lianti>(list.size());
		for (Object object : list) {
			Lianti lianti = (Lianti) object;
			liantiList.add(lianti);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", liantiList);
		try {
			velocityFileWriter("net/snake/service/template/lianti.vm", context, "lianti.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startDujie() {System.out.println("ShengChengVm.startDujie");
	List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getDujie");
	List<Dujie> liantiList = new ArrayList<Dujie>(list.size()*2);
	for (Object object : list) {
		Dujie lianti = (Dujie) object;
		liantiList.add(lianti);
	}
	VelocityContext context = new VelocityContext();
	context.put("list", liantiList);
	try {
		velocityFileWriter("net/snake/service/template/dujie.vm", context, "dujie.xml");
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
	public void startHufa(){
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.queryhufa");
		List<Hufa> liantiList = new ArrayList<Hufa>(list.size()*2);
		for (Object object : list) {
			Hufa lianti = (Hufa) object;
			liantiList.add(lianti);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", liantiList);
		try {
			velocityFileWriter("net/snake/service/template/hufa.vm", context, "hufa.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startAcivity(){
		try {
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.queryActivities");
		System.out.println("activity  has "+list.size());
		List<Activity> liantiList = new ArrayList<Activity>(list.size()*2);
		for (Object object : list) {
			Activity lianti = (Activity) object;
			liantiList.add(lianti);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", liantiList);
		
			velocityFileWriter("net/snake/service/template/activity.vm", context, "activity.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void startDujieAdd() {
		System.out.println("ShengChengVm.startDujieAdd");
	List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getDujieAdd");
	List<DujieAdd> liantiList = new ArrayList<DujieAdd>(list.size()*2);
	for (Object object : list) {
		DujieAdd lianti = (DujieAdd) object;
		liantiList.add(lianti);
	}
	VelocityContext context = new VelocityContext();
	context.put("list", liantiList);
	try {
		velocityFileWriter("net/snake/service/template/dujieAdd.vm", context, "dujieAdd.xml");
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
	public void startTotem() {
		System.out.println("ShengChengVm.startTotem");
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getTotem");
		List<Totem> totemList = new ArrayList<Totem>(list.size());
		for (Object object : list) {
			Totem totem = (Totem) object;
			totemList.add(totem);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", totemList);
		try {
			velocityFileWriter("net/snake/service/template/totem.vm", context, "totem.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void starttask() {
System.out.println("ShengChengVm.starttask");
		List<Task> taskList = taskDataProvider.getTaskList();
	//	List<Task> taskList2 = new ArrayList<Task>();
		for (Task task : taskList) {
			if (StringUtil.isNotEmpty(task.getTargetmonster())) {
				String type[] = task.getTargetmonster().split(",");
				StringBuffer targetmonsterBuffer = new StringBuffer();
				for (int i = 0; i < type.length; i++) {
					String[] targetMonster = type[i].split("#");
					MonsterModel mmModel = monsterModelDataProvider.getMonsterModelByID(Integer
							.valueOf(targetMonster[0]));
					targetmonsterBuffer.append(targetMonster[0]);
					targetmonsterBuffer.append("#");
					targetmonsterBuffer.append(targetMonster[1]);
					targetmonsterBuffer.append("#");
					if (null != mmModel) {
						String name[] = mmModel.getNameI18n().split("_");
						if (name.length > 0) {
							targetmonsterBuffer.append(TiHuan2(name[0]));
						} else {
							targetmonsterBuffer.append(TiHuan2(mmModel.getNameI18n()));
						}

					} else {
						targetmonsterBuffer.append(" ");
					}
					targetmonsterBuffer.append("#");
					targetmonsterBuffer.append(targetMonster[2]);
					targetmonsterBuffer.append("#");
					if (targetMonster.length==3) {
						targetmonsterBuffer.append("0");
					}else {
						targetmonsterBuffer.append(targetMonster[3]);
					}
					
					if (targetMonster.length==5) {
						if (!targetMonster[4].equals("undefined")) {
							targetmonsterBuffer.append("#");
							targetmonsterBuffer.append(targetMonster[4]);
						}
					}
					
					if (i >= 0 && i < type.length - 1) {
						targetmonsterBuffer.append(",");
					}
				}
				task.setTargetmonster(targetmonsterBuffer.toString());
				// ----------------------------------------------------------
				// System.out.println("task=="+task.getId());
				// System.out.println("targetmonsterBuffer.toString()=="+targetmonsterBuffer.toString());
			}
			if (StringUtil.isNotEmpty(task.getTargetgoods())) {

				// System.out.println("task="+task.getName());
				// System.out.println("taskid="+task.getId());

				String type[] = task.getTargetgoods().split(",");
				StringBuffer targetgoodsBuffer = new StringBuffer();
				for (int i = 0; i < type.length; i++) {
					String[] targetgoods = type[i].split("#");
					System.out.println("task "+task.getId()+" "+task.getName()+" targetgood "+i+" "+targetgoods[0]);
					GoodModelV2 goodModelV2 = goodModelDataProvider.getGoodModelByID(Integer
							.valueOf(targetgoods[0]));

					targetgoodsBuffer.append(targetgoods[0]);
					targetgoodsBuffer.append("#");
					targetgoodsBuffer.append(targetgoods[1]);
					targetgoodsBuffer.append("#");
					if (null != goodModelV2) {
						String name[] = goodModelV2.getNameI18n().split("_");
						if (name.length > 0) {
							targetgoodsBuffer.append(TiHuan2(name[0]));
						} else {
							targetgoodsBuffer.append(TiHuan2(goodModelV2.getNameI18n()));
						}

					} else {
						targetgoodsBuffer.append(" ");
					}
					targetgoodsBuffer.append("#");
					targetgoodsBuffer.append(targetgoods[2]);
					if (i >= 0 && i < type.length - 1) {
						targetgoodsBuffer.append(",");
					}

				}

				// System.out.println("targetgoodsBuffer.toString()="+targetgoodsBuffer.toString());
				task.setTargetgoods(targetgoodsBuffer.toString());
			}
			if (StringUtil.isNotEmpty(task.getTargethorse())) {
				String type[] = task.getTargethorse().split(",");
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < type.length; i++) {

					String[] targethorse = type[i].split("#");
					HorseModel horseModel = horseModelProvider.getHorseModelById(Integer
							.valueOf(targethorse[0]));

					stringBuffer.append(targethorse[0]);
					stringBuffer.append("#");
					stringBuffer.append(targethorse[1]);
					stringBuffer.append("#");
					if (null != horseModel) {
						String name[] = horseModel.getNameI18n().split("_");
						if (name.length > 0) {
							stringBuffer.append(TiHuan2(name[0]));
						} else {
							stringBuffer.append(TiHuan2(horseModel.getNameI18n()));
						}
					} else {
						stringBuffer.append(" ");
					}
					stringBuffer.append("#");
					stringBuffer.append(targethorse[2]);
					if (i >= 0 && i < type.length - 1) {
						stringBuffer.append(",");
					}
				}
				// System.out.println("stringBuffer.toString()="+stringBuffer.toString());
				task.setTargethorse(stringBuffer.toString());
			}
			// Task task2 = taskDataProvider.getTaskByID(task.getPremisetask());
			// task.setPremisetask(task2.getTaskId());

			if (StringUtil.isNotEmpty(task.getTargetshopping())) {
				String type[] = task.getTargetshopping().split(",");
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < type.length; i++) {

					String[] targetTargetshopping = type[i].split("#");
					GoodModelV2 goodModelV2 = goodModelDataProvider.getGoodModelByID(Integer
							.valueOf(targetTargetshopping[0]));

					stringBuffer.append(targetTargetshopping[0]);
					stringBuffer.append("#");

					stringBuffer.append(targetTargetshopping[1]);
					stringBuffer.append("#");
					if (null != goodModelV2) {
						String name[] = goodModelV2.getNameI18n().split("_");
						if (name.length > 0) {
							stringBuffer.append(TiHuan2(name[0]));
						} else {
							stringBuffer.append(TiHuan2(goodModelV2.getNameI18n()));
						}

					} else {
						stringBuffer.append("");
					}

					if (i >= 0 && i < type.length - 1) {
						stringBuffer.append(",");
					}
				}
				// System.out.println("stringBuffer.toString()="+stringBuffer.toString());
				task.setTargetshopping(stringBuffer.toString());
			}

			// targetEquip="$!{entry.targetequip}"
			if (StringUtil.isNotEmpty(task.getTargetequip())) {
				String type = task.getTargetequip();
				StringBuffer stringBuffer = new StringBuffer();
				String[] targetTargetshopping = type.split("#");
				GoodModelV2 goodModelV2 = goodModelDataProvider.getGoodModelByID(Integer
						.valueOf(targetTargetshopping[0]));
				stringBuffer.append(targetTargetshopping[0]);
				stringBuffer.append("#");
				stringBuffer.append(targetTargetshopping[1]);
				stringBuffer.append("#");
				if (null != goodModelV2) {
					String name[] = goodModelV2.getNameI18n().split("_");
					if (name.length > 0) {
						stringBuffer.append(TiHuan2(name[0]));
					} else {
						stringBuffer.append(TiHuan2(goodModelV2.getNameI18n()));
					}

				} else {
					stringBuffer.append("");
				}
				task.setTargetequip(stringBuffer.toString());
			}
			// targetSkillLv="$!{entry.targetskilllv}"
			if (StringUtil.isNotEmpty(task.getTargetskilllv())) {
				String type = task.getTargetskilllv();
				StringBuffer stringBuffer = new StringBuffer();
				String[] targetTargetshopping = type.split("#");
				Skill skill = skillProvider.getSkillByID(Integer.valueOf(targetTargetshopping[0]));
				stringBuffer.append(targetTargetshopping[0]);
				stringBuffer.append("#");
				stringBuffer.append(targetTargetshopping[1]);
				stringBuffer.append("#");
				if (null != skill) {
					String name[] = skill.getNameI18n().split("_");
					if (name.length > 0) {
						stringBuffer.append(TiHuan2(name[0]));
					} else {
						stringBuffer.append(TiHuan2(skill.getNameI18n()));
					}

				} else {
					stringBuffer.append("");
				}
				task.setTargetskilllv(stringBuffer.toString());
			}
			// targetPoint="$!{entry.targetpoint}"
			if (StringUtil.isNotEmpty(task.getTargetpoint())) {
				String type = task.getTargetpoint();
				StringBuffer stringBuffer = new StringBuffer();
				String[] targetTargetshopping = type.split("#");
				Channel channel = publicJavaProvider.getChannel(Integer.valueOf(targetTargetshopping[0]));
				stringBuffer.append(targetTargetshopping[0]);
				stringBuffer.append("#");
				stringBuffer.append(targetTargetshopping[1]);
				stringBuffer.append("#");
				if (null != channel) {
					String name[] = channel.getNameI18n().split("_");
					if (name.length > 0) {
						stringBuffer.append(TiHuan2(name[0]));
					} else {
						stringBuffer.append(TiHuan2(channel.getNameI18n()));
					}

				} else {
					stringBuffer.append("");
				}
				task.setTargetpoint(stringBuffer.toString());
			}
			// targetChannel="$!{entry.targetChannel}"
			if (StringUtil.isNotEmpty(task.getTargetchannel())) {
				String type = task.getTargetchannel();
				StringBuffer stringBuffer = new StringBuffer();
				String[] targetTargetshopping = type.split("#");
				Channel channel = publicJavaProvider.getChannel(Integer.valueOf(targetTargetshopping[0]));
				stringBuffer.append(targetTargetshopping[0]);
				stringBuffer.append("#");
				stringBuffer.append(targetTargetshopping[1]);
				stringBuffer.append("#");
				if (null != channel) {
					String name[] = channel.getNameI18n().split("_");
					if (name.length > 0) {
						stringBuffer.append(TiHuan2(name[0]));
					} else {
						stringBuffer.append(TiHuan2(channel.getNameI18n()));
					}

				} else {
					stringBuffer.append("");
				}
				task.setTargetchannel(stringBuffer.toString());
			}

			// targetBuff="$!{entry.targetBuff}"
			if (StringUtil.isNotEmpty(task.getTargetbuff())) {
				String type = task.getTargetbuff();
				StringBuffer stringBuffer = new StringBuffer();
				String[] targetTargetshopping = type.split("#");
				SkillEffect skillEffect = skillEffectProvider.getSkillEffectByID(Integer
						.valueOf(targetTargetshopping[0]));
				stringBuffer.append(targetTargetshopping[0]);
				stringBuffer.append("#");
				stringBuffer.append(targetTargetshopping[1]);
				stringBuffer.append("#");
				if (null != skillEffect) {
					String name[] = skillEffect.getNameI18n().split("_");
					if (name.length > 0) {
						stringBuffer.append(TiHuan2(name[0]));
					} else {
						stringBuffer.append(TiHuan2(skillEffect.getNameI18n()));
					}

				} else {
					stringBuffer.append("");
				}
				task.setTargetbuff(stringBuffer.toString());
			}
			//
//			task = (Task) TiHuan(task);
//			taskList2.add(task);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", taskList);
		try {
			velocityFileWriter("net/snake/service/template/task.vm", context, "task.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
	

	public void startnpc() {
System.out.println("ShengChengVm.startnpc");
		List<NPC> npcList = npcDataProvider.getNPCList();
		VelocityContext context = new VelocityContext();
		context.put("list", npcList);
		try {
			velocityFileWriter("net/snake/service/template/npc.vm", context, "npc.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startinstance() {
		System.out.println("ShengChengVm.startinstance");
		List<Instance> instancelist = publicJavaProvider.getInstance();
		VelocityContext context = new VelocityContext();

		context.put("list", instancelist);
		try {
			velocityFileWriter("net/snake/service/template/instance.vm", context, "instance.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startgoodmodel() {
System.out.println("ShengChengVm.startgoodmodel");
		List<GoodModelV2> listGoodModelV2 = goodModelDataProvider.getGoodModelList();
//		List<GoodModelV2> listGoodModelV3 = new ArrayList<GoodModelV2>(listGoodModelV2.size());
//		for (GoodModelV2 goodModelV2 : listGoodModelV3) {
//			goodModelV2 = (GoodModelV2) TiHuan(goodModelV2);
//			listGoodModelV3.add(goodModelV2);
//		}
		VelocityContext context = new VelocityContext();
		context.put("list", listGoodModelV2);
		try {
			velocityFileWriter("net/snake/service/template/goodmodel.vm", context, "goodmodel.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startChengShi(String str) {
		NoteOperate no = new NoteOperate(str);
		try {
			List<String> strList = no.fileLinesContent();
			Element root = new Element("root");

			Document Doc = new Document(root);
			for (String str2 : strList) {
				Element element = new Element("province");
				Element element2 = new Element("city");
				String nameString[] = str2.split(",");
				element.setAttribute("name", nameString[0]);
				String cityString[] = nameString[1].split("、");
				for (int a = 0; a < cityString.length; a++) {
					element.addContent(new Element("city").setAttribute("name", cityString[a]));
				}

				root.addContent(element);
			}

			// <province name="娌冲寳">
			// <city name="閭兏" />
			// <city name="鐭冲搴� />
			// </province>
			// Element element = new Element("dialog");
			// String[] string = str.split("_");
			// element.setAttribute("taskId", string[0]);
			// element.setAttribute("dialogType", string[1]);
			// element.setAttribute("npcId", string[2]);
			// for (Taskdialog taskdialog : map.get(str)) {
			// Element element2 = new Element("talk");
			// element2.setAttribute("talkType", taskdialog.getTalkType().toString());
			// if (taskdialog.getTalknpcId() != -1) {
			// element2.setAttribute("talkNpc", taskdialog.getTalknpcId().toString());
			// }
			// element2.setText(taskdialog.getTalkContent());
			// element.addContent(element2);
			// }
			//
			// di.addContent(element);
			// }
			// root.addContent(di);

			format.setIndent(" ");
			XMLOutputter XMLOut = new XMLOutputter(format);
			XMLOut.output(Doc, new FileOutputStream("chengshi.xml"));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void starthorsemodel() {
		List<HorseModel> listHorseModel = horseModelProvider.getHorseModels();
		VelocityContext context = new VelocityContext();
		context.put("list", listHorseModel);
		try {
			velocityFileWriter("net/snake/service/template/horsemodel.vm", context, "horsemodel.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	public void startmap() {
		List<Map> listMaps = mapDataProvider.getMapList();
		java.util.Map<Integer, Map> getmap = mapDataProvider.getMap();
		Element root = new Element("root");
		Document Doc = new Document(root);
		for (Map map : listMaps) {

			// `f_map_name_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '鍦烘櫙鍚嶅瓙,l鍚屾枃浠跺悕,涓嶅甫鎵╁睍鍥介檯鍖�,
			// `f_monster_desc_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '鍒锋�鎻忚堪鍥介檯鍖�,
			// `f_exercise_desc_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '缁冪骇绛夌骇鎻忚堪鍥介檯鍖�,
			// `f_boss_desc_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '鍒锋柊BOSS 鎻忚堪鍥介檯鍖�,
			// `f_boss_drop_goods_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT 'boss浜у嚭鐗╁搧鎻忚堪鍥介檯鍖�,
			Element element = new Element("scene");
			element.setAttribute("id", map.getMapId().toString());
			element.setAttribute("name", TiHuan2(map.getMapNameI18n() == null ? "" : map.getMapNameI18n()));
			element.setAttribute("exercise_desc", TiHuan2(map.getExerciseDescI18n()== null ? "" :map.getExerciseDescI18n()));
			element.setAttribute("pathName", map.getFileName());
			if (StringUtil.isEmpty(map.getBgsound())) {
				map.setBgsound(0);
			}
			element.setAttribute("bg_sound", map.getBgsound().toString());
			element.setAttribute("permit_pvp", map.getPermitPvp().toString());
			element.setAttribute("permit_jump", map.getPermitJump().toString());
			element.setAttribute("permit_ride", map.getPermitRide().toString());
			element.setAttribute("permit_show", map.getPermitShow().toString());
			if (map.getGongchengType() != 1) {
				element.setAttribute("isFuben", map.getInstanceId() == 0 ? "0" : "1");
			} else {
				element.setAttribute("isFuben", "2");
			}
			if (map.getGongchengType() == 2) {
				element.setAttribute("isFuben", "2");
			}
			if (map.getGongchengType() == 3) {
				element.setAttribute("isFuben", "3");
			}
			element.setAttribute("clear_pk_protect", map.getClearPkProtect() == 0 ? "0" : "1");
			List<Transport2> listTransport2s = transpert2DataProvider.getTransport2List(map.getMapId());
			for (Transport2 transport2 : listTransport2s) {
				// System.out.println("---" + transport2.getTransportId().toString());
				/** 鍔犲叆transOut鑺傜偣 */
				Element element2 = new Element("transOut");
				Element ownerElement = element2;
				ownerElement.setAttribute("id", transport2.getTransportId().toString());
				ownerElement.setAttribute("x", transport2.getX().toString());
				ownerElement.setAttribute("y", transport2.getY().toString());
				ownerElement.setAttribute("targetSceneID", transport2.getTargetSceneId().toString());
				Map map2 = getmap.get(transport2.getTargetSceneId());
				Map map3 = getmap.get(transport2.getSceneId());
				if (null != map2 && null != map3) {
					int map2Id = map2.getInstanceId().intValue();
					if (map2Id == map3.getInstanceId().intValue()) {
						if (map2Id != 0) {
							ownerElement.setAttribute("enable", "0");
						} else {
							ownerElement.setAttribute("enable", "1");
						}
					} else {
						ownerElement.setAttribute("enable", "1");
					}
				}

				element.addContent(ownerElement);
			}

			root.addContent(element);
		}

		format.setIndent(" ");

		XMLOutputter XMLOut = new XMLOutputter(format);
		try {
			XMLOut.output(Doc, new FileOutputStream("trans.xml"));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

	}

	public void startGoodsDc() {
		System.out.println("ShengChengVm.startGoodsDc");
		// 收集物品 (物品id#物品数量#物品名称#地图ID_x_y_距离#怪物名称),*
		List<Object> listGoodsdc = publicJavaProvider.getSelectTable("publicJavaDao.getGooddc");
		List<Goodsdc> list = new ArrayList<Goodsdc>(listGoodsdc.size());
		for (Object object : listGoodsdc) {
			Goodsdc goodsdc = (Goodsdc) object;
		
			if (goodsdc.getTargetgoods() != null) {
				String str[] = goodsdc.getTargetgoods().split(",");
				StringBuffer sb = new StringBuffer();
				for (String string : str) {//84000031#30#玉净瓶#30022_50_167_1#70100117
					//System.err.println("收集 "+goodsdc.getName()+" 需要的道具 "+string+" 奖励 "+goodsdc.getRewarddesc());
					String str2[] = string.split("#");
					sb.append(str2[0]);
					sb.append("#");
					sb.append(str2[1]);
					sb.append("#");
					GoodModelV2 goodModelV2 = goodModelDataProvider.getGoodModelByID(Integer.valueOf(str2[0]));
					if (null == goodModelV2) {
						sb.append("goodmodel——id错误是空");
					} else {
						sb.append(TiHuan2(goodModelV2.getNameI18n()));
					}
					sb.append("#");
					sb.append(str2[3]);
					sb.append("#");
					MonsterModel monsterModel = monsterModelDataProvider.getMonsterModelByID(Integer
							.valueOf(str2[4]));
					if (null == monsterModel) {
						sb.append("monstermodel——id错误是空");
					} else {
						sb.append(TiHuan2(monsterModel.getNameI18n()));
					}
					sb.append(",");
				}
				goodsdc.setTargetgoods(sb.toString());
			}
			list.add(goodsdc);
		}
		VelocityContext context = new VelocityContext();

		context.put("list", list);
		try {
			velocityFileWriter("net/snake/service/template/goods_dc.vm", context, "goodsdc.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startShopShengWang(){
		//getShopShengwang
		List<Object> shopShengWangList = publicJavaProvider.getSelectTable("publicJavaDao.getShopShengWang");
		List<ShopShengWang> list = new ArrayList<ShopShengWang>(shopShengWangList.size());
		for (Object object : shopShengWangList) {
			ShopShengWang shengWang =(ShopShengWang) object;
		//	shengWang = (ShopShengWang) TiHuan(shengWang);
			list.add(shengWang);
		}
		
		VelocityContext context = new VelocityContext();

		context.put("list", list);
		try {
			velocityFileWriter("net/snake/service/template/shop_shengwang.vm", context, "shopshengwang.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	public void startHiddenWeapons() {
		System.out.println("ShengChengVm.startHiddenWeapons");
		System.out.println("startHiddenWeapons");
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getHiddenWeapons");
		List<HiddenWeapons> list2 = new ArrayList<HiddenWeapons>(list.size());
		for (Object object : list) {
			HiddenWeapons hiddenWeapons = (HiddenWeapons) object;
			list2.add(hiddenWeapons);
		}
		VelocityContext context = new VelocityContext();

		context.put("list", list2);
		try {
			velocityFileWriter("net/snake/service/template/hidden_weapons.vm", context, "hiddenweapons.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	public void startJinmai() {System.out.println("ShengChengVm.startJinmai");
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getJingmai");
		List<Object> list2 = publicJavaProvider.getSelectTable("publicJavaDao.getJingmaiZhenlong");
		List<Channel> list3 = new ArrayList<Channel>(list.size());
		List<ChannelRealdragon> list4 = new ArrayList<ChannelRealdragon>(list2.size());
		for (Object object : list) {
			Channel channel = (Channel) object;
			list3.add(channel);
		}
		
		for (Object object : list2) {
			ChannelRealdragon channelRealdragon = (ChannelRealdragon) object;
			list4.add(channelRealdragon);
		}
		VelocityContext context = new VelocityContext();

		context.put("list", list3);
		try {
			velocityFileWriter("net/snake/service/template/channel.vm", context, "xuewei.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		context = new VelocityContext();

		context.put("list", list4);
		try {
			velocityFileWriter("net/snake/service/template/channel_realdragon.vm", context, "xuewei_realdragon.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void startmap2(int id) {
		// `f_map_name_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '鍦烘櫙鍚嶅瓙,l鍚屾枃浠跺悕,涓嶅甫鎵╁睍鍥介檯鍖�,
		// `f_monster_desc_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '鍒锋�鎻忚堪鍥介檯鍖�,
		// `f_exercise_desc_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '缁冪骇绛夌骇鎻忚堪鍥介檯鍖�,
		// `f_boss_desc_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT '鍒锋柊BOSS 鎻忚堪鍥介檯鍖�,
		// `f_boss_drop_goods_i18n` VARCHAR(255) NULL DEFAULT NULL COMMENT 'boss浜у嚭鐗╁搧鎻忚堪鍥介檯鍖�,
		String expa = "a=\"(\\d{0,1})\"";
		String expe = "e=\"(\\d{0,1})\"";
		String expf = "f=\"(\\d{0,1})\"";
		if (id == 0) {
			List<Map2> listMap2s = map2DataProvider.getMapList();
			System.out.println("map10000000========" + listMap2s.size());

			for (Map2 map2 : listMap2s) {

				VelocityContext context = new VelocityContext();
				List<Map2> listMap2s2 = new ArrayList<Map2>();
				String blcok = map2.getBlock().replaceAll(expa, "");
				blcok = blcok.replaceAll(expe, "");
				blcok = blcok.replaceAll(expf, "");
				map2.setBlock(blcok);
				listMap2s2.add(map2);
				String fString = map2.getFileName();
				context.put("list", listMap2s2);
				try {
					velocityFileWriter("net/snake/service/template/map.vm", context, "map/" + fString
							+ ".xml");
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		} else {
			List<Map2> listMap2s = map2DataProvider.getMap2byId(id);
			System.out.println("map222222222========" + listMap2s.size());
			VelocityContext context = new VelocityContext();
			String fString = listMap2s.get(0).getFileName();
			context.put("list", listMap2s);
			try {
				velocityFileWriter("net/snake/service/template/map.vm", context, "map/" + fString + ".xml");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
	}
	public void startEquipmentPlayconfig() {
		System.out.println("ShengChengVm.startEquipmentPlayconfig");
		List<Object> listEquipmentPlayconfig = publicJavaProvider
				.getSelectTable("publicJavaDao.getEquipmentPlayconfig");
		List<EquipmentPlayconfig> list2 = new ArrayList<EquipmentPlayconfig>(listEquipmentPlayconfig.size());
		for (Object object : listEquipmentPlayconfig) {
			EquipmentPlayconfig equipmentPlayconfig = (EquipmentPlayconfig) object;
			list2.add(equipmentPlayconfig);
		}
		VelocityContext context = new VelocityContext();

		context.put("list", list2);
		try {
			velocityFileWriter("net/snake/service/template/equipmenplayconfig.vm", context,
					"equipmenplayconfig.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	public void startskellecffect() {
		List<SkillEffect> listSkillEffect = skillEffectProvider.getSkilleEffectsList();
		VelocityContext context = new VelocityContext();
		context.put("list", listSkillEffect);
		try {
			velocityFileWriter("net/snake/service/template/skillecffect.vm", context, "skillecffect.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	public void startAvatar(int id) {
		if (id == 0) {

			List<Avatar> listAvatar = avatarProvider.getAvatar_Avatars();

			VelocityContext context = new VelocityContext();

			context.put("list", listAvatar);
			try {
				velocityFileWriter2("net/snake/service/template/huanzhuang-avatar.vm", context,
						"huanzhuang-avatar.jsfl");
				velocityFileWriter2("net/snake/service/template/huanzhuang-avatar2.vm", context,
						"huanzhuang-avatar2.jsfl");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		} else {

			List<Avatar> listAvatar = avatarProvider.getAvatar_AvatarsByid(id);
			VelocityContext context = new VelocityContext();
			context.put("list", listAvatar);
			try {
				velocityFileWriter2("net/snake/service/template/huanzhuang-avatar.vm", context,
						"huanzhuang-avatar.jsfl");
				velocityFileWriter2("net/snake/service/template/huanzhuang-avatar2.vm", context,
						"huanzhuang-avatar2.jsfl");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

	}
	public void startAvatar2(int ftype, int time) {
		AvatarExample example = new AvatarExample();
		AvatarExample.Criteria criteria = example.createCriteria();
		if (ftype != 0) {
			criteria.andTypeEqualTo(ftype);
		}
		if (time != 0) {
			long ago=System.currentTimeMillis()-(1000*3600*24*10);
			Calendar calendar=Calendar.getInstance();
			calendar.setTimeInMillis(ago);
			Date value= calendar.getTime();
//			criteria.andDateGreaterThanOrEqualTo(net.snake.common.Timer
//					.getDateByString(net.snake.common.Timer.getNowStringDate()));
			criteria.andDateGreaterThanOrEqualTo(value);
		}

		List<Avatar> listAvatar = avatarProvider.getAvatar_Avatars2(example);

		VelocityContext context = new VelocityContext();

		context.put("list", listAvatar);
		try {
			velocityFileWriter2("net/snake/service/template/huanzhuang-avatar.vm", context,
					"huanzhuang-avatar.jsfl");
			velocityFileWriter2("net/snake/service/template/huanzhuang-avatar2.vm", context,
					"huanzhuang-avatar2.jsfl");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
	public void startLanguage() {
		List<Object> list = publicJavaProvider.getSelectTable("publicJavaDao.getLanguage");
		List<Language> listLanguages = new ArrayList<Language>(list.size());
		for (Object object : list) {
			Language language = (Language) object;
			//language = (Language) TiHuan(language);
			listLanguages.add(language);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", listLanguages);
		try {
			velocityFileWriter("net/snake/service/template/language.vm", context, Constants.root + "lan.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

	public void startAvatarEffect2(int ftype, int time) {
		VelocityContext context = new VelocityContext();
		EffectExample example = new EffectExample();
		EffectExample.Criteria criteria = example.createCriteria();
		if (ftype != 0) {
			criteria.andTypeEqualTo(ftype);
		}
		if (time != 0) {
			long ago=System.currentTimeMillis()-(1000*3600*24*10);
			Calendar calendar=Calendar.getInstance();
			calendar.setTimeInMillis(ago);
			Date value= calendar.getTime();
//			criteria.andDateGreaterThanOrEqualTo(net.snake.common.Timer
//					.getDateByString(net.snake.common.Timer.getNowStringDate()));
			criteria.andDateGreaterThanOrEqualTo(value);
			
//			criteria.andDateGreaterThanOrEqualTo(net.snake.common.Timer
//					.getDateByString(net.snake.common.Timer.getNowStringDate()));
		}
		List<Effect> listEffect = avatarProvider.getAvatar_Effect2(example);
		try {
			context.put("list", listEffect);
			velocityFileWriter2("net/snake/service/template/huanzhuang-effect.vm", context,
					"huanzhuang-effect.jsfl");
			velocityFileWriter2("net/snake/service/template/huanzhuang-effect2.vm", context,
					"huanzhuang-effect2.jsfl");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

	public void startAvatarEffect(int id) {
		if (id == 0) {
			List<Effect> listEffect = avatarProvider.getAvatar_Effect();
			VelocityContext context = new VelocityContext();

			try {
				context.put("list", listEffect);
				velocityFileWriter2("net/snake/service/template/huanzhuang-effect.vm", context,
						"huanzhuang-effect.jsfl");
				velocityFileWriter2("net/snake/service/template/huanzhuang-effect2.vm", context,
						"huanzhuang-effect2.jsfl");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		} else {
			List<Effect> listEffect = avatarProvider.getAvatar_EffectByid(id);
			VelocityContext context = new VelocityContext();

			try {
				context.put("list", listEffect);
				velocityFileWriter2("net/snake/service/template/huanzhuang-effect.vm", context,
						"huanzhuang-effect.jsfl");
				velocityFileWriter2("net/snake/service/template/huanzhuang-effect2.vm", context,
						"huanzhuang-effect2.jsfl");
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}

	}

	private void velocityFileWriter2(String template, VelocityContext context, String fileName)
			throws Exception {
		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		p.setProperty(Velocity.RESOURCE_LOADER, "class");
		p.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init(p);
		// template="com/heishi/httpservice/service/template/monster-model.vm"
		Template templateo = ve.getTemplate(template, "utf-8");
		StringWriter sw = new StringWriter();

		templateo.merge(context, sw);
		IOTool.writeBinaryToFile(fileName, sw.toString().getBytes("gbk"));

	}
	private void velocityFileWriter(String template, VelocityContext context, String fileName)
			throws Exception {
		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		p.setProperty(Velocity.RESOURCE_LOADER, "class");
		p.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());

		ve.init(p);
		// template="com/heishi/httpservice/service/template/monster-model.vm"
		Template templateo = ve.getTemplate(template, "utf-8");
		StringWriter sw = new StringWriter();
		templateo.merge(context, sw);
		String sw2 = sw.toString();
		sw2 = sw2.replace("\"\r\n", "\" ").replace("\"\n", "\" ");
		// String reg ="[\n-\r]";
		// Pattern pa = Pattern.compile(reg);
		// Matcher m = pa.matcher(sw2);
		// sw2 = m.replaceAll("");
		sw2 = sw2.replace("\"true\"", "\"1\"");
		sw2 = sw2.replace("\"false\"", "\"0\"");

		// System.out.println(sw2);

		IOTool.writeBinaryToFile(fileName, sw2.getBytes("utf-8"));

	}

	public void startsceneMonster() {
		List<SceneMonster> listSceneMonster = sceneMonsterProvider.getSceneMonster();
		Element root = new Element("root");
		Document Doc = new Document(root);
		for (SceneMonster sceneMonster : listSceneMonster) {
			Element element = new Element("scene");
			element.setAttribute("id", sceneMonster.getMapId().toString());

			List<SceneMonster> listSceneMonster2 = sceneMonsterProvider.getSceneMonsters(sceneMonster
					.getMapId());
			for (SceneMonster sceneMonster2 : listSceneMonster2) {
				/** 鍔犲叆transOut鑺傜偣 */
				Element element2 = new Element("monster");
				Element ownerElement = element2;
				ownerElement.setAttribute("id", sceneMonster2.getModel().toString());
				ownerElement.setAttribute("x", sceneMonster2.getX().toString());
				ownerElement.setAttribute("y", sceneMonster2.getY().toString());
				element.addContent(ownerElement);
			}

			root.addContent(element);
		}

		format.setIndent(" ");

		XMLOutputter XMLOut = new XMLOutputter(format);
		try {
			XMLOut.output(Doc, new FileOutputStream("sceneMonster.xml"));
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}

	}
	public void startsound() {
		VelocityContext context = new VelocityContext();

		List<Sound> listEffect = avatarProvider.getSound();
		List<Sound> listEffect2 = avatarProvider.getSoundMP3();
		List<Sound> listEffect3 = new ArrayList<Sound>(listEffect2.size());
		for (Sound sound : listEffect2) {
			sound.setFilename("bgsound" + sound.getId() + ".mp3");

			listEffect3.add(sound);
		}
		try {
			context.put("list", listEffect);
			velocityFileWriter2("net/snake/service/template/soundSC.vm", context, "sound.jsfl");
			velocityFileWriter2("net/snake/service/template/soundSC2.vm", context, "sound2.jsfl");
			context.put("list", listEffect3);
			velocityFileWriter("net/snake/service/template/soundMP3.vm", context, "soundMP3.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

	public void startAchieve() {
		VelocityContext context = new VelocityContext();
		List<Achieve> listEffect = publicJavaProvider.getAchieve();
		try {
			context.put("list", listEffect);
			velocityFileWriter("net/snake/service/template/achieve.vm", context, "achieve.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
	
	public String TiHuan2(String str){
		if(StringUtil.isEmpty(str)){
			return str;
		}
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		str = str.replace("'", "&apos;");
		str = str.replace("\"", "&quot;");
		str = str.replace("&", "&amp;;");
	
		return str;
	}
	public void startDgwd(){
		List<Object> objList = publicJavaProvider.getSelectTable("publicJavaDao.getDgwd");
		List<Dgwd> list = new ArrayList<Dgwd>(objList.size());
		for (Object dgwd : objList) {
			Dgwd dgwd2 = (Dgwd) dgwd;
//			dgwd2 = (Dgwd) TiHuan(dgwd);
			list.add(dgwd2);
		}
		VelocityContext context = new VelocityContext();
		context.put("list", list);
		try {
			velocityFileWriter("net/snake/service/template/dgwd.vm", context, "dgwd.xml");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
	}
	
	public static void main(String[] args) {
		// FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext(
		// "f:/appPngToSwf/applicationContext.xml");
		// ShengChengVm scv = (ShengChengVm) fs.getBean("ShengChengVm");
		// scv.startmap();
		// scv.startmap2(0);
		//
		FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext(args[0]);
		String typeString = args[1];
		System.err.println("typtString=========" + typeString);
		ShengChengVm scv = (ShengChengVm) fs.getBean("ShengChengVm");
		if ("goodmodel".equals(typeString)) {
			scv.startgoodmodel();
		} else if ("hiddenweapons".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startHiddenWeapons();
		} else if ("horsemodel".equals(typeString)) {
			scv.starthorsemodel();
		} else if ("skill".equals(typeString)) {
			scv.startskill();
		} else if ("monstermodel".equals(typeString)) {
			scv.startmonstermodel();
		} else if ("avatar".equals(typeString)) {
			scv.startAvatar(Integer.valueOf(args[2]));
		} else if ("sound".equals(typeString)) {
			scv.startsound();
		} else if ("avatar_effect".equals(typeString)) {
			scv.startAvatarEffect(Integer.valueOf(args[2]));
		} else if ("avatar2".equals(typeString)) {
			scv.startAvatar2(Integer.valueOf(args[2]), Integer.valueOf(args[3]));
		} else if ("avatar_effect2".equals(typeString)) {
			scv.startAvatarEffect2(Integer.valueOf(args[2]), Integer.valueOf(args[3]));
		} else if ("task".equals(typeString)) {
			scv.starttask();
		} else if ("taskdialog".equals(typeString)) {
			scv.starttaskdialog();
		} else if ("npc".equals(typeString)) {
			scv.startnpc();
		} else if ("map".equals(typeString)) {
			scv.startmap();
		} else if ("map2".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startmap2(Integer.valueOf(args[2]));
		} else if ("achieve".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startAchieve();
		} else if ("icotobig".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.starticobig();
		} else if ("newIconBig".equals(typeString)) {
			scv.starticobignew();
		}else if ("instance".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startinstance();
		} else if ("sceneMonster".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startsceneMonster();
		} else if ("goodsdc".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startGoodsDc();
		} else if ("equipmentplayconfig".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startEquipmentPlayconfig();
		} else if ("Language".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startLanguage();
		} else if ("chengshi".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startChengShi(args[2]);
		} else if ("totem".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startTotem();
		} else if ("lianti".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startLianti();
		} else if ("languageflash".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startLanguageFlash();
		} else if ("shopshengwang".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startShopShengWang();
		}else if ("dgwd".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startDgwd();
		}else if ("jingmai".equals(typeString)) {
			// System.out.println("鍙傛暟======"+args[2]);
			scv.startJinmai();
		}else if ("dujie".equals(typeString)) {
			scv.startDujie();
		}else if ("dujieAdd".equals(typeString)) {
			scv.startDujieAdd();
		}else if("hufa".equals(typeString)){
			scv.startHufa();
		}else if("activity".equals(typeString)){
			scv.startAcivity();
		}else {
			scv.startskellecffect();
		}
		
	}

}
