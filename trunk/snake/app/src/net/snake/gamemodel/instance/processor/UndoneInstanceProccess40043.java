package net.snake.gamemodel.instance.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.Language;
import net.snake.gamemodel.activities.bean.DailyActivity;
import net.snake.gamemodel.activities.response.DailyActivityResponse40042;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.bean.InstanceDataRef;
import net.snake.gamemodel.instance.logic.MyInstanceDayStatManager;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 40043, accessLimit = 100)
public class UndoneInstanceProccess40043 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		List<InstanceDataRef> instanceDataRefList = InstanceDataProvider.getInstance().getInstanceDataRefList();
		byte byte1 = request.getByte();
		DailyActivity[] undinstance = null;
		switch (byte1) {
		case 1:
			undinstance = meetConditionRefToUnDoInstance(instanceDataRefList, character);
			break;
		case 0:
			undinstance = refToUnDoInstance(instanceDataRefList, character.getMyInstanceDayStatManager(), character);
			break;
		}
		character.sendMsg(new DailyActivityResponse40042(undinstance));
	}

	private DailyActivity[] refToUnDoInstance(List<InstanceDataRef> ref, MyInstanceDayStatManager day, Hero character) {
		List<DailyActivity> a = new ArrayList<DailyActivity>();
		for (InstanceDataRef dataref : ref) {
			if (dataref.getWeekdayshow().contains("" + getWeek())) {
				Integer countLimite = dataref.getEnterCountLimite();
				if (character.getMyCharacterVipManger().isVipYueka()) {
					countLimite++;
				}
				int state = 0;
				if (day.getTodayEnterCount(dataref.getInstanceModelId()) >= countLimite) {
					state = 1;
				}
				// 数量byte，{时间string，活动名称strin，等级short,发布人名称string，发布人id
				// int（无发布人为0），
				// 已完成次数short，总次数short，奖励信息byte（0表示没星，1半星，2一星，3,一星半......），
				// 详细信息string
				// xml格式,类型:byte(类型1进行中,2全天,3即将举行,4已过期)，完成状态（0未完成/1完成）}
				DailyActivity undo = new DailyActivity(dealnull(dataref.getInstanceopentimedescripI18n()), dealnull(dataref.getInstanceNameI18n()),
						dealnull(dataref.getLevelLowerLimit()), getNpcname(dataref.getTransnpcid()), dealnull(dataref.getTransnpcid()), dealnull(day.getTodayEnterCount(dataref
								.getInstanceModelId())), dealnull(countLimite + dataref.getfUnit()), dealnull(dataref.getfRewardgrade()), dealnull(dataref.getDescptI18n()),
						dealnull(2), dealnull(state), dealnull(dataref.getfRewardGood()), (short) 1);

				// ,dealnull();
				// );
				a.add(undo);
			}
		}
		return a.toArray(new DailyActivity[] {});
	}

	private DailyActivity[] meetConditionRefToUnDoInstance(List<InstanceDataRef> ref, Hero character) {
		List<DailyActivity> a = new ArrayList<DailyActivity>();
		MyInstanceDayStatManager im = character.getMyInstanceDayStatManager();
		for (InstanceDataRef dataref : ref) {
			if (dataref.getWeekdayshow().contains("" + getWeek()) && character.getGrade() > dataref.getLevelLowerLimit()) {
				Integer countLimite = dataref.getEnterCountLimite();
				if (character.getMyCharacterVipManger().isVipYueka()) {
					countLimite++;
				}
				int state = 0;
				if (im.getTodayEnterCount(dataref.getInstanceModelId()) >= countLimite) {
					state = 1;
				}
				DailyActivity undo = new DailyActivity(dealnull(dataref.getInstanceopentimedescripI18n()), dealnull(dataref.getInstanceNameI18n()),
						dealnull(dataref.getLevelLowerLimit()), getNpcname(dataref.getTransnpcid()), dealnull(dataref.getTransnpcid()), dealnull(im.getTodayEnterCount(dataref
								.getInstanceModelId())), dealnull(countLimite + dataref.getfUnit()), dealnull(dataref.getfRewardgrade()), dealnull(dataref.getDescptI18n()),
						dealnull(2), dealnull(state), dealnull(dataref.getfRewardGood()), (short) 1);
				a.add(undo);
			}
		}
		return a.toArray(new DailyActivity[] {});
	}

	public int getWeek() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		return dayOfWeek;
	}

	private String dealnull(String s) {
		return s == null ? "" : s;
	}

	private int dealnull(Integer id) {
		return id == null ? 0 : id;
	}

	private String getNpcname(int id) {
		if (id == 0) {
			return Language.WUXU;
		}
		Npc npc = NpcManager.getInstance().getCacheNpcMap().get(id);
		return npc == null ? Language.WEIZHI + "NPC" : npc.getNameI18n();
	}
}
