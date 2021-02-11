package com.sparta.dominic.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortManagerLogger
{
	private static final Logger LOGGER = LogManager.getLogger(SortManagerLogger.class.getSimpleName());

	private SortManagerLogger() {}

	public static Logger getLogger()
	{
		return LOGGER;
	}
}
