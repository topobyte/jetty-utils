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

import java.util.Map;

import de.topobyte.jetty.utils.pages.IndexGenerator;
import de.topobyte.jsoup.ContentGeneratable;
import de.topobyte.webgun.resolving.pathspec.PathSpecResolver;
import de.topobyte.webpaths.WebPath;

public class SimplePathResolver extends PathSpecResolver<WebContext>
{

	@Override
	public ContentGeneratable getGenerator(WebPath path, WebContext context,
			Map<String, String[]> parameters)
	{
		if (path.getNameCount() == 0) {
			return new IndexGenerator(context, path);
		}
		return super.getGenerator(path, context, parameters);
	}

}
