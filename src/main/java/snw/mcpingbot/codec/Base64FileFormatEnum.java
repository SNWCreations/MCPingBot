package snw.mcpingbot.codec;

public enum Base64FileFormatEnum {

    BASE64_FILETYPE_PNG("data:image/png;base64,"),
    BASE64_FILETYPE_JPG("data:image/jpeg;base64,"),
    BASE64_FILETYPE_JPEG("data:image/jpeg;base64,"),
    BASE64_FILETYPE_GIF("data:image/gif;base64,"),
    BASE64_FILETYPE_SVG("data:image/svg+xml;base64,"),
    BASE64_FILETYPE_ICO("data:image/x-icon;base64,"),
    BASE64_FILETYPE_BMP("data:image/bmp;base64,");

    Base64FileFormatEnum(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }

    public static Base64FileFormatEnum valueOfHeader(String base64) {
        if (base64 == null || base64.isEmpty()) return null;
        for (Base64FileFormatEnum value : values()) {
            if (base64.startsWith(value.getValue())) {
                return value;
            }
        }
        return null;
    }
}