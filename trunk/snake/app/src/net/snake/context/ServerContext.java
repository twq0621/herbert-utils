package net.snake.context;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import net.snake.api.context.IServerContext;
//import net.snake.api.context.ISublineCntlor;
import net.snake.api.datatbl.IDataUpdateListner;
//import net.snake.api.datatbl.IStaticDataTbl;
import net.snake.api.datatbl.StaticDataMnger;
import net.snake.api.dynplugin.IPluginMnger;

public class ServerContext implements IServerContext{

	private StaticDataMnger dataMnger=new StaticDataManager();
	private PluginsMnger pluginsMnger = new PluginsMnger();
	
	private Map<String, List<IDataUpdateListner>> datalisteners=new HashMap<String, List<IDataUpdateListner>>();
	
	public void initialStaticData() throws Exception{
		dataMnger.setupDataTable(this);
	}
	
	public void initailDynaPlugins(String modelsDir) throws IOException{
		pluginsMnger.loadPlugins(modelsDir);
	}
	
	@Override
	public StaticDataMnger getStaticDataManager() {
		return dataMnger;
	}
	
	@Override
	public IPluginMnger getPluginMnger(){
		return pluginsMnger;
	}

	//@Override
	//public ISublineCntlor getVirtualSublineCntlor() {
	//	IStaticDataTbl<ISublineCntlor> cntlor=this.dataMnger.getStaticDataTbl(StaticDataMnger.TblName_VirtualSubline);
	//	return cntlor.getDataCntlor();
	//}

	@Override
	public void registDataUpdatelistener(String intrestingData,
			IDataUpdateListner listner) {
		List<IDataUpdateListner> listners=datalisteners.get(intrestingData);
		if (listners== null) {
			listners =new ArrayList<IDataUpdateListner>();
			datalisteners.put(intrestingData, listners);
		}
		listners.add(listner);
		
	}

	@Override
	public void publishDataUpdate(String updata) {
		List<IDataUpdateListner> listners=datalisteners.get(updata);
		if (listners ==null) {
			return ;
		}
		if (listners.isEmpty()) {
			return ;
		}
		for (Iterator<IDataUpdateListner> iterator = listners.iterator(); iterator.hasNext();) {
			IDataUpdateListner iDataUpdateListner =  iterator
					.next();
			iDataUpdateListner.syncDataUpdate(updata, dataMnger);
			
		}
		
	}


}
