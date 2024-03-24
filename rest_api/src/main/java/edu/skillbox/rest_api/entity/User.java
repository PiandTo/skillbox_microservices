package edu.skillbox.rest_api.entity;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user", schema = "user_schema")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_deleted <> true")
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    private String name;
    private String surname;
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phoneNumber;
    private String login;
    private String avatar;
    private String description;
    private Boolean isDeleted = Boolean.FALSE;
    @ManyToMany
    @JoinTable(
        schema = "user_schema",
        name = "user_subscription",
        joinColumns= @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="subscription_id")
    )
    private Set<User> users;
    @ManyToMany(mappedBy = "users")
    private Set<User> subscriptions;

}
