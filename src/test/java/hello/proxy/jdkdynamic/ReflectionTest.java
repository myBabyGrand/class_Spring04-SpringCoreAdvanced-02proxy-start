package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello target = new Hello();

        //공통로직 1 시작
        log.info("start");
        log.info("result={}", target.callA());

        //공통로직 2 시작
        log.info("start");
        log.info("result={}", target.callB());
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //클래스 정보 획득
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //get callA method info
        Method methodA = classHello.getMethod("callA");
        Object result1 = methodA.invoke(target);
        log.info("result={}", result1);

        //get callB method info
        Method methodB = classHello.getMethod("callB");
        Object result2 = methodB.invoke(target);
        log.info("result={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        //클래스 정보 획득
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        dynamicCall(classHello.getMethod("callA"), target);
        dynamicCall(classHello.getMethod("callB"), target);
    }
    private void dynamicCall(Method method, Object target) throws Exception{
        log.info("start");
        Object result1 = method.invoke(target);
        log.info("result={}", result1);
    }


    @Slf4j
    static class Hello{
        public String callA(){
            log.info("callA");
            return "A";
        }
        public String callB(){
            log.info("callB");
            return "B";
        }
    }
}
