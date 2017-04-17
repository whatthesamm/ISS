// Example 27a
// Priority Queue and Simulation
// Segment class used in priority queue (PQ.java)
// Uses queue class Q1.java
//From CSCI1933 Week 9 Folder
public class Segment {

    private double time;
    private Queue q;
    private Segment next;

    // constructor

    public Segment(double t) {
        time = t;
        q = new Queue();
        next = null;
    }

    // methods

    public double getTime() {
        return time;
    }

    public Queue getEvents() {
        return q;
    }

    public Segment getNext() {
        return next;
    }

    public void setNext(Segment nextSegment) {
        next = nextSegment;
    }

}  // Segment class
