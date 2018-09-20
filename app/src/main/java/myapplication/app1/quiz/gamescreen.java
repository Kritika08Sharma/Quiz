package myapplication.app1.quiz;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class gamescreen extends AppCompatActivity {

    TextView question , score ,timertext;
    Button choice1 , choice2 , choice3 , choice4;
    static int number = 0;
    int index = 0;
    int marks = 0;
    CardView card;
    //object of vibrator class to include vibrating phone functionality. first define permission in manifest.
    Vibrator vibe;
    CountDownTimer timer ;
    //array of images . R.drawable.image is of type int . therefore int array.
    int []factimagearray = {R.drawable.nile , R.drawable.ganges , R.drawable.everest , R.drawable.sahara,R.drawable.greenland};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        timertext = findViewById(R.id.timer);
        vibe = (Vibrator) gamescreen.this.getSystemService(Context.VIBRATOR_SERVICE);
        card = findViewById(R.id.card);

        setQuestion();
    }

    @Override
    public void onBackPressed()
    {
        final AlertDialog.Builder restartalert = new AlertDialog.Builder(gamescreen.this);
        View restartview = getLayoutInflater().inflate((R.layout.restartlayout),null);
        Button restartyes = (Button) restartview.findViewById(R.id.restartyes);
        Button restartno = (Button) restartview.findViewById(R.id.restartno);
        restartalert.setView(restartview);
        restartalert.setCancelable(false);
         final AlertDialog restartalertdialog = restartalert.create();
        restartyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index =0;
                marks = 0;
                number = 0;
                setQuestion();
                restartalertdialog.dismiss();

            }
        });
        restartno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartalertdialog.dismiss();
            }
        });
        restartalertdialog.show();
    }

//    public void timer()
//    {
//        timer = new CountDownTimer(16000,1000) {
//            @Override
//            public void onTick(long time) {
//                timertext.setText(""+ (time/1000));
//            }
//
//            @Override
//            public void onFinish() {
//
//                timertext.setText("0");
//                index =index+1;
//                number = number+1;
//                setQuestion();
//            }
//        };
//        timer.start();
//    }
    public String getQuestion(int i)
    {
        //array of questions
        String []questionArray = {"Which is the longest river in the world ?" , "Which is the biggest delta in the world ?" ,"Which is the highest mountain in the world ?" ,"Which is the biggest desert in the world ?","Which is the biggest island in the world ?" };
        return questionArray[i];
    }

    public String getAnswers(int i)
    {
        //array of correct answers
        String []correctAnswer = {"NILE" , "GANGES" , "MOUNT EVEREST", "SAHARA DESERT","GREENLAND"};
        return correctAnswer[i];
    }

    public String getChoices(int i , int j)
    {
        //Array of choices to be displayed.
        String [][]choices = {{"AMAZON","NILE","YANGTZE","GANGA"},{"GANGES","MEKONG","NIGER","INDUS"},{"MOUNT KILIMANJARO","K2","MOUNT EVEREST","NANGA PARBAT"},{"THAR DESERT","SAHARA DESERT","GOBI DESERT","ATACAMA DESERT"},{"MADAGASCAR","GREENLAND","NEW GUINEA","GREAT BRITAIN"}};
        return choices[i][j];
    }

    public String getFacts(int m)
    {
        //Array of facts
        String[] facts = {getString(R.string.nile),getString(R.string.ganges),getString(R.string.everest),getString(R.string.sahara),getString(R.string.greenland)};
        return facts[m];
    }
    public void delay()
    {
        //vibrate phone for 500 milliseconds
        vibe.vibrate(400);
        //to introduce a delay after vibrating
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                //executed after 1000 ms
                //creating customised alert dialog
                final AlertDialog.Builder alert;alert = new AlertDialog.Builder(gamescreen.this);
                //linking the layout of dialog to this java file because agar usme jo button hai usko access krna hai toh nahi ho payega. us button ki id ko ye gamescreen.xml mein dhundhega jo ki DNE. kynki setConetvIew mein gamescreen hai.
                View dialogView = getLayoutInflater().inflate(R.layout.dialoglayout,null);
                //dialogView.findViewById() because apan ne view is object mein retain kiya hai inflator se. baaki agar ye nahi lagaaya to vo current java ki hi xml mein search krega.
                TextView factcontent = (TextView) dialogView.findViewById(R.id.factcontent);
                Button closeBtn = (Button)dialogView.findViewById(R.id.dialogbutton);
                ImageView factimage = (ImageView) dialogView.findViewById(R.id.factimage) ;
                TextView correct = (TextView) dialogView.findViewById(R.id.correct);

                //custom view jo banaya hai dialog ka vo set krna
                alert.setView(dialogView);
                alert.setCancelable(false);
                //getting drawable image object.
                Drawable drawable = getResources().getDrawable(factimagearray[number]);
                factimage.setImageDrawable(drawable);
                factcontent.setText(getFacts(number));
                correct.setText(getAnswers(number));
                //create and dismiss alertdialog k saath kaam krte. ".builder ke object alert pe nai chalega"
                final AlertDialog alertDialog = alert.create();
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                //display alert dialog.
                alertDialog.show();
                number = number+1;
                index = 0;
                if (number<=4)
                {
                    //load flip animation
                    AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(gamescreen.this, R.animator.flip);
                    set.setTarget(card); // set the view you want to animate
                    set.start();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            setQuestioninText();
                        }

                    }, 500);
                }
                else{
                    closeBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                            Intent intent = new Intent(gamescreen.this , scorescreen.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }, 400);
    }

    public void setQuestioninText()
    {
        question.setText(getQuestion(number));
        choice1.setText(getChoices(number,index));
        choice2.setText(getChoices(number,index+1));
        choice3.setText(getChoices(number,index+2));
        choice4.setText(getChoices(number,index+3));
        //timer();
    }

    public void check(Button button)
    {
        String select = button.getText().toString();
        if (select == getAnswers(number))
        {
            marks = marks+5;
            score.setText("Score : " +marks);
            number = number+1;
            index = 0;
            if (number<=4)
            {
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(gamescreen.this, R.animator.flip);
                set.setTarget(card); // set the view you want to animate
                set.start();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setQuestioninText();
                    }

                }, 500);
            }
            else
            {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        finish();
                        Intent intent = new Intent(gamescreen.this , scorescreen.class);
                        startActivity(intent);
                        }

                }, 1000);
            }
        }
        else {
            //to load shake animation of button upon wrong choice.
            button.startAnimation(AnimationUtils.loadAnimation(gamescreen.this , R.anim.shake));
            delay(); }
    }

    public void setQuestion()
    {
        setQuestioninText();
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(choice1);

            }});
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(choice2);
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(choice3);
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(choice4);
            }
        });
    }

}
