int rob(int* nums, int numsSize) {
    if (numsSize==1) {
        return nums[0];
    }
    int results[numsSize];
    results[0] = nums[0];
    results[1] = nums[1];
    int greatestSoFar=(results[0]>results[1]) ? results[0] : results[1];
    for (short i=2; i<numsSize; i++) {
        for (short j=0; j<i-1; j++) {
            int val = results[j]+nums[i];
            if (j==0 || val > results[i]) {
                results[i]=val;
                if (val > greatestSoFar) {
                    greatestSoFar = val;
                }
            }
        }
    }
    return greatestSoFar;
}