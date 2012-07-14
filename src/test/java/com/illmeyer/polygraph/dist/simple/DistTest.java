package com.illmeyer.polygraph.dist.simple;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import com.illmeyer.polygraph.core.Gun;
import com.illmeyer.polygraph.core.init.DefaultGunConfigurator;
import com.illmeyer.polygraph.messagetype.mail.Mail;
import com.illmeyer.polygraph.plumbing.CsvAddressSupplier;
import com.illmeyer.polygraph.plumbing.DiskCacheDispatcher;
import com.illmeyer.polygraph.plumbing.XmlTemplateDataProvider;
import com.illmeyer.polygraph.testtemplate.TestTemplate;

public class DistTest {
	@Test
	public void testMail() throws IOException {
		Gun g = new Gun();
		DefaultGunConfigurator gc = new DefaultGunConfigurator();
		gc.setActiveTemplate(TestTemplate.class.getName());
		gc.initialize();
		g.setConfigurator(gc);
		
		CsvAddressSupplier asup = new CsvAddressSupplier();
		asup.setInput(new URL("file:///mnt/ultrafett/home/escitalopram/galileo-workspace/polygraph/polygraph.dist.simple/src/main/dist/config/demo/input.csv"));
		asup.setAddressColumns(new HashSet<String>(Arrays.asList(new String[]{"email"})));
		g.setAddressSupplier(asup);
		
		DiskCacheDispatcher dcp = new DiskCacheDispatcher();
		dcp.setCacheDirectory(new File(System.getProperty("java.io.tmpdir")));
		g.setDispatcher(dcp);
		
		Mail m = new Mail();
		g.setMt(m);
		
		XmlTemplateDataProvider xtdp = new XmlTemplateDataProvider();
		xtdp.setXmlInput(new URL("file:///mnt/ultrafett/home/escitalopram/galileo-workspace/polygraph/polygraph.dist.simple/src/main/dist/config/demo/templatedata.xml"));
		g.setTemplateDataProvider(xtdp);
		
		g.initialize();
		g.trigger();
	}
}
