package com.edwin.spring.web.structure;


/**
 * 平衡二叉树
 *
 * @author caojunming
 * @data 2017年11月13日 下午3:16:23
 */
public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    public AVLTree(T data) {
        root = new AVLNode<>(data);
    }

    public void insert(T data) {
        if (data == null) {
            throw new RuntimeException("data can\'t not be null ");
        }
        this.root = insert(data, root);
    }

    private AVLNode<T> insert(T data, AVLNode<T> p) {

        // 说明已没有孩子结点,可以创建新结点插入了.
        if (p == null) {
            p = new AVLNode<T>(data);
        } else if (data.compareTo(p.data) < 0) {// 向左子树寻找插入位置
            p.left = insert(data, p.left);

            // 插入后计算子树的高度,等于2则需要重新恢复平衡,由于是左边插入,左子树的高度肯定大于等于右子树的高度
            if (height(p.left) - height(p.right) > 1) {
                // 判断data是插入点的左孩子还是右孩子
                if (data.compareTo(p.left.data) < 0) {
                    // 进行LL旋转
                    p = singleRotateLeft(p);
                } else {
                    // 进行左右旋转
                    p = doubleRotateWithLeft(p);
                }
            }
        } else if (data.compareTo(p.data) > 0) {// 向右子树寻找插入位置
            p.right = insert(data, p.right);

            if (height(p.right) - height(p.left) > 1) {
                if (data.compareTo(p.right.data) < 0) {
                    // 进行右左旋转
                    p = doubleRotateWithRight(p);
                } else {
                    p = singleRotateRight(p);
                }
            }
        } else {
            // if exist do nothing
        }
        // 重新计算各个结点的高度
        p.height = Math.max(height(p.left), height(p.right)) + 1;

        return p;// 返回根结点
    }

    /**
     *
     * @param x
     * @return
     */
    public AVLNode<T> singleRotateLeft(AVLNode<T> x) {
        // 把w结点旋转为根结点
        AVLNode<T> w = x.left;
        // 同时w的右子树变为x的左子树
        x.left = w.right;
        // x变为w的右子树
        w.right = x;
        // 重新计算x/w的高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        w.height = Math.max(height(w.left), x.height) + 1;
        return w;// 返回新的根结点
    }

    /**
     * 右右单旋转(RR旋转) x变为w的根结点, w变为x的左子树
     *
     * @param w
     * @return
     */
    public AVLNode<T> singleRotateRight(AVLNode<T> w) {

        AVLNode<T> x = w.right;

        w.right = x.left;
        x.left = w;

        // 重新计算x/w的高度
        w.height = Math.max(height(w.left), height(w.right)) + 1;
        x.height = Math.max(height(x.left), w.height) + 1;

        // 返回新的根结点
        return x;
    }

    /**
     * 左右旋转(LR旋转) x(根) w y 结点 把y变成根结点
     *
     * @param x
     * @return
     */
    public AVLNode<T> doubleRotateWithLeft(AVLNode<T> x) {
        // w先进行RR旋转
        x.left = singleRotateRight(x.left);
        // 再进行x的LL旋转
        return singleRotateLeft(x);
    }

    /**
     * 右左旋转(RL旋转)
     *
     * @param x
     * @return
     */
    public AVLNode<T> doubleRotateWithRight(AVLNode<T> x) {
        // 先进行LL旋转
        x.right = singleRotateLeft(x.right);
        // 再进行RR旋转
        return singleRotateRight(x);
    }

    public int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public static void main(String[] args) {
        AVLTree<NodeData> avlTree = new AVLTree<>(new NodeData(2));
        avlTree.insert(new NodeData(1));
        avlTree.insert(new NodeData(5));
        avlTree.insert(new NodeData(4));
        avlTree.insert(new NodeData(9));
        avlTree.insert(new NodeData(3));
        System.out.println(avlTree);
    }
}


class AVLNode<T extends Comparable<T>> {
    public AVLNode<T> left;
    public AVLNode<T> right;
    public T data;
    public int height;

    public AVLNode(T data) {
        this(null, null, data);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
        this(left, right, data, 0);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
        this.left = left;
        this.right = right;
        this.data = data;
        this.height = 0;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "[left=" + (left == null ? "" : left.toString()) + ", right="
                + (right == null ? "" : right.toString()) + ", data="
                + (data == null ? "" : data.toString()) + ", height=" + height + "]";
    }
}


class NodeData implements Comparable<NodeData> {
    private int value;

    public NodeData(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(NodeData o) {
        if (o == null)
            return -1;
        return this.value - o.value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[value=" + value + "]";
    }
}