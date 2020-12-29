package ru.chibisov.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * Представление сущности заявка(заказ) на починку в системе
 */
@Entity
@Table(name = "request")
public class Request extends CreateAtIdentified implements Identifiable<Long> {

    private static final long serialVersionUID = 869367318036171304L;

    /**
     * Уникальный идентификатор заявки в системе
     */
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq_gen")
    @SequenceGenerator(name = "request_seq_gen", sequenceName = "request_request_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Описание требуемых работ
     */
    @Column
    private String description;

    /**
     * Текущий статус заявки
     */
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Комментарий к заявке
     */
    @Column
    private String comment;

    /**
     * Мастер, выполняющий заявку
     */
    @ManyToOne
    @JoinColumn(name = "repairer_id", referencedColumnName = "user_id")
    private User repairer;

    /**
     * Закзачик, создавший заявку
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private User customer;

    /**
     * Цена за заказ-заявку
     */
    @Column
    private BigDecimal amount;

    /**
     * Материал и количество, необходимые для починки
     */
    @OneToMany(mappedBy = "id.requestId", fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request request = (Request) o;

        if (!Objects.equals(description, request.description)) return false;
        if (status != request.status) return false;
        if (!Objects.equals(comment, request.comment)) return false;
        if (!Objects.equals(repairer, request.repairer)) return false;
        if (!Objects.equals(customer, request.customer)) return false;
        return Objects.equals(amount, request.amount);
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        return result;
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
