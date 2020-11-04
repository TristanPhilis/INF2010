import javafx.scene.layout.BorderRepeat;
import org.w3c.dom.Node;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;

    public AvlTree() { }


    private void updateHeights(BinaryNode<ValueType> start){
        if (start != null) {
            BinaryNode<ValueType> currentNode = start;
            while(currentNode != null) {
                if (currentNode.left == null) {
                    if (currentNode.right == null) {
                        currentNode.height = 0;
                    } else {
                        currentNode.height = currentNode.right.height + 1;
                    }
                } else if (currentNode.right == null){
                    currentNode.height = currentNode.left.height + 1;
                } else {
                    currentNode.height = Math.max(currentNode.left.height, currentNode.right.height) + 1;
                }
                currentNode = currentNode.parent;
            }
        }
    }


    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     */
    public void add(ValueType value) {
        if (root != null){
            BinaryNode<ValueType> currentNode = root;
            BinaryNode<ValueType> addedNode = null;
            while (currentNode.value.compareTo(value) != 0){
                if (value.compareTo(currentNode.value) < 0){
                    if (currentNode.left == null){
                        addedNode = new BinaryNode<ValueType>(value, currentNode);
                        currentNode.left = addedNode;
                    }
                    currentNode = currentNode.left;
                } else {
                    if (currentNode.right == null){
                        addedNode = new BinaryNode<ValueType>(value, currentNode);
                        currentNode.right = addedNode;
                    }
                    currentNode = currentNode.right;
                }
            }
            updateHeights(addedNode);
            balance(addedNode);
        } else {
            root = new BinaryNode<>(value, null);
            root.height = 0;
        }
    }


    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value){
        if (root != null){
            BinaryNode<ValueType> nodeToRemove = root;
            while (nodeToRemove != null && nodeToRemove.value.compareTo(value) != 0){
                if (value.compareTo(nodeToRemove.value) < 0){
                    nodeToRemove = nodeToRemove.left;
                } else {
                    nodeToRemove = nodeToRemove.right;
                }
            }

            if (nodeToRemove != null) {//found the node
                BinaryNode<ValueType> parentNode;
                BinaryNode<ValueType> currentNode;
                BinaryNode<ValueType> updateNode;
                if (nodeToRemove.right == null) {
                    if (nodeToRemove.left != null) {        //only left branch exists
                        parentNode = nodeToRemove.left;
                    } else {                                //no child branch
                        parentNode = null;
                    }
                    updateNode = nodeToRemove.parent;
                } else if (nodeToRemove.left == null) {     //only the right branch exists
                    parentNode = nodeToRemove.right;
                    updateNode = nodeToRemove.parent;
                } else {                                    //Both branches exist
                    currentNode = nodeToRemove.right;
                    while (currentNode.left != null) {//finds the leftmost node
                        currentNode = currentNode.left;
                    }
                    parentNode = currentNode;
                    parentNode.parent.left = parentNode.right;
                    if (parentNode.right != null) {
                        parentNode.right.parent = parentNode.parent;
                        updateNode = parentNode.right;
                    } else {
                        updateNode = currentNode.parent;
                    }
                    parentNode.right = nodeToRemove.right;
                    nodeToRemove.right.parent = parentNode;
                    parentNode.left = nodeToRemove.left;
                    nodeToRemove.left.parent = parentNode;
                    parentNode.height = nodeToRemove.height;
                }


                if (nodeToRemove == root) {
                    if(parentNode != null) {parentNode.parent = null;}
                    root = parentNode;
                } else {
                    if (parentNode != null) {parentNode.parent = nodeToRemove.parent;}
                    if (nodeToRemove.parent.right == nodeToRemove) {
                        nodeToRemove.parent.right = parentNode;
                    } else {
                        nodeToRemove.parent.left = parentNode;
                    }
                    nodeToRemove.parent = null;
                }
                nodeToRemove.right = null;
                nodeToRemove.left = null;
                if (updateNode != null) {updateHeights(updateNode);}
            }
        }
    }


    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        if (root == null) {return false;}
        BinaryNode<ValueType> currentNode = root;
        while(currentNode != null && currentNode.value.compareTo(value) != 0){
            if(value.compareTo(currentNode.value) < 0){
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return currentNode != null;
    }


    /** TODO Worst case : O( 1 )
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        if (root == null) {return -1;}
        else {
            return root.height;
        }
    }


    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        if (root != null) {
            BinaryNode<ValueType> currentNode = root;
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
            return currentNode.value;
        } else {
            return null;
        }
    }


    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {
        LinkedList<ValueType> orderedList = new LinkedList<>();
        BinaryNode<ValueType> current = root;
        while (current.left != null) {
            current = current.left;
        }
        orderedList.add(current.value);
        boolean doneMakingList = false;

        while (!doneMakingList) {
            if (current.right != null){                                             //has a right child
                current = current.right;
                while (current.left != null) {
                    current = current.left;
                }
            } else if (current.parent != null && current.parent.left == current) {  //going up to the parent from the left
                current = current.parent;
            } else {                                                                //going up to the parent from the right
                while(current.parent != null && current.parent.right == current && !doneMakingList) {
                    if (current.parent == root){
                        doneMakingList = true;
                    } else {
                        current = current.parent;
                    }
                }
                if (current.parent != null) {current = current.parent;}
            }
            if (!doneMakingList) {orderedList.add(current.value);}
        }

        return orderedList;
    }


    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        LinkedList<ValueType> orderedList = new LinkedList<>();
        LinkedList<BinaryNode<ValueType>> nodeList = new LinkedList<>();
        if (root != null) {
            BinaryNode<ValueType> nodeToAdd;
            //necessary add to start the algorithm
            nodeList.add(root);

            for (int i = 0; i < nodeList.size(); i++) {
                nodeToAdd = nodeList.get(i);
                if(nodeToAdd.left != null) {
                    nodeList.add(nodeToAdd.left);
                }
                if(nodeToAdd.right != null) {
                    nodeList.add(nodeToAdd.right);
                }
            }

            for (BinaryNode<ValueType> iter : nodeList){
                orderedList.add(iter.value);
            }
        }

        return orderedList;
    }


    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Balances the whole tree
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {
        BinaryNode<ValueType> current = node;
        int balancingFactor;
        while(current != null) {//stops when current reaches the root
            if (current.left == null) {
                balancingFactor = -1 * current.height;
            } else if (current.right == null) {
                balancingFactor = current.height;
            } else {
                balancingFactor = current.left.height - current.right.height;
            }

            if(balancingFactor >= 2) {
                rotateLeft(current);
                updateHeights(current);
            } else if (balancingFactor <= -2) {
                rotateRight(current);
                updateHeights(current);
            }
            current = current.parent;
        }
    }


    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){
        if(node1 == root){
            root = node1.left;
        }
        node1.left.right = node1;
        node1.left.parent = node1.parent;
        node1.parent = node1.left;
        node1.left = null;
    }


    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){
        
    }


    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node

        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }
}
