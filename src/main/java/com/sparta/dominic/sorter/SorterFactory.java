package com.sparta.dominic.sorter;

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
