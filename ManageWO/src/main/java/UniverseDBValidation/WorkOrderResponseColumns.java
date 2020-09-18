package UniverseDBValidation;

public class WorkOrderResponseColumns {

    public static class CustomerColumns {
        public static final int LAST_NAME = 1;
        public static final int FIRST_NAME = 2;
        public static final int ADDRESS = 3;
        public static final int HOME_PHONE = 4;
        public static final int POSTAL_CODE = 5;
        public static final int COUNTRY = 24;
        public static final int EMAIL_ADDRESS = 165;
        public static final int CELL_PHONE = 202;
    }

    public static class SpecialOrderColumns {
        public static class SpecialOrderPartColumns {
            public static final int PART_NO = 3;
            public static final int VENDOR_NO = 8;
            public static final int VENDOR_PART_NO = 10;
            public static final int DESCRIPTION = 11;
            public static final int QUANTITY = 5;
            public static final int PRICE = 12;
            public static final int STATUS = 9;
        }
    }

    public static class WorkOrderColumns {
        public static final int WORK_ORDER_DATE = 5;
        public static final int AUTHOR = 7;
        public static final int STATUS_CODE = 83;
        public static final int WORK_ORDER_LOCATION = 93;
        public static final int SALESMAN_CODE = 110;
        public static final int PROMISE_DATE = 69;
        public static final int PROMISE_TIME = 133;
        public static final int SCHEDULE_PRIORITY_CODE = 349;
        public static final int APPOINTMENT_DATE = 413;
        public static final int APPOINTMENT_TIME = 414;
        public static final int IN_SERVICE_DATE = 371;
        public static final int PARTS_DISCOUNT = 308;
        public static final int CATEGORY_CODE = 346;
        public static final int TAG_NO = 164;
        public static final int MILEAGE_IN = 9;
        public static final int MILEAGE_OUT = 336;
        public static final int MILEAGE_UNIT_CODE = 327;
        public static final int COMPLETE_DATE = 29;
        public static final int CANCEL_DATE = 42;
        public static final int COMMENTS = 58;

        public static final int SPECIAL_ORDER_NUMBER = 75;
        public static final int CUSTOMER_NUMBER = 6;

        public static class PurchaseOrderColumns {
            public static final int PARTS_PURCHASE_ORDER = 77;
            public static final int SUBLET_PURCHASE_ORDER = 271;
            public static final int ALL_SUBLET_PURCHASE_ORDER = 233;

            public static final int SUBLET_JOB_NUMBER = 176;
            public static final int SUBLET_DESCRIPTION = 19;
        }

        public static class InventoryColumns {
            public static final int STOCK_NO = 2;
            public static final int WARRANTY_DATE = 89;
            public static final int CHASSIS_NO = 91;
            public static final int DESCRIPTION = 3;
            public static final int SERIAL_NO = 23;
        }

        public static class JobColumns {
            public static final int JOB_NO = 1;
            public static final int DESCRIPTION = 71;
            public static final int BILL_TYPE_CODE = 186;
            public static final int BILL_TO = 187;
            public static final int SALESMAN_CODE = 188;
            public static final int TAX_CODE = 189;
            public static final int STATUS_CODE = 242;
            public static final int QUOTED_AMOUNT = 281;
            public static final int ESTIMATED_AMOUNT = 211;

            public static class PartsColumns {
                public static final int PART_NO = 31;
                public static final int DESCRIPTION = 34;
                public static final int DISCOUNT_PERCENTAGE = 309;
                public static final int TYPE = 316;
                public static final int TAX_CODE = 60;
                public static final int SALESMAN_CODE = 111;
                public static final int PART_JOB_NUMBER = 174;

                public static class ActualsPartPricing {
                    public static final int QUANTITY = 35;
                    public static final int COST = 32;
                    public static final int PRICE = 33;
                    public static final int EXTENSION = 36;
                }

                public static class RequiredPartPricing {
                    public static final int QUANTITY = 64;
                    public static final int COST = 61;
                    public static final int PRICE = 62;
                    public static final int EXTENSION = 65;
                }
            }

            public static class LaborsColumns {
                public static final int LABOR_CODE = 14;
                public static final int DESCRIPTION = 13;
                public static final int TYPE = 207;
                public static final int MECHANIC_CODE = 17;
                public static final int SKILL_SET_CODE = 353;
                public static final int STATUS_CODE = 314;
                public static final int LABOR_DATE = 208;
                public static final int CHARGE_HOURS = 15;
                public static final int TAX_CODE = 59;
                public static final int FAULT_CODE = 167;
                public static final int SALESMAN_CODE = 112;
                public static final int LABOR_JOB_NUMBER = 175;

                public static class ActualsLaborPricing {
                    public static final int HOURS = 101;
                    public static final int RATE = 12;
                    public static final int EXTENSION = 16;
                }

                public static class RequiredLaborPricing {
                    public static final int HOURS = 100;
                    public static final int RATE = 104;
                    public static final int EXTENSION = 102;
                }
            }

            public static class SubletsColumns {
                public static final int DESCRIPTION = 19;
                public static final int VENDOR_NO = 18;
                public static final int PURCHASE_ORDER_NO = 271;
                public static final int EXPECTED_DATE = 114;
                public static final int COMPLETED_DATE = 115;
                public static final int TAX_CODE = 22;
                public static final int PURCHASE_ORDER_COMMENT = 273;
                public static final int SALESMAN_CODE = 113;
                public static final int INVOICE_NO = 280;
                public static final int SUBLET_JOB_NUMBER = 176;

                public static class RequiredSubletPricingColumns{
                    public static final int COST = 107;
                    public static final int LIST_PRICE = 108;
                }

                public static class ActualsSubletPricingColumns{
                    public static final int COST = 21;
                    public static final int LIST_PRICE = 20;
                }
            }

            public static class ExtrasColumns {
                public static final int EXTRA_CODE = 121;
                public static final int DESCRIPTION = 122;
                public static final int TAX_CODE = 127;
                public static final int SALESMAN_CODE = 130;
                public static final int EXTRA_JOB_NUMBER = 177;

                public static class RequiredExtrasPricingColumns{
                    public static final int QUANTITY = 50;
                    public static final int COST = 51;
                    public static final int LIST_PRICE = 52;
                    public static final int EXTENSION = 53;
                }

                public static class ActualsJExtrasPricingColumns{
                    public static final int QUANTITY = 123;
                    public static final int COST = 124;
                    public static final int LIST_PRICE = 125;
                    public static final int EXTENSION = 126;
                }
            }

            public static class JobEstimateCommentColumns {
                public static final int CONTENT = 338;
                public static final int ESTIMATE = 337;
                public static final int CREATED_DATE = 340;
                public static final int CREATED_TIME = 341;
                public static final int AUTHOR = 339;
            }

        }

        public static class BillingInfoSummaryColumns {
            public static final int BILL_CODE = 135;
            public static final int BILL_TYPE = 136;
            public static final int BILL_ID = 137;
            public static final int INVOICE_NO = 147;
            public static final int PARTS_TOTAL = 139;
            public static final int LABOR_TOTAL = 140;
            public static final int SUBLET_TOTAL = 141;
            public static final int EXTRA_TOTAL = 142;
            public static final int TAX_TOTAL = 144;
            public static final int TOTAL = 145;
            public static final int PAID_AMOUNT = 201;
        }

    }

}
