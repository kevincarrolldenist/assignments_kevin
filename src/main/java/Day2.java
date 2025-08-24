public class Day2 {
    public static void main(String[] args) {
        // Arithmetic Operators
//        int a = 10;
//        int b = 5;
//        System.out.println("Addition: " + (a + b)); // 15
//        System.out.println("Subtraction: " + (a - b)); // 5
//        System.out.println("Multiplication: " + (a * b)); // 50
//        System.out.println("Division: " + (a / b)); // 2
//        System.out.println("Modulus: " + (a % b)); // 0
//
//        // Relational Operators
//        System.out.println("Is a greater than b? " + (a > b)); // true
//        System.out.println("Is a less than b? " + (a < b)); // false
//        System.out.println("Is a equal to b? " + (a == b)); // false
//
//        // Logical Operators
//        boolean x = true;
//        boolean y = false;
//        System.out.println("x AND y: " + (x && y)); // false
//        System.out.println("x OR y: " + (x || y)); // true
//        System.out.println("NOT x: " + (!x)); // false
//
//        // Bitwise Operators
//        int m = 5; // 0101 in binary
//        int n = 3; // 0011 in binary
//        System.out.println("Bitwise AND: " + (m & n)); // 1 (0001)
//        System.out.println("Bitwise OR: " + (m | n)); // 7 (0111)
//        boolean isRaining = true;
//        boolean donehomework = false;
//        if(isRaining==false && donehomework==true) {
//            System.out.println("can play outside");
//        }
//        else {
//            System.out.println("cannot play outside");
//        }
//        Integer i=100;
//        if(i instanceof Integer) {
//            System.out.println("i is an instance of Integer");
//        } else {
//            System.out.println("i is not an instance of Integer");
//        }
//        String o=(i instanceof Integer) ? "i is an instance of Integer" : "i is not an instance of Integer";
//        System.out.println(o);
//        Integer sampledata=2;
//        System.out.println(sampledata << 2);
//        Integer lights=0b0100;
//        Integer checker=0b0100;
//        Integer result= lights & checker;
//        if(result==4)
//        {
//            System.out.println("3rd light is on");
//        }
//        else
//        {
//            System.out.println("3rd light is off");
//        }
        Integer file=0b0101;
        Integer c1=(file>>1) & 1;
        if(c1!=1)
        {
            Integer res=file | 0b0010;
            System.out.println("write permission added"+ " "+Integer.toBinaryString(res));
        }
        else
        {
            System.out.println("write permission already exists");
        }

    }
}
