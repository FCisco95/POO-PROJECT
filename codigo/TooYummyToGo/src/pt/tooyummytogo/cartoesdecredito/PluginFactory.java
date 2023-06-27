package pt.tooyummytogo.cartoesdecredito;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PluginFactory {
	
	public static List<CartoesDeCreditoPlugin> getPluginsList() {
		List<CartoesDeCreditoPlugin> plugins = new ArrayList<>();
		
		Properties pluginsProp = new Properties();
		
		try {
			pluginsProp.load(new FileInputStream(new File("plugins.properties")));
			
			String activatedPlugins = pluginsProp.getProperty("activatedPlugins");
			for (String pluginName : activatedPlugins.split(",")) {
				try {
					@SuppressWarnings("unchecked")
					Class<CartoesDeCreditoPlugin> klass = (Class<CartoesDeCreditoPlugin>) Class.forName(pluginName);
					Constructor<CartoesDeCreditoPlugin> cons = klass.getConstructor();
					CartoesDeCreditoPlugin p = cons.newInstance();
					plugins.add(p);
					
				} catch (ClassNotFoundException e) {
					//e.printStackTrace();
					//Do nothing
				} catch (NoSuchMethodException e) {
					//Do nothing
				} catch (SecurityException e) {
					//Do nothing
				} catch (InstantiationException e) {
					//Do nothing
				} catch (IllegalAccessException e) {
					//Do nothing
				} catch (IllegalArgumentException e) {
					//Do nothing
				} catch (InvocationTargetException e) {
					//Do nothing
				}
			}
			
			
		} catch (FileNotFoundException e) {
			//Do nothing
		} catch (IOException e) {
			//Do nothing
		}
		
		//plugins.add(new MonsterCardAdapter());
		//plugins.add(new PortugueseExpressAdapter());
		return plugins;
	}

}
