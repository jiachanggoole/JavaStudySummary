package com.sunny.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author JiaChang
 * @date 2019/1/30
 */
public class MyBinaryTree {

    private MyTreeNode root;

    public MyBinaryTree(MyTreeNode root) {
        this.root = root;
    }


    public MyBinaryTree() {
    }

    /**
     * 插入节点
     * @param insertNode 插入的数据
     */
    public boolean insert(MyTreeNode insertNode){
        if(isEmpty()){
            root = insertNode;
            return true;
        }

        MyTreeNode current = root;

        while (true){

            // 沿着左节点走
            if(insertNode.getValue() < current.getValue()){
                if(current.getLeftChild() != null){
                    current = current.getLeftChild();
                }else{
                    current.setLeftChild(insertNode);
                    return true;
                }
            }
            // 沿着右节点走
            else if(insertNode.getValue() > current.getValue()){
                if(current.getRightChild() != null){
                    current = current.getRightChild();
                }else{
                    current.setRightChild(insertNode);
                    return true;
                }
            }else {
                System.out.println("insert node fail , 重复插入：" + insertNode.getValue());
                return false;
            }
        }
    }

    /**
     * 查询数据
     * @param queryValue 要查询的值
     * @return 节点信息
     */
    public MyTreeNode findData(Integer queryValue){

        MyTreeNode current = root;

        while (current != null){

            if(Objects.equals(queryValue,current.getValue())){
                return current;
            }
            // 走左节点
            if(queryValue < current.getValue()){
                current = current.getLeftChild();
            }
            // 走右节点
            else if(queryValue > current.getValue()){
                current = current.getRightChild();
            }
        }

        return null;
    }

    /**
     * 查找最大值
     * @return 最大值节点信息
     */
    public MyTreeNode queryMaxValue(){

        MyTreeNode current = root;

        while (current != null){

            if(current.getRightChild() != null){
                current = current.getRightChild();
            }else{
                return current;
            }
        }

        return null;
    }

    /**
     * 查找最小值
     * @return 最小值节点信息
     */
    public MyTreeNode queryMinValue(){

        MyTreeNode current = root;

        while (current != null){

            if(current.getLeftChild() != null){
                current = current.getLeftChild();
            }else{
                return current;
            }
        }

        return null;
    }

    /**
     * 遍历树的节点信息
     * @param type 1:前序遍历 2:中序遍历 3:后序遍历
     */
    public void traversingTreeInfo(int type){
        switch (type){

            case 1:

                System.out.println(" **** 前序遍历 start **** ");
                beforeTraversing(root);
                System.out.println("\n **** 前序遍历 end **** \n");
                break;

            case 2:
                System.out.println(" **** 中序遍历 start **** ");
                centerTraversing(root);
                System.out.println("\n **** 中序遍历 end **** \n");
                break;

            case 3:
                System.out.println(" **** 后序遍历 start **** ");
                afterTraversing(root);
                System.out.println("\n **** 后序遍历 end **** \n");
                break;
        }


    }


    public void beforeTraversing(MyTreeNode current){
        if(current != null){
            System.out.print(current.getValue() + "\t");
            beforeTraversing(current.getLeftChild());
            beforeTraversing(current.getRightChild());
        }
    }


    public void centerTraversing(MyTreeNode current){
        if(current != null){
            centerTraversing(current.getLeftChild());
            System.out.print(current.getValue() + "\t");
            centerTraversing(current.getRightChild());
        }
    }

    public void afterTraversing(MyTreeNode current){
        if(current != null){
            afterTraversing(current.getLeftChild());
            afterTraversing(current.getRightChild());
            System.out.print(current.getValue() + "\t");
        }
    }


