public interface MultiSet {
    public boolean add(Object o);
    public void remove(Object o);
    public boolean contains(Object o);
    public boolean isEmpty();
    public int count(Object o);
    public int size();
}
