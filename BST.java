
/**Name: Anish Rajeshkumar
ID: A16670774
Email: arajeshkumar@ucsd.edu
Sources Used: tutors
File header: The purpose of this file is to implement all
the necessary methods needed to create a functioning binary 
 search tree
*/
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @param <K> The type of the keys of this BST. They need to be comparable by nature of the BST
 * "K extends Comparable" means that BST will only compile with classes that implement Comparable
 * interface. This is because our BST sorts entries by key. Therefore keys must be comparable.
 * @param <V> The type of the values of this BST. 
 */
/**Class header: This class stores multiple methods and 
 * helper methods which it 
uses to create a well functioning Binary search tree that
can update values, add keys, create lists of keys. 

* 

*/
public class BST<K extends Comparable<? super K>, V> implements DefaultMap<K, V> {
	/* 
	 * TODO: Add instance variables 
	 * You may add any instance variables you need, but 
	 * you may NOT use any class that implements java.util.SortedMap
	 * or any other implementation of a binary search tree
	 */
	Node<K,V> root;
	int size;
	
	//Comparator<K> comparator;
	/**
	 * This constructor initilizez the instance vairables and 
	 * initilizez the root node to be null and the size to be
	 * zero
	
	 */
	public BST() {
		this.root = null;
		this.size = 0;
	}
	/**
	 * A helper method 
	 * which Adds the key, value pair to this DefaultMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * @param node, key and value to set
	 * @return the root node
	 */
	private Node<K,V> set(Node<K,V> node, K key, V value) {
		if(node == null) {
			this.size++;
			return new Node<K,V>(key,value,null,null);
		}
		int comp = node.getKey().compareTo(key);
		if(comp < 0) {
			node.setRight(this.set(node.getRight(),key,value));
			return node;
		}
		else if(comp > 0) {
			node.setLeft(this.set(node.getLeft(),key,value));
			return node;
		}
		else {
			node.setValue(value);
			return node;
		}
		
		//return true;
	}
	/**
	 * A helper method 
	 * which traverses through the tree to find the key 
	 * inputted in the parameter and updates it with correct value
	 * if needed and returns the adress of that key back 
	 * to caller
	 * @param node, key and value to find
	 * @return the  node that was found
	 */
	private Node<K,V> find(Node<K,V> node, K key, V value) {
		if(node == null) {
			return null;
		}
		int comp = node.getKey().compareTo(key);
		if(comp < 0) {
			Node<K,V> right = this.find(node.getRight(),key,value);
			return right;
		}
		else if(comp > 0) {
			Node<K,V> left = this.find(node.getLeft(),key,value);
			return left;
		}
		else {
			node.setValue(value);
			return node;
		}
		
		//return true;
	}
	/**
	 * A helper method 
	 * same as above but this wont update 
	 * the key when it is found
	 * @param node, key and value to find
	 * @return the  node that was found
	 */
	private Node<K,V> find2(Node<K,V> node, K key, V value) {
		if(node == null) {
			return null;
		}
		int comp = node.getKey().compareTo(key);
		if(comp < 0) {
			Node<K,V> right = this.find2(node.getRight(),key,value);
			return right;
		}
		else if(comp > 0) {
			Node<K,V> left = this.find2(node.getLeft(),key,value);
			return left;
		}
		else {
			//node.setValue(value);
			return node;
		}
		
		//return true;
	}
	/**
	 * A helper method 
	 * which Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed

	 */
	private Node<K,V> remove(Node<K,V> node, K key, V value) {
		if(node == null) {
			return node;
		}
		int comp = node.getKey().compareTo(key);
		if(comp < 0) {
			node.setRight(this.remove(node.getRight(),key,value));
		}
		else if(comp > 0) {
			node.setLeft(this.remove(node.getLeft(),key,value));
		}
		else {
			if(node.getLeft() == null && node.getRight() == null) {
				node = null;
			}
			else if(node.getRight() != null) {
				node.setKey(this.successorKey(node));
				node.setValue(this.successorVal(node));
				node.setRight
				(this.remove(node.getRight(),node.getKey(),node.getValue()));
				
				
			}
			else {
				node.setKey(this.predecessorKey(node));
				node.setValue(this.predecessorVal(node));
				node.setLeft
				(this.remove(node.getLeft(),node.getKey(),node.getValue()));
			}
		}
		return node;
		
	}
	/**
	 * A helper method 
	 * which assists the remove method by finding the 
	 * key that needs to take the spot of the key that was removed
	 * 
	 * @param node that needs to be removed
	 * @return key that will take its place

	 */
	private K successorKey(Node<K,V> node) {
		  node = node.getRight();
		  while(node.getLeft() != null){
	            node = node.getLeft();
	        }
		  return node.getKey();
		
	}
	/**
	 * A helper method 
	 * which assists the remove method by finding the 
	 * value that needs to take the spot of the key and value
	 *  that was removed
	 * 
	 * @param node that needs to be removed
	 * @return value that will take its place
	
	 */
	private V successorVal(Node<K,V> node) {
		  node = node.getRight();
		  while(node.getLeft() != null){
	            node = node.getLeft();
	        }
		  return node.getValue();
		
	}
	/**
	 * A helper method 
	 * which assists the remove method by finding the 
	 * key that needs to take the spot of the key that was removed
	 * 
	 * @param node that needs to be removed
	 * @return key that will take its place
	
	 */
	private K predecessorKey(Node<K,V> node) {
		  node = node.getLeft();
		  while(node.getRight() != null){
	            node = node.getRight();
	        }
		  return node.getKey();
		
	}
	/**
	 * A helper method 
	 * which assists the remove method by finding the 
	 * value that needs to take the spot of the key and value
	 *  that was removed
	 * 
	 * @param node that needs to be removed
	 * @return value that will take its place

	 */
	private V predecessorVal(Node<K,V> node) {
		  node = node.getLeft();
		  while(node.getRight() != null){
	            node = node.getRight();
	        }
		  return node.getValue();
		
	}
	/**
	 * A helper method 
	 * which assists the keys 
	 * method by traversing through the BST
	 * to produce and arraylist that has all keys
	 * in ascending order
	 * 
	 * @param list to add keys, root node
	 * @return list of keys
	 
	 */
	private ArrayList<K> printSorted(ArrayList<K> Sortedlist ,Node<K,V> node) {
		
		if(node == null) {
			return Sortedlist;
		}
		printSorted(Sortedlist,node.getLeft());
		Sortedlist.add(node.getKey());
		printSorted(Sortedlist,node.getRight());
		return Sortedlist;
		
	}


