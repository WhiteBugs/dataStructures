package com.dataStructures.tree;

public class SplayTree extends BinaryTree<Integer>{

	public static void main(String[] args) {
		SplayTree tree = new SplayTree(-1);
		for(int i=0; i<10; i++){
			tree.insert(i);
		}
		for(int i=2; i<4; i++){
			System.out.println(tree.search(i).getElement());
		}
		tree.printStructure();

	}

	public SplayTree(int element){
		super(element);
	}
	
	public TreeNode<Integer> search(Integer element){
		this.firstNode = search(element, firstNode);
		return this.firstNode;
	}
	private TreeNode<Integer> search(Integer element , TreeNode<Integer> node){
		if(node == null)
			return null;
		int result = element.compareTo((int)node.getElement());
		if( result < 0 ){
			node.setLeft(search(element, node.getLeft()));
			return singleRotation(node , true);
		}else if(result > 0){
			node.setRight(search(element, node.getRight()));
			return singleRotation(node, false);
		}
		return node;
	}
	
	private TreeNode<Integer> singleRotation(TreeNode<Integer> node , boolean isLeft){
		if(node == null)
			return null;
		if(isLeft){
			TreeNode<Integer> leftNode = node.getLeft();
			node.setLeft(leftNode.getRight());
			leftNode.setRight(node);
			node = leftNode;
			return node;
		}else{
			TreeNode<Integer> rightNode = node.getRight();
			node.setRight(rightNode.getLeft());
			rightNode.setLeft(node);
			node = rightNode;
			return node;
		}
	}
}
