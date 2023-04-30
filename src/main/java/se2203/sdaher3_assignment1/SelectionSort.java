package se2203.sdaher3_assignment1;

public class SelectionSort implements SortingStrategy {
    private int[] list;
    private SortingHubController controller;

    // constructor class, controller will be used to call methods from SortingHubController class
    public SelectionSort(int[] list, SortingHubController controller) {
        this.controller = controller;
        this.list = list;
    }

    @Override
    public void sort(int[] numbers) {
        list = numbers;
        int n = numbers.length;

        for (int i = 0; i < n; i++) {
            int minIndex = i; // initialize minIndex first unsorted item each time through the outer loop
            // comparing,if j is less than minIndex, change the
            for (int j = i; j < n; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }

            // updating the screen during the sorting process
            // swapping numbers[i] and numbers[minIndex]
            int temp = numbers[minIndex];
            numbers[minIndex] = numbers[i];
            numbers[i] = temp;
            try {
                Thread.sleep(100);
                this.controller.updateGraph(numbers);
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
