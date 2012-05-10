package net.snake.dao.transport2;

import java.util.List;



public class Transport2DataProviderImpl implements Transpert2DataProvider  {

	private List<Transport2> listTransport2s = null;
	private Transport2DAO transport2dao;
	public Transport2DAO getTransport2dao() {
		return transport2dao;
	}
	public void setTransport2dao(Transport2DAO transport2dao) {
		this.transport2dao = transport2dao;
	}
	@Override
	public List<Transport2> getTransport2List(int mapid) {
		Transport2Example example = new Transport2Example();
		example.createCriteria().andSceneIdEqualTo(mapid);
		listTransport2s = transport2dao.selectByExample(example);
		return listTransport2s;
	}



}
