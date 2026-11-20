public class Main { 
    public static void main(String[] args) {
        int[] arr = {3, 7, 12, 18, 23, 27, 31, 35, 39};
        int target = 35;

        int left = 0;
        int right = arr.length - 1;
        int comparisons = 0;

    
        while (left <= right) {
            int mid = left + (right - left) / 2;
            comparisons++;

            System.out.println("left = " + left +
                ", right = " + right +
                ", mid = " + mid);

        if (arr[mid] == target) {
            System.out.println("Target found at index: " + mid);
            break;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    System.out.println("Total comparisons: " + comparisons);
    }
}