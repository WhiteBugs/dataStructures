package com.dataStructures.tree;


public class TreeNode {
	private TreeNode left;
	private TreeNode right;
	private Comparable element;
	
	public TreeNode(Comparable element){
		this.element = element;
	}
	
	public void setLeft(TreeNode left){
		this.left=left;
	}
	
	public TreeNode getLeft(){
		return left;
	}
	
	public void setRight(TreeNode right){
		this.right = right;
	}
	
	public TreeNode getRight(){
		return right;
	}
	
	public void setElement(Comparable element){
		this.element = element;
	}

	public Comparable getElement(){
		return element;
	}
}
