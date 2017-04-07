// Example 27
// Queue implementation using an Array
// Revised November 2016

public class Q2 implements Q {

    // constructors

    public Q2() {
        q = new Object[0];
    }

    public Q2(int initLength) {

        if (initLength < 0)
          throw new IllegalArgumentException("capacity requested is negative");
        q = new Object[initLength];
    }

    // selectors

    public void add(Object o) {
    
        if (q.length == 0) {  // array non-existant, create it and insert first object 
          q = new Object[1];
          size = 1;
          q[0] = o; 
        }
        else if (size == 0)  { // adding to empty queue
               rear = 0;
               front = 0;
               size = 1;
               q[0] = o;
        }
        else  {  // general case: array exists and non-empty
          if (size == q.length) {  // allocate bigger array if needed
            Object[] newq = new Object[2 * q.length + 1];
            if (front <= rear)  // queue has not wrapped, 
                                // so make simple copy to new space
              System.arraycopy(q, front, newq, 0, size);
            else if (front > rear) {  // queue has wrapped,
                                      // so copy in two chunks
                   System.arraycopy(q, front, newq, 0, q.length - front);
                   System.arraycopy(q, 0, newq, q.length - front, rear + 1);
                   front = 0; 
                   rear = size - 1;
                 }
            q = newq;
          }  // allocate bigger array if needed

          rear = (rear + 1) % q.length;
          q[rear] = o;  
          size++;

      }  // general case: array exists and non-empty
    }  // add

    public Object remove() {

        if (size == 0)
          return null;

        Object answer = q[front];
        front = (front + 1) % q.length;
        size--;
        return answer;
    }

    public int length() {
        return size;
    }

    private Object[] q;
    private int size;  // number of items in the array
    private int front;  // first element
    private int rear;  // last element

}  // Q2 class 
