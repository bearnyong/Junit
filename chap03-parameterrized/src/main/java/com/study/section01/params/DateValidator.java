package com.study.section01.params;

import java.time.Month;

public class DateValidator {

    public static boolean isCollect(Month month) {
        //1월부터 12월 사이인지 테스트 하기...
        int monthValue = month.getValue();
        return monthValue >= 1 && monthValue <= 12;
    }

    public static int getLastDatyOf(Month month) {
        //1월부터 12월 마지막 날
        return month.maxLength();
    }
}
