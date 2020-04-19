


class Solution {
    public int maxSubArray(int type,int[] nums) { //o(n)
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        max=Math.max(dp[0],max); //debug
        for (int i=1;i<dp.length;i++) {
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
            max=Math.max(dp[i],max);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        return sub(nums,0,nums.length-1);

    }

    public int sub(int[] nums,int left,int right) {
        if (left==right) {
            return nums[left];
        }
        if (left==right-1) {
            return  Math.max(nums[left]+nums[right],Math.max(nums[left],nums[right]));
        } //debug
        int mid = (int)((left+right)/2);
        int midMax = nums[mid];
        int leftmax = 0;
        int rightmax=0;
        int leftsum=0;
        int rightsum=0;
        int i=mid-1;
        while (i>=left) {
           leftsum+=nums[i];
           leftmax=Math.max(leftmax,leftsum);
           i-=1;
        }
        i=mid+1;
        while (i<=right) {
            rightsum+=nums[i];
            rightmax=Math.max(rightmax,rightsum);
            i+=1;
        }
        midMax=mid+Math.max(0,leftmax)+Math.max(0,rightmax);
        return Math.max(Math.max(midMax,sub(nums,left,mid-1)),sub(nums,mid+1,right));
    }
}
