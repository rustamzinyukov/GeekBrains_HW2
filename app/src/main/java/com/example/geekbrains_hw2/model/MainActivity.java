package com.example.geekbrains_hw2.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.geekbrains_hw2.R;
import com.example.geekbrains_hw2.ui.CalculatorPresenter;
import com.example.geekbrains_hw2.ui.CalculatorView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    public TextView resultTxt;
    public TextView infoTxt;
    public CalculatorPresenter presenter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null){
            presenter.argOne = savedInstanceState.getDouble("argOne",0.0);
            presenter.argTwo =savedInstanceState.getDouble("argTwo");
            presenter.isDotPressed = savedInstanceState.getBoolean("isDotPressed");
            presenter.isEqualPressed = savedInstanceState.getBoolean("isEqualPressed");
            presenter.counter = savedInstanceState.getInt("counter");

        }

        setContentView(R.layout.activity_main);


        resultTxt = findViewById(R.id.textView);
        infoTxt = findViewById(R.id.upperTextWindow);

        presenter = new CalculatorPresenter(this,new CalculatorImp());

        Map<Integer, Integer> digit = new HashMap<>();
        digit.put(R.id.button1, 1);
        digit.put(R.id.button2, 2);
        digit.put(R.id.button3, 3);
        digit.put(R.id.button4, 4);
        digit.put(R.id.button5, 5);
        digit.put(R.id.button6, 6);
        digit.put(R.id.button7, 7);
        digit.put(R.id.button8, 8);
        digit.put(R.id.button9, 9);
        digit.put(R.id.button0, 0);

        View.OnClickListener digitOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digit.get(view.getId()));
            }
        };

        findViewById(R.id.button1).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button2).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button3).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button4).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button5).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button6).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button7).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button8).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button9).setOnClickListener(digitOnClickListener);
        findViewById(R.id.button0).setOnClickListener(digitOnClickListener);


        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.buttonplus, Operator.ADD);
        operators.put(R.id.buttonminus, Operator.SUB);
        operators.put(R.id.buttonDiv, Operator.DIV);
        operators.put(R.id.buttonMult, Operator.MULT);
        operators.put(R.id.buttonSquare, Operator.SQUARE);
        operators.put(R.id.buttonSqrt, Operator.ROOT);
        operators.put(R.id.buttonDivX, Operator.ONEDIV);


        View.OnClickListener operatorOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.buttonplus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.buttonminus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.buttonDiv).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.buttonMult).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.buttonSqrt).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.buttonSquare).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.buttonDivX).setOnClickListener(operatorOnClickListener);

        findViewById(R.id.buttonPeriod).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPeriodPressed();
            }
        });

        findViewById(R.id.buttonEqual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onEqualPressed();
            }
        });

        findViewById(R.id.buttonAC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAcPressed();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("argOne", presenter.argOne);
        outState.putDouble("argTwo", presenter.argTwo);
        outState.putBoolean("isDotPressed", presenter.isDotPressed);
        outState.putBoolean("isEqualPressed", presenter.isEqualPressed);
        outState.putInt("counter", presenter.counter);

    }

    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }

    public void showInfo(String info){
        infoTxt.setText(info);

    }

}