package hw2;

class Main {

public static void main(String[] args) {
    DoubleLinkedList list = new DoubleLinkedList();
    list.head = new Node(1);
    Node second = new Node(2);
    Node third = new Node(3);
    list.head.next = second;
    second.next = third;
    second.prev = list.head;
    third.prev = second;
    list.reverse();
    Node current = list.head;
    while (current != null) {
        System.out.println(current.data);
        current = current.next;
    }
  }
}
