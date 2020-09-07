package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class Sublet {
    public String Description;
    public String VendorNo;
    public String VendorName;
    public String PurchaseOrderNo;
    public LocalDateTime ExpectedDate;
    public LocalDateTime CompletedDate;
    public String TaxCode;
    public String TaxDesc;
    public String PurchaseOrderComment;
    public String SalesmanCode;
    public String SalesmanDesc;
    public String InvoiceNo;
    public SubletPricing Required;
    public SubletPricing Actuals;
}
