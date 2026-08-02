package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.GoodsReceivedNoteRequest;
import org.example.inventorymanagementsystem.dto.response.GoodsReceivedNoteResponse;
import org.example.inventorymanagementsystem.service.interfaces.GoodsReceivedNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grns")
@RequiredArgsConstructor
public class GoodsReceivedNoteController {

    private final GoodsReceivedNoteService goodsReceivedNoteService;


    @PostMapping
    public GoodsReceivedNoteResponse create(
            @RequestBody GoodsReceivedNoteRequest request) {

        return goodsReceivedNoteService.create(request);
    }


    @PutMapping("/{id}")
    public GoodsReceivedNoteResponse update(
            @PathVariable Long id,
            @RequestBody GoodsReceivedNoteRequest request) {

        return goodsReceivedNoteService.update(id, request);
    }


    @GetMapping("/{id}")
    public GoodsReceivedNoteResponse getById(
            @PathVariable Long id) {

        return goodsReceivedNoteService.getById(id);
    }


    @GetMapping
    public List<GoodsReceivedNoteResponse> getAll() {

        return goodsReceivedNoteService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        goodsReceivedNoteService.delete(id);
    }
}