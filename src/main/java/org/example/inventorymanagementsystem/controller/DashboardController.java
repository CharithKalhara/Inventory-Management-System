package org.example.inventorymanagementsystem.controller;


import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.response.DashboardResponse;
import org.example.inventorymanagementsystem.service.interfaces.DashboardService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {


    private final DashboardService service;


    @GetMapping
    public DashboardResponse dashboard(){

        return service.getDashboard();

    }

}