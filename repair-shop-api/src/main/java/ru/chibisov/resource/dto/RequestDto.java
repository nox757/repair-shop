package ru.chibisov.resource.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Set;

@ApiModel(description = "Модель заявки")
public class RequestDto {

    @ApiModelProperty(value = "Идентификатор заявки", example = "1")
    private Long id;

    @ApiModelProperty(value = "Описание требуемых работ", example = "1. Починить ремень 2. Подшить пальто")
    private String description;

    @ApiModelProperty(value = "Текущий статус заявки", example = "DRAFT|CREATED|WORKED|TERMINATED|CLOSED", required = true)
    private String status;

    @ApiModelProperty(value = "Комментарий к заявке", example = "Отклонена исполнителем")
    private String comment;

    @ApiModelProperty(value = "Идентификатор мастера-исполнителя", example = "4")
    private Long repairerId;

    @ApiModelProperty(value = "Имя мастера-исполнителя", example = "Иванов Иван Иваныч")
    private String nameRepairer;

    @ApiModelProperty(value = "Идентификатор заказчика", example = "1", required = true)
    private Long customerId;

    @ApiModelProperty(value = "Имя заказчика", example = "Петров Петр Петрович", required = true)
    private String nameCustomer;

    @ApiModelProperty(value = "Цена заявки на починку", example = "24.42")
    private BigDecimal amount;

    @ApiModelProperty(value = "Материал и количество, необходимые для выполнения заявки")
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

    public String getStatus() {
        return status;
    }

    public RequestDto setStatus(String status) {
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

    @Override
    public String toString() {
        return "RequestDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", repairerId=" + repairerId +
                ", nameRepairer='" + nameRepairer + '\'' +
                ", customerId=" + customerId +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", amount=" + amount +
                ", materials=" + materials +
                '}';
    }
}
