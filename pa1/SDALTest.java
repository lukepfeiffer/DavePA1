package pa1;

import org.junit.jupiter.api.Test;

class SDALTest {
	
	//This test relies on contents working
	@Test
	void push_frontTest() {
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_front( 5 );
		list.push_front( 10 );
		list.push_front( 15 );
		list.push_front( 20 );
		Integer [] array =  list.contents();
		assert( array[0] == 20 );
		assert( array[1] == 15 );
		assert( array[2] == 10 );
		assert( array[3] == 5 );
	}
	
	//This test relies on contents working
	@Test
	void push_backTest() {
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_back( 5 );
		list.push_back( 10 );
		list.push_back( 15 );
		list.push_back( 20 );
		Integer [] array =  list.contents();
		assert( array[0] == 5 );
		assert( array[1] == 10 );
		assert( array[2] == 15 );
		assert( array[3] == 20 );
	}
	
	//This test relies on contents working
	@Test
	void insertTest() {
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_front( 1 );
		list.insert( 5, 0);
		list.insert( 10, 1 );
		list.insert( 15, 0 );
		list.insert( 20, 2 );
		list.insert( 25, 1 );
		Integer [] array =  list.contents();

		assert( array[0] == 15 );
		assert( array[1] == 25 );
		assert( array[2] == 5 );
		assert( 20 == array[3] );
		assert( 10 == array[4] );
		assert( 1 == array[5] );
	}
	
	//This test relies on contents working
	@Test
	void replaceTest() {
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_back( 10 );
		list.push_back( 20 );
		list.push_back( 30 );
		list.push_back( 40 );
		list.push_back( 50 );
		list.push_back( 60 );
		
		assert( list.replace( 3, 0 ) == 10 );
		Integer [] array =  list.contents();
		assert( array[0] == 	3 );
		
		assert( list.replace( 6,  1) == 20 );
		array =  list.contents();
		assert( array[1] == 6 );

	}
	
	//This test relies on contents working
	@Test
	void listIsFullTest() {
		ListInterface<Integer> list = new SDAL<Integer>( 4 );
		list.push_back( 10 );
		list.push_back( 20 );
		list.push_back( 30 );
		list.push_back( 40 );
		list.push_back( 50 );
		Integer [] array =  list.contents();
		array =  list.contents();
		assert( array.length == 5 );
		assert( array[3] == 40 );
		assert( array[4] == 50 );
	}
	
	//This test relies on contents working
	@Test
	void removeTest() {
		ListInterface<Integer> list = new SDAL<Integer>( 4 );
		list.push_back( 10 );
		list.push_back( 20 );
		list.push_back( 30 );
		list.push_back( 40 );
		list.push_back( 50 );
		
		assert( list.remove(0) == 10 );
		assert( list.remove(0) == 20 );
		assert( list.remove(2) == 50 );
		
		Integer [] array =  list.contents();
		assert( array[0] == 30 );
		assert( array[1] == 40 );
	}
	
	//Popback test
	@Test
	void pop_backTest() {
		
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_back( 10 );
		list.push_back( 20 );
		list.push_back( 30 );
		list.push_back( 40 );
		list.push_back( 50 );
		

		assert( list.pop_back() == 50 );
		assert( list.pop_back() == 40 );
		assert( list.pop_back() == 30 );
		assert( list.pop_back() == 20 );
		assert( list.pop_back() == 10 );
		assert( list.pop_back() == null );
	}
	
	//Popfront test
	@Test
	void pop_frontTest() {
		
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_back( 10 );
		list.push_back( 20 );
		list.push_back( 30 );
		list.push_back( 40 );
		list.push_back( 50 );
		

		assert( list.pop_front() == 10 );
		assert( list.pop_front() == 20 );
		assert( list.pop_front() == 30 );
		assert( list.pop_front() == 40 );
		assert( list.pop_front() == 50 );
		assert( list.pop_front() == null );
	}
	
	
	//Popfront test
	@Test
	void item_atTest() {
		
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_back( 10 );
		list.push_back( 20 );
		list.push_back( 30 );
		list.push_back( 40 );
		list.push_back( 50 );
		

		assert( list.item_at(0) == 10 );
		assert( list.item_at(1) == 20 );
		assert( list.item_at(2) == 30 );
		assert( list.item_at(3) == 40 );
		assert( list.item_at(4) == 50 );
		assert( list.item_at(5) == null );
	}
	
	@Test
	void peek_backTest() {
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_back( 10 );
		assert( list.peek_back() == 10 );
		list.push_back( 20 );
		assert( list.peek_back() == 20 );
		list.push_back( 30 );
		assert( list.peek_back() == 30 );
		list.push_back( 40 );
		assert( list.peek_back() == 40 );
		list.push_back( 50 );
		assert( list.peek_back() == 50 );
	}
	
	@Test
	void peek_frontTest() {
		ListInterface<Integer> list = new SDAL<Integer>();
		list.push_front( 10 );
		assert( list.peek_front() == 10 );
		list.push_front( 20 );
		assert( list.peek_front() == 20 );
		list.push_front( 30 );
		assert( list.peek_front() == 30 );
		list.push_front( 40 );
		assert( list.peek_front() == 40 );
		list.push_front( 50 );
		assert( list.peek_front() == 50 );
	}
	
}