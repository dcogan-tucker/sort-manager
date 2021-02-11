package com.sparta.dominic.util;

import com.sparta.dominic.exception.EmptyListException;
import com.sparta.dominic.exception.NullListException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayUtil
{
	public static <T extends Comparable<T>> List<T> createRandomList(int min, int max, int amount, Class<T> clazz)
	{
		List<T> result = new ArrayList<>(amount);
		Random randomGen = new Random();
		for (int i = 0; i < amount; i++)
		{
			if (clazz == Integer.class)
			{
				result.add((T) Integer.valueOf(randomGen.nextInt(max - min + 1) + min));
			}
			else if (clazz == Double.class)
			{
				result.add((T) Double.valueOf(min + randomGen.nextDouble() * (max - min)));
			}
			else if (clazz == Character.class)
			{
				result.add((T) Character.valueOf((char) (randomGen.nextInt(max - min + 1) + min)));
			}
		}
		return result;
	}

	public static <T extends Comparable<T>> void nullAndEmptyListChecker(List<T> listToSort) throws NullListException, EmptyListException
	{
		if (listToSort == null)
			throw new NullListException("");
		if (listToSort.size() == 0)
			throw new EmptyListException("");
	}

	public static <T extends Comparable<T>> void swapArrayElements(List<T> listToSort, int firstIndex, int secondIndex)
	{
		T temp = listToSort.get(firstIndex);
		listToSort.set(firstIndex, listToSort.get(secondIndex));
		listToSort.set(secondIndex, temp);
	}
}
