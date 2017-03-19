package com.vitorjorge.movies.movies.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.vitorjorge.movies.movies.R;
import com.vitorjorge.movies.movies.dataBase.Database;
import com.vitorjorge.movies.movies.domain.RepositoryUser;
import com.vitorjorge.movies.movies.model.User;
import java.util.ArrayList;

public class LoginScreenACT extends AppCompatActivity implements View.OnClickListener {

    private Animation shake;
    private EditText userName, password;
    private Button button;
    private CheckBox checkBox;
    private ArrayList<User> arrayList;
    private SQLiteDatabase conn;
    private Database database;
    private RepositoryUser repositoryUser;
    public static final String PREFERENCESFILE = "SHARED PREFERENCES";
    public static final String KEEPINGLOGIN = "keepingLogin";
    private User user;
    private static String TITLEALERTDIAG, MESSAGEALERTDIAG, DATABASEPROBLEM, ENTERINFO, INVALIDUSER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_act);

        userName = (EditText) findViewById(R.id.edtUserNameId);
        password = (EditText) findViewById(R.id.edtPasswordId);
        button = (Button) findViewById(R.id.btnLoginId);
        checkBox = (CheckBox)findViewById(R.id.chk_keepingLogedId);
        button.setOnClickListener(this);

        TITLEALERTDIAG = getResources().getString(R.string.ad_setTitle_login);
        MESSAGEALERTDIAG = getResources().getString(R.string.ad_setMessage_login);
        DATABASEPROBLEM = getResources().getString(R.string.tst_DatabaseProblema_Login);
        ENTERINFO = getResources().getString(R.string.tst_enterinfo_Login);
        INVALIDUSER = getResources().getString(R.string.tst_invalidUser);




        SharedPreferences sf = getSharedPreferences(PREFERENCESFILE, 0);
        if(sf.contains(KEEPINGLOGIN)){

            String keeplogin = sf.getString(KEEPINGLOGIN, "Não definido");
            if (keeplogin.equals("true")){

                Intent intent = new Intent(LoginScreenACT.this, MainScreenNV.class);
                startActivity(intent);
            }else {

            }
        }

        try{
            database = new Database(this);
            conn = database.getWritableDatabase();
            repositoryUser = new RepositoryUser(conn);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), INVALIDUSER, Toast.LENGTH_LONG).show();

            Log.i("DESCRITION", "-" + e.getMessage());

            e.printStackTrace();
        }

    }

    public User serchUser(){

        arrayList = new ArrayList<>();
        arrayList = repositoryUser.serchUsers();

        if(arrayList == null){

            return null;

        }else {

            Integer c = arrayList.size() -1 ;

            if (arrayList.get(c) != null){
                User user = new User();
                user = arrayList.get(c);
                return user;


            }

        }
        return null;

    }

    @Override
    public void onClick(View view) {

        user = serchUser();


            if (user == null){

                new AlertDialog.Builder(this)
                        .setTitle(TITLEALERTDIAG)
                        .setMessage(MESSAGEALERTDIAG)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setCancelable(false)
                        .show();

            }else{

                if (userName.getText().toString().isEmpty() && password.getText().toString().isEmpty()){
                    Toast.makeText(LoginScreenACT.this, ENTERINFO, Toast.LENGTH_SHORT).show();

                }else {

                    if(userName.getText().toString().equals(user.getUsuario()) && password.getText().toString().equals(user.getSenha())){



                        Intent intent = new Intent(LoginScreenACT.this, MainScreenNV.class);
                        startActivity(intent);
                        keepinglogin();


                    }else {
                        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        button.startAnimation(shake);
                        Toast.makeText(getApplicationContext(), INVALIDUSER , Toast.LENGTH_SHORT).show();

                    }

                }

            }

            userName.setText("");
            password.setText("");

    }


    public void checkKeepLogin(String string){

        SharedPreferences sf = getSharedPreferences(PREFERENCESFILE, 0);
        SharedPreferences.Editor editor = sf.edit();
        editor.putString(KEEPINGLOGIN, string);
        editor.commit();

    }

    private void keepinglogin(){

        if(checkBox.isChecked()){

            checkKeepLogin("true");

        }else {
            checkKeepLogin("false");
            repositoryUser.removeUser(user.getId());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null){

            conn.close();

        }
    }
}
