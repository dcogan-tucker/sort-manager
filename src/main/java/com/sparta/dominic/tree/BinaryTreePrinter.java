package com.sparta.dominic.tree;

import com.sparta.dominic.util.Printer;

public class BinaryTreePrinter
{
	public static <T extends Comparable<T>> void print(BinarySearchTree<T> binarySearchTree) {
		StringBuilder buffer = new StringBuilder(50);
		print(binarySearchTree.getRootNode(), buffer,"", "");
		Printer.printMessage(buffer.toString());
	}

	private static <T extends Comparable<T>> void print(BinarySearchTree.Node<T> nodeToPrint, StringBuilder buffer, String prefix, String childrenPrefix) {
		buffer.append(prefix);
		buffer.append(nodeToPrint.getValue());
		buffer.append('\n');

		if (nodeToPrint.getRightChild() != null)
		{
			print(nodeToPrint.getRightChild(), buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
		}

		if (nodeToPrint.getLeftChild() != null)
		{
			print(nodeToPrint.getLeftChild(), buffer, "│   " + childrenPrefix + "\n" + childrenPrefix + "├── ", childrenPrefix + "│   ");
		}
	}
}
