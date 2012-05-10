package net.snake.dao.resicon;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class ResIconProviderImpl implements ResIconProvider, InitializingBean {

	private List<Resicon> resiconList;
	private List<Resicon> resiconListbig;
	private ResiconDAO resiconDAO;

	public ResiconDAO getResiconDAO() {
		return resiconDAO;
	}

	public void setResiconDAO(ResiconDAO resiconDAO) {
		this.resiconDAO = resiconDAO;
	}

	@Override
	public List<Resicon> getResicons() {
		return resiconDAO.getLianHeChaXun(0);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		resiconList = resiconDAO.getLianHeChaXun(0);
//		resiconListbig = resiconDAO.getLianHeChaXun(1);
//		System.out.println("resiconList---------" + resiconList.size());
//		System.out.println("resiconListbig---------" + resiconListbig.size());

	}

	@Override
	public List<Resicon> getResiconsBig() {
		return resiconDAO.getLianHeChaXun(1);
	}

}
