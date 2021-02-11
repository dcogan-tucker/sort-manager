package com.sparta.dominic.sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySorterTest extends SorterTest<BinarySorter<Character>, Character>
{
	@Override
	protected BinarySorter<Character> newSorterInstance()
	{
		return new BinarySorter<>();
	}

	@Override
	protected List<Character> setList()
	{
		return new ArrayList<>(Arrays.asList('w', 'x', 'i', 'v', 'a', 'c', 'f', 'b', 'p'));
	}
}
