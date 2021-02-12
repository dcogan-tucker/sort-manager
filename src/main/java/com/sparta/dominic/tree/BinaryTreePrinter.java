package com.sparta.dominic.tree;

import com.sparta.dominic.util.Printer;

/**
 * Class to print a BinarySearchTree.
 */
public final class BinaryTreePrinter
{
	/**
	 * Print the given BinarySearchTree.
	 *
	 * @param binarySearchTree The BinarySearchTree to print.
	 * @param <T> The type of Object in the tree.
	 */
	public static <T extends Comparable<T>> void print(BinarySearchTree<T> binarySearchTree) {
		StringBuilder buffer = new StringBuilder(50);
		printHelper(binarySearchTree.getRootNode(), buffer,"", "");
		Printer.printMessage(buffer.toString());
	}

	/*
	 * Helper for the print method.
	 */
	private static <T extends Comparable<T>> void printHelper(BinarySearchTree.Node<T> nodeToPrint, StringBuilder buffer, String prefix, String childrenPrefix) {
		if (nodeToPrint != null)
		{
			buffer.append(prefix);
			buffer.append(nodeToPrint.getValue());
			buffer.append('\n');

			if (nodeToPrint.getRightChild() != null)
			{
				printHelper(nodeToPrint.getRightChild(), buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
			}
			else
			{
				buffer.append(childrenPrefix);
				buffer.append("├── null\n");
			}

			if (nodeToPrint.getLeftChild() != null)
			{
				printHelper(nodeToPrint.getLeftChild(), buffer, "│   " + childrenPrefix + "\n" + childrenPrefix + "├── ", childrenPrefix + "│   ");
			}
			else
			{
				buffer.append(childrenPrefix);
				buffer.append("├── null\n");
			}
		}
		else
		{
			buffer.append("null\n ├── null\n ├── null\n");
		}
	}
}
