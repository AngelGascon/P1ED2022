import java.util.Iterator;

/**
 * Raw implementation of a StaticTable
 * @param <T> Defines info of the data structure
 */
public class StaticTable <T extends Comparable<T>> implements TADList<T>, Iterable<T>{

    private Object[] list;
    private int lastElem;

    public StaticTable(){ create(); }
    public StaticTable(int size){ create(size); }

    @Override
    public void create() {
        //Default size 10
        list = new Object[10];
        lastElem = 0;
    }

    public void create(int size) {
        list = new Object[size];
        lastElem = 0;
    }

    @Override
    public void add(T elem) {
        if(lastElem < list.length){
            list[lastElem] = elem;
            lastElem++;
        }else{
            throw new ArrayIndexOutOfBoundsException("List needs resizing");
        }
    }

    @Override
    public void add(int pos, T elem) {
        if(lastElem < list.length && pos > list.length){
            list[lastElem] = elem;
            lastElem++;
        }else{
            throw new ArrayIndexOutOfBoundsException("Out of Bonds: "+0+"..."+(list.length-1));
        }
    }

    @Override
    public T get(int pos) {
        if(pos < list.length){
            return (T) list[pos];
        }else{
            throw new ArrayIndexOutOfBoundsException("Out of Bonds: "+0+"..."+(list.length-1));
        }
    }

    @Override
    public int lon() {
        return list.length;
    }

    @Override
    public void delete(int pos) {
        if(pos < lastElem){
            for(int i = pos; i+1 < lastElem;i++){
                list[i] = list[i+1];
            }
            lastElem--;
        }else{
            throw new ArrayIndexOutOfBoundsException("Out of Bonds: "+0+"..."+(list.length-1));
        }
    }

    @Override
    public int search(T elem) {
        int count = 0;
        for (Object obj : list) {
            if(((T)obj).compareTo(elem)==0) break;
            count++;
        }
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new StaticTable.StaticTableIterator(list, lastElem);
    }

    private class StaticTableIterator implements Iterator<T>{

        private Object[] aux;
        private int current, nElem;

        private StaticTableIterator(Object[] ref, int nElem){
            aux = ref;
            this.nElem = nElem;
            current = 0;
        }

        @Override
        public boolean hasNext() { return current < nElem; }

        @Override
        public T next() {
            current++;
            return (T) aux[current-1];
        }
    }
}