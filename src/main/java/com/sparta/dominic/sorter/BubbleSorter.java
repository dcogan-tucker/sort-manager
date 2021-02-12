package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.util.ListUtil;

import java.util.List;

/**
 * Class that can sort given lists of any comparable type into ascending or descending order using bubble sort.
 *
 * @param <T> The type of Object to sort, must be of Comparable.
 */
public class BubbleSorter<T extends Comparable<T>> implements Sorter<T>
{
	/*
	 * Protected as should only be called by the SorterFactory.
	 */
	protected BubbleSorter() {}

	@Override
	public List<T> sortListAsc(List<T> listToSort) throws NullListException, EmptyListException
	{
		return sortList(listToSort, true);
	}

	@Override
	public List<T> sortListDesc(List<T> listToSort) throws NullListException, EmptyListException
	{
		return sortList(listToSort, false);
	}

	/*
	 * Helper method to sortList into ascending or descending order.
	 */
	private List<T> sortList(List<T> listToSort, boolean asAscending) throws NullListException, EmptyListException
	{
		// Check for null and empty arrays.
		ListUtil.nullAndEmptyListChecker(listToSort);
		for (int i = 1; i < listToSort.size(); i++)
		{
			boolean swapped = false;
			for (int j = 0; j < listToSort.size() - i; j++)
			{
				int comparison = listToSort.get(j).compareTo(listToSort.get(j + 1));
				if ((asAscending && comparison > 0) || (!asAscending && comparison < 0))
				{
					// Swap the elements at index j and j + 1 in the array.
					ListUtil.swapArrayElements(listToSort, j, (j + 1));
					swapped = true;
				}
			}
			// Array was already sorted.
			if (!swapped) break;
		}
		return listToSort;
	}
}
