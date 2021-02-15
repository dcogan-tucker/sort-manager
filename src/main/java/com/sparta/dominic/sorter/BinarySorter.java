package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.tree.BinarySearchTree;
import com.sparta.dominic.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that can sort given lists of any comparable type into ascending or descending order using binary search trees.
 *
 * @param <T> The type of Object to sort, must be of Comparable.
 */
public class BinarySorter<T extends Comparable<T>> implements Sorter<T>
{
	private final BinarySearchTree<T> BST = new BinarySearchTree<>();
	private int size;

	/*
	 * Protected as should only be called by the SorterFactory.
	 */
	protected BinarySorter() {}

	@Override
	public List<T> sortListAsc(List<T> listToSort) throws NullListException, EmptyListException
	{
		ListUtil.nullAndEmptyListChecker(listToSort);
		BST.addElements(listToSort);
		size = BST.getNumberOfElements();
		List<T> sortedList = new ArrayList<>();
		sortedListAscHelper(sortedList, BST.getRootNode());
		BST.clear();
		return sortedList;
	}

	/*
	 * Helper method for the sortListAsc method.
	 */
	private void sortedListAscHelper(List<T> sortedList, BinarySearchTree.Node<T> current)
	{
		if (sortedList.size() < size)
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
		size = BST.getNumberOfElements();
		List<T> sortedList = new ArrayList<>();
		sortedListDescHelper(sortedList, BST.getRootNode());
		BST.clear();
		return sortedList;
	}

	/*
	 * Helper method for the sortListDesc method.
	 */
	private void sortedListDescHelper(List<T> sortedList, BinarySearchTree.Node<T> current)
	{
		if (sortedList.size() < size)
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

	public List<T> sortedListAsc(BinarySearchTree<T> binarySearchTree)
	{
		List<T> sortedList = new ArrayList<>();
		size = binarySearchTree.getNumberOfElements();
		sortedListAscHelper(sortedList, binarySearchTree.getRootNode());
		return sortedList;
	}

	public List<T> sortedListDesc(BinarySearchTree<T> binarySearchTree)
	{
		List<T> sortedList = new ArrayList<>();
		size = binarySearchTree.getNumberOfElements();
		sortedListDescHelper(sortedList, binarySearchTree.getRootNode());
		return sortedList;
	}
}
