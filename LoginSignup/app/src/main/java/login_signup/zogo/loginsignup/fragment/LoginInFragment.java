package login_signup.zogo.loginsignup.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import login_signup.zogo.loginsignup.R;
import login_signup.zogo.loginsignup.Utils.Utils;
import login_signup.zogo.loginsignup.activity.WelcomeActivity;
import login_signup.zogo.loginsignup.database.models.LoginUsers;

/**
 * Created by karuppiah on 9/27/2016.
 */
public class LoginInFragment extends Fragment {

    private View v;
    private EditText edtTxtForEmail, editTxtForPwd;
    private Button btnForLogin;
    private List<LoginUsers> allUsersRow;

    public LoginInFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.login, container, false);
        edtTxtForEmail = (EditText) v.findViewById(R.id.edtTxtForEmail);
        editTxtForPwd = (EditText) v.findViewById(R.id.edtTxtForPwd);
        btnForLogin = (Button) v.findViewById(R.id.btnForLogin);

        //Fetch all users from LoginUsers Table
        LoginUsers loginUsers = new LoginUsers();
        allUsersRow = loginUsers.getAllRow();

        btnForLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allUsersRow.size() > 0) { //if table is empty or not
                    if (edtTxtForEmail.getText().toString().trim().length() > 0) { //Checks email field empty or not
                        if (Utils.isEmailValid(edtTxtForEmail.getText().toString().trim())) { //checks valid email
                            if (editTxtForPwd.getText().toString().trim().length() > 0) { //checks pwd field empty or not
                                if (Utils.PasswordValidator(editTxtForPwd.getText().toString().trim())) { //Checks password field contains all requirements
                                    LoginUsers loginUsers = new LoginUsers();
                                    //check if user presents or not in DB
                                    List<LoginUsers> userData = loginUsers.isUserPresent(edtTxtForEmail.getText().toString().trim());
                                    if(userData.size() > 0) {
                                        //Fetch password from table
                                        String password = userData.get(0).password;
                                        //check if password matches or not
                                        if(password.equals(editTxtForPwd.getText().toString().trim())) {
                                            Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        } else {
                                            Utils.ShowAlert(getActivity(), "Password doesn't match, please try again later.");
                                        }
                                    } else {
                                        Utils.ShowAlert(getActivity(), "User doesn't found.");
                                    }
                                } else {
                                    Utils.ShowAlert(getActivity(), "Your password must be at least 6 characters long, using uppercase, lowercase, numbers and special characters.");
                                }
                            } else {
                                Utils.ShowAlert(getActivity(), "Please enter the Password.");
                            }
                        } else {
                            Utils.ShowAlert(getActivity(), "Please enter the valid Email Id.");
                        }
                    } else {
                        Utils.ShowAlert(getActivity(), "Please enter the Email Id.");
                    }
                } else {
                    Utils.ShowAlert(getActivity(), "No records found, please signup.");
                }
            }
        });

        return v;
    }
}
