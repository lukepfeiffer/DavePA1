package pa1;
import java.lang.reflect.Array;
import java.util.LinkedList;

public class CDAL<E> implements ListInterface<E> {
	public LinkedList<E[]> chains;
	private int size;
	private int chainSize = 50;
	
	public CDAL(){
		chains = new LinkedList< E[] >();
		size = 0;
		
		@SuppressWarnings("unchecked")
		E[] newArray = (E[])new Object[chainSize];
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
			E[] newArray = (E[])new Object[chainSize];
			chains.add(newArray);
		}
		
		E[] tempArray = chains.getLast();
		int position = size%chainSize;
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
		E[] array = chains.get(position/chainSize);
		E temp = null;
		
		temp = array[position % chainSize];
		array[position % chainSize] = element;
		
		return temp;
	}	 
	
	
	public E remove( int position ){
		E[] array = chains.get(position/chainSize);
		E temp = null;
		
		temp = array[position % chainSize];
		array[position % chainSize] = null;
		
		shiftArrayLeft(position);
		return temp;	
	}
	
	public E pop_back(){
		E[] array = chains.getLast();
		E temp = null;
		
		for (int i = 0; i < chainSize; ++i) {
			if (i == 49) {
				temp = array[i];
				array[i] = null;
				break;
			}
			
			if (array[i+1] == null) {
				temp = array[i];
				array[i] = null;
				break;
			}
		}
		
		return temp;
	}		
	
	public E pop_front(){
		E temp = chains.get(0)[0];
		shiftArrayLeft();
		
		return temp;
	}							 
	public E item_at( int position ){
		E[] array = chains.get(position/chainSize);
		for (int i = 0; i < array.length; ++i) {
			if (i == position%chainSize) {
				return array[i];
			}
		}
		
		return null;
	} 			
	
	public E peek_back(){
		E[] newArray = chains.get(size/chainSize);
		for (int i = 0; i < chainSize; ++i) {
			if (newArray[i+1] == null) {
				return newArray[i];
			}
		}
		
		return null;
	}
	
	public E peek_front(){
		return chains.get(0)[0];
	}
	
	public boolean is_empty(){	
		return chains.get(0)[0] == null;
	}	
	
	public boolean is_full(){
		return false;
	}					
	
	public int length(){
		return size;
	}			
	
	public void clear(){
		while (!chains.isEmpty()) {
			chains.removeFirst();
		}
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
		
		for (int i = 0; i < chainSize; ++i) {
			if (lastArray[i] == null) {
				break;
			}
			
			lastArraySize = i+1;
		}
		
		//Create a size for the array.
		int size = (chains.size()-1) * chainSize + lastArraySize;
		int currentChain = 0;
		
		@SuppressWarnings("unchecked")
		//This is the array we will return
		E[] newArray = (E[]) Array.newInstance(type.getClass(), size);
		E[] array = chains.get(currentChain);
		
		for (int i = 0; i < size; ++i) {
			//If its not the first iteration and we are at the end of a chain, do these steps
			if (i != 0 && i%chainSize == 0) {
				++currentChain;
				array = chains.get(currentChain);
			}
			//i%chainSize will equal the index of the current chain
			newArray[i] = array[i%chainSize];
		}

		return newArray;
	}	
	
	private void shiftArrayRight() {

		//Find the size of the last array
		int currentChain = chains.size() - 1;
		int lastArraySize = 0;

		E[] lastArray = chains.get(currentChain);

		//Create a size for the array.
		int newSize = (currentChain+1) * chainSize + lastArraySize;
		
		for (int i = newSize - 1; i >= 0; --i) {
			//If shifting right causes the array to go out of bounds on the last chain,
			//Create a new chain and set the first element of this new chain to
			//The last element of the last chain
			if (i % chainSize == 49 && lastArray == chains.getLast()) {
				@SuppressWarnings("unchecked")
				E[] newArray = (E[])new Object[chainSize];
				setContentsToNull(newArray);
				chains.add( newArray );
				newArray[0] = lastArray[49];
				continue;
			}
			
			//If we are on the last element of the chain, set the first element in the next
			//chain to the last element of the previous chain
			if (i % chainSize == 49) {
				--currentChain;
				E[] tempArray = chains.get(currentChain);
				lastArray[0] = tempArray[49];
				lastArray = tempArray;
				continue;
			}
			
			lastArray[i % chainSize + 1] = lastArray[i % chainSize];
		}
	}
	
	private void shiftArrayRight(int position) {
	}
	
	/*
	 * @param none
	 * @return none
	 * Shifts the contents of the array 1 index to the left
	 * Will remove the head of the list
	 */
	private void shiftArrayLeft() {	
		int arraySize = (chains.size() - 1) * chainSize;

		//Get the size of the last array
		for (int i = 0; i < chainSize; ++i) {
			if (chains.getLast()[i] == null) {
				arraySize += i;
				break;
			}
		}
		
		int currentChain = 0;
		E[] array = chains.get(currentChain);
		
		for (int i = 0; i < arraySize; ++i) {
			if ( chainSize - (i + 1) % chainSize == 0) {
				++currentChain;
				E[] newArray = chains.get(currentChain);
				array[chainSize - (i % chainSize)] = newArray[0];
				array = newArray;
				continue;
			}
			
			array[i] = array[i + 1];
		}
	}
	
	private void shiftArrayLeft(int position) {	
	}
	
	private void setContentsToNull(E[] array) {
		for (int i = 0; i < chainSize; ++i) {
			array[i] = null;
		}
	}
}