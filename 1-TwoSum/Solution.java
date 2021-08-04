class Solution {
    public int[] twoSum(int[] nums, int target) {
        // cache what we've already ran through into a HashMap
        // HashMaps are O(1) instead of O(N) to lookup
        HashMap<Integer,Integer> checked = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            // get this value, calc difference
            int v = nums[i];
            int d = target-v;
            // if we've seen the difference before, we have found a valid solution, so return it
            if (checked.containsKey(d)) {
                return new int[]{checked.get(d),i};
            } else {
                // otherwise cache
                checked.put(v,i);
            }
        }
        // shut up compiler
        return new int[]{};
    }
}
