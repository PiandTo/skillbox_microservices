package edu.skillbox.rest_api.entity;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", schema = "user_schema")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_deleted <> true")
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    @Getter
    private String name;
    @Getter
    private String surname;
    @Getter
    private String patronymic;
    @Getter
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Getter
    private String phoneNumber;
    @Getter
    private String login;
    @Getter
    private String avatar;
    @Getter
    private String description;
    @Getter
    private Boolean isDeleted = Boolean.FALSE;
    
    @ManyToMany(fetch = FetchType.LAZY)
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
