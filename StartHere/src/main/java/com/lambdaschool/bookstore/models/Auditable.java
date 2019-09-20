package com.lambdaschool.bookstore.models;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class Auditable
{
    @ApiModelProperty(name = "createdBy", value = "How the table was created", required = false, example = "SYSTEM")
    @CreatedBy
    protected String createdBy;

    @ApiModelProperty(name = "createdDate", value = "Date and time the table item was created at",
            required = false)
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

    @ApiModelProperty(name = "lastModifiedBy", value = "What the table item was last modified by",
            required = false, example = "SYSTEM")
    @LastModifiedBy
    protected String lastModifiedBy;

    @ApiModelProperty(name = "lastModifiedDate", value = "When the table item was last modified",
            required = false)
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;
}
