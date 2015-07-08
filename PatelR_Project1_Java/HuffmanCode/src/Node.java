
public class Node {
	//Structure of Node - 6 elements
	//letters, frequency, left, right, next, code
	private String letters,code;
	private double frequency;
	private Node next,left,right;
	public Node(){
		this(null,0.0,null,null,null,null);
	}//Node Constructor
	public Node(String letters, double frequency, Node left,
			Node right, Node next, String code){
		this.letters = letters;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
		this.next = next;
		this.code = code;
	}//Node Method
	public Node(String letters, double frequency){
		this(letters,frequency,null,null,null,"");
	}//Node Method
	public void setLetters(String letters){
		this.letters=letters;
	}//setLetters
	public void setFrequency(double frequency){
		this.frequency=frequency;
	}//setFrequency
	public void setNext(Node next){
		this.next=next;
	}//setNext
	public void setLeft(Node left){
		this.left=left;
	}//setLeft
	public void setRight(Node right){
		this.right=right;
	}//setRight
	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}//getCode
	public String getLetters(){
		return letters;
	}//getLetters
	public double getFrequency(){
		return frequency;
	}//getFrequency
	public Node getNext(){
		return next;
	}//getNext
	public Node getLeft(){
		return left;
	}//getLeft
	public Node getRight(){
		return right;
	}//getRight
}//Node Class