	/**
	 * Adds the specified key, value pair to this DefaultMap
	 * Note: duplicate keys are not allowed
	 * 
	 * @return true if the key value pair was added to this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean put(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null ) {
			 throw new IllegalArgumentException("null key");
		}
		Node<K,V> check = this.find(this.root, key, value);
		if(check == null) {
			this.root = this.set(this.root,key,value);
			//list.add(key);
			return true;
		}
		
		return false;
	}
	/**
	 * Replaces the value that maps to the key if it is present
	 * @param key The key whose mapped value is being replaced
	 * @param newValue The value to replace the existing value with
	 * @return true if the key was in this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean replace(K key, V newValue) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null ) {
			 throw new IllegalArgumentException("null key");
		}
		Node<K,V> check = this.find2(this.root, key, newValue);
		if(check != null) {
			this.root = this.set(this.root,key,newValue);
			//System.out.println(check.getValue());
			return true;
		}
		
		return false;
	}
	/**
	 * Remove the entry corresponding to the given key
	 * 
	 * @return true if an entry for the given key was removed
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean remove(K key) throws IllegalArgumentException {
		if(key == null ) {
			 throw new IllegalArgumentException("null key");
		}
		// TODO Auto-generated method stub
		Node<K,V> check = this.find2(this.root, key, null);
		if(check != null) {
			this.root =this.remove(this.root,key,check.getValue());
			this.size--;
			//list.remove(key);
			return true;
		}
		return false;
	}
	/**
	 * Adds the key, value pair to this DefaultMap if it is not present,
	 * otherwise, replaces the value with the given value
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public void set(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null ) {
			 throw new IllegalArgumentException("null key");
		}
		this.root=this.set(this.root,key,value);
		//list.add(key);
		
	}
	/**
	 * @return the value corresponding to the specified key
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public V get(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null ) {
			 throw new IllegalArgumentException("null key");
		}
		Node<K,V> check = this.find2(this.root, key, null);
		if(check != null) {
			return check.getValue();
		}
		
		return null;
	}
	/**
	 * 
	 * @return The number of (key, value) pairs in this DefaultMap
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		
		
		return this.size;
	}

	/**
	 * 
	 * @return true iff this.size() == 0 is true
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(this.size == 0) {
			return true;
		}
		return false;
	}
	/**
	 * @return true if the specified key is in this DefaultMap
	 * @throws IllegalArgumentException if the key is null
	 */
	@Override
	public boolean containsKey(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(key == null ) {
			 throw new IllegalArgumentException("null key");
		}
		Node<K,V> check = this.find2(this.root, key, null);
		if(check != null) {
			return true;
		}
		return false;
	}

	// Keys must be in ascending sorted order
	// You CANNOT use Collections.sort() or any other sorting implementations
	// You must do inorder traversal of the tree
	/**
	 * 
	 * @return an array containing the keys of this DefaultMap. If this DefaultMap is 
	 * empty, returns array of length zero. 
	 */
	@Override
	public List<K> keys() {
		// TODO Auto-generated method stub
		ArrayList<K> Sortedlist = new ArrayList<K>();
		Sortedlist = this.printSorted(Sortedlist, this.root);
		
		return Sortedlist;
	}
	/**Class header: This class is  class that 
	 * created nodes which store keys and values and have 
	 * methods such as getters and setters to retreieve values and 
	 * keys as well as setting their right and left child in a binary 
	 * search tree

	* 

	*/
	private static class Node<K extends Comparable<? super K>, V> 
								implements DefaultMap.Entry<K, V> {
		/* 
		 * TODO: Add instance variables
		 */
		K key;
		V value;
		Node<K,V> left;
		Node<K,V> right;
		private Node(K key, V value, Node<K,V> right, Node<K,V> left) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
			
		}
		public void setKey(K key) {
			// TODO Auto-generated method stub
			this.key = key;
			
		}
		public void setLeft(Node<K,V> node) {
			// TODO Auto-generated method stub
			this.left = node;
			
		}
		public void setRight(Node<K,V> node) {
			// TODO Auto-generated method stub
			this.right = node;
			
		}
		public Node<K,V> getRight() {
			// TODO Auto-generated method stub
			return this.right;
			
		}
		public Node<K,V> getLeft() {
			// TODO Auto-generated method stub
			return this.left;
			
		}
		
		
		
		
	}
	 
}