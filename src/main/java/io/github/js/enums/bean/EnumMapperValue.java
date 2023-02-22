package io.github.js.enums.bean;

public class EnumMapperValue {

    private String code;
    private String title;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.code = enumMapperType.getCode();
        this.title = enumMapperType.getTitle();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "EnumMapperValue{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
