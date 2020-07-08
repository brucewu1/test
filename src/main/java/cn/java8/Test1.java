package cn.java8;

public class Test1 {
    public static void main(String[] args) {

    }
    class ListNode{
        ListNode next;
        int value;

        public ListNode() {
        }

        public ListNode(ListNode next) {
            this.next = next;
        }

        public ListNode(ListNode next, int value) {
            this.next = next;
            this.value = value;
        }
    }

    public static ListNode oddEventList(ListNode head){
        if(head == null){
            return null;
        }
        ListNode odd = head,event = head.next,eventHead = event;
        while (event != null && event.next != null){
            odd.next = event.next;
            odd = odd.next;
            event.next = odd.next;
            event = event.next;
        }
        odd.next = eventHead;
        return head;
    }
}
