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
		Printer.printMessage("Array before sort: " + Arrays.toString(arrayToSort));
		bubbleSorter.sortArray(arrayToSort);
		Printer.printMessage("Array after sort: " + Arrays.toString(arrayToSort) + "\n");

		Printer.printMessage("------MERGE SORT------");
		MergeSorter mergeSorter = new MergeSorter();

		int[] arrayToSortTwo = {5, 7, 9, 1, -10, 5, 0, 0};
		Printer.printMessage("Array before sort: " + Arrays.toString(arrayToSortTwo));
		mergeSorter.sortArray(arrayToSortTwo);
		Printer.printMessage("Array after sort: " + Arrays.toString(arrayToSortTwo) + "\n");

		Printer.printMessage("------QUICK SORT------");
		QuickSorter quickSorter = new QuickSorter();

		int[] arrayToSortThree = {3, 67, 9, -12, -243, 5, 0, 1};
		Printer.printMessage("Array before sort: " + Arrays.toString(arrayToSortThree));
		quickSorter.sortArray(arrayToSortThree);
		Printer.printMessage("Array after sort: " + Arrays.toString(arrayToSortThree) + "\n");
	}
}
