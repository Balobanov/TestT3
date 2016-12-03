package evg.testt.dto;

/**
 * Created by DENNNN on 03.12.2016.
 */
public class MoveLead {
    private int destination;
    private int from;

    public MoveLead() {
    }

    public MoveLead(int destination, int from) {
        this.destination = destination;
        this.from = from;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }
}
