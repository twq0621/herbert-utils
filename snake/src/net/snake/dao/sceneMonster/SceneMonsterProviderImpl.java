package net.snake.dao.sceneMonster;

import javax.crypto.ExemptionMechanism;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class SceneMonsterProviderImpl implements SceneMonsterProvider,InitializingBean{

	private List<SceneMonster> sceneMonstersGuoLvChongFu;
	private SceneMonsterDAO sceneMonsterDAO;
	
	
	public SceneMonsterDAO getSceneMonsterDAO() {
		return sceneMonsterDAO;
	}

	public void setSceneMonsterDAO(SceneMonsterDAO sceneMonsterDAO) {
		this.sceneMonsterDAO = sceneMonsterDAO;
	}

	@Override
	public List<SceneMonster> getSceneMonster() {
		return sceneMonstersGuoLvChongFu;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		sceneMonstersGuoLvChongFu = sceneMonsterDAO.selectSceneMonsters();
		System.out.println("sceneMonstersGuoLvChongFu="+sceneMonstersGuoLvChongFu.size());
	}

	@Override
	public List<SceneMonster> getSceneMonsters(int mapId) {
		SceneMonsterExample example = new SceneMonsterExample();
		example.createCriteria().andMapIdEqualTo(mapId).andMarkEqualTo(1);
		
		return sceneMonsterDAO.selectByExample(example);
	}

}
