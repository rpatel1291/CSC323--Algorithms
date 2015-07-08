import java.io.PrintWriter;

public class LinkedList {
	private Node head;
	private int size;
	public LinkedList()
	{
        head = new Node(0);
        size = 0;
    }
    
    public void addToList(int job)
    {
        Node newNode = new Node(job);
        if (isEmpty())
        {
            head.setNext(newNode);
            size++;
        }
        else
        {
            Node current = head;
            while (current != null) {
                if (current.getNext() == null)
                {
                    break;
                }
                current = current.getNext();
            }
            current.setNext(newNode);
            size++;
        }
    }
    
    void removeFirst()
    {
        Node temp;
        temp = head.getNext().getNext();
        head.setNext(temp);
        size--;
    }
    
    void printLinkedList(PrintWriter outputFile)
    {
        for (Node walker = head.getNext(); walker != null; walker = walker.getNext())
        {
            outputFile.print(walker.getJobNode()+" ");
        }
        outputFile.print("\n");
    }
    
    public int getSize()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public Node getHeadNode()
    {
        return head;
    }
    
}
