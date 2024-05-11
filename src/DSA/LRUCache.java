package DSA;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    int capacity;
    Map<Integer,Node> mp=new HashMap<>();
    Node head=new Node(0,0);
    Node tail=new Node(0,0);
    public LRUCache(int capacity) {
        this.capacity=capacity;
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        if(mp.containsKey(key)){
            Node node = mp.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(mp.containsKey(key)){
            remove(mp.get(key));
        }
        else if(mp.size()==this.capacity){
            remove(tail.prev);
        }
        insert(new Node(key,value));
    }
    public void remove(Node node)
    {
        mp.remove(node.key);
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    public void insert(Node node) {
        mp.put(node.key, node);
        Node headNExt = head.next;
        head.next = node;
        node.prev=head;
        headNExt.prev=node;
        node.next=headNExt;
    }
//        Node new_node=new Node(node.key,node.value);
//        Node temp=head.next;
//        head.next=new_node;
//        new_node.prev=head;
//        temp.prev=new_node;
//        new_node.next=temp;



    public static void main(String[] args) {
        LRUCache lruCache =new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println("1: "+lruCache.get(1));
        lruCache.put(3,3);
        lruCache.get(2);
        System.out.println("2: "+lruCache.get(2));
        lruCache.put(4,4);
        System.out.println("1: "+lruCache.get(1));
        System.out.println("3: "+lruCache.get(3));
        System.out.println("4: "+lruCache.get(4));
    }
}
class Node{
    Node prev,next;
    int key, value;
    Node(int _key,int _value){
        key=_key;
        value=_value;
    }

}

/*
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */