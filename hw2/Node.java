package hw2;

class Node {
    int data;
    Node prev;
    Node next;

    public Node (int data){
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}