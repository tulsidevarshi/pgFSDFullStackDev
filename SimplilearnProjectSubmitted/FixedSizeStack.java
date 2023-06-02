package SimplilearnProjectSubmitted;
import java.util.Stack;
public class FixedSizeStack<T> extends Stack<T> {
    // T generalizes the type of objects that can come
    private static Integer maxSize;

    FixedSizeStack(int size){
        // call your parents first
        super();
        this.maxSize = size;
    }

    @Override
    public T push(T object){
        while(this.size() >= maxSize){
            this.remove(0);
        }
        return super.push(object);
    }
}
