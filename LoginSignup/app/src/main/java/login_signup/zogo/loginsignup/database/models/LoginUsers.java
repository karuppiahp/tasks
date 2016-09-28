package login_signup.zogo.loginsignup.database.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by karuppiah on 9/27/2016.
 */
@Table(name = "LoginUsers")
public class LoginUsers extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;
    // This is a regular field
    @Column(name = "name")
    public String name;
    @Column(name = "email")
    public String email;
    @Column(name = "password")
    public String password;
    @Column(name = "contactNumber")
    public String contactNumber;

    // Make sure to have a default constructor for every ActiveAndroid model
    public LoginUsers(){
        super();
    }

    //List all items from LoginUsers table
    public static List<LoginUsers> getAllRow() {
        return new Select().from(LoginUsers.class).execute();
    }

    //Fetch user from LoginUsers table
    public static List<LoginUsers> isUserPresent(String email) {
        return new Select().from(LoginUsers.class).where("email = ?", email).execute();
    }
}
