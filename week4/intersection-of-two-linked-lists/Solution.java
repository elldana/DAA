import java.util.HashSet;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> visitedNodes = new HashSet<>();
        ListNode currA = headA;
        while (currA != null) {
            visitedNodes.add(currA);
            currA = currA.next;
        }


        ListNode currB = headB;
        while (currB != null) {
            if (visitedNodes.contains(currB)) {
                return currB; 
            }
            currB = currB.next;
        }

        return null; 
    }
}