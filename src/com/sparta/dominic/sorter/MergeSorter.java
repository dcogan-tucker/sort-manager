package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyArrayException;
import com.sparta.dominic.exception.NullArrayException;
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
			SorterUtil.nullAndEmptyArrayChecker(arrayToSort);
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
		SorterUtil.copyArray(arrayToSort, startIndex, leftArray, 0);
		SorterUtil.copyArray(arrayToSort, (midPoint + 1), rightArray, 0);

		// merge the temp arrays.
		int[] merged = merge(leftArray, rightArray);
		// copy the merged array back to the original array.
		SorterUtil.copyArray(merged, 0, arrayToSort, startIndex, merged.length);
	}

	/**
	 * Returns a merged sorted array from the given sorted arrays.
	 *
	 * @param firstArray The first sorted array.
	 * @param secondArray The second sorted array.
	 *
	 * @return A sorted array.
	 */
	public static int[] merge(int[] firstArray, int[] secondArray)
	{
		int[] mergeTo = new int[firstArray.length + secondArray.length];
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < mergeTo.length; i++)
		{

			if (firstIndex == firstArray.length)
			{
				// copy rest of second array as first array has all been copied.
				SorterUtil.copyArray(secondArray, secondIndex, mergeTo, i);
				break;
			}
			else if (secondIndex == secondArray.length)
			{
				// copy rest of first array as second array has all been copied.
				SorterUtil.copyArray(firstArray, firstIndex, mergeTo, i);
				break;
			}
			else if (firstArray[firstIndex] < secondArray[secondIndex])
			{
				mergeTo[i] = firstArray[firstIndex];
				firstIndex++;
			}
			else
			{
				mergeTo[i] = secondArray[secondIndex];
				secondIndex++;
			}
		}
		return mergeTo;
	}
}
