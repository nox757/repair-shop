package ru.chibisov.resource.dto.search;

public class UserSearchDto {

    /**
     * Логин пользователя
     */
    private String name;

    /**
     * Адрес эл. почты
     */
    private String email;

    /**
     * Телефон пользователя
     */
    private String phone;

    public String getName() {
        return name;
    }

    public UserSearchDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserSearchDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserSearchDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
