// Copyright 2020 Sebastian Kuerten
//
// This file is part of jetty-utils.
//
// jetty-utils is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// jetty-utils is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with jetty-utils. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.jetty.utils;

import java.nio.file.Path;
import java.util.EnumSet;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;

import de.topobyte.system.utils.SystemPaths;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.SessionTrackingMode;

public class RunEmbeddedServer
{

	public static void main(String[] args) throws Exception
	{
		new RunEmbeddedServer().start();
	}

	private Server server;

	public void start() throws Exception
	{
		server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8090);

		ServletContextHandler servletContextHandler = new ServletContextHandler();
		servletContextHandler.setContextPath("/");

		servletContextHandler.addEventListener(new InitListener());
		servletContextHandler.addFilter(RootFilter.class.getName(), "/*",
				EnumSet.noneOf(DispatcherType.class));

		servletContextHandler.addServlet(IndexServlet.class.getName(), "/*");

		// Make sure default servlet exists
		JettyUtils.addDefaultServlet(servletContextHandler);

		JettyUtils.setSessionTracking(servletContextHandler,
				SessionTrackingMode.COOKIE);

		Path pathProject = SystemPaths.CWD;

		ResourceFactory resourceFactory = ResourceFactory
				.of(servletContextHandler);
		Resource resources = resourceFactory
				.newResource(pathProject.resolve("build/static"));

		servletContextHandler.setBaseResource(resources);

		server.setHandler(servletContextHandler);

		server.setConnectors(new Connector[] { connector });
		server.start();
		server.join();
	}

}
