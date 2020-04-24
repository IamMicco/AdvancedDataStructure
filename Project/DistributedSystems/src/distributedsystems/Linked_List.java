package distributedsystems;

public class Linked_List implements java.io.Serializable {

    private Node head = null;
    private Node currentNode = null;
    private int size = 0;

    Linked_List () {}

    public class Node implements java.io.Serializable {

        public int value;
        public Node next;
        public Node previous;
        
        Node (int nvalue) {

            value = nvalue;
        }

        Node (int nvalue, Node newNode) {

            value = nvalue;
            next = newNode;
        }
    }

    public Node getHead () {

        return head;
    }

    public int size () {

        return size;
    }

    public void append (int value) {

        if (head == null) {

            head = new Node(value);
            currentNode = head;
            size++;
        } else {

            currentNode.next = new Node(value);
            currentNode.next.previous = currentNode;
            currentNode = currentNode.next;
            size++;
        }
    }

    public int[] displayList () {

        int[] array = new int[size];
        Node current = head;

        for (int j = 0; j < size; j++) {

            array[j] = current.value;
            if (current.next != null) current = current.next;
        }

        return array;
    }

    public void remove (int value) {

        Node curNode = head;
        if (value == curNode.value) {

            head = head.next;
        }

        while (curNode.next != null) {

            if (curNode.next.value == value) {

                curNode.next = curNode.next.next;
                size--;
                break;
            }
            curNode = curNode.next;
        }
    }

    public void reverse () {

        Node prevNode = null;
        Node nextNode;
        Node curNode = head;

        while (curNode != null) {
            
            nextNode = curNode.next;
            curNode.next = prevNode;
            prevNode = curNode;
            curNode = nextNode;
        }
        head = prevNode;
    }

    // public static void main(String[] args) {
        
    //     Linked_List linkedList = new Linked_List();
    //     Linked_List.List list = linkedList.new List(23);

    //     list.append(34);
    //     list.append(334);
    //     list.append(354);
    //     list.append(348);
    //     list.append(8734);

    //     Linked_List.Node[] result = list.displayList();
    //     for (int i = 0; i < list.size; i++) {
            
    //         System.out.print(result[i].value + " ");
    //     }
        
    //     System.out.print("\n");
        
    //     System.out.print("\n");
    //     list.reverse();
        
    //     Linked_List.Node[] result2 = list.displayList();
    //     for (int i = 0; i < list.size; i++) {

    //         System.out.print(result2[i].value + " ");
    //     }
    // }
}