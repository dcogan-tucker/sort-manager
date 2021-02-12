package com.sparta.dominic.sorter;

/**
 * A Factory that can create Sorters.
 *
 * @param <T> The type of Object the sorter should sort, must be comparable.
 */
public class SorterFactory<T extends Comparable<T>>
{
	public Sorter<T> createSorter(SorterType sorterType)
	{
		switch (sorterType)
		{
			case BUBBLE:
				return new BubbleSorter<>();
			case MERGE:
				return new MergeSorter<>();
			case BINARY:
				return new BinarySorter<>();
			default:
				return null;
		}
	}
}
