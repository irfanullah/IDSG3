package UniverseDBValidation.response;

import java.util.List;

public class WorkOrderResponse {
    
    public WorkOrder WorkOrder;
    public Customer Customer;
    public Inventory Inventory;
    public List<Job> Jobs;
    public List<BillingInfoSummary> BillingInfoSummaries;
    public SpecialOrder SpecialOrder;
    public List<PurchaseOrder> PurchaseOrders;
    public RecordMetaData RecordMetaData;
    
}
