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

package de.topobyte.jetty.utils.widgets;

import static de.topobyte.jsoup.HTML.a;

import org.jsoup.nodes.Element;

import de.topobyte.cachebusting.CacheBusting;
import de.topobyte.jsoup.HTML;
import de.topobyte.jsoup.bootstrap3.components.Menu;
import de.topobyte.jsoup.components.A;
import de.topobyte.jsoup.components.Img;
import de.topobyte.pagegen.core.LinkResolver;
import de.topobyte.webpaths.WebPaths;

public class MainMenu extends Menu
{

	public MainMenu(LinkResolver resolver)
	{
		A brandIcon = a("/");
		brandIcon.attr("style", "float:left");
		navbarHeader.ap(brandIcon);

		Img image = HTML.img(resolver.getLink(
				WebPaths.get(CacheBusting.resolve("images/icon.svg"))));
		image.attr("width", "50px");
		image.attr("style", "padding:5px");
		brandIcon.ap(image);

		A brand = a("/");
		brand.appendText("Jetty Utils");

		addBrand(brand);

		Element linkProjects = a("").inner("Stuff");
		addMain(linkProjects, false);

		Element linkAbout = a("").inner("About");
		addRight(linkAbout, false);
	}

}
