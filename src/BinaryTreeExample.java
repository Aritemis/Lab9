import bridges.connect.Bridges;
import bridges.base.BinTreeElement;

import java.util.Iterator;

public class BinaryTreeExample 
{

	/**
	 * @param <T>
	 * @param args
	 */
	public static <T> void main(String[] args) 
	{
		//create the Bridges object
		// Use YOUR API Sha1 key and username
		Bridges<String, String> bridge = new Bridges<>(3, "268629325599", "Aritemis");

		// create elements
		// First parameter is String depicted when using mouseover with bridges animation
		// Second parameter is data object stored at that tree element
		BinTreeElement<String> root = new BinTreeElement<String>("M", "Mango");

		BinaryTree<String> tree = new BinaryTree<String>(root);
		
		// set the color and label of an Element
		root.getVisualizer().setColor("red");
		root.setLabel("Root");

		BinTreeElement<String> fTree = new BinTreeElement<String>("F", "Fig");
		BinTreeElement<String> rTree = new BinTreeElement<String>("R", "Ramen");
		BinTreeElement<String> tTree = new BinTreeElement<String>("T", "Tea");
		BinTreeElement<String> dTree = new BinTreeElement<String>("D", "D");
		BinTreeElement<String> qTree = new BinTreeElement<String>("Q", "Q");
		BinTreeElement<String> hTree = new BinTreeElement<String>("H", "H");
		BinTreeElement<String> gTree = new BinTreeElement<String>("G", "G");
		BinTreeElement<String> lTree = new BinTreeElement<String>("L", "L");

		// link elements
		rTree.setRight(tTree);
		
		hTree.setRight(lTree);
		hTree.setLeft(gTree);
		
		dTree.setRight(qTree);
		
		fTree.setRight(hTree);
		fTree.setLeft(dTree);
		
		root.setLeft(fTree);
		root.setRight(rTree);
		
		if (tree.getRoot() != null) 
		{
			// make sure we have a data structure to visualize!
			
			//pass root element of data structure
			bridge.setDataStructure(root);
//			tree.printLevels();
//			System.out.println(tree.getLeftMostData());
//			System.out.println(tree.getRightMostData());
//			System.out.println(tree.getHeight());
//			System.out.println(tree.getSize());
//			tree.removeRightMostNode();
//			Iterator inOrder = tree.getInOrderIterator();
//			while(inOrder.hasNext())
//			{
//				System.out.println(inOrder.next());
//			}
			
			Iterator postOrder = tree.getPostOrderIterator();
			while(postOrder.hasNext())
			{
				System.out.println(postOrder.next());
			}
			
			
			
			//visualize data structure
			bridge.visualize();
		}

		
	}

}
