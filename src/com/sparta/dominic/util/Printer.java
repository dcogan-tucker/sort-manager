package com.sparta.dominic.util;

public class Printer
{
	public static void printMessage(String message)
	{
		System.out.println(message);
	}

	public static void printFormattedMessage(String message, Object... values)
	{
		System.out.printf(message + "\n", values);
	}
}
