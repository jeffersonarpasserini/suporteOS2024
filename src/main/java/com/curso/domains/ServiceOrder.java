package com.curso.domains;

import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;

import java.time.LocalDate;
import java.util.UUID;

public class ServiceOrder {

    private UUID id;
    private LocalDate starDate = LocalDate.now();
    private LocalDate endDate;
    private String titleOS;
    private String description;
    private OrderPriority orderPriority;
    private OrderStatus orderStatus;
    private Technician technician;
    private User user;


}
