package com.quizapp.quiz.core.utils.enums;

public class EnumUtil <T extends Enum<T>> {
    public final Class<T> enumType;

    private EnumUtil(Class<T> enumType) {
        this.enumType = enumType;
    }

    public static <T extends Enum<T>> T convert(Class<T> enumType, String field) {
        EnumUtil<T> enumConverter = new EnumUtil<>(enumType);
        return enumConverter.convert(field);
    }

    public T convert(String field) {
        try {
            return Enum.valueOf(enumType, field);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid parameter for enum " + enumType.getSimpleName() + ": " + field);
        }
    }
}
