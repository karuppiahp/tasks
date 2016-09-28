package login_signup.zogo.loginsignup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import login_signup.zogo.loginsignup.Utils.DividerItemDecoration;
import login_signup.zogo.loginsignup.Models.UsersModel;
import login_signup.zogo.loginsignup.R;
import login_signup.zogo.loginsignup.adapter.RecyclerViewUserAdapter;
import login_signup.zogo.loginsignup.database.models.LoginUsers;

/**
 * Created by karuppiah on 9/27/2016.
 */
public class WelcomeActivity extends Activity {

    private RecyclerView recyclerView;
    List<UsersModel> allUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //recyclerview initialize
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //fetch user datas from table
        LoginUsers userData = new LoginUsers();
        List<LoginUsers> allRows = userData.getAllRow();
        for(int i=0; i<allRows.size(); i++) {
            //save the user data from table to pojo class
            UsersModel userModel = new UsersModel();
            userModel.setName(allRows.get(i).name);
            userModel.setMailId(allRows.get(i).email);
            userModel.setContactNo(allRows.get(i).contactNumber);
            allUsers.add(userModel);
        }
        recyclerView.setAdapter(new RecyclerViewUserAdapter(allUsers));
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }
}
