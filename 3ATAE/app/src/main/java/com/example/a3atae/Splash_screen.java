package com.example.a3atae;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sarnava.textwriter.TextWriter;

import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class Splash_screen extends AppCompatActivity {
ImageView imgback;
GifImageView gifback;
TextView finaltext;
    private TextWriter textWriter;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imgback=findViewById(R.id.imgback);
        gifback=findViewById(R.id.gifback);
        finaltext=findViewById(R.id.textfinal);

        tache1();
       /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {



              /* Intent intent = new Intent(Splash_screen.this, Logn_in.class );
                startActivity(intent);
                finish();*/
            }
      /*  }, 2000);



    }*/
    public void tache1(){
        Thread th1=new Thread(){
            @Override
            public void run() {


                try {
                    sleep(1500);
                    //had runOn.. ila briti t3ml chi changement f layout
                    runOnUiThread(new Runnable() {

                        @Override

                        public void run() {
                          /*  Intent presentation=new Intent(getApplicationContext(),Introduce.class);
                            startActivity(presentation);
                            finish();*/

                            textWriter=findViewById(R.id.textWriter);

                            textWriter
                                    .setWidth(20)
                                    .setDelay(40)
                                    .setColor(Color.rgb(226,159,1))
                                    .setConfig(TextWriter.Configuration.INTERMEDIATE)
                                    .setSizeFactor(50f)
                                    .setLetterSpacing(50f)
                                    .setText("AATAE")
                                    .setListener(new TextWriter.Listener() {
                                        @Override
                                        public void WritingFinished() {

                                            gifback.setVisibility(View.VISIBLE);
                                            imgback.setVisibility(View.INVISIBLE);
                                            timer = new Timer();
                                            timer.schedule(new TimerTask() {
                                                @Override
                                                public void run() {

                                                    Intent intent = new Intent(Splash_screen.this, Logn_in.class );
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            },2000);
                                        }
                                    })
                                    .startAnimation();

                        }

                    });


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };
        th1.start();
    }

    /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

               Intent intent = new Intent(Splash_screen.this, Logn_in.class );
                startActivity(intent);
                finish();
            }
        },6000);


        textWriter=findViewById(R.id.textWriter);

        textWriter
                .setWidth(20)
                .setDelay(70)
                .setColor(R.color.yellow)
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setSizeFactor(50f)
                .setLetterSpacing(50f)
                .setText("AATAE")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {


                    }
                })
                .startAnimation();
    }*/

}
