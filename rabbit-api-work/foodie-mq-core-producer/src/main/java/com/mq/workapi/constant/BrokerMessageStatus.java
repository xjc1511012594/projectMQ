package com.mq.workapi.constant;

/**
 * @author xulei
 * @date 2020-3-13 9:38
 */
public enum BrokerMessageStatus {
    SENDING("0"),
    SEND_OK("1"),
    SEND_FAIL("2"),
    SEND_FAIL_A_MOMENT("3");

    private String code;

    BrokerMessageStatus(String code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
