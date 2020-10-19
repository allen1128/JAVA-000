package trainingcamp.week1.exercise2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class CustomeClassLoader extends ClassLoader {
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
