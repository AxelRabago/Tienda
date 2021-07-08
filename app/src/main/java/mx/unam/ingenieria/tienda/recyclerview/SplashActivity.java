package mx.unam.ingenieria.tienda.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import mx.unam.ingenieria.tienda.LoginActivity;
import mx.unam.ingenieria.tienda.LoginAuth;
import mx.unam.ingenieria.tienda.R;

public class SplashActivity extends AppCompatActivity {

    private static final long DURACION_SPLASH=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //Para que la ventana solo sea vertical
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intAut= new Intent(getApplicationContext(), LoginAuth.class);
                startActivity(intAut);
                finish();
            }
        },DURACION_SPLASH);


    }
}