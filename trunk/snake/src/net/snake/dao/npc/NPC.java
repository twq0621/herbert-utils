package net.snake.dao.npc;

public class NPC {

	/**
	 * 涓婚敭 t_npc.f_id
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer id;
	/**
	 * x鍧愭爣 t_npc.f_x
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer x;
	/**
	 * y鍧愭爣 t_npc.f_y
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer y;
	/**
	 * 鍦烘櫙id t_npc.f_sceneid
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer sceneid;
	/**
	 * npc鍚嶅瓧 濡傚紶涓�t_npc.f_name
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String name;
	/**
	 * NPC绉板彿 濡�灞犲か t_npc.f_nickname
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String nickname;
	/**
	 * 娌℃湁浜や簰鐨勭畝鍗曞璇�t_npc.f_simpleTalk
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String simpletalk;
	/**
	 * npc 鍥剧墖璧勬簮妯℃澘 t_npc.f_npcmodel
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String npcmodel;
	/**
	 * npc鐨勫ご鍍忓浘鏍囪矾寰�t_npc.f_npc_icon
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer npcIcon;
	/**
	 * npc澶у浘鏍�t_npc.f_npc_big_icon
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer npcBigIcon;
	/**
	 * npc 绫诲瀷 1鍔熻兘 2 浠诲姟 3鍟嗕笟 t_npc.f_npctype
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Short npctype;
	/**
	 * npc鍔熻兘鍒楄〃,閫楀彿鍒嗗紑,鎸夊姛鑳芥帓搴�1浠诲姟鍔熻兘-2NPC鍟嗗簵-3甯細鍔熻兘-4鍏崇郴鍔熻兘锛堝か濡汇�甯堝緬锛�5鍚堟垚鍔熻兘锛堝疂鐭炽�瑁呭銆佽嵂鍝侊級-6瑁呭寮哄寲鍔熻兘锛堥暥宓屻�鎵撳瓟銆佹媶鍒嗭級-7浼犻�鍔熻兘-8浠撳簱鍔熻兘-9鎷嶅崠鍔熻兘,- t_npc.f_functions
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String functions;
	/**
	 * 浠诲姟鍙傛暟 t_npc.f_function_task
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String functionTask;
	/**
	 * 鍟嗗簵鎵�崠鐗╁搧鍒楄〃,鐢ㄩ�鍙烽殧寮�t_npc.f_function_shop
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String functionShop;
	/**
	 * 浼犻� t_npc.f_function_transfer
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String functionTransfer;
	/**
	 * 鍟嗗簵绫诲瀷1姝﹀櫒搴楋紝2鎶ゅ叿搴楋紝3楗板搧搴楋紝4鑽搧搴楋紝5鏉傝揣搴楋紝6鎴樺満澹版湜搴�t_npc.f_shop_type
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Byte shopType;
	/**
	 * 鐗瑰畾鐨勫璺痻鍧愭爣 t_npc.f_findX
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer findx;
	/**
	 * 鐗瑰畾鐨勫璺痻鍧愭爣 t_npc.f_findY
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer findy;
	/**
	 * npc鐨勫垵濮嬫柟鍚�0-7椤烘椂閽� 0鏄�鐐归挓鏂瑰悜) t_npc.f_direction
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer direction;
	/**
	 * 鏀诲嚮鏃跺０闊崇紪鍙�t_npc.f_attack_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer attackAudio;
	/**
	 * 琚敾鍑荤殑澹伴煶 t_npc.f_hurt_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer hurtAudio;
	/**
	 * 姝讳骸鏃剁殑澹伴煶缂栧彿 t_npc.f_die_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer dieAudio;
	/**
	 * 鍛ㄧ幆浠诲姟浠庤繖涓瓧娈靛紑濮嬬殑绛夌骇闄愬埗锛堢敤浜庨殢鏈洪�鎷╃洰鏍噉pc锛屽彇浠ndNpc锛�t_npc.f_min_grade
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer minGrade;
	/**
	 * 鍛ㄧ幆浠诲姟浠庤繖涓瓧娈电粨鏉熺殑绛夌骇闄愬埗锛堢敤浜庨殢鏈洪�鎷╃洰鏍噉pc锛屽彇浠ndNpc锛�t_npc.f_max_grade
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer maxGrade;
	/**
	 * npc 鍚︽槸鍙浆鍚�1鍙浆鍚�0涓嶅彲 t_npc.f_can_redirect
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private Integer canRedirect;
	/**
	 * npc闅忔満璇磋瘽鏍煎紡鍚宖_simpleTalk t_npc.f_randomTalk
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String randomtalk;
	/**
	 * npc鍚嶅瓧 濡傚紶涓�鍥介檯鍖�t_npc.f_name_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String nameI18n;
	/**
	 * NPC绉板彿 濡�灞犲か 鍥介檯鍖�t_npc.f_nickname_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String nicknameI18n;
	/**
	 * 娌℃湁浜や簰鐨勭畝鍗曞璇�鍥介檯鍖�t_npc.f_simpleTalk_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	private String simpletalkI18n;

	/**
	 * 涓婚敭 t_npc.f_id
	 * @return  the value of t_npc.f_id
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 涓婚敭 t_npc.f_id
	 * @param id  the value for t_npc.f_id
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * x鍧愭爣 t_npc.f_x
	 * @return  the value of t_npc.f_x
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * x鍧愭爣 t_npc.f_x
	 * @param x  the value for t_npc.f_x
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * y鍧愭爣 t_npc.f_y
	 * @return  the value of t_npc.f_y
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * y鍧愭爣 t_npc.f_y
	 * @param y  the value for t_npc.f_y
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setY(Integer y) {
		this.y = y;
	}

	/**
	 * 鍦烘櫙id t_npc.f_sceneid
	 * @return  the value of t_npc.f_sceneid
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getSceneid() {
		return sceneid;
	}

	/**
	 * 鍦烘櫙id t_npc.f_sceneid
	 * @param sceneid  the value for t_npc.f_sceneid
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setSceneid(Integer sceneid) {
		this.sceneid = sceneid;
	}

	/**
	 * npc鍚嶅瓧 濡傚紶涓�t_npc.f_name
	 * @return  the value of t_npc.f_name
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * npc鍚嶅瓧 濡傚紶涓�t_npc.f_name
	 * @param name  the value for t_npc.f_name
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * NPC绉板彿 濡�灞犲か t_npc.f_nickname
	 * @return  the value of t_npc.f_nickname
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * NPC绉板彿 濡�灞犲か t_npc.f_nickname
	 * @param nickname  the value for t_npc.f_nickname
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 娌℃湁浜や簰鐨勭畝鍗曞璇�t_npc.f_simpleTalk
	 * @return  the value of t_npc.f_simpleTalk
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getSimpletalk() {
		return simpletalk;
	}

	/**
	 * 娌℃湁浜や簰鐨勭畝鍗曞璇�t_npc.f_simpleTalk
	 * @param simpletalk  the value for t_npc.f_simpleTalk
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setSimpletalk(String simpletalk) {
		this.simpletalk = simpletalk;
	}

	/**
	 * npc 鍥剧墖璧勬簮妯℃澘 t_npc.f_npcmodel
	 * @return  the value of t_npc.f_npcmodel
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getNpcmodel() {
		return npcmodel;
	}

	/**
	 * npc 鍥剧墖璧勬簮妯℃澘 t_npc.f_npcmodel
	 * @param npcmodel  the value for t_npc.f_npcmodel
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNpcmodel(String npcmodel) {
		this.npcmodel = npcmodel;
	}

	/**
	 * npc鐨勫ご鍍忓浘鏍囪矾寰�t_npc.f_npc_icon
	 * @return  the value of t_npc.f_npc_icon
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getNpcIcon() {
		return npcIcon;
	}

	/**
	 * npc鐨勫ご鍍忓浘鏍囪矾寰�t_npc.f_npc_icon
	 * @param npcIcon  the value for t_npc.f_npc_icon
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNpcIcon(Integer npcIcon) {
		this.npcIcon = npcIcon;
	}

	/**
	 * npc澶у浘鏍�t_npc.f_npc_big_icon
	 * @return  the value of t_npc.f_npc_big_icon
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getNpcBigIcon() {
		return npcBigIcon;
	}

	/**
	 * npc澶у浘鏍�t_npc.f_npc_big_icon
	 * @param npcBigIcon  the value for t_npc.f_npc_big_icon
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNpcBigIcon(Integer npcBigIcon) {
		this.npcBigIcon = npcBigIcon;
	}

	/**
	 * npc 绫诲瀷 1鍔熻兘 2 浠诲姟 3鍟嗕笟 t_npc.f_npctype
	 * @return  the value of t_npc.f_npctype
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Short getNpctype() {
		return npctype;
	}

	/**
	 * npc 绫诲瀷 1鍔熻兘 2 浠诲姟 3鍟嗕笟 t_npc.f_npctype
	 * @param npctype  the value for t_npc.f_npctype
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNpctype(Short npctype) {
		this.npctype = npctype;
	}

	/**
	 * npc鍔熻兘鍒楄〃,閫楀彿鍒嗗紑,鎸夊姛鑳芥帓搴�1浠诲姟鍔熻兘-2NPC鍟嗗簵-3甯細鍔熻兘-4鍏崇郴鍔熻兘锛堝か濡汇�甯堝緬锛�5鍚堟垚鍔熻兘锛堝疂鐭炽�瑁呭銆佽嵂鍝侊級-6瑁呭寮哄寲鍔熻兘锛堥暥宓屻�鎵撳瓟銆佹媶鍒嗭級-7浼犻�鍔熻兘-8浠撳簱鍔熻兘-9鎷嶅崠鍔熻兘,- t_npc.f_functions
	 * @return  the value of t_npc.f_functions
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getFunctions() {
		return functions;
	}

	/**
	 * npc鍔熻兘鍒楄〃,閫楀彿鍒嗗紑,鎸夊姛鑳芥帓搴�1浠诲姟鍔熻兘-2NPC鍟嗗簵-3甯細鍔熻兘-4鍏崇郴鍔熻兘锛堝か濡汇�甯堝緬锛�5鍚堟垚鍔熻兘锛堝疂鐭炽�瑁呭銆佽嵂鍝侊級-6瑁呭寮哄寲鍔熻兘锛堥暥宓屻�鎵撳瓟銆佹媶鍒嗭級-7浼犻�鍔熻兘-8浠撳簱鍔熻兘-9鎷嶅崠鍔熻兘,- t_npc.f_functions
	 * @param functions  the value for t_npc.f_functions
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setFunctions(String functions) {
		this.functions = functions;
	}

	/**
	 * 浠诲姟鍙傛暟 t_npc.f_function_task
	 * @return  the value of t_npc.f_function_task
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getFunctionTask() {
		return functionTask;
	}

	/**
	 * 浠诲姟鍙傛暟 t_npc.f_function_task
	 * @param functionTask  the value for t_npc.f_function_task
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setFunctionTask(String functionTask) {
		this.functionTask = functionTask;
	}

	/**
	 * 鍟嗗簵鎵�崠鐗╁搧鍒楄〃,鐢ㄩ�鍙烽殧寮�t_npc.f_function_shop
	 * @return  the value of t_npc.f_function_shop
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getFunctionShop() {
		return functionShop;
	}

	/**
	 * 鍟嗗簵鎵�崠鐗╁搧鍒楄〃,鐢ㄩ�鍙烽殧寮�t_npc.f_function_shop
	 * @param functionShop  the value for t_npc.f_function_shop
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setFunctionShop(String functionShop) {
		this.functionShop = functionShop;
	}

	/**
	 * 浼犻� t_npc.f_function_transfer
	 * @return  the value of t_npc.f_function_transfer
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getFunctionTransfer() {
		return functionTransfer;
	}

	/**
	 * 浼犻� t_npc.f_function_transfer
	 * @param functionTransfer  the value for t_npc.f_function_transfer
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setFunctionTransfer(String functionTransfer) {
		this.functionTransfer = functionTransfer;
	}

	/**
	 * 鍟嗗簵绫诲瀷1姝﹀櫒搴楋紝2鎶ゅ叿搴楋紝3楗板搧搴楋紝4鑽搧搴楋紝5鏉傝揣搴楋紝6鎴樺満澹版湜搴�t_npc.f_shop_type
	 * @return  the value of t_npc.f_shop_type
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Byte getShopType() {
		return shopType;
	}

	/**
	 * 鍟嗗簵绫诲瀷1姝﹀櫒搴楋紝2鎶ゅ叿搴楋紝3楗板搧搴楋紝4鑽搧搴楋紝5鏉傝揣搴楋紝6鎴樺満澹版湜搴�t_npc.f_shop_type
	 * @param shopType  the value for t_npc.f_shop_type
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setShopType(Byte shopType) {
		this.shopType = shopType;
	}

	/**
	 * 鐗瑰畾鐨勫璺痻鍧愭爣 t_npc.f_findX
	 * @return  the value of t_npc.f_findX
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getFindx() {
		return findx;
	}

	/**
	 * 鐗瑰畾鐨勫璺痻鍧愭爣 t_npc.f_findX
	 * @param findx  the value for t_npc.f_findX
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setFindx(Integer findx) {
		this.findx = findx;
	}

	/**
	 * 鐗瑰畾鐨勫璺痻鍧愭爣 t_npc.f_findY
	 * @return  the value of t_npc.f_findY
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getFindy() {
		return findy;
	}

	/**
	 * 鐗瑰畾鐨勫璺痻鍧愭爣 t_npc.f_findY
	 * @param findy  the value for t_npc.f_findY
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setFindy(Integer findy) {
		this.findy = findy;
	}

	/**
	 * npc鐨勫垵濮嬫柟鍚�0-7椤烘椂閽� 0鏄�鐐归挓鏂瑰悜) t_npc.f_direction
	 * @return  the value of t_npc.f_direction
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getDirection() {
		return direction;
	}

	/**
	 * npc鐨勫垵濮嬫柟鍚�0-7椤烘椂閽� 0鏄�鐐归挓鏂瑰悜) t_npc.f_direction
	 * @param direction  the value for t_npc.f_direction
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	/**
	 * 鏀诲嚮鏃跺０闊崇紪鍙�t_npc.f_attack_audio
	 * @return  the value of t_npc.f_attack_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getAttackAudio() {
		return attackAudio;
	}

	/**
	 * 鏀诲嚮鏃跺０闊崇紪鍙�t_npc.f_attack_audio
	 * @param attackAudio  the value for t_npc.f_attack_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setAttackAudio(Integer attackAudio) {
		this.attackAudio = attackAudio;
	}

	/**
	 * 琚敾鍑荤殑澹伴煶 t_npc.f_hurt_audio
	 * @return  the value of t_npc.f_hurt_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getHurtAudio() {
		return hurtAudio;
	}

	/**
	 * 琚敾鍑荤殑澹伴煶 t_npc.f_hurt_audio
	 * @param hurtAudio  the value for t_npc.f_hurt_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setHurtAudio(Integer hurtAudio) {
		this.hurtAudio = hurtAudio;
	}

	/**
	 * 姝讳骸鏃剁殑澹伴煶缂栧彿 t_npc.f_die_audio
	 * @return  the value of t_npc.f_die_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getDieAudio() {
		return dieAudio;
	}

	/**
	 * 姝讳骸鏃剁殑澹伴煶缂栧彿 t_npc.f_die_audio
	 * @param dieAudio  the value for t_npc.f_die_audio
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setDieAudio(Integer dieAudio) {
		this.dieAudio = dieAudio;
	}

	/**
	 * 鍛ㄧ幆浠诲姟浠庤繖涓瓧娈靛紑濮嬬殑绛夌骇闄愬埗锛堢敤浜庨殢鏈洪�鎷╃洰鏍噉pc锛屽彇浠ndNpc锛�t_npc.f_min_grade
	 * @return  the value of t_npc.f_min_grade
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getMinGrade() {
		return minGrade;
	}

	/**
	 * 鍛ㄧ幆浠诲姟浠庤繖涓瓧娈靛紑濮嬬殑绛夌骇闄愬埗锛堢敤浜庨殢鏈洪�鎷╃洰鏍噉pc锛屽彇浠ndNpc锛�t_npc.f_min_grade
	 * @param minGrade  the value for t_npc.f_min_grade
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}

	/**
	 * 鍛ㄧ幆浠诲姟浠庤繖涓瓧娈电粨鏉熺殑绛夌骇闄愬埗锛堢敤浜庨殢鏈洪�鎷╃洰鏍噉pc锛屽彇浠ndNpc锛�t_npc.f_max_grade
	 * @return  the value of t_npc.f_max_grade
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getMaxGrade() {
		return maxGrade;
	}

	/**
	 * 鍛ㄧ幆浠诲姟浠庤繖涓瓧娈电粨鏉熺殑绛夌骇闄愬埗锛堢敤浜庨殢鏈洪�鎷╃洰鏍噉pc锛屽彇浠ndNpc锛�t_npc.f_max_grade
	 * @param maxGrade  the value for t_npc.f_max_grade
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setMaxGrade(Integer maxGrade) {
		this.maxGrade = maxGrade;
	}

	/**
	 * npc 鍚︽槸鍙浆鍚�1鍙浆鍚�0涓嶅彲 t_npc.f_can_redirect
	 * @return  the value of t_npc.f_can_redirect
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public Integer getCanRedirect() {
		return canRedirect;
	}

	/**
	 * npc 鍚︽槸鍙浆鍚�1鍙浆鍚�0涓嶅彲 t_npc.f_can_redirect
	 * @param canRedirect  the value for t_npc.f_can_redirect
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setCanRedirect(Integer canRedirect) {
		this.canRedirect = canRedirect;
	}

	/**
	 * npc闅忔満璇磋瘽鏍煎紡鍚宖_simpleTalk t_npc.f_randomTalk
	 * @return  the value of t_npc.f_randomTalk
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getRandomtalk() {
		return randomtalk;
	}

	/**
	 * npc闅忔満璇磋瘽鏍煎紡鍚宖_simpleTalk t_npc.f_randomTalk
	 * @param randomtalk  the value for t_npc.f_randomTalk
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setRandomtalk(String randomtalk) {
		this.randomtalk = randomtalk;
	}

	/**
	 * npc鍚嶅瓧 濡傚紶涓�鍥介檯鍖�t_npc.f_name_i18n
	 * @return  the value of t_npc.f_name_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * npc鍚嶅瓧 濡傚紶涓�鍥介檯鍖�t_npc.f_name_i18n
	 * @param nameI18n  the value for t_npc.f_name_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * NPC绉板彿 濡�灞犲か 鍥介檯鍖�t_npc.f_nickname_i18n
	 * @return  the value of t_npc.f_nickname_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getNicknameI18n() {
		return nicknameI18n;
	}

	/**
	 * NPC绉板彿 濡�灞犲か 鍥介檯鍖�t_npc.f_nickname_i18n
	 * @param nicknameI18n  the value for t_npc.f_nickname_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setNicknameI18n(String nicknameI18n) {
		this.nicknameI18n = nicknameI18n;
	}

	/**
	 * 娌℃湁浜や簰鐨勭畝鍗曞璇�鍥介檯鍖�t_npc.f_simpleTalk_i18n
	 * @return  the value of t_npc.f_simpleTalk_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public String getSimpletalkI18n() {
		return simpletalkI18n;
	}

	/**
	 * 娌℃湁浜や簰鐨勭畝鍗曞璇�鍥介檯鍖�t_npc.f_simpleTalk_i18n
	 * @param simpletalkI18n  the value for t_npc.f_simpleTalk_i18n
	 * @ibatorgenerated  Fri May 06 16:57:44 CST 2011
	 */
	public void setSimpletalkI18n(String simpletalkI18n) {
		this.simpletalkI18n = simpletalkI18n;
	}
}
