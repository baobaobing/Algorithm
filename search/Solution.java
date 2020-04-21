class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; //null case
        int mid = (int) ((left + right) / 2);
        if (right == -1) {
            return -1;
        }
        int head = nums[0];
        int tail = nums[nums.length - 1];
        while (left <= right) {
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                if (nums[mid] > tail) {
                    left = mid + 1;
                } else {
                    if (target > tail) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {
                if (nums[mid] < head) {
                    right = mid - 1;
                } else {
                    if (target >= head) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            mid = (int) ((left + right) / 2);
        }
        return -1;
    }
}