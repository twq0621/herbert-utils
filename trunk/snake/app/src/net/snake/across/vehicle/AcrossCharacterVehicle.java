/**
 * 
 */
package net.snake.across.vehicle;

import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.fight.bean.CharacterVehicle;


/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class AcrossCharacterVehicle {
public List<CharacterVehicle> list=new ArrayList<CharacterVehicle>();
public AcrossCharacterVehicle(AcrossEtc ae){
	CharacterVehicle cv = new CharacterVehicle();
	cv.setCharacterId(ae.getCharacterId());
	cv.setVehicleId(501);
	cv.setVehicleCount(10);
	list.add(cv);
	CharacterVehicle cv1 = new CharacterVehicle();
	cv1.setCharacterId(ae.getCharacterId());
	cv1.setVehicleId(502);
	cv1.setVehicleCount(10);
	list.add(cv1);
}
public List<CharacterVehicle> getList() {
	return list;
}
public void setList(List<CharacterVehicle> list) {
	this.list = list;
}

}
