package net.snake.gamemodel.fight.logic;

import java.util.Collection;

import net.snake.consts.Position;
import net.snake.consts.Symbol;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.container.HorseBodyGoodsContiner;
import net.snake.gamemodel.goods.logic.container.IBody;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.heroext.lianti.logic.CharacterLianTiController;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;


/**
 *@author serv_dev
 */
public class SuggestManager extends CharacterController {

	public SuggestManager(Hero character) {
		super(character);
	}
	
	public int getSuggest() {
//		50008您的经脉尚未全通，建议尽快冲通|冲穴后将带来大幅的属性增加，这些属性是基础中的基础，无可替代. 这些基础属性在被各种百分比增幅后（例如：战意激昂带来的增幅），将会变得令人吃惊的强大 所以这些属性点远不止表面看上去那么简单
		if (!isFullJinmai()) {
			return 0;
		}
////		50009您还有装备不是满星，建议凑齐|单件满星装备将带来大幅的属性加成，满星比九星装备虽然只高出一星，但属性高出了非常多.再者全套满星装备时还可以额外激活一个“满星套装加成”，获得额外攻击+5%，防御+5%.您可以通过装备强化获得满星装备，如果装备强化中出现空星，那么建议洗掉重新进行强化。 如果嫌麻烦的话不妨试试跟寻宝鼠兑换松果，运气好的话，很快就能集齐全套满星装备。
		if (!isfullStarEquiment()) {
//			return 50009;
			return 1;
		}
////		50010您的坐骑不妨继续进阶|高阶坐骑将带来更多的属性加成，并且狮子以上的坐骑还可以帮助您释放江湖绝学中的组合技.您可以一次性释放多种江湖绝学，大大减轻了您的PK操作难度，此乃PK胜出的关键所在. 另外高阶坐骑的拉风造型，是王者的实力标志。还有就是：坐骑越高阶跑的越快哟~		
		if(!isMaxLevelHorse()){
//			return 50010;
			return 2;
		}
//	//	50011您的暗器尚未到顶，建议提升|在PK中别人每次只能出一招，当您有了暗器之后，相当于一次出两招，暗器的毒系伤害无视防御并且会持续的作用在对方身上，让您轻易掌握到PK的主动权.另外暗器在练级过程中同样好用，可以大大加快您的打怪速度，知道别人为什么等级那么高了吗
		if(!isMaxLevelHW()){
//			return 50011;
			return 3;
		}
	//	50012您的装备尚未镶满六品玉石|无论如何，一旦您收集到玉石，且不管它是否够高级，是否适合，都不妨立即镶嵌到装备上. 当您有了更高级玉石时，只需替换老玉石即可，镶嵌摘除随时随地可进行，且不收任何费用. 您不需要任何犹豫，玉石是永远不会被淘汰的装备，投资玉石比投资任何装备都要来的划算
		if(!isFullMaxGem()){
//			return 50012;
			return 4;
		}
		//护身符最高级
		if(!isMaxAmuletAndMaxSLZ()){
//			return 50013;
			return 5;
		}
		//炼体最高级
		if(!isFullLianti()){
			return 6;
		}
		//全部战纹
		if(!isFullZhanWen()){
			return 7;
		}
		//全部战纹且戒备
		if(!zhanwenIsAdap()){
			return 8;
		}
		
	//	50013您的护身符不妨进阶到顶且镶嵌舍利子|舍利子会带来武功层数的加成，这很重要。高层数的武功将可以压制对方的武功，例如：  如果您自身的点穴武功层数高于对方的抗点穴层数，那么层数高出越多，则点中几率越大   反而言之也是同理，当您抗点穴的武功比别人的点穴层数高，那么对方将几乎不能点中您//		   终极护身符：神龙护身符，所有武功层数+15层，顾名思义：就像是有神龙在保护您一样
		return -1;
	}
	
	
	
	private boolean isFullLianti(){
		CharacterLianTiController c = character.getLiantiController();
		if(c.getLiantiJingjieId()>=8&&c.isMaxProperties()){
			return true;
		}
		return false;
	}
	
	private boolean isFullZhanWen(){
		IBody body = character.getCharacterGoodController().getBodyGoodsContiner();
		Collection<CharacterGoods> goodsList = body.getGoodsList();
		for (CharacterGoods characterGoods : goodsList) {
			String totem = characterGoods.getTotem();
			if(!characterGoods.getGoodModel().isFuShenfu()&&(totem==null||totem.equals(""))){
				return false;
			}
		}
		return true;
	}
	private boolean zhanwenIsAdap(){
		IBody body = character.getCharacterGoodController().getBodyGoodsContiner();
		Collection<CharacterGoods> goodsList = body.getGoodsList();
		String zhanwen=null;
		for (CharacterGoods characterGoods : goodsList) {
			if(!characterGoods.getGoodModel().isFuShenfu()){
				String totem = characterGoods.getTotem();
				if(totem==null||totem.equals("")){
					return false;
				}else{
					String[] split = totem.split(Symbol.FENHAO);
					String id = split[0];
					if(zhanwen!=null&&id!=null&&!zhanwen.equals(id)){
						return false;
					}
					zhanwen=id;
				}
			}
		}
		return true;
	}
	
