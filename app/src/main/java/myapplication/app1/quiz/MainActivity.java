package myapplication.app1.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    TextView appname;
    Animation appanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appname = findViewById(R.id.app_name);

        //linking animation file
        appanim = AnimationUtils.loadAnimation(getApplicationContext()  , R.anim.fade_in);

        //setting animation listener to define what is to be done onstart , onend and onrepeat of animation
        appanim.setAnimationListener(this);

        //starting aniation
        appname.startAnimation(appanim);


    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //switching activity on animation end
        finish();
        Intent intent = new Intent(MainActivity.this , gamescreen.class);
        startActivity(intent);
    }
    @Override
    public void onAnimationRepeat(Animation animation) {

    }




}
