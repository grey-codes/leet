class Solution {
    public void back(int[] candidates, int target,  int start, ArrayList<Integer> soFar, List<List<Integer>> combos) {
        if (target==0) { // if we match the target exactly
            combos.add(soFar); // add this to our list of matches
        } else {
            // otherwise, iterate through our candidates
            // we start at the start input, to prevent duplicates (allowing repeats of the same number, but not past numbers)
            for (int i=start; i<candidates.length; i++) {
                int val = candidates[i];
                // if the value is less than or equal to the target (prevent overshooting, or infinite loop case)
                if (val<=target) { 
                    // clone our list of used candidates
                    ArrayList<Integer> clone = (ArrayList<Integer>)soFar.clone();
                    // add the new value
                    clone.add(val);
                    // continue checking based on the new value, or if we hit a combination with no remaining solutions, backtrack into this loop
                    back(candidates,target-val,i,clone,combos);
                }
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> l = new ArrayList<>();
        ArrayList<Integer> l_inner = new ArrayList<>();
        back(candidates, target, 0, l_inner, l);
        return l;
    }
}
