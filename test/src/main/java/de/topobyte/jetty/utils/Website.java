package de.topobyte.jetty.utils;

import de.topobyte.webgun.util.CacheBuster;

public class Website
{

	public static final String TITLE = "Villa Digitalkultur";

	public static final Website INSTANCE = new Website();

	private CacheBuster cacheBuster;

	public CacheBuster getCacheBuster()
	{
		return cacheBuster;
	}

	public void setCacheBuster(CacheBuster cacheBuster)
	{
		this.cacheBuster = cacheBuster;
	}

}
