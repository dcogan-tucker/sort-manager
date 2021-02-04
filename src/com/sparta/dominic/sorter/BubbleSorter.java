package com.sparta.dominic.sorter;

public class BubbleSorter implements Sorter
{
	/**
	 * Sorts the given array into ascending order using bubble sort.
	 *
	 * @param arrayToSort The array to sort.
	 */
	@Override
	public int[] sortArray(int[] arrayToSort)
	{
		SorterUtil.nullAndEmptyArrayChecker(arrayToSort);
		for (int i = 1; i < arrayToSort.length; i++)
		{
			boolean swapped = false;
			for (int j = 0; j < arrayToSort.length - i; j++)
			{
				if (arrayToSort[j] > arrayToSort[j + 1])
				{
					SorterUtil.swapElements(arrayToSort, j, (j + 1));
					swapped = true;
				}
			}
			if (!swapped) break;
		}
		return arrayToSort;
	}
}
