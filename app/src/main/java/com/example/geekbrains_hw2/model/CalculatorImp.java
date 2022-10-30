package com.example.geekbrains_hw2.model;

public class CalculatorImp implements Calculator{
    @Override
    public double perform(double arg1, double arg2, Operator operator) {
        switch (operator){

            case MULT:
                return arg1 * arg2;

            case SUB:
                return arg1 - arg2;

            case DIV:
                return arg1 / arg2;

            case ADD:
                return arg1 + arg2;

            case ROOT:
                return Math.sqrt(arg1);

            case SQUARE:
                return arg1*arg1;

            case ONEDIV:
                return 1/arg1;
        }

        return 0.0;
    }
}
