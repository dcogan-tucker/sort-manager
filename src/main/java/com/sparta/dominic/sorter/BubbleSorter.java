package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.util.ArrayUtil;
import com.sparta.dominic.util.Printer;

import java.util.List;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T>
{
	@Override
	public List<T> sortListAsc(List<T> listToSort)
	{
		return sortList(listToSort, true);
	}

	@Override
	public List<T> sortListDesc(List<T> listToSort)
	{
		return sortList(listToSort, false);
	}

	public List<T> sortList(List<T> listToSort, boolean asAscending)
	{
		// Check for null and empty arrays.
		try
		{
			ArrayUtil.nullAndEmptyListChecker(listToSort);
			for (int i = 1; i < listToSort.size(); i++)
			{
				boolean swapped = false;
				for (int j = 0; j < listToSort.size() - i; j++)
				{
					int comparison = listToSort.get(j).compareTo(listToSort.get(j + 1));
					if ((asAscending && comparison > 0) || (!asAscending && comparison < 0))
					{
						// Swap the elements at index j and j + 1 in the array.
						ArrayUtil.swapArrayElements(listToSort, j, (j + 1));
						swapped = true;
					}
				}
				// Array was already sorted.
				if (!swapped) break;
			}
		} catch (EmptyListException | NullListException e)
		{
			Printer.printMessage(e.getMessage());
		}
		return listToSort;
	}
}
