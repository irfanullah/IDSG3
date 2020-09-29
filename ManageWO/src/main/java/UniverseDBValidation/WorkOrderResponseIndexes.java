package UniverseDBValidation;

public class WorkOrderResponseIndexes {

    public static class CustomerIndexes {
        public static final int FIRST_NAME = 0;
        public static final int LAST_NAME = 1;
        public static final int EMAIL_ADDRESS = 2;
        public static final int HOME_PHONE = 3;
        public static final int POSTAL_CODE = 4;
        public static final int COUNTRY = 5;
        public static final int ADDRESS = 6;
        public static final int CELL_PHONE = 7;
    }

    public static class SpecialOrderIndexes {
        public static class SpecialOrderPartIndexes {
            public static final int PART_NO = 0;
            public static final int VENDOR_NO = 1;
            public static final int VENDOR_PART_NO = 2;
            public static final int DESCRIPTION = 3;
            public static final int QUANTITY = 4;
            public static final int PRICE = 5;
            public static final int STATUS = 6;
        }
    }

    public static class WorkOrderIndexes {
        public static final int WORK_ORDER_DATE = 0;
        public static final int AUTHOR = 1;
        public static final int STATUS_CODE = 2;
        public static final int WORK_ORDER_LOCATION = 3;
        public static final int SALESMAN_CODE = 4;
        public static final int PROMISE_DATE = 5;
        public static final int PROMISE_TIME = 6;
        public static final int SCHEDULE_PRIORITY_CODE = 7;
        public static final int APPOINTMENT_DATE = 8;
        public static final int APPOINTMENT_TIME = 9;
        public static final int IN_SERVICE_DATE = 10;
        public static final int PARTS_DISCOUNT = 11;
        public static final int CATEGORY_CODE = 12;
        public static final int TAG_NO = 13;
        public static final int MILEAGE_IN = 14;
        public static final int MILEAGE_OUT = 15;
        public static final int MILEAGE_UNIT_CODE = 16;
        public static final int COMPLETE_DATE = 17;
        public static final int CANCEL_DATE = 18;
        public static final int COMMENTS = 19;

        public static class InventoryIndexes {
            public static final int STOCK_NO = 20;
            public static final int WARRANTY_DATE = 21;
            public static final int CHASSIS_NO = 22;
            public static final int DESCRIPTION = 23;
            public static final int SERIAL_NO = 24;
        }

        public static class JobIndexes {
            public static final int JOB_NO = 25;
            public static final int DESCRIPTION = 26;
            public static final int BILL_TYPE_CODE = 27;
            public static final int BILL_TO = 28;
            public static final int SALESMAN_CODE = 29;
            public static final int TAX_CODE = 30;
            public static final int STATUS_CODE = 31;
            public static final int QUOTED_AMOUNT = 32;
            public static final int ESTIMATED_AMOUNT = 33;

            public static class ExtrasIndexes {
                public static final int EXTRA_CODE = 34;
                public static final int DESCRIPTION = 35;
                public static final int TAX_CODE = 36;
                public static final int SALESMAN_CODE = 37;
                public static final int EXTRA_JOB_NUMBER = 38;

                public static class RequiredExtrasPricingIndexes{
                    public static final int QUANTITY = 39;
                    public static final int COST = 40;
                    public static final int LIST_PRICE = 41;
                    public static final int EXTENSION = 42;
                }

                public static class ActualsJExtrasPricingIndexes{
                    public static final int QUANTITY = 43;
                    public static final int COST = 44;
                    public static final int LIST_PRICE = 45;
                    public static final int EXTENSION = 46;
                }
            }

            public static class SubletsIndexes {
                public static final int DESCRIPTION = 47;
                public static final int VENDOR_NO = 48;
                public static final int PURCHASE_ORDER_NO = 49;
                public static final int EXPECTED_DATE = 50;
                public static final int COMPLETED_DATE = 51;
                public static final int TAX_CODE = 52;
                public static final int PURCHASE_ORDER_COMMENT = 53;
                public static final int SALESMAN_CODE = 54;
                public static final int INVOICE_NO = 55;
                public static final int SUBLET_JOB_NUMBER = 56;

                public static class RequiredSubletPricingIndexes{
                    public static final int COST = 57;
                    public static final int LIST_PRICE = 58;
                }

                public static class ActualsSubletPricingIndexes{
                    public static final int COST = 59;
                    public static final int LIST_PRICE = 60;
                }
            }

            public static class LaborsIndexes {
                public static final int LABOR_CODE = 61;
                public static final int DESCRIPTION = 62;
                public static final int TYPE = 63;
                public static final int MECHANIC_CODE = 64;
                public static final int SKILL_SET_CODE = 65;
                public static final int STATUS_CODE = 66;
                public static final int LABOR_DATE = 67;
                public static final int CHARGE_HOURS = 68;
                public static final int TAX_CODE = 69;
                public static final int FAULT_CODE = 70;
                public static final int SALESMAN_CODE = 71;
                public static final int LABOR_JOB_NUMBER = 72;

                public static class ActualsLaborPricing {
                    public static final int HOURS = 73;
                    public static final int RATE = 74;
                    public static final int EXTENSION = 75;
                }

                public static class RequiredLaborPricing {
                    public static final int HOURS = 76;
                    public static final int RATE = 77;
                    public static final int EXTENSION = 78;
                }
            }

            public static class PartsIndexes {
                public static final int PART_NO = 79;
                public static final int DESCRIPTION = 80;
                public static final int DISCOUNT_PERCENTAGE = 81;
                public static final int TYPE = 82;
                public static final int TAX_CODE = 83;
                public static final int SALESMAN_CODE = 84;
                public static final int PART_JOB_NUMBER = 85;

                public static class ActualsPartPricing {
                    public static final int QUANTITY = 86;
                    public static final int COST = 87;
                    public static final int PRICE = 88;
                    public static final int EXTENSION = 89;
                }

                public static class RequiredPartPricing {
                    public static final int QUANTITY = 90;
                    public static final int COST = 91;
                    public static final int PRICE = 92;
                    public static final int EXTENSION = 93;
                }
            }

            public static class JobEstimateCommentIndexes {
                public static final int CONTENT = 94;
                public static final int ESTIMATE = 95;
                public static final int CREATED_DATE = 96;
                public static final int CREATED_TIME = 97;
                public static final int AUTHOR = 98;
            }

        }

        public static final int SPECIAL_ORDER_NUMBER = 99;

        public static class PurchaseOrderIndexes {
            public static final int PARTS_PURCHASE_ORDER = 100;
            public static final int SUBLET_PURCHASE_ORDER = 101;
            public static final int ALL_SUBLET_PURCHASE_ORDER = 102;

            public static final int SUBLET_JOB_NUMBER = 103;
            public static final int SUBLET_DESCRIPTION = 104;
        }

        public static class BillingInfoSummaryIndexes {
            public static final int BILL_CODE = 105;
            public static final int BILL_TYPE = 106;
            public static final int BILL_ID = 107;
            public static final int INVOICE_NO = 108;
            public static final int PARTS_TOTAL = 109;
            public static final int LABOR_TOTAL = 110;
            public static final int SUBLET_TOTAL = 111;
            public static final int EXTRA_TOTAL = 112;
            public static final int TAX_TOTAL = 113;
            public static final int TOTAL = 114;
            public static final int PAID_AMOUNT = 115;
        }

        public static final int CUSTOMER_NUMBER = 116;
    }
    
}
