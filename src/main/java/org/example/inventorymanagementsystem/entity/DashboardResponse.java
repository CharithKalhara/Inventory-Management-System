import lombok.*;
import org.example.inventorymanagementsystem.dto.response.RecentInvoiceResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private Long totalProducts;

    private Long totalCustomers;

    private Long totalSuppliers;

    private Long totalWarehouses;

    private BigDecimal stockValue;

    private Long lowStockProducts;

    private List<RecentInvoiceResponse> recentInvoices;
}