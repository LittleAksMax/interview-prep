/**
 * Write a method to replace all the spaces in a string with '%20'.
 * Input is a character array with correct length so you can do it in place.
 * 1. T: O(N^2) S: O(1) -- iterate until space, copy everything forward two,
 *                       then add '%20' to replace ' '
 * 2. T: O(N) S: O(N) -- create second char array of same length,
 *                       iterate then replace pointer with new array's
 * 3. T: O(N) S: O(1) -- iterate backwards, and copy the last character to end
 * 
 * It's easiest to iterate backwards when replacing strings. (Hint used)
 * 
 * Q: Can the string just be all spaces, since that shouldn't be allowed by answer.
 *    (Assumed answer no)
 */


public class URLify {
    private static void urlify1(char[] s) {
        int ptr = 0;
        while (ptr != s.length) {
            // ' ', 'a', 'b' -> '%', '2', '0', 'a', 'b'
            if (s[ptr] == ' ') {
                for (int i = s.length - 1; i >= ptr + 3; i--) {
                    s[i] = s[i - 2];
                }
                s[ptr] = '%';
                s[ptr + 1] = '2';
                s[ptr + 2] = '0';
                ptr += 3;
            } else {
                ptr++;
            }
        }
    }

    private static void urlify2(char[] s) {
        // `s` should have the correct length anyway
        char[] urlified = new char[s.length];

        int sPtr = 0, urlifiedPtr = 0;

        // T: O(N) S: O(N)
        while (urlifiedPtr != urlified.length) {
            if (s[sPtr] == ' ') {
                urlified[urlifiedPtr] = '%';
                urlified[urlifiedPtr + 1] = '2';
                urlified[urlifiedPtr + 2] = '0';
                urlifiedPtr += 3;
            } else {
                urlified[urlifiedPtr++] = s[sPtr];
            }
            sPtr++;
        }

        // doesn't reassign the pointer outside this scope
        // so this technically doesnt work
        s = urlified;
    }

    private static void urlifyOptimal(char[] s) {
        // assuming string can't be all spaces
        // so lastIdx will always have a valid index != -1
        // O(N)
        int lastIdx = -1;
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] != ' ') {
                lastIdx = i;
                break;
            }
        }

        assert (lastIdx != -1) : "String can't be all spaces.";

        // T: O(N) S: O(1)
        int endIdx = s.length - 1;
        while (lastIdx >= 0) {
            if (s[lastIdx] == ' ') {
                s[endIdx] = '0';
                s[endIdx - 1] = '2';
                s[endIdx - 2] = '%';
                endIdx -= 3;
            } else {
                s[endIdx--] = s[lastIdx];
            }
            lastIdx--;
        }
    }
}