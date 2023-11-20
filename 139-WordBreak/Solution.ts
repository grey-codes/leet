function wordBreak(s: string, wordDict: string[]): boolean {
  const cache: {[key: number]: boolean} = {};
  const indices: number[] = [0];
  for (const startIndex of indices) {
      for (const word of wordDict) {
          const sStr = s.substring(startIndex, startIndex + word.length);
          if (sStr === word) {
              const pos = startIndex + word.length;
              if (pos === s.length) {
                  return true;
              }
              if (!cache[pos]) {
                  cache[pos] = true;
                  indices.push(pos);
              }
          }
      }
  }
  return false;
}