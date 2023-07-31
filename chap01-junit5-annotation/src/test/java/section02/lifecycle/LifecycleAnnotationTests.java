package section02.lifecycle;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

public class LifecycleAnnotationTests {

    /* 적힌 흐름대로 실행됨
     * @BeforeAll -> @BeforeEach -> @Test -> @AfterEach -> @AfterAll */

    @BeforeAll //실행 전 딱 한 번만 실행되는 메소드
    public static void 테스트_실행전_딱한번_실행() {
        /* 테스트코드 실행 시 자원을 할당하는 경우 */
        System.out.println("BeforeAll_실행됨...");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("테스트마다 실행 전 실행됨....");
    }

    /* @Test, @RepeatedTest, @ParameterizedTest, @TestFactory
     * 위의 테스트 메소드가 실행되기 전 실행이 되며 주로 테스트 하기 전 필요한 목업 데이터를 미리 세팅해 줄 목적으로 사용한다.*/

    @Test
    void 고민영_엄살() {
        System.out.println("자꾸 쉬자고 하네...");
    }

    @Test
    void 챗_상우() {
        System.out.println("질문을 해주세요..... END..... ");
    }

    /* @Test, @RepeatedTest, @ParameterizedTest, @TestFactory
     * 실행된 이후 실행되며 주로 단위 테스트들이 수행된 이후 사용한 자원을 해제할 목적으로 사용한다.*/

    @AfterEach
    void afterEach() {
        System.out.println("테스트마다 종료시 실행됨...");
    }

    @AfterAll //테스트 종료 후 딱 한 번 실행되는 메소드
    public static void 테스트_종료후_딱한번_실행() {
        /* 테스트 코드 종료 시 자원을 해제할 때 사용 */
        System.out.println("AfterAll_실행됨...");
    }

}
