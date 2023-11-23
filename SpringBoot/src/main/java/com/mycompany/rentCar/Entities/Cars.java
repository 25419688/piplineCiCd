package com.mycompany.rentCar.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String model;
    private int nb_doors;
    private int nb_places;
    private String address;
    private String description;
    private float price_per_day;
    private String registration_num;
    private String gearbox;
    @OneToOne(mappedBy = "cars")
    private Image image;




}
