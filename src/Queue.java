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

    public Object getDataAtIndex(int index){
        if (index >= size) return null;
        N temp = front;
        for (int i = 1; i < index; i++){
            temp = temp.getNext();
        }
        if (temp == null) return null;
        return temp.getData();
    }

    public Object removeAtIndex(int index){
        if (index >= size) return null;
        N temp = front;
        for (int i = 2; i < index; i++){
            temp = temp.getNext();
        }
        if (temp.getNext() == null) return null;
        N nextNode = temp.getNext();
        temp.setNext(nextNode.getNext());
        return nextNode.getData();
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