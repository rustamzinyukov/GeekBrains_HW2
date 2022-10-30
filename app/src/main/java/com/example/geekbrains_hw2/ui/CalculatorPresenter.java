package com.example.geekbrains_hw2.ui;

import com.example.geekbrains_hw2.model.Calculator;
import com.example.geekbrains_hw2.model.Operator;

import java.text.DecimalFormat;

public class CalculatorPresenter {

    private final CalculatorView view;
    private final Calculator calculator;

    public double argOne;
    public Double argTwo = null;
    private Operator selectedOperator;
    public Boolean isDotPressed = false;
    public Boolean isEqualPressed = false;
    public int counter = 0;
    private StringBuilder status = new StringBuilder();

    private final DecimalFormat formatter = new DecimalFormat("#.##");


    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {
        if (isEqualPressed == true){
            argTwo = 0.0;
            isEqualPressed = false;
            updateStatus();
        }

        if (isDotPressed ==false){
            if (argTwo == null){
                argOne = argOne*10 + digit;
                showFormatted(argOne);
            } else {
                argTwo = argTwo*10 + digit;
                showFormatted(argTwo);
            }
            updateStatus();
        } else {
            if (argTwo == null){
                counter+=1;
                argOne = argOne + digit/Math.pow(10,counter);
                showFormatted(argOne);
            } else {
                counter+=1;
                argTwo = argTwo + digit/Math.pow(10,counter);;
                showFormatted(argTwo);
            }
            isDotPressed = false;
            updateStatus();
        }
    }

    public void onOperatorPressed(Operator operator) {
        isDotPressed = false;
        counter = 0;

        if (operator!=Operator.ROOT && operator!=Operator.SQUARE && operator!=Operator.ONEDIV){
            if (selectedOperator!=null){
                argOne = calculator.perform(argOne,argTwo,selectedOperator);
                showFormatted(argOne);
                updateStatus();
            }
            argTwo = 0.0;
            selectedOperator = operator;
            updateStatus();
        } else {
            argOne = calculator.perform(argOne,0,operator);
            argTwo = null;
            selectedOperator = null;
            showFormatted (argOne);
            updateStatus();
        }

    }

    public void onPeriodPressed() {
        isDotPressed = true;
        updateStatus();
    }

    private void showFormatted(double value){
        view.showResult(formatter.format(value));
    }

    public void onEqualPressed() {
        isEqualPressed = true;
        argOne = (calculator.perform(argOne,argTwo,selectedOperator));
        showFormatted(argOne);
        argTwo = 0.0;
        updateStatus();
        isEqualPressed = false;
    }

    public void onAcPressed() {
        argOne = 0.0;
        argTwo = null;
        selectedOperator = null;
        showFormatted(argOne);
        updateStatus();
    }


    public void updateStatus(){
        status.setLength(0);
        status.append("ArgOne: " + argOne + "\n");
        status.append("ArgTwo: " + argTwo + "\n");
        if (selectedOperator!=null) {
            status.append("Selected Operator: " + selectedOperator.name() + "\n");
        } else {
            status.append("Selected Operator: null \n");
        };
        status.append("isEqualPressed: " + isEqualPressed + "\n");
        status.append("isPeriodPressed: " + isDotPressed + "\n");

        view.showInfo(status.toString());
    }

    public double getArgOne() {
        return argOne;
    }

    public Double getArgTwo() {
        return argTwo;
    }

    public Boolean getDotPressed() {
        return isDotPressed;
    }

    public Boolean getEqualPressed() {
        return isEqualPressed;
    }

    public int getCounter() {
        return counter;
    }


    public void setArgOne(double argOne) {
        this.argOne = argOne;
    }

    public void setArgTwo(Double argTwo) {
        this.argTwo = argTwo;
    }

    public void setDotPressed(Boolean dotPressed) {
        isDotPressed = dotPressed;
    }

    public void setEqualPressed(Boolean equalPressed) {
        isEqualPressed = equalPressed;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
