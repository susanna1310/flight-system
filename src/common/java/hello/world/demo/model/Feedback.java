package hello.world.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private double flight;
    private double catering;
    private double entertainment;
    private double comfort;
    private double service;
    private List<String> comments;
    private int feedbackCount;

    public Feedback () {
        this.flight = 0;
        this.comfort = 0;
        this.entertainment = 0;
        this.service = 0;
        this.catering = 0;
        this.comments = new ArrayList<>();
        this.feedbackCount = 0;
    }

    public void updateRating(
            double flight, double catering, double entertainment,
            double service, double comfort, String comment) {
        this.feedbackCount++;
        this.flight = (this.flight + flight) / feedbackCount;
        this.catering = (this.catering + catering) / feedbackCount;
        this.entertainment = (this.entertainment + entertainment) / feedbackCount;
        this.service = (this.service + service) / feedbackCount;
        this.comfort = (this.comfort + comfort) / feedbackCount;
        this.comments.add(comment);
    }

    public double getFlight() {
        return flight;
    }

    public void setFlight(int flightRate) {
        this.flight = flightRate;
    }

}
