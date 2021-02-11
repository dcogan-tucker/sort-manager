package com.sparta.dominic.sorter;

import java.util.List;

public interface Sorter<T extends Comparable<T>>
{
	List<T> sortListAsc(List<T> listToSort);

	List<T> sortListDesc(List<T> listToSort);
}
