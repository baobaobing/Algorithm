import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 0; //debug, empty string s
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch));
            }
            map.put(ch, i + 1);
            max = Math.max(max, i + 1 - start);
        }
        return max;
    }
}