package net.snake.api.dynplugin;

import java.io.IOException;


public interface IPluginMnger {
	public void loadPlugins(String workDir)throws IOException;

	public void removePlugin(String name) ;

	public void loadPlugin(String name) throws IOException;
	
	public <T> T getPlugin(String name);
}
