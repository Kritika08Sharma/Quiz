package myapplication.app1.quiz;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class scorescreen extends AppCompatActivity {

    Button play , exitgame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorescreen);

        play = findViewById(R.id.playagain);
        exitgame = findViewById(R.id.exit);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(scorescreen.this , MainActivity.class);
                startActivity(intent);
            }
        });

        exitgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }
}
