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

import static de.topobyte.jsoup.ElementBuilder.script;
import static de.topobyte.jsoup.ElementBuilder.styleSheet;

import java.io.IOException;

import de.topobyte.jsoup.FaviconUtil;
import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.components.Div;
import de.topobyte.jsoup.components.Head;
import de.topobyte.jsoup.components.Meta;
import de.topobyte.pagegen.core.BaseFileGenerator;
import de.topobyte.pagegen.core.Context;
import de.topobyte.pagegen.core.LinkResolver;
import de.topobyte.webpaths.WebPath;
import de.topobyte.webpaths.WebPaths;

public class BootstrapGenerator extends BaseFileGenerator
{

	public BootstrapGenerator(Context context, WebPath path)
	{
		super(context, path);
	}

	private static String[] cssPaths = new String[] {
			"client/bootstrap/css/bootstrap.min.css" };

	private static String[] jsPaths = new String[] {
			"client/jquery/jquery.min.js",
			"client/bootstrap/js/bootstrap.min.js" };

	public static void setupHeader(LinkResolver resolver, Head head)
	{
		Meta meta = head.ac(HTML.meta());
		meta.attr("http-equiv", "content-type");
		meta.attr("content", "text/html; charset=utf-8");

		meta = head.ac(HTML.meta());
		meta.attr("name", "viewport");
		meta.attr("content", "width=device-width, initial-scale=1");

		for (String cssPath : cssPaths) {
			head.appendChild(
					styleSheet(resolver.getLink(WebPaths.get(cssPath))));
		}

		for (String jsPath : jsPaths) {
			head.appendChild(script(resolver.getLink(WebPaths.get(jsPath))));
		}
	}

	@Override
	public void generate() throws IOException
	{
		Head head = builder.getHead();

		setupHeader(this, head);

		String faviconPath = getLink(context.getFavIcon());
		FaviconUtil.addToHeader(head, faviconPath);

		/*
		 * Main Content
		 */

		content = new Div("container");
		builder.getBody().appendChild(content);
	}

}