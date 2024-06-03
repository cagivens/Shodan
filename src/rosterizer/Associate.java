package rosterizer;

import java.util.ArrayList;

public class Associate {

    public enum TrainedRole {
        AMBASSADOR,
        END_OF_LINE,
        PROBLEM_SOLVE,
        WATERSPIDER,
        REFURB,
        WHD,
        RECOVERY,
        OUTBOUND,
        SHOE_PROCESSING,
        ICQA,
        TDR,
        PIT
    }

    private String login;
    private double rate;
    private ArrayList<TrainedRole> roles;
}
