package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyArrayException;
import com.sparta.dominic.exception.NullArrayException;

public class SorterUtil
{
	/*
	 * Checks if the array to be sorted is null or if it is empty and throws a
	 * corresponding exception.
	 */
	protected static void nullAndEmptyArrayChecker(int[] arrayToSort) throws EmptyArrayException, NullArrayException
	{
		if (arrayToSort == null)
		{
			throw new NullArrayException("Cannot sort as array is null!\n");
		}
		else if (arrayToSort.length == 0)
		{
			throw new EmptyArrayException("Cannot sort as array is empty!\n");
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

	/*
	 * Copy the source array from the source start index to the destination array starting from the
	 * destination index, copying until there are no more elements in the source, or the destination
	 * array has been filled.
	 */
	protected static void copyArray(int[] srcArray, int srcIndex, int[] destArray, int destIndex)
	{
		System.arraycopy(srcArray, srcIndex, destArray, destIndex, destArray.length - destIndex);
	}

	/*
	 * Copy the source array from the source start index to the destination array starting from the
	 * destination index, copying until there are no more elements in the source, or the number of
	 * elements copied is equal to the given length.
	 */
	protected static void copyArray(int[] srcArray, int srcIndex, int[] destArray, int destIndex, int length)
	{
		System.arraycopy(srcArray, srcIndex, destArray, destIndex, length);
	}
}
