package net.snake.dao.publicjava;

import java.util.List;
import java.util.Map;

import net.snake.common.BeanTool;
import net.snake.dao.achieve.Achieve;
import net.snake.dao.achieve.AchieveDAO;
import net.snake.dao.achieve.AchieveExample;
import net.snake.dao.channel.Channel;
import net.snake.dao.channel.ChannelDAO;
import net.snake.dao.instance.Instance;
import net.snake.dao.instance.InstanceDAO;
import net.snake.dao.instance.InstanceExample;

import org.springframework.beans.factory.InitializingBean;

public class PublicJavaProviderImpl implements PublicJavaProvider,InitializingBean {
	
	
	private AchieveDAO  achieveDAO;
	private InstanceDAO instanceDAO;
	private ChannelDAO channelDAO;
	private PublicJavaDao publicJavaDao;
	public PublicJavaDao getPublicJavaDao() {
		return publicJavaDao;
	}

	public void setPublicJavaDao(PublicJavaDao publicJavaDao) {
		this.publicJavaDao = publicJavaDao;
	}

	private List<Channel> listChannel;
	
	public ChannelDAO getChannelDAO() {
		return channelDAO;
	}

	public void setChannelDAO(ChannelDAO channelDAO) {
		this.channelDAO = channelDAO;
	}

	public InstanceDAO getInstanceDAO() {
		return instanceDAO;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		listChannel = channelDAO.selectByExample(null);
	}

	public void setInstanceDAO(InstanceDAO instanceDAO) {
		this.instanceDAO = instanceDAO;
	}

	public AchieveDAO getAchieveDAO() {
		return achieveDAO;
	}

	public void setAchieveDAO(AchieveDAO achieveDAO) {
		this.achieveDAO = achieveDAO;
	}

	@Override
	public List<Achieve> getAchieve() {
		AchieveExample example = new AchieveExample();
		example.setOrderByClause("f_id");
		
		return achieveDAO.selectByExample(example);
	}

	@Override
	public List<Instance> getInstance() {
		InstanceExample example = new InstanceExample();
		example.setOrderByClause("instance_model_id");
		return instanceDAO.selectByExample(example);
	}

	@Override
	public Channel getChannel(int id) {
		Map<Integer,Channel> mapChannel=BeanTool.listToMap(listChannel, "id");
		return null;
	}

	@Override
	public List<Object> getSelectTable(String table) {
		
		return publicJavaDao.getSelectTable(table);
	}
	
}
