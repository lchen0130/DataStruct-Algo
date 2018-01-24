import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Leo Chen
 * @userid 903218169
 * @GTID lchen396
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input is null "
                + "please enter valid data that isn't null");
        }
        for (T value : data) {
            add(value);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input is null "
                    + "please enter valid data that isn't null");
        }
        root = addHelper(data, root);

    }

    /**
     * add helper that recursively adds
     * @param data to be input
     * @param current node
     * @return node
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> current) {
        if (current == null) {
            size++;
            return new AVLNode<>(data);
        }
        int check = data.compareTo(current.getData());
        if (check > 0) {
            current.setRight(addHelper(data, current.getRight()));
        } else if (check < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
        }

        // update height and balance factor
        current = updateHeightandBalance(current);


        // check if unbalance and balance as needed
        if (current.getBalanceFactor() > 1) { // left heavy
            if (current.getLeft().getBalanceFactor() > 0) {
                // single rotation
                current = leftSingleRotation(current);
            } else {
                // double rotation
                current = leftDoubleRotation(current);
            }
        } else if (current.getBalanceFactor() < -1) { // right heavy
            if (current.getRight().getBalanceFactor() > 0) {
                // double rotation
                current = rightDoubleRotation(current);
            } else {
                // single rotation
                current = rightSingleRotation(current);
            }
        }
        return current;

    }

    /**
     * update height and balance factor
     * @param current node to look at
     * @return node
     */
    private AVLNode<T> updateHeightandBalance(AVLNode<T> current) {
        int leftKid = (current.getLeft() == null)
                ? -1 : current.getLeft().getHeight();
        int rightKid = (current.getRight() == null)
                ? -1 : current.getRight().getHeight();
        current.setBalanceFactor(leftKid - rightKid);
        current.setHeight(Math.max(leftKid, rightKid) + 1);

        return current;
    }

    /**
     * update current node and the two childrens
     * @param current node to update
     * @return node
     */
    private AVLNode<T> updateThree(AVLNode<T> current) {
        current.setRight(updateHeightandBalance(current.getRight()));
        current.setLeft(updateHeightandBalance(current.getLeft()));
        current = updateHeightandBalance(current);
        return current;
    }

    /**
     * double rotation towards right side
     * @param current node to rotate
     * @return avl node
     */
    private AVLNode<T> rightDoubleRotation(AVLNode<T> current) {
        AVLNode<T> tempRight = current.getRight();
        AVLNode<T> tempSmallData = tempRight.getLeft().getRight();
        current.setRight(current.getRight().getLeft());
        current.getRight().setRight(tempRight);
        current.getRight().getRight().setLeft(tempSmallData);
        current = rightSingleRotation(current);
        updateThree(current);
        return current;
    }

    /**
     * double right rotation
     * @param current node to rotate
     * @return avlnode updated
     */
    private AVLNode<T> leftDoubleRotation(AVLNode<T> current) {
        AVLNode<T> temp = current.getLeft();
        AVLNode<T> tempSmallData = temp.getRight().getLeft();
        current.setLeft(current.getLeft().getRight());
        current.getLeft().setLeft(temp);
        current.getLeft().getLeft().setRight(tempSmallData);
        current = leftSingleRotation(current);
        updateThree(current);
        return current;

    }

    /**
     * single right rotation
     * @param current node to update
     * @return updated node
     */
    private AVLNode<T> rightSingleRotation(AVLNode<T> current) {
        AVLNode<T> temp = current;
        current = current.getRight();
        temp.setRight(current.getLeft());
        current.setLeft(temp);
        updateThree(current);
        return current;
    }

    /**
     * single left rotation
     * @param current node to update
     * @return node updated
     */
    private AVLNode<T> leftSingleRotation(AVLNode<T> current) {
        AVLNode<T> temp = current;
        current = current.getLeft();
        temp.setLeft(current.getRight());
        current.setRight(temp);
        updateThree(current);
        return current;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("input data is null "
                    + "please enter valid data that isn't null");
        }
        if (!contains(data)) {
            throw new java.util.NoSuchElementException("data isn't in this "
                + "data structure, please try a different data to remove");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        root = removeHelper(data, root, dummy);
        size--;
        return dummy.getData();

    }

    /**
     * remove helper that helps the remove method
     * @param data to be inputed
     * @param current node that the method is looking at
     * @param dummy node that passes the return value back up
     * @return the avl node that
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> current,
                                    AVLNode<T> dummy) {
        if (current == null) {
            throw new java.util.NoSuchElementException("Element was not found "
                + "please try a different data");
        }
        int check = data.compareTo(current.getData());
        if (check > 0) {
            current.setRight(removeHelper(data, current.getRight(), dummy));
        } else if (check < 0) {
            current.setLeft(removeHelper(data, current.getLeft(), dummy));
        } else {
            // found the item here
            dummy.setData(current.getData());
            // replace current with the predecessor child

            // two children implementation
            if (current.getRight() != null && current.getLeft() != null) {
                AVLNode<T> predDummy = new AVLNode<>(null);
                current.setLeft(removePred(current.getLeft(), predDummy));
                current.setData(predDummy.getData());
            } else if (current.getRight() != null) {
                return current.getRight();
            } else {
                return current.getLeft();
            }

        }
        current = updateHeightandBalance(current);

        //balancing shit
        if (current.getBalanceFactor() > 1) { // left heavy
            if (current.getLeft().getBalanceFactor() > 0) {
                // single rotation
                current = leftSingleRotation(current);
            } else {
                // double rotation
                current = leftDoubleRotation(current);
            }
        } else if (current.getBalanceFactor() < -1) { // right heavy
            if (current.getRight().getBalanceFactor() > 0) {
                // double rotation
                current = rightDoubleRotation(current);
            } else {
                // single rotation
                current = rightSingleRotation(current);
            }
        }
        return current;
    }

    /**
     * remove previous nodes
     * @param current node looking at
     * @param dummy node to pass the value back
     * @return avl node that is passed back
     */
    private AVLNode<T> removePred(AVLNode<T> current, AVLNode<T> dummy) {
        if (current.getRight() == null) {
            dummy.setData(current.getData()); 
            return current.getLeft();
        }
        current.setRight(removePred(current.getRight(), dummy));

        // update height and balance factor
        current = updateHeightandBalance(current);


        // check if unbalance and balance as needed
        if (current.getBalanceFactor() > 1) { // left heavy
            if (current.getLeft().getBalanceFactor() > 0) {
                // single rotation
                current = leftSingleRotation(current);
            } else {
                // double rotation
                current = leftDoubleRotation(current);
            }
        } else if (current.getBalanceFactor() < -1) { // right heavy
            if (current.getRight().getBalanceFactor() > 0) {
                // double rotation
                current = rightDoubleRotation(current);
            } else {
                // single rotation
                current = rightSingleRotation(current);
            }
        }
        return current;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("input data is null "
                + "please enter valid data that isn't null");
        }
        return getHelper(data, root);
    }

    /**
     * helper method for get
     * @param data to get
     * @param current looking at
     * @return value of the node
     */
    private T getHelper(T data, AVLNode<T> current) {
        if (current == null) {
            throw new java.util.NoSuchElementException("Data was not found "
                    + "in the tree");
        }
        int compare = current.getData().compareTo(data);
        if (compare > 0) {
            return getHelper(data, current.getLeft());
        } else if (compare < 0) {
            return getHelper(data, current.getRight());
        } else {
            return (T) current.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null, please "
                    + "enter valid data that isn't null");
        }
        return containsHelper(data, root);

    }

    /**
     * helper method for contains
     * @param data that is checking for contains
     * @param current node that is under consideration
     * @return if it contains element data
     */
    private boolean containsHelper(T data, AVLNode<T> current) {
        if (current == null) {
            return false;
        }
        int compare = current.getData().compareTo(data);
        if (compare > 0) {
            return containsHelper(data, current.getLeft());
        } else if (compare < 0) {
            return containsHelper(data, current.getRight());
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }


    @Override
    public List<T> preorder() {
        ArrayList<T> myArr = new ArrayList<>(size);
        preorderHelper(myArr, root);
        return myArr;

    }

    /**
     * preorder helper method
     * @param myArr array to add things to
     * @param current node under consideration
     * @return list that is in preorder
     */
    private List<T> preorderHelper(ArrayList myArr, AVLNode<T> current) {
        if (current != null) {
            myArr.add(current.getData());
            preorderHelper(myArr, current.getLeft());
            preorderHelper(myArr, current.getRight());
        }
        return myArr;
    }

    @Override
    public List<T> postorder() {
        ArrayList<T> myArr = new ArrayList<>(size);
        postorderHelper(myArr, root);
        return myArr;
    }

    /**
     * helper method for postorder
     * @param myArr array to add things and return
     * @param current node under consideration
     * @return list that is in postorder
     */
    private List<T> postorderHelper(ArrayList myArr, AVLNode<T> current) {
        if (current != null) {
            postorderHelper(myArr, current.getLeft());
            postorderHelper(myArr, current.getRight());
            myArr.add(current.getData());
        }
        return myArr;
    }

    @Override
    public List<T> inorder() {
        ArrayList<T> myArr = new ArrayList<>(size);
        inorderHelper(myArr, root);
        return myArr;
    }

    /**
     * helper method for inorder
     * @param myArr array that things are added and returned
     * @param current node under consideration
     */
    private void inorderHelper(ArrayList myArr, AVLNode<T> current) {
        if (current != null) {
            inorderHelper(myArr, current.getLeft());
            myArr.add(current.getData());
            inorderHelper(myArr, current.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        List<AVLNode<T>> queue = new LinkedList<>();
        List<T> container = new ArrayList<T>();
        queue.add(root);
        while (!queue.isEmpty()) {
            AVLNode<T> current = queue.remove(0);
            if (current != null) {
                container.add(current.getData());
                queue.add(current.getLeft());
                queue.add(current.getRight());
            }
        }
        return container;
    }


    @Override
    public List<T> listLeavesDescending() {
        if (root == null) {
            return new ArrayList<T>(0);
        }
        List<T> list = new ArrayList<>();
        return listLeavesHelper(root, list);
    }

    /**
     * helper method for list leaves
     * @param current node under consideration
     * @param list that is added and returned
     * @return list that has the leaves
     */
    private List<T> listLeavesHelper(AVLNode<T> current, List<T> list) {
        if (current != null) {
            listLeavesHelper(current.getRight(), list);
            if (current.getLeft() == null && current.getRight() == null) {
                list.add(current.getData());

            }
            listLeavesHelper(current.getLeft(), list);
        }
        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
