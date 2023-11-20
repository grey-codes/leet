function climbStairs(n: number): number {
  const results: number[] = [];
  results[0]=0;
  results[1]=1;
  results[2]=2;
  results[3]=3;
  for (let i=4; i<=n; i++) {
      results[i]=results[i-1]+results[i-2];
  }
  return results[n];
};