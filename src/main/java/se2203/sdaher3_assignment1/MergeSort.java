package se2203.sdaher3_assignment1;

public class MergeSort implements SortingStrategy {
    private int list[];
    private SortingHubController controller;

    public MergeSort(int[] list, SortingHubController controller) {
        this.controller = controller;
        this.list = list;
    }

    @Override
    public void sort(int[] numbers) {
        mergeSort(numbers, 0, numbers.length - 1);
    }

    public void mergeSort(int[] numbers, int low, int high) {
        if (high <= low) return;

        int mid = (low + high) / 2;
        mergeSort(numbers, low, mid);
        mergeSort(numbers, mid + 1, high);
        merge(numbers, low, mid, high);
    }

    public void merge(int[] array, int low, int mid, int high) {
        // Creating temporary subarrays
        int leftArray[] = new int[mid - low + 1];
        int rightArray[] = new int[high - mid];

        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];

        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;

        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
            try {
                Thread.sleep(25);
                this.controller.updateGraph(array);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void run() {
        sort(list);
    }

}
