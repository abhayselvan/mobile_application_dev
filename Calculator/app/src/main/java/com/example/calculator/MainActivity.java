package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, openBracket, closeBracket, delete, decimalPoint, equals, divide, multiply, plus, minus;
    TextView expression, solution;
    Boolean newExpression = false;


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
        decimalPoint = findViewById(R.id.decimal_point);

        delete = findViewById(R.id.delete);

        delete.setLongClickable(true);
        delete.setOnClickListener(view -> {
            String s = expression.getText().toString();
            s = (s.length() == 0)
                    ? ""
                    : (s.substring(0, s.length() - 1));
            expression.setText(s);
        });
        delete.setOnLongClickListener(view -> {
            expression.setText("");
            return false;
        });

        equals.setOnClickListener(view -> {
            String currExpression = expression.getText().toString();
            if (!Calculator.isValidExpression(currExpression)){
                solution.setText("Invalid expression");
            }
            else {
                Double answer = Calculator.findSolution(currExpression);
                solution.setText(String.valueOf(answer));
            }
            newExpression = true;
        });
    }

    public void buttonClicked(View view){
        if (newExpression) {
            expression.setText("");
            newExpression = false;
        }
        Button id = findViewById(view.getId());
        String value = id.getText().toString();
        if (value.equals("÷")) value = "/";
        else if (value.equals("x")) value = "*";
        expression.setText(expression.getText().toString() + value);
    }
}


