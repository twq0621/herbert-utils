/**
 * 
 */
package net.snake.across.vehicle;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.fight.bean.CharacterVehicle;
import net.snake.gamemodel.hero.bean.Hero;

/***
 * 
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public class AcrossVehicleManager {
	private Map<String,AcrossCharacterVehicle> map=new ConcurrentHashMap<String,AcrossCharacterVehicle>();
	public synchronized AcrossCharacterVehicle addAcrossCharacterVehicle(AcrossEtc ae){
		String key=ae.getOldAreaId()+"_"+ae.getOldCharacterId();
		AcrossCharacterVehicle av=map.get(key);
		if(av!=null){
			return av;
		}
		 av=new AcrossCharacterVehicle(ae);
		 map.put(key, av);
		 return av;
	}
	/**
	 * 
	 * @param character
	 * @return
	 */
	public List<CharacterVehicle> getCharacterVehicle(Hero character) {
		AcrossEtc ae=character.getCharacterAcrossServerManager().getAce();
		AcrossCharacterVehicle acv=addAcrossCharacterVehicle(ae);
		return acv.getList();
	}
    public void clearVehicle(){
    	map.clear();
    }
}
