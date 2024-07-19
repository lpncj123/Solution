package cn.lp.modal;
import java.time.LocalDateTime;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.modal
 * @Author: lp
 * @CreateTime: 2024-07-11  14:07
 * @Description: TODO
 * @Version: 1.0
 */

public class Order {
    private Integer OrderId;
    private Integer UserId;
    private String Product;
    private String Amount;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

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

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }
}

