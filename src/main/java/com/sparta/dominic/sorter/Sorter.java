package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;

import java.util.List;

public interface Sorter<T extends Comparable<T>>
{
	List<T> sortListAsc(List<T> listToSort) throws NullListException, EmptyListException;

	List<T> sortListDesc(List<T> listToSort) throws NullListException, EmptyListException;
}
