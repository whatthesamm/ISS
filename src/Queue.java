public class Queue implements Q {

    // constructor

    public Queue() {}

    // selectors

    public void add(Object o) {

        if (size == 0) {
            front = new N(o, null);
            rear = front;
        }
        else {
            rear.setNext(new N(o, null));
            rear = rear.getNext();
        }
        size++;
    }

    public Object remove() {

        Object answer;

        if (size == 0)
            return null;

        answer = front.getData();
        front = front.getNext();
        size--;
        if (size == 0)
            rear = null;
        return answer;
    }

    public int length() {
        return size;
    }

    private int size;

    public N getFront() {
        return front;
    }

    private N front;
    private N rear;

}  // Q1 class