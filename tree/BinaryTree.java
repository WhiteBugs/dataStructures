/**
 * 
 */
package com.dataStructures.tree;


/**
 * @author JasonChan
 *
 */
public class BinaryTree {

	private TreeNode firstNode;
	
	public BinaryTree(Comparable element){
		firstNode = new TreeNode(element);
	}

	public BinaryTree(){
		this(null);
	}
	
	public TreeNode getFirstNode(){
		return firstNode;
	}
	
	public void makeEmpty(){
		firstNode.setElement(null);
		firstNode.setLeft(null);
		firstNode.setRight(null);
	}
	
	public void printElement(){
		printElement(firstNode);
	}
	private void printElement(TreeNode tree){
		if(tree.getLeft()!=null){
			printElement(tree.getLeft());
		}
		System.out.print(" "+tree.getElement()+" ");
		if(tree.getRight()!=null){
			printElement(tree.getRight());
		}
	}
	
	public Comparable findMax(){
		return findMax(firstNode);
	}
	private Comparable findMax(TreeNode tree){
		if(tree.getRight()==null){
			return tree.getElement();
		}else{
			return findMax(tree.getRight());
		}
	}
	
	public Comparable findMin(){
		return findMin(firstNode);
	}
	private Comparable findMin(TreeNode tree){
		if(tree.getLeft()==null){
			return tree.getElement();
		}else{
			return findMin(tree.getLeft());
		}
	}
	
	public void remove(Comparable element){
		try {
			remove(element,firstNode);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	private void remove(Comparable element, TreeNode tree) throws Exception{
		if(!contains(element)){
			throw new Exception("this element "+element+" is not exist !");
		}
		int result = element.compareTo(tree.getElement());
		if(result == 0){
			if(tree.getLeft()==null){
				if(tree.getRight()==null){
					tree.setElement(null);
				}else{
					tree.setElement(tree.getRight().getElement());
					tree.setLeft(tree.getRight().getLeft());
					tree.setRight(tree.getRight().getRight());
				}
				
			}else{
				if(tree.getLeft().getLeft()==null&&tree.getLeft().getRight()==null){
					tree.setElement(tree.getLeft().getElement());
					tree.setLeft(null);
				}else{
					Comparable max = findMax(tree.getLeft());
					tree.setElement(max);
					remove(max,tree.getLeft());
				}
			}
		}else if(result < 0){
			if(element.compareTo(tree.getLeft().getElement())==0&&
					tree.getLeft().getLeft()==null&&tree.getLeft().getRight()==null){
				tree.setLeft(null);
			}else{
				remove(element, tree.getLeft());
			}
		}else {
			if(element.compareTo(tree.getRight().getElement())==0&&
					tree.getRight().getLeft()==null&&tree.getRight().getRight()==null){
				tree.setRight(null);
			}else{
				remove(element, tree.getRight());
			}
		}
	}
	
	
	public boolean contains(Comparable element) throws Exception{
		if(firstNode.getElement()==null){
			throw new Exception(firstNode.toString()+" : this is a tree which don't have element!");
		}
		return contains(element,firstNode);
	}
	private boolean contains(Comparable element, TreeNode tree){
		int result = element.compareTo(tree.getElement());
		if(result==0){
			return true;
		}else if(result<0){
			if(tree.getLeft()==null){
				return false;
			}else{
				return contains(element,tree.getLeft());
			}
		}else if(result>0){
			if(tree.getRight()==null){
				return false;
			}else{
				return contains(element, tree.getRight());
			}
		}
		return true;
	}
	
	public void insert(Comparable element){
		if(firstNode.getElement()==null){
			firstNode.setElement(element);
		}else{
			insert(element,firstNode);
		}
	}
	private void insert(Comparable element, TreeNode tree){
		int result = element.compareTo(tree.getElement());
		if(result<0){
			if(tree.getLeft()==null){
				tree.setLeft(new TreeNode(element));
			}else{
				insert(element,tree.getLeft());
			}
		}else if(result>0){
			if(tree.getRight()==null){
				tree.setRight(new TreeNode(element));
			}else{
				insert(element, tree.getRight());
			}
		}
	}
}
