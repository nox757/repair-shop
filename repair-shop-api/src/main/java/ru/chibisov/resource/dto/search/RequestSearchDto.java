package ru.chibisov.resource.dto.search;

import java.math.BigDecimal;

public class RequestSearchDto {

    /**
     * Текущий статус заявки
     */
    private String status;

    /**
     * Комментарий к заявке
     */
    private String comment;

    /**
     * Имя мастер, выполняющий заявку
     */
    private String nameRepairer;

    /**
     * Имя закзачик, создавший заявку
     */
    private String nameCustomer;

    /**
     * Цена за заказ-заявку
     */
    private BigDecimal amount;

    public String getStatus() {
        return status;
    }

    public RequestSearchDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public RequestSearchDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getNameRepairer() {
        return nameRepairer;
    }

    public RequestSearchDto setNameRepairer(String nameRepairer) {
        this.nameRepairer = nameRepairer;
        return this;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public RequestSearchDto setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RequestSearchDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
}
