package cn.lp.modal;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.modal
 * @Author: lp
 * @CreateTime: 2024-07-11  14:07
 * @Description: TODO
 * @Version: 1.0
 */
@ToString
public class Order {
    private Integer OrderId;
    private Integer UserId;
    private String Product;
    private String Amount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Getter and Setter methods
    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}

