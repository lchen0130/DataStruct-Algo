public class LinkedList<Type> {
    private Node<Type> head;
    public LinkedList() {
        head = new Node(data, head);
    }

    public void addtoFront(Type data) {
        head = new Node<Type>(data, head);

    }
    public boolean isEmpty() {
        return head == null;
    }

    public Node<Type> removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        Node<Type> oldHead = head;
        head = head.next;
        return oldhead;
    }

    public String toString() {
        String out = "";
        Node<Type> curr = head;
        while (curr != null) {
            out += curr + " ";
            curr = curr.next;

        }
        return out;
    }

    public class Node<Type> {
        private Type data;
        private Node<Type> next;
        private Node(Type data, Node<Type> next) {
            this.data = data;
            this.next = next;
        }

        private Node(Type data) {
            this(data, null);
        }

        public String toString() {
            return data.toString();
        }

    }

    public static void main(String[] args) {
        LinkedList<Integer> csList = new LinkedList<Integer>();
        csList.addtoFront(1332);
        csList.addtoFront(1331);
        csList.addtoFront(1301);

        LinkedList<String> taList = new LinkedList<String>();
        taList.addtoFront("Alok");
        taList.addtoFront("Sam");
        taList.addtoFront("Alex");
    }

}
