package com.edwin.spring.web.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 跳跃表实现
 *
 * @author caojunming
 * @datetime 2020/10/2 8:11 PM
 */
public class SkipList<E, C extends Comparable<? super C>> {
    //最大层级
    static final int MAX_LEVEL = 1 << 6;
    static final int DEFAULT_LEVEL = 4;
    //跳跃表 down node 的层级
    int level;
    //头节点
    Node<E, C> head;
    ThreadLocalRandom random = ThreadLocalRandom.current();

    public SkipList() {
        this(DEFAULT_LEVEL);
    }

    //跳跃表的初始化
    public SkipList(int level) {
        this.level = level;
        int i = level;
        Node<E, C> temp = null;
        Node<E, C> prev = null;
        //从底部节点开始创建链表
        while (i-- != 0) {
            temp = new Node(null, (T) -> -1);
            temp.down = prev;
            prev = temp;
        }
        head = temp;//头节点
    }


    public void put(E val, C score) {
        Node<E, C> t = head;
        //存储每个层级，插入新节点的最左边节点
        List<Node<E, C>> paths = new ArrayList<>();
        //1，找到需要插入的位置
        while (t != null) {
            //表示存在该值的点，表示需要更新该节点
            if (t.score.compareTo(score) == 0) {
                Entity e = t.entity;
                //插入到链表尾部
                e.next = new Entity(val, null);
                return;
            }

            //查找插入的位置
            if (t.next == null || t.next.score.compareTo(score) > 0) {
                paths.add(t);
                t = t.down;
            } else {
                t = t.next;
            }
        }
        //随机生成新节点的层级
        int lev = randomLevel();

        // 如果随机的层级数，大于head节点的层级数
        // 需要更新head这一列的节点数量,保证head列数量最多
        if (lev > level) {
            Node<E, C> temp = null;
            Node<E, C> prev = head;
            while (level++ != lev) {
                temp = new Node(null, (T) -> -1);
                paths.add(0, temp);
                temp.down = prev;
                prev = temp;
            }
            head = temp;
            //更新层级
            level = lev;
        }
        //从后向前遍历path中的每一个节点，在其后面增加一个新的节点
        Node<E, C> down = null, newNade = null, path = null;
        Entity<E> e = new Entity<>(val, null);
        for (int i = level - 1; i >= level - lev; i--) {
            //创建新的节点
            newNade = new Node<E, C>(e, score);
            path = paths.get(i);
            newNade.next = path.next;
            path.next = newNade;
            newNade.down = down;
            down = newNade;
        }
    }

    /**
     * 查找跳跃表中的一个值
     */
    public List<E> get(C score) {
        Node<E, C> t = head;
        while (t != null) {
            if (t.score.compareTo(score) == 0) {
                return t.entity.toList();
            }
            if (t.next == null || t.next.score.compareTo(score) > 0) {
                t = t.down;
            } else {
                t = t.next;
            }
        }
        return null;
    }

    /**
     * 查找跳跃表中指定范围的值
     */
    public List<E> find(final C begin, C and) {
        Node<E, C> t = head;
        Node<E, C> beginNode = null;
        //确定下界索引
        while (t != null) {
            beginNode = t;
            if (t.next == null || t.next.score.compareTo(begin) >= 0) {
                t = t.down;
            } else {
                t = t.next;
            }
        }

        //循环链表
        List<E> list = new ArrayList<>();
        for (Node n = beginNode.next; n != null; n = n.next) {
            if (n.entity != null && n.score.compareTo(and) < 0)
                list.addAll(n.entity.toList());
        }
        return list;
    }

    /**
     * 根据score的值来删除节点。
     */
    public void delete(C score) {
        //1,查找到节点列的第一个节点的前驱
        Node<E, C> t = head;
        while (t != null) {
            if (t.next == null || t.next.score.compareTo(score) > 0) {
                //向下查找
                t = t.down;
            } else if (t.next.score.compareTo(score) == 0) {
                // 在这里说明找到了该删除的节点
                t.next = t.next.next;
                //向下查找
                t = t.down;
            } else {
                //向右查找
                t = t.next;
            }
        }
    }

    /**
     * 高度随机增加一
     */
    private int randomLevel() {
        int lev = 1;
        //产生一个非负整数
        while (random.nextInt() % 2 == 0)
            lev++;
        return Math.min(lev, MAX_LEVEL);
    }

    /**
     * 跳跃表的节点链表
     */
    static class Node<E, C extends Comparable> {
        Entity<E> entity;//存储的数据
        C score; //需要排序的字段
        Node<E, C> next;//指向下一个节点的指针
        Node<E, C> down;//指向下一层的指针

        Node(Entity<E> entity, C score) {
            this.entity = entity;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Node{score=" + score + '}';
        }
    }

    static class Entity<E> {
        E val;
        Entity<E> next;

        Entity(E val, Entity<E> next) {
            this.val = val;
            this.next = next;
        }

        List<E> toList() {
            List<E> list = new ArrayList<>();
            list.add(val);
            for (Entity<E> e = next; e != null; e = e.next) {
                list.add(e.val);
            }
            return list;
        }

        @Override
        public String toString() {
            return "Entity{" + "val=" + val + '}';
        }
    }
}