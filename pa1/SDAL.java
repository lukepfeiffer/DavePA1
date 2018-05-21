package pa1;
import java.lang.reflect.Array;

public class SDAL<E> implements ListInterface<E> {
	private E head;
	private E tail;
	private E[] elements;
	
	@SuppressWarnings("unchecked")
	public SDAL() {
		head = null;
		tail = null;
		
		elements = (E[])new Object[50];
	}
	
	@SuppressWarnings("unchecked")
	public SDAL( int size ) {
		head = null;
		tail = null;
		
		elements = (E[])new Object[size];
	}
	
	public int length() {
		for( int i = 0; i < elements.length; ++i ) {
			if( elements[i] == null ) {
				return i;
			}
		}
		
		return 0;
	}

	public void insert( E element, int position ) {
		//If position is greater than the tail index, dont do anything
		if( position == 0 && length() == 0 ) {
			head = element;
			tail = element;
		    
		    //Upate array
			elements[position] = element;
			
			
			if( is_full() ) {
				increaseSize();
			}
			
			return;
		}
		
		if( position > length() - 1 ) {
			return;
		}
		
		//Shift array right based on a given position
		shiftArrayRight( position );
		//If list is empty and position is head
	    if( position == 0 ) {
	    	head = element;
		    //Upate array
			elements[position] = element;
			
	    //If position is tail
	    } else if( position == length()-1 ) {
	    	tail = element;
		    
		    //Upate array
			elements[position] = element;
	    } else {
	    	elements[position] = element;
	    }

	}
	
	public void push_back( E element ) {
		//If list is empty, set tail and head element
		if( head == null ) {
			head = element;
			tail = element;
			elements[0] = element;
			return;
		}
		
		for( int i = 0; i <= length(); ++i ) {
			//Iterate over elements array until a null value is reached
			if( elements[i] == null ) {
				elements[i] = element;
				tail = element;
				
				if( is_full() ) {
					increaseSize();
				}
				
				return;
			}
		}

	}

	public void push_front( E element ) {
		//If list is empty, set tail and head element
		if( head == null ) {
			head = element;
			tail = element;
			elements[0] = element;
			return;
		}
		
		//Shift the array 1 index to the right
		shiftArrayRight();
		//Set head to new element
		head = element;
		//Update content of elements array
		elements[0] = element;
		
		//If the array is full increase the size by 50%
		if( is_full() ) {
			increaseSize();

		}
	}
	
	public E replace( E element, int position ) {
		E temp = elements[position];
		elements[position] = element;
		return temp;
	}
	
	public E remove( int position ){
		//If position is outside bounds, dont do anything
		if( position >= length() ) {
			return null;
		}
		
		//If position is 0, set head to next element
		if( position == 0 ) {
			head = elements[1];
		}
		
		//If position is 0 and only one element exists in list, set head and tail
		//And stop
		if( position == 0 && length() == 1 ) {
			E temp = head;
			head = null;
			tail = null;
			return temp;
		} else if( position == length()-1 ) {
			tail = elements[ length() - 2 ];
		}
		E element = elements[position];
		shiftArrayLeft( position );
		return element;
	}
	
	public E pop_back() {
		E temp = tail;
		int length = length();
		
		if( head == tail ) {
			elements[0] = null;
			head = null;
			tail = null;
			return temp;
		}
		
		tail = elements[length-2];
		elements[length-1] = null;
		return temp;
	}
	
	public E pop_front() {
		E temp = head;
		
		if( head == tail ) {
			elements[0] = null;
			head = null;
			tail = null;
			return temp;
		}
		
		shiftArrayLeft(0);
		head = elements[0];
		return temp;
	}
	
	public E item_at( int position ) {
		//If position is outside bounds, dont do anything
		if( position >= length() ) {
			return null;
		}
		
		return elements[position];
	}
	
	public E peek_back() {
		return tail;
	}
	
	public E peek_front() {
		return head;
	}
	
	public boolean is_empty() {
		if( head == null ) {
			return true;
		}
		
		return false;
	}
	
	public boolean is_full() {
		if( elements[ elements.length - 1 ] == null ) {
			return false;
		}
		
		return true;
	}
	
	public void clear() {
		for( int i = 0; i < length(); ++i ) {
			elements[i] = null;
		}
	}
	
	public boolean contains( E element ) {
		return true;
	}
	
	public void print() {
	}
	
	public E[] contents() {	
		E type = head;
		@SuppressWarnings("unchecked")
		E[] array = (E[]) Array.newInstance( type.getClass(), length() );
		for( int i = 0; i < length(); ++i ) {
			array[i] = this.elements[i];
		}
		
		return array;
	}
	
	//Increase size of array by 50%
	private void increaseSize() {
		int newSize = this.elements.length*2 - this.elements.length/2;
		E type = head;
		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) Array.newInstance( type.getClass(), newSize );
		
		//Copy contents into new array
		for( int i = 0; i < this.elements.length; ++i ) {
			newArray[i] = this.elements[i];
		}
		
		this.elements = newArray;
	}
	
	//Shift the array 1 index to the right
	private void shiftArrayRight() {
		for( int i = elements.length-2; i >= 0; --i ) {
			elements[i+1] = elements[i];
		}
	}
	
	//Shift the array 1 index to the right from a given index
	private void shiftArrayRight( int startIndex ) {
		for( int i = elements.length-2; i >= startIndex; --i ) {
			elements[i+1] = elements[i];
		}
	}
	
	//Shift the array 1 index to the left
	private void shiftArrayLeft( int position ) {
		for( int i = position; i < elements.length-1; ++i ) {
			elements[i] = elements[i+1];
		}
	}
}