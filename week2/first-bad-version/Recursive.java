public class Recursive {

    public static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (target < arr[mid]) {
            return binarySearch(arr, target, left, mid - 1);
        } else {
            return binarySearch(arr, target, mid + 1, right);
        }
    }

    public static void main(String[] args) {

        int[] arr = { 3, 7, 12, 18, 23, 27, 31, 35, 39 };

        int target = 20;

        int position = binarySearch(arr, target, 0, arr.length - 1);

        System.out.println("Insertion position: " + position);
    }
}