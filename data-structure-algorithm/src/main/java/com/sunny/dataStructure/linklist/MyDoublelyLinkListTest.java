package com.sunny.dataStructure.linklist;

/**
 * @author JiaChang
 * @date 2019/1/23
 */
public class MyDoublelyLinkListTest {


    public static void displayNodeInfo(MyDoubleNode node){
        if(node != null){
            node.displayInfo();
        }else{
            System.out.println("node not exist");
        }
    }

    public static void main(String[] args) {


        MyDoublelyLinkList myDoublelyLinkList = new MyDoublelyLinkList();

        myDoublelyLinkList.inserFirst(new MyDoubleNode("node1","value1"));

        myDoublelyLinkList.inserFirst(new MyDoubleNode("node3","value3"));

        myDoublelyLinkList.insertEnd(new MyDoubleNode("node4","value4"));

        myDoublelyLinkList.insertEnd(new MyDoubleNode("node7","value7"));

        myDoublelyLinkList.insertAfterOneNode(new MyDoubleNode("node1","value1"),new MyDoubleNode("node5","value5"));

        myDoublelyLinkList.displayInfo();

        System.out.println("\n---查询节点开始---");
        MyDoubleNode queryNode = myDoublelyLinkList.findNode(new MyDoubleNode("node3","value3"));
        displayNodeInfo(queryNode);

        MyDoubleNode queryNode1 = myDoublelyLinkList.findNode(new MyDoubleNode("node33","value3"));

        displayNodeInfo(queryNode1);

        System.out.println("---查询节点结束---\n");

        System.out.println("\n---删除首尾节点开始---");
        MyDoubleNode firstEndNode = myDoublelyLinkList.deleteEnd();
        displayNodeInfo(firstEndNode);

        MyDoubleNode firstNode = myDoublelyLinkList.deleteFirst();
        displayNodeInfo(firstNode);

        System.out.println("---删除首尾节点结束---\n");

        myDoublelyLinkList.displayInfo();

    }

}
