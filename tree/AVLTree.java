package com.dataStructures.tree;

public class AVLTree<T extends Comparable<? super T>>{
	
	private AVLTreeNode<T> firstNode;
	
	public AVLTree(T element){
		this.firstNode = new AVLTreeNode<T>(element , 0);
	}
	
	public AVLTreeNode<T> getFirstNode(){
		return firstNode;
	}
	
	public void makeEmpty(){
		firstNode.element =null;
		firstNode.left = null;
		firstNode.right = null;
	}
	
	public void printElement(){
		printElement(firstNode);
	}
	private void printElement(AVLTreeNode<T> tree){
		if(tree.left!=null){
			printElement(tree.left);
		}
		System.out.print(" "+tree.element+"("+tree.height+")"+" ");
		if(tree.right!=null){
			printElement(tree.right);
		}
	}
	
	public T findMax(){
		return findMax(firstNode);
	}
	private T findMax(AVLTreeNode<T> tree){
		if(tree.right==null){
			return (T) tree.element;
		}else{
			return findMax(tree.right);
		}
	}
	
	public T findMin(){
		return findMin(firstNode);
	}
	private T findMin(AVLTreeNode<T> tree){
		if(tree.left==null){
			return (T) tree.element;
		}else{
			return findMin(tree.left);
		}
	}
	
	public boolean contains(T element) throws Exception{
		if(firstNode.element==null){
			throw new Exception(firstNode.toString()+" : this is a tree which don't have element!");
		}
		return contains(element,firstNode);
	}
	private boolean contains(T element, AVLTreeNode<T> tree){
		int result = element.compareTo((T) tree.element);
		if(result==0){
			return true;
		}else if(result<0){
			if(tree.left==null){
				return false;
			}else{
				return contains(element,tree.left);
			}
		}else if(result>0){
			if(tree.right==null){
				return false;
			}else{
				return contains(element, tree.right);
			}
		}
		return true;
	}
	
	public void insert(T element){
		this.firstNode = insert(element, firstNode);
	}
	private AVLTreeNode<T> insert(T element, AVLTreeNode<T> node){
		if(node == null)
			return new AVLTreeNode(element , 0);
		int result = element.compareTo(node.element);
		if(result < 0){
			node.left = insert(element, node.left);
		}else if(result > 0){
			node.right = insert(element, node.right);
		}
		return balance(node);
	}
	
	public void remove(T element){
		this.firstNode = remove(element, firstNode);
	}
	private AVLTreeNode<T> remove(T element, AVLTreeNode<T> node){
		int result = element.compareTo(node.element);
		if(result == 0){
			if(height(node) == 0){
				return null;
		    }else{
		    	T min = node.left==null? null : findMax(node.left);
		    	T max = node.right==null? null : findMin(node.right);
		    	if(min != null){
		    		node.element = min;
		    		node.left = remove(node.element, node.left);
		    	}else{
		    		node.element = max;
		    		node.right = remove(node.element, node.right);
		    	}
		    }
			updateHeightRecursion(firstNode);
		}else if(result < 0){
			node.left = remove(element, node.left);
		}else{
			node.right = remove(element, node.right);
		}
		return balance(node);
	}
	
	public AVLTreeNode<T> balance(AVLTreeNode<T> node){
		if(node == null)
			return null;
		updateHeightRecursion(node ,2);
		if(height(node.left) - height(node.right) > 1){
			if(height(node.left.left) >= height(node.left.right)){//Single rotation
				AVLTreeNode<T> temp = node.left.right;
				node.left.right = node;
				node = node.left;
				node.right.left = temp;
			}else{// Double rotation
				AVLTreeNode<T> tempLeft = node.left.right.left;
				AVLTreeNode<T> tempRight = node.left.right.right;
				node.left.right.left = node.left;
				node.left.right.right = node;
				node = node.left.right;
				node.left.right = tempLeft;
				node.right.left = tempRight;
			}
			updateHeightRecursion(node ,2);
		}else if(height(node.right) - height(node.left) > 1){
			if(height(node.right.right) >= height(node.right.left)){//Single rotation
				AVLTreeNode<T> temp = node.right.left;
				node.right.left = node;
				node = node.right;
				node.left.right = temp;
			}else{// Double rotation
				AVLTreeNode<T> tempLeft = node.right.left.left;
				AVLTreeNode<T> tempRight = node.right.left.right;
				node.right.left.left = node;
				node.right.left.right = node.right;
				node = node.right.left;
				node.left.right = tempLeft;
				node.right.left = tempRight;
			}
			updateHeightRecursion(node ,2 );
		}
		return node;
	}
	
	private void updateHeightRecursion(AVLTreeNode<T> node){
		updateHeightRecursion(node, 0);
	}
	private void updateHeightRecursion(AVLTreeNode<T> node, int level){
		if(node == null)
			return;
		if(level == 1){
			node.height = Math.max(height(node.left), height(node.right))+1;
			return ;
		}else if(level > 1){
			updateHeightRecursion(node.left , level-1);
			updateHeightRecursion(node.right, level-1);
		}
		updateHeightRecursion(node.left);
		updateHeightRecursion(node.right);
		node.height = Math.max(height(node.left), height(node.right))+1;	
	}
	
	private int height(AVLTreeNode<T> node){
		return node == null? -1 : node.height;
	}
	
	
	
	

}
class AVLTreeNode<T>{
	public T element;
	public AVLTreeNode<T> left;
	public AVLTreeNode<T> right;
	public int height;
	

	public AVLTreeNode(T element , int height){
		this.element = element;
		this.height = height;
	}
}
