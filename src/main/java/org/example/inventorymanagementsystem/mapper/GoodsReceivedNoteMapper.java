package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.GoodsReceivedNoteItemRequest;
import org.example.inventorymanagementsystem.dto.request.GoodsReceivedNoteRequest;
import org.example.inventorymanagementsystem.dto.response.GoodsReceivedNoteItemResponse;
import org.example.inventorymanagementsystem.dto.response.GoodsReceivedNoteResponse;
import org.example.inventorymanagementsystem.entity.GoodsReceivedNote;
import org.example.inventorymanagementsystem.entity.GoodsReceivedNoteItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsReceivedNoteMapper {


    public GoodsReceivedNote toEntity(GoodsReceivedNoteRequest request) {

        return GoodsReceivedNote.builder()
                .grnNumber(request.getGrnNumber())
                .remarks(request.getRemarks())
                .build();
    }


    public GoodsReceivedNoteItem toItemEntity(
            GoodsReceivedNoteItemRequest request) {

        return GoodsReceivedNoteItem.builder()
                .quantityReceived(request.getQuantityReceived())
                .build();
    }


    public GoodsReceivedNoteResponse toResponse(
            GoodsReceivedNote grn) {


        List<GoodsReceivedNoteItemResponse> items =
                grn.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return GoodsReceivedNoteResponse.builder()
                .id(grn.getId())
                .grnNumber(grn.getGrnNumber())
                .purchaseOrderId(
                        grn.getPurchaseOrder().getId()
                )
                .poNumber(
                        grn.getPurchaseOrder().getPoNumber()
                )
                .receivedDate(
                        grn.getReceivedDate()
                )
                .remarks(
                        grn.getRemarks()
                )
                .items(items)
                .build();
    }


    public GoodsReceivedNoteItemResponse toItemResponse(
            GoodsReceivedNoteItem item) {

        return GoodsReceivedNoteItemResponse.builder()
                .id(item.getId())
                .productId(
                        item.getProduct().getId()
                )
                .productName(
                        item.getProduct().getName()
                )
                .quantityReceived(
                        item.getQuantityReceived()
                )
                .build();
    }


    public void updateEntity(
            GoodsReceivedNote grn,
            GoodsReceivedNoteRequest request) {

        grn.setGrnNumber(
                request.getGrnNumber()
        );

        grn.setRemarks(
                request.getRemarks()
        );
    }
}