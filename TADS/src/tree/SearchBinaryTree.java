package tree;


import LinkedList.LinkedList;
import LinkedList.MyList;
import exceptions.ColaVaciaException;
import queue.MyQueue;
import queue.Queue;

public class SearchBinaryTree<Key extends Comparable<Key>, Value> implements MySearchBinaryTree<Key,Value> {

    private TreeNode<Key, Value> root;


    public SearchBinaryTree() {
        this.root = null;
    }

    @Override
    public void insert(Key key, Value value) {
        this.root = insert(key, value, this.root);
    }

    private TreeNode<Key, Value> insert(Key key, Value value, TreeNode<Key, Value> subTree) {

        if (subTree == null) {

            TreeNode<Key, Value> newNode = new TreeNode(key, value);
            subTree = newNode;

        } else {

            if (key.compareTo(subTree.getKey()) > 0) {

                subTree.setRight(insert(key,value, subTree.getRight()));

            } else if (key.compareTo(subTree.getKey()) < 0)  {

                subTree.setLeft(insert(key,value, subTree.getLeft()));

            } else {

                // El elemento ya esta insertado no se hace nada.

            }

        }

        return subTree;
    }


    @Override
    public boolean contains(Key key) {
        return contains(key, this.root);
    }

    private boolean contains(Key k, TreeNode<Key, Value> subTree) {
        boolean result = false;

        if (subTree != null) {

            if (k.compareTo(subTree.getKey()) == 0) {

                result = true;

            } else if (k.compareTo(subTree.getKey()) > 0) {

                result = contains(k, subTree.getRight());

            } else {

                result = contains(k, subTree.getLeft());
            }

        }

        return result;
    }

    @Override
    public void remove(Key k) {
        this.root = deleteNodo(k,  this.root);
    }

    private TreeNode<Key, Value> deleteNodo(Key p, TreeNode<Key, Value> subTree){
        if(subTree == null) return null;

        if (p.compareTo(subTree.getKey())<0){
            subTree.setLeft(deleteNodo(p, subTree.getLeft()));
        }else if(p.compareTo(subTree.getKey())>0){
            subTree.setRight(deleteNodo(p, subTree.getRight()));
        }else{
            if(subTree.getLeft() == null && subTree.getRight() == null){
                return null;
            }else if(subTree.getLeft() == null){
                return subTree.getRight();
            }else if(subTree.getRight() == null){
                return subTree.getLeft();
            }else{
                //node with two nodes
                //search for max value in left sub tree
                TreeNode<Key, Value> max = maxTreeNode(subTree.getLeft());
                subTree.setKey(max.getKey());
                subTree.setValue(max.getValue());
                subTree.setLeft(deleteNodo(max.getKey(), subTree.getLeft()));
            }
        }
        return subTree;
    }

    private TreeNode<Key, Value> maxTreeNode(TreeNode<Key, Value> subtree){
        if(subtree.getRight() != null){
            return maxTreeNode(subtree.getRight());
        }
        return  subtree;
    }


    public MyList<Key> in_Order() {
        MyList<Key> inOrder = new LinkedList<Key>();
        doInOrder(this.root, inOrder);
        return inOrder;

    }

    private void doInOrder(TreeNode<Key, Value> subtree, MyList<Key> inOrder){
        if (subtree == null) return;
        doInOrder(subtree.getLeft(), inOrder);
        inOrder.add(subtree.getKey());
        doInOrder(subtree.getRight(), inOrder);
    }


    public MyList<Key> post_Order() {
        MyList<Key> postOrder = new LinkedList<Key>();
        doPostOrder(this.root, postOrder);
        return postOrder;
    }

    private void doPostOrder(TreeNode<Key, Value> subtree, MyList<Key> postOrder){
        if (subtree == null) return;
        doPostOrder(subtree.getLeft(), postOrder);
        doPostOrder(subtree.getRight(), postOrder);
        postOrder.add(subtree.getKey());
    }


    public MyList<Key> pre_Order() {
        MyList<Key> preOrder = new LinkedList<Key>();
        doPreOrder(this.root, preOrder);
        return preOrder;
    }
    private void doPreOrder(TreeNode<Key, Value> subtree, MyList<Key> preOrder){
        if(subtree == null) return;
        preOrder.add(subtree.getKey());
        doPreOrder(subtree.getLeft(), preOrder);
        doPreOrder(subtree.getRight(), preOrder);

    }

    @Override
    public Value find(Key key) {
        return find(key, this.root);
    }

    private Value find(Key k, TreeNode<Key, Value> subTree){
        Value result = null;

        if (subTree != null) {

            if (k.compareTo(subTree.getKey()) == 0) {

                result = subTree.getValue();

            } else if (k.compareTo(subTree.getKey()) > 0) {

                result = find(k, subTree.getRight());

            } else {

                result = find(k, subTree.getLeft());
            }

        }

        return result;
    }

    @Override
    public MyList<Key> level_Order() {
        MyList<Key> levelOrder = new LinkedList<Key>();
        MyQueue<TreeNode<Key, Value>> discoverNode = new Queue<>() ;
        if(this.root == null){
            return null;
        }discoverNode.enqueue(this.root);
        while(!discoverNode.isEmpty()){
            try {
                TreeNode<Key, Value> tmpNode = discoverNode.dequeue();
                if(tmpNode.getLeft() != null){
                    discoverNode.enqueue(tmpNode.getLeft());
                }if(tmpNode.getRight() != null){
                    discoverNode.enqueue(tmpNode.getRight());
                } levelOrder.add(tmpNode.getKey());
            } catch (ColaVaciaException e) {
                e.printStackTrace();
            }

        }

        return levelOrder;
    }


}
