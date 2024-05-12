package DSA;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    final int capacity;
    int curSize;
    int minFrequency;
    Map<Integer,DLL> frequencyMap;
    Map<Integer, DLLNode> cache;


    public LFUCache(int capacity) {
        this.capacity=capacity;
        this.minFrequency=0;
        this.curSize=0;
        this.frequencyMap = new HashMap<>();
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        DLLNode curDLLNode = cache.get(key);
        if(curDLLNode==null){
            return -1;
        }

        updateNode(curDLLNode);
        return curDLLNode.value;
    }

    public void put(int key, int value) {
        if(capacity==0){
            return;
        }
        if(cache.containsKey(key)){
            DLLNode curDLLNode = cache.get(key);
            curDLLNode.value=value;
            updateNode(curDLLNode);
        }
        else{
            curSize++;
            if(curSize>capacity){
                DLL minList=frequencyMap.get(minFrequency);
                cache.remove(minList.tail.prev.key);
                minList.removeNode(minList.tail.prev);
                curSize--;
            }
            minFrequency=1;
            DLLNode newDLLNode = new DLLNode(key,value);
            DLL list=frequencyMap.getOrDefault(1,new DLL());
            list.addNode(newDLLNode);
            frequencyMap.put(1,list);
            cache.put(key, newDLLNode);
        }
    }
    public void updateNode(DLLNode curDLLNode){
        Integer freq= curDLLNode.frequency;
        DLL curList = frequencyMap.get(freq);
        curList.removeNode(curDLLNode);
        if(freq==minFrequency && curList.listSize==0){
            minFrequency++;
        }
        curDLLNode.frequency++;
        DLL newList=frequencyMap.getOrDefault(curDLLNode.frequency,new DLL());
        newList.addNode(curDLLNode);
        frequencyMap.put(curDLLNode.frequency,newList);

    }

    public static void main(String[] args) {
        LFUCache lfucache = new LFUCache(2);
        lfucache.put(1,1);
        lfucache.put(2,2);
        System.out.println("1: "+lfucache.get(1));
        lfucache.put(3,3);
        System.out.println("2: "+lfucache.get(2));
        System.out.println("3: "+lfucache.get(3));
        lfucache.put(4,4);
        System.out.println("1: "+lfucache.get(1));
        System.out.println("3: "+lfucache.get(3));
        System.out.println("4: "+lfucache.get(4));
    }
}
class DLLNode {
    int key;
    int value;
    DLLNode next,prev;
    int frequency;
    public DLLNode(int key, int value){
        this.key=key;
        this.value=value;
        this.frequency=1;
    }
}
class DLL{
    DLLNode head;
    DLLNode tail;
    int listSize;
    public DLL()
    {
        listSize=0;
        head=new DLLNode(0,0);
        tail=new DLLNode(0,0);
        head.next=tail;
        tail.prev=head;
    }
    public void addNode(DLLNode newDLLNode){
        DLLNode headNext=head.next;
        head.next= newDLLNode;
        newDLLNode.prev=head;
        headNext.prev= newDLLNode;
        newDLLNode.next=headNext;
        listSize++;

    }
    public void removeNode(DLLNode curNode){
        DLLNode nextNode= curNode.next;
        DLLNode prevNode= curNode.prev;
        prevNode.next=nextNode;
        nextNode.prev=prevNode;
        listSize--;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */