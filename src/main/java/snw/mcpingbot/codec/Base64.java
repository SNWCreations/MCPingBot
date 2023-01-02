package snw.mcpingbot.codec;

import snw.mcpingbot.Main;

import java.io.*;
import java.nio.file.Files;
import java.security.SecureRandom;

public class Base64 {
    public static File decodeBase64String(String base64String) {
        if (base64String == null) {
            return null;
        }
        Base64FileFormatEnum value = Base64FileFormatEnum.valueOfHeader(base64String);
        if (value == null) {
            return null;
        }
        String tempBase64String = base64String.replace(value.getValue(), "").replace("\r\n", "");

        byte[] bytes = java.util.Base64.getMimeDecoder().decode(tempBase64String);
        File file;
        do {
            file = new File(Main.getInstance().getDataFolder(), randomString() + ".png");
        } while (file.exists());
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create file", e);
        }

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try (OutputStream out = Files.newOutputStream(file.toPath())) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
            throw new RuntimeException("Unable to write file", e);
        }

        return file;
    }

    private static String randomString() {
        char[] chars = "abcdef0123456789".toCharArray(); // I like 16 radix style.
        int size = 15; // actually, it is length
        StringBuilder builder = new StringBuilder();
        while (size-- > 0) {
            builder.append(chars[new SecureRandom().nextInt(chars.length)]);
        }
        return builder.toString();
    }
}
