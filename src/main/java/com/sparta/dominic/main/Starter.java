package com.sparta.dominic.main;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.logger.SortManagerLogger;
import com.sparta.dominic.sorter.Sorter;
import com.sparta.dominic.sorter.SorterFactory;
import com.sparta.dominic.sorter.SorterType;
import com.sparta.dominic.util.ListUtil;
import com.sparta.dominic.util.Printer;

import java.util.ArrayList;
import java.util.List;

public class Starter
{
	private static final SorterFactory<Integer> INTEGER_SORTER_FACTORY = new SorterFactory<>();
	private static final SorterFactory<Double> DOUBLE_SORTER_FACTORY = new SorterFactory<>();
	private static final SorterFactory<Character> CHARACTER_SORTER_FACTORY = new SorterFactory<>();

	public static void start()
	{
		bubbleSortExamples();
		mergeSortExamples();
		binarySortExamples();
	}

	/*
	 * Bubble sort for a random Integer, Double and Character list.
	 */
	private static void bubbleSortExamples()
	{
		Printer.printMessage("------BUBBLE SORT------");
		Printer.printMessage("----INTEGER SORTING----");
		Sorter<Integer> integerBubbleSorter = INTEGER_SORTER_FACTORY.createSorter(SorterType.BUBBLE);
		sortAndPrintRandomList(integerBubbleSorter, true, Integer.class);
		sortAndPrintRandomList(integerBubbleSorter, false, Integer.class);


		Printer.printMessage("----DOUBLE SORTING----");
		Sorter<Double> doubleBubbleSorter = DOUBLE_SORTER_FACTORY.createSorter(SorterType.BUBBLE);
		sortAndPrintRandomList(doubleBubbleSorter, true, Double.class);
		sortAndPrintRandomList(doubleBubbleSorter, false, Double.class);

		Printer.printMessage("---CHARACTER SORTING---");
		Sorter<Character> characterBubbleSorter = CHARACTER_SORTER_FACTORY.createSorter(SorterType.BUBBLE);
		sortAndPrintRandomList(characterBubbleSorter, true, Character.class);
		sortAndPrintRandomList(characterBubbleSorter, false, Character.class);
	}

	/*
	 * Merge sort for a random Integer, Double and Character list.
	 */
	private static void mergeSortExamples()
	{
		Printer.printMessage("------MERGE SORT------");
		Printer.printMessage("----INTEGER SORTING----");
		Sorter<Integer> integerMergeSorter = INTEGER_SORTER_FACTORY.createSorter(SorterType.MERGE);
		sortAndPrintRandomList(integerMergeSorter, true, Integer.class);
		sortAndPrintRandomList(integerMergeSorter, false, Integer.class);

		Printer.printMessage("----DOUBLE SORTING----");
		Sorter<Double> doubleMergeSorter = DOUBLE_SORTER_FACTORY.createSorter(SorterType.MERGE);
		sortAndPrintRandomList(doubleMergeSorter, true, Double.class);
		sortAndPrintRandomList(doubleMergeSorter, false, Double.class);

		Printer.printMessage("---CHARACTER SORTING---");
		Sorter<Character> characterMergeSorter = CHARACTER_SORTER_FACTORY.createSorter(SorterType.MERGE);
		sortAndPrintRandomList(characterMergeSorter, true, Character.class);
		sortAndPrintRandomList(characterMergeSorter, false, Character.class);
	}

	/*
	 * Binary tree sort for a random Integer, Double and Character list.
	 */
	private static void binarySortExamples()
	{
		Printer.printMessage("------BINARY SORT------");
		Printer.printMessage("----INTEGER SORTING----");
		Sorter<Integer> integerBinarySorter = INTEGER_SORTER_FACTORY.createSorter(SorterType.BINARY);
		sortAndPrintRandomList(integerBinarySorter, true, Integer.class);
		sortAndPrintRandomList(integerBinarySorter, false, Integer.class);

		Printer.printMessage("----DOUBLE SORTING----");
		Sorter<Double> doubleBinarySorter = DOUBLE_SORTER_FACTORY.createSorter(SorterType.BINARY);
		sortAndPrintRandomList(doubleBinarySorter, true, Double.class);
		sortAndPrintRandomList(doubleBinarySorter, false, Double.class);

		Printer.printMessage("---CHARACTER SORTING---");
		Sorter<Character> characterBinarySorter = CHARACTER_SORTER_FACTORY.createSorter(SorterType.BINARY);
		sortAndPrintRandomList(characterBinarySorter, true, Character.class);
		sortAndPrintRandomList(characterBinarySorter, false, Character.class);
	}

	/*
	 * Generate a random list of the specified class and sort it using the specified sorter into the specified order.
	 */
	private static <T extends Comparable<T>> void sortAndPrintRandomList(Sorter<T> sorter, boolean asAscending, Class<T> clazz)
	{
		List<T> listToSort;
		String order;
		if (clazz != Character.class)
		{
			listToSort = ListUtil.createRandomList(-50, 50, 10, clazz);
			order = asAscending ? "ascending" : "descending";
		}
		else
		{
			listToSort = ListUtil.createRandomList('a', 'z', 10, clazz);
			order =  asAscending ? "alphabetical" : "reverse alphabetical";
		}
		Printer.printFormattedMessage("Sorting the list %s into %s order using a %s...",
				listToSort, order, sorter.getClass().getSimpleName());
		long start = System.nanoTime();
		List<T> sortedList = sortList(listToSort, sorter, asAscending);
		long endTime = System.nanoTime();
		long timeTaken = (endTime - start) / 1000;
		Printer.printFormattedMessage("Operation took %d micro seconds.\nSorted List: %s\n", timeTaken, sortedList);
	}

	/*
	 * Sorts the given list using the specified sorter in the specified order.
	 */
	private static <T extends Comparable<T>> List<T> sortList(List<T> listToSort, Sorter<T> sorter, boolean asAscending)
	{
		List<T> sortedList;
		try
		{
			if (asAscending)
			{
				sortedList = sorter.sortListAsc(listToSort);
			} else
			{
				sortedList = sorter.sortListDesc(listToSort);
			}
		} catch (EmptyListException | NullListException e)
		{
			sortedList = new ArrayList<>();
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using " + sorter.getClass().getSimpleName() + " " + e.getMessage(), e);
			Printer.printNewLine();
		}
		return sortedList;
	}
}
