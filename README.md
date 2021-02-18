#Sort-Manager

## Introduction

The goal of this project was to develop a program that could sort a list of 
sortable values using a range of algorithms. The program is capable of generating
random lists of Integers, Double or Characters and sorting them into ascending or
descending order through the use of Bubble sort, Merge Sort and Binary Tree Sort.
The program also allows for the user generation of Binary Search Trees, providing 
functions to check the contents of the trees.

For the sorters the user can select the type of data to sort, and how it should be sorted.
They are then prompted to input the parameters for generating a random list. With the
generated list and sorted list then printed to the console along with the execution time.

For the Binary Search Tree the user can also select the type of data that it should hold.
They are then given the option to add single value, or a set of values, to the tree. When
satisfied the user can then print the resulting tree and is provided with options on if
they want to call functions to check if the tree contains an element, or to get the left
or right child of any given element.

## Background

### Bubble Sort

A simple algorithm that iterates through the unsorted list comparing adjacent elements,
swapping them if they are in the wrong order. This results in the best case time 
complexity of O(n) when the list is already sorted. However, the average time complexity
is O(n<sup>2</sup>), with up to n iterations over the list of length n needed.

### Merge Sort

A more complex, but more efficient algorithm that achieves it goal through divide and
conquer. The algorithm repeatedly divides the list up into sublists until the list are
of length 1 and then works back up merging the sorted lists. Merge sorts best and
average time complexity is the same at O(nlog(n)), considerably better than bubble sort.

### Binary Sort

#### Binary Search Trees

A Binary Search Tree is a tree where each node has two children. The left child
and all the nodes in its subtree are less that the root node, and the right child
and all the nodes in its subtree are greater than the root node.

#### Tree Traversal

Storing data in a Binary Search Tree allows an efficient method of collecting the
elements in a sorted order. By recursively adding the values in the left tree, then
the current node and then the right tree you are able to retrieve the content of
the tree in ascending order, and for descending order the reverse applies.

## Design Choices

### Interfaces

The use of interfaces allowed for a layer of abstraction, allowing the user to dynamically
chose the sorting algorithm to use.

### Generics

The Sorters and BinaryTree use generics, which allow for the sorting of lists of any
class that implements the comparable interface.

## Testing

### JUnit

To test the application I used JUnit testing. The tests cover the Bubble, Merge and
Binary sorters public sort methods as well as the exposed methods in the Binary Search
Tree.

#### Sorters

For the sorters I used an abstract test class, to define generic test that could be applied
to each Sorter implementation. The tests covered Exception throwing and result outputs
compared to the Collections sort method. The abstract class also contained two abstract
methods to be implemented by the extending class. The first to create an instance of
the sorter to be tested, and the second to create a list to be sorted.

One extending test class was created for each type of sorter, with each class covering lists
containing one of integer, double or characters. There was no need to test every Object
that extends Comparable for every sorter as the ordering of elements relied on the 
compareTo method, meaning that as long as that method behaves as expected so should the sorter.

#### Tree

For the Binary Search Tree I created a single test class, with tests covering all the paths
to a ChildNotFound Exceptions in the getLeftChild and getRightChild methods, as well as
tests to cover the successful retrieval of elements.

## Performance

### Runtime

Having talked about the theoretical time complexity of the sorting algorithms, I timed how long
each algorithm took. For small lists of less than 100 elements, the time for each algorithm came
in at well under 1 ms. This obviously didn't match the expectations discussed earlier.

To provide a more comprehensive result I performed the sorting algorithms on lists of 1000 elements.
I ran performances tests for the 3 data types of integer, double and characters. Recording the 
results 5 times for each order and each sorter for each type of data.

For integers and doubles the values in the generated list were between -100 and 100 inclusively,
and for characters in the alphabet. For Bubble Sort the average runtime for sorting the 1000 
element list was 5.7 ms. Merge sort provided a significant improvement in speed with an average
time of 0.31 ms. Binary Tree Sort came in with an even faster average of 0.23 ms. This time 
included adding the list of values to the tree, and the traversing it pull out an ordered list. 
However, as the randomly generated arrays included numerous duplicate values due to the fact 
the range of values was smaller than the size of the list, as binary search trees don't hold 
duplicate values, the sorted list contained significantly fewer elements. This would likely 
result in faster runtime for sorting.

To overcome this issue I ran further performance tests. While the new randomly generated lists
still contained 1000 elements, I adjusted the range in which the values could be, to between
-10000 and 10000. This would hopefully have an effect at ensuring considerably less duplicate values
are present. Surprisingly we had a faster average run time for both Bubble Sort and Merge Sort,
down to 3.8 ms and 0.23 ms respectively. Finally, Binary Tree Sort as before had an average
run time of 0.23 ms.

## Future Steps

### GUI

The next obvious step would be to create a user interface for the sort manager, allowing for a
clearer and more pleasant user experience than the console.

### Further Performance Testing

If time permitted, I would look at testing the performance of the sorters further. I want to know 
whether if we were sorting even larger lists would the performance lean even further towards merge 
and binary sort algorithms.
