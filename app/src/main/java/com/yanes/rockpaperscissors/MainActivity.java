package com.yanes.rockpaperscissors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    Button b_rock, b_paper, b_scissors;
    ImageView iv_cpu, iv_me;

    String myChoise, cpuChoise, result;

    Random r;
    int game_money;
    int total_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        game_money = intent.getIntExtra(RockPaperScissors.SGameMoney, 0);
        total_money = intent.getIntExtra(RockPaperScissors.STotalMoney, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv;
        tv = (TextView) findViewById(R.id.totalmoney);
        tv.setText("" + total_money);
        tv = (TextView) findViewById(R.id.gamemoney);
        tv.setText("" + game_money);


        iv_cpu=(ImageView) findViewById(R.id.iv_cpu);
        iv_me=(ImageView) findViewById(R.id.iv_me);

        b_rock=(Button)findViewById(R.id.b_rock);
        b_paper=(Button)findViewById(R.id.b_paper);
        b_scissors=(Button)findViewById(R.id.b_scissors);

        r=  new Random();

        b_rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            myChoise ="rock";
            iv_me.setImageResource(R.drawable.rock);
            calculate();
            }
        });

        b_paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myChoise ="paper";
                iv_me.setImageResource(R.drawable.paper);
                calculate();
                back();
            }
        });
        b_scissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myChoise ="scissors";
                iv_me.setImageResource(R.drawable.scissors);
                calculate();
                back();
            }
        });

    }
    public void back(){
        String str_total = Integer.toString(total_money);
        str_total = str_total.trim();
        RockPaperScissors.STotalMoney = str_total;

        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.putExtra(RockPaperScissors.STotalMoney, str_total);
        game_money = 0;
        String game = Integer.toString(game_money);
        RockPaperScissors.SGameMoney = game;
        intent1.putExtra(RockPaperScissors.SGameMoney, game);


        setResult(RESULT_OK, intent1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.finish();
            }
        }, 3000);

    }

    public void calculate(){
    int cpu= r.nextInt(3);
    if(cpu==0){
        cpuChoise= "rock";
        iv_cpu.setImageResource(R.drawable.rock);
    }else if(cpu ==1){
        cpuChoise="paper";
        iv_cpu.setImageResource(R.drawable.paper);
    }else if(cpu==2){
        cpuChoise= "scissors";
        iv_cpu.setImageResource(R.drawable.scissors);
    }
    if(myChoise.equals("rock") && cpuChoise.equals("paper")){
        result ="You lose";
    }
    if(myChoise.equals("rock") && cpuChoise.equals("scissors")){
            result ="You win";
        total_money += game_money * 2;

    }
    if(myChoise.equals("paper") && cpuChoise.equals("rock")){
            result ="You win";
        total_money += game_money * 2;

    }
    if(myChoise.equals("paper") && cpuChoise.equals("scissors")){
            result ="You lose";
    }
    if(myChoise.equals("scissors") && cpuChoise.equals("paper")){
            result ="You win";
        total_money += game_money * 2;

    }
    if(myChoise.equals("scissors") && cpuChoise.equals("rock")){
            result ="You lose";
    }
    if(myChoise.equals("scissors") && cpuChoise.equals("scissors")){
            result ="Draw";
        total_money += game_money;

    }
    if(myChoise.equals("rock") && cpuChoise.equals("rock")){
            result ="Draw";
        total_money += game_money;

    }
    if(myChoise.equals("paper") && cpuChoise.equals("paper")){
            result ="Draw";
        total_money += game_money;

    }

        Toast.makeText(MainActivity.this, result,Toast.LENGTH_SHORT).show();
    }

}
