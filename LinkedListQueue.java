package com.company;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LinkedListQueue implements IQueue {

    class Node{
        Object data;
        Node next;
    }
     Node head = null;
    public void add(Object element){
         Node current=new Node();
        current.data=element;
        current.next=null;
        Node temp = head;
        if(head==null)
            head=current;
        else {
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=current;
        }
    }
    public void enqueue(Object item){
        Node current=new Node();
        current.data=item;
        current.next=head;
        head=current;
    }
    public int size(){
        Node current=new Node();
        int size=0;
        if(head==null)
            return size;

        current=head;
        while (current!=null){
            size++;
            current=current.next;
        }
        return size;
    }
    public Object dequeue(){
        if(head==null){
            System.out.println("Error");
            System.exit(0);
        }
        if(head.next==null){
            Object o=head.data;
            head=null;
            return o;
        }
        Node temp = head;
        Node prev = head;
        while(temp.next!=null){
            prev=temp;
            temp=temp.next;
        }

        Object obj=temp.data;
        prev.next=null;

        return obj;
    }
    public void display(){
         Node current = head;
        System.out.print("[");
        for(int i = 0; current!=null; ++i) {
            System.out.print(current.data);
            if(current.next!=null )
                System.out.print(", ");
            current = current.next;
        }
        System.out.print("]");
    }
    public boolean isEmpty(){
        if(head==null)
            return true;
        return false;
    }


    public static void main(String[] args) {

        Scanner in =new Scanner(System.in);
        LinkedListQueue Queue =new LinkedListQueue();

        String str=in.nextLine().replaceAll("\\[|\\]", "");
        String []s=str.split(", ");
        Object[] arr = new Object[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new Object[]{};
        else {
            for(int i = 0; i < s.length; ++i){
                arr[i] = s[i];
                Queue.add(arr[i]);
            }
        }
        String op=in.next();
        if(op.equals("enqueue")){
            Object obj=in.next();
            Queue.enqueue(obj);
            Queue.display();
        }
        else if(op.equals("dequeue")){
            Queue.dequeue();
            Queue.display();
        }
        else if(op.equals("isEmpty")){
            if(Queue.isEmpty())
                System.out.println("True");
            else
                System.out.println("False");
        }
        else if(op.equals("size")){
            System.out.println(Queue.size());
        }
        else
            System.out.println("Error");

    }
}