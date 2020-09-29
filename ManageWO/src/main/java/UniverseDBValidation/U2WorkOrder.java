package UniverseDBValidation;

import UniverseDBValidation.response.*;
import org.apache.commons.text.StringEscapeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class U2WorkOrder {

    private final HashMap<String, String> mileageTypeMap;
    private final HashMap<String, String> billTypeMap;
    private final HashMap<Integer, String> schedulePriorityTypeMap;
    private final HashMap<String, String> laborStatusMap;
    private List<String> systemConfigurations;

    // Configs
    private boolean calcWoTaxAtLineLevel;
    private String billCodesForEstTotalAmt;
    private final boolean estimateMode = false;
    private boolean hidePartCost;
    private boolean useG2;

    // Custom Client
    private boolean marineMax;

    private final U2RestApi u2Api;

    public U2WorkOrder(String u2RestApiBaseUrl, String u2RestApiToken){
        mileageTypeMap = new HashMap<>();
        mileageTypeMap.put("M", "Miles");
        mileageTypeMap.put("K", "Kilometers");
        mileageTypeMap.put("H", "Hours");

        billTypeMap = new HashMap<>();
        billTypeMap.put("E", "External");
        billTypeMap.put("I", "Internal");
        billTypeMap.put("W", "Warranty");

        schedulePriorityTypeMap = new HashMap<>();
        schedulePriorityTypeMap.put(1, "High");
        schedulePriorityTypeMap.put(2, "Regular");
        schedulePriorityTypeMap.put(3, "Low");

        laborStatusMap = new HashMap<>();
        laborStatusMap.put("0", "U");
        laborStatusMap.put("1", "S");
        laborStatusMap.put("2", "I");
        laborStatusMap.put("3", "P");
        laborStatusMap.put("4", "HP");
        laborStatusMap.put("5", "C");
        laborStatusMap.put("6", "HS");
        laborStatusMap.put("7", "HPA");

        u2Api = new U2RestApiImpl(u2RestApiBaseUrl, u2RestApiToken);
    }

    public WorkOrderResponse getWorkOrderResponse(int accountId, String location, String workOrderNumber) {

        systemConfigurations = getSystemConfigurations(accountId, location);

        calcWoTaxAtLineLevel = systemConfigurations.get(51).equalsIgnoreCase("Y");
        billCodesForEstTotalAmt = systemConfigurations.get(109);
        hidePartCost = systemConfigurations.get(153).equalsIgnoreCase("Y");
        useG2 = systemConfigurations.get(111).equalsIgnoreCase("Y");

        marineMax = getMarineMaxCustomClientConfig(accountId, location);

        WorkOrderResponse workOrderResponse = new WorkOrderResponse();

        String[] workOrderRecord = getWorkOrderRecord(accountId, location, workOrderNumber);

        String customerNumber = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.CUSTOMER_NUMBER];

        workOrderResponse.customer = getCustomer(accountId, location, customerNumber);

        workOrderResponse.workOrder = getWorkOrder(accountId, location, workOrderNumber, workOrderRecord);
        workOrderResponse.inventory = getInventory(workOrderRecord);
        workOrderResponse.jobs = getJobs(accountId, location, workOrderNumber, workOrderRecord);
        workOrderResponse.specialOrder = getSpecialOrder(accountId, location, workOrderRecord);
        workOrderResponse.purchaseOrders = getPurchaseOrders(accountId, location, workOrderRecord);
        workOrderResponse.billingInfoSummaries = getBillingInfoSummaries(accountId, location, workOrderRecord);

        return workOrderResponse;
    }

    private String[] getWorkOrderRecord(int accountId, String location, String workOrderNumber) {
        List<Integer> workOrderFields = new ArrayList<>();

        // Work Order
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.WORK_ORDER_DATE); // 0
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.AUTHOR); // 1
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.STATUS_CODE); // 2
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.WORK_ORDER_LOCATION); // 3
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.SALESMAN_CODE); // 4
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PROMISE_DATE); // 5
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PROMISE_TIME); // 6
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.SCHEDULE_PRIORITY_CODE); // 7
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.APPOINTMENT_DATE); // 8
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.APPOINTMENT_TIME); // 9
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.IN_SERVICE_DATE); // 10
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PARTS_DISCOUNT); // 11
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.CATEGORY_CODE); // 12
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.TAG_NO); // 13
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.MILEAGE_IN); // 14
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.MILEAGE_OUT); // 15
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.MILEAGE_UNIT_CODE); // 16
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.COMPLETE_DATE); // 17
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.CANCEL_DATE); // 18
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.COMMENTS); // 19

        // Inventory
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.InventoryColumns.STOCK_NO); // 20
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.InventoryColumns.WARRANTY_DATE); // 21
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.InventoryColumns.CHASSIS_NO); // 22
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.InventoryColumns.DESCRIPTION); // 23
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.InventoryColumns.SERIAL_NO); // 24

        // Jobs
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.JOB_NO); // 25
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.DESCRIPTION); // 26
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.BILL_TYPE_CODE); // 27
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.BILL_TO); // 28
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SALESMAN_CODE); // 29
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.TAX_CODE); // 30
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.STATUS_CODE); // 31
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.QUOTED_AMOUNT); // 32
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ESTIMATED_AMOUNT); // 33

        // Extras
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.EXTRA_CODE); // 34
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.DESCRIPTION); // 35
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.TAX_CODE); // 36
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.SALESMAN_CODE); // 37
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.EXTRA_JOB_NUMBER); // 38

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.RequiredExtrasPricingColumns.QUANTITY); // 39
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.RequiredExtrasPricingColumns.COST); // 40
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.RequiredExtrasPricingColumns.LIST_PRICE); // 41
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.RequiredExtrasPricingColumns.EXTENSION); // 42

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.ActualsJExtrasPricingColumns.QUANTITY); // 43
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.ActualsJExtrasPricingColumns.COST); // 44
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.ActualsJExtrasPricingColumns.LIST_PRICE); // 45
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.ExtrasColumns.ActualsJExtrasPricingColumns.EXTENSION); // 46

        // Sublets
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.DESCRIPTION); // 47
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.VENDOR_NO); // 48
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.PURCHASE_ORDER_NO); // 49
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.EXPECTED_DATE); // 50
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.COMPLETED_DATE); // 51
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.TAX_CODE); // 52
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.PURCHASE_ORDER_COMMENT); // 53
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.SALESMAN_CODE); // 54
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.INVOICE_NO); // 55
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.SUBLET_JOB_NUMBER); // 56

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.RequiredSubletPricingColumns.COST); // 57
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.RequiredSubletPricingColumns.LIST_PRICE); // 58

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.ActualsSubletPricingColumns.COST); // 59
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.SubletsColumns.ActualsSubletPricingColumns.LIST_PRICE); // 60

        // Labor
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.LABOR_CODE); // 61
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.DESCRIPTION); // 62
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.TYPE); // 63
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.MECHANIC_CODE); // 64
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.SKILL_SET_CODE); // 65
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.STATUS_CODE); // 66
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.LABOR_DATE); // 67
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.CHARGE_HOURS); // 68
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.TAX_CODE); // 69
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.FAULT_CODE); // 70
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.SALESMAN_CODE); // 71
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.LABOR_JOB_NUMBER); // 72

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.ActualsLaborPricing.HOURS); // 73
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.ActualsLaborPricing.RATE); // 74
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.ActualsLaborPricing.EXTENSION); // 75

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.RequiredLaborPricing.HOURS); // 76
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.RequiredLaborPricing.RATE); // 77
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.LaborsColumns.RequiredLaborPricing.EXTENSION); // 78

        // Parts
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.PART_NO); // 79
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.DESCRIPTION); // 80
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.DISCOUNT_PERCENTAGE); // 81
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.TYPE); // 82
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.TAX_CODE); // 83
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.SALESMAN_CODE); // 84
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.PART_JOB_NUMBER); // 85

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.ActualsPartPricing.QUANTITY); // 86
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.ActualsPartPricing.COST); // 87
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.ActualsPartPricing.PRICE); // 88
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.ActualsPartPricing.EXTENSION); // 89

        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.RequiredPartPricing.QUANTITY); // 90
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.RequiredPartPricing.COST); // 91
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.RequiredPartPricing.PRICE); // 92
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.PartsColumns.RequiredPartPricing.EXTENSION); // 93

        // Job Estimate Comments
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.JobEstimateCommentColumns.CONTENT); // 94
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.JobEstimateCommentColumns.ESTIMATE); // 95
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.JobEstimateCommentColumns.CREATED_DATE); // 96
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.JobEstimateCommentColumns.CREATED_TIME); // 97
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.JobColumns.JobEstimateCommentColumns.AUTHOR); // 98

        // Special Orders
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.SPECIAL_ORDER_NUMBER); // 99

        // Purchase Orders
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PurchaseOrderColumns.PARTS_PURCHASE_ORDER); // 100
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PurchaseOrderColumns.SUBLET_PURCHASE_ORDER); // 101
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PurchaseOrderColumns.ALL_SUBLET_PURCHASE_ORDER); // 102
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PurchaseOrderColumns.SUBLET_JOB_NUMBER); // 103
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.PurchaseOrderColumns.SUBLET_DESCRIPTION); // 104

        // Billing Info Summary
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.BILL_CODE); // 105
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.BILL_TYPE); // 106
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.BILL_ID); // 107
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.INVOICE_NO); // 108
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.PARTS_TOTAL); // 109
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.LABOR_TOTAL); // 110
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.SUBLET_TOTAL); // 111
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.EXTRA_TOTAL); // 112
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.TAX_TOTAL); // 113
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.TOTAL); // 114
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.BillingInfoSummaryColumns.PAID_AMOUNT); // 115

        // Customer
        workOrderFields.add(WorkOrderResponseColumns.WorkOrderColumns.CUSTOMER_NUMBER); // 116

        String tableName = U2Tables.getWorkOrder(location);

        return u2Api.readFields(accountId, location, tableName, workOrderNumber, workOrderFields);
    }

    private List<BillingInfoSummary> getBillingInfoSummaries(int accountId, String location, String[] workOrderRecord) {
        List<BillingInfoSummary> billingInfoSummaries = new ArrayList<>();

        String[] billCode = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.BILL_CODE].split(U2Delimiters.VM);
        String[] billType = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.BILL_TYPE].split(U2Delimiters.VM);
        String[] billId = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.BILL_ID].split(U2Delimiters.VM);
        String[] invoiceNo = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.INVOICE_NO].split(U2Delimiters.VM);
        String[] partsTotal = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.PARTS_TOTAL].split(U2Delimiters.VM);
        String[] laborTotal = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.LABOR_TOTAL].split(U2Delimiters.VM);
        String[] subletTotal = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.SUBLET_TOTAL].split(U2Delimiters.VM);
        String[] extraTotal = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.EXTRA_TOTAL].split(U2Delimiters.VM);
        String[] taxTotal = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.TAX_TOTAL].split(U2Delimiters.VM);
        String[] total = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.TOTAL].split(U2Delimiters.VM);
        String[] paidAmounts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.BillingInfoSummaryIndexes.PAID_AMOUNT].split(U2Delimiters.VM);

        for(int i=0;i< billCode.length;i++){
            BillingInfoSummary infoSummary = new BillingInfoSummary();

            infoSummary.billCode = billCode[i].split("\\*")[1];
            String type = billType[i];
            infoSummary.billTypeCode = billTypeMap.get(type);
            String billTo = DataHelper.getArrayIndexSafeString(billId, i);
            switch (type){
                case "E":
                    infoSummary.billTo = getCustomerLastName(accountId , location, billTo);
                    String firstName = getCustomerFirstName(accountId, location, billTo);
                    if(firstName != null && !firstName.isEmpty())
                        infoSummary.billTo = infoSummary.billTo + ", " + firstName;
                    break;
                case "I":
                    infoSummary.billTo = getWorkOrderBillCodeName(accountId, location, billTo);
                    break;
                case "W":
                    infoSummary.billTo = getCustomerLastName(accountId , location, billTo);
                    break;
            }

            infoSummary.billInvoiceNo = DataHelper.getArrayIndexSafeString(invoiceNo, i);
            infoSummary.partsTotal = DataHelper.getArrayIndexSafeU2Double(partsTotal, i);
            infoSummary.laborTotal = DataHelper.getArrayIndexSafeU2Double(laborTotal, i);
            infoSummary.subletTotal = DataHelper.getArrayIndexSafeU2Double(subletTotal, i);
            infoSummary.extrasTotal = DataHelper.getArrayIndexSafeU2Double(extraTotal, i);
            infoSummary.taxTotal = DataHelper.getArrayIndexSafeU2Double(taxTotal, i);
            infoSummary.total = DataHelper.getArrayIndexSafeU2Double(total, i);
            infoSummary.paidAmount = DataHelper.getArrayIndexSafeU2Double(paidAmounts, i);

            /*
            if(infoSummary.total == null || infoSummary.total == 0.0)
                continue;
            */
            billingInfoSummaries.add(infoSummary);
        }

        return billingInfoSummaries;
    }

    private String getWorkOrderBillCodeName(int accountId, String location, String billTo) {
        List<Integer> fields = new ArrayList<>();

        fields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getWoBillCode(location),
                billTo,
                fields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private List<PurchaseOrder> getPurchaseOrders(int accountId, String location, String[] workOrderRecord) {

        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        String[] partsNo = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PurchaseOrderIndexes.PARTS_PURCHASE_ORDER].split(U2Delimiters.VM);
        for (String s : partsNo) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.purchaseOrderNo = DataHelper.getEmptyStringAsNull(s);

            if (purchaseOrder.purchaseOrderNo == null)
                continue;

            purchaseOrder.type = getPurchaseOrderTypeDesc("P");

            populatePurchaseOrder(accountId, location, purchaseOrder);

            purchaseOrders.add(purchaseOrder);
        }

        String[] subletsNo = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PurchaseOrderIndexes.SUBLET_PURCHASE_ORDER].split(U2Delimiters.VM);
        String[] subletDescription = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PurchaseOrderIndexes.SUBLET_DESCRIPTION].split(U2Delimiters.VM);
        String[] subletJobNumber = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PurchaseOrderIndexes.SUBLET_JOB_NUMBER].split(U2Delimiters.VM);
        for(int i=0;i< subletsNo.length;i++){
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.purchaseOrderNo = DataHelper.getEmptyStringAsNull(subletsNo[i]);

            if(purchaseOrder.purchaseOrderNo == null)
                continue;

            purchaseOrder.type = getPurchaseOrderTypeDesc("S");
            purchaseOrder.jobNo = subletJobNumber[i];
            purchaseOrder.description = subletDescription[i];

            populatePurchaseOrder(accountId, location, purchaseOrder);

            purchaseOrders.add(purchaseOrder);
        }

        String[] allSubletsNo = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PurchaseOrderIndexes.ALL_SUBLET_PURCHASE_ORDER].split(U2Delimiters.VM);
        for (String s : allSubletsNo) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.purchaseOrderNo = DataHelper.getEmptyStringAsNull(s);

            if (purchaseOrder.purchaseOrderNo == null)
                continue;

            purchaseOrder.type = getPurchaseOrderTypeDesc("M");
            purchaseOrder.description = "SUBLET / MISCELLANEOUS";

            populatePurchaseOrder(accountId, location, purchaseOrder);

            purchaseOrders.add(purchaseOrder);
        }

        return purchaseOrders;
    }

    private void populatePurchaseOrder(int accountId, String location, PurchaseOrder order) {
        List<Integer> purchaseOrderFields = new ArrayList<>();

        purchaseOrderFields.add(1); // Vendor No
        purchaseOrderFields.add(2); // Entry Date
        purchaseOrderFields.add(3); // Expected Date
        purchaseOrderFields.add(30); // Completed Date
        purchaseOrderFields.add(16); // Ordered By
        purchaseOrderFields.add(91); // Invoice No
        purchaseOrderFields.add(8); // Reason Code
        purchaseOrderFields.add(6); // Ship Via
        purchaseOrderFields.add(4); // Ship Terms
        purchaseOrderFields.add(5); // Pay Terms

        purchaseOrderFields.add(11);
        purchaseOrderFields.add(12);

        purchaseOrderFields.add(8); // Parts Descriptions
        purchaseOrderFields.add(9); // All Sublets Descriptions

        String tableName = U2Tables.getMiscellaneousPurchaseOrder(location);

        if("Parts".equalsIgnoreCase(order.type)){
            tableName = U2Tables.getPurchaseOrder(location);
        }

        String[] response = u2Api.readFields(accountId, location, tableName, order.purchaseOrderNo, purchaseOrderFields);

        order.vendorNo = DataHelper.getEmptyStringAsNull(response[0]);
        order.vendorName = StringEscapeUtils.escapeJava(getVendorName(accountId, location, order.vendorNo));
        order.entryDate = DataHelper.getLocalDateFromU2(response[1]);
        order.expectedDate = DataHelper.getLocalDateFromU2(response[2]);
        order.completedDate = DataHelper.getLocalDateFromU2(response[3]);
        order.orderedBy = DataHelper.getEmptyStringAsNull(response[4]);
        order.invoiceNo = DataHelper.getEmptyStringAsNull(response[5]);
        if(!"Parts".equalsIgnoreCase(order.type)) {
            order.reasonCode = DataHelper.getEmptyStringAsNull(response[6]);
            order.reasonDesc = getPurchaseOrderReasonDescription(order.reasonCode);
        }
        order.shipVia = DataHelper.getEmptyStringAsNull(response[7]);
        order.shipTerms = DataHelper.getEmptyStringAsNull(response[8]);
        order.payTerms = DataHelper.getEmptyStringAsNull(response[9]);
        order.value = DataHelper.getDoubleFromU2(response[10]) * DataHelper.getDoubleFromU2(response[11]);

        if("Parts".equalsIgnoreCase(order.type)){
            order.description = response[12].replace(U2Delimiters.VM, ",");
        } else if (!"Sublet".equalsIgnoreCase(order.type)){
            order.description = response[13].replace(U2Delimiters.VM, ",");
        }
    }

    private String[] getSpecialOrderRecord(int accountId, String location, String specialOrderNumber) {
        List<Integer> specialOrderFields = new ArrayList<>();
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.PART_NO); // Parts No
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.VENDOR_NO); // Vendor
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.VENDOR_PART_NO); // VendorPartNo
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.DESCRIPTION); // Part Description
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.QUANTITY); // Quantity
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.PRICE); // Price
        specialOrderFields.add(WorkOrderResponseColumns.SpecialOrderColumns.SpecialOrderPartColumns.STATUS); // Status

        String tableName = U2Tables.getSpecialOrders(location);
        return u2Api.readFields(accountId, location, tableName, specialOrderNumber, specialOrderFields);
    }

    private SpecialOrder getSpecialOrder(int accountId, String location, String[] workOrderRecord) {

        SpecialOrder specialOrder = new SpecialOrder();
        specialOrder.parts = new ArrayList<>();

        String specialOrderNumber = DataHelper.getArrayIndexSafeString(workOrderRecord,
                WorkOrderResponseIndexes.WorkOrderIndexes.SPECIAL_ORDER_NUMBER);

        if(specialOrderNumber == null || specialOrderNumber.trim().isEmpty())
            return specialOrder;

        specialOrder.specialOrderNo = specialOrderNumber.trim();

        String[] specialOrderRecord = getSpecialOrderRecord(accountId, location, specialOrderNumber);

        String[] partsNo = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.PART_NO].split(U2Delimiters.VM);
        String[] vendor = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.VENDOR_NO].split(U2Delimiters.VM);
        String[] vendorPartNo = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.VENDOR_PART_NO].split(U2Delimiters.VM);
        String[] partDescription = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.DESCRIPTION].split(U2Delimiters.VM);
        String[] quantity = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.QUANTITY].split(U2Delimiters.VM);
        String[] price = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.PRICE].split(U2Delimiters.VM);
        String[] status = specialOrderRecord[WorkOrderResponseIndexes.SpecialOrderIndexes.SpecialOrderPartIndexes.STATUS].split(U2Delimiters.VM);

        for(int i=0;i< partsNo.length;i++){
            String pNo = partsNo[i].trim();

            if(pNo.isEmpty())
                continue;

            SpecialOrderPart specialOrderPart= new SpecialOrderPart();

            specialOrderPart.partNo = pNo;
            specialOrderPart.vendorNo = vendor[i];
            specialOrderPart.vendorPartNo = vendorPartNo[i];
            specialOrderPart.description = partDescription[i];
            specialOrderPart.quantity = DataHelper.getDoubleFromU2(quantity[i]);
            specialOrderPart.price = DataHelper.getDoubleFromU2(price[i]);
            specialOrderPart.status = getSpecialOrderPartsStatusDescription(status[i]);
            specialOrderPart.comments = getG2Comments(accountId, location, "SOPART",
                    specialOrder.specialOrderNo+"~"+pNo);
            specialOrder.parts.add(specialOrderPart);
        }

        return specialOrder;
    }

    private List<G2Comment> getG2Comments(int accountId, String location, String tagId, String key){
        List<G2Comment> g2Comments = new ArrayList<>();

        String[] commentIds = getG2CommentsIds(accountId, location, tagId, key);

        for(String id : commentIds){

            List<Integer> fields = new ArrayList<>();
            fields.add(12); // content - 0
            fields.add(1); // createdDate - 1
            fields.add(2); // createdTime - 2
            fields.add(3); // createdAuthor - 3
            fields.add(8); // modifiedDate - 4
            fields.add(9); // modifiedTime - 5
            fields.add(10); // modifiedAuthor - 6

            String[] response = u2Api.readFields(accountId, location, U2Tables.getG2Comments(location), id, fields);

            G2Comment g2Comment = new G2Comment();
            g2Comment.content = DataHelper.getArrayIndexSafeString(response, 0);
            g2Comment.createdAuthor = DataHelper.getArrayIndexSafeString(response, 3);
            g2Comment.modifiedAuthor = DataHelper.getArrayIndexSafeString(response, 6);
            g2Comment.createdDateTime = DataHelper.getLocalDateTimeFromU2(response[1], response[2]);
            g2Comment.modifiedDateTime = DataHelper.getLocalDateTimeFromU2(response[4], response[5]);

            g2Comments.add(g2Comment);
        }

        return g2Comments;
    }

    private String[] getG2CommentsIds(int accountId, String location , String tagId, String key){

        // TODO: What is WO.FPTR.LOCN? and use WO.FPTR.LOCN instead of RVK
        String pk = "RVK*"+tagId+"*"+key;

        List<Integer> commentIdsFields = new ArrayList<>();
        commentIdsFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getG2CommentsXref(location),
                pk,
                commentIdsFields);

        String ids = DataHelper.getArrayIndexSafeString(response, 0);

        if(ids == null)
            return new String[0];

        return ids.split(U2Delimiters.VM);
    }

    private List<List<JobEstimateComment>> getJobEstimateComments(String[] workOrderRecord){

        String[] contents = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.JobEstimateCommentIndexes.CONTENT].split(U2Delimiters.VM);
        String[] estimates = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.JobEstimateCommentIndexes.ESTIMATE].split(U2Delimiters.VM);
        String[] createdDates = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.JobEstimateCommentIndexes.CREATED_DATE].split(U2Delimiters.VM);
        String[] createdTimes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.JobEstimateCommentIndexes.CREATED_TIME].split(U2Delimiters.VM);
        String[] authors = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.JobEstimateCommentIndexes.AUTHOR].split(U2Delimiters.VM);

        List<List<JobEstimateComment>> estimateComments = new ArrayList<>();
        String delimiters = ((char)252) + "";

        for(int i=0;i<authors.length;i++){
            String[] jobContents = DataHelper.getArrayIndexSafeString(contents, i)==null? null : DataHelper.getArrayIndexSafeString(contents, i).split(delimiters);
            String[] jobEstimates = DataHelper.getArrayIndexSafeString(estimates, i)==null? null : DataHelper.getArrayIndexSafeString(estimates, i).split(delimiters);
            String[] jobCreatedDates = DataHelper.getArrayIndexSafeString(createdDates, i)==null? null : DataHelper.getArrayIndexSafeString(createdDates, i).split(delimiters);
            String[] jobCreatedTimes = DataHelper.getArrayIndexSafeString(createdTimes, i)==null? null : DataHelper.getArrayIndexSafeString(createdTimes, i).split(delimiters);
            String[] jobAuthors = authors[i].split(delimiters);

            List<JobEstimateComment> jobEstimateComments = new ArrayList<>();

            for(int j=0; j< jobAuthors.length; j++){
                JobEstimateComment jobEstimateComment = new JobEstimateComment();

                jobEstimateComment.author = jobAuthors[j];
                jobEstimateComment.content = DataHelper.getArrayIndexSafeString(jobContents, j);
                jobEstimateComment.createdDateTime = DataHelper.getLocalDateTimeFromU2(DataHelper.getArrayIndexSafeString(jobCreatedDates, j),
                        DataHelper.getArrayIndexSafeString(jobCreatedTimes, j));
                jobEstimateComment.estimate = DataHelper.getDoubleFromU2(DataHelper.getArrayIndexSafeString(jobEstimates, j));
                if(j != 0) {
                    jobEstimateComment.difference = jobEstimateComment.estimate - jobEstimateComments.get(j-1).estimate;
                }

                jobEstimateComments.add(jobEstimateComment);
            }

            estimateComments.add(jobEstimateComments);
        }

        return estimateComments;
    }

    private List<Job> getJobs(int accountId, String location, String workOrderNo, String[] workOrderRecord) {

        String[] jobNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.JOB_NO].split(U2Delimiters.VM);
        String[] jobDescriptions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.DESCRIPTION].split(U2Delimiters.VM);
        String[] jobBillType = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.BILL_TYPE_CODE].split(U2Delimiters.VM);
        String[] jobBillID = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.BILL_TO].split(U2Delimiters.VM);
        String[] jobSalesmanCode = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SALESMAN_CODE].split(U2Delimiters.VM);
        String[] jobTaxCode = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.TAX_CODE].split(U2Delimiters.VM);
        String[] jobStatusCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.STATUS_CODE].split(U2Delimiters.VM);
        String[] jobQuotedAmounts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.QUOTED_AMOUNT].split(U2Delimiters.VM);
        String[] jobEstimatedAmounts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ESTIMATED_AMOUNT].split(U2Delimiters.VM);

        HashMap<String, List<Extras>> extras = getExtras(accountId, location, workOrderRecord);
        HashMap<String, List<Sublet>> sublets = getSublets(accountId, location, workOrderRecord);
        HashMap<String, List<Labor>> labors = getLabors(accountId, location, workOrderRecord);
        HashMap<String, List<Part>> parts = getParts(accountId, location, workOrderRecord);
        List<List<JobEstimateComment>> jobEstimateComments = getJobEstimateComments(workOrderRecord);

        int size = jobNumbers.length;
        Job[] jobs = new Job[size];

        for(int i=0;i<size;i++){
            String[] descriptions = DataHelper.getMultiValue(jobDescriptions[i], 3, "\\|");
            jobs[i] = new Job();
            jobs[i].jobNo = jobNumbers[i];
            jobs[i].complaint = DataHelper.getEmptyStringAsNull(descriptions[0]);
            jobs[i].cause = DataHelper.getEmptyStringAsNull(descriptions[1]);
            jobs[i].correction = DataHelper.getEmptyStringAsNull(descriptions[2]);
            jobs[i].billTypeCode = jobBillType[i];
            jobs[i].billTypeDesc = billTypeMap.get(jobs[i].billTypeCode);
            jobs[i].salesmanCode = DataHelper.getEmptyStringAsNull(jobSalesmanCode[i]);
            jobs[i].salesmanDesc = getSalesmenName(accountId, location, jobs[i].salesmanCode);
            jobs[i].taxCode = jobTaxCode[i];
            jobs[i].taxDesc = getTaxDescription(accountId, location, jobs[i].taxCode);
            jobs[i].billTo = jobBillID[i];
            jobs[i].billToDesc = getCustomerLastName(accountId, location, jobs[i].billTo);

            jobs[i].statusCode = DataHelper.getArrayIndexSafeString(jobStatusCodes, i);
            jobs[i].statusDesc = getJobStatusDescription(accountId, location, jobs[i].statusCode);

            jobs[i].quotedAmount = DataHelper.getArrayIndexSafeU2Double(jobQuotedAmounts, i);
            jobs[i].estimatedAmount = DataHelper.getArrayIndexSafeU2Double(jobEstimatedAmounts, i);

            JobTotals actuals = new JobTotals();
            JobTotals required = new JobTotals();

            jobs[i].extras = extras.containsKey(jobs[i].jobNo)? extras.get(jobs[i].jobNo): new ArrayList<>();
            jobs[i].sublets = sublets.containsKey(jobs[i].jobNo)? sublets.get(jobs[i].jobNo): new ArrayList<>();
            jobs[i].labors = labors.containsKey(jobs[i].jobNo)? labors.get(jobs[i].jobNo): new ArrayList<>();
            jobs[i].parts = parts.containsKey(jobs[i].jobNo)? parts.get(jobs[i].jobNo): new ArrayList<>();

            jobs[i].comments = getG2Comments(accountId, location, "WOJOBNO", workOrderNo+"~"+jobs[i].jobNo);

            if(i <= jobEstimateComments.size()-1){
                jobs[i].estimatedAmountComments = jobEstimateComments.get(i);
            }

            for(Labor labor : jobs[i].labors) {
                jobs[i].chargeHours += labor.chargeHours;
                jobs[i].requiredHours += labor.required.hours;
                jobs[i].actualHours += labor.actuals.hours;

                actuals.labor += labor.actuals.extension;
                required.labor += labor.required.extension;

                actuals.total += labor.actuals.extension;
                required.total += labor.required.extension;
            }

            for(Part part : jobs[i].parts){
                actuals.parts += part.actuals.extension;
                required.parts += part.required.extension;

                actuals.total += part.actuals.extension;
                required.total += part.required.extension;
            }

            for(Sublet sublet : jobs[i].sublets){
                actuals.sublet += sublet.actuals.listPrice;
                required.sublet += sublet.required.listPrice;

                actuals.total += sublet.actuals.listPrice;
                required.total += sublet.required.listPrice;
            }

            for(Extras extra : jobs[i].extras){
                actuals.extras += extra.actuals.extension;
                required.extras += extra.required.extension;

                actuals.total += extra.actuals.extension;
                required.total += extra.required.extension;
            }

            roundOffJobTotals(actuals);
            roundOffJobTotals(required);

            jobs[i].required = required;
            jobs[i].actuals = actuals;
        }

        return new ArrayList<>(Arrays.asList(jobs));
    }

    private HashMap<String, List<Labor>> getLabors(int accountId, String location, String[] workOrderRecord){

        String[] codes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.LABOR_CODE].split(U2Delimiters.VM);
        String[] descriptions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.DESCRIPTION].split(U2Delimiters.VM);
        String[] types = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.TYPE].split(U2Delimiters.VM);
        String[] mechanicCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.MECHANIC_CODE].split(U2Delimiters.VM);
        String[] skillSetCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.SKILL_SET_CODE].split(U2Delimiters.VM);
        String[] statusCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.STATUS_CODE].split(U2Delimiters.VM);
        String[] laborDates = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.LABOR_DATE].split(U2Delimiters.VM);
        String[] chargeHours = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.CHARGE_HOURS].split(U2Delimiters.VM);
        String[] taxCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.TAX_CODE].split(U2Delimiters.VM);
        String[] faultCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.FAULT_CODE].split(U2Delimiters.VM);
        String[] salesmanCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.SALESMAN_CODE].split(U2Delimiters.VM);
        String[] laborJobNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.LABOR_JOB_NUMBER].split(U2Delimiters.VM);

        String[] actualsHours = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.ActualsLaborPricing.HOURS].split(U2Delimiters.VM);
        String[] actualsRates = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.ActualsLaborPricing.RATE].split(U2Delimiters.VM);
        String[] actualsExtensions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.ActualsLaborPricing.EXTENSION].split(U2Delimiters.VM);

        String[] requiredHours = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.RequiredLaborPricing.HOURS].split(U2Delimiters.VM);
        String[] requiredRates = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.RequiredLaborPricing.RATE].split(U2Delimiters.VM);
        String[] requiredExtensions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.LaborsIndexes.RequiredLaborPricing.EXTENSION].split(U2Delimiters.VM);

        int size = codes.length;

        HashMap<String, List<Labor>> laborsMap = new HashMap<>();

        for(int i=0;i<size;i++){

            Labor labor = new Labor();
            labor.laborCode = codes[i];
            labor.description = descriptions[i];
            labor.taxCode = taxCodes[i];
            labor.taxDesc = getTaxDescription(accountId, location, labor.taxCode);
            labor.salesmanCode = DataHelper.getArrayIndexSafeString(salesmanCodes, i);
            labor.salesmanDesc = getSalesmenName(accountId, location, labor.salesmanCode);
            labor.type = DataHelper.getArrayIndexSafeString(types, i);
            labor.typeDesc = getLaborTypeDescription(accountId, location, labor.type);
            labor.mechanicCode = DataHelper.getArrayIndexSafeString(mechanicCodes, i);
            labor.mechanicDesc = getMechanicDescription(accountId, location, labor.mechanicCode);
            labor.skillSetCode = DataHelper.getArrayIndexSafeString(skillSetCodes, i);
            labor.skillSetDesc = getSkillSetDescription(accountId, location, labor.skillSetCode);

            String status = DataHelper.getArrayIndexSafeString(statusCodes, i);
            if(status == null)
                status = "0";
            labor.statusCode = laborStatusMap.get(status);

            labor.laborDate = DataHelper.getLocalDateFromU2(laborDates[i]);
            labor.laborDateStr = DataHelper.formatDate(labor.laborDate);
            labor.chargeHours = DataHelper.getDoubleFromU2(chargeHours[i]);
            labor.faultCode = DataHelper.getArrayIndexSafeString(faultCodes, i);

            LaborPricing required = new LaborPricing();
            LaborPricing actuals = new LaborPricing();

            required.rate = DataHelper.getArrayIndexSafeU2Double(requiredRates, i);
            required.hours = DataHelper.getArrayIndexSafeU2Double(requiredHours, i);
            required.extension = DataHelper.getArrayIndexSafeU2Double(requiredExtensions, i);

            actuals.rate = DataHelper.getArrayIndexSafeU2Double(actualsRates, i);
            actuals.hours = DataHelper.getArrayIndexSafeU2Double(actualsHours, i);
            actuals.extension = DataHelper.getArrayIndexSafeU2Double(actualsExtensions, i);

            labor.actuals = actuals;
            labor.required = required;

            String jobNumber = laborJobNumbers[i];

            if(laborsMap.containsKey(jobNumber)){
                laborsMap.get(jobNumber).add(labor);
            }else{
                List<Labor> labors = new ArrayList<>();
                labors.add(labor);
                laborsMap.put(jobNumber, labors);
            }

        }
        return laborsMap;
    }

    private HashMap<String, List<Extras>> getExtras(int accountId, String location, String[] workOrderRecord){

        String[] codes =  workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.EXTRA_CODE].split(U2Delimiters.VM);
        String[] descriptions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.DESCRIPTION].split(U2Delimiters.VM);
        String[] taxCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.TAX_CODE].split(U2Delimiters.VM);
        String[] salesman = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.SALESMAN_CODE].split(U2Delimiters.VM);
        String[] jobNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.EXTRA_JOB_NUMBER].split(U2Delimiters.VM);

        String[] requiredQtys = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.RequiredExtrasPricingIndexes.QUANTITY].split(U2Delimiters.VM);
        String[] requiredCosts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.RequiredExtrasPricingIndexes.COST].split(U2Delimiters.VM);
        String[] requiredListPrices = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.RequiredExtrasPricingIndexes.LIST_PRICE].split(U2Delimiters.VM);
        String[] requiredExtensions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.RequiredExtrasPricingIndexes.EXTENSION].split(U2Delimiters.VM);

        String[] actualsQtys = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.ActualsJExtrasPricingIndexes.QUANTITY].split(U2Delimiters.VM);
        String[] actualsCosts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.ActualsJExtrasPricingIndexes.COST].split(U2Delimiters.VM);
        String[] actualsListPrices = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.ActualsJExtrasPricingIndexes.LIST_PRICE].split(U2Delimiters.VM);
        String[] actualsExtensions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.ExtrasIndexes.ActualsJExtrasPricingIndexes.EXTENSION].split(U2Delimiters.VM);

        int size = codes.length;

        HashMap<String, List<Extras>> extrasMap = new HashMap<>();

        for(int i=0;i<size;i++){

            Extras extra = new Extras();
            extra.extraCode = codes[i];
            extra.description = descriptions[i];
            extra.taxCode = taxCodes[i];
            extra.taxDesc = getTaxDescription(accountId, location, extra.taxCode);
            extra.salesmanCode = DataHelper.getArrayIndexSafeString(salesman, i);
            extra.salesmanDesc = getSalesmenName(accountId, location, extra.salesmanCode);

            ExtrasPricing required = new ExtrasPricing();
            ExtrasPricing actuals = new ExtrasPricing();

            required.quantity = DataHelper.getArrayIndexSafeU2Double(requiredQtys, i);
            required.cost = DataHelper.getArrayIndexSafeU2Double(requiredCosts, i);
            required.listPrice = DataHelper.getArrayIndexSafeU2Double(requiredListPrices, i);
            required.extension = DataHelper.getArrayIndexSafeU2Double(requiredExtensions, i);

            actuals.quantity = DataHelper.getArrayIndexSafeU2Double(actualsQtys, i);
            actuals.cost = DataHelper.getArrayIndexSafeU2Double(actualsCosts, i);
            actuals.listPrice = DataHelper.getArrayIndexSafeU2Double(actualsListPrices, i);
            actuals.extension = DataHelper.getArrayIndexSafeU2Double(actualsExtensions, i);

            extra.actuals = actuals;
            extra.required = required;

            String jobNumber = jobNumbers[i];

            if(extrasMap.containsKey(jobNumber)){
                extrasMap.get(jobNumber).add(extra);
            }else{
                List<Extras> extras = new ArrayList<>();
                extras.add(extra);
                extrasMap.put(jobNumber, extras);
            }

        }
        return extrasMap;
    }

    private HashMap<String, List<Sublet>> getSublets(int accountId, String location,  String[] workOrderRecord) {

        String[] descriptions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.DESCRIPTION].split(U2Delimiters.VM);
        String[] vendorCodes =  workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.VENDOR_NO].split(U2Delimiters.VM);
        String[] purchaseOrderNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.PURCHASE_ORDER_NO].split(U2Delimiters.VM);
        String[] expectedDate = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.EXPECTED_DATE].split(U2Delimiters.VM);
        String[] completeDate = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.COMPLETED_DATE].split(U2Delimiters.VM);
        String[] taxCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.TAX_CODE].split(U2Delimiters.VM);
        String[] purchaseOrderComments = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.PURCHASE_ORDER_COMMENT].split(U2Delimiters.VM);
        String[] salesman = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.SALESMAN_CODE].split(U2Delimiters.VM);
        String[] invoiceNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.INVOICE_NO].split(U2Delimiters.VM);
        String[] jobNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.SUBLET_JOB_NUMBER].split(U2Delimiters.VM);

        String[] subletsRequiredCosts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.RequiredSubletPricingIndexes.COST].split(U2Delimiters.VM);
        String[] subletsRequiredLists = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.RequiredSubletPricingIndexes.LIST_PRICE].split(U2Delimiters.VM);

        String[] subletsActualsCosts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.ActualsSubletPricingIndexes.COST].split(U2Delimiters.VM);
        String[] subletsActualsLists = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.SubletsIndexes.ActualsSubletPricingIndexes.LIST_PRICE].split(U2Delimiters.VM);

        int size = vendorCodes.length;
        HashMap<String, List<Sublet>> subletsMap = new HashMap<>();

        for(int i=0;i<size;i++){

            Sublet sublet = new Sublet();
            sublet.vendorNo = vendorCodes[i];
            sublet.vendorName = StringEscapeUtils.escapeJava(getVendorName(accountId, location, sublet.vendorNo));
            sublet.description = descriptions[i];
            sublet.taxCode = taxCodes[i];
            sublet.taxDesc = getTaxDescription(accountId, location, sublet.taxCode);
            sublet.completedDate = DataHelper.getArrayIndexSafeLocalDateFromU2(completeDate, i);
            sublet.completedDateStr = DataHelper.formatDate(sublet.completedDate);
            sublet.expectedDate = DataHelper.getArrayIndexSafeLocalDateFromU2(expectedDate, i);
            sublet.expectedDateStr = DataHelper.formatDate(sublet.expectedDate);
            sublet.purchaseOrderNo = purchaseOrderNumbers[i];
            sublet.purchaseOrderComment = DataHelper.getArrayIndexSafeString(purchaseOrderComments, i);
            sublet.salesmanCode = salesman[i];
            sublet.salesmanDesc = getSalesmenName(accountId, location, sublet.salesmanCode);
            sublet.invoiceNo = invoiceNumbers[i];

            SubletPricing required = new SubletPricing();
            SubletPricing actuals = new SubletPricing();

            required.cost = DataHelper.getDoubleFromU2(subletsRequiredCosts[i]);
            required.listPrice = DataHelper.getDoubleFromU2(subletsRequiredLists[i]);

            actuals.cost = DataHelper.getDoubleFromU2(subletsActualsCosts[i]);
            actuals.listPrice = DataHelper.getDoubleFromU2(subletsActualsLists[i]);

            sublet.required = required;
            sublet.actuals = actuals;

            String jobNumber = jobNumbers[i];

            if(subletsMap.containsKey(jobNumber)){
                subletsMap.get(jobNumber).add(sublet);
            }else{
                List<Sublet> sublets = new ArrayList<>();
                sublets.add(sublet);
                subletsMap.put(jobNumber, sublets);
            }

        }
        return subletsMap;
    }

    private HashMap<String, List<Part>> getParts(int accountId, String location, String[] workOrderRecord) {

        String[] partNos = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.PART_NO].split(U2Delimiters.VM);
        String[] descriptions =  workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.DESCRIPTION].split(U2Delimiters.VM);
        String[] discountPercentages = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.DISCOUNT_PERCENTAGE].split(U2Delimiters.VM);
        String[] types = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.TYPE].split(U2Delimiters.VM);
        String[] taxCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.TAX_CODE].split(U2Delimiters.VM);
        String[] salesmanCodes = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.SALESMAN_CODE].split(U2Delimiters.VM);
        String[] partJobNumbers = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.PART_JOB_NUMBER].split(U2Delimiters.VM);

        String[] actualsQtys = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.ActualsPartPricing.QUANTITY].split(U2Delimiters.VM);
        String[] actualsCosts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.ActualsPartPricing.COST].split(U2Delimiters.VM);
        String[] actualsPrices = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.ActualsPartPricing.PRICE].split(U2Delimiters.VM);
        String[] actualsExtensions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.ActualsPartPricing.EXTENSION].split(U2Delimiters.VM);

        String[] requiredQtys = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.RequiredPartPricing.QUANTITY].split(U2Delimiters.VM);
        String[] requiredCosts = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.RequiredPartPricing.COST].split(U2Delimiters.VM);
        String[] requiredPrices = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.RequiredPartPricing.PRICE].split(U2Delimiters.VM);
        String[] requiredExtensions = workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.JobIndexes.PartsIndexes.RequiredPartPricing.EXTENSION].split(U2Delimiters.VM);

        int size = partNos.length;
        HashMap<String, List<Part>> partsMap = new HashMap<>();

        for(int i=0; i<size; i++){

            Part part = new Part();
            part.partNo = partNos[i];
            part.description = DataHelper.getArrayIndexSafeString(descriptions, i);
            part.discountPercentage = DataHelper.getArrayIndexSafeU2Double(discountPercentages, i);
            part.type = DataHelper.getArrayIndexSafeString(types, i);
            part.typeDesc = getPartTypesDescription(accountId, location, part.type);
            part.taxCode = DataHelper.getArrayIndexSafeString(taxCodes, i);
            part.taxDesc = getTaxDescription(accountId, location, part.taxCode);
            part.salesmanCode = DataHelper.getArrayIndexSafeString(salesmanCodes, i);
            part.salesmanDesc = getSalesmenName(accountId, location, part.salesmanCode);

            PartPricing required = new PartPricing();
            PartPricing actuals = new PartPricing();

            required.quantity = DataHelper.getArrayIndexSafeU2Double(requiredQtys, i);
            required.cost = DataHelper.getArrayIndexSafeU2Double(requiredCosts, i);
            required.price = DataHelper.getArrayIndexSafeU2Double(requiredPrices, i);
            required.extension = DataHelper.getArrayIndexSafeU2Double(requiredExtensions, i);

            actuals.quantity = DataHelper.getArrayIndexSafeU2Double(actualsQtys, i);
            actuals.cost = DataHelper.getArrayIndexSafeU2Double(actualsCosts, i);
            actuals.price = DataHelper.getArrayIndexSafeU2Double(actualsPrices, i);
            actuals.extension = DataHelper.getArrayIndexSafeU2Double(actualsExtensions, i);

            part.required = required;
            part.actuals = actuals;

            String jobNumber = partJobNumbers[i];

            if(partsMap.containsKey(jobNumber)){
                partsMap.get(jobNumber).add(part);
            }else{
                List<Part> parts = new ArrayList<>();
                parts.add(part);
                partsMap.put(jobNumber, parts);
            }

        }
        return partsMap;
    }

    private WorkOrder getWorkOrder(int accountId, String location, String workOrderNumber, String[] workOrderRecord){
        WorkOrder workOrder = new WorkOrder();

        workOrder.workOrderNo = workOrderNumber;
        workOrder.workOrderDate = DataHelper.getLocalDateFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.WORK_ORDER_DATE]);
        workOrder.workOrderDateStr = DataHelper.formatDate(workOrder.workOrderDate);
        workOrder.author = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.AUTHOR]);
        workOrder.statusCode = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.STATUS_CODE]);
        workOrder.statusDesc = getWorkOrderStatusDescription(accountId, location, workOrder.statusCode);
        workOrder.workOrderLocation = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.WORK_ORDER_LOCATION]);
        workOrder.workOrderLocationDesc = getWorkOrderLocationDescription(accountId, location, workOrder.workOrderLocation);
        workOrder.salesmanCode = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.SALESMAN_CODE]);
        workOrder.salesmanDesc = getSalesmenName(accountId, location, workOrder.salesmanCode);
        workOrder.promiseDateTime = DataHelper.getLocalDateTimeFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PROMISE_DATE],
                workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PROMISE_TIME]);
        workOrder.promiseDateStr = DataHelper.formatDate(workOrder.promiseDateTime);
        workOrder.promiseTimeStr = DataHelper.formatTime(workOrder.promiseDateTime);
        workOrder.schedulePriorityCode = DataHelper.getNullableInteger(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.SCHEDULE_PRIORITY_CODE]);
        workOrder.schedulePriorityDesc = schedulePriorityTypeMap.get(workOrder.schedulePriorityCode);
        workOrder.appointmentDateTime = DataHelper.getLocalDateTimeFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.APPOINTMENT_DATE],
                workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.APPOINTMENT_TIME]);
        workOrder.appointmentDateStr = DataHelper.formatDate(workOrder.appointmentDateTime);
        workOrder.appointmentTimeStr = DataHelper.formatTime(workOrder.appointmentDateTime);
        workOrder.inServiceDate = DataHelper.getLocalDateFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.IN_SERVICE_DATE]);
        workOrder.inServiceDateStr = DataHelper.formatDate(workOrder.inServiceDate);
        workOrder.partsDiscount = DataHelper.getDoubleFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.PARTS_DISCOUNT]);
        workOrder.categoryCode = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.CATEGORY_CODE]);
        workOrder.categoryDesc = getWorkOrderCategoryDescription(accountId, location, workOrder.categoryCode);
        workOrder.tagNo = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.TAG_NO]);
        workOrder.mileageIn = DataHelper.getNullableDouble(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.MILEAGE_IN]);
        workOrder.mileageOut = DataHelper.getNullableDouble(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.MILEAGE_OUT]);
        workOrder.mileageUnitCode = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.MILEAGE_UNIT_CODE]);
        workOrder.mileageUnitDesc = mileageTypeMap.get(workOrder.mileageUnitCode);
        workOrder.completeDate = DataHelper.getLocalDateFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.COMPLETE_DATE]);
        workOrder.completeDateStr = DataHelper.formatDate(workOrder.completeDate);
        workOrder.cancelDate = DataHelper.getLocalDateFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.CANCEL_DATE]);
        workOrder.cancelDateStr = DataHelper.formatDate(workOrder.cancelDate);
        workOrder.comments = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.COMMENTS]
                .replace(U2Delimiters.VM, "\r\n"));

        return workOrder;
    }

    private Customer getCustomer(int accountId, String location, String customerNumber) {

        Customer customer = new Customer();

        List<Integer> customerFields = new ArrayList<>();

        customerFields.add(WorkOrderResponseColumns.CustomerColumns.FIRST_NAME); // 0
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.LAST_NAME); // 1
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.EMAIL_ADDRESS); // 2
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.HOME_PHONE); // 3
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.POSTAL_CODE); // 4
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.COUNTRY); // 5
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.ADDRESS); // 6
        customerFields.add(WorkOrderResponseColumns.CustomerColumns.CELL_PHONE); // 7

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getCustomer(location),
                customerNumber,
                customerFields);

        customer.customerNo = customerNumber;
        customer.name = response[WorkOrderResponseIndexes.CustomerIndexes.FIRST_NAME]
                + " " + response[WorkOrderResponseIndexes.CustomerIndexes.LAST_NAME];
        customer.email = DataHelper.getEmptyStringAsNull(response[WorkOrderResponseIndexes.CustomerIndexes.EMAIL_ADDRESS]);
        customer.homePhone = DataHelper.getEmptyStringAsNull(response[WorkOrderResponseIndexes.CustomerIndexes.HOME_PHONE]);
        customer.mobilePhone = DataHelper.getEmptyStringAsNull(response[WorkOrderResponseIndexes.CustomerIndexes.CELL_PHONE]);
        customer.zipCode = DataHelper.getEmptyStringAsNull(response[WorkOrderResponseIndexes.CustomerIndexes.POSTAL_CODE]);
        customer.country = DataHelper.getEmptyStringAsNull(response[WorkOrderResponseIndexes.CustomerIndexes.COUNTRY]);

        boolean splitStreet = systemConfigurations.get(13).equalsIgnoreCase("Y");

        String[] addresses = DataHelper.getMultiValue(response[WorkOrderResponseIndexes.CustomerIndexes.ADDRESS] ,3, U2Delimiters.VM);

        if(splitStreet){
            customer.addressLine1 = DataHelper.getEmptyStringAsNull(addresses[0]);
            customer.addressLine2 = DataHelper.getEmptyStringAsNull(addresses[1]);
            customer.city = DataHelper.getEmptyStringAsNull(addresses[2].split(",")[0]);
            String[] state = addresses[2].split(",");
            customer.state = DataHelper.getEmptyStringAsNull(DataHelper.concatStrings(state, 1, state.length));
        }else{
            customer.addressLine1 = DataHelper.getEmptyStringAsNull(addresses[0].split(",")[0]);
            String[] addressLine2 = addresses[0].split(",");
            customer.addressLine2 = DataHelper.getEmptyStringAsNull(DataHelper.concatStrings(addressLine2, 1, addressLine2.length));
            customer.city = DataHelper.getEmptyStringAsNull(addresses[1].split(",")[0]);
            String[] state = addresses[1].split(",");
            customer.state = DataHelper.getEmptyStringAsNull(DataHelper.concatStrings(state, 1, state.length));
        }
        customer.addressLine1 = DataHelper.getFirstLetterInUpperCase(customer.addressLine1);
        customer.addressLine2 = DataHelper.getFirstLetterInUpperCase(customer.addressLine2);
        customer.city = DataHelper.getFirstLetterInUpperCase(customer.city);
        customer.state = DataHelper.getFirstLetterInUpperCase(customer.state);
        return customer;
    }

    private Inventory getInventory(String[] workOrderRecord){
        Inventory inventory = new Inventory();

        inventory.stockNo = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.InventoryIndexes.STOCK_NO]);
        inventory.warrantyDate = DataHelper.getLocalDateFromU2(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.InventoryIndexes.WARRANTY_DATE]);
        inventory.warrantyDateStr = DataHelper.formatDate(inventory.warrantyDate);
        inventory.chassisNo = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.InventoryIndexes.CHASSIS_NO]);
        inventory.description = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.InventoryIndexes.DESCRIPTION]);
        inventory.serialNo = DataHelper.getEmptyStringAsNull(workOrderRecord[WorkOrderResponseIndexes.WorkOrderIndexes.InventoryIndexes.SERIAL_NO]);

        return inventory;
    }

    private List<String> getSystemConfigurations(int accountId, String location){
        List<String> parameters = new ArrayList<>();
        parameters.add("SYSTEM.CONFIG");
        parameters.add(location);
        parameters.add("RV.CONTROL");
        parameters.add("SYSTEM");
        parameters.add("");

        String[] response = u2Api.callSubroutine(
                accountId,
                location,
                parameters,
                "GET.CONFIG");

        String[] configs =DataHelper.getMultiValue(response[0], 155, U2Delimiters.VM);
        return new ArrayList<>(Arrays.asList(configs));
    }

    // Helper Methods
    private void roundOffJobTotals(JobTotals jobTotals) {
        jobTotals.parts = DataHelper.roundOff(jobTotals.parts, 2);
        jobTotals.labor = DataHelper.roundOff(jobTotals.labor, 2);
        jobTotals.sublet = DataHelper.roundOff(jobTotals.sublet, 2);
        jobTotals.extras = DataHelper.roundOff(jobTotals.extras, 2);
        // jobTotals.tax = DataHelper.roundOff(jobTotals.tax, 2);
        jobTotals.total = DataHelper.roundOff(jobTotals.total, 2);
    }

    private String getSpecialOrderPartsStatusDescription(String status){
        switch (status){
            case "P":
                return "ON PO";
            case "R":
                return "RECEIVED";
            case "C":
                return "CANCELLED";
            case "F":
                return "COMPLETED";
            case "PR":
                return "PARTLY RECD";
            case "BO":
                return "BACK ORDER";
            case "PENDING":
                return "PENDING";
            default:
                return "NO ORDER";
        }
    }

    private String getPurchaseOrderTypeDesc (String purchaseOrderType){
        switch (purchaseOrderType){
            case "P":
                return "Parts";
            case "S":
                return "Sublet";
        }
        return "Misc";
    }

    private String getWorkOrderCategoryDescription(int accountId, String location, String categoryCode) {
        List<Integer> fields = new ArrayList<>();

        fields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getWOControl(location),
                "CAT*"+categoryCode,
                fields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getWorkOrderLocationDescription(int accountId, String location, String workOrderLocation) {

        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getWO(location),
                "03*"+workOrderLocation);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getSalesmenName(int accountId, String location, String salesmanCode) {

        if(salesmanCode == null)
            return null;

        List<Integer> fields = new ArrayList<>();
        fields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getSalesmen(location),
                salesmanCode,
                fields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getWorkOrderStatusDescription(int accountId, String location, String statusCode) {

        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getWO(location),
                "04*"+statusCode);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getTaxDescription(int accountId, String location, String taxCode) {

        if(taxCode == null)
            return null;

        List<Integer> taxFields = new ArrayList<>();
        taxFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getTaxTable(location),
                taxCode,
                taxFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getVendorName(int accountId, String location, String vendorNo) {

        if(vendorNo == null)
            return null;

        List<Integer> vendorFields = new ArrayList<>();
        vendorFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getVendor(location),
                vendorNo,
                vendorFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getCustomerLastName(int accountId, String location, String customerNumber) {

        List<Integer> customerFields = new ArrayList<>();

        customerFields.add(WorkOrderResponseColumns.CustomerColumns.LAST_NAME);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getCustomer(location),
                customerNumber,
                customerFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getCustomerFirstName(int accountId, String location, String customerNumber) {

        List<Integer> customerFields = new ArrayList<>();

        customerFields.add(WorkOrderResponseColumns.CustomerColumns.FIRST_NAME);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getCustomer(location),
                customerNumber,
                customerFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getLaborTypeDescription(int accountId, String location, String type) {

        if(type == null)
            return null;

        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getLabourTypes(location),
                type);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getMechanicDescription(int accountId, String location, String mechanic) {

        if(mechanic == null)
            return null;

        List<Integer> mechanicDescriptionFields = new ArrayList<>();
        mechanicDescriptionFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getMechanics(location),
                mechanic,
                mechanicDescriptionFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getSkillSetDescription(int accountId, String location, String skillSet) {

        if(skillSet == null)
            return null;

        List<Integer> skillSetFields = new ArrayList<>();
        skillSetFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getG2MasterSkillSets(location),
                skillSet,
                skillSetFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getJobStatusDescription(int accountId, String location, String status) {

        if(status == null)
            return null;

        List<Integer> statusFields = new ArrayList<>();
        statusFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getJobStatus(location),
                status,
                statusFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getPartTypesDescription(int accountId, String location, String type) {

        if(type == null)
            return null;

        List<Integer> typeFields = new ArrayList<>();
        typeFields.add(1);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getPartsTypes(location),
                type,
                typeFields);

        return DataHelper.getArrayIndexSafeString(response, 0);
    }

    private String getPurchaseOrderReasonDescription(String reasonCode){
        switch (reasonCode){
            case "C":
                return "Customer";
            case "S":
                return "Stock";
            case "W":
                return "Work Order";
            case "0":
                return  "Others";
            default:
                return null;
        }
    }

    private boolean getMarineMaxCustomClientConfig(int accountId, String location){
        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getRVControls(location),
                "MMAX");

        try {
            return Boolean.parseBoolean(DataHelper.getArrayIndexSafeString(response, 0));
        } catch (Exception e) {
            return false;
        }
    }
}
