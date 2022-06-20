//package com.kololantoo.esdemo.model;
//
//import io.swagger.annotations.ApiModelProperty;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.List;
//
///**
// * @author zhengyang
// * @date 2022/3/1
// * @description 产品基础信息ES model，部分衍生计算字段如下
// */
//@Document(indexName = "security_basic_info_20220614")
////@Setting(replicas = 2)
//public class EsSecurityBasicInfo extends EsProductBase {
//    //————————————————————————————————————————产品权重与boolean信息————————————————————————————————————
//
//    @ApiModelProperty(value = "产品排序权重分")
//    @Field(type = FieldType.Double)
//    private Double sortScore;
//
//    @ApiModelProperty(value = "是否可见（0：否，1：是）")
//    @Field(type = FieldType.Keyword)
//    private String visible;
//
//    @ApiModelProperty("是否是活钱（0：否，1：是）")
//    @Field(type = FieldType.Keyword)
//    private String isFlexible;
//
//    @ApiModelProperty("是否当日起息（0：否，1：是）")
//    @Field(type = FieldType.Keyword)
//    private String isDayInterest;
//
//    @ApiModelProperty("是否当日到账（（0：否，1：是）")
//    @Field(type = FieldType.Keyword)
//    private String isDayArrival;
//
//    //————————————————————————————————————————关联其他表获取的数据————————————————————————————————————
//
//    @ApiModelProperty(value = "产品首字母拼音")
//    @Field(type = FieldType.Keyword)
//    private String securityPinyin;
//
//    /**
//     * 2022.05.25 jira: EMBF-263
//     */
//    @ApiModelProperty(value = "销售机构代码")
//    @Field(type = FieldType.Text)
//    private List<String> partyCodeList;
//
//    @ApiModelProperty(value = "产品管理人简称")
//    @Field(type = FieldType.Text)
//    private String managerNameAbbr;
//
//    //————————————————————————————————————————产品衍生计算信息————————————————————————————————————
//
//    @ApiModelProperty(value = "由 产品登记编号+产品简称+产品首字母拼音+产品名称拼音 拼接而成，字母统一转小写，搜索用")
//    @Field(type = FieldType.Keyword)
//    private String nameKey;
//
//    @ApiModelProperty(value = "收益率口径类型：取所有收益率口径中收益率最大的口径")
//    @Field(type = FieldType.Keyword)
//    private String yieldTypeEnum;
//
//    @ApiModelProperty(value = "收益率：取所有收益率口径中最大的，若无收益率，则取业绩比较基准下限")
//    @Field(type = FieldType.Double)
//    private Double maxYield;
//
//    @ApiModelProperty(value = "万份收益")
//    @Field(type = FieldType.Double)
//    private Double yieldWan;
//
//    @ApiModelProperty("收益率是否来自业绩比较基准: 0否 1是")
//    @Field(type = FieldType.Keyword)
//    private String isBenchMarkYield;
//
//    @ApiModelProperty(value = "业绩比较基准")
//    @Field(type = FieldType.Text)
//    private String benchMark;
//
//    @ApiModelProperty(value = "业绩比较描述")
//    @Field(type = FieldType.Text)
//    private String benchmarkExplain;
//
//    @ApiModelProperty(value = "存储当前产品含异常数据的字段")
//    @Field(type = FieldType.Text)
//    private List<String> errorReasonList;
//
//    @ApiModelProperty(value = "开售时间，可能为空")
//    @Field(type = FieldType.Text)
//    private String startDate;
//
//    @ApiModelProperty(value = "截止时间，可能为空")
//    @Field(type = FieldType.Text)
//    private String endDate;
//
//    //————————————————————————————————————————筛选标签属性————————————————————————————————————
//
//    @ApiModelProperty(name = "投资期限")
//    @Field(type = FieldType.Keyword)
//    private String investExpireEnum;
//
//    @ApiModelProperty(value = "投资期限换算后天数")
//    @Field(type = FieldType.Double)
//    private Double invetExpireCount;
//
//    @ApiModelProperty("转换后的投资期限")
//    @Field(type = FieldType.Text)
//    private String investExpire;
//    @ApiModelProperty("转换后的投资期限单位")
//    @Field(type = FieldType.Text)
//    private String investExpireUnit;
//
//    @ApiModelProperty(value = "投资期限详情")
//    @Field(type = FieldType.Text)
//    private String investExpireDetail;
//
//    @ApiModelProperty(name = "起购金额")
//    @Field(type = FieldType.Keyword)
//    private String purchaseAmountEnum;
//
//    @ApiModelProperty("起购金额换算后值：元")
//    @Field(type = FieldType.Double)
//    private Double purchaseAmountCount;
//
//    @ApiModelProperty("转换后的起购金额")
//    @Field(type = FieldType.Text)
//    private String purchaseAmount;
//    @ApiModelProperty("转换后的起购金额单位")
//    @Field(type = FieldType.Text)
//    private String purchaseAmountUnit;
//
//    @ApiModelProperty("起购金额详情")
//    @Field(type = FieldType.Text)
//    private String purchaseAmountDetail;
//
//    @ApiModelProperty(name = "购买状态")
//    @Field(type = FieldType.Keyword)
//    private String purchaseStatusEnum;
//
//    @ApiModelProperty(name = "产品状态")
//    @Field(type = FieldType.Keyword)
//    private String productStatusEnum;
//
//    @ApiModelProperty(value = "更新时间")
//    @Field(type = FieldType.Text)
//    private String updateTime;
//
//    public Double getSortScore() {
//        return sortScore;
//    }
//
//    public void setSortScore(Double sortScore) {
//        this.sortScore = sortScore;
//    }
//
//    public String getVisible() {
//        return visible;
//    }
//
//    public void setVisible(String visible) {
//        this.visible = visible;
//    }
//
//    public String getIsFlexible() {
//        return isFlexible;
//    }
//
//    public void setIsFlexible(String isFlexible) {
//        this.isFlexible = isFlexible;
//    }
//
//    public String getIsDayInterest() {
//        return isDayInterest;
//    }
//
//    public void setIsDayInterest(String isDayInterest) {
//        this.isDayInterest = isDayInterest;
//    }
//
//    public String getIsDayArrival() {
//        return isDayArrival;
//    }
//
//    public void setIsDayArrival(String isDayArrival) {
//        this.isDayArrival = isDayArrival;
//    }
//
//    public String getSecurityPinyin() {
//        return securityPinyin;
//    }
//
//    public void setSecurityPinyin(String securityPinyin) {
//        this.securityPinyin = securityPinyin;
//    }
//
//    public List<String> getPartyCodeList() {
//        return partyCodeList;
//    }
//
//    public void setPartyCodeList(List<String> partyCodeList) {
//        this.partyCodeList = partyCodeList;
//    }
//
//    public String getManagerNameAbbr() {
//        return managerNameAbbr;
//    }
//
//    public void setManagerNameAbbr(String managerNameAbbr) {
//        this.managerNameAbbr = managerNameAbbr;
//    }
//
//    public String getNameKey() {
//        return nameKey;
//    }
//
//    public void setNameKey(String nameKey) {
//        this.nameKey = nameKey;
//    }
//
//    public String getYieldTypeEnum() {
//        return yieldTypeEnum;
//    }
//
//    public void setYieldTypeEnum(String yieldTypeEnum) {
//        this.yieldTypeEnum = yieldTypeEnum;
//    }
//
//    public Double getMaxYield() {
//        return maxYield;
//    }
//
//    public void setMaxYield(Double maxYield) {
//        this.maxYield = maxYield;
//    }
//
//    public Double getYieldWan() {
//        return yieldWan;
//    }
//
//    public void setYieldWan(Double yieldWan) {
//        this.yieldWan = yieldWan;
//    }
//
//    public String getIsBenchMarkYield() {
//        return isBenchMarkYield;
//    }
//
//    public void setIsBenchMarkYield(String isBenchMarkYield) {
//        this.isBenchMarkYield = isBenchMarkYield;
//    }
//
//    public String getBenchMark() {
//        return benchMark;
//    }
//
//    public void setBenchMark(String benchMark) {
//        this.benchMark = benchMark;
//    }
//
//    public String getBenchmarkExplain() {
//        return benchmarkExplain;
//    }
//
//    public void setBenchmarkExplain(String benchmarkExplain) {
//        this.benchmarkExplain = benchmarkExplain;
//    }
//
//    public List<String> getErrorReasonList() {
//        return errorReasonList;
//    }
//
//    public void setErrorReasonList(List<String> errorReasonList) {
//        this.errorReasonList = errorReasonList;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }
//
//    public String getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(String endDate) {
//        this.endDate = endDate;
//    }
//
//    public String getInvestExpireEnum() {
//        return investExpireEnum;
//    }
//
//    public void setInvestExpireEnum(String investExpireEnum) {
//        this.investExpireEnum = investExpireEnum;
//    }
//
//    public Double getInvetExpireCount() {
//        return invetExpireCount;
//    }
//
//    public void setInvetExpireCount(Double invetExpireCount) {
//        this.invetExpireCount = invetExpireCount;
//    }
//
//    public String getInvestExpire() {
//        return investExpire;
//    }
//
//    public void setInvestExpire(String investExpire) {
//        this.investExpire = investExpire;
//    }
//
//    public String getInvestExpireUnit() {
//        return investExpireUnit;
//    }
//
//    public void setInvestExpireUnit(String investExpireUnit) {
//        this.investExpireUnit = investExpireUnit;
//    }
//
//    public String getInvestExpireDetail() {
//        return investExpireDetail;
//    }
//
//    public void setInvestExpireDetail(String investExpireDetail) {
//        this.investExpireDetail = investExpireDetail;
//    }
//
//    public String getPurchaseAmountEnum() {
//        return purchaseAmountEnum;
//    }
//
//    public void setPurchaseAmountEnum(String purchaseAmountEnum) {
//        this.purchaseAmountEnum = purchaseAmountEnum;
//    }
//
//    public Double getPurchaseAmountCount() {
//        return purchaseAmountCount;
//    }
//
//    public void setPurchaseAmountCount(Double purchaseAmountCount) {
//        this.purchaseAmountCount = purchaseAmountCount;
//    }
//
//    public String getPurchaseAmount() {
//        return purchaseAmount;
//    }
//
//    public void setPurchaseAmount(String purchaseAmount) {
//        this.purchaseAmount = purchaseAmount;
//    }
//
//    public String getPurchaseAmountUnit() {
//        return purchaseAmountUnit;
//    }
//
//    public void setPurchaseAmountUnit(String purchaseAmountUnit) {
//        this.purchaseAmountUnit = purchaseAmountUnit;
//    }
//
//    public String getPurchaseAmountDetail() {
//        return purchaseAmountDetail;
//    }
//
//    public void setPurchaseAmountDetail(String purchaseAmountDetail) {
//        this.purchaseAmountDetail = purchaseAmountDetail;
//    }
//
//    public String getPurchaseStatusEnum() {
//        return purchaseStatusEnum;
//    }
//
//    public void setPurchaseStatusEnum(String purchaseStatusEnum) {
//        this.purchaseStatusEnum = purchaseStatusEnum;
//    }
//
//    public String getProductStatusEnum() {
//        return productStatusEnum;
//    }
//
//    public void setProductStatusEnum(String productStatusEnum) {
//        this.productStatusEnum = productStatusEnum;
//    }
//
//    public String getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(String updateTime) {
//        this.updateTime = updateTime;
//    }
//}
