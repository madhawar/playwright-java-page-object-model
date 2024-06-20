package support;

import java.util.List;

public class DataPOJO {

    private String typeOfCover;
    private String cruiseCover;
    private String travellingFrom;
    private String travellingTo;
    private String departureDateLead;
    private String returnDateLead;
    private String coverFor;
    private Organiser organiser;
    private String title;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String telephoneNumber;
    private String postCode;
    private Travellers travellers;

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

    public static class Travellers {
        private List<String> age;

        public List<String> getAge() {
            return age;
        }

//        public void setAge(List<String> age) {
//            this.age = age;
//        }
    }

    public Travellers getTravellers() {
        return travellers;
    }

    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser organiser) {
        this.organiser = organiser;
    }

//    public String getTitle() { return  title; }
//    public String getFirstName() { return  firstName; }
//    public String getLastName() { return  lastName; }
//    public String getEmailAddress() { return  emailAddress; }
//    public String getTelephoneNumber() { return  telephoneNumber; }
//    public String getPostCode() { return  postCode; }

}