/**
 * Given two strings, write a method to decide whether one is a permutation of the other.
 * 1. T: O(N) S: O(1) -- constant HashMap depending on encoding + iteration (Optimal)
 * 
 * Q: What is the encoding?
 */

package arrstr;

import java.util.HashMap;

public class CheckPermutation {
    // NOTE: I still used a hashmap and not an array
    private static boolean checkPermutationOptimal(String s1, String s2) {
        // if lengths don't match, can't be permutation
        if (s1.length() != s2.length()) {
            return false;
        }

        HashMap<Character, Integer> freq = new HashMap<>();

        // T: O(N) S: O(N)
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }

        // T: O(N) S: O(1)
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);

            // character in s1 does not exist in s2
            if (!freq.containsKey(c)) {
                return false;
            }
            
            // decrement frequency
            freq.put(c, freq.get(c) - 1);

            // there is one more of one character than in the other strings
            // guaranteed to happen if not permutation
            if (freq.get(c) == -1) {
                return false;
            }
        }

        return true;
    }
}
