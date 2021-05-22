package DataStructure;

import com.company.ListNode;

public class Stack {
    private ListNode head;

    public Integer pop() {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        head = head.next;
        pre.next = null;
        return pre.value;
    }

    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public void push(int ele) {
        ListNode node = new ListNode(ele);
        node.next = head;
        head = node;
    }
}
