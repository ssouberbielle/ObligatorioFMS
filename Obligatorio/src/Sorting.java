public class Sorting {

    public static void mergeSort(User[] arr, int len) {
        if (len < 2) {
            return;
        }

        int mid = len / 2;
        User[] left_arr = new User[mid];
        User[] right_arr = new User[len - mid];

        //Dividing array into two and copying into two separate arrays
        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid) {
                left_arr[i] = arr[i];
            } else {
                right_arr[k] = arr[i];
                k = k + 1;
            }
        }
        // Recursively calling the function to divide the subarrays further
        mergeSort(left_arr, mid);
        mergeSort(right_arr, len - mid);
        // Calling the merge method on each subdivision
        merge(left_arr, right_arr, arr, mid, len - mid);
    }

    public static void mergeSort(AuthorYearPublications[] arr, int len) {
        if (len < 2) {
            return;
        }

        int mid = len / 2;
        AuthorYearPublications[] left_arr = new AuthorYearPublications[mid];
        AuthorYearPublications[] right_arr = new AuthorYearPublications[len - mid];

        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid) {
                left_arr[i] = arr[i];
            } else {
                right_arr[k] = arr[i];
                k = k + 1;
            }
        }
        mergeSort(left_arr, mid);
        mergeSort(right_arr, len - mid);
        merge(left_arr, right_arr, arr, mid, len - mid);
    }

    public static void merge(AuthorYearPublications[] left_arr, AuthorYearPublications[] right_arr, AuthorYearPublications[] arr, int left_size, int right_size) {

        int i = 0, l = 0, r = 0;
        while (l < left_size && r < right_size) {

            if (left_arr[l].compareTo(right_arr[r]) < 0) {
                arr[i++] = left_arr[l++];
            } else {
                arr[i++] = right_arr[r++];
            }
        }
        while (l < left_size) {
            arr[i++] = left_arr[l++];
        }
        while (r < right_size) {
            arr[i++] = right_arr[r++];
        }
    }


    public static void merge(User[] left_arr, User[] right_arr, User[] arr, int left_size, int right_size) {

        int i = 0, l = 0, r = 0;
        //The while loops check the conditions for merging
        while (l < left_size && r < right_size) {

            if (left_arr[l].compareTo(right_arr[r]) < 0) {
                arr[i++] = left_arr[l++];
            } else {
                arr[i++] = right_arr[r++];
            }
        }
        while (l < left_size) {
            arr[i++] = left_arr[l++];
        }
        while (r < right_size) {
            arr[i++] = right_arr[r++];
        }
    }

    public static void mergeSort(LanguageTimes[] arr, int len) {
        if (len < 2) {
            return;
        }

        int mid = len / 2;
        LanguageTimes[] left_arr = new LanguageTimes[mid];
        LanguageTimes[] right_arr = new LanguageTimes[len - mid];

        //Dividing array into two and copying into two separate arrays
        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid) {
                left_arr[i] = arr[i];
            } else {
                right_arr[k] = arr[i];
                k = k + 1;
            }
        }
        // Recursively calling the function to divide the subarrays further
        mergeSort(left_arr, mid);
        mergeSort(right_arr, len - mid);
        // Calling the merge method on each subdivision
        merge(left_arr, right_arr, arr, mid, len - mid);
    }


    public static void merge(LanguageTimes[] left_arr, LanguageTimes[] right_arr, LanguageTimes[] arr, int left_size, int right_size) {

        int i = 0, l = 0, r = 0;

        while (l < left_size && r < right_size) {

            if (left_arr[l].compareTo(right_arr[r]) < 0) {
                arr[i++] = left_arr[l++];
            } else {
                arr[i++] = right_arr[r++];
            }
        }
        while (l < left_size) {
            arr[i++] = left_arr[l++];
        }
        while (r < right_size) {
            arr[i++] = right_arr[r++];
        }
    }

    public static void mergeSort(Book[] arr, int len) {
        if (len < 2) {
            return;
        }
        int mid = len / 2;
        Book[] left_arr = new Book[mid];
        Book[] right_arr = new Book[len - mid];
        int k = 0;
        for (int i = 0; i < len; ++i) {
            if (i < mid) {
                left_arr[i] = arr[i];
            } else {
                right_arr[k] = arr[i];
                k = k + 1;
            }
        }
        mergeSort(left_arr, mid);
        mergeSort(right_arr, len - mid);
        merge(left_arr, right_arr, arr, mid, len - mid);
    }


    public static void merge(Book[] left_arr, Book[] right_arr, Book[] arr, int left_size, int right_size) {

        int i = 0, l = 0, r = 0;
        while (l < left_size && r < right_size) {

            if (left_arr[l].compareTo(right_arr[r]) < 0) {
                arr[i++] = left_arr[l++];
            } else {
                arr[i++] = right_arr[r++];
            }
        }
        while (l < left_size) {
            arr[i++] = left_arr[l++];
        }
        while (r < right_size) {
            arr[i++] = right_arr[r++];
        }

    }

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


