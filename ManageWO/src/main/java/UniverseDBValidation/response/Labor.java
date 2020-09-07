package UniverseDBValidation.response;

import java.time.LocalDateTime;

// TODO: This is remaining
public class Labor {
    public String LaborCode;
    public String Description;
    public String Type;
    public String TypeDesc;
    public String MechanicCode;
    public String MechanicDesc;
    public String SkillSetCode;
    public String SkillSetDesc;
    public String StatusCode;
    public String StatusDesc;
    public LocalDateTime LaborDate;
    public Double ChargeHours;
    public String TaxCode;
    public String TaxDesc;
    public String FaultCode;
    public String FaultDesc;
    public String SalesmanCode;
    public String SalesmanDesc;
    public LaborPricing Actuals;
    public LaborPricing Required;
}
