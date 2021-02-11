package com.sparta.dominic.sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSorterTest extends SorterTest<MergeSorter<Double>, Double>
{
	@Override
	protected MergeSorter<Double> newSorterInstance()
	{
		return new MergeSorter<>();
	}

	@Override
	protected List<Double> setList()
	{
		return new ArrayList<>(Arrays.asList(0.4, -11.1, 99.9, 54.321, 1.23, 3.14, -0d, 0d, -0.27, 1d));
	}
}
