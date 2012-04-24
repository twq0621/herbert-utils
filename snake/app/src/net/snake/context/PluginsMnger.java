package net.snake.context;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.api.dynplugin.IPluginMnger;
import net.snake.api.models.IModelLoader;

public class PluginsMnger implements IPluginMnger{

	private String servicesDir;
	private Map<String, Object> plugins = new HashMap<String, Object>();
	
	public void registPlugin(String name,Object plugin){
		plugins.put(name, plugin);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void loadPlugins(String workDir ) throws IOException{
		servicesDir= workDir+"service/";
		InputStream inputStream=null;
		inputStream=PluginsMnger.class.getClassLoader().getResourceAsStream("cnf/services.cnf");
		Reader reader=new InputStreamReader(inputStream);
		BufferedReader lineReader=new BufferedReader(reader);
		String line=lineReader.readLine();
		List<String> lines = new ArrayList<String>();
		while (line !=null) {
			lines.add(line);
			line = lineReader.readLine();
		}
		
		for (int i = 0; i < lines.size(); i++) {
			String services=lines.get(i);
			String[] svcArray = services.split(",");
			for (int j = 0; j < svcArray.length; j++) {
				String modeleName = svcArray[j];
				String modelJar = servicesDir+modeleName+".jar";
				URL url=new File(modelJar).toURI().toURL();
				URLClassLoader loader = new URLClassLoader(new URL[]{url});
				try {
					Class model = loader.loadClass("net.snake.game.service."+modeleName+"ModelLoader");
					IModelLoader<?> loader2=(IModelLoader<?>)model.newInstance();
					loader2.load(loader);
					Object service=loader2.getService();
					registPlugin(modeleName, service);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}


	@Override
	public void removePlugin(String name) {
		plugins.remove(name);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void loadPlugin(String name) throws IOException {
		String modeleName = name;
		String modelJar = servicesDir+modeleName+".jar";
		URL url=new File(modelJar).toURI().toURL();
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		try {
			Class model = loader.loadClass("net.snake.game.service."+modeleName+"ModelLoader");
			IModelLoader<?> loader2=(IModelLoader<?>)model.newInstance();
			loader2.load(loader);
			Object service=loader2.getService();
			registPlugin(modeleName, service);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getPlugin(String name) {
		return (T)plugins.get(name);
	}


}
