package com.sparta.dominic.tree;

import com.sparta.dominic.exception.ChildNotFoundException;

import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T>
{
	public static class Node<T>
	{
		private final T value;
		private Node<T> leftChild;
		private Node<T> rightChild;

		public Node(T value)
		{
			this.value = value;
		}

		public T getValue()
		{
			return value;
		}

		public Node<T> getLeftChild()
		{
			return leftChild;
		}

		public Node<T> getRightChild()
		{
			return rightChild;
		}
	}

	private Node<T> root;

	private int size;

	public Node<T> getRootNode()
	{
		return root;
	}

	@Override
	public T getRootElement()
	{
		return root.value;
	}

	@Override
	public int getNumberOfElements()
	{
		return size;
	}

	@Override
	public void addElement(T element)
	{
		if (root == null)
		{
			root = new Node<>(element);
			size++;
		}
		else
		{
			addElementHelper(element, root);
		}
	}

	private void addElementHelper(T element, Node<T> current)
	{
		int comparison = element.compareTo(current.value);
		if (comparison > 0)
		{
			if (current.rightChild == null)
			{
				current.rightChild = new Node<>(element);
				size++;
			} else
			{
				addElementHelper(element, current.rightChild);
			}
		} else if (comparison < 0)
		{
			if (current.leftChild == null)
			{
				current.leftChild = new Node<>(element);
				size++;
			} else
			{
				addElementHelper(element, current.leftChild);
			}
		}
	}

	@Override
	public void addElements(List<T> elements)
	{
		for (T element : elements)
		{
			addElement(element);
		}
	}

	@Override
	public boolean hasElement(T element)
	{
		return hasElementHelper(element, root);
	}

	private boolean hasElementHelper(T element, Node<T> current)
	{
		if (current == null)
		{
			return false;
		}

		int comparison = element.compareTo(current.value);
		if (comparison == 0)
		{
			return true;
		}
		else if (comparison > 0)
		{
			return hasElementHelper(element, current.rightChild);
		}
		else
		{
			return hasElementHelper(element, current.leftChild);
		}
	}

	@Override
	public T getLeftChild(T element) throws ChildNotFoundException
	{
		return getLeftChildHelper(element, root);
	}

	private T getLeftChildHelper(T element, Node<T> current) throws ChildNotFoundException
	{
		if (current == null)
			throw new ChildNotFoundException("");

		int comparison = element.compareTo(current.value);
		if (comparison == 0)
		{
			if (current.leftChild == null)
				throw new ChildNotFoundException("");
			return current.leftChild.value;
		}
		else if (comparison > 0)
		{
			return getLeftChildHelper(element, current.rightChild);
		}
		else
		{
			return getLeftChildHelper(element, current.leftChild);
		}
	}

	@Override
	public T getRightChild(T element) throws ChildNotFoundException
	{
		return getRightChildHelper(element, root);
	}

	private T getRightChildHelper(T element, Node<T> current) throws ChildNotFoundException
	{
		if (current == null)
			throw new ChildNotFoundException("");

		int comparison = element.compareTo(current.value);
		if (comparison == 0)
		{
			if (current.rightChild == null)
				throw new ChildNotFoundException("");
			return current.rightChild.value;
		}
		else if (comparison > 0)
		{
			return getRightChildHelper(element, current.rightChild);
		}
		else
		{
			return getRightChildHelper(element, current.leftChild);
		}
	}

	public void clear()
	{
		root = null;
	}
}
