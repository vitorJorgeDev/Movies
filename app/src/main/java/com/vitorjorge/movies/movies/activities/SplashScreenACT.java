package com.vitorjorge.movies.movies.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.vitorjorge.movies.movies.R;
import com.vitorjorge.movies.movies.dataBase.Database;
import com.vitorjorge.movies.movies.domain.RepositoryUser;
import com.vitorjorge.movies.movies.model.User;
import com.vitorjorge.movies.movies.network.LoginAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreenACT extends AppCompatActivity {

    // Tempo que a imagem ficará visivel
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private Database database;
    private SQLiteDatabase conn;
    private RepositoryUser repositoryUser;
    private User userClass;
    private static String DATABASEINSSUE, USERNOTSAVED;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_act);

        DATABASEINSSUE = getResources().getString(R.string.tst_DatabaseProblema_Login);
        USERNOTSAVED = getResources().getString(R.string.tst_userNotSaved);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            System.exit(0);
        }

        try{
            database = new Database(this);
            conn = database.getWritableDatabase();
            repositoryUser = new RepositoryUser(conn);


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), DATABASEINSSUE, Toast.LENGTH_LONG).show();

            Log.i("DESCRITION", "-" + e.getMessage());

            e.printStackTrace();
        }

        runAnimation();
        searchUser();

    }


    public void searchUser() {

        final RestAdapter restadapter = new RestAdapter.Builder()
                .setEndpoint("http://www.mocky.io")
                .build();
        LoginAPI api = restadapter.create(LoginAPI.class);

        api.getCharacter(0,
                new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        // salvando no banco

                        userClass = user;

                        try{
                            if(userClass.getId() == 0){

                                repositoryUser.insert(userClass);

                            }else {

                            }

                        }catch (Exception e ){
                            Toast.makeText(getApplicationContext(), USERNOTSAVED, Toast.LENGTH_LONG).show();
                            Log.i("DESCRITION", "=" + e.getMessage());
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("DESCRICAO", "=" + error.getMessage());

                    }
                });
    }


    private void runAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animation_splash);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.ivSplashScreenId);

        if(iv != null){
            iv.clearAnimation();
            iv.startAnimation(anim);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenACT.this, LoginScreenACT.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreenACT.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null) {

            conn.close();

        }
    }
}
