package com.sparta.dominic.tree;

import com.sparta.dominic.exception.ChildNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinarySearchTreeTest
{
	private static final BinarySearchTree<Integer> BINARY_SEARCH_TREE = new BinarySearchTree<>();

	@BeforeEach
	void setUp()
	{
		BINARY_SEARCH_TREE.clear();
	}

	@Test
	void addElementToEmptyTreeSetsItAsTheValueOfTheRoot()
	{
		BINARY_SEARCH_TREE.addElement(25);

		Assertions.assertEquals(Integer.valueOf(25), BINARY_SEARCH_TREE.getRootElement());
	}

	@Test
	void hasElementCheckOnEmptyTreeReturnsFalse()
	{
		Assertions.assertFalse(BINARY_SEARCH_TREE.hasElement(10));
	}

	@Test
	void hasElementCheckOnLeftMostElementReturnsTrue()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 30, 28, 33, 15, 20, 10, 13, 0, -6)));

		Assertions.assertTrue(BINARY_SEARCH_TREE.hasElement(-6));
	}

	@Test
	void hasElementCheckOnRightMostElementReturnsTrue()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 30, 20, 23, 29, 35, 40, 38, 42)));

		Assertions.assertTrue(BINARY_SEARCH_TREE.hasElement(42));
	}

	@Test
	void hasElementCheckOnLeftMostElementOnRightSideOfTreeReturnsTrue()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 30, 20, 23, 29, 34, 40, 32, 42, 39, 36)));

		Assertions.assertTrue(BINARY_SEARCH_TREE.hasElement(36));
	}

	@Test
	void hasElementCheckOnRightMostElementOnLeftSideOfTreeReturnsTrue()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 14, 10, 20, 15, 19, 17, 25, 30)));

		Assertions.assertTrue(BINARY_SEARCH_TREE.hasElement(30));
	}

	@Test
	void getLeftChildOfRootElement() throws ChildNotFoundException
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 20, 30)));

		Assertions.assertEquals(Integer.valueOf(20), BINARY_SEARCH_TREE.getLeftChild(25));
	}

	@Test
	void getLeftChildOfRightMostElement() throws ChildNotFoundException
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 20, 30, 27, 39, 42, 40)));

		Assertions.assertEquals(Integer.valueOf(40), BINARY_SEARCH_TREE.getLeftChild(42));
	}

	@Test
	void getLeftChildOfRightMostElementOnLeftSideOfTree() throws ChildNotFoundException
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(12, 20, 3, 4, 5, 7, 6, 9, 11, 10, 25, 16)));

		Assertions.assertEquals(Integer.valueOf(10), BINARY_SEARCH_TREE.getLeftChild(11));
	}

	@Test
	void getLeftChildOfLeftMostElementThrowsChildNotFoundException()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(15, 10, 25, 20, 30, 12, 6, 8, 4)));

		Integer toCheck = 4;
		String expectedMessage = "The element " + toCheck + " does not have a left child.";

		Exception exception = assertThrows(ChildNotFoundException.class,
				() -> BINARY_SEARCH_TREE.getLeftChild(toCheck));
		Assertions.assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void getLeftChildOfLeftMostElementOnRightSideOfTree()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(10, 5, 9, 0, 35, 40, 20, 22, 7, 15, 3, 13, 11)));

		Integer toCheck = 11;
		String expectedMessage = "The element " + toCheck + " does not have a left child.";

		Exception exception = assertThrows(ChildNotFoundException.class,
				() -> BINARY_SEARCH_TREE.getLeftChild(toCheck));
		Assertions.assertEquals(expectedMessage, exception.getMessage());

	}

	@Test
	void getLeftChildOfElementThatDoesNotExist()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(66, 33, 99, 20, 40, 80, 100)));

		Integer toCheck = 0;
		String expectedMessage = "The element " + toCheck + " does not exist in the tree.";

		Exception exception = assertThrows(ChildNotFoundException.class,
				() -> BINARY_SEARCH_TREE.getLeftChild(toCheck));
		Assertions.assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void getRightChildOfRootElement() throws ChildNotFoundException
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 20, 30)));

		Assertions.assertEquals(Integer.valueOf(30), BINARY_SEARCH_TREE.getRightChild(25));
	}

	@Test
	void getRightChildOfLeftMostElement() throws ChildNotFoundException
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(25, 20, 15, 27, 22, 13, 14)));

		Assertions.assertEquals(Integer.valueOf(14), BINARY_SEARCH_TREE.getRightChild(13));
	}

	@Test
	void getRightChildOfLeftMostElementOnRightSideOfTree() throws ChildNotFoundException
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(12, 6, 3, 10, 23, 19, 30, 22, 15, 16, 13, 14)));

		Assertions.assertEquals(Integer.valueOf(14), BINARY_SEARCH_TREE.getRightChild(13));
	}

	@Test
	void getRightChildOfRightMostElementThrowsChildNotFoundException()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(15, 10, 25, 20, 30, 27, 35)));

		Integer toCheck = 35;
		String expectedMessage = "The element " + toCheck + " does not have a right child.";

		Exception exception = assertThrows(ChildNotFoundException.class,
				() -> BINARY_SEARCH_TREE.getRightChild(toCheck));
		Assertions.assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void getRightChildOfRightMostElementOnLeftSideOfTree()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(20, 5, 9, 0, 13, 8, 16, 14, 18, 17, 19)));

		Integer toCheck = 19;
		String expectedMessage = "The element " + toCheck + " does not have a right child.";

		Exception exception = assertThrows(ChildNotFoundException.class,
				() -> BINARY_SEARCH_TREE.getRightChild(toCheck));
		Assertions.assertEquals(expectedMessage, exception.getMessage());

	}

	@Test
	void getRightChildOfElementThatDoesNotExist()
	{
		BINARY_SEARCH_TREE.addElements(new ArrayList<>(
				Arrays.asList(66, 33, 99, 20, 40, 80, 100)));

		Integer toCheck = 0;
		String expectedMessage = "The element " + toCheck + " does not exist in the tree.";

		Exception exception = assertThrows(ChildNotFoundException.class,
				() -> BINARY_SEARCH_TREE.getRightChild(toCheck));
		Assertions.assertEquals(expectedMessage, exception.getMessage());
	}
}
