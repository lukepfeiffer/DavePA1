package pa1;

public class Node<E> {
	E data;
	Node<E> next;
	
	public Node( E data ) {
		this.data = data;
		next = null;
	}
}