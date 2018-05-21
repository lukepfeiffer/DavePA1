package pa1;

public interface ListInterface<E> {
	public void insert( E element, int position );  //adds the specified element to the list at the specified position, shifting the element originally at that and those in subsequent positions one position to the ”right.“
	public void push_back( E element ); 	   		//appends the specified element to the list.
	public void push_front( E element );			//prepends the specified element to the list.
	public E replace( E element, int position );	//replaces the existing element at the specified position with the specified element and return the original element.
	public E remove( int position );				//removes and returns the the element at the specified position, shifting the subsequent elements one position to the ”left.“
	public E pop_back();							//removes and returns the element at the list's tail.
	public E pop_front();							//removes and returns the element at the list's head.
	public E item_at( int position ); 				//returns (without removing from the list) the element at the specified position.
	public E peek_back();							//returns the element at the list's tail.
	public E peek_front();							//returns the element at the list's head.
	public boolean is_empty();						//returns true IFF the list contains no elements.
	public boolean is_full();						//returns true IFF the no more elemtns can be added to the list.
	public int length();							//returns the number of elements in the list as a size_t.
	public void clear();							//removes all elements from the list.
	public boolean contains( E element );			//returns true IFF at least one of the elements of the list matches the specified element.
	public void print();					//If the list is empty, inserts "<empty list>" into the ostream; otherwise, inserts, enclosed in square brackets, the list's elements, separated by commas, in sequential order.
	public E[] contents();										//Allocates, initializes, and returns an array containing a copy of the list's elements in sequential order.
}