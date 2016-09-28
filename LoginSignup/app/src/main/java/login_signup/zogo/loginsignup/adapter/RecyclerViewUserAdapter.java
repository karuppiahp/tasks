package login_signup.zogo.loginsignup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import login_signup.zogo.loginsignup.Models.UsersModel;
import login_signup.zogo.loginsignup.R;

/**
 * Created by karuppiah on 9/28/2016.
 */
public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.MyViewHolder> {

    private List<UsersModel> usersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtForUserData;

        public MyViewHolder(View view) {
            super(view);
            txtForUserData = (TextView) view.findViewById(R.id.txtForUserData);
        }
    }


    public RecyclerViewUserAdapter(List<UsersModel> usersList) {
        this.usersList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_data_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtForUserData.setText(usersList.get(position).getName() + "\n" + usersList.get(position).getMailId() + "\n" + usersList.get(position).getContactNo());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
