package com.mq.workapi.enums;

/**
 * @author xulei
 * @date 2020-3-12 11:22
 */
public enum ElasticJobTypeEnum {

    SIMPLE("SimpleJob","简单类型job"),
    DATAFLOW("DataflowJob","流失类型job"),
    SCRIPT("ScriptJpb","脚本类型job");

    private String type;

    private String desc;

    ElasticJobTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
