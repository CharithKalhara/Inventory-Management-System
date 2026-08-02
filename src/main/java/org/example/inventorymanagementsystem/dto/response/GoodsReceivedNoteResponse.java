package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsReceivedNoteResponse {

    private Long id;

    private String grnNumber;

    private Long purchaseOrderId;

    private String poNumber;

    private LocalDate receivedDate;

    private String remarks;

    private List<GoodsReceivedNoteItemResponse> items;

}