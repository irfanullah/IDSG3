package UniverseDBValidation;

public class U2Tables {

    private static final String WORK_ORDERS = "WORK.ORDERS";
    private static final String CUSTOMER = "CUSTOMER";
    private static final String JOB_STATUS = "JOB.STATUS";
    private static final String WO_TABLE = "WO.TABLE";
    private static final String SALESMEN = "SALESMEN";
    private static final String WO_CONTROL = "WO.CONTROL";
    private static final String TAX_TABLE = "TAX.TABLE";
    private static final String VENDOR = "VENDOR";
    private static final String PO_FILE = "PO.FILE";
    private static final String MISC_PO_FILE = "MISC.PO.FILE";
    private static final String SPECIAL_ORDERS = "SPECIAL.ORDERS";
    private static final String RV_CONTROL = "RV.CONTROL";
    private static final String LABOUR_TYPES = "LABOUR.TYPES";
    private static final String MECHANICS = "MECHANICS";
    private static final String G2_MASTER_SKILL_SETS = "G2.MASTER.SKILL.SETS";
    private static final String G2_COMMENTS = "G2.COMMENTS";
    private static final String G2_COMMENTS_XREF = "G2.COMMENTS.XREF";
    private static final String PARTS_TYPES = "PARTS.TYPES";
    private static final String WO_BILL_CODE = "WO.BILL.CODE";

    public static String getWoBillCode(String location) {
        return location + "_" + WO_BILL_CODE;
    }

    public static String getPartsTypes(String location) {
        return location + "_" + PARTS_TYPES;
    }

    public static String getG2CommentsXref(String location) {
        return location + "_" + G2_COMMENTS_XREF;
    }

    public static String getG2Comments(String location) {
        return location + "_" + G2_COMMENTS;
    }

    public static String getLabourTypes(String location) {
        return location + "_" + LABOUR_TYPES;
    }

    public static String getMechanics(String location) {
        return location + "_" + MECHANICS;
    }

    public static String getG2MasterSkillSets(String location) {
        return location + "_" + G2_MASTER_SKILL_SETS;
    }

    public static String getWorkOrder(String location) {
        return location + "_" + WORK_ORDERS;
    }

    public static String getCustomer(String location) {
        return location + "_" + CUSTOMER;
    }

    public static String getJobStatus(String location) {
        return location + "_" + JOB_STATUS;
    }

    public static String getWO(String location) {
        return location + "_" + WO_TABLE;
    }

    public static String getSalesmen(String location) {
        return location + "_" + SALESMEN;
    }

    public static String getWOControl(String location) {
        return location + "_" + WO_CONTROL;
    }

    public static String getTaxTable(String location) {
        return location + "_" + TAX_TABLE;
    }

    public static String getVendor(String location) {
        return location + "_" + VENDOR;
    }

    public static String getPurchaseOrder(String location) {
        return location + "_" + PO_FILE;
    }

    public static String getMiscellaneousPurchaseOrder(String location) {
        return location + "_" + MISC_PO_FILE;
    }

    public static String getSpecialOrders(String location) {
        return location + "_" + SPECIAL_ORDERS;
    }

    public static String getRVControls(String location){
        return location + "_" + RV_CONTROL;
    }
}
