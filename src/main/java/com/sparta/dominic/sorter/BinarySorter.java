package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.tree.BinarySearchTree;
import com.sparta.dominic.tree.BinaryTree;
import com.sparta.dominic.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

public class BinarySorter<T extends Comparable<T>> implements Sorter<T>
{
	private final BinarySearchTree<T> BST = new BinarySearchTree<>();

	@Override
	public List<T> sortListAsc(List<T> listToSort) throws NullListException, EmptyListException
	{
		ListUtil.nullAndEmptyListChecker(listToSort);
		BST.addElements(listToSort);
		List<T> sortedList = new ArrayList<>();
		sortedListAscHelper(sortedList, BST.getRootNode());
		BST.clear();
		return sortedList;
	}

	private void sortedListAscHelper(List<T> sortedList, BinarySearchTree.Node<T> current)
	{
		if (sortedList.size() < BST.getNumberOfElements())
		{
			if (current.getLeftChild() != null)
			{
				// add left subtree to the sorted array.
				sortedListAscHelper(sortedList, current.getLeftChild());
			}

			// add the current value to the sorted array.
			sortedList.add(current.getValue());

			if (current.getRightChild() != null)
			{
				// add right subtree to the sorted array.
				sortedListAscHelper(sortedList, current.getRightChild());
			}
		}
	}

	@Override
	public List<T> sortListDesc(List<T> listToSort) throws NullListException, EmptyListException
	{
		ListUtil.nullAndEmptyListChecker(listToSort);
		BST.addElements(listToSort);
		List<T> sortedList = new ArrayList<>();
		sortedListDescHelper(sortedList, BST.getRootNode());
		BST.clear();
		return sortedList;
	}

	private void sortedListDescHelper(List<T> sortedList, BinarySearchTree.Node<T> current)
	{
		if (sortedList.size() < BST.getNumberOfElements())
		{
			if (current.getRightChild() != null)
			{
				// add right subtree to the sorted array.
				sortedListDescHelper(sortedList, current.getRightChild());
			}

			// add the current value to the sorted array.
			sortedList.add(current.getValue());

			if (current.getLeftChild() != null)
			{
				// add left subtree to the sorted array.
				sortedListDescHelper(sortedList, current.getLeftChild());
			}
		}
	}
}
