package net.snake.gamemodel.goods.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.snake.GameServer;
import net.snake.ai.util.ArithmeticUtils;
import net.snake.commons.BeanUtils;
import net.snake.commons.GenerateProbability;
import net.snake.commons.UUIDGenerater;
import net.snake.commons.script.SGoods;
import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GameConstant;
import net.snake.consts.GoodItemId;
import net.snake.consts.Position;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.consts.Symbol;
import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.config.bean.ConfigParam;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.bean.EquipmentStrengthen;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.persistence.EquipmentStrengthenManager;
import net.snake.gamemodel.goods.logic.action.UseGoodAction;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.response.BodyGoodsUpdata10180;
import net.snake.gamemodel.goods.response.GoodsUpdata10176;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.pet.response.HorseGoodsUpdata10078;
import net.snake.gamemodel.quickbar.logic.QuickBarGoods;
import net.snake.gamemodel.trade.response.StallGoodsUpdata13002;
import net.snake.netio.message.ResponseMsg;

import org.apache.log4j.Logger;

/**
 * 角色物品表 克隆该对象为浅克隆 需深度克隆请重写clone方法 spring
 * 
 * 
 * 装备
 * 
 * 强化等级情况发送str 基础属性（类型，天生，强化；） baseAttributeStr 附加属性情况发送str 熔合属性（计算后发送str） rongheAttributeStr
 * 
 * @author benchild
 */

