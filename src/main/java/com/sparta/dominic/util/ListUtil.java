package com.sparta.dominic.util;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;
import com.sparta.dominic.sorter.ComparableType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class providing static list utility methods.
 */
public final class ListUtil
{
	/**
	 * Returns a random list of a the given class that extends comparable of the given size with values between the
	 * given range.
	 *
	 * @param min The min value.
	 * @param max The max value.
	 * @param size The size of the output list.
	 * @param <T> The Object type, must be comparable.
	 *
	 * @return The randomly generated list.
	 */
	public static <T extends Comparable<T>> List<T> createRandomList(int min, int max, int size, ComparableType type)
	{
		List<T> result = new ArrayList<>(size);
		Random randomGen = new Random();
		for (int i = 0; i < size; i++)
		{
			switch (type)
			{
				case INTEGER:
					result.add((T) Integer.valueOf(randomGen.nextInt(max - min + 1) + min));
					break;
				case DOUBLE:
					result.add((T) Double.valueOf(min + randomGen.nextDouble() * (max - min)));
					break;
				case CHARACTER:
					result.add((T) Character.valueOf((char) (randomGen.nextInt(max - min + 1) + min)));
			}
		}
		return result;
	}

	/**
	 * Checks if the given list is null or empty.
	 *
	 * @param listToCheck The list to check.
	 * @throws NullListException If the given list is null.
	 * @throws EmptyListException If the given list is empty.
	 */
	public static void nullAndEmptyListChecker(List<?> listToCheck) throws NullListException, EmptyListException
	{
		if (listToCheck == null)
			throw new NullListException("Can not sort a null list.");
		if (listToCheck.size() == 0)
			throw new EmptyListException("Can not sort an empty list.");
	}

	/**
	 * Swap the given elements in the given list.
	 *
	 * @param listToSort The list to swap elements in.
	 * @param firstIndex The index of the first element.
	 * @param secondIndex The index of the second element.
	 * @param <T> The type of Object in the list.
	 */
	public static <T> void swapArrayElements(List<T> listToSort, int firstIndex, int secondIndex)
	{
		T temp = listToSort.get(firstIndex);
		listToSort.set(firstIndex, listToSort.get(secondIndex));
		listToSort.set(secondIndex, temp);
	}
}
