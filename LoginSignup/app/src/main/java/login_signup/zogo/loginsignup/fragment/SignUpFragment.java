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
public class SignUpFragment extends Fragment {

    View v;
    EditText editTxtForName, editTxtForMail, editTxtForPwd, editTxtForPhNo;
    Button createAccBtn;

    public SignUpFragment() {
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
        v = inflater.inflate(R.layout.sign_up, container, false);
        editTxtForName = (EditText) v.findViewById(R.id.edtTxtForName);
        editTxtForMail = (EditText) v.findViewById(R.id.edtTxtForMail);
        editTxtForPwd = (EditText) v.findViewById(R.id.edtTxtForPwd);
        editTxtForPhNo = (EditText) v.findViewById(R.id.edtTxtForPhNo);
        createAccBtn = (Button) v.findViewById(R.id.btnForCreateAcc);
        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTxtForName.getText().toString().trim().length() > 0) { //Checks name field empty or not
                    if(editTxtForMail.getText().toString().trim().length() > 0) { //Checks email field empty or not
                        if(Utils.isEmailValid(editTxtForMail.getText().toString().trim())) { //checks valid email
                            if(editTxtForPwd.getText().toString().trim().length() > 0) { //checks pwd field empty or not
                                if(Utils.PasswordValidator(editTxtForPwd.getText().toString().trim())) { //Checks password field contains all requirements
                                    if(editTxtForPhNo.getText().toString().trim().length() > 0) { //checks contact number field empty or not
                                        if(editTxtForPhNo.getText().toString().trim().length() == 10) { //checks contact number contains 10 numbers
                                            if (Utils.isPhoneNumberValid(editTxtForPhNo.getText().toString().trim())) { //checks valid contact number
                                                LoginUsers loginUsers = new LoginUsers();
                                                //Checks if user email is already present or not
                                                List<LoginUsers> userData = loginUsers.isUserPresent(editTxtForMail.getText().toString().trim());
                                                if(userData.size() > 0) {
                                                    String email = userData.get(0).email;
                                                    if (email.equals(editTxtForMail.getText().toString().trim())) {
                                                        Utils.ShowAlert(getActivity(), "Email id already exits");
                                                    }
                                                } else {
                                                    //fetch row size to initialize for remoteId in table
                                                    List<LoginUsers> allUsersRow = loginUsers.getAllRow();
                                                    if (allUsersRow.size() > 0) {
                                                        loginUsers.remoteId = allUsersRow.size() + 1;
                                                    } else {
                                                        loginUsers.remoteId = 1;
                                                    }
                                                    loginUsers.name = editTxtForName.getText().toString().trim();
                                                    loginUsers.email = editTxtForMail.getText().toString().trim();
                                                    loginUsers.password = editTxtForPwd.getText().toString().trim();
                                                    loginUsers.contactNumber = editTxtForPhNo.getText().toString().trim();
                                                    loginUsers.save();

                                                    Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                                                    startActivity(intent);
                                                    getActivity().finish();
                                                }
                                            } else {
                                                Utils.ShowAlert(getActivity(), "Please enter a valid contact number.");
                                            }
                                        } else {
                                            Utils.ShowAlert(getActivity(), "Your contact number must contains 10 characters long.");
                                        }
                                    } else {
                                        Utils.ShowAlert(getActivity(), "Please enter the Contact Number.");
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
                    Utils.ShowAlert(getActivity(), "Please enter the Name.");
                }
            }
        });

        return v;
    }

}
