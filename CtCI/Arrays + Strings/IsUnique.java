/**
 * N - length of string
 * (a) Implement an algorithm to determine if a string has all unique characters.
 * 1. T: O(N^2) S: O(1) -- comparing pairs
 * 2. T: O(N) S: O(N) -- hashtable
 * (b) What if you cannot use additional data structures?
 * 3. T: O(N log N) S: O(1) -- sort + iterate and check adjacent characters
 * 
 * The best solution is:
 * 1. Ask: Is it ASCII, extended ASCII, or Unicode? Assume it's ASCII.
 * 2. Since it's ASCII, there can only be 128 characters.
 * 3. Make an array of 128 booleans, that map the character codes to whether
 *    they are already present.
 * 4. Iterate through and use the array as a hashmap.
 * 5. If the length of the string is > 128, there must be a repeat due to the
 *    Pigeonhole Principle, so just return false immediately.
 * T: O(N) -- arguably O(1) since you will never iterate more than 128 times
 * S: O(1) -- constant memory allocated
 */

package arrstr;

import java.util.Arrays;
import java.util.HashMap;

public class IsUnique {
    private static boolean isUnique2(String s) {
        HashMap<Character, Integer> chars = new HashMap<>();

        // T: O(N) S: O(N)
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (chars.containsKey(c)) {
                chars.put(c, chars.get(c) + 1);
            } else {
                chars.put(c, 1);
            }
        }

        // T: O(N) S: O(1)
        for (char c : chars.keySet()) {
            if (chars.get(c) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUnique3(String s) {
        // T: O(N) S: O(N)
        // have to use additional data structure in Java
        char[] chars = s.toCharArray();
        
        // T: O(N log N) S: O(1) -- quicksort
        Arrays.sort(chars);

        // T: O(N) S: O(1)
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isUniqueOptimal(String s) {
        if (s.length() > 128) {
            return false;
        }

        // default false values in array
        boolean[] chars = new boolean[128];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!chars[c]) {
                chars[c] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}