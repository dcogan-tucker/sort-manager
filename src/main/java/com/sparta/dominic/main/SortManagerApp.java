package com.sparta.dominic.main;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.sparta.dominic.exception.ChildNotFoundException;
import com.sparta.dominic.logger.SortManagerLogger;
import com.sparta.dominic.sorter.*;
import com.sparta.dominic.tree.BinarySearchTree;
import com.sparta.dominic.tree.BinaryTreePrinter;
import com.sparta.dominic.util.ListUtil;
import com.sparta.dominic.util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortManagerApp
{
	private static final Scanner NUMBER_SCANNER = new Scanner(System.in);
	private static final Scanner STRING_SCANNER = new Scanner(System.in);

	public static void launch()
	{
		mainLoop();
	}

	/*
	 * Main Loop For App
	 */
	private static void mainLoop()
	{
		while (true)
		{
			boolean exit = !selectPrimaryFunction();
			if (exit) break;
		}
		Printer.printMessage("Program Closing...");
		NUMBER_SCANNER.close();
		STRING_SCANNER.close();
	}

	/*
	 * Loop For Selecting Primary Function.
	 */
	private static boolean selectPrimaryFunction()
	{
		while (true)
		{
			Printer.printMessage("Select Option:\n" +
								 "1 : Sorters\n" +
								 "2 : Binary Trees\n" +
								 "3 : Exit");
			boolean selectionSuccessful = true;
			if (NUMBER_SCANNER.hasNextInt())
			{
				ComparableType type;
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						type = typeSelection();
						sorterLoop(type);
						break;
					case 2:
						type = typeSelection();
						treeLoop(type);
						break;
					case 3:
						return false;
					default:
						selectionSuccessful = false;
				}
			}
			else
			{
				NUMBER_SCANNER.next();
			}

			if (selectionSuccessful)
				break;
			Printer.printMessage("Invalid Input. Try Again.");
		}
		return true;
	}

	/*
	 * Loop For Type Selection.
	 */
	private static ComparableType typeSelection()
	{
		while (true)
		{
			Printer.printMessage("Select Element Type:\n" +
								 "1 : Integer\n" +
								 "2 : Double\n" +
								 "3 : Character");
			if (NUMBER_SCANNER.hasNextInt())
			{
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						return ComparableType.INTEGER;
					case 2:
						return ComparableType.DOUBLE;
					case 3:
						return ComparableType.CHARACTER;
				}
			}
			else
			{
				NUMBER_SCANNER.next();
			}
			Printer.printMessage("Invalid Input. Try Again.");
		}
	}

	/*
	 * Loop for sorters.
	 */
	private static void sorterLoop(ComparableType type)
	{
		sortFactoryCreation:
		while (true)
		{
			SorterFactory<? extends Comparable<?>> sorterFactory = createSortFactory(type);
			sorterSelection:
			while (true)
			{
				Sorter sorter = createSorter(sorterFactory);

				arrayGeneration:
				while (true)
				{
					List listToSort = generateList(type);
					boolean asAscending = getListOrdering();

					printListAndSortResults(listToSort, asAscending, sorter);
					options: while (true)
					{
						Printer.printFormattedMessage("Perform Another Random List Sort Using the %s?",
								sorter.getClass().getSimpleName());
						if (STRING_SCANNER.hasNextLine())
						{
							switch (STRING_SCANNER.nextLine().toLowerCase())
							{
								case "no":
								case "n":
									break arrayGeneration;
								case "yes":
								case "y":
									break options;
							}
						}
						Printer.printMessage("Invalid Input. Try Again.");
					}
				}

				while (true)
				{
					Printer.printMessage("Select Option:\n" +
							"1 : New Element Type\n" +
							"2 : New Sorter\n" +
							"3 : Exit Sorters");
					if (NUMBER_SCANNER.hasNextInt())
					{
						switch (NUMBER_SCANNER.nextInt())
						{
							case 1:
								type = typeSelection();
								continue sortFactoryCreation;
							case 2:
								continue sorterSelection;
							case 3:
								Printer.printMessage("Exiting Sorters...");
								break sortFactoryCreation;
						}
					}
				}
			}
		}
	}

	/*
	 * Creates a sort factory of the given type.
	 */
	private static SorterFactory<? extends Comparable<?>> createSortFactory(ComparableType type)
	{
		switch (type)
		{
			case INTEGER:
				return new SorterFactory<Integer>();
			case DOUBLE:
				return new SorterFactory<Double>();
			case CHARACTER:
				return new SorterFactory<Character>();
			default:
				return null;
		}
	}

	/*
	 * Loop For Selecting Sorter Type.
	 */
	private static Sorter<? extends Comparable<?>> createSorter(SorterFactory<? extends Comparable<?>> sorterFactory)
	{
		while (true)
		{
			Printer.printMessage("Select Sort Method:\n" +
						         "1 : Bubble Sort\n" +
								 "2 : Merge Sort\n" +
								 "3 : Binary Sort");
			if (NUMBER_SCANNER.hasNextInt())
			{
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						return sorterFactory.createSorter(SorterType.BUBBLE);
					case 2:
						return sorterFactory.createSorter(SorterType.MERGE);
					case 3:
						return sorterFactory.createSorter(SorterType.BINARY);
				}
			}
			else
			{
				NUMBER_SCANNER.next();
			}
			Printer.printMessage("Invalid Input. Try Again.");
		}
	}

	/*
	 * Generate a random list of the given type.
	 */
	private static List<? extends Comparable<?>> generateList(ComparableType type)
	{
		Printer.printMessage("Input Parameters for List Generation:");
		int size = sizeOfList();
		int minValue = minValueInList(type);
		int maxValue = maxValueInList(type);
		return ListUtil.createRandomList(minValue, maxValue, size, type);
	}

	/*
	 * Loop for inputting size of list.
	 */
	private static int sizeOfList()
	{
		while (true)
		{
			Printer.printMessage("Size of Array to Generate:");
			if (NUMBER_SCANNER.hasNextInt())
			{
				return NUMBER_SCANNER.nextInt();
			}
			else
			{
				Printer.printMessage("Invalid Input. Try Again.");
				NUMBER_SCANNER.next();
			}
		}
	}

	/*
	 * Input min value.
	 */
	private static int minValueInList(ComparableType type)
	{
		return getBoundaryValue(type, true);
	}

	/*
	 * Input max value.
	 */
	private static int maxValueInList(ComparableType type)
	{
		return getBoundaryValue(type, false);
	}

	/*
	 * Loop for inputting boundary values.
	 */
	private static int getBoundaryValue(ComparableType type, boolean minVal)
	{
		while (true)
		{
			Printer.printFormattedMessage("%s Value:", minVal ? "Minimum" : "Maximum");
			switch (type)
			{
				case INTEGER:
				case DOUBLE:
					if (NUMBER_SCANNER.hasNextInt())
					{
						return NUMBER_SCANNER.nextInt();
					}
					else
					{
						NUMBER_SCANNER.nextInt();
					}
					break;
				case CHARACTER:
					String input = STRING_SCANNER.nextLine();
					if (input.length() == 1 && input.matches("[a-zA-Z]"))
					{
						return input.toLowerCase().charAt(0);
					}
			}
			Printer.printMessage("Invalid Input. Try Again.");
		}
	}

	/*
	 * Loop for sort order selection.
	 */
	private static boolean getListOrdering()
	{
		while (true)
		{
			Printer.printMessage("Select Option:\n" +
								 "1 : Ascending\n" +
								 "2 : Descending");
			if (NUMBER_SCANNER.hasNextInt())
			{
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						return true;
					case 2:
						return false;
				}
			}
			else
			{
				NUMBER_SCANNER.next();
			}
			Printer.printMessage("Invalid Input. Try Again.");
		}
	}

	/*
	 * Print original list, sorted list, method used and time taken.
	 */
	private static void printListAndSortResults(List<? extends Comparable<?>> listToSort, boolean asAscending, Sorter<? extends Comparable<?>> sorter)
	{
		Printer.printFormattedMessage("Sorting the randomly generated list %s into %s order using the %s...",
				listToSort, asAscending ? "ascending" : "descending", sorter.getClass().getSimpleName());
		long startTime = System.nanoTime();
		List sortedList = sortList(listToSort, asAscending, sorter);
		long endTime = System.nanoTime();
		long timeTaken = (endTime - startTime) / 1000;
		Printer.printFormattedMessage("Operation took %d micro seconds.\nSorted List %s\n",
				timeTaken, sortedList);
	}

	/*
	 * Sort the given list, in the given order using the given sorter.
	 */
	private static List<? extends Comparable<?>> sortList(List listToSort, boolean asAscending, Sorter<? extends Comparable<?>> sorter)
	{
		try
		{
			if (asAscending)
			{
				return sorter.sortListAsc(listToSort);
			} else
			{
				return sorter.sortListDesc(listToSort);
			}
		}
		catch (Exception e)
		{
			SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using " + sorter.getClass().getSimpleName() + " " + e.getMessage(), e);
			Printer.printNewLine();
		}
		return new ArrayList<>();
	}

	/*
	 * Loop for the tree.
	 */
	private static void treeLoop(ComparableType type)
	{
		treeCreation:
		while (true)
		{
			BinarySearchTree<? extends Comparable<?>> binarySearchTree = createBinarySearchTree(type);
			buildTree(binarySearchTree, type);
			performOperations(binarySearchTree, type);

			while (true)
			{
				Printer.printMessage("Select Option:\n" +
						"1 : New Element Type\n" +
						"2 : Clear Tree\n" +
						"3 : Exit Trees");
				if (NUMBER_SCANNER.hasNextInt())
				{
					switch (NUMBER_SCANNER.nextInt())
					{
						case 1:
							type = typeSelection();
							continue treeCreation;
						case 2:
						case 3:
							Printer.printMessage("Exiting Trees...");
							break treeCreation;
					}
				}
			}
		}
	}

	/*
	 * Create a binary search tree of the given type.
	 */
	private static BinarySearchTree<? extends Comparable<?>> createBinarySearchTree(ComparableType type)
	{
		switch (type)
		{
			case INTEGER:
				return new BinarySearchTree<Integer>();
			case DOUBLE:
				return new BinarySearchTree<Double>();
			case CHARACTER:
				return new BinarySearchTree<Character>();
		}
		return null;
	}

	/*
	 * Loop for building the tree.
	 */
	private static void buildTree(BinarySearchTree<? extends Comparable<?>> binarySearchTree, ComparableType type)
	{
		while (true)
		{
			Printer.printMessage("Select Option:\n" +
								 "1 : Add Element\n" +
								 "2 : Add Elements\n" +
								 "3 : Print Final Tree");
			if (NUMBER_SCANNER.hasNextInt())
			{
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						addElementPrompt(binarySearchTree, type);
						break;
					case 2:
						addElementsPrompt(binarySearchTree, type);
						break;
					case 3:
						printTree(binarySearchTree);
						return;
					default:
						Printer.printMessage("Invalid Input. Try Again.");
				}
			}
			else
			{
				Printer.printMessage("Invalid Input. Try Again.");
				NUMBER_SCANNER.next();
			}

		}
	}

	/*
	 * Loop for adding an element.
	 */
	private static void addElementPrompt(BinarySearchTree binarySearchTree, ComparableType type)
	{
		addLoop: while (true)
		{
			Printer.printFormattedMessage("Input %s value:", type.name().toLowerCase());
			switch (type)
			{
				case INTEGER:
					if (NUMBER_SCANNER.hasNextInt())
					{
						binarySearchTree.addElement(NUMBER_SCANNER.nextInt());
						break addLoop;
					} else
					{
						NUMBER_SCANNER.next();
						break;
					}
				case DOUBLE:
					if (NUMBER_SCANNER.hasNextDouble())
					{
						binarySearchTree.addElement(NUMBER_SCANNER.nextDouble());
						break addLoop;

					} else
					{
						NUMBER_SCANNER.next();
						break;
					}
				case CHARACTER:
					if (STRING_SCANNER.hasNextLine())
					{
						String input = STRING_SCANNER.nextLine();
						if (input.length() == 1 && input.matches("[a-zA-Z]"))
						{
							binarySearchTree.addElement(input.toLowerCase().charAt(0));
							break addLoop;
						}
					}
			}
			Printer.printMessage("Input Invalid. Try Again.");
		}
	}

	/*
	 * Loop for adding a list of elements.
	 */
	private static void addElementsPrompt(BinarySearchTree binarySearchTree, ComparableType type)
	{
		while (true)
		{
			Printer.printFormattedMessage("Input %s values separated by spaces:", type.name().toLowerCase());
			List elementsToAdd = new ArrayList();
			List invalidElements = new ArrayList();
			if (STRING_SCANNER.hasNextLine())
			{
				String input = STRING_SCANNER.nextLine();
				String[] inputs = input.split(" ");

				switch (type)
				{
					case INTEGER:
						for (String element : inputs)
						{
							Integer intVal = Ints.tryParse(element);
							if (intVal != null)
							{
								if (!elementsToAdd.contains(intVal))
									elementsToAdd.add(intVal);
							} else
							{
								invalidElements.add(element);
							}
						}
						break;
					case DOUBLE:
						for (String element : inputs)
						{
							Double doubleVal = Doubles.tryParse(element);
							if (doubleVal != null)
							{
								if (!elementsToAdd. contains(doubleVal))
									elementsToAdd.add(doubleVal);
							} else
							{
								invalidElements.add(element);
							}
						}
						break;
					case CHARACTER:
						for (String element : inputs)
						{
							if (element.length() == 1 && element.matches("[a-zA-Z]"))
							{
								Character charValue = element.toLowerCase().charAt(0);
								if (!elementsToAdd.contains(charValue))
									elementsToAdd.add(charValue);
							} else
							{
								invalidElements.add(element);
							}
						}
				}
			}
			if (elementsToAdd.size() == 0)
			{
				Printer.printMessage("No Valid Values Input. Try Again.");
			}
			else
			{
				Printer.printFormattedMessage("The values %s will be added to the tree.", elementsToAdd);
				binarySearchTree.addElements(elementsToAdd);
				if (invalidElements.size() > 0)
				{
					Printer.printFormattedMessage("The invalid values %s will not be added.", invalidElements);
				}
				break;
			}
		}
	}

	/*
	 * Print the tree.
	 */
	private static void printTree(BinarySearchTree<? extends Comparable<?>> binarySearchTree)
	{
		Printer.printMessage("Printing Binary Search Tree...");
		BinaryTreePrinter.print(binarySearchTree);
	}

	/*
	 * Loop for selecting operation to perform.
	 */
	private static void performOperations(BinarySearchTree<? extends Comparable<?>> binarySearchTree, ComparableType type)
	{
		while (true)
		{
			Printer.printMessage("Select Option:\n" +
			 					 "1 : Has Element\n" +
								 "2 : Get Left Child\n" +
								 "3 : Get Right Child\n" +
								 "4 : Sorted List\n" +
								 "5 : Exit");

			if (NUMBER_SCANNER.hasNextInt())
			{
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						hasElementCheck(binarySearchTree, type);
						break;
					case 2:
						getLeftChild(binarySearchTree, type);
						break;
					case 3:
						getRightChild(binarySearchTree, type);
						break;
					case 4:
						getSortedList(binarySearchTree, type);
						break;
					case 5:
						return;
					default:
						Printer.printMessage("Invalid Input. Try Again.");
				}
			}
			else
			{
				Printer.printMessage("Invalid Input. Try Again.");
				NUMBER_SCANNER.next();
			}
		}
	}

	/*
	 * Check if the tree has the inputted element.
	 */
	private static void hasElementCheck(BinarySearchTree binarySearchTree, ComparableType type)
	{
		Printer.printFormattedMessage("Input a Value to Check if the Tree Contains it:");
		Integer intVal = null;
		Double doubleVal = null;
		Character charVal = null;

		boolean containsValue = false;

		String input = STRING_SCANNER.nextLine();

		switch (type)
		{
			case INTEGER:
				intVal = Ints.tryParse(input);
				if (intVal != null)
				{
					containsValue = binarySearchTree.hasElement(intVal);
				}
				break;
			case DOUBLE:
				doubleVal = Doubles.tryParse(input);
				if (doubleVal != null)
				{
					containsValue = binarySearchTree.hasElement(doubleVal);
				}
				break;
			case CHARACTER:
				if (input.length() == 1 && input.matches("[a-zA-Z]"))
				{
					charVal = input.toLowerCase().charAt(0);
					containsValue = binarySearchTree.hasElement(charVal);
				}
		}

		Printer.printFormattedMessage("The Binary Search Tree %s contain the value %s.\n",
				containsValue ? "does" : "does not", input);
	}

	/*
	 * Get the left child of the inputted element.
	 */
	private static void getLeftChild(BinarySearchTree binarySearchTree, ComparableType type)
	{
		String input;
		Integer intVal;
		Double doubleVal;
		Character charVal;

		Integer intResult = null;
		Double doubleResult = null;
		Character charResult = null;
		input: while (true)
		{
			Printer.printMessage("Input a Value to Get the Left Child of:");
			input = STRING_SCANNER.nextLine();


			try
			{
				switch (type)
				{
					case INTEGER:
						intVal = Ints.tryParse(input);
						if (intVal != null)
						{
							intResult = (Integer) binarySearchTree.getLeftChild(intVal);
							break input;
						}
						break;
					case DOUBLE:
						doubleVal = Doubles.tryParse(input);
						if (doubleVal != null)
						{
							doubleResult = (Double) binarySearchTree.getLeftChild(doubleVal);
							break input;
						}
						break;
					case CHARACTER:
						if (input.length() == 1 && input.matches("[a-zA-Z]"))
						{
								charVal = input.toLowerCase().charAt(0);
								charResult = (char) binarySearchTree.getLeftChild(charVal);
								break input;
						}
				}
				Printer.printMessage("Invalid Input. Try Again.");
			} catch (ChildNotFoundException e)
			{
				Printer.printMessage(e.getMessage());
				SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
						+ binarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
				break;
			}
		}
		Printer.printFormattedMessage("The left child of the %s value %s is %s.\n",
				type.name(), input, ((type == ComparableType.INTEGER) ? intResult :
						((type == ComparableType.DOUBLE) ? doubleResult : charResult)));
	}

	/*
	 * Get the right child of the inputted element.
	 */
	private static void getRightChild(BinarySearchTree binarySearchTree, ComparableType type)
	{
		String input;
		Integer intVal;
		Double doubleVal;
		Character charVal;

		Integer intResult = null;
		Double doubleResult = null;
		Character charResult = null;
		input: while (true)
		{
			Printer.printMessage("Input a Value to Get the Right Child of:");
			input = STRING_SCANNER.nextLine();


			try
			{
				switch (type)
				{
					case INTEGER:
						intVal = Ints.tryParse(input);
						if (intVal != null)
						{
							intResult = (Integer) binarySearchTree.getRightChild(intVal);
							break input;
						}
						break;
					case DOUBLE:
						doubleVal = Doubles.tryParse(input);
						if (doubleVal != null)
						{
							doubleResult = (Double) binarySearchTree.getRightChild(doubleVal);
							break input;
						}
						break;
					case CHARACTER:
						if (input.length() == 1 && input.matches("[a-zA-Z]"))
						{
							charVal = input.toLowerCase().charAt(0);
							charResult = (char) binarySearchTree.getRightChild(charVal);
							break input;
						}
				}
				Printer.printMessage("Invalid Input. Try Again.");
			} catch (ChildNotFoundException e)
			{
				Printer.printMessage(e.getMessage());
				SortManagerLogger.getLogger().error(e.getClass().getSimpleName() + " raised when sorting using "
						+ binarySearchTree.getClass().getSimpleName() + " " + e.getMessage(), e);
				break;
			}
		}
		Printer.printFormattedMessage("The right child of the %s value %s is %s.\n",
				type.name(), input, ((type == ComparableType.INTEGER) ? intResult :
						((type == ComparableType.DOUBLE) ? doubleResult : charResult)));
	}

	/*
	 * Sort the tree into a list into the selected order.
	 */
	private static void getSortedList(BinarySearchTree binarySearchTree, ComparableType type)
	{
		SorterFactory sorterFactory = createSortFactory(type);
		BinarySorter binarySorter = (BinarySorter) sorterFactory.createSorter(SorterType.BINARY);
		List result;
		input: while (true)
		{
			Printer.printMessage("Select Sorting Order:\n" +
								 "1 : Ascending\n" +
								 "2 : Descending");

			if (NUMBER_SCANNER.hasNextInt())
			{
				switch (NUMBER_SCANNER.nextInt())
				{
					case 1:
						Printer.printMessage("Sorting Tree Into Ascending Order...");
						result = binarySorter.sortedListAsc(binarySearchTree);
						break input;
					case 2:
						Printer.printMessage("Sorting Tree Into Descending Order...");
						result = binarySorter.sortedListDesc(binarySearchTree);
						break input;
					default:
						Printer.printMessage("Invalid Input. Try Again.");
				}
			}
			else
			{
				Printer.printMessage("Invalid Input. Try Again");
				NUMBER_SCANNER.next();
			}
		}

		Printer.printFormattedMessage("Sorted List: %s\n", result);
	}
}
