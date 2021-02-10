package com.sparta.dominic.main;

import com.sparta.dominic.sorter.*;
import com.sparta.dominic.printer.Printer;

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
		int[] sortedArray = bubbleSorter.sortArray(arrayToSort);
		Printer.printFormattedMessage("Array after sort: %s\n",
				Arrays.toString(sortedArray));

		int[] emptyArray = {};
		Printer.printFormattedMessage("Array before sort: %s",
				Arrays.toString(emptyArray));
		// empty array should throw IllegalArgumentException and be caught.
		bubbleSorter.sortArray(emptyArray);

		int[] nullArray = null;
		Printer.printFormattedMessage("Array before sort: %s",
				Arrays.toString(nullArray));
		// null array should throw IllegalArgumentException and be caught.
		bubbleSorter.sortArray(nullArray);

		Printer.printMessage("------MERGE SORT------");
		MergeSorter mergeSorter = new MergeSorter();

		int[] arrayToSort2 = {1, 7, 10, 15, 11, 4, 1, 0, -22};
		Printer.printFormattedMessage("Array before sort: %s",
				Arrays.toString(arrayToSort2));
		int[] sortedArray2 = mergeSorter.sortArray(arrayToSort2);
		Printer.printFormattedMessage("Array after sort: %s\n",
				Arrays.toString(sortedArray2));


		Printer.printFormattedMessage("Array before sort: %s",
				Arrays.toString(emptyArray));
		// empty array should throw IllegalArgumentException and be caught.
		mergeSorter.sortArray(emptyArray);

		Printer.printFormattedMessage("Array before sort: %s",
				Arrays.toString(nullArray));
		// null array should throw IllegalArgumentException and be caught.
		mergeSorter.sortArray(nullArray);
	}
}
