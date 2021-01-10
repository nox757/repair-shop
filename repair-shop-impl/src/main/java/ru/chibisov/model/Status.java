package ru.chibisov.model;

/**
 * Возможные статусы заявки на починку {@link Request}
 * Черновик, Создана, В работе (у мастера), Расторгнута, Выполнена
 */
public enum Status {
    DRAFT,
    CREATED,
    WORKED,
    TERMINATED,
    CLOSED
}
