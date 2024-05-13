package DSA;

public class MaxHeap {
    int[] arr;
    int maxSize;
    int heapSize;

    MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        heapSize = 0;
    }

    void MaxHeapify(int i) {
        int l = lChild(i);
        int r = rChild(i);
        int largest = i;
        if (l < heapSize && arr[l] > arr[i])
            largest = l;
        if (r < heapSize && arr[r] > arr[largest])
            largest = r;
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            MaxHeapify(largest);
        }
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int lChild(int i) {
        return (2 * i + 1);
    }

    int rChild(int i) {
        return (2 * i + 2);
    }

    int removeMax() {
        if (heapSize <= 0)
            return Integer.MIN_VALUE;
        if (heapSize == 1) {
            heapSize--;
            return arr[0];
        }
        int root = arr[0];
        arr[0] = arr[heapSize - 1];
        heapSize--;

        MaxHeapify(0);

        return root;
    }

    void increaseKey(int i, int newVal) {
        arr[i] = newVal;
        while (i != 0 && arr[parent(i)] < arr[i]) {
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            i = parent(i);
        }
    }

    int getMax() {
        return arr[0];
    }

    int curSize() {
        return heapSize;
    }

    void deleteKey(int i) {
        increaseKey(i, Integer.MAX_VALUE);
        removeMax();
    }

    void insertKey(int x) {
        if (heapSize == maxSize) {
            System.out.println("\nOverflow: Could not insertKey\n");
            return;
        }

        heapSize++;
        int i = heapSize - 1;
        arr[i] = x;

        while (i != 0 && arr[parent(i)] < arr[i]) {
            int temp = arr[i];
            arr[i] = arr[parent(i)];
            arr[parent(i)] = temp;
            i = parent(i);
        }
    }

    public static void main(String[] args) {
        MaxHeap h = new MaxHeap(15);

        int k, i, n = 6;
        System.out.println("Entered 6 keys:- 3, 10, 12, 8, 2, 14 \n");
        h.insertKey(3);
        h.insertKey(10);
        h.insertKey(12);
        h.insertKey(8);
        h.insertKey(2);
        h.insertKey(14);


        System.out.println("The current size of the heap is "
                + h.curSize() + "\n");


        System.out.println("The current maximum element is " + h.getMax()
                + "\n");


        h.deleteKey(2);


        System.out.println("The current size of the heap is "
                + h.curSize() + "\n");
        h.insertKey(15);
        h.insertKey(5);
        System.out.println("The current size of the heap is "
                + h.curSize() + "\n");
        System.out.println("The current maximum element is " + h.getMax()
                + "\n");
    }
}
