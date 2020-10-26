package ru.chibisov.service.dto;

public class UserDto {

    /**
     * Уникальный идентификатор пользователя в системе
     */
    private Long id;

    /**
     * Роль пользователя системы
     */
    private String role;

    /**
     * Логин пользователя
     */
    private String name;

    /**
     * Хеш пароля
     */
    private String password;

    /**
     * Контактный телефон
     */
    private String phone;

    /**
     * Адрес эл. почты
     */
    private String email;


    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDto setRole(String role) {
        this.role = role;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
