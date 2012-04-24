package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.chest.bean.ChestGoods;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;


/**
 * 宝箱奖池结果
 * 
 * @author serv_dev
 * 
 */

public class ChestGoodsManager {

	private static final Logger logger = Logger.getLogger(ChestGoodsManager.class);
	private static ChestGoodsDAO dao = new ChestGoodsDAO(SystemFactory.getCharacterSqlMapClient());

	//单例实现=====================================
	private static ChestGoodsManager instance;
	private ChestGoodsManager() {		
		
	}
	
	public static ChestGoodsManager getInstance() {
		if (instance == null) {
			instance=new ChestGoodsManager();
		}
		return instance;
	}
	//单例实现========================================
	
	
	public void addChsetGoods(ChestGoods chestGoods){
		try {
			dao.insertSelective(chestGoods);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void delChsetGoods(ChestGoods chestGoods){
		try {
			dao.deleteByPrimaryKey(chestGoods.getChestGoodsId());
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public CharacterGoods chestGoodsTOCharacterGoods( ChestGoods chestGoods){
		CharacterGoods characterGoods = CharacterGoods.initCharacterGood(1, GoodmodelManager.getInstance().get(chestGoods.getGoodmodelId()));
		characterGoods.setId(chestGoods.getId());                         
		characterGoods.setAdditionDesc(chestGoods.getAdditionDesc());     
		characterGoods.setBaseDesc(chestGoods.getBaseDesc());             
		characterGoods.setBind(chestGoods.getBind());                     
		characterGoods.setBornLv(chestGoods.getBornLv());                 
		characterGoods.setCount(chestGoods.getCount());                   
		characterGoods.setCurrDurability(chestGoods.getCurrDurability()); 
		characterGoods.setGoodmodelId(chestGoods.getGoodmodelId());       
		characterGoods.setInEquipId(chestGoods.getInEquipId());           
		//characterGoods.setStrengthenDesc(chestGoods.getStrengthenDesc());
		characterGoods.setStrengthenCount(chestGoods.getStrengthenCount());
		characterGoods.setStallIngot(chestGoods.getStallIngot());         
		characterGoods.setStallCopper(chestGoods.getStallCopper());       
		characterGoods.setQuickbarindex(chestGoods.getQuickbarindex());   
		characterGoods.setPosition(chestGoods.getPosition());
		
		characterGoods.setMaxDurability(chestGoods.getMaxDurability());   
		characterGoods.setIsUse(chestGoods.getIsUse());                   
		characterGoods.setInHorseId(chestGoods.getInHorseId());     
		characterGoods.setIsShow(chestGoods.getIsShow());
		
		return characterGoods;
	}
	
	public ChestGoods characterGoodsTOChestGoods(CharacterGoods characterGoods ){
		ChestGoods chestGoods = new ChestGoods();
		chestGoods.setId(characterGoods.getId());
		chestGoods.setAdditionDesc(characterGoods.getAdditionDesc());
		chestGoods.setBaseDesc(characterGoods.getBaseDesc());
		chestGoods.setBind(characterGoods.getBind());
		chestGoods.setBornLv(characterGoods.getBornLv());
		chestGoods.setCount(characterGoods.getCount());
		chestGoods.setCurrDurability(characterGoods.getCurrDurability());
		chestGoods.setGoodmodelId(characterGoods.getGoodmodelId());
		chestGoods.setInEquipId(characterGoods.getInEquipId());

		chestGoods.setStrengthenCount(characterGoods.getStrengthenCount());
		chestGoods.setStallIngot(characterGoods.getStallIngot());
		chestGoods.setStallCopper(characterGoods.getStallCopper());
		chestGoods.setQuickbarindex(characterGoods.getQuickbarindex());
		chestGoods.setPosition(characterGoods.getPosition());
		chestGoods.setMaxDurability(characterGoods.getMaxDurability());
		chestGoods.setIsUse(characterGoods.getIsUse());
		chestGoods.setInHorseId(characterGoods.getInHorseId());
		chestGoods.setIsShow(characterGoods.getIsShow());
		chestGoods.setPingzhi(characterGoods.getPingzhiColor());
		
		return chestGoods;
	}
	
	@SuppressWarnings("unchecked")
	public List<ChestGoods> getChestGoods(int characterid){
		List<ChestGoods> list =null;
		try {
			list = dao.selectByCharacterId(characterid);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		
		return list;
	}
}
