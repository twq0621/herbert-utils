package net.snake.dao.task;

import java.util.Date;

public class Task {

	/**
	 * 涓婚敭id(鑷闀� t_task.f_id
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer id;
	/**
	 * 鐣岄潰浠诲姟id t_task.f_task_id
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer taskId;
	/**
	 * 浠诲姟鍚嶇О t_task.f_name
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String name;
	/**
	 * 濡�1涓荤嚎 2鏀嚎 3鏃ョ幆寮忎换鍔�.鏃ユ娂闀栦换鍔�鍛ㄧ幆寮忎换鍔�.鍗囩骇鐜紡浠诲姟 t_task.f_type
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Byte type;
	/**
	 * 鍔熻兘绫诲瀷:0澶嶅悎浠诲姟,1鍒烘潃鎬墿 2瀵绘壘NPC 3鏀堕泦 4鍒拌揪鎸囧畾鍖哄煙 5浼犻� 6閲囬泦 7瑙﹀彂鍔ㄤ綔 8鎹曡幏鍧愰獞 t_task.f_functionType
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer functiontype;
	/**
	 * 鏄惁鍙斁寮冿紙1鍙斁寮�0涓嶅彲鏀惧純锛�t_task.f_islost
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Boolean islost;
	/**
	 * 鏄惁鍙噸澶嶆帴锛�鍙噸澶�0涓嶅彲閲嶅锛�t_task.f_isRepeat
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Boolean isrepeat;
	/**
	 * 浠诲姟鍙戝竷鏂瑰紡 1 npc 鍙戝竷 2鍒涘缓瑙掕壊鏃舵坊鍔�3绯荤粺鍙戝竷 4閬撳叿鍙戝竷 5闄烽槺鐐硅Е鍙�t_task.f_publishStyle
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Byte publishstyle;
	/**
	 * 浠诲姟鎺ュ彈绛夌骇涓嬮檺 t_task.f_receptionLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Short receptionlevel;
	/**
	 * 鎺ュ彈浠诲姟鏃剁瓑绾т笂闄�t_task.f_receptionUpLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Short receptionuplevel;
	/**
	 * 璧峰NPC t_task.f_beginNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer beginnpc;
	/**
	 * 缁撴潫npc id t_task.f_endNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer endnpc;
	/**
	 * 浠诲姟鐩爣鐗╁搧 (鐗╁搧id#鐗╁搧鏁伴噺#鍦烘櫙id_x_y_璺濈),* t_task.f_targetGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetgoods;
	/**
	 * 浠诲姟鐩爣绫诲瀷鎬�(鎬墿妯℃澘id#鏁伴噺#鍦烘櫙_x_y_璺濈洰鏍囧嚑姝ュ仠姝�,* t_task.f_targetMonster
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetmonster;
	/**
	 * 鐩爣Npc (npcid),* t_task.f_targetNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetnpc;
	/**
	 * 鏍煎紡 浠ユ牸瀛愪负鍗曚綅 鍦烘櫙id t_task.f_targetArea
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetarea;
	/**
	 * 鍔ㄤ綔瑙﹀彂 澶氫釜鍔ㄤ綔浠�鍒嗗壊  锛�缁勯槦锛�娣诲姞濂藉弸3浣跨敤鍠囧彮 4瑁呭鐗╁搧 5浣跨敤鎭㈠绫婚亾鍏�6閬撳叿鍚堟垚 7瀹濈煶闀跺祵 8 瑁呭淇悊 9涔板叆閬撳叿 10鍗栧嚭閬撳叿 11绉樼睄鍗囩骇 12灏嗘妧鑳�閬撳叿鎷栧叆蹇嵎鏍�) t_task.f_targetAction
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetaction;
	/**
	 * 瀹屾垚浠诲姟绛夌骇 t_task.f_endTaskLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer endtasklevel;
	/**
	 * 鍗曚釜浠诲姟鏃堕棿闄愬埗浠ュ垎閽熶负鍗曚綅 -1涓烘病鏈夋椂闂撮檺鍒�t_task.f_limitTime
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Short limittime;
	/**
	 * 濂栧姳缁忛獙(濂栧姳) t_task.f_experience
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer experience;
	/**
	 * 濂栧姳閾滃竵(濂栧姳) t_task.f_copper
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer copper;
	/**
	 * 濂栧姳閲戝竵(绀奸噾)(濂栧姳) t_task.f_gold
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer gold;
	/**
	 * 鍥哄畾浠诲姟濂栧姳(鏁伴噺涓嶈兘澶т簬鐗╁搧涓婇檺)鏍煎紡 (鐗╁搧id#鏁伴噺#闂ㄦ淳id),*(濂栧姳) t_task.f_goods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String goods;
	/**
	 * 浠诲姟鍙戣捣鎻忚堪 t_task.f_taskDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String taskdes;
	/**
	 * 鏈畬鎴愪换鍔℃弿杩�t_task.f_unEndDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String unenddes;
	/**
	 * 瀹屾垚浠诲姟鎻忚堪 t_task.f_endDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String enddes;
	/**
	 * 浠诲姟鎻忚堪 t_task.f_publishDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String publishdes;
	/**
	 * 濂芥劅鍊�t_task.f_goodFeel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer goodfeel;
	/**
	 * 甯淳璐＄尞(濂栧姳) t_task.f_party
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer party;
	/**
	 * 鍠勬伓鍊�t_task.f_goodBad
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer goodbad;
	/**
	 * 绉板彿id(濂栧姳) t_task.f_title
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer title;
	/**
	 * 鎺ュ彈浠诲姟鏃惰幏寰楃殑浠诲姟鍝侊紙涓嶅彲鍙犲姞鐨勭墿鍝侊級鏍煎紡 鐗╁搧id:浜や换鍔℃椂鏄惁绯荤粺鏀跺洖(0/1)闆朵负涓嶆敹鍥�1鏀跺洖 t_task.f_taskGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String taskgoods;
	/**
	 * 浠诲姟瀹屾垚浠绘椂鍙栬蛋鐨勭墿鍝両D t_task.f_takeGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer takegoods;
	/**
	 * 濂栧姳鎴樺満澹版湜(濂栧姳) t_task.f_warrepute
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer warrepute;
	/**
	 * 鍔卞鐨刡uffID(濂栧姳) t_task.f_buffid
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer buffid;
	/**
	 * 濂栧姳鐨勫叏鏈嶅叕鍛�濂栧姳) t_task.f_bulletin
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer bulletin;
	/**
	 * 濂栧姳鐨勭湡姘旈噺(濂栧姳) t_task.f_zhenqi
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer zhenqi;
	/**
	 * 鐜换鍔＄殑闅惧害 t_task.f_loopTaskDifficulty
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer looptaskdifficulty;
	/**
	 * 鐜换鍔″鍔辩殑涓板帤搴�t_task.f_loopTaskBonus
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer looptaskbonus;
	/**
	 * 浠诲姟鏉′欢锛氭崟鑾峰潗楠慖D (鍧愰獞id#鏁伴噺#鍦烘櫙_x_y_璺濈) t_task.f_targetHorse
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targethorse;
	/**
	 * 閬撳叿瑙﹀彂浠诲姟(閬撳叿id) t_task.f_triggerGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer triggergoods;
	/**
	 * 闄烽槺瑙﹀彂浠诲姟(鍦烘櫙id_x_y_璺濈) t_task.f_triggerScene
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String triggerscene;
	/**
	 * 鍙栬蛋鐜╁鐨勯摐甯�t_task.f_takecopper
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer takecopper;
	/**
	 * 鍓嶇疆浠诲姟鎺掑簭鐢╥d(鏆傛椂娌＄敤) t_task.f_premiseId
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer premiseid;
	/**
	 * 鍓嶆彁浠诲姟id t_task.f_premiseTask
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer premisetask;
	/**
	 * 宸ュ叿鐢ㄤ簬璺熻釜 鎻掑叆/淇敼鏃堕棿 t_task.f_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Date time;
	/**
	 * 0涓嶆墸鍧愰獞1鎵ｅ潗楠�t_task.f_horseDrop
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer horsedrop;
	/**
	 * (韬笂鏄惁鏈夌┛鎴存鍣�鏈夋病鏈変僵鎴存鍣�t_task.f_needWuqi
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer needwuqi;
	/**
	 * (韬笂鏄惁鏈夌┛鎴磋。鏈�鏈夋病鏈変僵鎴磋。鏈�t_task.f_needDress
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer needdress;
	/**
	 * (闇�宸插涔犵殑姝﹀姛id) 鏈夋病鏈夊浼氭鍔�t_task.f_needSkill
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer needskill;
	/**
	 * (闄愬畾鎬荤殑姝﹀姛绛夌骇)姝﹀姛灞傛暟鏄惁杈惧埌 t_task.f_needWugongGrade
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer needwugonggrade;
	/**
	 * 浠诲姟鐩爣鎻忚堪 t_task.f_taskTargetDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String tasktargetdes;
	/**
	 * 鍒ゆ柇鐜╁鏄惁浠庡晢鍩庝腑璐拱杩囨煇涓墿鍝侊紙鐗╁搧鍙嚜瀹氫箟杈撳叆锛�(閫楀彿鐩搁殧鐨勯亾鍏稩D#鏁伴噺) t_task.f_targetShopping
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetshopping;
	/**
	 * 鍏呭�閲戦(鍒ゆ柇鐜╁鏄惁鍏呭�杩�(鍗曚綅鍏冨疂) t_task.f_targetRecharge
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetrecharge;
	/**
	 * 鍒ゆ柇鐜╁鏄惁浣跨敤杩囧潗楠戣繘鏀绘湰鍔熻兘锛氬皢鐨勫崲椹垚鍔熷湴杩涢樁鍚堟垚涓洪噾鐢茬鐗�1涓洪渶杩涢樁0涓嶉渶杩涢樁) t_task.f_targetMountUpgrade
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetmountupgrade;
	/**
	 * 鍒ゆ柇鐜╁褰撳墠鏄惁缁勯槦锛岄槦浼嶆�浜烘暟鏄惁>X(鏍煎紡:"鐩爣闃熶紞浜烘暟" 鎴�"") t_task.f_targetGroup
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetgroup;
	/**
	 * 鍒ゆ柇鐜╁褰撳墠鎷ユ湁濂藉弸浜烘暟鐨勬暟閲忔槸鍚�X(鏍煎紡:"鐩爣濂藉弸浜烘暟" 鎴�"") t_task.f_targetFriend
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetfriend;
	/**
	 * 鍒ゆ柇鐜╁鏌愪釜閮ㄤ綅鏄惁浣╂埓浜嗚澶嘔D(鏍煎紡:"鐩爣閮ㄤ綅ID#鐩爣瑁呭ID" 鎴�"") t_task.f_targetEquip
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetequip;
	/**
	 * 鍒ゆ柇鐜╁鏌愪釜姝﹀姛鐨勭瓑绾ф槸鍚﹀ぇ浜嶺(鏍煎紡:"鐩爣姝﹀姛ID#鐩爣绛夌骇" 鎴�"") t_task.f_targetSkillLv
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetskilllv;
	/**
	 * 鍒ゆ柇鐜╁鏁翠綋姝﹀姛澧冪晫灞傛暟鏄惁澶т簬X(鏍煎紡:"鐩爣绛夌骇" 鎴�"") t_task.f_targetAllSkillLv
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetallskilllv;
	/**
	 * 鍒ゆ柇鐜╁鏌愪釜缁忚剦绌翠綅鏄惁鍐查�(鏍煎紡:"鐩爣绌翠綅ID" 鎴�"") t_task.f_targetPoint
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetpoint;
	/**
	 * 鍒ゆ柇鐜╁鏌愭潯缁忚剦鏄惁鍐查�(鏍煎紡:"鐩爣缁忚剦ID" 鎴�"") t_task.f_targetChannel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetchannel;
	/**
	 * 鍒ゆ柇鐜╁韬笂鏄惁鏈夋煇涓狟UFF-ID(鏍煎紡:"鐩爣BUFF ID" 鎴�"") t_task.f_targetBuff
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String targetbuff;
	/**
	 * 鐜换鍔℃渶澶т笂闄愭鏁�t_task.f_loopMaxCount
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer loopmaxcount;
	/**
	 * 濂栧姳鏆楀櫒(鏆楀櫒id) t_task.f_hiddenWeapon
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Integer hiddenweapon;
	/**
	 * 鎺ヤ换鍔″紑濮嬫椂闂�t_task.f_accept_begin_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Date acceptBeginTime;
	/**
	 * 浜や换鍔＄粨鏉熸椂闂�t_task.f_complete_end_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private Date completeEndTime;
	/**
	 * 浠诲姟鍚嶇О鍥介檯鍖�t_task.f_name_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String nameI18n;
	/**
	 * 鏈畬鎴愪换鍔℃弿杩伴檯鍖栧浗 t_task.f_unEndDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String unenddesI18n;
	/**
	 * 瀹屾垚浠诲姟鎻忚堪鍥介檯鍖�t_task.f_endDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String enddesI18n;
	/**
	 * 浠诲姟鎻忚堪 t_task.f_publishDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String publishdesI18n;
	/**
	 * 浠诲姟鐩爣鎻忚堪鍥介檯鍖�t_task.f_taskTargetDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	private String tasktargetdesI18n;
	/**
	 * 涓婚敭id(鑷闀� t_task.f_id
	 * @return  the value of t_task.f_id
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 涓婚敭id(鑷闀� t_task.f_id
	 * @param id  the value for t_task.f_id
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 鐣岄潰浠诲姟id t_task.f_task_id
	 * @return  the value of t_task.f_task_id
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getTaskId() {
		return taskId;
	}

	/**
	 * 鐣岄潰浠诲姟id t_task.f_task_id
	 * @param taskId  the value for t_task.f_task_id
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/**
	 * 浠诲姟鍚嶇О t_task.f_name
	 * @return  the value of t_task.f_name
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * 浠诲姟鍚嶇О t_task.f_name
	 * @param name  the value for t_task.f_name
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 濡�1涓荤嚎 2鏀嚎 3鏃ョ幆寮忎换鍔�.鏃ユ娂闀栦换鍔�鍛ㄧ幆寮忎换鍔�.鍗囩骇鐜紡浠诲姟 t_task.f_type
	 * @return  the value of t_task.f_type
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 濡�1涓荤嚎 2鏀嚎 3鏃ョ幆寮忎换鍔�.鏃ユ娂闀栦换鍔�鍛ㄧ幆寮忎换鍔�.鍗囩骇鐜紡浠诲姟 t_task.f_type
	 * @param type  the value for t_task.f_type
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 鍔熻兘绫诲瀷:0澶嶅悎浠诲姟,1鍒烘潃鎬墿 2瀵绘壘NPC 3鏀堕泦 4鍒拌揪鎸囧畾鍖哄煙 5浼犻� 6閲囬泦 7瑙﹀彂鍔ㄤ綔 8鎹曡幏鍧愰獞 t_task.f_functionType
	 * @return  the value of t_task.f_functionType
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getFunctiontype() {
		return functiontype;
	}

	/**
	 * 鍔熻兘绫诲瀷:0澶嶅悎浠诲姟,1鍒烘潃鎬墿 2瀵绘壘NPC 3鏀堕泦 4鍒拌揪鎸囧畾鍖哄煙 5浼犻� 6閲囬泦 7瑙﹀彂鍔ㄤ綔 8鎹曡幏鍧愰獞 t_task.f_functionType
	 * @param functiontype  the value for t_task.f_functionType
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setFunctiontype(Integer functiontype) {
		this.functiontype = functiontype;
	}

	/**
	 * 鏄惁鍙斁寮冿紙1鍙斁寮�0涓嶅彲鏀惧純锛�t_task.f_islost
	 * @return  the value of t_task.f_islost
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Boolean getIslost() {
		return islost;
	}

	/**
	 * 鏄惁鍙斁寮冿紙1鍙斁寮�0涓嶅彲鏀惧純锛�t_task.f_islost
	 * @param islost  the value for t_task.f_islost
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setIslost(Boolean islost) {
		this.islost = islost;
	}

	/**
	 * 鏄惁鍙噸澶嶆帴锛�鍙噸澶�0涓嶅彲閲嶅锛�t_task.f_isRepeat
	 * @return  the value of t_task.f_isRepeat
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Boolean getIsrepeat() {
		return isrepeat;
	}

	/**
	 * 鏄惁鍙噸澶嶆帴锛�鍙噸澶�0涓嶅彲閲嶅锛�t_task.f_isRepeat
	 * @param isrepeat  the value for t_task.f_isRepeat
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setIsrepeat(Boolean isrepeat) {
		this.isrepeat = isrepeat;
	}

	/**
	 * 浠诲姟鍙戝竷鏂瑰紡 1 npc 鍙戝竷 2鍒涘缓瑙掕壊鏃舵坊鍔�3绯荤粺鍙戝竷 4閬撳叿鍙戝竷 5闄烽槺鐐硅Е鍙�t_task.f_publishStyle
	 * @return  the value of t_task.f_publishStyle
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Byte getPublishstyle() {
		return publishstyle;
	}

	/**
	 * 浠诲姟鍙戝竷鏂瑰紡 1 npc 鍙戝竷 2鍒涘缓瑙掕壊鏃舵坊鍔�3绯荤粺鍙戝竷 4閬撳叿鍙戝竷 5闄烽槺鐐硅Е鍙�t_task.f_publishStyle
	 * @param publishstyle  the value for t_task.f_publishStyle
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setPublishstyle(Byte publishstyle) {
		this.publishstyle = publishstyle;
	}

	/**
	 * 浠诲姟鎺ュ彈绛夌骇涓嬮檺 t_task.f_receptionLevel
	 * @return  the value of t_task.f_receptionLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Short getReceptionlevel() {
		return receptionlevel;
	}

	/**
	 * 浠诲姟鎺ュ彈绛夌骇涓嬮檺 t_task.f_receptionLevel
	 * @param receptionlevel  the value for t_task.f_receptionLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setReceptionlevel(Short receptionlevel) {
		this.receptionlevel = receptionlevel;
	}

	/**
	 * 鎺ュ彈浠诲姟鏃剁瓑绾т笂闄�t_task.f_receptionUpLevel
	 * @return  the value of t_task.f_receptionUpLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Short getReceptionuplevel() {
		return receptionuplevel;
	}

	/**
	 * 鎺ュ彈浠诲姟鏃剁瓑绾т笂闄�t_task.f_receptionUpLevel
	 * @param receptionuplevel  the value for t_task.f_receptionUpLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setReceptionuplevel(Short receptionuplevel) {
		this.receptionuplevel = receptionuplevel;
	}

	/**
	 * 璧峰NPC t_task.f_beginNpc
	 * @return  the value of t_task.f_beginNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getBeginnpc() {
		return beginnpc;
	}

	/**
	 * 璧峰NPC t_task.f_beginNpc
	 * @param beginnpc  the value for t_task.f_beginNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setBeginnpc(Integer beginnpc) {
		this.beginnpc = beginnpc;
	}

	/**
	 * 缁撴潫npc id t_task.f_endNpc
	 * @return  the value of t_task.f_endNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getEndnpc() {
		return endnpc;
	}

	/**
	 * 缁撴潫npc id t_task.f_endNpc
	 * @param endnpc  the value for t_task.f_endNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setEndnpc(Integer endnpc) {
		this.endnpc = endnpc;
	}

	/**
	 * 浠诲姟鐩爣鐗╁搧 (鐗╁搧id#鐗╁搧鏁伴噺#鍦烘櫙id_x_y_璺濈),* t_task.f_targetGoods
	 * @return  the value of t_task.f_targetGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetgoods() {
		return targetgoods;
	}

	/**
	 * 浠诲姟鐩爣鐗╁搧 (鐗╁搧id#鐗╁搧鏁伴噺#鍦烘櫙id_x_y_璺濈),* t_task.f_targetGoods
	 * @param targetgoods  the value for t_task.f_targetGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetgoods(String targetgoods) {
		this.targetgoods = targetgoods;
	}

	/**
	 * 浠诲姟鐩爣绫诲瀷鎬�(鎬墿妯℃澘id#鏁伴噺#鍦烘櫙_x_y_璺濈洰鏍囧嚑姝ュ仠姝�,* t_task.f_targetMonster
	 * @return  the value of t_task.f_targetMonster
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetmonster() {
		return targetmonster;
	}

	/**
	 * 浠诲姟鐩爣绫诲瀷鎬�(鎬墿妯℃澘id#鏁伴噺#鍦烘櫙_x_y_璺濈洰鏍囧嚑姝ュ仠姝�,* t_task.f_targetMonster
	 * @param targetmonster  the value for t_task.f_targetMonster
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetmonster(String targetmonster) {
		this.targetmonster = targetmonster;
	}

	/**
	 * 鐩爣Npc (npcid),* t_task.f_targetNpc
	 * @return  the value of t_task.f_targetNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetnpc() {
		return targetnpc;
	}

	/**
	 * 鐩爣Npc (npcid),* t_task.f_targetNpc
	 * @param targetnpc  the value for t_task.f_targetNpc
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetnpc(String targetnpc) {
		this.targetnpc = targetnpc;
	}

	/**
	 * 鏍煎紡 浠ユ牸瀛愪负鍗曚綅 鍦烘櫙id t_task.f_targetArea
	 * @return  the value of t_task.f_targetArea
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetarea() {
		return targetarea;
	}

	/**
	 * 鏍煎紡 浠ユ牸瀛愪负鍗曚綅 鍦烘櫙id t_task.f_targetArea
	 * @param targetarea  the value for t_task.f_targetArea
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetarea(String targetarea) {
		this.targetarea = targetarea;
	}

	/**
	 * 鍔ㄤ綔瑙﹀彂 澶氫釜鍔ㄤ綔浠�鍒嗗壊  锛�缁勯槦锛�娣诲姞濂藉弸3浣跨敤鍠囧彮 4瑁呭鐗╁搧 5浣跨敤鎭㈠绫婚亾鍏�6閬撳叿鍚堟垚 7瀹濈煶闀跺祵 8 瑁呭淇悊 9涔板叆閬撳叿 10鍗栧嚭閬撳叿 11绉樼睄鍗囩骇 12灏嗘妧鑳�閬撳叿鎷栧叆蹇嵎鏍�) t_task.f_targetAction
	 * @return  the value of t_task.f_targetAction
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetaction() {
		return targetaction;
	}

	/**
	 * 鍔ㄤ綔瑙﹀彂 澶氫釜鍔ㄤ綔浠�鍒嗗壊  锛�缁勯槦锛�娣诲姞濂藉弸3浣跨敤鍠囧彮 4瑁呭鐗╁搧 5浣跨敤鎭㈠绫婚亾鍏�6閬撳叿鍚堟垚 7瀹濈煶闀跺祵 8 瑁呭淇悊 9涔板叆閬撳叿 10鍗栧嚭閬撳叿 11绉樼睄鍗囩骇 12灏嗘妧鑳�閬撳叿鎷栧叆蹇嵎鏍�) t_task.f_targetAction
	 * @param targetaction  the value for t_task.f_targetAction
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetaction(String targetaction) {
		this.targetaction = targetaction;
	}

	/**
	 * 瀹屾垚浠诲姟绛夌骇 t_task.f_endTaskLevel
	 * @return  the value of t_task.f_endTaskLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getEndtasklevel() {
		return endtasklevel;
	}

	/**
	 * 瀹屾垚浠诲姟绛夌骇 t_task.f_endTaskLevel
	 * @param endtasklevel  the value for t_task.f_endTaskLevel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setEndtasklevel(Integer endtasklevel) {
		this.endtasklevel = endtasklevel;
	}

	/**
	 * 鍗曚釜浠诲姟鏃堕棿闄愬埗浠ュ垎閽熶负鍗曚綅 -1涓烘病鏈夋椂闂撮檺鍒�t_task.f_limitTime
	 * @return  the value of t_task.f_limitTime
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Short getLimittime() {
		return limittime;
	}

	/**
	 * 鍗曚釜浠诲姟鏃堕棿闄愬埗浠ュ垎閽熶负鍗曚綅 -1涓烘病鏈夋椂闂撮檺鍒�t_task.f_limitTime
	 * @param limittime  the value for t_task.f_limitTime
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setLimittime(Short limittime) {
		this.limittime = limittime;
	}

	/**
	 * 濂栧姳缁忛獙(濂栧姳) t_task.f_experience
	 * @return  the value of t_task.f_experience
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * 濂栧姳缁忛獙(濂栧姳) t_task.f_experience
	 * @param experience  the value for t_task.f_experience
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * 濂栧姳閾滃竵(濂栧姳) t_task.f_copper
	 * @return  the value of t_task.f_copper
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 濂栧姳閾滃竵(濂栧姳) t_task.f_copper
	 * @param copper  the value for t_task.f_copper
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 濂栧姳閲戝竵(绀奸噾)(濂栧姳) t_task.f_gold
	 * @return  the value of t_task.f_gold
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getGold() {
		return gold;
	}

	/**
	 * 濂栧姳閲戝竵(绀奸噾)(濂栧姳) t_task.f_gold
	 * @param gold  the value for t_task.f_gold
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setGold(Integer gold) {
		this.gold = gold;
	}

	/**
	 * 鍥哄畾浠诲姟濂栧姳(鏁伴噺涓嶈兘澶т簬鐗╁搧涓婇檺)鏍煎紡 (鐗╁搧id#鏁伴噺#闂ㄦ淳id),*(濂栧姳) t_task.f_goods
	 * @return  the value of t_task.f_goods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getGoods() {
		return goods;
	}

	/**
	 * 鍥哄畾浠诲姟濂栧姳(鏁伴噺涓嶈兘澶т簬鐗╁搧涓婇檺)鏍煎紡 (鐗╁搧id#鏁伴噺#闂ㄦ淳id),*(濂栧姳) t_task.f_goods
	 * @param goods  the value for t_task.f_goods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * 浠诲姟鍙戣捣鎻忚堪 t_task.f_taskDes
	 * @return  the value of t_task.f_taskDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTaskdes() {
		return taskdes;
	}

	/**
	 * 浠诲姟鍙戣捣鎻忚堪 t_task.f_taskDes
	 * @param taskdes  the value for t_task.f_taskDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTaskdes(String taskdes) {
		this.taskdes = taskdes;
	}

	/**
	 * 鏈畬鎴愪换鍔℃弿杩�t_task.f_unEndDes
	 * @return  the value of t_task.f_unEndDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getUnenddes() {
		return unenddes;
	}

	/**
	 * 鏈畬鎴愪换鍔℃弿杩�t_task.f_unEndDes
	 * @param unenddes  the value for t_task.f_unEndDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setUnenddes(String unenddes) {
		this.unenddes = unenddes;
	}

	/**
	 * 瀹屾垚浠诲姟鎻忚堪 t_task.f_endDes
	 * @return  the value of t_task.f_endDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getEnddes() {
		return enddes;
	}

	/**
	 * 瀹屾垚浠诲姟鎻忚堪 t_task.f_endDes
	 * @param enddes  the value for t_task.f_endDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setEnddes(String enddes) {
		this.enddes = enddes;
	}

	/**
	 * 浠诲姟鎻忚堪 t_task.f_publishDes
	 * @return  the value of t_task.f_publishDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getPublishdes() {
		return publishdes;
	}

	/**
	 * 浠诲姟鎻忚堪 t_task.f_publishDes
	 * @param publishdes  the value for t_task.f_publishDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setPublishdes(String publishdes) {
		this.publishdes = publishdes;
	}

	/**
	 * 濂芥劅鍊�t_task.f_goodFeel
	 * @return  the value of t_task.f_goodFeel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getGoodfeel() {
		return goodfeel;
	}

	/**
	 * 濂芥劅鍊�t_task.f_goodFeel
	 * @param goodfeel  the value for t_task.f_goodFeel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setGoodfeel(Integer goodfeel) {
		this.goodfeel = goodfeel;
	}

	/**
	 * 甯淳璐＄尞(濂栧姳) t_task.f_party
	 * @return  the value of t_task.f_party
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getParty() {
		return party;
	}

	/**
	 * 甯淳璐＄尞(濂栧姳) t_task.f_party
	 * @param party  the value for t_task.f_party
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setParty(Integer party) {
		this.party = party;
	}

	/**
	 * 鍠勬伓鍊�t_task.f_goodBad
	 * @return  the value of t_task.f_goodBad
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getGoodbad() {
		return goodbad;
	}

	/**
	 * 鍠勬伓鍊�t_task.f_goodBad
	 * @param goodbad  the value for t_task.f_goodBad
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setGoodbad(Integer goodbad) {
		this.goodbad = goodbad;
	}

	/**
	 * 绉板彿id(濂栧姳) t_task.f_title
	 * @return  the value of t_task.f_title
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getTitle() {
		return title;
	}

	/**
	 * 绉板彿id(濂栧姳) t_task.f_title
	 * @param title  the value for t_task.f_title
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTitle(Integer title) {
		this.title = title;
	}

	/**
	 * 鎺ュ彈浠诲姟鏃惰幏寰楃殑浠诲姟鍝侊紙涓嶅彲鍙犲姞鐨勭墿鍝侊級鏍煎紡 鐗╁搧id:浜や换鍔℃椂鏄惁绯荤粺鏀跺洖(0/1)闆朵负涓嶆敹鍥�1鏀跺洖 t_task.f_taskGoods
	 * @return  the value of t_task.f_taskGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTaskgoods() {
		return taskgoods;
	}

	/**
	 * 鎺ュ彈浠诲姟鏃惰幏寰楃殑浠诲姟鍝侊紙涓嶅彲鍙犲姞鐨勭墿鍝侊級鏍煎紡 鐗╁搧id:浜や换鍔℃椂鏄惁绯荤粺鏀跺洖(0/1)闆朵负涓嶆敹鍥�1鏀跺洖 t_task.f_taskGoods
	 * @param taskgoods  the value for t_task.f_taskGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTaskgoods(String taskgoods) {
		this.taskgoods = taskgoods;
	}

	/**
	 * 浠诲姟瀹屾垚浠绘椂鍙栬蛋鐨勭墿鍝両D t_task.f_takeGoods
	 * @return  the value of t_task.f_takeGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getTakegoods() {
		return takegoods;
	}

	/**
	 * 浠诲姟瀹屾垚浠绘椂鍙栬蛋鐨勭墿鍝両D t_task.f_takeGoods
	 * @param takegoods  the value for t_task.f_takeGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTakegoods(Integer takegoods) {
		this.takegoods = takegoods;
	}

	/**
	 * 濂栧姳鎴樺満澹版湜(濂栧姳) t_task.f_warrepute
	 * @return  the value of t_task.f_warrepute
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getWarrepute() {
		return warrepute;
	}

	/**
	 * 濂栧姳鎴樺満澹版湜(濂栧姳) t_task.f_warrepute
	 * @param warrepute  the value for t_task.f_warrepute
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setWarrepute(Integer warrepute) {
		this.warrepute = warrepute;
	}

	/**
	 * 鍔卞鐨刡uffID(濂栧姳) t_task.f_buffid
	 * @return  the value of t_task.f_buffid
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getBuffid() {
		return buffid;
	}

	/**
	 * 鍔卞鐨刡uffID(濂栧姳) t_task.f_buffid
	 * @param buffid  the value for t_task.f_buffid
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setBuffid(Integer buffid) {
		this.buffid = buffid;
	}

	/**
	 * 濂栧姳鐨勫叏鏈嶅叕鍛�濂栧姳) t_task.f_bulletin
	 * @return  the value of t_task.f_bulletin
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getBulletin() {
		return bulletin;
	}

	/**
	 * 濂栧姳鐨勫叏鏈嶅叕鍛�濂栧姳) t_task.f_bulletin
	 * @param bulletin  the value for t_task.f_bulletin
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setBulletin(Integer bulletin) {
		this.bulletin = bulletin;
	}

	/**
	 * 濂栧姳鐨勭湡姘旈噺(濂栧姳) t_task.f_zhenqi
	 * @return  the value of t_task.f_zhenqi
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getZhenqi() {
		return zhenqi;
	}

	/**
	 * 濂栧姳鐨勭湡姘旈噺(濂栧姳) t_task.f_zhenqi
	 * @param zhenqi  the value for t_task.f_zhenqi
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setZhenqi(Integer zhenqi) {
		this.zhenqi = zhenqi;
	}

	/**
	 * 鐜换鍔＄殑闅惧害 t_task.f_loopTaskDifficulty
	 * @return  the value of t_task.f_loopTaskDifficulty
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getLooptaskdifficulty() {
		return looptaskdifficulty;
	}

	/**
	 * 鐜换鍔＄殑闅惧害 t_task.f_loopTaskDifficulty
	 * @param looptaskdifficulty  the value for t_task.f_loopTaskDifficulty
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setLooptaskdifficulty(Integer looptaskdifficulty) {
		this.looptaskdifficulty = looptaskdifficulty;
	}

	/**
	 * 鐜换鍔″鍔辩殑涓板帤搴�t_task.f_loopTaskBonus
	 * @return  the value of t_task.f_loopTaskBonus
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getLooptaskbonus() {
		return looptaskbonus;
	}

	/**
	 * 鐜换鍔″鍔辩殑涓板帤搴�t_task.f_loopTaskBonus
	 * @param looptaskbonus  the value for t_task.f_loopTaskBonus
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setLooptaskbonus(Integer looptaskbonus) {
		this.looptaskbonus = looptaskbonus;
	}

	/**
	 * 浠诲姟鏉′欢锛氭崟鑾峰潗楠慖D (鍧愰獞id#鏁伴噺#鍦烘櫙_x_y_璺濈) t_task.f_targetHorse
	 * @return  the value of t_task.f_targetHorse
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargethorse() {
		return targethorse;
	}

	/**
	 * 浠诲姟鏉′欢锛氭崟鑾峰潗楠慖D (鍧愰獞id#鏁伴噺#鍦烘櫙_x_y_璺濈) t_task.f_targetHorse
	 * @param targethorse  the value for t_task.f_targetHorse
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargethorse(String targethorse) {
		this.targethorse = targethorse;
	}

	/**
	 * 閬撳叿瑙﹀彂浠诲姟(閬撳叿id) t_task.f_triggerGoods
	 * @return  the value of t_task.f_triggerGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getTriggergoods() {
		return triggergoods;
	}

	/**
	 * 閬撳叿瑙﹀彂浠诲姟(閬撳叿id) t_task.f_triggerGoods
	 * @param triggergoods  the value for t_task.f_triggerGoods
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTriggergoods(Integer triggergoods) {
		this.triggergoods = triggergoods;
	}

	/**
	 * 闄烽槺瑙﹀彂浠诲姟(鍦烘櫙id_x_y_璺濈) t_task.f_triggerScene
	 * @return  the value of t_task.f_triggerScene
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTriggerscene() {
		return triggerscene;
	}

	/**
	 * 闄烽槺瑙﹀彂浠诲姟(鍦烘櫙id_x_y_璺濈) t_task.f_triggerScene
	 * @param triggerscene  the value for t_task.f_triggerScene
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTriggerscene(String triggerscene) {
		this.triggerscene = triggerscene;
	}

	/**
	 * 鍙栬蛋鐜╁鐨勯摐甯�t_task.f_takecopper
	 * @return  the value of t_task.f_takecopper
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getTakecopper() {
		return takecopper;
	}

	/**
	 * 鍙栬蛋鐜╁鐨勯摐甯�t_task.f_takecopper
	 * @param takecopper  the value for t_task.f_takecopper
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTakecopper(Integer takecopper) {
		this.takecopper = takecopper;
	}

	/**
	 * 鍓嶇疆浠诲姟鎺掑簭鐢╥d(鏆傛椂娌＄敤) t_task.f_premiseId
	 * @return  the value of t_task.f_premiseId
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getPremiseid() {
		return premiseid;
	}

	/**
	 * 鍓嶇疆浠诲姟鎺掑簭鐢╥d(鏆傛椂娌＄敤) t_task.f_premiseId
	 * @param premiseid  the value for t_task.f_premiseId
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setPremiseid(Integer premiseid) {
		this.premiseid = premiseid;
	}

	/**
	 * 鍓嶆彁浠诲姟id t_task.f_premiseTask
	 * @return  the value of t_task.f_premiseTask
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getPremisetask() {
		return premisetask;
	}

	/**
	 * 鍓嶆彁浠诲姟id t_task.f_premiseTask
	 * @param premisetask  the value for t_task.f_premiseTask
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setPremisetask(Integer premisetask) {
		this.premisetask = premisetask;
	}

	/**
	 * 宸ュ叿鐢ㄤ簬璺熻釜 鎻掑叆/淇敼鏃堕棿 t_task.f_time
	 * @return  the value of t_task.f_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 宸ュ叿鐢ㄤ簬璺熻釜 鎻掑叆/淇敼鏃堕棿 t_task.f_time
	 * @param time  the value for t_task.f_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * 0涓嶆墸鍧愰獞1鎵ｅ潗楠�t_task.f_horseDrop
	 * @return  the value of t_task.f_horseDrop
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getHorsedrop() {
		return horsedrop;
	}

	/**
	 * 0涓嶆墸鍧愰獞1鎵ｅ潗楠�t_task.f_horseDrop
	 * @param horsedrop  the value for t_task.f_horseDrop
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setHorsedrop(Integer horsedrop) {
		this.horsedrop = horsedrop;
	}

	/**
	 * (韬笂鏄惁鏈夌┛鎴存鍣�鏈夋病鏈変僵鎴存鍣�t_task.f_needWuqi
	 * @return  the value of t_task.f_needWuqi
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getNeedwuqi() {
		return needwuqi;
	}

	/**
	 * (韬笂鏄惁鏈夌┛鎴存鍣�鏈夋病鏈変僵鎴存鍣�t_task.f_needWuqi
	 * @param needwuqi  the value for t_task.f_needWuqi
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setNeedwuqi(Integer needwuqi) {
		this.needwuqi = needwuqi;
	}

	/**
	 * (韬笂鏄惁鏈夌┛鎴磋。鏈�鏈夋病鏈変僵鎴磋。鏈�t_task.f_needDress
	 * @return  the value of t_task.f_needDress
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getNeeddress() {
		return needdress;
	}

	/**
	 * (韬笂鏄惁鏈夌┛鎴磋。鏈�鏈夋病鏈変僵鎴磋。鏈�t_task.f_needDress
	 * @param needdress  the value for t_task.f_needDress
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setNeeddress(Integer needdress) {
		this.needdress = needdress;
	}

	/**
	 * (闇�宸插涔犵殑姝﹀姛id) 鏈夋病鏈夊浼氭鍔�t_task.f_needSkill
	 * @return  the value of t_task.f_needSkill
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getNeedskill() {
		return needskill;
	}

	/**
	 * (闇�宸插涔犵殑姝﹀姛id) 鏈夋病鏈夊浼氭鍔�t_task.f_needSkill
	 * @param needskill  the value for t_task.f_needSkill
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setNeedskill(Integer needskill) {
		this.needskill = needskill;
	}

	/**
	 * (闄愬畾鎬荤殑姝﹀姛绛夌骇)姝﹀姛灞傛暟鏄惁杈惧埌 t_task.f_needWugongGrade
	 * @return  the value of t_task.f_needWugongGrade
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getNeedwugonggrade() {
		return needwugonggrade;
	}

	/**
	 * (闄愬畾鎬荤殑姝﹀姛绛夌骇)姝﹀姛灞傛暟鏄惁杈惧埌 t_task.f_needWugongGrade
	 * @param needwugonggrade  the value for t_task.f_needWugongGrade
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setNeedwugonggrade(Integer needwugonggrade) {
		this.needwugonggrade = needwugonggrade;
	}

	/**
	 * 浠诲姟鐩爣鎻忚堪 t_task.f_taskTargetDes
	 * @return  the value of t_task.f_taskTargetDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTasktargetdes() {
		return tasktargetdes;
	}

	/**
	 * 浠诲姟鐩爣鎻忚堪 t_task.f_taskTargetDes
	 * @param tasktargetdes  the value for t_task.f_taskTargetDes
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTasktargetdes(String tasktargetdes) {
		this.tasktargetdes = tasktargetdes;
	}

	/**
	 * 鍒ゆ柇鐜╁鏄惁浠庡晢鍩庝腑璐拱杩囨煇涓墿鍝侊紙鐗╁搧鍙嚜瀹氫箟杈撳叆锛�(閫楀彿鐩搁殧鐨勯亾鍏稩D#鏁伴噺) t_task.f_targetShopping
	 * @return  the value of t_task.f_targetShopping
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetshopping() {
		return targetshopping;
	}

	/**
	 * 鍒ゆ柇鐜╁鏄惁浠庡晢鍩庝腑璐拱杩囨煇涓墿鍝侊紙鐗╁搧鍙嚜瀹氫箟杈撳叆锛�(閫楀彿鐩搁殧鐨勯亾鍏稩D#鏁伴噺) t_task.f_targetShopping
	 * @param targetshopping  the value for t_task.f_targetShopping
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetshopping(String targetshopping) {
		this.targetshopping = targetshopping;
	}

	/**
	 * 鍏呭�閲戦(鍒ゆ柇鐜╁鏄惁鍏呭�杩�(鍗曚綅鍏冨疂) t_task.f_targetRecharge
	 * @return  the value of t_task.f_targetRecharge
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetrecharge() {
		return targetrecharge;
	}

	/**
	 * 鍏呭�閲戦(鍒ゆ柇鐜╁鏄惁鍏呭�杩�(鍗曚綅鍏冨疂) t_task.f_targetRecharge
	 * @param targetrecharge  the value for t_task.f_targetRecharge
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetrecharge(String targetrecharge) {
		this.targetrecharge = targetrecharge;
	}

	/**
	 * 鍒ゆ柇鐜╁鏄惁浣跨敤杩囧潗楠戣繘鏀绘湰鍔熻兘锛氬皢鐨勫崲椹垚鍔熷湴杩涢樁鍚堟垚涓洪噾鐢茬鐗�1涓洪渶杩涢樁0涓嶉渶杩涢樁) t_task.f_targetMountUpgrade
	 * @return  the value of t_task.f_targetMountUpgrade
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetmountupgrade() {
		return targetmountupgrade;
	}

	/**
	 * 鍒ゆ柇鐜╁鏄惁浣跨敤杩囧潗楠戣繘鏀绘湰鍔熻兘锛氬皢鐨勫崲椹垚鍔熷湴杩涢樁鍚堟垚涓洪噾鐢茬鐗�1涓洪渶杩涢樁0涓嶉渶杩涢樁) t_task.f_targetMountUpgrade
	 * @param targetmountupgrade  the value for t_task.f_targetMountUpgrade
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetmountupgrade(String targetmountupgrade) {
		this.targetmountupgrade = targetmountupgrade;
	}

	/**
	 * 鍒ゆ柇鐜╁褰撳墠鏄惁缁勯槦锛岄槦浼嶆�浜烘暟鏄惁>X(鏍煎紡:"鐩爣闃熶紞浜烘暟" 鎴�"") t_task.f_targetGroup
	 * @return  the value of t_task.f_targetGroup
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetgroup() {
		return targetgroup;
	}

	/**
	 * 鍒ゆ柇鐜╁褰撳墠鏄惁缁勯槦锛岄槦浼嶆�浜烘暟鏄惁>X(鏍煎紡:"鐩爣闃熶紞浜烘暟" 鎴�"") t_task.f_targetGroup
	 * @param targetgroup  the value for t_task.f_targetGroup
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetgroup(String targetgroup) {
		this.targetgroup = targetgroup;
	}

	/**
	 * 鍒ゆ柇鐜╁褰撳墠鎷ユ湁濂藉弸浜烘暟鐨勬暟閲忔槸鍚�X(鏍煎紡:"鐩爣濂藉弸浜烘暟" 鎴�"") t_task.f_targetFriend
	 * @return  the value of t_task.f_targetFriend
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetfriend() {
		return targetfriend;
	}

	/**
	 * 鍒ゆ柇鐜╁褰撳墠鎷ユ湁濂藉弸浜烘暟鐨勬暟閲忔槸鍚�X(鏍煎紡:"鐩爣濂藉弸浜烘暟" 鎴�"") t_task.f_targetFriend
	 * @param targetfriend  the value for t_task.f_targetFriend
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetfriend(String targetfriend) {
		this.targetfriend = targetfriend;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愪釜閮ㄤ綅鏄惁浣╂埓浜嗚澶嘔D(鏍煎紡:"鐩爣閮ㄤ綅ID#鐩爣瑁呭ID" 鎴�"") t_task.f_targetEquip
	 * @return  the value of t_task.f_targetEquip
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetequip() {
		return targetequip;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愪釜閮ㄤ綅鏄惁浣╂埓浜嗚澶嘔D(鏍煎紡:"鐩爣閮ㄤ綅ID#鐩爣瑁呭ID" 鎴�"") t_task.f_targetEquip
	 * @param targetequip  the value for t_task.f_targetEquip
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetequip(String targetequip) {
		this.targetequip = targetequip;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愪釜姝﹀姛鐨勭瓑绾ф槸鍚﹀ぇ浜嶺(鏍煎紡:"鐩爣姝﹀姛ID#鐩爣绛夌骇" 鎴�"") t_task.f_targetSkillLv
	 * @return  the value of t_task.f_targetSkillLv
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetskilllv() {
		return targetskilllv;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愪釜姝﹀姛鐨勭瓑绾ф槸鍚﹀ぇ浜嶺(鏍煎紡:"鐩爣姝﹀姛ID#鐩爣绛夌骇" 鎴�"") t_task.f_targetSkillLv
	 * @param targetskilllv  the value for t_task.f_targetSkillLv
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetskilllv(String targetskilllv) {
		this.targetskilllv = targetskilllv;
	}

	/**
	 * 鍒ゆ柇鐜╁鏁翠綋姝﹀姛澧冪晫灞傛暟鏄惁澶т簬X(鏍煎紡:"鐩爣绛夌骇" 鎴�"") t_task.f_targetAllSkillLv
	 * @return  the value of t_task.f_targetAllSkillLv
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetallskilllv() {
		return targetallskilllv;
	}

	/**
	 * 鍒ゆ柇鐜╁鏁翠綋姝﹀姛澧冪晫灞傛暟鏄惁澶т簬X(鏍煎紡:"鐩爣绛夌骇" 鎴�"") t_task.f_targetAllSkillLv
	 * @param targetallskilllv  the value for t_task.f_targetAllSkillLv
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetallskilllv(String targetallskilllv) {
		this.targetallskilllv = targetallskilllv;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愪釜缁忚剦绌翠綅鏄惁鍐查�(鏍煎紡:"鐩爣绌翠綅ID" 鎴�"") t_task.f_targetPoint
	 * @return  the value of t_task.f_targetPoint
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetpoint() {
		return targetpoint;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愪釜缁忚剦绌翠綅鏄惁鍐查�(鏍煎紡:"鐩爣绌翠綅ID" 鎴�"") t_task.f_targetPoint
	 * @param targetpoint  the value for t_task.f_targetPoint
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetpoint(String targetpoint) {
		this.targetpoint = targetpoint;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愭潯缁忚剦鏄惁鍐查�(鏍煎紡:"鐩爣缁忚剦ID" 鎴�"") t_task.f_targetChannel
	 * @return  the value of t_task.f_targetChannel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetchannel() {
		return targetchannel;
	}

	/**
	 * 鍒ゆ柇鐜╁鏌愭潯缁忚剦鏄惁鍐查�(鏍煎紡:"鐩爣缁忚剦ID" 鎴�"") t_task.f_targetChannel
	 * @param targetchannel  the value for t_task.f_targetChannel
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetchannel(String targetchannel) {
		this.targetchannel = targetchannel;
	}

	/**
	 * 鍒ゆ柇鐜╁韬笂鏄惁鏈夋煇涓狟UFF-ID(鏍煎紡:"鐩爣BUFF ID" 鎴�"") t_task.f_targetBuff
	 * @return  the value of t_task.f_targetBuff
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTargetbuff() {
		return targetbuff;
	}

	/**
	 * 鍒ゆ柇鐜╁韬笂鏄惁鏈夋煇涓狟UFF-ID(鏍煎紡:"鐩爣BUFF ID" 鎴�"") t_task.f_targetBuff
	 * @param targetbuff  the value for t_task.f_targetBuff
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTargetbuff(String targetbuff) {
		this.targetbuff = targetbuff;
	}

	/**
	 * 鐜换鍔℃渶澶т笂闄愭鏁�t_task.f_loopMaxCount
	 * @return  the value of t_task.f_loopMaxCount
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getLoopmaxcount() {
		return loopmaxcount;
	}

	/**
	 * 鐜换鍔℃渶澶т笂闄愭鏁�t_task.f_loopMaxCount
	 * @param loopmaxcount  the value for t_task.f_loopMaxCount
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setLoopmaxcount(Integer loopmaxcount) {
		this.loopmaxcount = loopmaxcount;
	}

	/**
	 * 濂栧姳鏆楀櫒(鏆楀櫒id) t_task.f_hiddenWeapon
	 * @return  the value of t_task.f_hiddenWeapon
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Integer getHiddenweapon() {
		return hiddenweapon;
	}

	/**
	 * 濂栧姳鏆楀櫒(鏆楀櫒id) t_task.f_hiddenWeapon
	 * @param hiddenweapon  the value for t_task.f_hiddenWeapon
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setHiddenweapon(Integer hiddenweapon) {
		this.hiddenweapon = hiddenweapon;
	}

	/**
	 * 鎺ヤ换鍔″紑濮嬫椂闂�t_task.f_accept_begin_time
	 * @return  the value of t_task.f_accept_begin_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Date getAcceptBeginTime() {
		return acceptBeginTime;
	}

	/**
	 * 鎺ヤ换鍔″紑濮嬫椂闂�t_task.f_accept_begin_time
	 * @param acceptBeginTime  the value for t_task.f_accept_begin_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setAcceptBeginTime(Date acceptBeginTime) {
		this.acceptBeginTime = acceptBeginTime;
	}

	/**
	 * 浜や换鍔＄粨鏉熸椂闂�t_task.f_complete_end_time
	 * @return  the value of t_task.f_complete_end_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public Date getCompleteEndTime() {
		return completeEndTime;
	}

	/**
	 * 浜や换鍔＄粨鏉熸椂闂�t_task.f_complete_end_time
	 * @param completeEndTime  the value for t_task.f_complete_end_time
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setCompleteEndTime(Date completeEndTime) {
		this.completeEndTime = completeEndTime;
	}

	/**
	 * 浠诲姟鍚嶇О鍥介檯鍖�t_task.f_name_i18n
	 * @return  the value of t_task.f_name_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 浠诲姟鍚嶇О鍥介檯鍖�t_task.f_name_i18n
	 * @param nameI18n  the value for t_task.f_name_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 鏈畬鎴愪换鍔℃弿杩伴檯鍖栧浗 t_task.f_unEndDes_i18n
	 * @return  the value of t_task.f_unEndDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getUnenddesI18n() {
		return unenddesI18n;
	}

	/**
	 * 鏈畬鎴愪换鍔℃弿杩伴檯鍖栧浗 t_task.f_unEndDes_i18n
	 * @param unenddesI18n  the value for t_task.f_unEndDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setUnenddesI18n(String unenddesI18n) {
		this.unenddesI18n = unenddesI18n;
	}

	/**
	 * 瀹屾垚浠诲姟鎻忚堪鍥介檯鍖�t_task.f_endDes_i18n
	 * @return  the value of t_task.f_endDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getEnddesI18n() {
		return enddesI18n;
	}

	/**
	 * 瀹屾垚浠诲姟鎻忚堪鍥介檯鍖�t_task.f_endDes_i18n
	 * @param enddesI18n  the value for t_task.f_endDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setEnddesI18n(String enddesI18n) {
		this.enddesI18n = enddesI18n;
	}

	/**
	 * 浠诲姟鎻忚堪 t_task.f_publishDes_i18n
	 * @return  the value of t_task.f_publishDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getPublishdesI18n() {
		return publishdesI18n;
	}

	/**
	 * 浠诲姟鎻忚堪 t_task.f_publishDes_i18n
	 * @param publishdesI18n  the value for t_task.f_publishDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setPublishdesI18n(String publishdesI18n) {
		this.publishdesI18n = publishdesI18n;
	}

	/**
	 * 浠诲姟鐩爣鎻忚堪鍥介檯鍖�t_task.f_taskTargetDes_i18n
	 * @return  the value of t_task.f_taskTargetDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public String getTasktargetdesI18n() {
		return tasktargetdesI18n;
	}

	/**
	 * 浠诲姟鐩爣鎻忚堪鍥介檯鍖�t_task.f_taskTargetDes_i18n
	 * @param tasktargetdesI18n  the value for t_task.f_taskTargetDes_i18n
	 * @ibatorgenerated  Fri May 06 17:25:38 CST 2011
	 */
	public void setTasktargetdesI18n(String tasktargetdesI18n) {
		this.tasktargetdesI18n = tasktargetdesI18n;
	}

	private long completeEndTime2;
	private long acceptBeginTime2;
	public long getCompleteEndTime2() {
		return getCompleteEndTime()==null?0:getCompleteEndTime().getTime();
	}

	public long getAcceptBeginTime2() {
		return getAcceptBeginTime()==null?0:getAcceptBeginTime().getTime();
	}
}
