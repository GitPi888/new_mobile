package com.example.demo.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.Model.UserDatabase;
import com.example.demo.Model.UserModel;
import com.example.demo.R;
import com.example.demo.activities.Updatepassword;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String email_user,password_user;
    TextView txt_email,txt_name,update_password;
    Button btn_logout;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        email_user=this.getArguments().getString("email");
        password_user=this.getArguments().getString("pass");
    }
    public ArrayList<UserModel> getData(String email_user){
        String[] rs = new String[1];
        rs[0]=email_user;
        ArrayList<UserModel> ds = new ArrayList<>();
        UserDatabase db = new UserDatabase(getContext());
        SQLiteDatabase sq = db.getReadableDatabase();
        Cursor c = sq.rawQuery("Select*from USER where EMAIL=?",rs);
        if(c.moveToFirst()){
            UserModel userModel = new UserModel();
            userModel.setName(c.getString(1));
            userModel.setEmail(c.getString(2));
            ds.add(userModel);
        }
        return  ds;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        txt_email = root.findViewById(R.id.fragment_Email);
        txt_name = root.findViewById(R.id.fragment_Username);
        update_password=root.findViewById(R.id.txt_Update_Password);
        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Updatepassword.class);
                intent.putExtra("key_email",email_user);
                intent.putExtra("key_password",password_user);
                startActivity(intent);
            }
        });
        btn_logout=root.findViewById(R.id.btn_Logout);
        ArrayList<UserModel> ds = new ArrayList<>();
        ds=getData(email_user);
        txt_email.setText(ds.get(0).getEmail());
        txt_name.setText((ds.get(0).getName()));
        return root;
    }
}