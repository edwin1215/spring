package com.edwin.spring.web.jvm.dynType;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * 实战：掌控方法分派规则
 *
 * @author caojunming
 * @datetime 2020/9/6 12:16 AM
 */
public class ClassTest {

    public static void main(String[] args) {
        GrandFather son = new Son();
        son.thinking();
    }
}

class GrandFather {
    static void heartbeart() {
        System.out.println("beng beng!");
    }
    void thinking() {
        System.out.println("i am grandfather");
    }
}

class Father extends GrandFather {

    static void heartbeart() {
        System.out.println("beng beng!");
    }
    @Override
    void thinking() {
        System.out.println("i am father");
    }
}

class Son extends Father {
    void thinking() {
        // 请读者在这里填入适当的代码(不能修改其他地方的代码)
        // 实现调用祖父类的thinking()方法，打印"i am grandfather"
        try {
            MethodType mt = MethodType.methodType(void.class);
            Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImpl.setAccessible(true);
            MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class, "thinking", mt, getClass().getSuperclass());
            mh.invoke(this);
        } catch (Throwable e) {
            e.printStackTrace();
        }
//        try {
//            MethodType mt = MethodType.methodType(void.class);
//            MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, Father.class);
//            mh.invoke(this);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }

    }
}