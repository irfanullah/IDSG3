package UniverseDBValidation.response;

import java.util.List;

public class Job {
    public String jobNo;
    public String complaint;
    public String cause;
    public String correction;
    public String billTypeCode;
    public String billTypeDesc;
    public String billTo;
    public String billToDesc;
    public String statusCode;
    public String statusDesc;
    public String taxCode;
    public String taxDesc;
    public String salesmanCode;
    public String salesmanDesc;
    public Double quotedAmount;
    public Double estimatedAmount;

    public Double requiredHours = 0.0;
    public Double actualHours = 0.0;
    public Double chargeHours = 0.0;

    public JobTotals actuals;
    public JobTotals required;

    public List<Part> parts;
    public List<Labor> labors;
    public List<Sublet> sublets;
    public List<Extras> extras;

    public List<G2Comment> comments;
    public List<JobEstimateComment> estimatedAmountComments;
}
