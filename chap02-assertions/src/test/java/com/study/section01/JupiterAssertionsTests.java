package com.study.section01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JupiterAssertionsTests {

    @Test
    @DisplayName("더하기 기능 테스트")
    void testAssertEqualus() {

        /* 1. given: 검증을 위한 파라미터를 준비하는 단계
         * 2. when: 테스트를 진행할 메서드를 호출
         * 3. then: 실행 결과를 검증 */

        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        int first = 10;
        int second = 20;
        int expected = first + second;

        // 2. when: 테스트를 진행할 메서드를 호출
        Calculator calculator = new Calculator();
        int result = calculator.plusTwoNumbers(first, second);

        // 3. then: 실행 결과를 검증
        // -------- assertEquals(계산 결과가 정확한 값)
        // 3.1. 일반 리터럴 값
        Assertions.assertEquals(/*비교대상값*/expected, /*예상값*/result, /*실패했을 때*/"불일치한 경우 보여지는 메세지");
        // 3.2. 람다 표현식
        Assertions.assertEquals(/*비교대상값*/expected, /*예상값*/result, /*실패했을 때*/() -> "불일치한 경우 보여지는 메세지");
        // -------------------- expected와 result의 값이 같은지 비교한다?
    }


    @Test
    @DisplayName("인스턴스 동일성 비교 테스트")
    void testAssertNotEqualsWithInstances() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        Object obj1 = new Object();

        // 2. when: 테스트를 진행할 메서드를 호출
        Object obj2 = new Object();

        // 3. then: 실행 결과를 검증
        // hashcode, equals 오버라이드(?)
        Assertions.assertNotEquals(obj1, obj2);
    }


    @Test
    @DisplayName("null 인지 테스트")
    void testAssertNull() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        String str;

        // 2. when: 테스트를 진행할 메서드를 호출
        str = null;

        // 3. then: 실행 결과를 검증
        Assertions.assertNull(str);
    }


    @Test
    @DisplayName("null 아닌지 테스트")
    public void notNullMethod() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        String str;

        // 2. when: 테스트를 진행할 메서드를 호출
        str = "java"; //null을 줄 경우 에러 발생.

        // 3. then: 실행 결과를 검증
        Assertions.assertNotNull(str);
    }


    @Test
    @DisplayName("두 값이 같은지 확인")
    void testAssertTrue() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        int num1 = 10;
        int num2 = 10;

        // 2. when: 테스트를 진행할 메서드를 호출
        boolean result = num1 == num2;

        // 3. then: 실행 결과를 검증
        Assertions.assertTrue(result); //결과값이 boolean - true일 경우를 확인
    }


    @Test
    @DisplayName("두 값이 다른지 확인")
    void testAssertFalse() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        int num1 = 10;
        int num2 = 20;

        // 2. when: 테스트를 진행할 메서드를 호출
        boolean result = num1 == num2;

        // 3. then: 실행 결과를 검증
        Assertions.assertFalse(result); //결과값이 boolean - false 경우를 확인
        Assertions.assertNotEquals(num1, num2); //더 쓰기 편한 거~~ 알아서~~
    }


    @Test
    @DisplayName("동시에 여러가지 값에 대한 검증을 수행하는 경우 테스트")
    void testAssertAll() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        String firstName = "길동";
        String lastName = "홍";

        // 2. when: 테스트를 진행할 메서드를 호출
        Person person = new Person(firstName, lastName);

        // 3. then: 실행 결과를 검증
        Assertions.assertAll("그룹화된 테스트의 이름(실패시 보여짐)",
                () -> Assertions.assertEquals(firstName/*firstName이*/, person.getFirstName()/*여기의 값과 다를 경우*/, () -> "first Name이 잘못됨"),
                () -> Assertions.assertEquals(lastName/*lastName이*/, person.getLastName()/*여기의 값과 다를 경우*/, () -> "first Name이 잘못됨"));
    }


    @Test
    @DisplayName("인스턴스 타입의 대한 검증을 수행하는 경우")
    void tsetAssertType() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        String firstName = "길동";
        String lastName = "홍";

        // 2. when: 테스트를 진행할 메서드를 호출
        Person person = new Person(firstName, lastName);

        // 3. then: 실행 결과를 검증
        // 3.1. 그냥 표현식
        Assertions.assertInstanceOf(Person.class, person);
        // 3.2. 람다 표현식
        Assertions.assertAll(
                () -> Assertions.assertInstanceOf(Person.class, person),
                () -> Assertions.assertInstanceOf(Object.class, person));
    }


    @Test
    @DisplayName("void 메소드의 경우 exception 발생이 없이 정상적으로 동작하는지 테스트")
    void testAssertDoesNotThrow() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        int firstNum = 10;
        int secondNum = 3;

        // 2. when: 테스트를 진행할 메서드를 호출
        PositivenumberValidator positivenumberValidator = new PositivenumberValidator();

        // 3. then: 실행 결과를 검증
        Assertions.assertDoesNotThrow(
                () -> positivenumberValidator.checkDividableNumbers(firstNum, secondNum) /*에러가 발생되지 않는 경우에 true -> 실행 O*/
        );
    }


    @Test
    @DisplayName("void 메소드의 경우 exception이 발생하는지 테스트")
    void testAssertThrows() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        int firstnum = 10;
        int secondNum = 0;
        String errormessage = "0으로 나눌 수 없습니다.";

//        // 2. when: 테스트를 진행할 메서드를 호출
//        PositivenumberValidator validator = new PositivenumberValidator();
//
//        // 3. then: 실행 결과를 검증
//        Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.checkDividableNumbers(firstnum, secondNum)
//        );

        // 3. then: 실행 결과를 검증
//        Assertions.assertThrows(
//                Exception.class,
//                () -> validator.checkDividableNumbers(firstnum, secondNum)
//        );

        // 2. when: 테스트를 진행할 메서드를 호출
        PositivenumberValidator validator = new PositivenumberValidator();
        Exception exception = Assertions.assertThrows(
                Exception.class,
                () -> validator.checkDividableNumbers(firstnum, secondNum));

        Assertions.assertAll(
                () -> Assertions.assertInstanceOf(IllegalArgumentException.class, exception, () -> "예외 타입 일치하지 않음"),
                () -> Assertions.assertEquals(errormessage, exception.getMessage(), /*두 비교 값이 다를 경우에*/() -> "에러 메세지가 같지 않습니다.")
        );

    }

    @Test
    @DisplayName("예상 목록이 실제 목록과 일치하는지 확인")
    void testAssertLinesMatch() {
        // 1. given: 검증을 위한 파라미터를 준비하는 단계
        List<String> expected = Arrays.asList("java", "database", "spring");

        // 2. when: 테스트를 진행할 메서드를 호출
        List<String> actual = Arrays.asList("java", "database", "spring");

        // 3. then: 실행 결과를 검증
        Assertions.assertLinesMatch(expected, actual, ()-> "두 리스트의 값이 같지 않음");
    }

    // 1. given: 검증을 위한 파라미터를 준비하는 단계
    // 2. when: 테스트를 진행할 메서드를 호출
    // 3. then: 실행 결과를 검증

}
