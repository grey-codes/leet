function rob(nums: number[]): number {
  const results: number[] = [];
  if (nums.length===1) {
      return nums[0];
  }
  results[0] = nums[0];
  results[1] = nums[1];
  let greatestSoFar=(results[0]>results[1]) ? results[0] : results[1];
  for (let i=2; i<nums.length; i++) {
      for (let j=0; j<i-1; j++) {
          const val = results[j]+nums[i];
          if (j===0 || val > results[i]) {
              results[i]=val;
              if (val > greatestSoFar) {
                  greatestSoFar = val;
              }
          }
      }
  }
  return greatestSoFar;
};