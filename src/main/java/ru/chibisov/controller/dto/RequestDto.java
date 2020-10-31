package ru.chibisov.controller.dto;

import ru.chibisov.model.Status;

import java.math.BigDecimal;
import java.util.Set;

public class RequestDto {

    /**
     * Уникальный идентификатор заявки в системе
     */
    private Long id;

    /**
     * Описание требуемых работ
     */
    private String description;

    /**
     * Текущий статус заявки
     */
    private Status status;

    /**
     * Комментарий к заявке
     */
    private String comment;

    /**
     * ИД мастера выполняюшего заявку
     */
    private Long repairerId;

    /**
     * Имя мастер, выполняющий заявку
     */
    private String nameRepairer;

    /**
     * ИД закзачика, создавшего заявку
     */
    private Long customerId;

    /**
     * Имя закзачик, создавший заявку
     */
    private String nameCustomer;

    /**
     * Цена за заказ-заявку
     */
    private BigDecimal amount;

    /**
     * Материал и количество, необходимые для починки
     */
    private Set<RequestMaterialDto> materials;


    public Long getId() {
        return id;
    }

    public RequestDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public RequestDto setStatus(Status status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public RequestDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Long getRepairerId() {
        return repairerId;
    }

    public RequestDto setRepairerId(Long repairerId) {
        this.repairerId = repairerId;
        return this;
    }

    public String getNameRepairer() {
        return nameRepairer;
    }

    public RequestDto setNameRepairer(String nameRepairer) {
        this.nameRepairer = nameRepairer;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public RequestDto setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public RequestDto setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RequestDto setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Set<RequestMaterialDto> getMaterials() {
        return materials;
    }

    public RequestDto setMaterials(Set<RequestMaterialDto> materials) {
        this.materials = materials;
        return this;
    }
}
