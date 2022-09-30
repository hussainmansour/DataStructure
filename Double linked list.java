import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {

    public void add(int index, Object element);

    public void add(Object element);

    public Object get(int index);

    public void set(int index, Object element);

    public void clear();

    public boolean isEmpty();

    public void remove(int index);

    public int size();

    public ILinkedList sublist(int fromIndex, int toIndex);

    public boolean contains(Object o);
}

public class DoubleLinkedList implements ILinkedList {

    public class Node {
        Object data;
        Node next;
        Node prev;
    }

    Node head = null;
    static int size = 0;

    public void add(Object element) {

        size++;
        Node current = new Node();
        current.data = element;
        current.next = null;
        Node temp = head;
        if (head == null) {
            head = current;
            current.prev = null;
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = current;
            current.prev = temp;
        }
    }

    public void add(int index, Object element) {

        size++;
        Node current = new Node();
        current.data = element;
        Node temp = head;

        if (index == 0) {
            head.prev = current;
            current.prev = null;
            current.next = head;
            head = current;
        } else {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            (temp.prev).next = current;
            current.prev = temp.prev;
            temp.prev = current;
            current.next = temp;
        }
    }

    public Object get(int index) {

        Node temp = head;
        int i = 0;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        return temp.data;
    }

    public void set(int index, Object element) {
        Node temp = head;
        int i = 0;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        temp.data = element;
    }

    public void remove(int index) {
        size--;
        Node current = head;
        if (index == 0) {
            head = head.next;
        } else {
            int i = 0;
            while (i != index) {
                current = current.next;
                i++;
            }
            if (current.next == null) {
                (current.prev).next = current.next;
            } else {
                (current.prev).next = current.next;
                (current.next).prev = current.prev;
            }
        }
    }

    public boolean contains(Object o) {
        boolean flag = false;
        String c1 = o.toString();
        Node current = head;
        while (current != null) {
            if ((((current.data).toString()).equals(c1))) {
                flag = true;
                break;
            }
            current = current.next;
        }
        return flag;
    }

    public ILinkedList sublist(int fromIndex, int toIndex) {
        DoubleLinkedList sub = new DoubleLinkedList();
        Node first = head;
        for (int i = 0; i < fromIndex; i++) {
            first = first.next;
        }
        if (fromIndex == toIndex) {
            sub.add(first.data);
            return sub;
        }
        for (int i = fromIndex; i <= toIndex; i++) {
            sub.add(first.data);
            first = first.next;
        }
        return sub;
    }

    public int size() {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public void clear() {
        size = 0;
        head = null;
    }

    public boolean isEmpty() {
        if (head == null)
            return true;
        else
            return false;
    }

    public void display() {
        Node current = head;
        System.out.print("[");
        for (int i = 0; current != null; ++i) {
            System.out.print(current.data);
            if (current.next != null)
                System.out.print(", ");
            current = current.next;
        }
        System.out.print("]");
    }

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        DoubleLinkedList list = new DoubleLinkedList();

        String sin = read.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");

        Object[] arr = new Object[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new Object[] {};
        else {
            for (int i = 0; i < s.length; ++i) {
                arr[i] = s[i];
                list.add(arr[i]);
            }
        }

        String operation = read.nextLine();

        if (operation.equals("add")) {

            Object ob1 = read.next();
            list.add(ob1);
            list.display();
        } else if (operation.equals("addToIndex")) {
            int index = read.nextInt();
            Object ob1 = read.next();
            if ((index < 0) || (index >= size)) {
                System.out.println("Error");
                return;
            } else {
                list.add(index, ob1);
            }
            list.display();
        } else if (operation.equals("get")) {
            int index = read.nextInt();
            if ((index < 0) || (index >= size)) {
                System.out.println("Error");
                return;
            } else {
                System.out.println(list.get(index));
            }

        } else if (operation.equals("set")) {

            int index = read.nextInt();
            Object ob1 = read.next();

            if ((index < 0) || (index >= size)) {
                System.out.println("Error");
                return;
            } else {
                list.set(index, ob1);
            }
            list.display();
        } else if (operation.equals("remove")) {

            int index = read.nextInt();
            if ((index < 0) || (index >= size)) {
                System.out.println("Error");
                return;
            } else {
                list.remove(index);
                list.display();
            }
        } else if (operation.equals("contains")) {

            Object ob1 = read.next();
            if (size == 0) {
                System.out.println("Error");
                return;
            } else {
                if (list.contains(ob1))
                    System.out.println("True");
                else
                    System.out.println("False");
            }
        } else if (operation.equals("sublist")) {
            int index1 = read.nextInt();
            int index2 = read.nextInt();
            if ((index1 < 0) || (index1 >= size) || (index2 < 0) || (index2 >= size) || (index2 < index1)) {
                System.out.println("Error");
                return;
            }
            DoubleLinkedList sub = new DoubleLinkedList();
            sub = (DoubleLinkedList) list.sublist(index1, index2);
            sub.display();
        } else if (operation.equals("clear")) {
            list.clear();
            list.display();
        } else if (operation.equals("isEmpty")) {
            if (list.isEmpty())
                System.out.println("True");
            else
                System.out.println("False");
        } else if (operation.equals("size")) {
            System.out.println(list.size());
        }
    }
}