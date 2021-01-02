package ru.chibisov.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель пользователя")
public class UserDto {

    @ApiModelProperty(value = "Идентификатор пользователя", example = "1")
    private Long id;

    @ApiModelProperty(value = "Роль пользователя", example = "CUSTOMER | REPAIRER", required = true)
    private String role;

    @ApiModelProperty(value = "Имя пользователя", example = "Валерьян Валера Валерьевич", required = true)
    private String name;

    @ApiModelProperty(value = "Хеш пароля", example = "d8578edf8458ce06fbc5bb76a58c5ca4", required = true)
    private String password;

    @ApiModelProperty(value = "Контактный телефон", example = "+79779922001", required = true)
    private String phone;

    @ApiModelProperty(value = "Адрес электронной почты", example = "test@test.ru", required = true)
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
