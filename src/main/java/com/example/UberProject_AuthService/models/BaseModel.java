package com.example.UberProject_AuthService.models;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass

/*
@MappedSuperClass is used to specify that this class acts as parent class for the db.
->no table is created for this entity
->a table is created for the classes extending this parent class
 */
@Getter
@Setter
public abstract class BaseModel {

    @Id // this says that Id is our primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// this strategy uses auto increment property for Id
    protected Long Id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate // only stores time stamp of object creation
    protected Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // stores time stamp of last update of the object
    protected Date updatedAt;
}