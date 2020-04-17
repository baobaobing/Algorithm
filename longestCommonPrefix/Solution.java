import java.util.HashMap;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String max = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int min = Math.min(max.length(), strs[i].length()); //debug
            max = max.substring(0, min); //debug
            for (int j = 0; j < min; j++) {
                if (strs[i].charAt(j) != strs[0].charAt(j)) {
                    max = max.substring(0, j); //debug [)
                    break;
                }
            }
        }
        return max;
    }
}