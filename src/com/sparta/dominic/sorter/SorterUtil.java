package com.sparta.dominic.sorter;

public class SorterUtil
{
	/**
	 * Checks if the array to be sorted is null or if it is empty and throws a
	 * corresponding exception.
	 *
	 * @param arrayToSort The array that is to be sorted.
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

	/**
	 * Swaps the element at the given indices for the given array.
	 *
	 * @param arrayToSort The array to do the swap on.
	 * @param firstIndex The index of the first value.
	 * @param secondIndex The index of the second value.
	 */
	protected static void swapElements(int[] arrayToSort, int firstIndex, int secondIndex)
	{
		int temp = arrayToSort[firstIndex];
		arrayToSort[firstIndex] = arrayToSort[secondIndex];
		arrayToSort[secondIndex] = temp;
	}
}
