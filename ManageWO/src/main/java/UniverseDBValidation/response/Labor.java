package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class Labor {
    public String laborCode;
    public String description;
    public String type;
    public String typeDesc;
    public String mechanicCode;
    public String mechanicDesc;
    public String skillSetCode;
    public String skillSetDesc;
    public String statusCode;
    public String statusDesc;
    public LocalDateTime laborDate;
    public String laborDateStr;
    public Double chargeHours;
    public String taxCode;
    public String taxDesc;
    public String faultCode;
    public String faultDesc;
    public String salesmanCode;
    public String salesmanDesc;
    public LaborPricing actuals;
    public LaborPricing required;
}
