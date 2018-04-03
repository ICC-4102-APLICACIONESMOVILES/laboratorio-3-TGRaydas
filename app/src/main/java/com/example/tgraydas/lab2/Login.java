package com.example.tgraydas.lab2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View the_view = inflater.inflate(R.layout.fragment_login, container, false);
        Button the_button = the_view.findViewById(R.id.login_action);
        the_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoLogin(the_view);
            }
        });
        String DATABASE_NAME = "movies_db";
        Database_PJ the_DB;
        the_DB = Room.databaseBuilder(getActivity().getApplicationContext(),
                Database_PJ.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        return the_view;
    }
    public boolean CheckInfo (String email, String password){
        String[] check_email = email.split("@");
        Integer len = check_email.length;
        if (len == 2 && password.length() > 5){
            return true;
        }
        else {
            return false;
        }
    }
    public void WriteSharedPreferences(String email, String password){
        SharedPreferences sharedPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putInt("active", 1);
        editor.commit();
    }
    public void DoLogin(View view) {
        EditText email = view.findViewById(R.id.email_input);
        EditText password = view.findViewById(R.id.password_input);
        boolean check_info = CheckInfo(email.getText().toString(), password.getText().toString());
        if (!check_info){
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Cometió un error porfavor ingrese denuevo sus credenciales", Toast.LENGTH_SHORT);
        }
        else {
            WriteSharedPreferences(email.getText().toString(), password.getText().toString());
            TextView email_nav = view.findViewById(R.id.email_nav);
        }
    }

}
