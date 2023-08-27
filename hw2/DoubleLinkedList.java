package hw2;

class DoubleLinkedList {
    Node head;

    public DoubleLinkedList () {
        this.head = null;
    }

    public void reverse () {
        Node current = head;
        Node temp = null;
        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if(temp != null) {
            head = temp.prev;
        }
    }

}
