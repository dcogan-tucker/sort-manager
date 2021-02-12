package com.sparta.dominic.main;

import com.sparta.dominic.logger.SortManagerLogger;
import com.sparta.dominic.sorter.Sorter;
import com.sparta.dominic.sorter.SorterFactory;
import com.sparta.dominic.sorter.SorterType;
import com.sparta.dominic.util.ListUtil;
import com.sparta.dominic.util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Starter
{
	private static final Scanner SCANNER = new Scanner(System.in);

	private static SorterFactory sorterFactory;
	private static Sorter sorter;
	private static List listToSort;
	private static List sortedList;

	private static boolean asAscending;
	private static int minValue;
	private static int maxValue;
	private static int length;
	private static Class typeClass;

	public static void start()
	{
		start: while (true)
		{
			typeSelection();
			sortSelection();
			parameterSelection();
			generateArrayToSort();
			printListAndSortDetails();

			while (true)
			{
				Printer.printMessage("Would you like to sort another random array?");
				if (SCANNER.hasNextLine())
				{
					String input = SCANNER.next().toLowerCase();
					switch (input)
					{
						case "yes":
						case "y":
							continue start;
						case "no":
						case "n":
							break start;
						default:
							Printer.printMessage("Please input yes or no.");
					}
				}
			}
		}
	}

	/*
	 * Select type of value to sort.
	 */
	private static void typeSelection()
	{
		while (true)
		{
			Printer.printMessage("Select What Type Of Elements To Sort:\n" +
					"1 : Integers\n" +
					"2 : Doubles\n" +
					"3 : Characters");

			if (SCANNER.hasNextInt())
			{
				int typeSelection = SCANNER.nextInt();
				boolean typeSelectionSuccessful = true;
				switch (typeSelection)
				{
					case 1:
						sorterFactory = new SorterFactory<Integer>();
						typeClass = Integer.class;
						break;
					case 2:
						sorterFactory = new SorterFactory<Double>();
						typeClass = Double.class;
						break;
					case 3:
						sorterFactory = new SorterFactory<Character>();
						typeClass = Character.class;
						break;
					default:
						typeSelectionSuccessful = false;
						break;
				}
				if (typeSelectionSuccessful)
					break;
			}
			Printer.printMessage("Please select one of the numbered options.");
		}
	}

	/*
	 * Select sort method.
	 */
	private static void sortSelection()
	{
		while (true)
		{
			Printer.printMessage("Select What Sort Algorithm To Use:\n" +
								 "1 : Bubble\n" +
					  			 "2 : Merge\n" +
			   					 "3 : Binary");

			if (SCANNER.hasNextInt())
			{
				int sorterSelection = SCANNER.nextInt();
				boolean sorterSelectionSuccessful = true;
				switch (sorterSelection)
				{
					case 1:
						sorter = sorterFactory.createSorter(SorterType.BUBBLE);
						break;
					case 2:
						sorter = sorterFactory.createSorter(SorterType.MERGE);
						break;
					case 3:
						sorter = sorterFactory.createSorter(SorterType.BINARY);
						break;
					default:
						sorterSelectionSuccessful = false;
						break;
				}
				if (sorterSelectionSuccessful)
					break;
			}
			Printer.printMessage("Please select one of the numbered options.");
		}
	}

	/*
	 * Select parameters to generate array.
	 */
	private static void parameterSelection()
	{
		lengthSelection();
		minValueSelection();
		maxValueSelection();
		orderingSelection();
	}

	/*
	 * The length of the list to generate.
	 */
	private static void lengthSelection()
	{
		while (true)
		{
			Printer.printMessage("Input length of randomly generated array:");
			if (SCANNER.hasNextInt())
			{
				length = SCANNER.nextInt();
				break;
			}
			Printer.printMessage("Please make sure the input is a valid integer.");
		}
	}

	/*
	 * The min value to include in the list.
	 */
	private static void minValueSelection()
	{
		while (true)
		{
			Printer.printMessage("Input the minimum value to be generated:");
			if (SCANNER.hasNextLine())
			{
				String input = SCANNER.next();
				if (input.length() == 1)
				{
					if (input.matches("\\d+"))
					{
						minValue = Integer.parseInt(input);
						break;
					}
					else if (input.matches("\\w"))
					{
						minValue = input.charAt(0);
						break;
					}
				}
			}
			Printer.printMessage("Please make sure the input is valid.");
		}
	}

	/*
	 * The max value to include in the list.
	 */
	private static void maxValueSelection()
	{
		while (true)
		{
			Printer.printMessage("Input the maximum value to be generated:");
			if (SCANNER.hasNextLine())
			{
				String input = SCANNER.next();
				if (input.length() == 1)
				{
					if (input.matches("\\d+"))
					{
						maxValue = Integer.parseInt(input);
						break;
					}
					else if (input.matches("\\w"))
					{
						maxValue = input.charAt(0);
						break;
					}
				}
			}
			Printer.printMessage("Please make sure the input is valid.");
		}
	}

	/*
	 * Select the sorting order.
	 */
	private static void orderingSelection()
	{
		ordering: while (true)
		{
			Printer.printMessage("The sorted array should be:\n" +
					"1 : Ascending\n" +
					"2 : Descending");
			if (SCANNER.hasNextInt())
			{
				switch (SCANNER.nextInt())
				{
					case 1:
						asAscending = true;
						break ordering;
					case 2:
						asAscending = false;
						break ordering;
					default:
						Printer.printMessage("Please make sure the input is valid.");
				}
			}
		}
	}

	/*
	 * Generate array to sort.
	 */
	private static void generateArrayToSort()
	{
		listToSort = ListUtil.createRandomList(minValue, maxValue, length, typeClass);
	}

	/*
	 * Prints the list and details about it.
	 */
	private static void printListAndSortDetails()
	{
		Printer.printFormattedMessage("Sorting the list %s into %s order using a %s...",
				listToSort, asAscending ? "ascending" : "descending", sorter.getClass().getSimpleName());
		long start = System.nanoTime();
		sortList();
		long endTime = System.nanoTime();
		long timeTaken = (endTime - start) / 1000;
		Printer.printFormattedMessage("Operation took %d micro seconds.\nSorted List: %s\n", timeTaken, sortedList);
	}

	/*
	 * Sorts the current list using the current sorter.
	 */
	private static <T extends Comparable<T>> void sortList()
	{
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
	}
}
