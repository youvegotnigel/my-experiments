package interviewQuestions;

import org.testng.annotations.Test;

import java.util.Arrays;

public class ArraysTest {

    @Test
    public void findLargestNumber() {

        int[] numbers = {5, 7, -3, 9, 2, -9, 0, 3};

        System.out.println("Original Array : ");
        printArray(numbers);

        System.out.println("\n\nThe max no is : " + max(numbers));
        System.out.println("The min no is : " + min(numbers));

        System.out.println("The Maximum difference method #1 : " + (max(numbers) - min(numbers)));
        System.out.println("The Maximum difference method #2 : " + getMaxDifferenceOfArray(numbers));

        System.out.println("The second largest no is : " + getSecondLargestOfArray(numbers));


        System.out.println("\n\n------------Sorted Array by Method---------------------");
        Arrays.sort(numbers);
        printArray(numbers);

        System.out.println("\n\n------------Array in Ascending Order---------------------");
        sortTheArrayAscending(numbers);

        System.out.println("\n\n------------Array in Descending Order---------------------");
        sortTheArrayDescending(numbers);

    }

    public void printArray(int[] a) {

        for (int i : a) {
            System.out.print(i + ", ");
        }
    }

    public int max(int[] a) {
        int max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public int min(int[] a) {
        int min = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    public void sortTheArrayAscending(int[] a) {

        int temp = a[0];

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {

                if (a[i] > a[j]) {  //smallest number on top
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        printArray(a);
    }

    public void sortTheArrayDescending(int[] a) {

        int temp = a[0];

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {

                if (a[i] < a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        printArray(a);
    }

    public int getMaxDifferenceOfArray(int[] a) {
        int max = a[0];
        int min = a[0];

        for (int i = 0; i < a.length; i++) {
            if (a[i] > max)
                max = a[i];
            else if (a[i] < min)
                min = a[i];
        }
        return (max - min);
    }

    public int getSecondLargestOfArray(int[] a) {
        int largest = a[0];
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > largest) {
                secondLargest = largest;
                largest = a[i];
            } else if (a[i] > secondLargest) {
                secondLargest = a[i];
            }
        }
        return secondLargest;
    }


    @Test
    public void bubbleSort() {

        int[] a = {5, 7, -3, 9, -9, 0};
        int temp = a[0];

        System.out.println("\n\nBefore Sort : ");
        printArray(a);
        System.out.println("\n");

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {

                if (a[i] < a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            System.out.format("After executing round (%d) -> ", i+1);
            for (int k : a) {
                System.out.print(k + ", ");
            }
            System.out.println();
        }
        System.out.println("\n\nAfter Sort : ");
        printArray(a);
        System.out.println("\n\n");
    }


}
