package datastructure.node;


/*
* 链表的实现和使用
* */
public class Main {

    private static Node head;

    public static void main(String[] args) {

        addValue(1);
        addValue(2);
//        travers();
        System.out.println("链表长度为：" + linkListLength(head));
    }

    //添加单向列表
    public static void addValue(int value) {
        if (head == null) {
            head = new Node(value);
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(value);
        }
    }

    //遍历单向列表
    public static void travers() {
        Node temp = head;
        while (temp != null) {
            System.out.println("数据：" + temp.data);
            temp = temp.next;
        }

    }

    /**
     * 向单向列表插入数据
     * index 插入位置
     * vale 插入值
     */
    public static void insertData(int index, int value) {

        if (index < 1 || index > linkListLength(head)) {
            System.out.println("插入位置不合法！");
            return;
        }

        Node temp = head;

        int currentPos = 0;

        Node insertNode = new Node(value);
        while (temp.next != null) {
            if ((index - 1) == currentPos) {
                insertNode.next = temp.next;
                temp.next = insertNode;
                return;
            }
            currentPos++;
            temp = temp.next;
        }

    }

    /**
     * 获取链表长度
     * head 链表头
     */
    public static int linkListLength(Node head) {
        if (head == null) {
            return 0;
        }

        int length = 1;
        Node temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

}
