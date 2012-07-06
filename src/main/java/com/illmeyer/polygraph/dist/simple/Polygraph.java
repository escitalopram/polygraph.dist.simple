package com.illmeyer.polygraph.dist.simple;

import java.io.IOException;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.illmeyer.polygraph.core.Gun;
import com.illmeyer.polygraph.core.init.DefaultGunConfigurator;

public class Polygraph {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Resource res = new FileSystemResource(args[1]);
		XmlBeanFactory xbf = new XmlBeanFactory(res);

		DefaultGunConfigurator gc = new DefaultGunConfigurator();
		gc.setActiveTemplate(args[0]);
		gc.initialize();
		Gun g = (Gun)xbf.getBean("gun");
		g.setConfigurator(gc);
		g.initialize();
		g.trigger();
		g.destroy();
	}
}
