package com.sparta.dominic.tree;

import com.sparta.dominic.exception.ChildNotFoundException;

import java.util.List;

public interface BinaryTree<T extends Comparable<T>>
{
	T getRootElement();

	int getNumberOfElements();

	void addElement(T element);

	void addElements(List<T> elements);

	boolean hasElement(T element);

	T getLeftChild(T element) throws ChildNotFoundException;

	T getRightChild(T element) throws ChildNotFoundException;

	void clear();
}
