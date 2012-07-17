/*
This file is part of the Polygraph bulk messaging framework
Copyright (C) 2012 Wolfgang Illmeyer

The Polygraph framework is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

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
