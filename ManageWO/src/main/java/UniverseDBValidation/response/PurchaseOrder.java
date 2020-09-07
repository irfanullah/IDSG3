package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class PurchaseOrder {
    public String PurchaseOrderNo;
    public String Type;
    public String Description;
    public String VendorNo;
    public String VendorName;
    public String JobNo;
    public LocalDateTime EntryDate;
    public Double Value;
    public LocalDateTime ExpectedDate;
    public LocalDateTime CompletedDate;
    public String OrderedBy;
    public String InvoiceNo;
    public String ReasonCode;
    public String ReasonDesc;
    public String ShipVia;
    public String ShipTerms;
    public String PayTerms;
}
