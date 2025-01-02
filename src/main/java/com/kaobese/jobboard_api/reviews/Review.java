package com.kaobese.jobboard_api.reviews;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Review {
    @Id
    private Long id;
}
