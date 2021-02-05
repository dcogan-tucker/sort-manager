package com.sparta.dominic.sorter;

public class BubbleSorter implements Sorter
{
	/**
	 * Sorts the given array into ascending order using bubble sort.
	 *
	 * @param arrayToSort The array to sort.
	 *
	 * @return The sorted array.
	 */
	@Override
	public int[] sortArray(int[] arrayToSort)
	{
		// Check for null and empty arrays.
		SorterUtil.nullAndEmptyArrayChecker(arrayToSort);
		for (int i = 1; i < arrayToSort.length; i++)
		{
			boolean swapped = false;
			for (int j = 0; j < arrayToSort.length - i; j++)
			{
				if (arrayToSort[j] > arrayToSort[j + 1])
				{
					// Swap the elements at index j and j + 1 in the array.
					SorterUtil.swapElements(arrayToSort, j, (j + 1));
					swapped = true;
				}
			}
			// Array was already sorted.
			if (!swapped) break;
		}
		return arrayToSort;
	}
}
