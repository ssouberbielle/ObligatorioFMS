public class BubbleSort {
    public static void bubbleSort(User arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j].compareToAvg(arr[j + 1]) < 0) // si el ratingavg de la izq>der
                {
                    // swapea temp y arr[i]
                    User temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
}

