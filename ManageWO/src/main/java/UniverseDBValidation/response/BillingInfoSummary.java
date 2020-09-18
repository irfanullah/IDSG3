package UniverseDBValidation.response;

public class BillingInfoSummary {
    public String billTypeCode;
    public String billCode;
    public String billTo;
    public Boolean isDeductibleCustomer; //TODO: Is Deductible Customer is remaining
    public String billInvoiceNo;
    public Double partsTotal;
    public Double laborTotal;
    public Double subletTotal;
    public Double extrasTotal;
    public Double taxTotal;
    public Double total;
    public Double paidAmount;
}
