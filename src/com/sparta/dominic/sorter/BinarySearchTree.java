package com.sparta.dominic.sorter;

import com.sparta.dominic.exception.ChildNotFoundException;

public class BinarySearchTree implements BinaryTree
{
	/*
	 * Each node in the tree has an int value and points to its left and
	 * right child. If it doesn't have any children these values will be null.
	 */
	private static class Node
	{
		private final int value;
		private Node leftChild;
		private Node rightChild;

		public Node(int value)
		{
			this.value = value;
		}
	}

	/*
	 * Root of the binary tree.
	 */
	private Node root;

	/*
	 * Number of elements in the tree.
	 */
	private int size;

	/**
	 * Returns the value of the root node in the tree.
	 *
	 * @return The value of the root node.
	 */
	@Override
	public int getRootElement()
	{
		return root.value;
	}

	/**
	 * Returns the number of elements in the tree.
	 *
	 * @return The number of elements in the tree.
	 */
	@Override
	public int getNumberOfElements()
	{
		return size;
	}

	/**
	 * Add the given element to the tree at the appropriate posiiton.
	 *
	 * @param element The element to add.
	 */
	@Override
	public void addElement(int element)
	{
		if (root == null)
		{
			// Tree is empty add element as root.
			root = new Node(element);
			size++;
		}
		else
		{
			addElementHelper(element, root);
		}

	}

	/*
	 * Helper method to for the addElement method.
	 */
	private void addElementHelper(int element, Node current)
	{
		if (element > current.value)
		{
			if (current.rightChild == null)
			{
				current.rightChild = new Node(element);
				size++;
			}
			else
			{
				addElementHelper(element, current.rightChild);
			}
		}
		else if (element < current.value)
		{
			if (current.leftChild == null)
			{
				current.leftChild = new Node(element);
				size++;
			}
			else
			{
				addElementHelper(element, current.leftChild);
			}
		}
	}

	/**
	 * Adds all the elements of the given array to the to the tree.
	 *
	 * @param elements The array of elements to add.
	 */
	@Override
	public void addElements(int[] elements)
	{
		for (int element : elements)
		{
			addElement(element);
		}
	}

	/**
	 * Returns true if a node with the given value exists in the tree.
	 *
	 * @param value The value to search for.
	 *
	 * @return true if the given value exists in the tree.
	 */
	@Override
	public boolean findElement(int value)
	{
		return findElementHelper(value, root);
	}

	/*
	 * Helper method for the findElement method.
	 */
	private static boolean findElementHelper(int value, Node current)
	{
		if (current == null)
		{
			return false;
		}
		else if (value == current.value)
		{
			return true;
		}
		else if (value > current.value)
		{
			return findElementHelper(value, current.rightChild);
		}
		else
		{
			return findElementHelper(value, current.leftChild);
		}
	}

	/**
	 * Returns the value of the left child of the node with the given value.
	 *
	 * @param element The value of the node to find the left child of.
	 *
	 * @return The value of the left child of the node with the given value.
	 * @throws ChildNotFoundException If there is no child to the left of the given value, the given value
	 * doesn't exist or the tree is empty.
	 */
	@Override
	public int getLeftChild(int element) throws ChildNotFoundException
	{
		if (root == null)
		{
			throw new ChildNotFoundException("The tree is empty and thus the root has no children.");
		}
		return getLeftChildHelper(element, root);
	}

	/*
	 * Helper method for the getLeftChild method.
	 */
	private int getLeftChildHelper(int element, Node current) throws ChildNotFoundException
	{
		if (element == current.value)
		{
			if (current.leftChild == null)
			{
				throw new ChildNotFoundException("There is no child to the left of the value " + element + ".");
			}
			else
			{
				return current.leftChild.value;
			}
		}
		else if (element > current.value)
		{
			if (current.rightChild == null)
			{
				throw new ChildNotFoundException("There is no node with the value " + element + ".");
			}
			else
			{
				return getLeftChildHelper(element, current.rightChild);
			}
		}
		else
		{
			if (current.leftChild == null)
			{
				throw new ChildNotFoundException("There is no node with the value " + element + ".");
			}
			else
			{
				return getLeftChildHelper(element, current.leftChild);
			}
		}
	}

	/**
	 * Returns the value of the right child of the node with the given value.
	 *
	 * @param element The value of the node to find the right child of.
	 *
	 * @return The value of the left child of the node with the given value.
	 * @throws ChildNotFoundException If there is no child to the right of the given value, the given value
	 * doesn't exist or the tree is empty.
	 */
	@Override
	public int getRightChild(int element) throws ChildNotFoundException
	{
		if (root == null)
		{
			throw new ChildNotFoundException("There is no node with the value " + element + ".");
		}
		return getRightChildHelper(element, root);
	}

	/*
	 * Helper method for the getRightChild method.
	 */
	private int getRightChildHelper(int element, Node current) throws ChildNotFoundException
	{
		if (element == current.value)
		{
			if (current.rightChild == null)
			{
				throw new ChildNotFoundException("There is no child to the left of the value " + element + ".");
			}
			else
			{
				return current.rightChild.value;
			}
		}
		else if (element > current.value)
		{
			if (current.rightChild == null)
			{
				throw new ChildNotFoundException("There is no node with the value " + element + ".");
			}
			else
			{
				return getLeftChildHelper(element, current.rightChild);
			}
		}
		else
		{
			if (current.leftChild == null)
			{
				throw new ChildNotFoundException("There is no node with the value " + element + ".");
			}
			else
			{
				return getLeftChildHelper(element, current.leftChild);
			}
		}
	}

	/**
	 * Returns an int array of the contents of the tree in ascending order.
	 *
	 * @return An int array of the contents of the tree in ascending order.
	 */
	@Override
	public int[] getSortedTreeAsc()
	{
		int[] sortedTree = new int[size];
		getSortedTreeAscHelper(sortedTree, 0, root);
		return sortedTree;
	}

	/*
	 * Helper method for the getSortedTreeAsc method.
	 */
	private int getSortedTreeAscHelper(int[] sortedTree, int index, Node current)
	{
		if (index < sortedTree.length)
		{
			if (current.leftChild != null)
			{
				index = getSortedTreeAscHelper(sortedTree, index, current.leftChild);
				index++;
			}

			sortedTree[index] = current.value;

			if (current.rightChild != null)
			{
				index++;
				getSortedTreeAscHelper(sortedTree, index, current.rightChild);
			}
		}
		return index;
	}

	/**
	 * Returns an int array of the contents of the tree in descending order.
	 *
	 * @return An int array of the contents of the tree in descending order.
	 */
	@Override
	public int[] getSortedTreeDesc()
	{
		int[] sortedTree = new int[size];
		getSortedTreeDscHelper(sortedTree, 0, root);
		return sortedTree;
	}

	/*
	 * Helper method for the getSortedTreeDesc method.
	 */
	private int getSortedTreeDscHelper(int[] sortedTree, int index, Node current)
	{
		if (index < sortedTree.length)
		{
			if (current.rightChild != null)
			{
				index = getSortedTreeDscHelper(sortedTree, index, current.rightChild);
				index++;
			}

			sortedTree[index] = current.value;

			if (current.leftChild != null)
			{
				index++;
				getSortedTreeDscHelper(sortedTree, index, current.leftChild);
			}
		}
		return index;
	}
}
