import java.util.ArrayList;


public class PowerOfTwoMaxHeap< T extends Comparable<T> > {
    private static int childCount;
    private final ArrayList<T> heapArr;

    public PowerOfTwoMaxHeap(int childCount) {
        verifyChildCount();
        this.childCount = childCount;
        this.heapArr = new ArrayList<>();
    }

    private static void verifyChildCount() {
        if (childCount < 2) {
            throw new IllegalArgumentException("The number of child nodes must be greater or equal to 2");
        }
    }
    /**
     * insert - Inserts a new item at the end of the Heap
     * It appends the new item => o-o- + o = o-o-o
     * @param item: the nodes value to insert
     */
    public void insert(T item) {
        int appendIndex = heapArr.size(); // Get the insertion position
        heapArr.add(appendIndex, item); // Insert the node (or node's key(or node's value))
        heapify();  // Rearrange the Heap Structure to satisfy the max-heap property
    }

    /**
     * popMax - Extracts the largest node in the Heap, removing it.
     * Return: The extracted node's value
     */
    public T popMax() {
        if (!heapArr.isEmpty()) {
            T maxItem = heapArr.getFirst();
            heapArr.removeFirst();
            heapify();
            return maxItem;
        }
        return null;
    }

    /**
     * heapify - Rearrange the Heap Structure, satisfying the Max-Heap property
     * It sorts the items, swapping them up as required.
     * It not only swaps between parent item and child item, but also child to child/relations
     * allowing for a faster restructuring.
     * Best Case: O(1)
     * Worst Case: O(n)
     */
    private void heapify() {
        int i, heapSize = heapArr.size();
        T currentItem, previousItem;

        for (i = heapSize - 1; i >= 1; i--) { // start comparing at last item
            currentItem = heapArr.get(i);
            previousItem = heapArr.get(i - 1);

            // if curr element is greater that prev element, swap them satisfying max-heap property
            if (currentItem.compareTo(previousItem) > 0) {
                heapArr.set(i - 1, currentItem);
                heapArr.set(i,     previousItem);
            }
        }
    }

    public void printHeap() {
        // N -> CN1, CN2, ...
        // 1, [ 2, 3, 4,] [5, 6, 7], 8, 9, [10, 11, 12, 13, 14
        int size = heapArr.size();
        int parentIndex, childIndex, i, j;
        T parentValue, childValue;
        String output;

        for (parentIndex = 0; parentIndex < size; parentIndex++) {
            parentValue = heapArr.get(parentIndex);
            output = String.format("%s[%d]", parentValue, parentIndex);

            for (j = 0; j < childCount; j++) {
                childIndex = (childCount * parentIndex) + j;
                childValue = heapArr.get(childIndex);

                // From the 2nd line(j>0, j==1),left-pad the lines with spaces the length of output
                for (i = 0; i < output.length() && j > 0; i++) {
                    System.out.print(' ');
                }
                if (j == 0) {
                    System.out.print(" -> ");
                } else {
                    System.out.print("|-> ");
                }
                System.out.printf("%s[%d]\n", childValue, childIndex);
            }
        }
    }
}
