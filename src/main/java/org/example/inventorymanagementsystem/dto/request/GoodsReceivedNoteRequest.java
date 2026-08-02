package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsReceivedNoteRequest {

    private String grnNumber;

    private Long purchaseOrderId;

    private String remarks;

    private List<GoodsReceivedNoteItemRequest> items;

}