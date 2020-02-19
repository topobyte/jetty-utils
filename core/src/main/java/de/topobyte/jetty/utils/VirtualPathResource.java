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
import java.nio.file.Path;

import org.eclipse.jetty.util.resource.EmptyResource;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;

public class VirtualPathResource extends PathResource
{

	private String virtual;
	private int len;

	public VirtualPathResource(Path path, String virtual)
	{
		super(path);
		if (virtual.endsWith("/")) {
			this.virtual = virtual;
			len = virtual.length() - 1;
		} else {
			this.virtual = virtual + "/";
			len = virtual.length();
		}
	}

	@Override
	public Resource addPath(String subpath) throws IOException
	{
		if (subpath.startsWith(virtual)) {
			return super.addPath(subpath.substring(len));
		}
		return EmptyResource.INSTANCE;
	}

}
