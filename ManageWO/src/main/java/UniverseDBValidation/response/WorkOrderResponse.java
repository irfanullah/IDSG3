package UniverseDBValidation.response;

import java.util.List;

public class WorkOrderResponse {
    public WorkOrder workOrder;
    public Customer customer;
    public Inventory inventory;
    public List<Job> jobs;
    public List<BillingInfoSummary> billingInfoSummaries;
    public SpecialOrder specialOrder;
    public List<PurchaseOrder> purchaseOrders;
}
