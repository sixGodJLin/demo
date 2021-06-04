package com.example.linj.myapplication;

import java.text.DecimalFormat;

/**
 * @author JLin
 * @date 2019/6/12
 */
public class SW {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i <= 1920; i++) {
            System.out.println("<dimen name=\"px" + i + "\">" + df.format(((double) i) / 3) + "dp</dimen>");
        }
    }
}
