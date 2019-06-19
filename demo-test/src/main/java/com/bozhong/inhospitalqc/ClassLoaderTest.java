package com.bozhong.inhospitalqc;

/**
 * @author JiaChang
 * @date 2019-04-20
 */
public class ClassLoaderTest {

    private static ClassLoaderTest instance = new ClassLoaderTest();

    public static int a;

    public static int b = 0;

    private ClassLoaderTest(){
        a++;
        b++;
    }

    public static ClassLoaderTest getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        ClassLoaderTest instance = ClassLoaderTest.getInstance();
        System.out.println(ClassLoaderTest.a);
        System.out.println(ClassLoaderTest.b);

    }

}