    public void deleteNode(Integer deleteValue){

        MyTreeNode current = root;
        MyTreeNode parent = null;
        // 记录要删除的节点是否是其父节点的左子节点
        boolean isLeftChild = true;

        while (current != null){

            if(Objects.equals(deleteValue,current.getValue())){
                break;
            }
            // 走左节点
            if(deleteValue < current.getValue()){
                parent = current;
                current = current.getLeftChild();
                isLeftChild = true;
            }
            // 走右节点
            else if(deleteValue > current.getValue()){
                parent = current;
                current = current.getRightChild();
                isLeftChild = false;
            }
        }

        // 删除的节点信息找不到
        if(current == null){
            System.out.println("delete node fail , node not exist :" + deleteValue);
            return;
        }

        // 1. 删除的节点没有子节点
        if(!current.isHaveChild()){

            // 删除的就是根节点
            if(parent == null){
                root = null;
                return;
            }
            // 删除的是左子节点
            if(isLeftChild){
                parent.setLeftChild(null);
            }
            // 删除的是右子节点
            else{
                parent.setRightChild(null);
            }

            return;
        }

        // 2. 删除的节点有一个子节点--左子节点
        if(current.getLeftChild() != null && current.getRightChild() == null){

            // 删除根节点，左子节点直接提拔为根节点
            if(parent == null){
                root = current.getLeftChild();
                return;
            }

            if(isLeftChild){
                parent.setLeftChild(current.getLeftChild());
            }else{
                parent.setRightChild(current.getLeftChild());
            }
        }

        // 2. 删除的节点有一个子节点--右子节点
        if(current.getLeftChild() == null && current.getRightChild() != null){
            // 删除根节点，左子节点直接提拔为根节点
            if(parent == null){
                root = current.getRightChild();
                return;
            }

            if(isLeftChild){
                parent.setLeftChild(current.getRightChild());
            }else {
                parent.setRightChild(current.getRightChild());
            }
            return;
        }

        // 3. 删除的节点有两个个子节点(主要是右子节点树直接提上去会有问题的)

        // 先找到后继节点
        MyTreeNode afterContinue = current.getRightChild();
        MyTreeNode afterContinueParent = current;

        while (afterContinue.getLeftChild() != null){
            afterContinueParent = afterContinue;
            afterContinue = afterContinue.getLeftChild();
        }

        // 3.1 删除节点的右子节点r没有左子节点,后继节点就是r，直接上移
        if(afterContinueParent == current){
            if(parent == null){
                root = afterContinue;
                root.setLeftChild(current.getLeftChild());
            }
            else if(isLeftChild){
                afterContinue.setLeftChild(current.getLeftChild());
                parent.setLeftChild(afterContinue);
            }else {
                afterContinue.setLeftChild(current.getLeftChild());
                parent.setRightChild(afterContinue);
            }
            return;
        }

        // 3.2 删除节点的右子节点r有左子节点,后继节点没有右节点
        // 3.3 后继节点有右节点 (后继节点放到删除节点的位置，后继节点的右节点数放到后继节点的父节点左子节点)
        afterContinueParent.setLeftChild(afterContinue.getRightChild());
        afterContinue.setRightChild(current.getRightChild());
        if(current == root){
            root = afterContinue;
            root.setLeftChild(current.getLeftChild());
        }else if(isLeftChild){
            parent.setLeftChild(afterContinue);
            afterContinue.setLeftChild(current.getLeftChild());
        }else{
            parent.setRightChild(afterContinue);
            afterContinue.setLeftChild(current.getLeftChild());
        }
    }



    //以树的形式打印出该树
    public void displayTree(){
        int depth = getTreeDepth();
        ArrayList<MyTreeNode> currentLayerNodes = new ArrayList<MyTreeNode> ();
        currentLayerNodes.add(root);  //存储该层所有节点
        int layerIndex = 1;
        while(layerIndex <= depth){
            int NodeBlankNum = (int)Math.pow(2, depth-layerIndex)-1;  //在节点之前和之后应该打印几个空位
            for(int i = 0;i<currentLayerNodes.size();i++){
                MyTreeNode node = currentLayerNodes.get(i);
                printBlank(NodeBlankNum);   //打印节点之前的空位

                if(node == null){
                    System.out.print("*\t");  //如果该节点为null，用空位代替
                }else{
                    System.out.print("*  "+node.getValue()+"\t");  //打印该节点
                }

                printBlank(NodeBlankNum);  //打印节点之后的空位
                System.out.print("*\t");   //补齐空位
            }
            System.out.println();
            layerIndex++;
            currentLayerNodes = getAllNodeOfThisLayer(currentLayerNodes);  //获取下一层所有的节点
        }
    }