	/**
	 * 是否经脉全通
	 * @return
	 */
	private boolean isFullJinmai(){
		int datongjinmai = character.getMyChannelManager().getDatongjinmai();
		int datongjinmaiZhenLong = character.getMyChannelManager().getDatongjinmaiZhenLong();
		if(datongjinmai+datongjinmaiZhenLong>=16){
			return true;
		}
		return false;
	}
	/**
	 * 是否有装备还不满星
	 * @return
	 */
	private boolean isfullStarEquiment(){
		CharacterGoodController goodController = character.getCharacterGoodController();
		Collection<CharacterGoods> bodyGoodsList = goodController.getBodyGoodsList();
		Collection<Horse> horseCollection = character.getCharacterHorseController().getHorseCollection();
//		CharacterHorseController hc = character.getCharacterHorseController();
		//检查座骑装备
		for (Horse horse : horseCollection) {
			HorseBodyGoodsContiner goodsContainer = horse.getGoodsContainer();
			Collection<CharacterGoods> goodsList = goodsContainer.getGoodsList();
			for (CharacterGoods characterGoods : goodsList) {
				if(!characterGoods.isAllStar()){
					return false;
				}
			}
		}
		boolean hashsf=false;
		//检查身上装备
		for (CharacterGoods characterGoods : bodyGoodsList) {
			if(characterGoods.getPosition()==13){
				hashsf=true;
			}
			if(!characterGoods.isAllStar()&&characterGoods.getPosition()!=13){
				return false;
			}
		}
		IBody body = goodController.getBodyGoodsContiner();
		if(hashsf){
			if(body.getTotalCapacity()>bodyGoodsList.size()){
				//没装备满
				return false;
			}
		}else{
			if(body.getTotalCapacity()>bodyGoodsList.size()+1){
				//没装备满
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 坐骑最高阶
	 * @return
	 */
	private boolean isMaxLevelHorse(){
		CharacterHorseController horseController = character.getCharacterHorseController();
		Collection<Horse> horseCollection = horseController.getHorseCollection();
		for (Horse horse : horseCollection) {
			//己有最高阶座骑
			if(horse.getHorseModel().getJinjie()==0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 暗器最高等级
	 * @return
	 */
	private boolean isMaxLevelHW(){
		CharacterHiddenWeapon hw = character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon();
		if(hw!=null&&hw.getGrade()>=7&&hw.getXiuGrade()>=28){
			return true;
		}
		return false;
	}
	/**
	 * 嵌满了六品宝石
	 * @return
	 */
	private boolean isFullMaxGem(){
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		Collection<CharacterGoods> bodyGoodsList = characterGoodController.getBodyGoodsList();
		if(bodyGoodsList.size()<characterGoodController.getBodyGoodsContiner().getTotalCapacity()){
			return false;
		}
		for (CharacterGoods characterGoods : bodyGoodsList) {
			if(!checkEquimentGem(characterGoods)){
				return false;
			}
		}
		
		Collection<Horse> horseCollection = character.getCharacterHorseController().getHorseCollection();
		for (Horse horse : horseCollection) {
			HorseBodyGoodsContiner goodsContainer = horse.getGoodsContainer();
			Collection<CharacterGoods> goodsList = goodsContainer.getGoodsList();
			for (CharacterGoods horseGoods : goodsList) {
				if(!checkEquimentGem(horseGoods)){
					return false;
				}
			}
		}
		
		return true;
	}
	//是否嵌满了顶级宝石
	private boolean checkEquimentGem(CharacterGoods good){
		EquipmentPlayconfig config = EquipmentPlayconfigManager
		.getInstance().getEPlayconfigByGoodsId(
				good.getGoodModel().getId());
		if(config.getMaxStoneNum()<=0){
			//该装备不需要嵌宝石
			return true;
		}
		String inEquipId = good.getInEquipId();
		if(inEquipId==null||inEquipId.equals("")){
			return false;
		}
		String[] split = inEquipId.split(Symbol.FENHAO);
		if(split.length<config.getMaxStoneNum()){
			return false;
		}
		for (String string : split) {
			EquipmentPlayconfig config2 = EquipmentPlayconfigManager
			.getInstance().getEPlayconfigByGoodsId(Integer.parseInt(string.split(",")[0]));
			if(config2.getNextGoodmodelId()!=1){
				return false;
			}
		}		
		return true;
	}
	/**
	 * 护身符是最高级 
	 * @return
	 */
	private boolean isMaxAmuletAndMaxSLZ(){
		CharacterGoods goodsByPostion = character.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if(goodsByPostion!=null){
			EquipmentPlayconfig config = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(goodsByPostion.getGoodModel().getId());
			if(config.getNextGoodmodelId()==1){
				return true;
			}	
		}
		return true;
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}
	
}
