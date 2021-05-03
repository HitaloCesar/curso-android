package com.example.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.courtcounter.R;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Stack<Integer> stkA = new Stack<>();
    Stack<Integer> stkB = new Stack<>();

    int pointsA = 0;
    int pointsB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stkA.push(0);
        stkB.push(0);
    }

    public void reset(View view){
        pointsA = 0;
        pointsB = 0;
        stkA.clear();
        stkB.clear();
        stkA.push(0);
        stkB.push(0);
        displayForTeamA(pointsA);
        displayForTeamB(pointsB);
    }

    public void threePointsA(View view){
        pointsA+=3;
        stkA.push(pointsA);
        displayForTeamA(pointsA);
    }

    public void twoPointsA(View view){
        pointsA+=2;
        stkA.push(pointsA);
        displayForTeamA(pointsA);
    }

    public void freeThrowA(View view){
        pointsA+=1;
        stkA.push(pointsA);
        displayForTeamA(pointsA);
    }

    public void undoA(View view){
        pointsA = stkA.pop();
        if(!stkA.empty()) {
            pointsA = stkA.peek();
        }
        else{
            stkA.push(0);
            pointsA = 0;
        }
        displayForTeamA(pointsA);
    }

    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void threePointsB(View view){
        pointsB+=3;
        stkB.push(pointsB);
        displayForTeamB(pointsB);
    }

    public void twoPointsB(View view){
        pointsB+=2;
        stkB.push(pointsB);
        displayForTeamB(pointsB);
    }

    public void freeThrowB(View view){
        pointsB+=1;
        stkB.push(pointsB);
        displayForTeamB(pointsB);
    }

    public void undoB(View view){
        pointsB = stkB.pop();
        if(!stkB.empty()) {
            pointsB = stkB.peek();
        }
        else{
            stkB.push(0);
            pointsB = 0;
        }
        displayForTeamB(pointsB);
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}