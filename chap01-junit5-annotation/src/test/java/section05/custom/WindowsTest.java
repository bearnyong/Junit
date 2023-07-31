package section05.custom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //생명주기 runtime : 프로그램 종료시까지 동작 (.class: 컴파일 시점까지, .source: 자바 프로그램 시점까지)
@Target(ElementType.METHOD) //해당 어노테이션의 지정할 위치를 설정 (.METHOD: 메소드에 설정함(CustomAnnotationTests클래스 참고))
@EnabledOnOs(OS.WINDOWS)
@EnabledOnJre(value = JRE.JAVA_8)
@Test
public @interface WindowsTest {

}
