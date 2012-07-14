package com.illmeyer.polygraph.dist.simple;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.illmeyer.polygraph.core.Gun;
import com.illmeyer.polygraph.core.init.DefaultGunConfigurator;

public class Polygraph {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ApplicationContext context= new FileSystemXmlApplicationContext(args[1]);
		DefaultGunConfigurator gc = new DefaultGunConfigurator();
		gc.setActiveTemplate(args[0]);
		gc.initialize();
		Gun g = (Gun)context.getBean("gun");
		g.setConfigurator(gc);
		g.initialize();
		g.trigger();
		g.destroy();
	}
}
