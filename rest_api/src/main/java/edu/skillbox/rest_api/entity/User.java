package edu.skillbox.rest_api.entity;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;
    private String name;
    private String surname;
    private String patronymic;
    @Enumerated
    private Gender gender;
    private String phoneNumber;
    private String login;
    private String avatar;
    private String description;
    // private UUID adressId;
    private ArrayList<Subscription> subscriptions;
}
