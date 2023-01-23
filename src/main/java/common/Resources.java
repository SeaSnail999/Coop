package common;

import java.io.IOException;
import java.io.InputStream;

public class Resources {
    public static String readRecourse(String path) {
        InputStream in = Resources.class.getResourceAsStream(path);
        byte[] bytes = null;
        try {
            assert in != null;
            bytes = in.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert bytes != null;
        return new String(bytes);
    }
}
