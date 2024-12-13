import java.util.ArrayList;

public class Heap< T extends Comparable<T> > {
    private int childCount;
    private ArrayList<T> data;

    public Heap(int childCount) {
        this.childCount = childCount;
        this.data = new ArrayList<>();
    }
}
