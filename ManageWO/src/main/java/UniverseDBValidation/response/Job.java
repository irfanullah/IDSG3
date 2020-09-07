package UniverseDBValidation.response;

import java.util.List;

public class Job {
    public String JobNo;
    public String Complaint;
    public String Cause;
    public String Correction;
    public String BillTypeCode;
    public String BillTypeDesc;
    public String BillTo;
    public String BillToDesc;
    public String StatusCode;
    public String StatusDesc;
    public String TaxCode;
    public String TaxDesc;
    public String SalesmanCode;
    public String SalesmanDesc;
    public Double QuotedAmount;
    public Double EstimatedAmount;
    public Double RequiredHours;
    public Double ActualHours;
    public Double ChargeHours;
    public JobTotals Actuals;
    public JobTotals Required;
    public List<Part> Parts;
    public List<Labor> Labor;
    public List<Sublet> Sublets;
    public List<Extras> Extras;
    public List<G2Comment> Comments;
    public List<JobEstimateComment> EstimatedAmountComments;
}
