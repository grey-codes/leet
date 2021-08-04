/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    // essentially a modified BFS which uses HashMap instead of HashSet/bool array
    public Node helper(Node head, HashMap<Node,Node> clones) {
        // avoid null pointer exception because leetcode gives stupid edge cases
        if (head == null)
            return null;
        // now, clone this head
        Node clone = new Node(head.val);
        // make sure we only clone it once
        clones.put(head,clone);
        // if there is a next value to iterate upon
        if (head.next!=null) {
            // check if it's in our cache
            if (clones.containsKey(head.next))
                // if so, use that
                clone.next = clones.get(head.next);
            else
                // otherwise, recurse 
                clone.next = helper(head.next,clones);
        }
        // if there is a random value to iterate upon
        if (head.random!=null) {
            // check if it's in our cache
            if (clones.containsKey(head.random))
                // if so, use that
                clone.random = clones.get(head.random);
            else
                // otherwise, recurse 
                clone.random = helper(head.random,clones);
        }
        // return our cloned copy
        return clone;
    }
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> clones = new HashMap<>();
        return helper(head,clones);
    }
}
