package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, openBracket, closeBracket, delete, decimalPoint, equals, divide, multiply, plus, minus;
    TextView expression, solution;
    String newExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = findViewById(R.id.expression);
        solution = findViewById(R.id.solution);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        openBracket = findViewById(R.id.open_bracket);
        closeBracket = findViewById(R.id.close_bracket);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        equals = findViewById(R.id.equals);
        delete = findViewById(R.id.delete);
        decimalPoint = findViewById(R.id.decimal_point);

    }

    public void buttonClicked(View view){
        switch (view.getId()){
            case R.id.one:
                newExpression = expression.getText().toString() + "1";
                expression.setText(newExpression);
                break;
            case R.id.two:
                newExpression = expression.getText().toString() + "2";
                expression.setText(newExpression);
                break;
            case R.id.three:
                newExpression = expression.getText().toString() + "3";
                expression.setText(newExpression);
                break;
            case R.id.four:
                newExpression = expression.getText().toString() + "4";
                expression.setText(newExpression);
                break;
            case R.id.five:
                newExpression = expression.getText().toString() + "5";
                expression.setText(newExpression);
                break;
            case R.id.six:
                newExpression = expression.getText().toString() + "6";
                expression.setText(newExpression);
                break;
            case R.id.seven:
                newExpression = expression.getText().toString() + "7";
                expression.setText(newExpression);
                break;
            case R.id.eight:
                newExpression = expression.getText().toString() + "8";
                expression.setText(newExpression);
                break;
            case R.id.nine:
                newExpression = expression.getText().toString() + "9";
                expression.setText(newExpression);
                break;
            case R.id.open_bracket:
                newExpression = expression.getText().toString() + "(";
                expression.setText(newExpression);
                break;
            case R.id.close_bracket:
                newExpression = expression.getText().toString() + ")";
                expression.setText(newExpression);
                break;
            case R.id.minus:
                newExpression = expression.getText().toString() + "-";
                expression.setText(newExpression);
                break;
            case R.id.plus:
                newExpression = expression.getText().toString() + "+";
                expression.setText(newExpression);
                break;
            case R.id.divide:
                newExpression = expression.getText().toString() + "/";
                expression.setText(newExpression);
                break;
            case R.id.multiply:
                newExpression = expression.getText().toString() + "*";
                expression.setText(newExpression);
                break;
            case R.id.decimal_point:
                newExpression = expression.getText().toString() + ".";
                expression.setText(newExpression);
                break;
            case R.id.delete:
                newExpression = expression.getText().toString() + "0";
                expression.setText(newExpression);
                break;
            case R.id.equals:
                newExpression = expression.getText().toString() + "0";
                expression.setText(newExpression);
                break;
        }
    }



}