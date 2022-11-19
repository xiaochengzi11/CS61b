package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> comparator;
    public MaxArrayDeque(){
        super();
    }
    public MaxArrayDeque(Comparator<T> c){
        super();
        comparator = c;
    }
    public T max(Comparator<T> c){
        if (isEmpty())
            return null;
        int maxIndex = 0;
        for(int i = 1; i < size(); ++i)
            maxIndex = c.compare(get(maxIndex),get(i)) < 0 ? i : maxIndex;
        return get(maxIndex);
    }
    public T max(){
        return max(comparator);
    }
}
