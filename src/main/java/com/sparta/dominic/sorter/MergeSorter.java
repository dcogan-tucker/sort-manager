package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

public class MergeSorter<T extends Comparable<T>> implements Sorter<T>
{
	@Override
	public List<T> sortListAsc(List<T> listToSort) throws NullListException, EmptyListException
	{
		sortList(listToSort, true);
		return listToSort;
	}

	@Override
	public List<T> sortListDesc(List<T> listToSort) throws NullListException, EmptyListException
	{
		sortList(listToSort, false);
		return listToSort;
	}

	private void sortList(List<T> listToSort, boolean asAscending) throws NullListException, EmptyListException
	{
		ListUtil.nullAndEmptyListChecker(listToSort);
		if (listToSort.size() < 2) {
			return;
		}
		int midPoint = listToSort.size()/2;
		List<T> left = new ArrayList<>(listToSort.subList(0, midPoint));
		List<T> right = new ArrayList<>(listToSort.subList(midPoint, listToSort.size()));

		// Sort left sublist
		sortList(left, asAscending);
		// Sort right sublist
		sortList(right, asAscending);
		// Merge sorted left and right sublist.
		merge(left, right, listToSort, asAscending);
	}

	private void merge(List<T> leftList, List<T> rightList, List<T> destinationList, boolean asAscending)
	{
		int leftIndex = 0;
		int rightIndex = 0;
		int listIndex = 0;

		while (leftIndex < leftList.size() && rightIndex < rightList.size())
		{
			int comparison = leftList.get(leftIndex).compareTo(rightList.get(rightIndex));
			if ((asAscending && comparison < 0) || (!asAscending && comparison > 0))
			{
				destinationList.set(listIndex++, leftList.get(leftIndex++));
			}
			else
			{
				destinationList.set(listIndex++, rightList.get(rightIndex++));
			}
		}
		while (leftIndex < leftList.size())
		{
			destinationList.set(listIndex++, leftList.get(leftIndex++));
		}
		while (rightIndex < rightList.size())
		{
			destinationList.set(listIndex++, rightList.get(rightIndex++));
		}
	}
}
