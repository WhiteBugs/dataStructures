/**
 * 
 */
package com.dataStructures.tree;


/**
 * @author JasonChan
 *
 */
public class BinaryTree <T extends Comparable<? super T>>{

	private TreeNode<T> firstNode;
	
	public BinaryTree(T element){
		firstNode = new TreeNode<T>(element);
	}

	public BinaryTree(){
		this(null);
	}
	
	public TreeNode<T> getFirstNode(){
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
	private void printElement(TreeNode<T> tree){
		if(tree.getLeft()!=null){
			printElement(tree.getLeft());
		}
		System.out.print(" "+tree.getElement()+" ");
		if(tree.getRight()!=null){
			printElement(tree.getRight());
		}
	}
	
	public T findMax(){
		return findMax(firstNode);
	}
	private T findMax(TreeNode<T> tree){
		if(tree.getRight()==null){
			return (T) tree.getElement();
		}else{
			return findMax(tree.getRight());
		}
	}
	
	public T findMin(){
		return findMin(firstNode);
	}
	private T findMin(TreeNode<T> tree){
		if(tree.getLeft()==null){
			return (T) tree.getElement();
		}else{
			return findMin(tree.getLeft());
		}
	}
	
	public void remove(T element){
		try {
			remove(element,firstNode);
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
	private void remove(T element, TreeNode<T> tree) throws Exception{
		if(!contains(element)){
			throw new Exception("this element "+element+" is not exist !");
		}
		int result = element.compareTo((T) tree.getElement());
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
					T max = findMax(tree.getLeft());
					tree.setElement(max);
					remove(max,tree.getLeft());
				}
			}
		}else if(result < 0){
			if(element.compareTo((T) tree.getLeft().getElement())==0&&
					tree.getLeft().getLeft()==null&&tree.getLeft().getRight()==null){
				tree.setLeft(null);
			}else{
				remove(element, tree.getLeft());
			}
		}else {
			if(element.compareTo((T) tree.getRight().getElement())==0&&
					tree.getRight().getLeft()==null&&tree.getRight().getRight()==null){
				tree.setRight(null);
			}else{
				remove(element, tree.getRight());
			}
		}
	}
	
	
	public boolean contains(T element) throws Exception{
		if(firstNode.getElement()==null){
			throw new Exception(firstNode.toString()+" : this is a tree which don't have element!");
		}
		return contains(element,firstNode);
	}
	private boolean contains(T element, TreeNode<T> tree){
		int result = element.compareTo((T) tree.getElement());
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
	
	public void insert(T element){
		if(firstNode.getElement()==null){
			firstNode.setElement(element);
		}else{
			insert(element,firstNode);
		}
	}
	private void insert(T element, TreeNode<T> tree){
		int result = element.compareTo((T) tree.getElement());
		if(result<0){
			if(tree.getLeft()==null){
				tree.setLeft(new TreeNode<T>(element));
			}else{
				insert(element,tree.getLeft());
			}
		}else if(result>0){
			if(tree.getRight()==null){
				tree.setRight(new TreeNode<T>(element));
			}else{
				insert(element, tree.getRight());
			}
		}
	}
}