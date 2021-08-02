class Solution {
    public void back(int[] candidates, int target,  int start, ArrayList<Integer> soFar, List<List<Integer>> combos, HashSet<List<Integer>> combosHashed) {
        if (target==0) { // if we match the target exactly
            if (combosHashed.add(soFar)) { // if we don't have the answer yet
                combos.add(soFar); // add this to our list of matches
            }
        } else {
            // otherwise, iterate through our candidates
            // we start at the start input, to prevent duplicates (allowing repeats of the same number, but not past numbers)
            for (int i=start; i<candidates.length; i++) {
                int val = candidates[i];
                // skip duplicates since any amount of n,n,n,n,n,... can essentially just be done once in order
                if (i>start && candidates[i]==candidates[start])
                    continue;
                // if the value is less than or equal to the target (prevent overshooting, or infinite loop case)
                if (val<=target) { 
                    // clone our list of used candidates
                    ArrayList<Integer> clone = new ArrayList<>(soFar);
                    // add the new value
                    clone.add(val);
                    // continue checking based on the new value, or if we hit a combination with no remaining solutions, backtrack into this loop
                    back(candidates,target-val,i+1,clone,combos,combosHashed);
                }
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> l = new ArrayList<>();
        ArrayList<Integer> l_inner = new ArrayList<>();
        HashSet<List<Integer>> hs = new HashSet<>();
        // must be sorted
        // this modifies original which you probably shouldn't do, but it's probably faster to do this than cloning
        Arrays.sort(candidates);
        back(candidates, target, 0, l_inner, l, hs);
        return l;
    }
}
