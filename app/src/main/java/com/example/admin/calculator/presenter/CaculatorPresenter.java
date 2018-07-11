package com.example.admin.calculator.presenter;

import android.util.Log;

import com.example.admin.calculator.common.Gereral;
import com.example.admin.calculator.model.shared.MySharedPrecence;
import com.example.admin.calculator.view.CaculatorView;

import java.util.Arrays;
import java.util.Stack;

public class CaculatorPresenter implements CaculatorPresenterImp {

    private CaculatorView mCaculatorView;

    public CaculatorPresenter(CaculatorView caculatorView){
        this.mCaculatorView = caculatorView;
    }

    @Override
    public void transformExpressions(String expression) {
        String[] element = processString(expression);
        String[] elementMath = postfix(element);

        Stack <String> S = new Stack<String>();

        for (int i=0; i<elementMath.length; i++){
            char c =(char) elementMath[i].charAt(0);
            if (!isOperator(c)) S.push(elementMath[i]);
            else{
                double num = 0f;
                double num1 = Float.parseFloat(S.pop());
                double num2 = Float.parseFloat(S.pop());
                switch (c) {
                    case Gereral.OPERATOR_SUM : num = num2 + num1; break;
                    case Gereral.OPERATOR_SUB : num = num2 - num1; break;
                    case Gereral.OPERATOR_MUL : num = num2 * num1; break;
                    case Gereral.OPERATOR_DIV : num = num2 / num1; break;
                    case Gereral.OPERATOR_PERCENT : num = num2 % num1; break;
                    default:
                        break;
                }
                S.push(Float.toString((float)num));
            }
        }
        mCaculatorView.resultCaculator(S.pop());
    }

    public int priority(char c) {
        if (c == Gereral.OPERATOR_SUM || c == Gereral.OPERATOR_SUB) return 1;
        else if (c == Gereral.OPERATOR_MUL || c == Gereral.OPERATOR_DIV || c == Gereral.OPERATOR_PERCENT) return 2;
        else return 0;
    }

    public boolean isOperator(char c) {
        char operator[] = { Gereral.OPERATOR_SUM, Gereral.OPERATOR_SUB, Gereral.OPERATOR_MUL, Gereral.OPERATOR_GC, Gereral.OPERATOR_DN, Gereral.OPERATOR_MN, Gereral.OPERATOR_PERCENT };
        Arrays.sort(operator);
        if (Arrays.binarySearch(operator, c) > -1)
            return true;
        else return false;
    }

    public String[] postfix(String[] elementMath) {
        String s1 = "", E[];
        Stack<String> S = new Stack <String>();
        for (int i=0; i<elementMath.length; i++){
            char c = elementMath[i].charAt(0);

            if (!isOperator(c))
                s1 = s1 + " " + elementMath[i];
            else{
                if (c == Gereral.OPERATOR_MN) S.push(elementMath[i]);
                else{
                    if (c == Gereral.OPERATOR_DN){
                        char c1;
                        do{
                            c1 = S.peek().charAt(0);
                            if (c1 != Gereral.OPERATOR_MN) s1 = s1 + " " + S.peek();
                            S.pop();
                        }while (c1 != Gereral.OPERATOR_MN);
                    }
                    else{
                        while (!S.isEmpty() && priority(S.peek().charAt(0)) >= priority(c)){
                            s1 = s1 + " " + S.peek();
                            S.pop();
                        }
                        S.push(elementMath[i]);
                    }
                }
            }
        }
        while (!S.isEmpty()){
            s1 = s1 + " " + S.peek();
            S.pop();
        }
        E = s1.trim().split(" ");
        return E;
    }

    public String[] processString(String input) {
        String s1 = "", elementMath[] = null;
        input = input.trim();
        input = input.replaceAll("\\s+"," ");

        if (isOperator(input.charAt(0))){
            s1 = s1 + " " + Gereral.OPERATOR_0 + " ";
            for (int i=0; i<input.length(); i++){
                char c = input.charAt(i);
                if (!isOperator(c))
                    s1 = s1 + c;
                else s1 = s1 + " " + c + " ";
            }
        }else{
            for (int i=0; i<input.length(); i++){
                char c = input.charAt(i);
                if (!isOperator(c))
                    s1 = s1 + c;
                else s1 = s1 + " " + c + " ";
            }
        }

        s1 = s1.trim();
        s1 = s1.replaceAll("\\s+"," ");
        elementMath = s1.split(" ");
        return elementMath;
    }
}
