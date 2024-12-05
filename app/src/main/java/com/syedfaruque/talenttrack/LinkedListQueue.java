package com.syedfaruque.talenttrack;

/**
 * This LinkedListQueue class represents a queue implemented using singly linked list
 * During the enqueue process, a Node is added to the linked list after the tail (O(1))
 * During the dequeue process, the head of the linked list is reassigned (O(1))
 *
 * @author Syed Faruque
 *      e-mail:syed.faruque@stonybrook.edu
 *      Stony Brook ID: 116340094
 *      Recitation: CSE 214.R03
 */

public class LinkedListQueue{

    /**
     * This Node class represents a node contained inside the linked list queue
     * A Node holds data and the reference to the next Node
     * the data the Node holds is a Bigram
     */
    private class Node {
        /**
         * attributes of a Node. Data and reference to next Node
         */
        private Bigram data;
        private Node next;

        /**
         * params constructor that takes in a data property and creates a new Node with that data property
         * @param data   the Bigram to be assigned to the data property of the node
         */
        public Node(Bigram data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * attributes of the linked list queue. The head and tail of the linked list, and the number of nodes it contains
     */
    private Node head;
    private Node tail;
    private int size;

    /**
     * no params constructor that creates a linked list queue object with null head and tail and 0 size
     */
    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * adds a new Bigram node after the previous tail in the linked list
     * @param b   the Bigram to that the node to be added will contain as data
     */
    public void enqueue(Bigram b) {
        Node newNode = new Node(b);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * removes and returns the head of the linked list
     * @return the data that the removed head contained
     */
    public Bigram dequeue(){
        Bigram removedBigram;
        if (head == null) {
            System.out.println("The queue is empty and no more elements are there to be dequeued");
        }
        removedBigram = head.data;
        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
        }
        else {
            head = head.next;
            size--;
        }
        return removedBigram;
    }

    /**
     * @return the Bigram data from the head of the linked list
     */
    public Bigram peek() {
        return head.data;
    }

    /**
     * @return the number of nodes the linked list contains
     */
    public int size() {
        return size;
    }

    /**
     * @return true if the linked list contains no nodes, false otherwise
     */
    public boolean isEmpty(){
        return (head == null);
    }

}