    //获取指定节点集合的所有子节点
    private ArrayList getAllNodeOfThisLayer(List parentNodes){
        ArrayList list = new ArrayList<MyTreeNode>();
        MyTreeNode parentNode;
        for(int i=0;i<parentNodes.size();i++){
            parentNode = (MyTreeNode)parentNodes.get(i);
            if(parentNode != null){
                if(parentNode.getLeftChild() != null){  //如果上层的父节点存在左子节点，加入集合
                    list.add(parentNode.getLeftChild());
                }else{
                    list.add(null);  //如果上层的父节点不存在左子节点，用null代替，一样加入集合
                }
                if(parentNode.getRightChild() != null){
                    list.add(parentNode.getRightChild());
                }else{
                    list.add(null);
                }
            }else{  //如果上层父节点不存在，用两个null占位，代表左右子节点
                list.add(null);
                list.add(null);
            }
        }
        return list;
    }

    //打印指定个数的空位
    private void printBlank(int num){
        for(int i=0;i<num;i++){
            System.out.print("*\t");
        }
    }

    //私有方法，用迭代方法来获取左子树和右子树的最大深度，返回两者最大值
    private int getDepth(MyTreeNode currentNode,int initDeep){
        int deep = initDeep;  //当前节点已到达的深度
        int leftDeep = initDeep;
        int rightDeep = initDeep;
        if(currentNode.getLeftChild() != null){  //计算当前节点左子树的最大深度
            leftDeep = getDepth(currentNode.getLeftChild(), deep+1);
        }
        if(currentNode.getRightChild() != null){  //计算当前节点右子树的最大深度
            rightDeep = getDepth(currentNode.getRightChild(), deep+1);
        }

        return Math.max(leftDeep, rightDeep);
    }

    //获取树的深度
    public int getTreeDepth(){
        if(root == null){
            return 0;
        }
        return getDepth(root,1);
    }

    public boolean isEmpty(){
        return root == null;
    }


    public static void main(String[] args) {


        MyBinaryTree myBinaryTree = new MyBinaryTree();

        System.out.println(myBinaryTree.insert(new MyTreeNode(21,"21")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(19,"19")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(50,"50")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(30,"30")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(17,"17")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(20,"20")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(60,"60")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(40,"40")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(16,"16")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(70,"70")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(55,"55")));
        System.out.println(myBinaryTree.insert(new MyTreeNode(56,"56")));

        System.out.println(" **** findData start **** ");
        myBinaryTree.findData(20).displayInfo();
        System.out.println(myBinaryTree.findData(80) == null);
        System.out.println(" **** findData end **** \n");


        System.out.println(" **** queryMaxValue start **** ");
        myBinaryTree.queryMaxValue().displayInfo();
        System.out.println(" **** queryMaxValue end **** \n");

        System.out.println(" **** queryMinValue start **** ");
        myBinaryTree.queryMinValue().displayInfo();
        System.out.println(" **** queryMinValue end **** \n");

        myBinaryTree.traversingTreeInfo(1);
        myBinaryTree.traversingTreeInfo(2);
        myBinaryTree.traversingTreeInfo(3);


//        myBinaryTree.deleteNode(40); // 删除的节点无子节点
//        myBinaryTree.deleteNode(30); // 删除的节点有一个右子节点
//        myBinaryTree.deleteNode(16); // 删除的节点有一个左子节点
//        myBinaryTree.deleteNode(50); // 删除的节点的右子节点是后继者(上面测试数据 55 56 注释掉)
//        myBinaryTree.deleteNode(50); // 删除的节点的右子节点有左子节点 ，后继者无右子节点(上面测试数据 55打开 56注释)
        myBinaryTree.deleteNode(50); // 删除的节点的右子节点有左子节点 ，后继者有右子节点(上面测试数据 55 56 打开)

        myBinaryTree.displayTree();
    }

}
