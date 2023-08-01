package com.study.section02;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.LocalDateTimeAssert;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AssertJTest {

    @Test
    @DisplayName("문자열 테스트하기")
    void testStringValidation() {
        String expected = "hello world";
        String actual = new String(expected);

        Assertions.assertThat(actual)
                .isNotEmpty() // "", null 값 체크
                .isNotBlank() // "", null, " " 모두 값으로 체크하지 않음
                .contains("hello") //hello를 포함하는가?
                .doesNotContain("hahahoho") //hahahoho를 포함하지 않는가?
                .startsWith("h") //시작위치
                .endsWith("d") //끝위치
                .isEqualTo("hello world"); //actual가 hello world와 동일한 값인가?
    }

    @Test
    @DisplayName("숫자 테스트하기")
    void testIntegerValiation() {
        double pi = Math.PI;

        Double actual = Double.valueOf(pi);

        Assertions.assertThat(actual)
                .isPositive()
                .isGreaterThan(3) //3보다 큼?
                .isLessThan(4) //4보다 작음?
                .isEqualTo(Math.PI); //actual가 Math.PI와 같은가?
    }

    @Test
    @DisplayName("날짜 테스트하기")
    void testLocalDateTimeValidation() {
        String birthDay = "2014-09-18T16:42:00.000";

        LocalDateTime thatDay = LocalDateTime.parse(birthDay);

        Assertions.assertThat(thatDay)
                .hasYear(2014)
                .hasMonthValue(9)
                .hasDayOfMonth(18)
                .isBetween("2014-01-01T00:00:00.000", "2014-12-31T23:59:59.999")
                .isBefore(LocalDateTime.now());
    }

    @Test
    @DisplayName("예외 테스트하기")
    void testExceptionValidation() {
        Throwable thorw = AssertionsForClassTypes.catchThrowable(
                () -> {
                    throw new IllegalArgumentException("잘못된 파라미터를 입력하였습니다.");
                });

        Assertions.assertThat(thorw)
                .isInstanceOf/*타입비교*/(IllegalArgumentException.class) //매개변수로 넘겨진 클래스형과 대생의 타입이 같은가?
                .hasMessageContaining("파라미터"); //파라미터라는 매개변수를 메세지가 포함하고 있는가?
    }

    @Test
    @DisplayName("예외 테스트하기2")
    void testEceptionValidation2() {
//        Assertions.assertThatThrownBy(
//                () -> {throw new IllegalArgumentException("잘못된 파라미터를 입력하였습니다.");})
//                .isInstanceOf/*타입비교*/(IllegalArgumentException.class) //매개변수와 넘겨진 클래스형과 대생의 타입이 같은가?
//                .hasMessageContaining("잘못된");

        /* 자주 사용하는 예외처리에 대한 정의된 함수를 제공한다.
         * 1. assertThatNullPointerException
         * 2. assertThatillegalArgumentException
         * 3. assertThatIllegalStateException
         * 4. assertThatIOException */
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(
                        () -> {
                            throw new IllegalArgumentException("잘못된 파라미터를 입력하였습니다.");
                        }).withMessageContaining("파라미터");
    }

    @Test
    void test() {
        Throwable throwable = new IllegalArgumentException();

        boolean result = (throwable instanceof IllegalArgumentException);

        org.junit.jupiter.api.Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("예외를 던지지 않는 경우 테스트하기")
    void testNoneException() {
        Assertions.assertThatCode(
                () -> {
                    throw new IllegalArgumentException("파라미터 문제");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("예외를 던지지 않는 경우 테스트하기2")
    void testNoneException2() {
        Assertions.assertThatCode(
                () -> {
                    System.out.println("안녕 예외가 없어 나는");
                }
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("filltering assertions 테스트하기")
    void testFilteringAssertions() {
        Member member1 = new Member(1, "user01", "홍길동", 20);
        Member member2 = new Member(2, "user02", "김형통", 16);
        Member member3 = new Member(3, "user03", "강소임", 40);
        Member member4 = new Member(4, "user04", "이단비", 19);

        List<Member> members = Arrays.asList(member1, member2, member3, member4);

        Assertions.assertThat(members)
                .filteredOn(m -> m.getAge() > 20)
                .containsOnly(member3); //member3의 age가 20보다 커?
    }

    @Test
    @DisplayName("객체의 ㅍ로퍼티 검증 테스트하기")
    void testPropertyValidation() {
        Member member1 = new Member(1, "user01", "홍길동", 20);
        Member member2 = new Member(2, "user02", "김형통", 16);
        Member member3 = new Member(3, "user03", "강소임", 40);
        Member member4 = new Member(4, "user04", "이단비", 19);

        List<Member> members = Arrays.asList(member1, member2, member3, member4);

        Assertions.assertThat(members)
                .filteredOn("age", 20)
                .containsOnly(member1);
    }

    @Test
    @DisplayName("프로퍼티 추출 테스트하기")
    void testExtractProperty() {
        Member member1 = new Member(1, "user01", "홍길동", 20);
        Member member2 = new Member(2, "user02", "김형통", 16);
        Member member3 = new Member(3, "user03", "강소임", 40);
        Member member4 = new Member(4, "user04", "이단비", 19);

        List<Member> members = Arrays.asList(member1, member2, member3, member4);

        List<String> expected = Arrays.asList("홍길동", "김형통", "강소임", "이단비");

        Assertions.assertThat(members)
                .extracting("name", String.class) //member에 있는 name 속성들을 가져와서 String 타입으로 맞추기
                .containsAll(expected); //expected의 값이 member의 name 속성들과 같은지 비교하기?
    }

    @Test
    @DisplayName("튜플로 추출하여 테스트하기")
    void testExtractPropertyTuple() {
        Member member1 = new Member(1, "user01", "홍길동", 20);
        Member member2 = new Member(2, "user02", "김형통", 16);
        Member member3 = new Member(3, "user03", "강소임", 40);
        Member member4 = new Member(4, "user04", "이단비", 19);

        List<Member> members = Arrays.asList(member1, member2, member3, member4);

        Assertions.assertThat(members)
                .extracting("name", "age")
                .contains(
                        Tuple.tuple("홍길동", 20),
                        Tuple.tuple("김형통", 16)
                );
    }
}
