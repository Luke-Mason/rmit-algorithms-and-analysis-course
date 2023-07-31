
/**
 * AVL tree implementation.  Extends BST class, overriding insertions.
 * 
 * @author jefcha
 */
public class AVLTree extends BST
{

	/**
	 * Constructor.
	 */
	public AVLTree() {
        super();
	}


	/**
     * Add key to tree.
     * 
     * @param key Key to add to tree.
     */
	@Override
	public void insert(int key) {
		mRoot = insert(mRoot, key);
	}
	

    /**
     * Recursive method to add key to tree.  Will perform necessary rotations to rebalance tree after insertion.
     * 
     * @param root Root node of tree to add 'key'.
     * @param key Key to add to tree.
     * @return Update root node.
     */
	@Override 
	protected Node insert(Node root, int key) {
		// check if root is empty
		if (root == null) {
			root = new Node(key);
		}
        else if (key < root.mKey) {
            // insert into left subtree
			root.mLeftChild = insert(root.mLeftChild, key);
		}
		else if (key > root.mKey) {
            // insert into right subtree
			root.mRightChild = insert(root.mRightChild, key);

			// check balance factor
            if (balanceFactor(root) <= -2)
                if (key > root.mRightChild.mKey) {
                    root = leftRotation(root);
                }
                else {
            		root = rightLeftRotation(root);
                }
		}
		else {
			// duplicate value, we do not insert into tree
		}
        return root;
	} // end of insert()
	
	
	/**
	 * Compute the balance factor.
	 * 
	 * @param node Node we like to compute balance factor for.
	 * @return Balance factor of node.
	 */
	public int balanceFactor(Node node) {
		return height(node.mLeftChild) - height(node.mRightChild);
	} // end of balanceFactor


    /**
     * Perform right rotation between root and its leftChild.
     * 
     * @param root Root of tree to perform rotations on.
     * @return new root of rotation tree.
     */
    protected Node rightRotation (Node root)
    {
    	Node child = root.mLeftChild;
	root.mRightChild = child.mLeftChild;
	child.mLeftChild = root;

    	// REPLACE, placeholder
        return child;
    } // end of rightRotation()

    
    /**
     * Perform left rotation between root and its right child.
     * 
     * @param root Root of tree to perform rotation on.
     * @return new root of rotation tree.
     */
    protected Node leftRotation (Node root)
    {
        Node child = root.mRightChild;
        root.mRightChild = child.mLeftChild;
        child.mLeftChild = root;

        return child;
    } // end of leftRotation()

    
    /**
     * Perform left-right rotation.
     * 
     * @param root Root of tree to perform rotations on.
     * @return new root of rotation tree.
     */
    protected Node leftRightRotation (Node root)
    {
	root.mLeftChild = rightRotation(root.mRightChild);
    	// REPLACE, placeholder
        return rightRotation(root);
    } // end of leftRightRotation()


    /**
     * Perform right-left rotation.
     * 
     * @param root Root of tree to perform rotations on.
     * @return new root of rotation tree.
     */
    protected Node rightLeftRotation (Node root)
    {
        root.mRightChild = rightRotation(root.mRightChild);

        return leftRotation(root);
    } // end of rightLeftRotation()


} // end of class AVLTree
