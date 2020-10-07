package ru.chibisov.model;

import java.math.BigDecimal;
import java.util.Map;

public class Request implements Identifiable<Long> {

    private static final long serialVersionUID = 869367318036171304L;

    private Long id;
    private String description;
    private Status status;
    private String comment;
    private User repairer;
    private User customer;
    private BigDecimal amount;
    private Map<Material, Double> materials;

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

    public Map<Material, Double> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<Material, Double> materials) {
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
