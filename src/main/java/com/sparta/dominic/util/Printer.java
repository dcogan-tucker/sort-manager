package com.sparta.dominic.util;

/**
 * Wrapper class to System.out print methods.
 */
public final class Printer
{
	/**
	 * Print the given message to the console.
	 *
	 * @param message The message to print.
	 */
	public static void printMessage(String message)
	{
		System.out.println(message);
	}

	/**
	 * Print a formatted message to the console.
	 *
	 * @param formatString The format string.
	 * @param args The arguments referenced by the format string.
	 */
	public static void printFormattedMessage(String formatString, Object... args)
	{
		System.out.printf(formatString + "\n", args);
	}

	/**
	 * Print a empty line.
	 */
	public static void printNewLine()
	{
		System.out.println();
	}
}
