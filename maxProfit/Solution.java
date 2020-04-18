
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length==0) {
            return 0;
        }
        int min = prices[0];
        int max =0;
        for (int i=0;i<prices.length;i++) {
            min=Math.min(min,prices[i]);
            max=Math.max(max,prices[i]-min);
        }
        return max;
    }
    public int maxProfitII(int[] prices) {
        int max = 0;
        for (int i=1;i< prices.length;i++) {
            max+=Math.max(0,(prices[i]-prices[i-1]));            
        }
        return max;
    } //muliple transaction greedy
}