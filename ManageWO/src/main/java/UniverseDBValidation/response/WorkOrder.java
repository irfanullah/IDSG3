package UniverseDBValidation.response;

import java.time.LocalDateTime;

public class WorkOrder {
    public String workOrderNo;
    public LocalDateTime workOrderDate;
    public String workOrderDateStr;
    public String workOrderDateWeb;
    public String author;
    public String statusCode;
    public String statusDesc;
    public String workOrderLocation;
    public String workOrderLocationDesc;
    public String salesmanCode;
    public String salesmanDesc;
    public LocalDateTime promiseDateTime;
    public String promiseDateStr;
    public String promiseTimeStr;
    public Integer schedulePriorityCode;
    public String schedulePriorityDesc;
    public LocalDateTime appointmentDateTime;
    public String appointmentDateStr;
    public String appointmentTimeStr;
    public LocalDateTime inServiceDate;
    public String inServiceDateStr;
    public Double partsDiscount;
    public String categoryCode;
    public String categoryDesc;
    public String tagNo;
    public Double mileageIn;
    public Double mileageOut;
    public String mileageUnitCode;
    public String mileageUnitDesc;
    public LocalDateTime completeDate;
    public String completeDateStr;
    public LocalDateTime cancelDate;
    public String cancelDateStr;
    public String comments;
}
