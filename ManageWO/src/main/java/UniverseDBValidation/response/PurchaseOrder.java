package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class PurchaseOrder {
    public String purchaseOrderNo;
    public String type;
    public String description;
    public String vendorNo;
    public String vendorName;
    public String jobNo;
    public LocalDateTime entryDate;
    public Double value;
    public LocalDateTime expectedDate;
    public LocalDateTime completedDate;
    public String orderedBy;
    public String invoiceNo;
    public String reasonCode;
    public String reasonDesc;
    public String shipVia;
    public String shipTerms;
    public String payTerms;
}
