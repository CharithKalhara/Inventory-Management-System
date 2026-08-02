package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsReceivedNoteItemResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantityReceived;

}