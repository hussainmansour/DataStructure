package com.company;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}

public class ArrayQueue /*implements IQueue*/ {

    public void enqueue(Object item){
        if(rear== arr.length-1 && front!=-1){
            System.out.println("Error");
            System.exit(0);
        }
        rear++;
        arr[rear]=item;
       if(front==-1)
           front=0;
    }
    public void display(){
        System.out.print("[");
        for(int i = rear; i>=front; --i) {
            System.out.print(arr[i]);
            if(i!=front)
                System.out.print(", ");
        }
        System.out.print("]");
    }
    public Object dequeue(){
        if(front==-1&&rear==-1) {
            System.out.println("Error");
            System.exit(0);
        }
        front++;
        return arr[front-1];
    }
    public boolean isEmpty(){
        if(front>rear || rear==-1)return true;
        return false;
    }
    public int size(){
        if(rear==-1)return 0;
        return rear-front+1;
    }

    static Object[] arr = new Object[1000];
     static int  front = -1, rear= -1;

    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        ArrayQueue Queue = new ArrayQueue();
        String str=in.nextLine().replaceAll("\\[|\\]", "");
        String []s=str.split(", ");
        if (s.length == 1 && s[0].isEmpty())
            arr = new Object[1000];
        else {
            for(int i = s.length-1,j=0; i >=0 ; --i,j++)
                arr[j] = s[i];
            front =0;
            rear=s.length-1;
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