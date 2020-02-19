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

import java.util.HashSet;
import java.util.Set;

import javax.servlet.SessionTrackingMode;

import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyUtils
{

	public static void setTracking(ServletContextHandler servletContextHandler,
			SessionTrackingMode mode)
	{
		Set<SessionTrackingMode> sessionTracking = new HashSet<>();
		sessionTracking.add(mode);
		servletContextHandler.setSessionHandler(new SessionHandler());
		servletContextHandler.getSessionHandler()
				.setSessionTrackingModes(sessionTracking);
	}

}
