package com.sparta.dominic.sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleSorterTest extends SorterTest<BubbleSorter<Integer>, Integer>
{
	@Override
	protected BubbleSorter<Integer> newSorterInstance()
	{
		return new BubbleSorter<>();
	}

	@Override
	protected List<Integer> setList()
	{
		return new ArrayList<>(Arrays.asList(15, -3, 22, 100, -15, 65, 9, 4, 0, 1));
	}
}
