
public class Node {
	private int JobNode;
	private Node next;
	public Node()
	    {
	        JobNode = 0;
	        next = null;
	    }
	    
	public Node (int newJobID)
	    {
	        JobNode = newJobID;
	        next = null;
	    }
	    
	public int getJobNode()
	    {
	        return JobNode;
	    }
	    
	public Node getNext()
	    {
	        return next;
	    }
	    
	public void setNext(Node newNext)
	    {
	        next = newNext;
	    }
}
