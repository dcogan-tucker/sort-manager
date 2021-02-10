package com.sparta.dominic.util;

import com.sparta.dominic.exception.EmptyArrayException;
import com.sparta.dominic.exception.NullArrayException;

/**
 * Class that provides static utility methods for arrays.
 */
public final class ArrayUtil
{
	/**
	 * Checks the validity of the given array.
	 *
	 * @param arrayToSort The array to check.
	 * @throws EmptyArrayException If the given array is empty.
	 * @throws NullArrayException If the array is null.
	 */
	public static void nullAndEmptyArrayChecker(int[] arrayToSort) throws EmptyArrayException, NullArrayException
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

	/**
	 * Swap the elements at the given indices for the given array.
	 *
	 * @param arrayForSwap The array to swap the elements in.
	 * @param firstIndex The index of the first element.
	 * @param secondIndex The index of the second element.
	 */
	public static void swapArrayElements(int[] arrayForSwap, int firstIndex, int secondIndex)
	{
		int temp = arrayForSwap[firstIndex];
		arrayForSwap[firstIndex] = arrayForSwap[secondIndex];
		arrayForSwap[secondIndex] = temp;
	}

	/**
	 * Returns a merged sorted array from the given sorted arrays.
	 *
	 * @param firstSortedArray The first sorted array.
	 * @param secondSortedArray The second sorted array.
	 *
	 * @return A sorted array.
	 */
	public static int[] mergeSortedArrays(int[] firstSortedArray, int[] secondSortedArray)
	{
		int[] mergedArray = new int[firstSortedArray.length + secondSortedArray.length];
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < mergedArray.length; i++)
		{

			if (firstIndex == firstSortedArray.length)
			{
				// copy rest of second array as first array has all been copied.
				ArrayUtil.copyArray(secondSortedArray, secondIndex, mergedArray, i);
				break;
			}
			else if (secondIndex == secondSortedArray.length)
			{
				// copy rest of first array as second array has all been copied.
				ArrayUtil.copyArray(firstSortedArray, firstIndex, mergedArray, i);
				break;
			}
			else if (firstSortedArray[firstIndex] < secondSortedArray[secondIndex])
			{
				mergedArray[i] = firstSortedArray[firstIndex];
				firstIndex++;
			}
			else
			{
				mergedArray[i] = secondSortedArray[secondIndex];
				secondIndex++;
			}
		}
		return mergedArray;
	}

	/**
	 * Copy the source array from the source start index to the destination array starting from the
	 * destination index, copying until there are no more elements in the source, or the destination
	 * array has been filled.
	 *
	 * @param srcArray The source to copy from.
	 * @param srcIndex The index to start copying from.
	 * @param destArray The destination to copy to.
	 * @param destIndex The index to start copying to.
	 */
	public static void copyArray(int[] srcArray, int srcIndex, int[] destArray, int destIndex)
	{
		System.arraycopy(srcArray, srcIndex, destArray, destIndex, destArray.length - destIndex);
	}

	/**
	 * Copy the source array from the source start index to the destination array starting from the
	 * destination index, copying until there are no more elements in the source, or the number of
	 * elements copied is equal to the given length.
	 *
	 * @param srcArray The source to copy from.
	 * @param srcIndex The index to start copying from.
	 * @param destArray The destination to copy to.
	 * @param destIndex The index to start copying to.
	 * @param length The number of elements to copy.
	 */
	public static void copyArray(int[] srcArray, int srcIndex, int[] destArray, int destIndex, int length)
	{
		System.arraycopy(srcArray, srcIndex, destArray, destIndex, length);
	}
}
