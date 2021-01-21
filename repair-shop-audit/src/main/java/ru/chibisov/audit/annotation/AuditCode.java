package ru.chibisov.audit.annotation;

public enum AuditCode {
    //Материалы
    CREATE_MATERIAL("Создание материала"),
    UPDATE_MATERIAL("Обновление материала"),
    DELETE_MATERIAL("Удаление материала"),

    //Заявки
    CREATE_REQUEST("Создание заявки"),
    UPDATE_REQUEST("Обновление заявки"),
    DELETE_REQUEST("Удаление заявки"),

    //Поставщики
    CREATE_SUPPLIER("Создание поставщика"),
    UPDATE_SUPPLIER("Обновление поставщика"),
    DELETE_SUPPLIER("Удаление поставщика"),

    //Пользователи
    CREATE_USER("Создание пользователя"),
    UPDATE_USER("Обновление пользователя"),
    DELETE_USER("Удаление пользователя");

    private final String description;

    AuditCode(String description) {
        this.description = description;
    }

    public final String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name();
    }
}
