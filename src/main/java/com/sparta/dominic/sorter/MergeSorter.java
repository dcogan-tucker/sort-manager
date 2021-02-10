package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyArrayException;
import com.sparta.dominic.exception.NullArrayException;
import com.sparta.dominic.util.ArrayUtil;
import com.sparta.dominic.util.Printer;

public class MergeSorter implements Sorter
{

	/**
	 * Sorts the given array into ascending order using merge  sort.
	 *
	 * @param arrayToSort The array to sort.
	 */
	@Override
	public int[] sortArray(int[] arrayToSort)
	{
		try
		{
			ArrayUtil.nullAndEmptyArrayChecker(arrayToSort);
			sortAuxiliary(arrayToSort, 0, arrayToSort.length - 1);
		} catch (EmptyArrayException | NullArrayException e)
		{
			Printer.printMessage(e.getMessage());
		}
		return arrayToSort;
	}

	/*
	 * Auxiliary method to help perform the sort. This method uses recursion to sort the
	 * array.
	 */
	private static int[] sortAuxiliary(int[] arrayToSort, int startIndex, int  endIndex)
	{
		if (startIndex < endIndex)
		{
			// find midpoint between start and end index.
			int midPoint = startIndex + (endIndex - startIndex) / 2;

			// sort the left subarray.
			sortAuxiliary(arrayToSort, startIndex, midPoint);
			// sort the right subarray.
			sortAuxiliary(arrayToSort, (midPoint + 1), endIndex);

			// create left and right temp arrays and merge them.
			createAndMergeSubArrays(arrayToSort, startIndex, midPoint, endIndex);
		}
		return arrayToSort;
	}

	/*
	 * Creates sub arrays using the given index and merges them.
	 */
	private static void  createAndMergeSubArrays(int[] arrayToSort, int startIndex, int midPoint, int endIndex)
	{
		int[] leftArray = new int[midPoint - startIndex + 1];
		int[] rightArray = new int[endIndex - midPoint];

		// Copy the subarrays to temp arrays.
		ArrayUtil.copyArray(arrayToSort, startIndex, leftArray, 0);
		ArrayUtil.copyArray(arrayToSort, (midPoint + 1), rightArray, 0);

		// merge the temp arrays.
		int[] merged = ArrayUtil.mergeSortedArrays(leftArray, rightArray);
		// copy the merged array back to the original array.
		ArrayUtil.copyArray(merged, 0, arrayToSort, startIndex, merged.length);
	}
}
