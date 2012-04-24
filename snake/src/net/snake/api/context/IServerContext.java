package net.snake.api.context;

import net.snake.api.datatbl.IDataUpdateListner;
import net.snake.api.datatbl.StaticDataMnger;
import net.snake.api.dynplugin.IPluginMnger;

public interface IServerContext {
	public StaticDataMnger getStaticDataManager();

	public IPluginMnger getPluginMnger();

	//public ISublineCntlor getVirtualSublineCntlor();

	public void registDataUpdatelistener(String intrestingData, IDataUpdateListner listner);

	public void publishDataUpdate(String updata);
}
