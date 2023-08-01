package com.study.section01.params;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.stream.Stream;

public class ParameterrizedTests {

    /* 테스트 시 여러 값들을 이용하여 검증을 해야하는 경우 모든 경우의 수만큼 테스트 메소드를 작성해야 한다.
     * parameterized-test는 @ParameterizedTest 어노테이션을 @Test 어노테이션을 대체하여 사용하며,
     * 이 경우 테스트 메소드에 매개변수를 선언할 수 있다. (일반적인 테스트 메소드는 매개변수 사용 불가)
     * 파라미터로 전달할 값의 목록만큼 반복적으로 테스트 메소드를 실행하며, 반복 실행 시마다 준비된 값 목록을 매개변수로 전달하여 테스트 코드를 실행하게 된다.
     * given, when than 패턴에서 테스트를 위한 데이터를 준비하는 단계인 given 단계를 생략하는 것이 가능함 */

    // 1. given: 검증을 위한 파라미터를 준비하는 단계
    // 2. when: 테스트를 진행할 메서드를 호출
    // 3. then: 실행 결과를 검증

    @DisplayName("홀수 짝수 판별 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, -1, 15, 123})
//얘 길이만큼 하나씩 인자로 뽑아서 실행시킨다?
    void testIsOdd(int number) {
        boolean result = NumberValidator.isOdd(number); //홀수값...
        Assertions.assertTrue(result);
    }


    @DisplayName("null 값 테스트")
    @ParameterizedTest
    @NullSource
    void testIsnull() { //테스트코드에서는 자료형 null을 가질 수 없음... 그래서 오류!!
        String input = null;
        boolean result = (input == null);

        Assertions.assertTrue(result);
    }

    @DisplayName("null 값 테스트2")
    @ParameterizedTest
    @NullSource
    void testIsnull2(String input) {
        boolean result = StringValidator.isNull(input);

        Assertions.assertTrue(result);
    }

    @DisplayName("empty 값 테스트")
    @ParameterizedTest
    @EmptySource
    void testisEmpty(String input) {
        boolean result = StringValidator.isEmpty(input);

        Assertions.assertTrue(result);
    }

    @DisplayName("blank 값 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void testIsBlank(String input) {
        boolean result = StringValidator.isBlank(input);

        Assertions.assertTrue(result);
    }

    @DisplayName("Month에 정의된 타입들이 1~12월 사이의 범위인지 테스트")
    @ParameterizedTest
    @EnumSource(Month.class)
    void testMonthValueisCollect(Month month) {
        boolean result = DateValidator.isCollect(month);

        Assertions.assertTrue(result);
    }

    @DisplayName("4월, 6월, 9월, 11월이 30일까진지 확인")
    @ParameterizedTest
    @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void testHasThirtyDayLong(Month month) {
        int verifyValue = 30;
        int actual = DateValidator.getLastDatyOf(month);

        Assertions.assertEquals(verifyValue, actual);
    }

    @DisplayName("2월, 4월, 6월, 9월, 11월을 제외한 달이 31일까진지 확인")
    @ParameterizedTest
    @EnumSource(
            value = Month.class,
            names = {"FEBRUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"},
            mode = EnumSource.Mode.EXCLUDE //enum속성에 포함하지 않는 애들을 month로 넘기겠다
    )
    void testHasThiyOneDaysLong(Month month) {
        int verifyValue = 31;
        int actual = DateValidator.getLastDatyOf(month);

        Assertions.assertEquals(verifyValue, actual);
    }

    @DisplayName("영문자를 대문자로 변경하는지 확인")
    @ParameterizedTest
    @CsvSource(
            value = {"test:TEST", "tEst:TEST", "JavaScript:JAVASCRIPT"},
            delimiter = ':' //구분자 설정
    )
    void testToUpperCase(String input, String verifyValue) {
        //input : test
        //verifyValue : TEST

        String actual = input.toUpperCase();
        //input의 값을 대문자로 변경 : TEST

        Assertions.assertEquals(verifyValue/*TEST*/, actual/*TEST*/);
    }

    @DisplayName("CSV 파일을 읽은 테스트 데이터를 테스트에 활용하는지 확인")
    @ParameterizedTest
    @CsvFileSource(resources = "/parameter-test-data.csv", numLinesToSkip = 1)
    void testToUpperCaseWithCSVFileDate(String input, String verifyValue) {
        String actual = input.toUpperCase();
        Assertions.assertEquals(verifyValue, actual);
    }


    private static Stream<Arguments> providerStringSource() {
        return Stream.of(
                Arguments.of("hello world", "HELLO WORLD"),
                Arguments.of("Javascript", "JAVASCRIPT"),
                Arguments.of("tEsT", "TEST")
        );
    }

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MethodSource("providerStringSource")
    private @interface MethodSourceStringSource {
    }

    @DisplayName("메소드 소스를 활용한 대문자 변환 테스트")
    @ParameterizedTest
    @MethodSourceStringSource
    void testToUpperCaseWithMethodSource(String input, String verifyValue) {
        String actual = input.toUpperCase();
        Assertions.assertEquals(verifyValue, actual);
    }

    @DisplayName("두 수를 더한 결과를 정상적으로 반환하는지 테스트")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2} 이 맞는지 확인")
    @ArgumentsSource(SumTwoNumbersArgumentsProvider.class)
    void testSumTwoNumbers(int num1, int num2, int num3) {
        int actual = Calculator.sumTwoNumbers(num1, num2);

        Assertions.assertEquals(num3, actual);
    }

    @DisplayName("암시적 변환 테스트")
    @ParameterizedTest(name = "[{0}] is 30 days long")
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void testAutoConverting(Month month) {
        int verifyValue = 30;
        int actual = DateValidator.getLastDatyOf(month);

        Assertions.assertEquals(verifyValue, actual);
    }

    @DisplayName("argument converter를 이용한 명시적 변환 테스트")
    @ParameterizedTest(name = "{0}은 {1}년 입니다.")
    @CsvSource({"2014/09/18,2014", "2021/10/13,2021"})
    void testCustomConverter(@ConvertWith(SlashyDateConverter.class) LocalDate date, int year) {
        int autual = date.getYear();

        Assertions.assertEquals(year, autual);
    }
}
