import java.lang.reflect.Array;
public class DynamicArray<a> {
    private a[] arr;
    private int size;
    public DynamicArray(Class<a> type) {
        arr = (a[]) Array.newInstance(type, 1);
        size = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public a get(int index){
        if(index < 0 || index >= size) return null;
        return arr[index];
    }
    public boolean contains(a item) {
        for(int i = 0; i < size; i++) {
            if(item.equals(arr[i])) return true;
        }
        return false;
    }
    private void resizeArray() {
        int newArrayLength = arr.length*2;
        a[] newArray = (a[]) Array.newInstance((Class<a>) arr.getClass().getComponentType(), newArrayLength);
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }
    public void add(a item) {
        if(size == arr.length) resizeArray();
        arr[size++] = item;
    }
    public void add(int index, a item) {
        if(index < 0 || index > size) throw new IllegalArgumentException("Array Index out of Bounds!");
        if(size == arr.length) resizeArray();
        System.arraycopy(arr, index, arr, index + 1, size++ - index);
        arr[index] = item;
    }
    public void set(int index, a item) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        arr[index] = item;
    }
    public a remove(int index) {
        if(index < 0 || index >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        a removedItem = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        arr[--size] = null;
        return removedItem;
    }
    public a remove(a item) {
        int index = -1;
        for(int i = 0; i < size; i++) {
            if(item.equals(arr[i])) {
                index = i;
                break;
            }
        }
        if(index != -1) remove(index);
        return item;
    }
    public a removeAll(a item) {
        for(int i = 0; i < size; i++) {
            if(item.equals(arr[i])) {
                remove(i);
                i--;
            }
        }
        return item;
    }
    public void clear() {
        for(int i = 0; i < size; i++) arr[i] = null;
        size = 0;
    }
}
