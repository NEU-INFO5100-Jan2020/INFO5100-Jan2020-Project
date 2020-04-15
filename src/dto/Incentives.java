package dto;


import service.BigDataType;

import java.util.Date;

public class Incentives extends BigDataType {
    private int incentiveId;
    private String title;
    private String description;
    private String disclaimer;
    private Date startDate;
    private Date endDate;
    private int discountValue;
    private String discountType;
    private int dealerId;
    private boolean isDeleted;
    private String filterList;
    private String vehicleIdList;

    public int getIncentiveId() {
        return incentiveId;
    }

    public void setIncentiveId(int incentiveId) {
        this.incentiveId = incentiveId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getFilterList() {
        return filterList;
    }

    public void setFilterList(String filterList) {
        this.filterList = filterList;
    }

    public String getVehicleIdList() {
        return vehicleIdList;
    }

    public void setVehicleIdList(String vehicleIdList) {
        this.vehicleIdList = vehicleIdList;
    }
}
