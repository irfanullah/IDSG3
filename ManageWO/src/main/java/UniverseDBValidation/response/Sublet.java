package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class Sublet {
    public String description;
    public String vendorNo;
    public String vendorName;
    public String purchaseOrderNo;
    public LocalDateTime expectedDate;
    public String expectedDateStr;
    public LocalDateTime completedDate;
    public String completedDateStr;
    public String taxCode;
    public String taxDesc;
    public String purchaseOrderComment;
    public String salesmanCode;
    public String salesmanDesc;
    public String invoiceNo;
    public SubletPricing required;
    public SubletPricing actuals;
}
