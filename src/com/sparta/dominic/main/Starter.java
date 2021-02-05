package com.sparta.dominic.main;

import com.sparta.dominic.sorter.*;
import com.sparta.dominic.util.Printer;

import java.util.Arrays;

public class Starter
{
	public static void start()
	{
		Printer.printMessage("------BUBBLE SORT------");
		BubbleSorter bubbleSorter = new BubbleSorter();

		int[] arrayToSort = {1, 4, 6, -3, 9, 0, 55, 2, -100};
		Printer.printFormattedMessage("Array before sort: %s",
				Arrays.toString(arrayToSort));
		bubbleSorter.sortArray(arrayToSort);
		Printer.printFormattedMessage("Array after sort: %s\n",
				Arrays.toString(arrayToSort));

		try
		{
			int[] emptyArray = {};
			Printer.printFormattedMessage("Array before sort: %s",
					Arrays.toString(emptyArray));
			// empty array should throw IllegalArgumentException.
			bubbleSorter.sortArray(emptyArray);
		}
		catch (IllegalArgumentException e)
		{
			Printer.printMessage(e.getMessage());
		}

		try
		{
			int[] nullArray = null;
			Printer.printFormattedMessage("Array before sort: %s",
					Arrays.toString(nullArray));
			// null array should throw a NullPointerException.
			bubbleSorter.sortArray(nullArray);
		}
		catch (NullPointerException e)
		{
			Printer.printMessage(e.getMessage());
		}
	}
}
