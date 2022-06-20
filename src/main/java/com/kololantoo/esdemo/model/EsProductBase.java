//package com.kololantoo.esdemo.model;
//
//import io.swagger.annotations.ApiModelProperty;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
///**
// * @author zhengyang
// * @date 2022/4/2
// * @description 产品基础信息,来自BFNP_BS_BASICINFO
// */
//public class EsProductBase {
//
//    @Id
//    @ApiModelProperty(value = "证券代码")
//    @Field(type = FieldType.Keyword)
//    private String securityCode;
//
//    @ApiModelProperty(value = "EID")
//    @Field(type = FieldType.Keyword)
//    private Long eid;
//
//    @ApiModelProperty(value = "公告日期")
//    @Field(type = FieldType.Date)
//    private Date noticeDate;
//
//    @ApiModelProperty(value = "证券内码")
//    @Field(type = FieldType.Keyword)
//    private String securityInnerCode;
//
//    @ApiModelProperty(value = "证券名称")
//    @Field(type = FieldType.Keyword)
//    private String securityName;
//
//    @ApiModelProperty(value = "证券简称")
//    @Field(type = FieldType.Keyword)
//    private String securityNameAbbr;
//
//    @ApiModelProperty(value = "母产品证券内码")
//    @Field(type = FieldType.Keyword)
//    private String psecurityInnerCode;
//
//    @ApiModelProperty(value = "产品编码")
//    @Field(type = FieldType.Keyword)
//    private String productCode;
//
//    @ApiModelProperty(value = "理财产品登记编号")
//    @Field(type = FieldType.Keyword)
//    private String wmpRegNum;
//
//    @ApiModelProperty(value = "产品管理人")
//    @Field(type = FieldType.Keyword)
//    private String manager;
//
//    @ApiModelProperty(value = "产品管理人代码")
//    @Field(type = FieldType.Keyword)
//    private String managerCode;
//
//    @ApiModelProperty(value = "产品托管人")
//    @Field(type = FieldType.Keyword)
//    private String trustee;
//
//    @ApiModelProperty(value = "产品托管人代码")
//    @Field(type = FieldType.Keyword)
//    private String trusteeCode;
//
//    @ApiModelProperty(value = "产品类型：固收类、混合类、权益类")
//    @Field(type = FieldType.Keyword)
//    private String securityType;
//
//    @ApiModelProperty(value = "是否货币型：0 否 1 是")
//    @Field(type = FieldType.Keyword)
//    private String isCurrencyType;
//
//    @ApiModelProperty(value = "运作模式：开放式、封闭式")
//    @Field(type = FieldType.Keyword)
//    private String operateMode;
//
//    @ApiModelProperty(value = "风险等级：")
//    @Field(type = FieldType.Keyword)
//    private String riskLevel;
//
//    @ApiModelProperty(value = "募集方式：公募、私募")
//    @Field(type = FieldType.Keyword)
//    private String raiseWay;
//
//    @ApiModelProperty(value = "投资策略")
//    @Field(type = FieldType.Text)
//    private String investStrategy;
//
//    @ApiModelProperty(value = "投资范围")
//    @Field(type = FieldType.Text)
//    private String investScope;
//
//    @ApiModelProperty(value = "投资比例")
//    @Field(type = FieldType.Text)
//    private String investRatio;
//
//    @ApiModelProperty(value = "产品期限单位：日、月、年、永续")
//    @Field(type = FieldType.Text)
//    private String productExpireUnit;
//
//    /**
//     * productExpire&productExpireUnit
//     * ！！！这两个字段非basicInfo原始数据
//     * 代码逻辑处理后覆盖，并返回供【产品详情页】内容显示
//     */
//    @ApiModelProperty(value = "产品期限")
//    @Field(type = FieldType.Keyword)
//    private String productExpire;
//
//    @ApiModelProperty(value = "投资币种:")
//    @Field(type = FieldType.Keyword)
//    private String investCurrencyEnum;
//
//    @ApiModelProperty(value = "销售区域：全国...")
//    @Field(type = FieldType.Text)
//    private String saleArea;
//
//    @ApiModelProperty(value = "预期收益率")
//    @Field(type = FieldType.Double)
//    private Double expectYield;
//
//    @ApiModelProperty(value = "是否支持质押：是、否")
//    @Field(type = FieldType.Keyword)
//    private String isPledge;
//
//    @ApiModelProperty(value = "净值类型：费前、费后")
//    @Field(type = FieldType.Keyword)
//    private String perNavType;
//
//    @ApiModelProperty(value = "净值计算方式：固定净值、浮动净值")
//    @Field(type = FieldType.Keyword)
//    private String pernavCalculateWay;
//
//    @ApiModelProperty(value = "净值更新频率：每工作日、每周、不定期")
//    @Field(type = FieldType.Keyword)
//    private String pernavUpdateFrequency;
//
//    @ApiModelProperty(value = "专属标签：新客专享、新资金专享、私行专享、代发专享、名单制专属、平台专属")
//    @Field(type = FieldType.Text)
//    private String exclusiveLabel;
//
//    @ApiModelProperty(value = "备注")
//    @Field(type = FieldType.Text)
//    private String remark;
//
//    public String getSecurityCode() {
//        return securityCode;
//    }
//
//    public void setSecurityCode(String securityCode) {
//        this.securityCode = securityCode;
//    }
//
//    public Long getEid() {
//        return eid;
//    }
//
//    public void setEid(Long eid) {
//        this.eid = eid;
//    }
//
//    public Date getNoticeDate() {
//        return noticeDate;
//    }
//
//    public void setNoticeDate(Date noticeDate) {
//        this.noticeDate = noticeDate;
//    }
//
//    public String getSecurityInnerCode() {
//        return securityInnerCode;
//    }
//
//    public void setSecurityInnerCode(String securityInnerCode) {
//        this.securityInnerCode = securityInnerCode;
//    }
//
//    public String getSecurityName() {
//        return securityName;
//    }
//
//    public void setSecurityName(String securityName) {
//        this.securityName = securityName;
//    }
//
//    public String getSecurityNameAbbr() {
//        return securityNameAbbr;
//    }
//
//    public void setSecurityNameAbbr(String securityNameAbbr) {
//        this.securityNameAbbr = securityNameAbbr;
//    }
//
//    public String getPsecurityInnerCode() {
//        return psecurityInnerCode;
//    }
//
//    public void setPsecurityInnerCode(String psecurityInnerCode) {
//        this.psecurityInnerCode = psecurityInnerCode;
//    }
//
//    public String getProductCode() {
//        return productCode;
//    }
//
//    public void setProductCode(String productCode) {
//        this.productCode = productCode;
//    }
//
//    public String getWmpRegNum() {
//        return wmpRegNum;
//    }
//
//    public void setWmpRegNum(String wmpRegNum) {
//        this.wmpRegNum = wmpRegNum;
//    }
//
//    public String getManager() {
//        return manager;
//    }
//
//    public void setManager(String manager) {
//        this.manager = manager;
//    }
//
//    public String getManagerCode() {
//        return managerCode;
//    }
//
//    public void setManagerCode(String managerCode) {
//        this.managerCode = managerCode;
//    }
//
//    public String getTrustee() {
//        return trustee;
//    }
//
//    public void setTrustee(String trustee) {
//        this.trustee = trustee;
//    }
//
//    public String getTrusteeCode() {
//        return trusteeCode;
//    }
//
//    public void setTrusteeCode(String trusteeCode) {
//        this.trusteeCode = trusteeCode;
//    }
//
//    public String getSecurityType() {
//        return securityType;
//    }
//
//    public void setSecurityType(String securityType) {
//        this.securityType = securityType;
//    }
//
//    public String getIsCurrencyType() {
//        return isCurrencyType;
//    }
//
//    public void setIsCurrencyType(String isCurrencyType) {
//        this.isCurrencyType = isCurrencyType;
//    }
//
//    public String getOperateMode() {
//        return operateMode;
//    }
//
//    public void setOperateMode(String operateMode) {
//        this.operateMode = operateMode;
//    }
//
//    public String getRiskLevel() {
//        return riskLevel;
//    }
//
//    public void setRiskLevel(String riskLevel) {
//        this.riskLevel = riskLevel;
//    }
//
//    public String getRaiseWay() {
//        return raiseWay;
//    }
//
//    public void setRaiseWay(String raiseWay) {
//        this.raiseWay = raiseWay;
//    }
//
//    public String getInvestStrategy() {
//        return investStrategy;
//    }
//
//    public void setInvestStrategy(String investStrategy) {
//        this.investStrategy = investStrategy;
//    }
//
//    public String getInvestScope() {
//        return investScope;
//    }
//
//    public void setInvestScope(String investScope) {
//        this.investScope = investScope;
//    }
//
//    public String getInvestRatio() {
//        return investRatio;
//    }
//
//    public void setInvestRatio(String investRatio) {
//        this.investRatio = investRatio;
//    }
//
//    public String getProductExpireUnit() {
//        return productExpireUnit;
//    }
//
//    public void setProductExpireUnit(String productExpireUnit) {
//        this.productExpireUnit = productExpireUnit;
//    }
//
//    public String getProductExpire() {
//        return productExpire;
//    }
//
//    public void setProductExpire(String productExpire) {
//        this.productExpire = productExpire;
//    }
//
//    public String getInvestCurrencyEnum() {
//        return investCurrencyEnum;
//    }
//
//    public void setInvestCurrencyEnum(String investCurrencyEnum) {
//        this.investCurrencyEnum = investCurrencyEnum;
//    }
//
//    public String getSaleArea() {
//        return saleArea;
//    }
//
//    public void setSaleArea(String saleArea) {
//        this.saleArea = saleArea;
//    }
//
//    public Double getExpectYield() {
//        return expectYield;
//    }
//
//    public void setExpectYield(Double expectYield) {
//        this.expectYield = expectYield;
//    }
//
//    public String getIsPledge() {
//        return isPledge;
//    }
//
//    public void setIsPledge(String isPledge) {
//        this.isPledge = isPledge;
//    }
//
//    public String getPerNavType() {
//        return perNavType;
//    }
//
//    public void setPerNavType(String perNavType) {
//        this.perNavType = perNavType;
//    }
//
//    public String getPernavCalculateWay() {
//        return pernavCalculateWay;
//    }
//
//    public void setPernavCalculateWay(String pernavCalculateWay) {
//        this.pernavCalculateWay = pernavCalculateWay;
//    }
//
//    public String getPernavUpdateFrequency() {
//        return pernavUpdateFrequency;
//    }
//
//    public void setPernavUpdateFrequency(String pernavUpdateFrequency) {
//        this.pernavUpdateFrequency = pernavUpdateFrequency;
//    }
//
//    public String getExclusiveLabel() {
//        return exclusiveLabel;
//    }
//
//    public void setExclusiveLabel(String exclusiveLabel) {
//        this.exclusiveLabel = exclusiveLabel;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//}
