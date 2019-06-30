package com.example.inspiron.jibei;


public class Extra {
    /** 卡号 */
    public static final String CARD_ID = "CARD_ID";
    /** 记账时间（月份） */
    public static final String ACCOUNT_DATE = "ACCOUNT_DATE";
    /** 支出/收入类型 */
    public static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
    /** 记账列表数据源是否发生改变 */
    public static final String ACCOUNT_HAS_CHANGE = "ACCOUNT_HAS_CHANGE";

    public static final String DETAIL_TYPE_DEFAULT = "DETAIL_TYPE_DEFAULT";

    /** 不区分支出收入（所有） */
    public static int ACCOUNT_TYPE_ALL = 0;
    /** 支出 */
    public static int ACCOUNT_TYPE_EXPEND = 1;
    /** 收入 */
    public static int ACCOUNT_TYPE_INCOME = 2;

    public static final String CARD_REMIND_START_TYPE = "CARD_REMIND_START_TYPE";


    public static class requestCode {
        public static int CARD_BELONG = 0;
        public static int ACCOUNT_CHANGE = 1;
    }

    public static class resultCode {
        public static int CARD_BELONG = 0;
        public static int ACCOUNT_CHANGE = 1;
    }
}
