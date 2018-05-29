package pa1;
import java.lang.reflect.Array;
import java.util.LinkedList;

public class CDAL<E> implements ListInterface<E> {
	public LinkedList<E[]> chains;
	private int size;
	
	public CDAL(){
		chains = new LinkedList< E[] >();
		size = 0;
		
		@SuppressWarnings("unchecked")
		E[] newArray = (E[])new Object[50];
		setContentsToNull(newArray);
		
		chains.add( newArray );
	}
	
	public void insert( E element, int position ){
	}
	
	public void push_back( E element ){	
		if (chains.get(0)[0] == null) {
			chains.get(0)[0] = element;
			++size;
			return;
		}
		
		if (chains.getLast()[49] != null) {
			@SuppressWarnings("unchecked")
			E[] newArray = (E[])new Object[50];
			chains.add(newArray);
		}
		
		E[] tempArray = chains.getLast();
		int position = size%50;
		tempArray[position] = element;
		++size;
	} 	   		 
	
	public void push_front( E element ) {
		if (chains.get(0)[0] != null) {
			shiftArrayRight();
		}

		chains.get(0)[0] = element;
		++size;	
	}
	
	public E replace( E element, int position ){
		return element;
	}	 
	public E remove( int position ){
		return null;
	}
	
	public E pop_back(){
		return null;
	}							 
	public E pop_front(){
		return null;
	}							 
	public E item_at( int position ){
		return null;
	} 				 
	public E peek_back(){
		return null;
	}							 
	public E peek_front(){
		return null;
	}
	
	public boolean is_empty(){	
		return false;
	}	
	
	public boolean is_full(){
		return true;
	}					
	
	public int length(){
		return -1;
	}							 
	public void clear(){
	}			
	
	public boolean contains( E element ){
		for (int i = 0; i < chains.size(); ++i) {
			
			for (int k = 0; k < chains.get(i).length; ++k) {
				if (element == chains.get(i)[k]) {
					return true;
				}
			}
		}
		
		return false;
	}		
	
	public void print(){
	}		
	
	public E[] contents(){
		E type = chains.getFirst()[0];

		if (type == null) {
			return null;
		}
		
		//Find the size of the last array
		int lastArraySize = 0;
		E[] lastArray = chains.getLast();
		
		for (int i = 0; i < 50; ++i) {
			if (lastArray[i] == null) {
				break;
			}
			
			lastArraySize = i+1;
		}
		
		//Create a size for the array.
		int size = (chains.size()-1) * 50 + lastArraySize;
		int currentChain = 0;
		
		@SuppressWarnings("unchecked")
		//This is the array we will return
		E[] newArray = (E[]) Array.newInstance(type.getClass(), size);
		E[] array = chains.get(currentChain);
		
		for (int i = 0; i < size; ++i) {
			//If its not the first iteration and we are at the end of a chain, do these steps
			if (i != 0 && i%50 == 0) {
				++currentChain;
				array = chains.get(currentChain);
			}
			//i%50 will equal the index of the current chain
			newArray[i] = array[i%50];
		}

		return newArray;
	}	
	
	private void shiftArrayRight() {

		//Find the size of the last array
		int currentChain = chains.size() - 1;
		int lastArraySize = 0;

		E[] lastArray = chains.get(currentChain);

		//Create a size for the array.
		int newSize = (currentChain+1) * 50 + lastArraySize;
		
		for (int i = newSize - 1; i >= 0; --i) {
			//If shifting right causes the array to go out of bounds on the last chain,
			//Create a new chain and set the first element of this new chain to
			//The last element of the last chain
			if (i % 50 == 49 && lastArray == chains.getLast()) {
				@SuppressWarnings("unchecked")
				E[] newArray = (E[])new Object[50];
				setContentsToNull(newArray);
				chains.add( newArray );
				newArray[0] = lastArray[49];
				continue;
			}
			
			//If we are on the last element of the chain, set the first element in the next
			//chain to the last element of the previous chain
			if (i % 50 == 49) {
				--currentChain;
				E[] tempArray = chains.get(currentChain);
				lastArray[0] = tempArray[49];
				lastArray = tempArray;
				continue;
			}
			
			lastArray[i % 50 + 1] = lastArray[i % 50];
		}
	}
	
	private void setContentsToNull(E[] array) {
		for (int i = 0; i < 50; ++i) {
			array[i] = null;
		}
	}
}