package UniverseDBValidation;

import UniverseDBValidation.response.*;
import java.util.List;
import java.util.*;

public class U2WorkOrder {

    private HashMap<String, String> mileageTypeMap;
    private HashMap<String, String> billTypeMap;
    private HashMap<Integer, String> schedulePriorityTypeMap;
    private U2RestApi u2Api;

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

        u2Api = new U2RestApiImpl(u2RestApiBaseUrl, u2RestApiToken);
    }

    public WorkOrderResponse getWorkOrder(int accountId, String location, String workOrderNo) {
        WorkOrderResponse workOrderResponse = new WorkOrderResponse();

        List<Integer> workOrderFields = new ArrayList<>();
        workOrderFields.add(WorkOrderColumns.WorkOrderDate); // 0
        workOrderFields.add(WorkOrderColumns.Author); // 1
        workOrderFields.add(WorkOrderColumns.StatusCode); // 2
        workOrderFields.add(WorkOrderColumns.WorkLocation); // 3
        workOrderFields.add(WorkOrderColumns.SalesmanCode); // 4
        workOrderFields.add(WorkOrderColumns.PromiseDate); // 5
        workOrderFields.add(WorkOrderColumns.PromiseTime); // 6
        workOrderFields.add(WorkOrderColumns.TagNumber); // 7
        workOrderFields.add(WorkOrderColumns.CustomerId); // 8
        workOrderFields.add(WorkOrderColumns.JobNumber); // 9
        workOrderFields.add(WorkOrderColumns.JobDescription); // 10
        workOrderFields.add(WorkOrderColumns.StockChassisNumber); // 11
        workOrderFields.add(WorkOrderColumns.StockSerialNumber); // 12
        workOrderFields.add(WorkOrderColumns.StockDescription); // 13
        workOrderFields.add(WorkOrderColumns.StockWarrantyDate); // 14

        workOrderFields.add(WorkOrderColumns.PatsDiscount); // 15
        workOrderFields.add(WorkOrderColumns.CategoryCode); // 16

        workOrderFields.add(WorkOrderColumns.StockMileageHours); // 17
        workOrderFields.add(WorkOrderColumns.MileageOut);// 18
        workOrderFields.add(WorkOrderColumns.MileageType); // 19
        workOrderFields.add(WorkOrderColumns.DateComplete); // 20
        workOrderFields.add(WorkOrderColumns.CancelDate); // 21
        workOrderFields.add(WorkOrderColumns.PriorityID); // 22
        workOrderFields.add(WorkOrderColumns.AppointmentDate); // 23
        workOrderFields.add(WorkOrderColumns.AppointmentTime); // 24
        workOrderFields.add(WorkOrderColumns.InServiceDate); // 25
        workOrderFields.add(WorkOrderColumns.StockNumber); // 26

        workOrderFields.add(WorkOrderColumns.BillInvNo); // BillingInfoSummary.BillInvoiceNo // 27

        workOrderFields.add(WorkOrderColumns.JobBillType); // 28
        workOrderFields.add(WorkOrderColumns.JobBillID); // 29
        workOrderFields.add(WorkOrderColumns.JobSalesmanCode); // 30
        workOrderFields.add(WorkOrderColumns.JobTaxCode); // 31

        workOrderFields.add(WorkOrderColumns.ExtrasCode); // 32
        workOrderFields.add(WorkOrderColumns.ExtrasDescription); // 33
        workOrderFields.add(WorkOrderColumns.ExtrasTaxCode); // 34
        workOrderFields.add(WorkOrderColumns.ExtraSalesman); // 35
        workOrderFields.add(WorkOrderColumns.ExtrasJobNumber); // 36

        workOrderFields.add(WorkOrderColumns.SubletVendor); // 37
        workOrderFields.add(WorkOrderColumns.SubletDescription); // 38
        workOrderFields.add(WorkOrderColumns.SubletTaxCode); // 39
        workOrderFields.add(WorkOrderColumns.SubletJobNumber); // 40
        workOrderFields.add(WorkOrderColumns.SubletPONumber); // 41
        workOrderFields.add(WorkOrderColumns.SubletPOComments); // 42
        workOrderFields.add(WorkOrderColumns.SubletSalesman); // 43
        workOrderFields.add(WorkOrderColumns.SubletInvioceNumber); // 44

        workOrderFields.add(WorkOrderColumns.JobStatusCode); // 45
        workOrderFields.add(WorkOrderColumns.JobQuotedAmount); // 46
        workOrderFields.add(WorkOrderColumns.JobEstAmt); // 47

        workOrderFields.add(WorkOrderColumns.SubletCompleteDate); // 48
        workOrderFields.add(WorkOrderColumns.SubletExpectedDate); // 49

        workOrderFields.add(WorkOrderColumns.JobEstimateCommentUser); // 50
        workOrderFields.add(WorkOrderColumns.JobEstimateCommentContent); // 51
        workOrderFields.add(WorkOrderColumns.JobEstimateCommentDate); // 52
        workOrderFields.add(WorkOrderColumns.JobEstimateCommentTime); // 53
        workOrderFields.add(WorkOrderColumns.JobEstimateCommentAmount); // 54

        workOrderFields.add(WorkOrderColumns.SubletUsedBillAmount); // 55
        workOrderFields.add(WorkOrderColumns.SubletUsedCost); // 56
        workOrderFields.add(WorkOrderColumns.SubletRequiredCost); // 57
        workOrderFields.add(WorkOrderColumns.SubletRequiredList); // 59

        workOrderFields.add(WorkOrderColumns.ExtrasQtyRequired); // 59
        workOrderFields.add(WorkOrderColumns.ExtrasRequiredCostPer); // 60
        workOrderFields.add(WorkOrderColumns.ExtrasRequiredListPer); // 61
        workOrderFields.add(WorkOrderColumns.ExtrasRequiredListExtension); // 62

        workOrderFields.add(WorkOrderColumns.ExtrasQtyUsed); // 63
        workOrderFields.add(WorkOrderColumns.ExtrasUsedCostPer); // 64
        workOrderFields.add(WorkOrderColumns.ExtrasUsedListPer); // 65
        workOrderFields.add(WorkOrderColumns.ExtrasUsedListExtension); // 66

        workOrderFields.add(WorkOrderColumns.Cause); // 67
        workOrderFields.add(WorkOrderColumns.Correction); // 68
        workOrderFields.add(WorkOrderColumns.ActualHours); // 69
        workOrderFields.add(WorkOrderColumns.HoursBilled); // 70
        workOrderFields.add(WorkOrderColumns.TotalHours); // 71

        // Jobs Required and Actuals ------ Start
        //Required
        workOrderFields.add(WorkOrderColumns.PartsTotalCostbyJob); // 72
        workOrderFields.add(WorkOrderColumns.LaborTotalCostbyJob); // 73
        workOrderFields.add(WorkOrderColumns.SubletTotalCostbyJob); // 74
        workOrderFields.add(WorkOrderColumns.ExtraTotalCostbyJob); // 75

        workOrderFields.add(WorkOrderColumns.PartsTotalListbyJob); // 76
        workOrderFields.add(WorkOrderColumns.LaborTotalListbyJob); // 77
        workOrderFields.add(WorkOrderColumns.SubletTotalListbyJob); // 78
        workOrderFields.add(WorkOrderColumns.ExtrasTotalListbyJob); // 79
        // Jobs Required and Actuals ------ End

        workOrderFields.add(WorkOrderColumns.PartNumber); // 80
        workOrderFields.add(WorkOrderColumns.LaborCode); // 81
        workOrderFields.add(WorkOrderColumns.Comments); // 82

        workOrderFields.add(WorkOrderColumns.PartsPurchaseOrderNumbers); // 83
        workOrderFields.add(WorkOrderColumns.AllSubletPos); // 84

        workOrderFields.add(WorkOrderColumns.SpecialOrderNumber); // 85

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getWorkOrder(location),
                workOrderNo,
                workOrderFields);

        WorkOrder workOrder = new WorkOrder();
        workOrder.WorkOrderNo = workOrderNo;
        workOrder.WorkOrderDate = DataHelper.getLocalDateFromU2(response[0]);
        workOrder.Author = DataHelper.getEmptyStringAsNull(response[1]);
        workOrder.StatusCode = DataHelper.getEmptyStringAsNull(response[2]);
        workOrder.StatusDesc = getWorkOrderStatusDescription(accountId, location, workOrder.StatusCode);
        workOrder.WorkOrderLocation = DataHelper.getEmptyStringAsNull(response[3]);
        workOrder.WorkOrderLocationDesc = getWorkOrderLocationDescription(accountId, location, workOrder.WorkOrderLocation);
        workOrder.SalesmanCode = DataHelper.getEmptyStringAsNull(response[4]);
        workOrder.SalesmanDesc = getSalesmenName(accountId, location, workOrder.SalesmanCode);
        workOrder.PromiseDateTime = DataHelper.getLocalDateTimeFromU2(response[5],response[6]);
        workOrder.TagNo = DataHelper.getEmptyStringAsNull(response[7]);
        String CustomerNo = response[8];
        String stockChassisNumber = DataHelper.getEmptyStringAsNull(response[11]);
        String stockSerialNumber = DataHelper.getEmptyStringAsNull(response[12]);
        String stockDescription = DataHelper.getEmptyStringAsNull(response[13]);
        workOrder.PartsDiscount = DataHelper.getNullableDouble(response[15]);
        workOrder.CategoryCode = DataHelper.getEmptyStringAsNull(response[16]);
        workOrder.CategoryDesc = getWorkOrderCategoryDescription(accountId, location, workOrder.CategoryCode);
        workOrder.MileageIn = DataHelper.getNullableDouble(response[17]);
        workOrder.MileageOut = DataHelper.getNullableDouble(response[18]);
        workOrder.MileageUnitCode = DataHelper.getEmptyStringAsNull(response[19]);
        workOrder.MileageUnitDesc = mileageTypeMap.get(workOrder.MileageUnitCode);
        workOrder.CompleteDate = DataHelper.getLocalDateFromU2(response[20]);
        workOrder.CancelDate  = DataHelper.getLocalDateFromU2(response[21]);
        workOrder.SchedulePriorityCode = DataHelper.getNullableInteger(response[22]);
        workOrder.SchedulePriorityDesc = schedulePriorityTypeMap.get(workOrder.SchedulePriorityCode);
        workOrder.AppointmentDateTime = DataHelper.getLocalDateTimeFromU2(response[23],response[24]);
        workOrder.InServiceDate = DataHelper.getLocalDateFromU2(response[25]);
        String stockNumber = DataHelper.getEmptyStringAsNull(response[26]);

        // BillingInfoSummary.BillInvoiceNo = response[27];
        workOrderResponse.WorkOrder = workOrder;

        Inventory inventory = new Inventory();
        inventory.StockNo = stockNumber;
        inventory.ChassisNo = stockChassisNumber;
        inventory.Description = stockDescription;
        inventory.SerialNo = stockSerialNumber;
        inventory.WarrantyDate = DataHelper.getLocalDateFromU2(response[14]);
        workOrderResponse.Inventory = inventory;

        workOrderResponse.Jobs = getJobs(accountId, location, response);

        Customer customer = getCustomer(accountId, location, CustomerNo);
        workOrderResponse.Customer = customer;

        workOrderResponse.PurchaseOrders = getPurchaseOrders(accountId, location, response);

        workOrderResponse.SpecialOrder = getSpecialOrder(accountId, location, response);

        return workOrderResponse;
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

    private SpecialOrder getSpecialOrder(int accountId, String location, String[] workOrderResponse) {
        SpecialOrder specialOrder = new SpecialOrder();
        specialOrder.Parts = new ArrayList<>();

        String specialOrderNo = workOrderResponse[85].trim();

        if(specialOrderNo.isEmpty())
        return specialOrder;

        List<Integer> specialOrderFields = new ArrayList<>();
        specialOrderFields.add(3); // Parts No
        specialOrderFields.add(8); // Vendor
        specialOrderFields.add(10); // VendorPartNo
        specialOrderFields.add(11); // Part Description
        specialOrderFields.add(5); // Quantity
        specialOrderFields.add(12); // Price
        specialOrderFields.add(9); // Status

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getSpecialOrders(location),
                specialOrderNo,
                specialOrderFields);

        String[] partsNo = response[0].split(U2Delimiters.VM);
        String[] vendor = response[1].split(U2Delimiters.VM);
        String[] vendorPartNo = response[2].split(U2Delimiters.VM);
        String[] partDescription = response[3].split(U2Delimiters.VM);
        String[] quantity = response[4].split(U2Delimiters.VM);
        String[] price = response[5].split(U2Delimiters.VM);
        String[] status = response[6].split(U2Delimiters.VM);

        for(int i=0;i< partsNo.length;i++){
            String pNo = partsNo[i].trim();

            if(pNo.isEmpty())
                continue;

            SpecialOrderPart specialOrderPart= new SpecialOrderPart();

            specialOrderPart.PartNo = pNo;
            specialOrderPart.VendorNo = vendor[i];
            specialOrderPart.VendorPartNo = vendorPartNo[i];
            specialOrderPart.Description = partDescription[i];
            specialOrderPart.Quantity = DataHelper.getDoubleFromU2(quantity[i]);
            specialOrderPart.Price = DataHelper.getDoubleFromU2(price[i]);
            specialOrderPart.Status = getSpecialOrderPartsStatusDescription(status[i]);

            specialOrder.Parts.add(specialOrderPart);
        }

        return specialOrder;
    }

    private List<PurchaseOrder> getPurchaseOrders(int accountId, String location, String[] response) {
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();

        String[] partsNo = response[83].split(U2Delimiters.VM);
        for(int i=0;i< partsNo.length;i++){
            PurchaseOrder purchaseOrder = new PurchaseOrder();

            purchaseOrder.PurchaseOrderNo = DataHelper.getEmptyStringAsNull(partsNo[i]);

            if(purchaseOrder.PurchaseOrderNo == null)
                continue;

            purchaseOrder.Type = getPurchaseOrderTypeDesc("P");

            populatePurchaseOrder(accountId, location, purchaseOrder);

            purchaseOrders.add(purchaseOrder);
        }

        String[] subletsNo = response[41].split(U2Delimiters.VM);
        String[] subletDescription = response[38].split(U2Delimiters.VM);
        String[] subletJobNumber = response[40].split(U2Delimiters.VM);
        for(int i=0;i< subletsNo.length;i++){
            PurchaseOrder purchaseOrder = new PurchaseOrder();

            purchaseOrder.PurchaseOrderNo = DataHelper.getEmptyStringAsNull(subletsNo[i]);

            if(purchaseOrder.PurchaseOrderNo == null)
                continue;

            purchaseOrder.Type = getPurchaseOrderTypeDesc("S");
            purchaseOrder.JobNo = subletJobNumber[i];
            purchaseOrder.Description = subletDescription[i];

            populatePurchaseOrder(accountId, location, purchaseOrder);

            purchaseOrders.add(purchaseOrder);
        }

        String[] allSubletsNo = response[84].split(U2Delimiters.VM);
        for(int i=0;i< allSubletsNo.length;i++){
            PurchaseOrder purchaseOrder = new PurchaseOrder();

            purchaseOrder.PurchaseOrderNo = DataHelper.getEmptyStringAsNull(allSubletsNo[i]);

            if(purchaseOrder.PurchaseOrderNo == null)
                continue;

            purchaseOrder.Type = getPurchaseOrderTypeDesc("M");
            purchaseOrder.Description = "SUBLET / MISCELLANEOUS";

            populatePurchaseOrder(accountId, location, purchaseOrder);

            purchaseOrders.add(purchaseOrder);
        }

        return purchaseOrders;
    }

    private Customer getCustomer(int accountId, String location, String customerNumber) {
        Customer customer = new Customer();

        List<Integer> customerFields = new ArrayList<>();
        customerFields.add(WorkOrderColumns.CustomerColumns.FirstName);
        customerFields.add(WorkOrderColumns.CustomerColumns.LastName);
        customerFields.add(WorkOrderColumns.CustomerColumns.CustomerEmailAddress);
        customerFields.add(WorkOrderColumns.CustomerColumns.HomePhone);
        customerFields.add(WorkOrderColumns.CustomerColumns.CustomerZipCode);
        customerFields.add(WorkOrderColumns.CustomerColumns.CustomerCountry);
        customerFields.add(WorkOrderColumns.CustomerColumns.Addresses);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getCustomer(location),
                customerNumber,
                customerFields);

        customer.CustomerNo = customerNumber;
        customer.Name = response[0] + " " + response[1];
        customer.Email = response[2];
        customer.HomePhone = response[3];
        customer.ZipCode = response[4];
        customer.Country = response[5];
        String[] addresses = response[6].split(U2Delimiters.VM);
        customer.AddressLine1 = DataHelper.getFirstLetterInUpperCase(addresses[0]);
        customer.City = DataHelper.getFirstLetterInUpperCase(addresses[1]);
        return customer;
    }

    private HashMap<String, List<Extras>> getExtras(int accountId, String location, String[] response) {
        String[] extraCodes =  response[32].split(U2Delimiters.VM);
        String[] extrasDescriptions = response[33].split(U2Delimiters.VM);
        String[] extrasTaxCodes = response[34].split(U2Delimiters.VM);
        String[] extrasSalesman = response[35].split(U2Delimiters.VM);
        String[] extrasJobNumbers = response[36].split(U2Delimiters.VM);

        String[] extrasRequiredQtys = response[59].split(U2Delimiters.VM);
        String[] extrasRequiredCosts = response[60].split(U2Delimiters.VM);
        String[] extrasRequiredListPrices = response[61].split(U2Delimiters.VM);
        String[] extrasRequiredExtensions = response[62].split(U2Delimiters.VM);

        String[] extrasActualsQtys = response[63].split(U2Delimiters.VM);
        String[] extrasActualsCosts = response[64].split(U2Delimiters.VM);
        String[] extrasActualsListPrices = response[65].split(U2Delimiters.VM);
        String[] extrasActualsExtensions = response[66].split(U2Delimiters.VM);

        int size = extraCodes.length;
        HashMap<String, List<Extras>> extrasMap = new HashMap<>();

        for(int i=0;i<size;i++){

            Extras extra = new Extras();
            extra.ExtraCode = extraCodes[i];
            extra.Description = extrasDescriptions[i];
            extra.TaxCode = extrasTaxCodes[i];
            extra.TaxDesc = getTaxDescription(accountId, location, extra.TaxCode);

            if(i<= extrasSalesman.length-1) {
                extra.SalesmanCode = DataHelper.getEmptyStringAsNull(extrasSalesman[i]);
                extra.SalesmanDesc = getSalesmenName(accountId, location, extra.SalesmanCode);
            }

            ExtrasPricing required = new ExtrasPricing();
            ExtrasPricing actuals = new ExtrasPricing();

            required.Quantity = getArrayIndexSafeDouble(extrasRequiredQtys, i);
            required.Cost = getArrayIndexSafeDouble(extrasRequiredCosts, i);
            required.ListPrice = getArrayIndexSafeDouble(extrasRequiredListPrices, i);
            required.Extension = getArrayIndexSafeDouble(extrasRequiredExtensions, i);

            actuals.Quantity = getArrayIndexSafeDouble(extrasActualsQtys, i);
            actuals.Cost = getArrayIndexSafeDouble(extrasActualsCosts, i);
            actuals.ListPrice = getArrayIndexSafeDouble(extrasActualsListPrices, i);
            actuals.Extension = getArrayIndexSafeDouble(extrasActualsExtensions, i);

            extra.Actuals = actuals;
            extra.Required = required;

            String jobNumber = extrasJobNumbers[i];

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

    private List<Job> getJobs(int accountId, String location, String[] response) {
        String[] jobNumbers = response[9].split(U2Delimiters.VM);
        String[] jobDescriptions = response[10].split(U2Delimiters.VM);
        String[] jobBillType = response[28].split(U2Delimiters.VM);
        String[] jobBillID = response[29].split(U2Delimiters.VM);
        String[] jobSalesmanCode = response[30].split(U2Delimiters.VM);
        String[] jobTaxCode = response[31].split(U2Delimiters.VM);
        String[] jobStatusCodes = response[45].split(U2Delimiters.VM);
        String[] jobQuotedAmounts = response[46].split(U2Delimiters.VM);
        String[] jobEstimatedAmounts = response[47].split(U2Delimiters.VM);

        String[] jobEstimateCommentUsers = response[50].split(U2Delimiters.VM);
        String[] jobEstimateCommentContents = response[51].split(U2Delimiters.VM);
        String[] jobEstimateCommentDates = response[52].split(U2Delimiters.VM);
        String[] jobEstimateCommentTimes = response[53].split(U2Delimiters.VM);
        String[] jobEstimateCommentAmounts = response[54].split(U2Delimiters.VM);

        String[] jobCauses = response[67].split(U2Delimiters.VM);
        String[] jobCorrection = response[68].split(U2Delimiters.VM);
        String[] jobActualHours = response[69].split(U2Delimiters.VM);
        String[] jobHoursBilled = response[70].split(U2Delimiters.VM);
        String[] jobTotalHours = response[71].split(U2Delimiters.VM);

        String[] jobRequiredParts = response[72].split(U2Delimiters.VM);
        String[] jobRequiredLabor = response[73].split(U2Delimiters.VM);
        String[] jobRequiredSublet = response[74].split(U2Delimiters.VM);
        String[] jobRequiredExtra = response[75].split(U2Delimiters.VM);

        String[] jobActualsParts = response[76].split(U2Delimiters.VM);
        String[] jobActualsLabor = response[77].split(U2Delimiters.VM);
        String[] jobActualsSublet = response[78].split(U2Delimiters.VM);
        String[] jobActualsExtra = response[79].split(U2Delimiters.VM);

        HashMap<String, List<Extras>> extras = getExtras(accountId, location, response);
        HashMap<String, List<Sublet>> sublets = getSublets(accountId, location, response);
        int size = jobNumbers.length;
        Job[] jobs = new Job[size];

        for(int i=0;i<size;i++){
            jobs[i] = new Job();
            jobs[i].JobNo = jobNumbers[i];
            jobs[i].Complaint = jobDescriptions[i];
            jobs[i].BillTypeCode = jobBillType[i];
            jobs[i].BillTypeDesc = billTypeMap.get(jobs[i].BillTypeCode);
            jobs[i].SalesmanCode = DataHelper.getEmptyStringAsNull(jobSalesmanCode[i]);
            jobs[i].SalesmanDesc = getSalesmenName(accountId, location, jobs[i].SalesmanCode);
            jobs[i].TaxCode = jobTaxCode[i];
            jobs[i].TaxDesc = getTaxDescription(accountId, location, jobs[i].TaxCode);
            jobs[i].BillTo = jobBillID[i];
            jobs[i].BillToDesc = getCustomerLastName(accountId, location, jobs[i].BillTo);
            if(i<=jobStatusCodes.length-1) {
                jobs[i].StatusCode = jobStatusCodes[i];
                jobs[i].StatusDesc = getJobStatusDescription(accountId, location, jobs[i].StatusCode);
            }
            if(i<=jobQuotedAmounts.length-1)
                jobs[i].QuotedAmount = DataHelper.getDoubleFromU2(jobQuotedAmounts[i]);
            if(i<=jobEstimatedAmounts.length-1)
                jobs[i].EstimatedAmount = DataHelper.getDoubleFromU2(jobEstimatedAmounts[i]);

            jobs[i].Cause = getArrayIndexSafeString(jobCauses,i);
            jobs[i].Correction = getArrayIndexSafeString(jobCorrection,i);

            jobs[i].ActualHours = getArrayIndexSafeDouble(jobActualHours,i);
            jobs[i].ChargeHours = getArrayIndexSafeDouble(jobHoursBilled,i);
            jobs[i].RequiredHours = getArrayIndexSafeDouble(jobTotalHours,i);

            JobTotals actuals = new JobTotals();
            JobTotals required = new JobTotals();

            actuals.Extras = getArrayIndexSafeDouble(jobActualsExtra,i);
            actuals.Sublet = getArrayIndexSafeDouble(jobActualsSublet,i);
            actuals.Parts = getArrayIndexSafeDouble(jobActualsParts,i);
            actuals.Labor = getArrayIndexSafeDouble(jobActualsLabor,i);

            required.Extras = getArrayIndexSafeDouble(jobRequiredExtra,i);
            required.Sublet = getArrayIndexSafeDouble(jobRequiredSublet,i);
            required.Parts = getArrayIndexSafeDouble(jobRequiredParts,i);
            required.Labor = getArrayIndexSafeDouble(jobRequiredLabor,i);

            jobs[i].Required = required;
            jobs[i].Actuals = actuals;

            jobs[i].Extras = extras.get(jobs[i].JobNo);
            jobs[i].Sublets = sublets.get(jobs[i].JobNo);

            if(i<=jobEstimateCommentUsers.length-1){
                List<JobEstimateComment> jobEstimateComments = new ArrayList<>();
                JobEstimateComment jobEstimateComment = new JobEstimateComment();
                jobEstimateComment.Author = jobEstimateCommentUsers[i];
                jobEstimateComment.CreatedDateTime = DataHelper.getLocalDateTimeFromU2(jobEstimateCommentDates[i],
                        jobEstimateCommentTimes[i]);
                jobEstimateComment.Content = jobEstimateCommentContents[i];
                jobEstimateComment.Estimate = DataHelper.getDoubleFromU2(jobEstimateCommentAmounts[i]);
                jobEstimateComments.add(jobEstimateComment);
                jobs[i].EstimatedAmountComments = jobEstimateComments;
            }
        }

        return new ArrayList<Job>(Arrays.asList(jobs));
    }

    private HashMap<String, List<Sublet>> getSublets(int accountId, String location, String[] response) {
        String[] subletsVendorCodes =  response[37].split(U2Delimiters.VM);
        String[] subletsDescriptions = response[38].split(U2Delimiters.VM);
        String[] subletsTaxCodes = response[39].split(U2Delimiters.VM);
        String[] subletsJobNumbers = response[40].split(U2Delimiters.VM);
        String[] subletsPurchaseOrderNumbers = response[41].split(U2Delimiters.VM);
        String[] subletsPurchaseOrderComments = response[42].split(U2Delimiters.VM);
        String[] subletsSalesman = response[43].split(U2Delimiters.VM);
        String[] subletsInvoiceNumbers = response[44].split(U2Delimiters.VM);
        String[] subletsCompleteDate = response[48].split(U2Delimiters.VM);
        String[] subletsExpectedDate = response[49].split(U2Delimiters.VM);

        String[] subletsRequiredCosts = response[57].split(U2Delimiters.VM);
        String[] subletsRequiredLists = response[58].split(U2Delimiters.VM);

        String[] subletsActualsCosts = response[56].split(U2Delimiters.VM);
        String[] subletsActualsLists = response[55].split(U2Delimiters.VM);

        int size = subletsVendorCodes.length;
        HashMap<String, List<Sublet>> subletsMap = new HashMap<>();

        for(int i=0;i<size;i++){

            Sublet sublet = new Sublet();
            sublet.VendorNo = subletsVendorCodes[i];
            sublet.VendorName = getVendorName(accountId, location, sublet.VendorNo);
            sublet.Description = subletsDescriptions[i];
            sublet.TaxCode = subletsTaxCodes[i];
            sublet.TaxDesc = getTaxDescription(accountId, location, sublet.TaxCode);

            if(i<=subletsCompleteDate.length-1)
                sublet.CompletedDate = DataHelper.getLocalDateFromU2(subletsCompleteDate[i]);

            if(i<=subletsExpectedDate.length-1)
                sublet.ExpectedDate = DataHelper.getLocalDateFromU2(subletsExpectedDate[i]);

            sublet.PurchaseOrderNo = subletsPurchaseOrderNumbers[i];

            if(i<=subletsPurchaseOrderComments.length-1)
                sublet.PurchaseOrderComment = DataHelper.getEmptyStringAsNull(subletsPurchaseOrderComments[i]);

            sublet.SalesmanCode = subletsSalesman[i];
            sublet.SalesmanDesc = getSalesmenName(accountId, location, sublet.SalesmanCode);
            sublet.InvoiceNo = subletsInvoiceNumbers[i];

            SubletPricing required = new SubletPricing();
            SubletPricing actuals = new SubletPricing();

            required.Cost = DataHelper.getDoubleFromU2(subletsRequiredCosts[i]);
            required.ListPrice = DataHelper.getDoubleFromU2(subletsRequiredLists[i]);

            actuals.Cost = DataHelper.getDoubleFromU2(subletsActualsCosts[i]);
            actuals.ListPrice = DataHelper.getDoubleFromU2(subletsActualsLists[i]);

            sublet.Required = required;
            sublet.Actuals = actuals;

            String jobNumber = subletsJobNumbers[i];

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

    private String getCustomerLastName(int accountId, String location, String customerNumber) {
        List<Integer> customerFields = new ArrayList<>();
        customerFields.add(WorkOrderColumns.CustomerColumns.LastName);

        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getCustomer(location),
                customerNumber,
                customerFields);

        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
    }

    private String getJobStatusDescription(int accountId, String location, String status) {
        if(status == null)
            return null;
        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getJob(location),
                status);
        return response[0];
    }

    private String getWorkOrderCategoryDescription(int accountId, String location, String categoryCode) {
        List<Integer> fields = new ArrayList<Integer>();
        fields.add(1);
        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getWOControl(location),
                "CAT*"+categoryCode,
                fields);
        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
    }

    private String getWorkOrderLocationDescription(int accountId, String location, String workOrderLocation) {
        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getWO(location),
                "03*"+workOrderLocation);
        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
    }

    private String getSalesmenName(int accountId, String location, String salesmanCode) {
        if(salesmanCode == null)
            return null;

        List<Integer> fields = new ArrayList<Integer>();
        fields.add(1);
        String[] response = u2Api.readFields(
                accountId,
                location,
                U2Tables.getSalesmen(location),
                salesmanCode,
                fields);
        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
    }

    private String getWorkOrderStatusDescription(int accountId, String location, String statusCode) {
        String[] response = u2Api.readRecord(
                accountId,
                location,
                U2Tables.getWO(location),
                "04*"+statusCode);
        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
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

        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
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

        if(Objects.isNull(response) || response.length == 0)
            return null;
        return response[0];
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
        purchaseOrderFields.add(9); // All sublets Descriptions

        String tableName = U2Tables.getMiscellaneousPurchaseOrder(location);

        if("Parts".equalsIgnoreCase(order.Type)){
            tableName = U2Tables.getPurchaseOrder(location);
        }

        String[] response = u2Api.readFields(
                accountId,
                location,
                tableName,
                order.PurchaseOrderNo,
                purchaseOrderFields);

        order.VendorNo = DataHelper.getEmptyStringAsNull(response[0]);
        order.VendorName = getVendorName(accountId, location, order.VendorNo);
        order.EntryDate = DataHelper.getLocalDateFromU2(response[1]);
        order.ExpectedDate = DataHelper.getLocalDateFromU2(response[2]);
        order.CompletedDate = DataHelper.getLocalDateFromU2(response[3]);
        order.OrderedBy = DataHelper.getEmptyStringAsNull(response[4]);
        order.InvoiceNo = DataHelper.getEmptyStringAsNull(response[5]);
        if(!"Parts".equalsIgnoreCase(order.Type)) {
            order.ReasonCode = DataHelper.getEmptyStringAsNull(response[6]);
            order.ReasonDesc = getPurchaseOrderReasonDescription(order.ReasonCode);
        }
        order.ShipVia = DataHelper.getEmptyStringAsNull(response[7]);
        order.ShipTerms = DataHelper.getEmptyStringAsNull(response[8]);
        order.PayTerms = DataHelper.getEmptyStringAsNull(response[9]);
        order.Value = DataHelper.getDoubleFromU2(response[10]) * DataHelper.getDoubleFromU2(response[11]);

        if("Parts".equalsIgnoreCase(order.Type)){
            order.Description = response[12].replace(U2Delimiters.VM, ",");
        } else if (!"Sublet".equalsIgnoreCase(order.Type)){
            order.Description = response[13].replace(U2Delimiters.VM, ",");
        }

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

    private Double getArrayIndexSafeDouble(String[] strings, int index){
        if(index<=strings.length-1)
            return DataHelper.getDoubleFromU2(strings[index]);
        return 0.0;
    }

    private String getArrayIndexSafeString(String[] strings, int index){
        if(index<=strings.length-1)
            return DataHelper.getEmptyStringAsNull(strings[index]);
        return null;
    }
}
