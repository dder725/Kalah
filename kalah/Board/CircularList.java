package kalah.Board;

/**
 * Implementation of circular list that is used in traversal of the board
 * Based on a guide found at https://www.baeldung.com/java-circular-linked-list
 */
public class CircularList {
    private CircularListNode head = null;
    private  CircularListNode tail = null;

    public void addNode(int value) {
        CircularListNode newNode = new CircularListNode(value);

        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
        }

        tail = newNode;
        tail.nextNode = head;
    }

    public boolean containsNode(int searchValue) {
        CircularListNode currentNode = head;

        if (head == null) {
            return false;
        } else {
            do {
                if (currentNode.value == searchValue) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
            return false;
        }
    }
}

/**
 * Auxiliary class for a node of a circular list (houses, store)
 */
class CircularListNode {

    int value;
    CircularListNode nextNode;

    public CircularListNode(int value) {
        this.value = value;
    }
}
