import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;

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

public class SingleLinkedList implements ILinkedList {

    public class Node {
        Object data;
        Node next;
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
        } else {
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = current;
        }
    }

    public void add(int index, Object element) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }

        size++;
        Node current = new Node();
        current.data = element;
        Node temp = head;
        Node prev = head;

        if (index == 0) {
            current.next = head;
            head = current;
        } else {
            for (int i = 0; i < index; i++) {
                prev = temp;
                temp = temp.next;
            }
            prev.next = current;
            current.next = temp;
        }
    }

    public Object get(int index) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }

        Node temp = head;
        int i = 0;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        return temp.data;
    }

    public void set(int index, Object element) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }

        Node temp = head;
        int i = 0;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        temp.data = element;
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

    public void remove(int index) {

        if ((index < 0) || (index >= size)) {
            System.out.println("Error");
            System.exit(0);
        }
        size--;
        Node current = head;
        Node prev = head;
        if (index == 0) {
            head = head.next;

        } else {
            int i = 0;
            while (i != index) {
                prev = current;
                current = current.next;
                i++;
            }
            prev.next = current.next;
        }
    }

    public boolean contains(Object o) {

        if (size == 0) {
            System.out.println("Error");
            System.exit(0);
        }

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

        if ((fromIndex < 0) || (fromIndex >= size) || (toIndex < 0) || (toIndex >= size) || (toIndex < fromIndex)) {
            System.out.println("Error");
            System.exit(0);
        }

        SingleLinkedList sub = new SingleLinkedList();
        Node last = head;
        Node first = head;
        for (int i = 0; i < fromIndex; i++) {
            first = first.next;
        }
        for (int i = fromIndex; i <= toIndex; i++) {
            sub.add(first.data);
            first = first.next;
        }
        return sub;
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

        SingleLinkedList list = new SingleLinkedList();

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

            list.add(index, ob1);
            list.display();
        } else if (operation.equals("get")) {

            int index = read.nextInt();
            System.out.println(list.get(index));

        } else if (operation.equals("set")) {

            int index = read.nextInt();
            Object ob1 = read.next();

            list.set(index, ob1);
            list.display();
        } else if (operation.equals("clear")) {
            list.clear();
            list.display();
        } else if (operation.equals("isEmpty")) {
            if (list.isEmpty())
                System.out.println("True");
            else
                System.out.println("False");
        } else if (operation.equals("remove")) {

            int index = read.nextInt();

            list.remove(index);
            list.display();

        } else if (operation.equals("size")) {
            System.out.println(list.size());
        } else if (operation.equals("sublist")) {

            int index1 = read.nextInt();
            int index2 = read.nextInt();

            SingleLinkedList sub = new SingleLinkedList();
            sub = (SingleLinkedList) list.sublist(index1, index2);
            sub.display();
        } else if (operation.equals("contains")) {

            Object ob1 = read.next();
            if (list.contains(ob1))
                System.out.println("True");
            else
                System.out.println("False");
        }
    }
}