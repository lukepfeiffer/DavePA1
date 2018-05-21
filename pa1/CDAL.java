package pa1;
import java.lang.reflect.Array;
import java.util.LinkedList;

public class CDAL<E> implements ListInterface<E> {
	public LinkedList<E[]> chains;
	public E head;
	public E tail;
	
	public CDAL(){
		head = null;
		tail = null;
		chains = new LinkedList< E[] >();
		
		@SuppressWarnings("unchecked")
		E[] newArray = (E[])new Object[50];
		chains.add( newArray );
	}
	
	public void insert( E element, int position ){
	}
	
	public void push_back( E element ){	
	} 	   		 
	
	public void push_front( E element ){	
	}
	
	public E replace( E element, int position ){
		return element;
	}	 
	public E remove( int position ){
		return head;
	}
	
	public E pop_back(){
		return tail;
	}							 
	public E pop_front(){
		return head;
	}							 
	public E item_at( int position ){
		return head;
	} 				 
	public E peek_back(){
		return tail;
	}							 
	public E peek_front(){
		return head;
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
		return true;
	}		
	
	public void print(){
		
	}					
	public E[] contents(){
		return null;
	}										
}