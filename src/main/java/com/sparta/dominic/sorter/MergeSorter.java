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
			int[] leftArray = new int[midPoint - startIndex + 1];
			int[] rightArray = new int[endIndex - midPoint];

			// Copy the subarrays to temp arrays.
			ArrayUtil.copyArray(arrayToSort, startIndex, leftArray, 0);
			ArrayUtil.copyArray(arrayToSort, (midPoint + 1), rightArray, 0);

			// Merge subarrays.
			int[] mergedArray = ArrayUtil.mergeSortedArrays(leftArray, rightArray);
			ArrayUtil.copyArray(mergedArray, 0, arrayToSort, startIndex, mergedArray.length);
		}
		return arrayToSort;
	}
}
