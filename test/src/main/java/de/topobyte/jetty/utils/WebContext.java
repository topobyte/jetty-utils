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

import de.topobyte.cachebusting.CacheBusting;
import de.topobyte.pagegen.core.Context;
import de.topobyte.webpaths.WebPath;
import de.topobyte.webpaths.WebPaths;

public class WebContext implements Context
{

	private WebPath favicon = WebPaths.get(CacheBusting.resolve("favicon.ico"));

	@Override
	public WebPath getFavIcon()
	{
		return favicon;
	}

}
