package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
            s = (s == null || s.length() == 0)
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
            if (!isValidExpression(currExpression)){
                solution.setText("Invalid expression");
            }
            else {
                Double answer = findSolution(currExpression);
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

    private boolean isValidExpression(String s) {
        if (s == null || s.length() == 0) return false;

        if (s.length() == 1){
            if (Character.isDigit(s.charAt(0)))
                return true;
            else
                return false;
        }

        Set <Character> operators = new HashSet<>();
        operators.add('+');
        operators.add('-');
        operators.add('/');
        operators.add('*');

        if (operators.contains(s.charAt(s.length()-1)) || s.charAt(s.length()-1) == '(' || s.charAt(s.length()-1) == '.')
            return false;

        int count = 0;

        for (int i = 0; i < s.length()-1; i++){
            char c = s.charAt(i);
            if (Character.isDigit(c) && s.charAt(i+1)=='(')
                return false;
            if (operators.contains(c) && (operators.contains(s.charAt(i+1)) || s.charAt(i+1) == ')'))
                return false;
            if (c == '/' && s.charAt(i+1) == '0')
                return false;
            if (c == '.' && s.charAt(i+1) == '.')
                return false;
            if (c == '(' && s.charAt(i+1) == ')')
                return false;
            if (c == '(')
                count ++;
            if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }

        if (s.charAt(s.length()-1) == ')')
            count --;

        return count == 0;
    }

    private Double findSolution(String s) {

        Stack<Double> nums = new Stack<>(); // the stack that stores numbers
        Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)

        String numString = "";
        double num = 0.0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || c == '.'){
                numString += c;
                while (i < s.length() - 1 && (Character.isDigit(s.charAt(i+1)) || s.charAt(i+1) == '.')){
                    numString += s.charAt(i+1);
                    i++;
                }
                num = Double.parseDouble(numString);
                numString = "";
                nums.push(num);
                num = 0.0;
            }
             else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(') nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())) nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static double operation(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0.0;
    }
    // helper function to check precedence of current operator and the uppermost operator in the ops stack
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

}


