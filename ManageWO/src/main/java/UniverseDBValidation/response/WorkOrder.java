package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class WorkOrder {
    public String WorkOrderNo;
    public LocalDateTime WorkOrderDate;
    public String Author;
    public String StatusCode;
    public String StatusDesc;
    public String WorkOrderLocation;
    public String WorkOrderLocationDesc;
    public String SalesmanCode;
    public String SalesmanDesc;
    public LocalDateTime PromiseDateTime;
    public Integer SchedulePriorityCode;
    public String SchedulePriorityDesc;
    public LocalDateTime AppointmentDateTime;
    public LocalDateTime InServiceDate;
    public Double PartsDiscount;
    public String CategoryCode;
    public String CategoryDesc;
    public String TagNo;
    public Double MileageIn;
    public Double MileageOut;
    public String MileageUnitCode;
    public String MileageUnitDesc;
    public LocalDateTime CompleteDate;
    public LocalDateTime CancelDate;
}
