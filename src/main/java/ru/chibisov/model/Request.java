package ru.chibisov.model;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Представление сущности заявка(заказ) на починку в системе
 */
public class Request implements Identifiable<Long> {

    private static final long serialVersionUID = 869367318036171304L;

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
     * Мастер, выполняющий заявку
     */
    private User repairer;

    /**
     * Закзачик, создавший заявку
     */
    private User customer;

    /**
     * Цена за заказ-заявку
     */
    private BigDecimal amount;

    /**
     * Материал и количество, необходимые для починки
     */
    private Set<RequestMaterial> materials;

    public Request() {
        this.status = Status.DRAFT;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getRepairer() {
        return repairer;
    }

    public void setRepairer(User repairer) {
        this.repairer = repairer;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Set<RequestMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(Set<RequestMaterial> materials) {
        this.materials = materials;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", repairer=" + repairer.toString() +
                ", customer=" + customer.toString() +
                ", amount=" + amount +
                ", materials=" + materials +
                '}';
    }
}
