package com.sparta.dominic.main;

import com.sparta.dominic.logger.SortManagerLogger;
import com.sparta.dominic.sorter.Sorter;
import com.sparta.dominic.sorter.SorterFactory;
import com.sparta.dominic.sorter.SorterType;
import com.sparta.dominic.tree.BinarySearchTree;
import com.sparta.dominic.tree.BinaryTreePrinter;
import com.sparta.dominic.util.ListUtil;
import com.sparta.dominic.util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Starter
{
	private static final Scanner SCANNER = new Scanner(System.in);

	private static Class typeClass;

	private static SorterFactory sorterFactory;
	private static Sorter sorter;
	private static List listToSort;
	private static List sortedList;

	private static boolean asAscending;
	private static int minValue;
	private static int maxValue;
	private static int length;

	private static BinarySearchTree binarySearchTree;

	public static void start()
	{
		start: while (true)
		{
			while (true)
			{
				Printer.printMessage("Select Function:\n" +
						"1 : Sorters\n" +
						"2 : Binary Trees");
				boolean selectionSuccessful = true;
				if (SCANNER.hasNextInt())
				{
					int selection = SCANNER.nextInt();
					switch (selection)
					{
						case 1:
							sorterLoop();
							break;
						case 2:
							treeLoop();
							break;
						default:
							selectionSuccessful = false;
					}
				}
				else
				{
					SCANNER.next();
				}
				if (selectionSuccessful)
					break;
			}

			while (true)
			{
				Printer.printMessage("Start Again?");
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
	 * Loop for sorter process.
	 */
	private static void sorterLoop()
	{
		sortFactoryTypeSelection();
		sortSelection();
		parameterSelection();
		generateArrayToSort();
		printListAndSortDetails();
	}

	/*
	 * Select type of value to sort.
	 */
	private static void sortFactoryTypeSelection()
	{
		while (true)
		{
			Printer.printMessage("Select Element Type:\n" +
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
			else
			{
				SCANNER.next();
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
			Printer.printMessage("Select Algorithm:\n" +
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
			else
			{
				SCANNER.next();
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
			Printer.printMessage("Input List Size:");
			if (SCANNER.hasNextInt())
			{
				length = SCANNER.nextInt();
				break;
			}
			else
			{
				SCANNER.next();
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
			Printer.printMessage("Input Minimum Value:");
			if (SCANNER.hasNextLine())
			{
				String input = SCANNER.next();
				if (input.length() == 1 && input.matches("\\w"))
				{
					minValue = input.charAt(0);
					break;
				}

				if (input.matches("^-?[0-9]\\d*(\\.\\d+)?$"))
				{
					minValue = Integer.parseInt(input);
					break;
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
			Printer.printMessage("Input Maximum Value:");
			if (SCANNER.hasNextLine())
			{
				String input = SCANNER.next();
				if (input.length() == 1 && input.matches("[a-zA-Z]"))
				{
					maxValue = input.toLowerCase().charAt(0);
					break;
				}

				if (input.matches("^-?[0-9]\\d*(\\.\\d+)?$"))
				{
					maxValue = Integer.parseInt(input);
					break;
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
			Printer.printMessage("Select Ordering:\n" +
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
						Printer.printMessage("Please select one of the numbered options.");
				}
			}
			else
			{
				Printer.printMessage("Please select one of the numbered options.");
				SCANNER.next();
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

	/*
	 * Loop for tree functions.
	 */
	private static void treeLoop()
	{
		treeTypeSelection();
		addElementsSelection();
	}

	/*
     * Select type of tree and create.
	 */
	private static void treeTypeSelection()
	{
		while (true)
		{
			Printer.printMessage("Select Element Type:\n" +
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
						binarySearchTree = new BinarySearchTree();
						typeClass = Integer.class;
						break;
					case 2:
						binarySearchTree = new BinarySearchTree();
						typeClass = Double.class;
						break;
					case 3:
						binarySearchTree = new BinarySearchTree();
						typeClass = Character.class;
						break;
					default:
						typeSelectionSuccessful = false;
						break;
				}
				if (typeSelectionSuccessful)
					break;
			}
			else
			{
				SCANNER.next();
			}
			Printer.printMessage("Please select one of the numbered options.");
		}
	}

	/*
	 * Select next action.
	 */
	private static void addElementsSelection()
	{
		start: while (true)
		{
			Printer.printMessage("Select:\n" +
					"1 : Add Element.\n" +
					"2 : Print Final Tree");
			if (SCANNER.hasNextInt())
			{
				int selection = SCANNER.nextInt();
				switch (selection)
				{
					case 1:
						addElement();
						break;
					case 2:
						Printer.printMessage("Final Binary Search Tree!");
						BinaryTreePrinter.print(binarySearchTree);
						treeFunctionSelection();
						break start;
					default:
						Printer.printMessage("Please select one of the numbered options.");
				}
			}
			else
			{
				SCANNER.next();
				Printer.printMessage("Please select one of the numbered options.");
			}
		}
	}

	/*
	 * Add element to tree.
	 */
	private static void addElement()
	{
		while (true)
		{
			Printer.printMessage("Input the " + typeClass.getSimpleName() + " to Add:");
			boolean additionSuccessful = false;
			String input = null;
			if (SCANNER.hasNext())
			{
				input = SCANNER.next();
				if (Integer.class.equals(typeClass))
				{
					if (input.matches("^-?[0-9]\\d*(\\.\\d+)?$"))
					{
						binarySearchTree.addElement(Integer.parseInt(input));
						additionSuccessful = true;
					}
				}
				else if (Double.class.equals(typeClass))
				{
					if (input.matches("(-?(\\\\d)+(\\\\.)?(\\\\d)*)\n"))
					{
						binarySearchTree.addElement(Double.parseDouble(input));
						additionSuccessful = true;
					}
				}
				else
				{
					if (input.length() == 1 && input.matches("[a-zA-Z]"))
					{
						binarySearchTree.addElement(input.toLowerCase().charAt(0));
						additionSuccessful = true;
					}
				}
			}
			
			if (additionSuccessful)
			{
				Printer.printMessage("Adding " + input + " to the tree...");
				BinaryTreePrinter.print(binarySearchTree);
				break;
			}
			else
			{
				Printer.printMessage("Please ensure your input is a " + typeClass.getSimpleName());
			}
		}
	}

	/*
	 * Select tree function.
	 */
	private static void treeFunctionSelection()
	{
		start: while (true)
		{
			Printer.printMessage("Select Function:\n" +
								 "1 : Has Element.\n" +
								 "2 : Get Left Child.\n" +
			                     "3 : Get Right Child.\n" +
								 "4 : Exit.");

			if(SCANNER.hasNextInt())
			{
				int input = SCANNER.nextInt();
				switch (input)
				{
					case 1:
						treeHasElement();
						break;
					case 2:
						treeGetLeftChild();
						break;
					case 3:
						treeGetRightChild();
						break;
					case 4:
						break start;
					default:
						Printer.printMessage("Please select one of the numbered options.");
				}
			}
			else
			{
				Printer.printMessage("Please select one of the numbered options.");
				SCANNER.next();
			}
		}
	}

	/*
	 * Check for right child of element.
	 */
	private static void treeGetRightChild()
	{
		while (true)
		{
			Printer.printMessage("Input Element To Find Right Child Of:");
			String input;
			if (SCANNER.hasNext())
			{
				input = SCANNER.next();
				try
				{
					Object right = null;
					if (Integer.class.equals(typeClass))
					{
						if (input.matches("^-?[0-9]\\d*(\\.\\d+)?$"))
						{
							right = binarySearchTree.getRightChild(Integer.parseInt(input));
						}
					} else if (Double.class.equals(typeClass))
					{
						if (input.matches("(-?(\\\\d)+(\\\\.)?(\\\\d)*)\n"))
						{
							right = binarySearchTree.getRightChild(Double.parseDouble(input));
						}
					} else
					{
						if (input.length() == 1 && input.matches("[a-zA-Z]"))
						{
							right = binarySearchTree.getRightChild(input.toLowerCase().charAt(0));
						}
					}

					if (right != null)
					{
						Printer.printMessage("The right child of " + input + " is " + right);
						break;
					}
				}
				catch (Exception e)
				{
					Printer.printMessage("Either " + input + " doesn't exist or doesn't have a right child.\n");
					SortManagerLogger.getLogger().error(e.getMessage(), e);
				}
			}

		}
	}

	/*
	 * Check for left child of element.
	 */
	private static void treeGetLeftChild()
	{
		while (true)
		{
			Printer.printMessage("Input Element To Find Left Child Of:");
			String input;
			if (SCANNER.hasNext())
			{
				input = SCANNER.next();
				try
				{
					Object left = null;
					if (Integer.class.equals(typeClass))
					{
						if (input.matches("^-?[0-9]\\d*(\\.\\d+)?$"))
						{
							left = binarySearchTree.getLeftChild(Integer.parseInt(input));
						}
					} else if (Double.class.equals(typeClass))
					{
						if (input.matches("(-?(\\\\d)+(\\\\.)?(\\\\d)*)\n"))
						{
							left = binarySearchTree.getLeftChild(Double.parseDouble(input));
						}
					} else
					{
						if (input.length() == 1 && input.matches("[a-zA-Z]"))
						{
							left = binarySearchTree.getLeftChild(input.toLowerCase().charAt(0));
						}
					}

					if (left != null)
					{
						Printer.printMessage("The left child of " + input + " is " + left);
						break;
					}
				}
				catch (Exception e)
				{
					Printer.printMessage("Either " + input + " doesn't exist or doesn't have a left child.\n");
					SortManagerLogger.getLogger().error(e.getMessage(), e);
				}
			}
		}
	}

	/*
	 * Check if tree has element.
	 */
	private static void treeHasElement()
	{
		Printer.printMessage("Input Element To Find:");
		String input;
		if (SCANNER.hasNext())
		{
			input = SCANNER.next();
			boolean hasElement = false;
			if (Integer.class.equals(typeClass))
			{
				if (input.matches("^-?[0-9]\\d*(\\.\\d+)?$"))
				{
					hasElement = binarySearchTree.hasElement(Integer.parseInt(input));
				}
			} else if (Double.class.equals(typeClass))
			{
				if (input.matches("(-?(\\\\d)+(\\\\.)?(\\\\d)*)\n"))
				{
					hasElement = binarySearchTree.hasElement(Double.parseDouble(input));
				}
			} else
			{
				if (input.length() == 1 && input.matches("[a-zA-Z]"))
				{
					hasElement = binarySearchTree.hasElement(input.toLowerCase().charAt(0));
				}
			}

			Printer.printMessage("The binary search tree " + (hasElement ? "does" : "does not")
							+ " contain the element " + input + ".");
		}
	}
}
