package net.snake.dao.publicjava;

import java.util.List;

import net.snake.dao.achieve.Achieve;
import net.snake.dao.channel.Channel;
import net.snake.dao.instance.Instance;



public interface PublicJavaProvider {

	
	public List<Achieve> getAchieve();
	public List<Instance> getInstance();
	public Channel getChannel(int id);
	public List<Object> getSelectTable(String table);
}
