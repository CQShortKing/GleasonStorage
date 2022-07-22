package week02;

import java.util.Iterator;
import java.util.LinkedList;
public class LinkListTest {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        //插入
        list.add("Jack");
        list.add("Mike");
        list.add("Peter");
        list.add("John");
        list.add(1,"Gaten");
        try {
            //遍历（方法1）
            for (String l : list) {
                System.out.println(l);
            }
            list.remove(2);
            //查找
            System.out.println(list.get(1));
            Iterator<String> it = list.iterator();
            //遍历（方法2）
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//反转链表
    public static class Node {

        private long data;
        private Node nextNode;

        public Node(long value)
        {
            this.data = value;
        }

        public long getData() {
            return data;
        }

        public void setData(long data) {
            this.data = data;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

    }
    public static class linkList {

        private Node firstNode;//头结点

        public linkList()
        {
            firstNode = null;
        }

        /**
         * 头插法建立链表
         */
        public void insertBegin(long value)
        {
            Node node = new Node(value);
            if (firstNode != null) {
                node.setNextNode(firstNode);
            }
            firstNode = node;
        }
        /**
         * reverseList反转列表
         */
        public Node  reverseList(){
            if(firstNode == null )
                return null;
            else if( firstNode.getNextNode() == null){
                return firstNode;
            }
            Node first = firstNode;
            Node second = firstNode.getNextNode();
            Node third = firstNode.getNextNode().getNextNode();

            first.setNextNode(null);
            while(third != null){
                second.setNextNode(first);
                first = second;
                second = third;
                third = third.getNextNode();
            }
            second.setNextNode(first);
            return second;
        }
        //first node为列表翻转后的头结点
        public void printReverse(Node firstnode){
            if(firstnode== null)
            {
                System.out.println("链表为空");
                return;
            }
            Node currNode = firstnode;
            while(currNode != null)
            {
                System.out.print(currNode.getData()+" ");

                currNode = currNode.getNextNode();

            }
        }
    }
}
