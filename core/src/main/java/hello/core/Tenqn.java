package hello.core;

public class Tenqn {
    public static void main(String[] args) {
        StaticExample staticExample = new StaticExample();
        System.out.println("인스턴스 변수 = " + staticExample.num1);
        System.out.println("스태틱 변수 = " + StaticExample.num2);

        StaticField staticField1 = new StaticField();
        StaticField staticField2 = new StaticField();

        staticField1.num1 = 100;
        staticField2.num1 = 1000;
        System.out.println("staticField1.num1 = " + staticField1.num1); // 100
        System.out.println("staticField2.num1 = " + staticField2.num1); // 1000

        staticField1.num2 = 150;
//        staticField2.num2 = 1500;

        System.out.println("staticField1.num2 = " + staticField1.num2); // 1500
        System.out.println("staticField2.num2 = " + staticField2.num2); // 1500


    }}

    class StaticExample {
        int num1 = 10;
        static int num2 = -10;

    }

class StaticField {
    int num1 = 10;
    static int num2 = 15;
}

