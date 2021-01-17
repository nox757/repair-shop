package ru.chibisov.audit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(value = {"id"})
@Entity
@Table(name = "audit_log")
public class AuditEntity implements Serializable {

    /**
     * Уникальный идентификатор события в таблице
     */
    @Id
    @Column(name = "audit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_log_seq_gen")
    @SequenceGenerator(name = "audit_log_seq_gen", sequenceName = "audit_log_audit_id_seq", allocationSize = 1)
    private Long id;

    /**
     * Идентификатор события
     */
    @Column
    private UUID eventId;

    /**
     * Уникальный код события
     */
    @Column
    private String auditCode;

    /**
     * Текущее соостояние события
     */
    @Column
    @Enumerated(EnumType.STRING)
    private EventAudit event;

    /**
     * Время соответствующее событию START
     */
    @Column
    private LocalDateTime timeStart;

    /**
     * Время соответствующее событию SUCCESS,FAILURE
     */
    @Column
    private LocalDateTime timeEnd;

    /**
     * Имя пользователя, выполняющего запрос
     */
    @Column
    private String userName;

    /**
     * Входящие параметры запроса
     */
    @Column
    private String params;

    /**
     * Возвращаемое значение результата выполнения запроса
     */
    @Column
    private String returnValue;

    public Long getId() {
        return id;
    }

    public AuditEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public AuditEntity setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
        return this;
    }

    public AuditEntity setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
        return this;
    }

    public UUID getEventId() {
        return eventId;
    }

    public AuditEntity setEventId(UUID eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getAuditCode() {
        return auditCode;
    }

    public AuditEntity setAuditCode(String auditCode) {
        this.auditCode = auditCode;
        return this;
    }

    public EventAudit getEvent() {
        return event;
    }

    public AuditEntity setEvent(EventAudit event) {
        this.event = event;
        return this;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public String getUserName() {
        return userName;
    }

    public AuditEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getParams() {
        return params;
    }

    public AuditEntity setParams(String params) {
        this.params = params;
        return this;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public AuditEntity setReturnValue(String returnValue) {
        this.returnValue = returnValue;
        return this;
    }

}
