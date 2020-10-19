package trainingcamp.week1.exercise1;

public class Hello {

    public double bar() {
        int i = 3;
        int j = 4;
        double d = 2.0d;
        float f = 1.0f;
        char c = 'C';
        boolean b = true;

        i = i + j;
        i = i - 2;
        i = i * j;
        double di = i / 2.0d;

        if (b) {
            di = 10.0d;
        }

        if (c == 'B' || d == 2.0d || f == 1.0f) {
            di = 20.0d;
        }

        for (j = 0; j < 5; j++) {
            di += j;
        }

        j = 2;
        do {
            di++;
        } while (j-- == 0);

        return di;
    }

    public static void main(String[] args) {
        System.out.println(new Hello().bar());
    }
}
