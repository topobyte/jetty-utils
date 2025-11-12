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

package de.topobyte.jetty.utils.pages;

import static de.topobyte.jsoup.ElementBuilder.styleSheet;

import java.io.IOException;

import org.jsoup.nodes.Element;

import de.topobyte.cachebusting.CacheBusting;
import de.topobyte.jetty.utils.Website;
import de.topobyte.jetty.utils.widgets.MainMenu;
import de.topobyte.jsoup.FaviconUtil;
import de.topobyte.pagegen.bootstrap.Bootstrap3Generator;
import de.topobyte.webpaths.WebPath;
import de.topobyte.webpaths.WebPaths;

public class BaseGenerator extends Bootstrap3Generator
{

	public BaseGenerator(WebPath path)
	{
		super(path, Website.INSTANCE.getCacheBuster());
	}

	@Override
	public void generate() throws IOException
	{
		super.generate();
		Element head = builder.getHead();

		head.appendChild(styleSheet(
				getLink(WebPaths.get(CacheBusting.resolve("custom.css")))));

		FaviconUtil.addToHeader(head,
				CacheBusting.resolve("images/favicon-16.png"), 16);
		FaviconUtil.addToHeader(head,
				CacheBusting.resolve("images/favicon-32.png"), 32);
		FaviconUtil.addToHeader(head,
				CacheBusting.resolve("images/favicon-48.png"), 48);
		FaviconUtil.addToHeader(head,
				CacheBusting.resolve("images/favicon-64.png"), 64);
		FaviconUtil.addToHeader(head,
				CacheBusting.resolve("images/favicon-96.png"), 96);
	}

	protected void menu()
	{
		MainMenu menu = new MainMenu(this);
		getBuilder().getBody().prependChild(menu);
	}

}
