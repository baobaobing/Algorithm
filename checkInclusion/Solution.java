import java.util.HashMap;
import java.util.LinkedList;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        LinkedList<Character> queue = new LinkedList<>();
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] tag = new int[26];
        int[] test = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            tag[s1.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s2.length(); i++) {
            if (queue.size() == s1.length()) {
                Character ch = queue.removeFirst();
                test[ch - 'a'] -= 1;
            }
            if (tag[s2.charAt(i) - 'a'] == 0) { //debug: judgement sequence
                queue.clear();
                test = new int[26];
                continue;
            }
            queue.addLast(s2.charAt(i));
            test[s2.charAt(i) - 'a'] += 1; //debug: paste and revise
            if (isEqual(tag, test)) { //debug: for end
                return true;
            }
        }
        return false;
    }

    public boolean isEqual(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}