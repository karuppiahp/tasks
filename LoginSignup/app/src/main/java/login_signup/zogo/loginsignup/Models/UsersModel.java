package login_signup.zogo.loginsignup.Models;

/**
 * Created by karuppiah on 9/28/2016.
 */
public class UsersModel {

    private String name;
    private String emailId;
    private String contactNo;

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailId() {
        return emailId;
    }

    public void setMailId(String emailId) {
        this.emailId = emailId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
