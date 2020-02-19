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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.jsoup.JsoupServletUtil;
import de.topobyte.webgun.resolving.PathResolver;
import de.topobyte.webpaths.WebPath;
import de.topobyte.webpaths.WebPaths;

@WebServlet("/*")
public class IndexServlet extends HttpServlet
{

	final static Logger logger = LoggerFactory.getLogger(IndexServlet.class);

	private static final long serialVersionUID = 1L;

	static List<PathResolver<WebContext>> resolvers = new ArrayList<>();
	static {
		resolvers.add(new SimplePathResolver());
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		WebPath path = WebPaths.get(uri);

		Map<String, String[]> parameters = request.getParameterMap();

		logger.info("URI: " + uri);
		logger.info("Path: " + path);

		WebContext context = new WebContext();

		ContentGeneratable generator = null;

		for (PathResolver<WebContext> resolver : resolvers) {
			generator = resolver.getGenerator(path, context, parameters);
			if (generator != null) {
				break;
			}
		}

		if (generator != null) {
			JsoupServletUtil.respond(response, generator);
		} else {
			ServletUtil.respond404(context, path, response);
		}
	}

}
