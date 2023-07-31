package section05.custom;

public class CustomAnnotationTests {

    @WindowsTest //클래스 이름
    void windCustom() {
        System.out.println("테스트");
    }

    @DevelopmentTest
    void devTest() {
        System.out.println("dev 테스트 좀 할게여~...");
    }

    @ProductionTest
    void proTest() {
        System.out.println("pro 테스트 좀 할게여~...");
    }

    @MacTest
    void macCustom() {
        System.out.println("맥에서만 테스트 할게요...");
    }

}
