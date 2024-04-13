package com.inventory.inventoryservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable{

  @Column(name = "uuid", updatable = false)
  @Size(max = 120)
  protected String uuid = UUID.randomUUID().toString();

  @Column(name = "enabled")
  private Boolean enabled = true;

  @Column(name = "is_deleted")
  private Boolean deleted = false;

  @CreatedBy
  @Column(name = "au_created_by", updatable = false)
  protected Long createdBy;

  @CreatedDate
  @Column(name = "au_created_at", updatable = false)
  protected LocalDateTime createdAt;

  @LastModifiedBy
  @Column(name = "au_modified_by", insertable = false)
  protected Long lastModifiedBy;

  @LastModifiedDate
  @Column(name = "au_modified_at")
  protected LocalDateTime lastModifiedAt;
}
