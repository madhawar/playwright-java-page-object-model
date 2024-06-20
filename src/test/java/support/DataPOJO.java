package support;

public class DataPOJO {

    private String typeOfCover;
    private String cruiseCover;
    private String travellingFrom;
    private String travellingTo;
    private String departureDateLead;
    private String returnDateLead;
    private String coverFor;
    private String travellerAge1;

    public String getTypeOfCover() {
        return typeOfCover;
    }

    public String getCruiseCover() {
        return cruiseCover;
    }

    public String getTravellingFrom() {
        return travellingFrom;
    }

    public String getTravellingTo() { return travellingTo; }

    public int getDepartureDateLead() { return Integer.parseInt(departureDateLead); }

    public int getReturnDateLead() { return Integer.parseInt(returnDateLead); }

    public String getCoverFor() { return coverFor; }

    public String getTravellerAge1() {
        return travellerAge1;
    }
}