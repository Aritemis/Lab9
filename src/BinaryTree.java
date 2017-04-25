/**
 * Implementation of a binary tree
 * 
 */
import bridges.base.BinTreeElement;
import java.util.Iterator;

public class BinaryTree<T> 
{
	// the root of the binary tree
	private BinTreeElement<T> root;

	/**
	 * sets the root of this binary tree
	 * @param root
	 */
	public BinaryTree(BinTreeElement<T> root) 
	{
		this.root = root;
	}
	
	/**
	 * Returns the root of a tree.
	 * @return
	 */
	public BinTreeElement<T> getRoot()
	{
		return root;
	}

	/**
	 * prints out the level that each node appears.
	 * also sets the color of each node to yellow
	 */
	public void printLevels() 
	{
		/**
		 * We start at the root and do an inorder traversal
		 */
		printLevelHelper(root,0);
		
		
	}

	/**
	 * Helper method to print the levels
	 * @param node
	 * @param level
	 */
	private void printLevelHelper(BinTreeElement<T> node, int level) 
	{
		if (node != null)
		{
			printLevelHelper(node.getLeft(), level + 1);
			System.out.println(node.getValue()); // i.e. "visit"

			// now set the node to yellow as well as set the label to the level
			node.getVisualizer().setColor("yellow");
			node.setLabel("" + level);

			printLevelHelper(node.getRight(), level + 1);
		}
	}

	/**
	 * Highlights the left most node to green and
	 * returns the value associated with the node.
	 * @return
	 */
	public T getLeftMostData() 
	{
		if (root == null)
		{
			return null;
		}
		else
		{
			return getLeftMostHelper(root);
		}
	}

	/**
	 * Helper method to get the value and highlight the
	 * node to green.
	 * @param node
	 * @return
	 */
	private T getLeftMostHelper(BinTreeElement<T> node)
	{
		T result = null;
		if(node.getLeft() == null)
		{
			node.getVisualizer().setColor("green");
			result = node.getValue();
		}
		else
		{
			result = getLeftMostHelper(node.getLeft());
		}
		return result;
	}
	
	/**
	 * Returns the size of the tree (as the number of elements)
	 * 
	 * @return
	 */
	public int getSize() 
	{
		return sizeHelper(root);
	}
	
	

	private int sizeHelper(BinTreeElement<T> node)
	{
		int numElements = 0;
		if(node != null)
		{
			int numLeft = sizeHelper(node.getLeft());
			int numRight = sizeHelper(node.getRight());
			numElements = numLeft + numRight + 1;
		}
		return numElements;
	}

	/**
	 * Returns the height of the tree.
	 * 
	 * @return
	 */
	public int getHeight() 
	{
		return heightHelper(root, -1);
	}
	
	private int heightHelper(BinTreeElement<T> node, int maxHeight)
	{

		if(node != null)
		{
			int maxRight = heightHelper(node.getRight(), maxHeight + 1);
			int maxLeft = heightHelper(node.getLeft(), maxHeight + 1);
			if(maxLeft > maxRight)
			{
				maxHeight = maxLeft;
			}
			else
			{
				maxHeight = maxRight;
			}
		}
		return maxHeight;
	}
	
	
	/**
	 * Highlights the left most node to green and
	 * returns the value associated with the node.
	 * @return
	 */
	public T getRightMostData()
	{
		if (root == null)
		{
			return null;
		}
		else
		{
			return getRightMostHelper(root);
		}
	}


	private T getRightMostHelper(BinTreeElement<T> node) 
	{
		T result = null;
		if(node.getRight() == null)
		{
			node.getVisualizer().setColor("purple");
			result = node.getValue();
		}
		else
		{
			result = getRightMostHelper(node.getRight());
		}
		return result;
	}

	/**
	 * Removes and returns the left most node in the tree
	 * @return
	 */
	public T removeLeftMostNode() 
	{
		if (root == null)
		{
			return null;
		}
		else if (root.getLeft() == null) 
		{
			// special case - the root is the left most node

			T data = root.getValue();

			root = root.getRight();

			return data;
		}
		else 
		{
			// call helper method
			return removeLeftMostNodeHelper(root);
		}
	}

	/**
	 * Private helper method to remove the left most node in the tree
	 * @param node
	 * @return
	 */
	private T removeLeftMostNodeHelper(BinTreeElement<T> node) 
	{
		if ((node.getLeft()).getLeft() == null) 
		{
			// node is the parent whose reference must be adjusted

			// node.getLeft() is a reference to the left-most node

			// retrieve the value
			T data = node.getLeft().getValue();

			// we must adjust the left child of node so that it
			// now refers to the right child of the node being deleted
			node.setLeft((node.getLeft()).getRight());

			return data;
		}
		else 
		{
			// continue going left
			return removeLeftMostNodeHelper(node.getLeft());
		}
	}

	
	/**
	 * Removes and returns the left most node in the tree
	 * @return
	 */
	public T removeRightMostNode()
	{
		if (root == null)
		{
			return null;
		}
		else if (root.getRight() == null) 
		{
			// special case - the root is the right most node

			T data = root.getValue();

			root = root.getLeft();

			return data;
		}
		else 
		{
			// call helper method
			return removeRightMostNodeHelper(root);
		}
	}

	private T removeRightMostNodeHelper(BinTreeElement<T> node) 
	{
		T data = null;
		if ((node.getRight()).getRight() == null) 
		{
			// node is the parent whose reference must be adjusted

			// node.getRight() is a reference to the right-most node

			// retrieve the value
			data = node.getRight().getValue();

			// we must adjust the right child of node so that it
			// now refers to the left child of the node being deleted
			node.setRight((node.getRight()).getLeft());
			
		}
		else 
		{
			// continue going left
			data = removeRightMostNodeHelper(node.getRight());
		}
		return data;
	}

	public Iterator<T> getPreOrderIterator() 
	{
		return new OrderIterator();
	}

	public Iterator<T> getInOrderIterator()
	{
		return new OrderIterator(1);
	}
	
	public Iterator<T> getPostOrderIterator() 
	{
		return new OrderIterator(true);
	}

	private class OrderIterator implements Iterator<T>
	{
		private T[] elements;
		private int next;

		private OrderIterator() 
		{
			// create an array large enough to hold all elements in the tree
			elements = (T[])new Object[getSize()];
			next = 0;

			// now perform an preorder traversal
			preOrder(root);

			// reset next back to the beginning of the array
			next = 0;
		}
		
		private OrderIterator(int num)
		{
			elements = (T[])new Object[getSize()];
	
			inOrder(root);
			next = 0;
			
		}
		
		private OrderIterator(boolean bool)
		{
			elements = (T[])new Object[getSize()];
	
			postOrder(root);
			next = 0;
		}

		private void preOrder(BinTreeElement<T> node) 
		{
			if (node != null) 
			{
				elements[next++] = node.getValue();
				preOrder(node.getLeft());
				preOrder(node.getRight());
			}
		}
		
		private void postOrder(BinTreeElement<T> node) 
		{
			if (node != null) 
			{
				postOrder(node.getLeft());
				postOrder(node.getRight());
				elements[next++] = node.getValue();
			}
		}
		
		private void inOrder(BinTreeElement<T> node) 
		{
			if (node != null) 
			{
				inOrder(node.getLeft());
				elements[next++] = node.getValue();
				inOrder(node.getRight());
				
			}
		}

		public boolean hasNext() 
		{
			return (next < getSize());
		}

		public T next() 
		{
			return elements[next++];
		}
	}

}
