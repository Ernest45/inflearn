package hello.core;

import java.util.StringTokenizer;

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
        staticField2.num2 = 1500;

        System.out.println("staticField1.num2 = " + staticField1.num2); // 1500
        System.out.println("staticField2.num2 = " + staticField2.num2); // 1500

        String name1 = "kim coding";
        String name2 = "kim coding";

        String name3 = new String("kim coding");
        String name4 = new String("kim coding");

        boolean comparison1 = name1 == "kim coding"; //t
        boolean comparison2 = name1 == name2; // // t
        boolean comparison3 = name1 == name3; // f
        boolean comparison4 = name3 == name4; // f
        boolean comparison5 = name1.equals("kim coding"); //t
        boolean comparison6 = name1.equals(name3); // t
        boolean comparison7 = name3.equals(name4); // t
        System.out.println("comparison1 = " + comparison1);
        System.out.println("comparison2 = " + comparison2);
        System.out.println("comparison3 = " + comparison3);
        System.out.println("comparison4 = " + comparison4);
        System.out.println("comparison5 = " + comparison5);
        System.out.println("comparison6 = " + comparison6);
        System.out.println("comparison7 = " + comparison7);

        String str = "This is a string example using StringTokenizer";
        StringTokenizer tokenizer = new StringTokenizer(str);
        System.out.println(str);
        System.out.println();

        System.out.println("total tokens : " + tokenizer.countTokens());
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());

        }
        System.out.println("total tokens : " + tokenizer.countTokens());

        StringBuilder sb = new StringBuilder();
        sb.append("문자열 ").append("연결");
        String str1 = sb.toString();
        System.out.println(sb);
        System.out.println(str1);
        System.out.println();

        StringBuffer stringBuffer = new StringBuffer("java");
        System.out.println("문자열 = " + stringBuffer);
        System.out.println(stringBuffer.append(" programming"));
        System.out.println("append() 메서드 호출 후 문자열 : " + stringBuffer);
        System.out.println();

        StringBuffer stv1 = new StringBuffer();
        StringBuffer stv2 = new StringBuffer("java");
        System.out.println(stv1.capacity());
        System.out.println(stv2.capacity());

        StringBuffer stvv = new StringBuffer("java oracle");
        System.out.println("문자열 = " + stvv);
        System.out.println(stvv.delete(4, 8));
        System.out.println(stvv.deleteCharAt(1));
        System.out.println("delete 호출  문자열 = " + stvv);
        System.out.println();

        StringBuffer stx = new StringBuffer("java Programming");
        System.out.println("문자열 = " + stx);
        System.out.println(stx.insert(4, "Script"));
        System.out.println("insert 호출 후 문자열 = " + stx);
        System.out.println();

        System.out.printf("%s%n", "Hello JAVA"); // 줄 바꿈이 됩니다.
        System.out.printf("%s%n", "Kim" + "Coding");
        System.out.printf("%d%n", 3+6);
        System.out.printf("지금은 %s입니다", 2022 + "year"); // 자동 타입 변환이 일어납니다.
        System.out.printf("나는 %c%s입니다", '김', "코딩"); //여러 개의 인자를 넣을 수 있습니다.
    }


}

    class StaticExample {
        int num1 = 10;
        static int num2 = -10;

    }

class StaticField {
    int num1 = 10;
    static int num2 = 15;
}