public class CharacterGoods extends GoodsDataEntry implements QuickBarGoods, Cloneable, PropertyChangeListener, SGoods, Comparable<CharacterGoods> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CharacterGoods.class);

	private Map<Property, Integer> basePropsMap;// 基础属性 （装备使用） 基础属性以分号隔开
	// 装备上签套的其他装备
	private int[] extraArrayValue; // 附加属性(装备使用)
	private Property[] extraArray; // 附加属性(装备使用)

	private CharacterGoods nextCharacterGoods;
	private String nextTotem;

	private Goodmodel goodModelRef;
	private boolean equipmentUpdate = true;// 是否更新装备显示
	private String baseAttributeStr = "";// 装备基础属性变化
	private String additionAttributeStr = "";// 装备附加属性变化(属性类型，属性值，属性最大值，是否满值;)
	private String rongheAttributeStr = "";// 装备融合属性变化(宝石id,融合属性类型,属性值；)
	private String tmpAddOneExtraStr = "";// 装备新增一条附件属性临时存贮(发送给客户端显示使用)
	private String tmpAddOneAttributeStr = "";// 装备新增一条附件属性临时存贮(发送给客户端显示使用)
	private String tmpResetExtraValueStr = "";// 装备重置附件属性值临时存贮(发送给客户端显示使用)
	private String tmpResetExtraValueAttributeStr = "";// 装备重置附件属性值临时存贮(发送给客户端显示使用)

	private String tmpStroneAttributeStr = "";// 宝石附加属性值临时存贮(发送给客户端显示使用)
	// 交易状态的索引,交易取消时要置为-1
	private int tradeIndex = -1;
	private int rongxueV = 0;// 舍利子或者附身符的溶血值
	private boolean manxingGems;// 是否满星顶级
	private boolean maxAddAttribute = true;// 满附加属性
	private boolean maxTotemAttribute = true;// 满图腾属性
	private boolean maxBornAttribute = true;// 满天生
	private int baoshi6Num = 0;// 6品宝石的数量
	private String strengthenAfterBaseDesc;// 强化以后的天生属性值

	public void destroy() {
		goodModelRef = null;
		nextCharacterGoods = null;
		extraArray = null;
		extraArrayValue = null;
		basePropsMap = null;
	}

	public String getNextTotem() {
		return nextTotem;
	}

	public void setNextTotem(String nextTotem) {
		this.nextTotem = nextTotem;
	}

	public int getBaoshi6Num() {
		return baoshi6Num;
	}

	public void setBaoshi6Num(int baoshi6Num) {
		this.baoshi6Num = baoshi6Num;
	}

	public String getTmpAddOneExtraStr() {
		return tmpAddOneExtraStr;
	}

	/**
	 * 是否是需要动态显示tip的物品
	 * 
	 * @return true 是
	 */
	public boolean isDynamic() {
		return getGoodModel().isEquipment() || getGoodModel().isChongqiwawa() || getGoodModel().isShelizi() || getGoodModel().isGemStone();
	}

	public void setTmpAddOneExtraStr(String tmpAddOneExtraStr) {
		String additionDesc = tmpAddOneExtraStr;
		if (additionDesc != null && additionDesc.length() > 0) {
			StringBuilder additionStr = new StringBuilder();
			String[] extraData = additionDesc.split(Symbol.FENHAO);
			for (int i = 0; i < extraData.length; i++) {
				String[] extraValueStr = extraData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(extraValueStr[0]);
				int extraValue = Integer.parseInt(extraValueStr[1]);
				additionStr.append(propType).append(Symbol.DOUHAO).append(extraValue).append(Symbol.FENHAO);
			}
			tmpAddOneAttributeStr = additionStr.toString();
		} else {
			tmpAddOneAttributeStr = "";
		}

		this.tmpAddOneExtraStr = tmpAddOneExtraStr;
	}

	public String getTmpResetExtraValueStr() {
		return tmpResetExtraValueStr;
	}

	/**
	 * 是否过期
	 * 
	 * @return
	 */
	public boolean isExpired() {
		Date lastdate = getLastDate();
		if (lastdate != null) {
			return System.currentTimeMillis() > lastdate.getTime();
		} else {
			return false;
		}
	}

	public String getTmpAddOneAttributeStr() {
		return tmpAddOneAttributeStr;
	}

	public String getTmpResetExtraValueAttributeStr() {
		return tmpResetExtraValueAttributeStr;
	}

	public void setTmpResetExtraValueStr(String tmpResetExtraValueStr) {
		String additionDesc = tmpResetExtraValueStr;
		if (additionDesc != null && additionDesc.length() > 0) {
			StringBuilder additionStr = new StringBuilder();
			String[] extraData = additionDesc.split(Symbol.FENHAO);
			for (int i = 0; i < extraData.length; i++) {
				String[] extraValueStr = extraData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(extraValueStr[0]);
				int extraValue = Integer.parseInt(extraValueStr[1]);
				additionStr.append(propType).append(Symbol.DOUHAO).append(extraValue).append(Symbol.FENHAO);
			}
			tmpResetExtraValueAttributeStr = additionStr.toString();
		} else {
			tmpResetExtraValueAttributeStr = "";
		}

		this.tmpResetExtraValueStr = tmpResetExtraValueStr;
	}

	public boolean isMaxTotemAttribute() {
		return maxTotemAttribute;
	}

	public void setMaxTotemAttribute(boolean maxTotemAttribute) {
		this.maxTotemAttribute = maxTotemAttribute;
	}

	public boolean isMaxAddAttribute() {
		return maxAddAttribute;
	}

	public void setMaxAddAttribute(boolean maxAddAttribute) {
		this.maxAddAttribute = maxAddAttribute;
	}

	public boolean isManxingGems() {
		return manxingGems;
	}

	public int getRongxueV() {
		return rongxueV;
	}

	public void setRongxueV(int rongxueV) {
		this.rongxueV = rongxueV;
	}

	/**
	 * 是否绑定
	 * 
	 * @return
	 */
	public boolean isBinding() {
		return getBind() == 1;
	}

	public int getBaoshiNum() {
		String inEquipId = getInEquipId();
		if ("".equals(inEquipId))
			return 0;
		return inEquipId.split(";").length;
	}

	public int getStrengthenNum() {
		return this.getPin();
	}

	public void setTradeIndex(int tradeIndex) {
		this.tradeIndex = tradeIndex;
	}

	/**
	 * 是否满星
	 * 
	 * @return
	 */
	public boolean isAllStar() {
		if (!getGoodModel().isEquipment())
			return false;
		// int grade = getGoodModel().getStrengthenGrade();
		return GameConstant.StrengthenCountLimit == this.getStrengthenCount();
	}

	/**
	 * 获得品质 显示使用
	 * 
	 * @return
	 */
	public int getPingzhiColor() {
		return this.getPin();
	}

	public int getPingzhi() {
		return this.getPin();
	}

	/**
	 * 进阶
	 * 
	 * @return
	 */
	public int getJinjie() {
		return this.getPin();
	}

	public int getTradeIndex() {
		return tradeIndex;
	}

	/**
	 * 根据（交易状态的索引,交易取消时要置为-1） 是否在交易中
	 * 
	 * @return
	 */
	public boolean isInTrade() {
		return (tradeIndex != -1);
	}

	public CharacterGoods(GoodsDataEntry goodsDataEntry) {
		BeanUtils.copyProperties(goodsDataEntry, this);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		CharacterGoods goods = (CharacterGoods) super.clone();
		goods.setId(UUIDGenerater.generate());
		if (basePropsMap != null) {
			goods.basePropsMap = new HashMap<Property, Integer>();
			goods.basePropsMap.putAll(this.basePropsMap);
		}
		if (extraArrayValue != null) {
			goods.extraArrayValue = Arrays.copyOf(extraArrayValue, extraArrayValue.length);
		}
		if (extraArray != null) {
			goods.extraArray = Arrays.copyOf(extraArray, extraArray.length);
		}
		goods.nextCharacterGoods = null;

		return goods;
	}

	@Override
	public void setStroneAddproperty(String stroneAddproperty) {
		super.setStroneAddproperty(stroneAddproperty);
		equipmentUpdate = true;
	}

	/**
	 * 客户端请求的时候察看属性，就调用一次改方法
	 */
	public void equipmentUpdate() {
		if (!equipmentUpdate || getGoodmodelId() <= 0) {
			return;
		}
		// 装备基础属性变化
		maxBornAttribute = true;
		String baseDesc = getBaseDesc();
		if (baseDesc != null && baseDesc.length() > 0) {
			String[] baseData = baseDesc.split(Symbol.FENHAO);
			StringBuilder baseDataStr = new StringBuilder();
			for (int i = 0; i < baseData.length; i++) {
				String[] baseValueStr = baseData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(baseValueStr[0]);
				int bornLv = Integer.parseInt(baseValueStr[1]);

				baseDataStr.append(propType).append(Symbol.DOUHAO);
				Property property = Property.getProperty(propType);
				int value1 = getGoodModel().getBasePropertyValue(property);// 基础属性
				baseDataStr.append(value1).append(Symbol.DOUHAO);
				// int value2 = Math.round((float) (value1 * bornLv *
				// 0.0001));// 天生
				baseDataStr.append(bornLv).append(Symbol.DOUHAO);
				int value3 = 0;
				if (value1 != bornLv) {
					maxBornAttribute = false;
				}
				EquipmentStrengthen equipmentStrengthen = EquipmentStrengthenManager.getInstance().getEuipmentStrengthen(getGoodModel().getId(), this.getStrengthenCount());
				if (equipmentStrengthen != null) {
					value3 = equipmentStrengthen.getStrengthenValue(property);
				}
				baseDataStr.append(value3).append(Symbol.FENHAO);
			}
			baseAttributeStr = baseDataStr.toString();
		} else {
			baseAttributeStr = "";
		}
		int pingzhi;
		maxAddAttribute = true;
		// 附加属性叠加
		String additionDesc = getAdditionDesc();
		if (additionDesc != null && additionDesc.length() > 0) {
			StringBuilder additionStr = new StringBuilder();
			String[] extraData = additionDesc.split(Symbol.FENHAO);
			pingzhi = extraData.length;
			for (int i = 0; i < pingzhi; i++) {
				String[] extraValueStr = extraData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(extraValueStr[0]);
				if (propType == 99) {
					additionStr.append(propType).append(Symbol.DOUHAO);
					additionStr.append(0).append(Symbol.DOUHAO);
					additionStr.append(0).append(Symbol.DOUHAO);
					additionStr.append(0).append(Symbol.FENHAO);
					continue;
				}
				Property property = Property.getProperty(propType);
				int extraValue = Integer.parseInt(extraValueStr[1]);
				int maxExtraValue = getGoodModel().getAddPropertyMaxValue(property);
				additionStr.append(propType).append(Symbol.DOUHAO);
				additionStr.append(extraValue).append(Symbol.DOUHAO);
				additionStr.append(maxExtraValue).append(Symbol.DOUHAO);
				additionStr.append(maxExtraValue == extraValue ? 1 : 0).append(Symbol.FENHAO);
				if (maxExtraValue != extraValue) {
					maxAddAttribute = false;
				}
			}

			if (pingzhi != GameConstant.StrengthenPinLimit) {
				maxAddAttribute = false;
			}
			additionAttributeStr = additionStr.toString();
		} else {
			maxAddAttribute = false;
			additionAttributeStr = "";
		}

		String inEquipId = getInEquipId();
		manxingGems = false;
		baoshi6Num = 0;
		rongheAttributeStr = "";
		if (inEquipId != null && inEquipId.length() > 0) {
			StringBuilder rongheDataStr = new StringBuilder();
			// 装备融合属性变化
			String[] baoshiStr = inEquipId.split(Symbol.FENHAO);
			for (int i = 0; i < baoshiStr.length; i++) {
				int gemsId = Integer.parseInt(baoshiStr[i]);
				Goodmodel baoshi = GoodmodelManager.getInstance().get(gemsId);
				if (baoshi.isGemStone()) {
					rongheDataStr.append(gemsId).append(Symbol.DOUHAO);
					rongheDataStr.append(baoshi.getGemsType()).append(Symbol.DOUHAO);
					rongheDataStr.append(baoshi.getGemsValue());
					rongheDataStr.append(Symbol.FENHAO);
				}
			}
			rongheAttributeStr = rongheDataStr.toString();
		}
		equipmentUpdate = false;
	}

	/**
	 * 是否是满天生
	 * 
	 * @return
	 */
	public boolean isMaxBornAttribute() {
		return maxBornAttribute;
	}

	public String getTmpStroneAttributeStr() {
		return tmpStroneAttributeStr;
	}

	public String getBaseAttributeStr() {
		return baseAttributeStr == null ? "" : baseAttributeStr;
	}

	public String getRongheAttributeStr() {
		return rongheAttributeStr == null ? "" : rongheAttributeStr;
	}

	/** 当这些属性发生变化时更新装备显示 **/
	@Override
	public void setBaseDesc(String baseDesc) {
		super.setBaseDesc(baseDesc);
		equipmentUpdate = true;
	}

	@Override
	public void setBornLv(Integer bornLv) {
		super.setBornLv(bornLv);
		equipmentUpdate = true;
	}

	public String getAdditionAttributeStr() {
		return additionAttributeStr;
	}

	@Override
	public void setStrengthenCount(Integer strengthenCount) {
		super.setStrengthenCount(strengthenCount);
		equipmentUpdate = true;
	}

	@Override
	public void setShelizhiInEquipId(String shelizhiInEquipId) {
		super.setShelizhiInEquipId(shelizhiInEquipId);
		equipmentUpdate = true;
	}

	@Override
	public void setInEquipId(String inEquipId) {
		super.setInEquipId(inEquipId);
		equipmentUpdate = true;
	}

	@Override
	public String getInEquipId() {
		return super.getInEquipId() == null ? "" : super.getInEquipId();
	}

	@Override
	public void setAdditionDesc(String additionDesc) {
		super.setAdditionDesc(additionDesc);
		equipmentUpdate = true;
	}

	/****/

	public void setGoodModel(Goodmodel goodModelRef) {
		this.goodModelRef = goodModelRef;
		if (goodModelRef != null) {
			setGoodmodelId(goodModelRef.getId());
		}
	}

	public CharacterGoods() {
	}

	public CharacterGoods getNextCharacterGoods() {
		return nextCharacterGoods;
	}

	public void setNextCharacterGoods(CharacterGoods nextCharacterGoods) {
		this.nextCharacterGoods = nextCharacterGoods;
	}

	public void setMaxDurability(Integer maxDurability) {
		super.setMaxDurability(maxDurability);
	}

	/**
	 * 指定强化等级、品阶创建物品
	 * 
	 * @param goodId
	 * @param count
	 * @param bind
	 * @param qianghua
	 *            强化等级(强化属性条目)
	 * @param pingjie
	 *            品阶 (附加属性条目)
	 * @return
	 */
	public static CharacterGoods createCharacterGood(int goodId, int count, byte bind) {
		Goodmodel goodModel = GoodmodelManager.getInstance().get(goodId);
		if (goodModel == null) {
			return null;
		}
		CharacterGoods charactergoods = initCharacterGood(count, goodModel);
		if (goodModel.isEquipment()) {
			charactergoods.setCurrDurability(goodModel.getDurability());

			String additiondesc = goodModel.getAdditionDesc();
			int grade = goodModel.getGrade();
			String InlayGemsdesc = goodModel.getInlayGemsDesc();
			if ((additiondesc != null && additiondesc.length() > 0) || (grade > 0) || (InlayGemsdesc != null && InlayGemsdesc.length() > 0)) {
				charactergoods.initBaseProps();
				charactergoods.setStrengthenCount(grade);
				charactergoods.setAdditionDesc(additiondesc);
				charactergoods.setInEquipId(InlayGemsdesc);
			} else {
				charactergoods.initEquipmentByPinjie();
			}
		} else {
			charactergoods.setCurrDurability(0);
		}
		if (goodModel.getBinding() == BindChangeType.BIND) {// 拾取后绑定
			charactergoods.setBind(CommonUseNumber.byte1);
		} else {
			charactergoods.setBind(bind);
		}
		charactergoods.setQuickbarindex(0);
		if (goodModel.getEndDate() != null && goodModel.getEndDate().length() > 0) {
			charactergoods.setLastDate(ArithmeticUtils.stringToDate(goodModel.getEndDate()));
		}
		if (goodModel.getLiveTime() != null && goodModel.getLiveTime() > 0) {
			charactergoods.setLastDate(new Date(System.currentTimeMillis() + goodModel.getLiveTime() * 1000));
		}
		return charactergoods;
	}

	public void initEquipment(int addValueNums, int chestResources_id) {
		// 当前耐久值 INT（最大耐久度 / 5） 与 最大耐久度 之间随机一个值
		initBaseProps();
		randomGenerateExtraProp(addValueNums, chestResources_id, 0);
	}

	private void initEquipmentByPinjie() {
		// 当前耐久值 INT（最大耐久度 / 5） 与 最大耐久度 之间随机一个值
		// 设置装备基础属性
		initBaseProps();
		// 设置装备附加属性 根据附加属性值
		randomGenerateExtraPropByPinjie(null);
	}

	/**
	 * 根据进阶随机附加属性
	 * 
	 * @param attrs
	 *            保留的附加属性，没有的话传null
	 */
	public void randomGenerateExtraPropByPinjie(byte attrs[]) {
		extraArray = new Property[GameConstant.StrengthenPinLimit];
		extraArrayValue = new int[GameConstant.StrengthenPinLimit];
		int cnt = 0;
		if (attrs != null) {
			String additionDesc = getAdditionDesc();
			if (additionDesc != null && additionDesc.length() > 0) {
				String[] extraData = additionDesc.split(Symbol.FENHAO);
				cnt = extraData.length;
				for (byte i = 0; i < attrs.length; i++) {
					String[] extraValueStr = extraData[attrs[i]].split(Symbol.DOUHAO);
					int propType = Integer.parseInt(extraValueStr[0]);
					Property property = Property.getProperty(propType);
					int percent = Integer.parseInt(extraValueStr[1]);
					extraArray[i] = property;
					extraArrayValue[i] = percent;
				}
			}
		}
		int relpinjie = this.getRelPinjie(this.getPin());// 可以附加的属性个数
		if (cnt < 0) {
			cnt = 0;
		}
		for (int i = 0; i < 50; i++) {
			cnt = addExtraPropByPinjie(cnt, relpinjie);
			if (cnt >= relpinjie) {
				break;
			}
		}
		this.enforcementAddExtraPropByPinjie(this.getPin());
		setAdditionDescByExtra();
	}

	private int getRelPinjie(int pinjie) {
		int relpinjie = pinjie;
		for (int i = 0; i < pinjie; i++) {
			if (GenerateProbability.isGenerate2(GenerateProbability.gailv, getProbabilityByPin(i + 1))) {
				break;
			} else {
				relpinjie--;
			}
		}
		if (pinjie == 2 || pinjie == 3) {
			if (relpinjie < 1) {
				relpinjie = 1;
			}
		}
		if (pinjie == 4 || pinjie == 5) {
			if (relpinjie < 2) {
				relpinjie = 2;
			}
		}
		return relpinjie;
	}

	public int getProbabilityByPin(int pin) {
		ConfigParam param = GameServer.configParamManger.getConfigParam();
		int prb = 0;
		switch (pin) {
		case 1:
			prb = param.getPin1();
			break;
		case 2:
			prb = param.getPin2();
			break;
		case 3:
			prb = param.getPin3();
			break;
		case 4:
			prb = param.getPin4();
			break;
		case 5:
			prb = param.getPin5();
			break;
		}
		return prb;
	}

	/**
	 * 如果品阶升级，则更新附加属性
	 * 
	 * @param pinjie
	 */
	public void generateExtraPropByPinjieUpdate(int newpinjie) {
		int oldpinjie = this.getPin();
		if (newpinjie <= oldpinjie) {
			return;
		}
		final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(this.getGoodModel().getId());
		if (equipmentPlayconfig != null) {
			int nextGoodModelId = equipmentPlayconfig.getNextGoodmodelId();
			this.setGoodmodelId(nextGoodModelId);
			this.setGoodModel(GoodmodelManager.getInstance().get(nextGoodModelId));
		}
		int relpinjie = this.getRelPinjie(newpinjie);// 可以附加的属性个数
		String additionDesc = getAdditionDesc();
		int cnt = initExtraArray(additionDesc);
		if (cnt < 0) {
			cnt = 0;
		}
		if (cnt >= relpinjie) {
			return;
		}
		for (int i = 1; i < 50; i++) {
			cnt = addExtraPropByPinjie(cnt, relpinjie);
			if (cnt >= relpinjie) {
				break;
			}
		}

		this.enforcementAddExtraPropByPinjie(relpinjie);
		setAdditionDescByExtra();

	}

	/**
	 * 根据已有的属性初始化 extraArray extraArrayValue
	 * 
	 * @param additionDesc
	 * @return
	 */
	public int initExtraArray(String additionDesc) {
		extraArray = new Property[GameConstant.StrengthenPinLimit];
		extraArrayValue = new int[GameConstant.StrengthenPinLimit];
		int size = 0;
		if (additionDesc != null && additionDesc.length() > 0) {
			String[] extraData = additionDesc.split(Symbol.FENHAO);
			size = extraData.length;
			for (int i = 0; i < size; i++) {
				String[] extraValueStr = extraData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(extraValueStr[0]);
				Property property = Property.getProperty(propType);
				int percent = Integer.parseInt(extraValueStr[1]);
				extraArray[i] = property;
				extraArrayValue[i] = percent;
			}
		}
		return size;
	}

	/**
	 * 1品9 2品9 3品9 4品9 5品9 共45阶
	 * 
	 * @return
	 */
	public int getPin() {
		if (this.getStrengthenCount() == 0) {
			return 0;
		}
		int pin = this.getStrengthenCount() / GameConstant.StrengthenJie;
		pin++;
		int jie = this.getJie();
		if (jie == 9) {
			pin = pin - 1;
		}
		return pin;
	}

	public int getJie() {
		if (this.getStrengthenCount() == 0) {
			return 0;
		}
		int jie = this.getStrengthenCount() % GameConstant.StrengthenJie;
		if (jie == 0) {
			jie = 9;
		}
		return jie;
	}

	/**
	 * 强制添加附加属性
	 * 
	 * @param count
	 * @return
	 */
	private void enforcementAddExtraPropByPinjie(int pinjie) {
		logger.info("pinjie=" + pinjie);
		int minAddAddr = 0;
		if (pinjie == 2 || pinjie == 3) {
			minAddAddr = 1;
		}
		if (pinjie == 4 || pinjie == 5) {
			minAddAddr = 2;
		}
		int cnt = 0;
		for (int i = 0; i < extraArray.length; i++) {
			if (extraArray[i] != null) {
				cnt++;
			}
		}
		logger.info("cnt=" + cnt);
		if (cnt >= minAddAddr) {
			return;
		}
		int count = minAddAddr - cnt;
		logger.info("count=" + count);
		List<Property> list = new ArrayList<Property>();
		Goodmodel goodmodel = getGoodModel();
		if (goodmodel.getAttackProbability() > 0) {
			list.add(Property.attack);
		}
		if (goodmodel.getDefenceProbability() > 0) {
			list.add(Property.defence);
		}
		if (goodmodel.getCrtProbability() > 0) {
			list.add(Property.crt);
		}
		if (goodmodel.getDodgeProbability() > 0) {
			list.add(Property.dodge);
		}
		if (goodmodel.getHpProbability() > 0) {
			list.add(Property.maxHp);
		}
		if (goodmodel.getSpProbability() > 0) {
			list.add(Property.maxSp);
		}
		if (goodmodel.getMpProbability() > 0) {
			list.add(Property.maxMp);
		}
		if (goodmodel.getHitProbability() > 0) {
			list.add(Property.hit);
		}

		for (int i = 0; i < count; i++) {
			int pnum = GenerateProbability.randomIntValue(0, list.size() - 1);
			int value = getExtraProVal(list.get(pnum));
			int index = 0;
			for (int j = 0; j < extraArray.length; j++) {
				if (extraArray[j] == null) {
					index = j;
					break;
				}
			}
			addExtraProps(index, list.get(pnum), value);
		}
	}

	private int getExtraProVal(Property pro) {
		int min = this.getGoodModel().getAddPropertyMinValue(pro);
		int max = this.getGoodModel().getAddPropertyMaxValue(pro);
		int value = GenerateProbability.randomIntValue(min, max);
		return value;
	}

	private int addExtraPropByPinjie(int count, int pinjie) {
		int value;// 比率
		Goodmodel goodmodel = getGoodModel();
		int randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		if (randomValue <= goodmodel.getAttackProbability()) {
			value = getExtraProVal(Property.attack);
			addExtraProps(count, Property.attack, value);
			count++;
			if (count >= pinjie) {
				return count;
			}
		}

		if (goodmodel.getDefenceProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getDefenceProbability()) {
				value = getExtraProVal(Property.defence);
				addExtraProps(count, Property.defence, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}

		if (goodmodel.getCrtProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getCrtProbability()) {
				value = getExtraProVal(Property.crt);
				addExtraProps(count, Property.crt, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}

		if (goodmodel.getDodgeProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getDodgeProbability()) {
				value = getExtraProVal(Property.dodge);
				addExtraProps(count, Property.dodge, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}
		if (goodmodel.getHpProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getHpProbability()) {
				value = getExtraProVal(Property.maxHp);
				addExtraProps(count, Property.maxHp, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}
		if (goodmodel.getSpProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getSpProbability()) {
				value = getExtraProVal(Property.maxSp);
				addExtraProps(count, Property.maxSp, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}

		if (goodmodel.getMpProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getMpProbability()) {
				value = getExtraProVal(Property.maxMp);
				addExtraProps(count, Property.maxMp, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}
		if (goodmodel.getHitProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
			if (randomValue <= goodmodel.getHitProbability()) {
				value = getExtraProVal(Property.hit);
				addExtraProps(count, Property.hit, value);
				count++;
				if (count >= pinjie) {
					return count;
				}
			}
		}
		return count;
	}

	public static CharacterGoods createCharacterGoods(Integer num, int goodModelid, int loopcount) {
		Goodmodel gm = GoodmodelManager.getInstance().get(goodModelid);
		if (gm != null) {
			return createCharacterGoods(num, gm, loopcount, 0);
		}
		return null;

	}

	public static CharacterGoods createCharacterGoods_ChestResourcesById(String chestResources_id) {

		ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(chestResources_id);
		if (null == chestResources) {
			return null;
		}
		int goodModelid = chestResources.getGoodmodelId();
		Goodmodel gm = GoodmodelManager.getInstance().get(goodModelid);
		if (gm != null) {
			CharacterGoods cg = createCharacterGoods((int) chestResources.getQuantity(), gm, 0, Integer.valueOf(chestResources_id.split("_")[0]));
			cg.randomGenerateExtraPropByPinjie(null);
			return cg;
		}
		return null;

	}

	@Override
	public void setCurrDurability(Integer currDurability) {
		super.setCurrDurability(currDurability);
	}

	/**
	 * 根据物品模型生成角色物品
	 * 
	 * @param num
	 *            希望创建的数量,超过最大可叠加的值时,将自动置为最大可叠加值
	 * @param goodModel
	 * @param loopcount
	 *            生成时的循环次数,用于产生高品质的物品,对于已经有固定品质的物品,此值无效
	 * @param chestResources_id
	 *            宝箱id不是宝箱传入0
	 * @return
	 */
	public static CharacterGoods createCharacterGoods(Integer num, Goodmodel goodModel, int loopcount, int chestResources_id) {

		CharacterGoods charactergoods = initCharacterGood(num, goodModel);

		if (goodModel.isEquipment()) {// 此道具是装备
			charactergoods.setCurrDurability(goodModel.getDurability());

			String additiondesc = goodModel.getAdditionDesc();
			int grade = goodModel.getGrade();
			String InlayGemsdesc = goodModel.getInlayGemsDesc();
			if ((additiondesc != null && additiondesc.length() > 0) || (grade > 0) || (InlayGemsdesc != null && InlayGemsdesc.length() > 0)) {
				charactergoods.initBaseProps();
				charactergoods.setStrengthenCount(grade);
				charactergoods.setAdditionDesc(additiondesc);
				charactergoods.setInEquipId(InlayGemsdesc);
			} else {
				charactergoods.initEquipment(loopcount, chestResources_id);
			}
		} else {// 此道具非装备
			charactergoods.setCurrDurability(0);
		}

		if (goodModel.getBinding() == BindChangeType.BIND) {// 拾取后绑定
			charactergoods.setBind(CommonUseNumber.byte1);
		} else {
			charactergoods.setBind(CommonUseNumber.byte0);
		}

		if (chestResources_id != 0) {
			ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_int().get(chestResources_id);
			if (chestResources.getBinding() == BindChangeType.BIND) {
				charactergoods.setBind(CommonUseNumber.byte1);
			} else {
				charactergoods.setBind(CommonUseNumber.byte0);
			}
		}

		charactergoods.setQuickbarindex(0);
		if (goodModel.getEndDate() != null && goodModel.getEndDate().length() > 0) {
			charactergoods.setLastDate(ArithmeticUtils.stringToDate(goodModel.getEndDate()));
		}
		if (goodModel.getLiveTime() != null && goodModel.getLiveTime() > 0) {
			charactergoods.setLastDate(new Date(System.currentTimeMillis() + goodModel.getLiveTime() * 1000));
		}

		if (goodModel.isShelizi()) {
			charactergoods.randomSkillForShelizi(0);
		}
		return charactergoods;
	}

	/**
	 * 创建并初始化一定数量的角色道具。
	 * 
	 * @param num
	 *            指定的数量
	 * @param goodModel
	 *            道具模型
	 * @return
	 */
	public static CharacterGoods initCharacterGood(Integer num, Goodmodel goodModel) {
		CharacterGoods charactergoods = new CharacterGoods();
		charactergoods.setId(UUIDGenerater.generate());
		charactergoods.setCharacterId(0);
		charactergoods.setGoodmodelId(goodModel.getId());
		charactergoods.setGoodModel(goodModel);
		charactergoods.setPosition(CommonUseNumber.short0);
		charactergoods.setInHorseId(0);
		charactergoods.setMaxDurability(goodModel.getDurability());
		charactergoods.setIsShow(CommonUseNumber.byte1);
		charactergoods.setIsUse(CommonUseNumber.byte0);
		charactergoods.setBornStrengthenCnt(0);
		charactergoods.setStroneAddproperty("");
		charactergoods.setCount(num);
		charactergoods.setStallCopper(0);
		charactergoods.setStallIngot(0);
		charactergoods.setBornLv(0);
		charactergoods.setAdditionDesc("");
		charactergoods.setBaseDesc("");
		// charactergoods.setStrengthenDesc("");
		charactergoods.setStrengthenCount(goodModel.getGrade());
		charactergoods.setChongqiOwnerId(0);
		return charactergoods;
	}

	@Override
	public int compareTo(CharacterGoods o2) {
		int quanzhong1 = 1000000000;
		long sort1 = this.getGoodModel().getKind() * quanzhong1 + this.getGoodmodelId();
		long sort2 = o2.getGoodModel().getKind() * quanzhong1 + o2.getGoodmodelId();
		return sort1 < sort2 ? -1 : (sort1 == sort2 ? 0 : 1);
	}

	public Goodmodel getGoodModel() {
		if (goodModelRef == null) {
			goodModelRef = GoodmodelManager.getInstance().get(getGoodmodelId());
		}
		return goodModelRef;
	}

	/**
	 * 处理基础属性
	 * 
	 * @param pe
	 */
	private void handleBasedesc(PropertyEntity pe) {
		String basedesc = getBaseDesc();
		if (basedesc == null || basedesc.length() == 0) {
			return;
		}
		String[] baseData = basedesc.split(Symbol.FENHAO);
		// 基础属性叠加
		for (int i = 0; i < baseData.length; i++) {
			try {
				String[] baseValueStr = baseData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(baseValueStr[0]);
				if (propType == Property.movespeed.getNum()) {
					if (this.getPosition() != Position.POSTION_TESHU || this.getIsUse() != 1) {
						continue;
					}
				}
				int bornLv = Integer.parseInt(baseValueStr[1]);
				Property property = Property.getProperty(propType);
				int value1 = getGoodModel().getBasePropertyValue(property);// 基础属性
				int value3 = 0;
				EquipmentStrengthen equipmentStrengthen = EquipmentStrengthenManager.getInstance().getEuipmentStrengthen(getGoodModel().getId(), this.getStrengthenCount());
				if (equipmentStrengthen != null) {
					value3 = value3 + equipmentStrengthen.getStrengthenValue(property);
				}
				int value = value1 + bornLv + value3;
				pe.addExtraProperty(property, value);
			} catch (NumberFormatException e) {
			}
		}
	}

	/**
	 * 处理附加属性
	 * 
	 * @param pe
	 */
	private void handleAdditiondesc(PropertyEntity pe) {
		String additiondesc = getAdditionDesc();
		if (additiondesc == null || additiondesc.length() == 0) {
			return;
		}
		String[] extraData = additiondesc.split(";");
		if (extraData.length == 0 || "".equals(extraData[0])) {
			return;
		}
		for (int i = 0; i < extraData.length; i++) {
			try {
				String[] extraValueStr = extraData[i].split(",");
				int propType = Integer.parseInt(extraValueStr[0]);
				if (propType == 99) {
					continue;
				}
				Property property = Property.getProperty(propType);
				int extraValue = Integer.parseInt(extraValueStr[1]);
				pe.addExtraProperty(property, extraValue);
			} catch (NumberFormatException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 计算融合属性
	 * 
	 * @param pe
	 */
	private void handleInequipid(PropertyEntity pe) {
		String inequipid = getInEquipId();
		if (inequipid == null || inequipid.length() == 0) {
			return;
		}
		// 1增加攻击力,2增加防御力,3增加暴击,4增加闪避,5增加生命上限值,6增加体力上限值,7增加内力上限值,8增加攻击速度,9增加移动速度,10命中
		String[] gemsStr = inequipid.split(Symbol.FENHAO);
		if (gemsStr == null || gemsStr.length == 0) {
			return;
		}
		for (int i = 0; i < gemsStr.length; i++) {
			String gemsId = gemsStr[i];
			if ("".equals(gemsId)) {
				continue;
			}
			try {
				Goodmodel gemsGoodmodel = GoodmodelManager.getInstance().get(Integer.parseInt(gemsId));
				if (gemsGoodmodel.isGemStone()) {
					Property property = Property.getProperty(gemsGoodmodel.getGemsType());
					pe.addExtraProperty(property, gemsGoodmodel.getGemsValue());
				}
			} catch (NumberFormatException e) {
			}
		}
	}

	public PropertyEntity getPropertyEntity() {
		if (!getGoodModel().isEquipment()) {
			return null;
		}

		if (getCurrDurability() == 0) {
			return null;
		}
		PropertyEntity pe = new PropertyEntity();

		handleBasedesc(pe);

		// 附加属性叠加
		handleAdditiondesc(pe);

		// 熔合属性 加成
		handleInequipid(pe);

		if (getRongxueV() > 0) {
			pe.addExtraProperty(Property.maxHp, getRongxueV());
		}
		return pe;

	}

	public int getGS() {
		if (!getGoodModel().isEquipment()) {
			return 0;
		}
		float gs = 0;
		int lvl = this.getStrengthenCount();
		PropertyEntity entity = this.getPropertyEntity();
		if(entity==null){
			return 0;
		}
		gs += (lvl * lvl) *0.1 + (entity.getAttack() * 1.2 + entity.getDefend() * +2 * entity.getCrt() + 1 * entity.getHit() + 1.5 * entity.getDodge()) *0.3;
		return (int)Math.ceil(gs);
	}

	public CharacterGoods createNextHushenfuCharacterGoods(int nextGoodModelId, boolean create) {
		try {
			Goodmodel goodmodel = GoodmodelManager.getInstance().get(nextGoodModelId);
			CharacterGoods characterGoods = createCharacterGoods(1, goodmodel, 0, 0);
			if (goodmodel.isEquipment()) {
				characterGoods.initBaseProps();
				if (create) {// 创建新装备的时候通过
					if (goodmodel.getId() == GoodItemId.SL_FUSHENFU) {
						characterGoods.setBind(CommonUseNumber.byte1);
						characterGoods.setShelizhiInEquipId("");
					} else {
						characterGoods.setBind(getBind());
						characterGoods.setShelizhiInEquipId(getShelizhiInEquipId());
					}
				} else {// 查看新装备的时候
					if (goodmodel.getId() == GoodItemId.SL_FUSHENFU) {
						characterGoods.setShelizhiInEquipId("");
					} else {
						characterGoods.setShelizhiInEquipId(getShelizhiInEquipId());
					}
					characterGoods.setPosition(Position.HUSHENFU_NEXT_TIPS);
					characterGoods.equipmentUpdate();
				}
				setNextCharacterGoods(characterGoods);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return getNextCharacterGoods();
	}

	public CharacterGoods createNextSheliziCharacterGoods(int nextGoodModelId, boolean create) {
		try {
			Goodmodel goodmodel = GoodmodelManager.getInstance().get(nextGoodModelId);
			CharacterGoods characterGoods = createCharacterGoods(1, goodmodel, 0, 0);
			if (goodmodel.isShelizi()) {
				if (create) {// 创建新装备的时候通过
					characterGoods.setBind(getBind());
					characterGoods.setWugongId(getWugongId());
				} else {// 查看新装备的时候
					characterGoods.setWugongId(getWugongId());
					characterGoods.setPosition(Position.SHENLIZI_NEXT_TIPS);
					characterGoods.equipmentUpdate();
				}
				setNextCharacterGoods(characterGoods);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return getNextCharacterGoods();
	}

	/**
	 * 初始化装备基础属性
	 */
	public void initBaseProps() {
		basePropsMap = new HashMap<Property, Integer>();
		StringBuilder baseDesc = new StringBuilder();
		baseDesc.append(addBaseProps(Property.attack, getGoodModel().getAttack()));
		baseDesc.append(addBaseProps(Property.defence, getGoodModel().getDefence()));
		baseDesc.append(addBaseProps(Property.crt, getGoodModel().getCrt()));
		baseDesc.append(addBaseProps(Property.hit, getGoodModel().getHit()));
		baseDesc.append(addBaseProps(Property.dodge, getGoodModel().getDodge()));
		baseDesc.append(addBaseProps(Property.maxHp, getGoodModel().getHp()));
		baseDesc.append(addBaseProps(Property.maxSp, getGoodModel().getSp()));
		baseDesc.append(addBaseProps(Property.maxMp, getGoodModel().getMp()));
		baseDesc.append(addBaseProps(Property.attackspeed, getGoodModel().getAtkColdtime()));
		baseDesc.append(addBaseProps(Property.movespeed, getGoodModel().getMoveSpeed()));
		setBaseDesc(baseDesc.toString());
	}

	private String addBaseProps(Property property, int value) {
		if (value > 0) {
			basePropsMap.put(property, value);
			return "" + property.getNum() + Symbol.DOUHAO + "0" + Symbol.FENHAO;
		}
		return "";
	}

	/**
	 * 根据轮询的次数产生附加属性以及比率
	 * 
	 * @param addValueNums
	 *            轮询次数
	 * @param chestResources_id
	 * @param pinzhi
	 *            如果为0则按正常的方式随机 如果>0则按指定的条目随机，但是最多不能过5
	 */
	public void randomGenerateExtraProp(int addValueNums, int chestResources_id, int pinzhi) {

		extraArray = new Property[GameConstant.StrengthenPinLimit];
		extraArrayValue = new int[GameConstant.StrengthenPinLimit];

		int cnt = 0;
		if (chestResources_id != 0) {
			ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_int().get(chestResources_id);
			for (int i = 0; i < addValueNums; i++) {
				if (GenerateProbability.isGenerate2(GenerateProbability.gailv, chestResources.getFlopProbability())) {
					cnt = addExtraProp(cnt);
				}
			}
		} else {
			if (pinzhi > 0) {
				// this.generateExtraPropByPinjieUpdate(pinzhi);
			} else {
				for (int i = 0; i < addValueNums; i++) {
					if (GenerateProbability.isGenerate2(GenerateProbability.gailv, getGoodModel().getFlopProbability())) {
						cnt = addExtraProp(cnt);
					}
				}
			}
		}
		setAdditionDescByExtra();
	}

	public void setAdditionDescByExtra() {
		StringBuilder strBuffer = new StringBuilder();
		for (int i = 0; i < extraArray.length; i++) {
			if (extraArray[i] == null) {
				continue;
			} else {
				strBuffer.append(extraArray[i].getNum()).append(Symbol.DOUHAO).append(extraArrayValue[i]).append(Symbol.FENHAO);
			}
		}
//		logger.info("strBuffer" + strBuffer.toString());
		this.setAdditionDesc(strBuffer.toString());
	}

	private int addExtraProp(int count) {
		if (count >= GameConstant.StrengthenPinLimit) {
			return count;
		}
		int value;// 比率
		Goodmodel goodmodel = getGoodModel();

		int randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;

		if (randomValue <= goodmodel.getAttackProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.attack, value);
			count++;
		}

		if (goodmodel.getDefenceProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getDefenceProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.defence, value);
			count++;
		}

		if (goodmodel.getCrtProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getCrtProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.crt, value);
			count++;
		}

		if (goodmodel.getDodgeProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getDodgeProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.dodge, value);
			count++;
		}

		if (goodmodel.getHpProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getHpProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.maxHp, value);
			count++;
		}

		if (goodmodel.getSpProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getSpProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.maxSp, value);
			count++;
		}

		if (goodmodel.getMpProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getMpProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.maxMp, value);
			count++;
		}
		if (goodmodel.getHitProbability() > 0) {
			randomValue = GenerateProbability.random.nextInt(GenerateProbability.gailv) + 1;
		}
		if (randomValue <= goodmodel.getHitProbability()) {
			value = GenerateProbability.randomIntValue(1, 100);
			addExtraProps(count, Property.hit, value);
			count++;
		}
		return count;
	}

	private void addExtraProps(int index, Property property, int value) {
		if (index >= GameConstant.StrengthenPinLimit) {
			return;
		}
		extraArray[index] = property;
		if (value > extraArrayValue[index]) {
			extraArrayValue[index] = value;
		}
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		if (getGoodModel().isEquipment()) {
			return PropertyAdditionType.zhuangbei;
		}
		return null;
	}

	public ResponseMsg getGoodsUpdateMsg() {
		int position = getPosition();
		if ((position >= Position.BodyGoodsBeginPostion && position <= Position.BodyGoodsEndPostion)) {
			return new BodyGoodsUpdata10180(this);
		} else if ((position >= Position.HorseGoodsBeginPostion && position <= Position.HorseGoodsEndPostion)) {
			return new HorseGoodsUpdata10078(this);
		} else if (position >= Position.BagGoodsBeginPostion && position <= Position.BagGoodsEndPostion) {
			return new GoodsUpdata10176(this);
		} else if (position >= Position.StorageGoodsBeginPostion && position <= Position.StorageGoodsEndPostion) {
			return new GoodsUpdata10176(this);
		} else if (position >= Position.AcrossBagBeginPostion && position <= Position.AcrossBagEndPostion) {
			return new GoodsUpdata10176(this);
		} else if (position >= Position.StallGoodsBeginPostion && position <= Position.StallGoodsEndPostion) {
			return new StallGoodsUpdata13002(this);
		}
		return null;
	}

	/**
	 * 最好的装备
	 * 
	 * @return
	 */
	public boolean isBestEquipmment() {
		return isAllStar() && isManxingGems() && getPingzhiColor() == GameConstant.StrengthenPinLimit && isMaxAddAttribute() && isMaxTotemAttribute();
	}

	/**
	 * 随机一个技能给舍利子
	 * 
	 * @param posigner
	 */
	public void randomSkillForShelizi(int posigner) {
		Goodmodel goodmodel = getGoodModel();
		if (goodmodel.isShelizi()) {

			if (getWugongId() != null && getWugongId() > 0) {
				// 重置武功类型成功时，读取“武功取值区间”，从中间取出
				// “0通用+X本门派代码的技能后，排除舍利子目前的武功ID，从剩余的区间中给出一种武功”附加到舍利子上
				Map<Integer, List<Integer>> map = goodmodel.getPosignerskillSheliziMap();
				List<Integer> list = map.get(posigner);
				List<Integer> list1 = map.get(0);
				Set<Integer> _tmpSet = new HashSet<Integer>(list);
				_tmpSet.addAll(list1);
				_tmpSet.remove(getWugongId());
				if (_tmpSet.size() > 0) {
					int n = GenerateProbability.randomIntValue(0, _tmpSet.size() - 1);
					Object[] arr = _tmpSet.toArray();
					setWugongId((Integer) arr[n]);
				}

			} else {
				Map<Integer, List<Integer>> map = goodmodel.getPosignerskillSheliziMap();
				List<Integer> list = map.get(posigner);
				if (list.size() > 0) {
					int n = GenerateProbability.randomIntValue(0, list.size() - 1);
					Integer skillid = list.get(n);
					if (skillid != null) {
						setWugongId(skillid);
					} else {
						list = map.get(0);
						if (list.size() > 0) {
							n = GenerateProbability.randomIntValue(0, list.size() - 1);
							skillid = list.get(n);
							if (skillid != null) {
								setWugongId(skillid);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取该物品使用封装对象
	 * 
	 * @return
	 */
	public UseGoodAction getUseGoodAction() {
		Goodmodel gm = this.getGoodModel();
		if (gm == null) {
			return null;
		}
		return gm.getUseGoodAction();
	}

	public boolean isBetterEquip(CharacterGoods oldEquip) {
		// 1： 只与同部位的道具进行比较
		// 2： 原部位上无装备，则新获得的装备属性更好
		// 3： 佩戴等级限制越高，则装备属性越好
		// 4： 佩戴等级限制相同，则品质颜色越高装备属性越好
		// 5： 颜色品质相同，则强化等级越高，属性越好
		// 6： 强化等级相同，则两者属性的好坏判断为相同，不弹出换装提示面板
		if (!getGoodModel().isEquipment() || !oldEquip.getGoodModel().isEquipment()) {
			return false;
		}
		int oldvalue = 10000 * oldEquip.getGoodModel().getLimitGrade() + 1000 * oldEquip.getPingzhiColor() + 100 * oldEquip.getJinjie();
		int newvalue = 10000 * getGoodModel().getLimitGrade() + 1000 * getPingzhiColor() + 100 * getJinjie();
		return oldvalue < newvalue;

	}

	@Override
	public int getGrade() {
		return getGoodModel().getGrade();
	}

	@Override
	public int getRepairMoney() {
		return getGoodModel().getRepairMoney();
	}

	private boolean isIgnoreBind = false;

	public boolean isIgnoreBind() {
		return isIgnoreBind;
	}

	private int toBadLostTime = 0;// 进入包裹后的失效时间 单位 秒

	public void setIgnoreBind(boolean isIgnoreBind) {
		this.isIgnoreBind = isIgnoreBind;
	}

	public boolean isOwner = true;// 掉落时是否有归属标识

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public int getToBadLostTime() {
		return toBadLostTime;
	}

	public void setToBadLostTime(int toBadLostTime) {
		this.toBadLostTime = toBadLostTime;
	}

	public boolean isNoticeGood = false;

	public boolean isNoticeGood() {
		return isNoticeGood;
	}

	public void setNoticeGood(boolean isNoticeGood) {
		this.isNoticeGood = isNoticeGood;
	}

	public String getStrengthenAfterBaseDesc() {
		return strengthenAfterBaseDesc;
	}

	/**
	 * 只能在进行强化天生属性的时候，才可以对该值进行赋值
	 * 
	 * @param strengthenAfterBaseDesc
	 */
	public void setStrengthenAfterBaseDesc(String strengthenAfterBaseDesc) {
		this.strengthenAfterBaseDesc = strengthenAfterBaseDesc;
	}

}
