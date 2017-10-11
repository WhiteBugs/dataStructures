package com.dataStructures.tree;


public class TreeNode <T> {
	private TreeNode<T> left;
	private TreeNode<T> right;
	private T element;
	
	public TreeNode(T element){
		this.element = element;
	}
	
	public void setLeft(TreeNode<T> left){
		this.left=left;
	}
	
	public TreeNode<T> getLeft(){
		return left;
	}
	
	public void setRight(TreeNode<T> right){
		this.right = right;
	}
	
	public TreeNode<T> getRight(){
		return right;
	}
	
	public void setElement(T element){
		this.element = element;
	}

	public T getElement(){
		return element;
	}
}
