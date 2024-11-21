/******************************************************************
 *
 *   Mariano Garcia Melo / Section 001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueSleds methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param      - if true, method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true' (or ascending sort).
     */

    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {
        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            int selected_index = i;

            for (int j = i + 1; j < n; j++) {
                if (ascending) {
                    if (values[j] < values[selected_index]) {
                        selected_index = j;
                    }
                } else {
                    if (values[j] > values[selected_index]) {
                        selected_index = j;
                    }
                }
            }

            // Swap values[i] and values[selected_index]
            int temp = values[i];
            values[i] = values[selected_index];
            values[selected_index] = temp;
        }
    }

    /**
     * Method mergeSortDivisibleByKFirst
     *
     * This method will perform a merge sort algorithm. However, all numbers
     * that are divisible by the argument 'k' are returned first in the sorted
     * list.
     *
     * @param values - input array to sort per definition above
     * @param k      - value k, such that all numbers divisible by this value are first
     */
    public void mergeSortDivisibleByKFirst(int[] values, int k) {
        if (k == 0) return;
        if (values.length <= 1) return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, idx = left;

        while (i < n1 && j < n2) {
            if (customCompare(L[i], R[j], k) <= 0) {
                arr[idx++] = L[i++];
            } else {
                arr[idx++] = R[j++];
            }
        }

        while (i < n1) {
            arr[idx++] = L[i++];
        }

        while (j < n2) {
            arr[idx++] = R[j++];
        }
    }

    private int customCompare(int a, int b, int k) {
        boolean aDiv = a % k == 0;
        boolean bDiv = b % k == 0;

        if (aDiv && bDiv) {
            return Integer.compare(a, b);
        } else if (aDiv) {
            return -1; // 'a' comes before 'b'
        } else if (bDiv) {
            return 1; // 'b' comes before 'a'
        } else {
            return Integer.compare(a, b);
        }
    }

    /**
     * Method asteroidsDestroyed
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     *
     * @param mass      - integer value representing the mass of the planet
     * @param asteroids - integer array of the mass of asteroids
     * @return - return true if all asteroids destroyed, else false.
     */
    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long currMass = mass;

        for (int asteroid : asteroids) {
            if (currMass >= asteroid) {
                currMass += asteroid;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Method numRescueSleds
     *
     * Return the minimum number of rescue sleds to carry every given person.
     *
     * @param people - an array of weights for people that need to go in a sled
     * @param limit  - the weight limit per sled
     * @return - the minimum number of rescue sleds required to hold all people
     */
    public static int numRescueSleds(int[] people, int limit) {
        Arrays.sort(people);
        int sleds = 0;
        int i = 0, j = people.length - 1;

        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
                j--;
            } else {
                j--;
            }
            sleds++;
        }
        return sleds;
    }
}
