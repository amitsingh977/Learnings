package DSA;

public class MinHeap {
    int maxSize;
    int heapSize;
    int[] arr;
    MinHeap(int maxSize){
        this.maxSize=maxSize;
        arr =new int[maxSize];
        heapSize=0;
    }
    public  void MinHeapify(int i){
        int smallest=i;
        int l=lChild(i);
        int r= rChild(i);
        if(l<heapSize && arr[i]>arr[l]){
            smallest=l;
        }
        if(r<heapSize && arr[i]>arr[r]){
            smallest=r;
        }
        if(smallest!=i){
            int temp=arr[i];
            arr[i]=arr[smallest];
            arr[smallest]=temp;
            MinHeapify(smallest);
        }
        
    }
    int lChild(int i){
        return 2*i+1;
    }
    int rChild(int i){
        return 2*i+2;
    }
}
