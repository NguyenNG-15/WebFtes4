package ftes.users;
import java.sql.Date;

public class UserDTO {
    private int userID;
    private String fullName;
    private String gender;
    private Date dob;
    private String email;
    private String phone;

    public UserDTO() {}

    public UserDTO(int userID, String fullName, String gender, Date dob, String email, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}