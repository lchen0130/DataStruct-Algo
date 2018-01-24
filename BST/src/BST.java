

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, please "
            + "input a valid data value");
        }
        for (T value : data) {
            add(value);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null, please enter "
            + "valid data entry that's not null");
        } else {
            root = addHelper(data, root);
        }
    }

    /**
     * helper method for add method
     * @param data to be added
     * @param current node that method is looking at
     * @return node that is added
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> current) {
        if (current == null) {
            size++;
            return new BSTNode<>(data);
        }
        int check = data.compareTo(current.getData());
        if (check < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
        } else if (check > 0) {
            current.setRight(addHelper(data, current.getRight()));
        }
        return current;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null, please "
            + "enter valid data that isn't null");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeHelper(data, root, dummy);
        size--;
        return dummy.getData();


    }

    /**
     * helper method that works with remove
     * @param data the data to be removed
     * @param current node that is looked at
     * @param dummy node that passes info currently set to null
     * @return node that is removed
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> current,
                                    BSTNode<T> dummy) {
        if (current == null) {
            throw new java.util.NoSuchElementException("Element was not "
                    + "found");
        }
        int check = data.compareTo(current.getData());

        if (check > 0) {
            current.setRight(removeHelper(data, current.getRight(), dummy));
        } else if (check < 0) {
            current.setLeft(removeHelper(data, current.getLeft(), dummy));
        } else { // data is equal here
            dummy.setData(current.getData());
            if (current.getRight() != null && current.getLeft() != null) {
                // two children implementation
                BSTNode<T> onceLeft = current.getLeft();
                if (onceLeft.getRight() == null) {
                    current.setData(onceLeft.getData());
                    current.setLeft(onceLeft.getLeft());
                } else {
                    while (onceLeft.getRight().getRight() != null) {
                        onceLeft = onceLeft.getRight();
                    }
                    current.setData(onceLeft.getRight().getData());
                    onceLeft.setRight(onceLeft.getRight().getLeft());
                }
            } else if (current.getRight() != null) { //left null right ok
                return current.getRight();

            } else if (current.getLeft() != null) {
                return current.getLeft();
                
            } else {
                return null; // this is no children case
            }
        }
        return current;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null, please "
                    + "enter valid data that isn't null");
        }
        return getHelper(data, root);

    }

    /**
     * helper method for the get function
     * @param data that is getting
     * @param current node that is under consideration
     * @return data that is get
     */
    private T getHelper(T data, BSTNode<T> current) {
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
            return current.getData();
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
    private boolean containsHelper(T data, BSTNode<T> current) {
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
        // DO NOT MODIFY THIS METHOD
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
    private List<T> preorderHelper(ArrayList myArr, BSTNode current) {
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
    private List<T> postorderHelper(ArrayList myArr, BSTNode current) {
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
    private void inorderHelper(ArrayList myArr, BSTNode current) {
        if (current != null) {
            inorderHelper(myArr, current.getLeft());
            myArr.add(current.getData());
            inorderHelper(myArr, current.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        List<BSTNode<T>> queue = new LinkedList<>();
        List<T> container = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> current = queue.remove(0);
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
    private List<T> listLeavesHelper(BSTNode<T> current, List<T> list) {
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
        if (root == null) {
            return -1;
        }
        return heightHelper(root);
        
    }

    /**
     * helper method for height
     * @param current node under consideration
     * @return int that is height
     */
    private int heightHelper(BSTNode current) {

        if (current == null) {
            return -1;
        }
        return Math.max(heightHelper(current.getLeft()),
                heightHelper(current.getRight())) + 1;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
