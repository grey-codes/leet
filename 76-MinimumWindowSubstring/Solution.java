class Solution {
    public String minWindow(String s, String t) {
        // build frequency map of our desired search string; essentially a map via an array
        int[] searchMap = new int[128];
        int searchCount = 0;

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (searchMap[c] == 0) {
                searchCount++;
                searchMap[c] = 1;
            } else {
                searchMap[c] += 1;
            }
        }

        // data structure to hold result of window string; essentially a map via an array
        int[] resultMap = new int[128];
        int resultCount = 0;
        // is the window valid?
        boolean isValid = false;
        // left side of sliding window, as integer
        // also track min vals
        int left = 0, minLeft = 0, minRight = 0, minLen = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (searchMap[c] > 0) {
                resultMap[c] += 1;

                if (!isValid) { // if it's not valid yet
                    if (resultMap[c] == searchMap[c]) { // check if we've just reached the desired count for a char
                        resultCount++; // if so, increase the count of discovered result values
                        if (resultCount == searchCount) { // and if that count is the search count
                            isValid = true; // we have hit the first valid window
                        }
                    }
                }

                if (isValid) { // if we have a valid window
                    // shift left forward until the last character it's valid
                    // note: using simple iteration instead of a queue saves 3ms off 6ms on LeetCode
                    while (left < right) {
                        char leftChar = s.charAt(left);
                        int leftCount = searchMap[leftChar];
                        if (leftCount == 0)
                            left++; //skip invalid characters
                        else if (resultMap[leftChar] > leftCount) {
                            left++;
                            resultMap[leftChar]--;
                        } else {
                            break;
                        }
                    }
                    int len = right - left;
                    if (len < minLen) { // if min (or first valid)
                        minLeft = left;
                        minRight = right + 1;
                        minLen = len;
                    }
                }

            }
        }
        // calculating substring at the end instead of the beginning saves 53ms off of 56ms
        // aka, this is disproportionately expensive
        return s.substring(minLeft, minRight);
    }
}
