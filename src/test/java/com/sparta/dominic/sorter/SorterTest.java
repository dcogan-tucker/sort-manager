package com.sparta.dominic.sorter;


import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class SorterTest<S extends Sorter<T>, T extends Comparable<T>>
{
	protected Sorter<T> sorter;
	protected List<T> listToSort;

	protected abstract S newSorterInstance();

	protected abstract List<T> setList();

	@BeforeEach
	public void setUp()
	{
		sorter = newSorterInstance();
		listToSort = setList();
	}

	@Test
	void sortAscNullListThrowsNullListException()
	{
		String expectedMessage = "Can not sort a null list.";
		Exception exception = assertThrows(NullListException.class, () -> sorter.sortListAsc(null));
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void sortDescNullListThrowsNullListException()
	{
		String expectedMessage = "Can not sort a null list.";
		Exception exception = assertThrows(NullListException.class, () -> sorter.sortListDesc(null));
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void sortAscEmptyListThrowsEmptyListException()
	{
		String expectedMessage = "Can not sort an empty list.";
		Exception exception = assertThrows(EmptyListException.class, () -> sorter.sortListAsc(new ArrayList<>()));
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void sortDescEmptyListThrowsEmptyListException()
	{
		String expectedMessage = "Can not sort an empty list.";
		Exception exception = assertThrows(EmptyListException.class, () -> sorter.sortListDesc(new ArrayList<>()));
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void sortListAscComparedToJavaCollectionsSort() throws NullListException, EmptyListException
	{
		// clone list and sort using collections library.
		List<T> expected = new ArrayList<>(listToSort);
		Collections.sort(expected);

		List<T> sortedList = sorter.sortListAsc(listToSort);

		MatcherAssert.assertThat(sortedList, containsInAnyOrder(expected.toArray()));
	}

	@Test
	void sortListDscComparedToJavaCollectionsSort() throws NullListException, EmptyListException
	{
		// clone list and sort using collections library.
		List<T> expected = new ArrayList<>(listToSort);
		expected.sort(Collections.reverseOrder());

		List<T> sortedList = sorter.sortListDesc(listToSort);

		MatcherAssert.assertThat(sortedList, containsInAnyOrder(expected.toArray()));
	}
}
