package UniverseDBValidation;

public class U2Tables {
    private static String WorkOrder = "WORK.ORDERS";
    private static String Customer = "CUSTOMER";
    private static String Job = "JOB.STATUS";
    private static String WO = "WO.TABLE";
    private static String Salesmen = "SALESMEN";
    private static String WOControl = "WO.CONTROL";
    private static String TaxTable = "TAX.TABLE";
    private static String Vendor = "VENDOR";
    private static String PurchaseOrder = "PO.FILE";
    private static String MiscellaneousPurchaseOrder = "MISC.PO.FILE";
    private static String SpecialOrders = "SPECIAL.ORDERS";

    public static String getWorkOrder(String location) {
        return location + "_" + WorkOrder;
    }

    public static String getCustomer(String location) {
        return location + "_" + Customer;
    }

    public static String getJob(String location) {
        return location + "_" + Job;
    }

    public static String getWO(String location) {
        return location + "_" + WO;
    }

    public static String getSalesmen(String location) {
        return location + "_" + Salesmen;
    }

    public static String getWOControl(String location) {
        return location + "_" + WOControl;
    }

    public static String getTaxTable(String location) {
        return location + "_" + TaxTable;
    }

    public static String getVendor(String location) {
        return location + "_" + Vendor;
    }

    public static String getPurchaseOrder(String location) {
        return location + "_" + PurchaseOrder;
    }

    public static String getMiscellaneousPurchaseOrder(String location) {
        return location + "_" + MiscellaneousPurchaseOrder;
    }

    public static String getSpecialOrders(String location) {
        return location + "_" + SpecialOrders;
    }
}
