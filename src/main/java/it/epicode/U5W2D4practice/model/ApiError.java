package it.epicode.U5W2D4practice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private String message;
    private LocalDateTime dataErrore;
}