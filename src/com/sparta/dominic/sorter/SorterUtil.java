package com.sparta.dominic.sorter;

public class SorterUtil
{
	/*
	 * Checks if the array to be sorted is null or if it is empty and throws a
	 * corresponding exception.
	 */
	protected static void nullAndEmptyArrayChecker(int[] arrayToSort)
	{
		if (arrayToSort == null)
		{
			throw new NullPointerException("Cannot sort as array is null!\n");
		}
		else if (arrayToSort.length == 0)
		{
			throw new IllegalArgumentException("Cannot sort as array is empty!\n");
		}
	}

	/*
	 * Swaps the elements at the given indices for the given array.
	 */
	protected static void swapElements(int[] arrayToSort, int firstIndex, int secondIndex)
	{
		int temp = arrayToSort[firstIndex];
		arrayToSort[firstIndex] = arrayToSort[secondIndex];
		arrayToSort[secondIndex] = temp;
	}
}
