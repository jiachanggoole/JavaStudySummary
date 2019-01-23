package com.sunny.dataStructure.linklist;

/**
 * 单向链表
 * 只要维护first节点的信息即可
 * @author JiaChang
 * @date 2019/1/22
 */
public class MyLinkList {

    /**
     * 首节点
     */
    private MyNode first;

    public MyLinkList() {
        first = null;
    }


    /**
     * 插入节点
     *
     * @param key
     * @param value
     */
    public void insertNode(String key, String value) {

        // 新插入的节点封装
        MyNode insertNode = new MyNode(key, value);

        // 新插入的节点放在首节点前面
        insertNode.setNextNode(first);
        // 新掺入的节点就是首节点
        first = insertNode;

    }

    /**
     * 删除第一个节点
     *
     * @return 第一个节点信息
     */
    public MyNode deleteFirstNode() {
        if (!linkListEmpty()) {
            // 先把第一个节点备份下返回
            MyNode temp = first;
            MyNode secondNode = first.getNextNode();
            first = secondNode;
            return temp;
        } else {
            System.out.println("链表为空，deleteFirstNode 失败...");
        }
        return null;
    }


    /**
     * 显示链表所有节点信息
     */
    public void displayLinkListInfo() {
        MyNode current = first;

        while (current != null) {
            current.displayInfo();
            current = current.getNextNode();
        }

    }

    /**
     * 删除特定节点
     *
     * @param key
     * @param value
     * @return 删除的节点信息
     */
    public MyNode deleteNode(String key, String value) {

        MyNode current = first;
        MyNode previous = null;

        while (current != null) {

            if (current.dataEquals(key, value)) {

                // 删除的节点的上一个节点的next指向删除节点的next
                previous.setNextNode(current.getNextNode());

                return current;
            }

            previous = current;
            current = current.getNextNode();

        }
        return null;
    }

    /**
     * 查询特定节点
     *
     * @param key
     * @param value
     * @return
     */
    public MyNode findNode(String key, String value) {
        MyNode current = first;
        while (current != null) {

            if (current.dataEquals(key, value)) {
                return current;
            }

            current = current.getNextNode();

        }
        return null;
    }

    /**
     * 链表是否为空
     *
     * @return
     */
    public boolean linkListEmpty() {
        return first == null;
    }

}
