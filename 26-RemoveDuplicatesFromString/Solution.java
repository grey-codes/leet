class Solution {
    public int removeDuplicates(int[] nums) {
        int idx = 0;
        for (int i=0; i<nums.length; i++) {
            int v = nums[i];
            nums[idx++]=v;
            while (i+1<nums.length && nums[i+1]==v)
                i++;
        }
        return idx;
    }
}
