package com.sparta.dominic.main;

import com.sparta.dominic.exception.ChildNotFoundException;
import com.sparta.dominic.logger.SortManagerLogger;
import com.sparta.dominic.sorter.Sorter;
import com.sparta.dominic.sorter.SorterFactory;
import com.sparta.dominic.sorter.SorterType;
import com.sparta.dominic.tree.BinarySearchTree;
import com.sparta.dominic.tree.BinaryTreePrinter;
import com.sparta.dominic.util.ListUtil;
import com.sparta.dominic.util.Printer;

import java.util.ArrayList;
import java.util.Arrays;
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
		binaryTreeExamples();
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
	 * Binary tree examples.
	 */
	private static void binaryTreeExamples()
	{
		Printer.printMessage("-----BINARY TREES-----");
		BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
		List<Integer> listToAdd = new ArrayList<>(Arrays.asList(20, 15, 9, 18, 16, 35, 24, 60, 40));
		Printer.printFormattedMessage("Adding the elements %s to the list...", listToAdd);
		integerBinarySearchTree.addElements(listToAdd);
		Printer.printMessage("Printing the Binary Search Tree...\n");
		BinaryTreePrinter.print(integerBinarySearchTree);
		Printer.printFormattedMessage("The Binary Search Tree contains %d elements.",
				integerBinarySearchTree.getNumberOfElements());
		Integer elementToLookFor = 24;
		Printer.printFormattedMessage("The binary tree %s contain the element %d.",
				integerBinarySearchTree.hasElement(elementToLookFor) ? "does" : "does not", elementToLookFor);
		Integer elementToLookFor2 = 0;
		Printer.printFormattedMessage("The binary tree %s contain the element %d.",
				integerBinarySearchTree.hasElement(elementToLookFor2) ? "does" : "does not", elementToLookFor2);
		Integer elementToLookFor3 = 18;
		String result;
		try
		{
			Integer leftOfElement = integerBinarySearchTree.getLeftChild(elementToLookFor3);
			result = "is " + leftOfElement;
		} catch (Exception e)
		{
			result = "does not exist";
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
					+ integerBinarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
		}
		Printer.printFormattedMessage("The left child of the element %d  %s.",
				elementToLookFor3, result);

		Integer elementToLookFor4 = 35;
		try
		{
			Integer rightOfElement = integerBinarySearchTree.getRightChild(elementToLookFor4);
			result = "is " + rightOfElement;
		} catch (Exception e)
		{
			result = "does not exist";
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
					+ integerBinarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
		}
		Printer.printFormattedMessage("The right child of the element %d  %s.\n",
				elementToLookFor4, result);

		Integer elementToLookFor5 = 100;
		try
		{
			Integer rightOfElement = integerBinarySearchTree.getRightChild(elementToLookFor5);
			result = "is " + rightOfElement;
		} catch (Exception e)
		{
			result = "does not exist";
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
					+ integerBinarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
		}
		Printer.printFormattedMessage("The right child of the element %d  %s.\n",
				elementToLookFor5, result);

		Integer elementToLookFor6 = 16;
		try
		{
			Integer rightOfElement = integerBinarySearchTree.getRightChild(elementToLookFor6);
			result = "is " + rightOfElement;
		} catch (Exception e)
		{
			result = "does not exist";
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
					+ integerBinarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
		}
		Printer.printFormattedMessage("The right child of the element %d  %s.\n",
				elementToLookFor6, result);

		try
		{
			Integer leftOfElement = integerBinarySearchTree.getLeftChild(elementToLookFor6);
			result = "is " + leftOfElement;
		} catch (Exception e)
		{
			result = "does not exist";
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
					+ integerBinarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
		}
		Printer.printFormattedMessage("The left child of the element %d  %s.\n",
				elementToLookFor6, result);

		Printer.printMessage("Clearing the Binary Search Tree...");
		integerBinarySearchTree.clear();
		Printer.printMessage("Printing the Binary Search Tree...");
		BinaryTreePrinter.print(integerBinarySearchTree);
	}

	/*
	 * Binary sort for a random Integer, Double and Character list.
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
		} catch (Exception e)
		{
			sortedList = new ArrayList<>();
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using " + sorter.getClass().getSimpleName() + " " + e.getMessage(), e);
			Printer.printNewLine();
		}
		return sortedList;
	}
}
