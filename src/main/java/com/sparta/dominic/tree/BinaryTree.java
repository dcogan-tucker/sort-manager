package com.sparta.dominic.tree;

import com.sparta.dominic.exception.ChildNotFoundException;

import java.util.List;

/**
 * Interface for a Binary Tree.
 *
 * @param <T> The object type to be stored in the tree, must be comparable.
 */
public interface BinaryTree<T extends Comparable<T>>
{
	/**
	 * Return the root element of the tree.
	 *
	 * @return The root element of the tree.
	 */
	T getRootElement();

	/**
	 * Return the number of elements in the tree.
	 *
	 * @return The number of elements in the tree.
	 */
	int getNumberOfElements();

	/**
	 * Add the given element to the tree.
	 *
	 * @param element The element to add.
	 */
	void addElement(T element);

	/**
	 * Add the list of given elements to the tree.
	 *
	 * @param elements The list of elements to add.
	 */
	void addElements(List<T> elements);

	/**
	 * Returns true if the tree has the given element.
	 *
	 * @param element The element to look for.
	 * @return true if the tree has the given element.
	 */
	boolean hasElement(T element);

	/**
	 * Returns the element to the left of the given element.
	 *
	 * @param element The element to check.
	 * @return The left child of the given element.
	 * @throws ChildNotFoundException If the given element doesn't
	 * exist or doesn't have a left child
	 */
	T getLeftChild(T element) throws ChildNotFoundException;

	/**
	 * Returns the element to the right of the given element.
	 *
	 * @param element The element to check.
	 * @return The right child of the given element.
	 * @throws ChildNotFoundException If the given element doesn't
	 * exist or doesn't have a right child
	 */
	T getRightChild(T element) throws ChildNotFoundException;

	/**
	 * Clear the tree.
	 */
	void clear();
}
