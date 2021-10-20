package com.example.calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Calculator {

    public static Boolean isValidExpression(String s){
        if (s == null || s.length() == 0) return false;

        if (s.length() == 1){
            if (Character.isDigit(s.charAt(0)))
                return true;
            else
                return false;
        }

        Set<Character> operators = new HashSet<>();
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

    public static Double findSolution(String s){
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

