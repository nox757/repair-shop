package ru.chibisov.resource.dto.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Модель для поиска заявок")
public class RequestSearchDto {

    @ApiModelProperty(value = "Текущий статус заявки", example = "1")
    private String status;

    @ApiModelProperty(value = "Комментарий к заявке", example = "1")
    private String comment;

    @ApiModelProperty(value = "Имя мастера, выполняющего заявку", example = "Петр Петрович")
    private String nameRepairer;

    @ApiModelProperty(value = "Имя заказчика, создавшего заявку", example = "Иванов Иван")
    private String nameCustomer;

    @ApiModelProperty(value = "Цена договора заявки", example = "100.00")
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

    @Override
    public String toString() {
        return "RequestSearchDto{" +
                "status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", nameRepairer='" + nameRepairer + '\'' +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", amount=" + amount +
                '}';
    }
}
