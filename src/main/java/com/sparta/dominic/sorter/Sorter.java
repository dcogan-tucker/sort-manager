package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;

import java.util.List;

public interface Sorter<T extends Comparable<T>>
{
	/**
	 * Returns the given list sorted in ascending order.
	 *
	 * @param listToSort The list to sort.
	 * @return The list sorted.
	 * @throws NullListException If the given list is null.
	 * @throws EmptyListException If the given list is empty.
	 */
	List<T> sortListAsc(List<T> listToSort) throws NullListException, EmptyListException;

	List<T> sortListDesc(List<T> listToSort) throws NullListException, EmptyListException;
}
