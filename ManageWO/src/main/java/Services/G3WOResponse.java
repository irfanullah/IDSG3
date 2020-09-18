package Services;


import java.util.List;

public class G3WOResponse {

    public G3APIWorkOrder WorkOrder;
    public G3APICustomer Customer;
    public G3APIInventory Inventory;
    public List<G3APIJob> Jobs;
    public List<G3APIBillingInfoSummary> BillingInfoSummaries;
    public G3APISpecialOrder SpecialOrder;
    public List<G3APIPurchaseOrder> PurchaseOrders;
    public G3APIRecordMetaData RecordMetaData;
    public class G3APIWorkOrder
    {
        public String WorkOrderNo ;
        public String WorkOrderDate ;
        public String Author ;
        public String StatusCode ;
        public String StatusDesc ;
        public String WorkOrderLocation ;
        public String WorkOrderLocationDesc ;
        public String SalesmanCode ;
        public String SalesmanDesc ;
        public String PromiseTime ;
        public String PromiseDate ;
        public Integer SchedulePriorityCode ;
        public String SchedulePriorityDesc ;
        public String AppointmentDate ;
        public String AppointmentTime ;
        public String InServiceDate ;
        public Double PartsDiscount ;
        public String CategoryCode ;
        public String CategoryDesc ;
        public String TagNo ;
        public Double MileageIn ;
        public Double MileageOut ;
        public String MileageUnitCode ;
        public String MileageUnitDesc ;
        public String CompleteDate ;
        public String CancelDate ;
        public String Comments ;
    }

    public class G3APICustomer
    {
        public String CustomerNo ;
        public String Name ;
        public String Email ;
        public String HomePhone ;
        public String MobilePhone ;
        public String AddressLine1 ;
        public String AddressLine2 ;
        public String City ;
        public String State ;
        public String ZipCode ;
        public String Country ;
    }

    public class G3APIInventory
    {
        public String StockNo ;
        public String WarrantyDate ;
        public String ChassisNo ;
        public String Description ;
        public String SerialNo ;
    }
    public class G3APIJob
    {
        public String JobNo ;
        public String Complaint ;
        public String Cause ;
        public String Correction ;
        public String BillTypeCode ;
        public String BillTypeDesc ;
        public String BillTo ;
        public String BillToDesc ;
        public String StatusCode ;
        public String StatusDesc ;
        public String TaxCode ;
        public String TaxDesc ;
        public String SalesmanCode ;
        public String SalesmanDesc ;
        public Double QuotedAmount ;
        public Double EstimatedAmount ;
        public Double RequiredHours ;
        public Double ActualHours ;
        public Double ChargeHours ;
        public G3APIJobTotals Actuals ;
        public G3APIJobTotals Required ;
        public List<G3APIPart> Parts ;
        public List<G3APILabor> Labor ;
        public List<G3APISublet> Sublets ;
        public List<G3APIExtras> Extras ;
        public List<G3APIG2Comment> Comments ;
        public List<G3APIJobEstimateComment> EstimatedAmountComments ;
    }

    public class G3APIJobEstimateComment
    {
        public String Content ;
        public Double Estimate ;
        public Double Difference ;
        public String CreatedDateTime ;
        public String Author ;

    }

    public class G3APIG2Comment
    {
        public String Content ;
        public String CreatedDateTime ;
        public String CreatedAuthor ;
        public String ModifiedDateTime ;
        public String ModifiedAuthor ;
    }

    public class G3APIExtras
    {
        public String ExtraCode ;
        public String Description ;
        public String TaxCode ;
        public String TaxDesc ;
        public String SalesmanCode ;
        public String SalesmanDesc ;
        public G3APIExtrasPricing Required ;
        public G3APIExtrasPricing Actuals ;
    }

    public class G3APIExtrasPricing
    {
        public Double Quantity ;
        public Double Cost ;
        public Double ListPrice ;
        public Double Extension ;

    }

    public class G3APIJobTotals
    {
        public Double Parts ;
        public Double Labor ;
        public Double Sublet ;
        public Double Extras ;
        public Double Tax ;
        public Double Total ;
    }
    public class G3APIPart
    {
        public String PartNo ;
        public String Description​ ;
        public Double DiscountPercentage​ ;
        public String Type​ ;
        public String TypeDesc​ ;
        public String TaxCode​ ;
        public String TaxDesc​ ;
        public String SalesmanCode​ ;
        public String SalesmanDesc​ ;
        public G3APIPartPricing Actuals ;
        public G3APIPartPricing Required ;
    }
    public class G3APIPartPricing
    {
        public Double Quantity ;
        public Double Cost ;
        public Double Price ;
        public Double Extension ;
    }
    public class G3APILabor
    {
        public String LaborCode ;
        public String Description ;
        public String Type ;
        public String TypeDesc ;
        public String MechanicCode ;
        public String MechanicDesc ;
        public String SkillSetCode ;
        public String SkillSetDesc ;
        public String StatusCode ;
        public String StatusDesc ;
        public String LaborDate ;
        public Double ChargeHours ;
        public String TaxCode ;
        public String TaxDesc ;
        public String FaultCode ;
        public String FaultDesc ;
        public String SalesmanCode ;
        public String SalesmanDesc ;
        public G3APILaborPricing Actuals ;
        public G3APILaborPricing Required ;

    }
    public class G3APILaborPricing
    {
        public Double Hours

        ;
        public Double Rate ;
        public Double Extension ;
    }

    public class G3APISublet
    {
        public String Description ;
        public String VendorNo ;
        public String VendorName ;
        public String PurchaseOrderNo ;
        public String ExpectedDate ;
        public String CompletedDate ;
        public String TaxCode ;
        public String TaxDesc ;
        public String PurchaseOrderComment ;
        public String SalesmanCode ;
        public String SalesmanDesc ;
        public String InvoiceNo ;
        public G3APISubletPricing Required ;
        public G3APISubletPricing Actuals ;
    }
    public class G3APISubletPricing
    {
        public Double Cost ;
        public Double ListPrice ;
    }
    public class G3APIBillingInfoSummary
    {
        public String BillTypeCode ;
        public String BillCode ;
        public String BillTo ;
        public Boolean IsDeductibleCustomer ;
        public String BillInvoiceNo ;
        public Double PartsTotal ;
        public Double LaborTotal ;
        public Double SubletTotal ;
        public Double ExtrasTotal ;
        public Double TaxTotal ;
        public Double Total ;
        public Double PaidAmount ;
    }
    public class G3APISpecialOrder
    {
        public String SpecialOrderNo ;
        public List<G3APISpecialOrderPart> Parts ;
    }
    public class G3APISpecialOrderPart
    {
        public String PartNo ;
        public String VendorNo ;
        public String VendorPartNo ;
        public String Description ;
        public Double Quantity ;
        public Double Price ;
        public String Status ;
        public List<G3APIG2Comment> Comments ;
    }

    public class G3APIPurchaseOrder
    {
        public String PurchaseOrderNo ;
        public String Type ;
        public String Description ;
        public String VendorNo ;
        public String VendorName ;
        public String JobNo ;
        public String EntryDate ;
        public Double Value ;
        public String ExpectedDate ;
        public String CompletedDate ;
        public String OrderedBy ;
        public String InvoiceNo ;
        public String ReasonCode ;
        public String ReasonDesc ;
        public String ShipVia ;
        public String ShipTerms ;
        public String PayTerms ;
    }
    public class G3APIRecordMetaData
    {
        public String HashCode ;
        public String RetrievalTimeUtc ;
    }

}

