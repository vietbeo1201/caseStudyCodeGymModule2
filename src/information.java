package src;
public class information {
    private String name = "Unknown Name";
    private String gender = "Unknown Gender";
    private String phoneNumber;
    private String group = "Unknown Group";
    private String address = "Unknown Address";
    private String dateOfBirth;
    private String email = "Unknown Email";

    public information(String name, String gender, String phoneNumber, String group, String address, String dateOfBirth, String email) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.group = group;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
    public information() {}

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String  getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String  phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getGroup(){
        return group;
    }
    public void setGroup(String group){
        this.group = group;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return   name
                + ", " + gender
                + ", " + phoneNumber
                + ", " + group
                + ", " + address
                + ", " + dateOfBirth
                + ", " + email;
    }
}

