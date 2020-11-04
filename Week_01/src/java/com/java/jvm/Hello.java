package com.java.jvm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

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

    public static class CustomeClassLoader extends ClassLoader {
        public static void main(String[] args) throws Exception {
            Class<?> zlass = new CustomeClassLoader().loadClass("Hello.xlass");
            Method method = zlass.getMethod("hello");
            method.invoke(zlass.newInstance());
        }

        protected Class<?> findClass(String name) {
            try {
                InputStream input = CustomeClassLoader.class.getClassLoader().getResourceAsStream(name);
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                try {
                    byte[] buf = new byte[4096];
                    int bytesRead = 0;
                    while ((bytesRead = input.read(buf)) != -1) {
                        output.write(buf, 0, bytesRead);
                    }
                    byte[] existing = output.toByteArray();
                    byte[] modified = new byte[existing.length];
                    for (int i = 0; i < existing.length; i++) {
                        modified[i] = (byte) ~existing[i];
                    }
                    return defineClass("Hello", modified, 0, modified.length);
                } finally {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
