package pl.smsapi.api.response;

public class ContactResponse implements Response {

    public static enum Gender {

        MALE(-1),
        FEMALE(1);
        private final int type;

        Gender(int type) {
            this.type = type;
        }

        public Integer type() {
            return type;
        }
    }

    ;
    private String number;
    private String firstName;
    private String lastName;
    private String info;
    private String email;
    private String birthday;
    private String city;
    private String gender;
    private int dateAdd;
    private int dateMod;

    public ContactResponse(String number, String firstName, String lastName, String info, String birthday, String city, String gender, int dateAdd, int dateMod) {

        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.info = info;
        this.birthday = birthday;
        this.city = city;
        this.gender = gender;
        this.dateAdd = dateAdd;
        this.dateMod = dateMod;

    }

    public String getNumber() {
        return number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInfo() {
        return info;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public int getDateAdd() {
        return dateAdd;
    }

    public int getDateMod() {
        return dateMod;
    }
}
