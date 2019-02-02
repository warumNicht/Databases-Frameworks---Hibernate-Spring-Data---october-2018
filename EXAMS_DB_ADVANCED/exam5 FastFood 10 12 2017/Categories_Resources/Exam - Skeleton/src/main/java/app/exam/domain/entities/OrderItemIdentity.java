package app.exam.domain.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemIdentity implements Serializable {
    private Integer orderId;
    private Integer itemId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId,itemId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItemIdentity that = (OrderItemIdentity) o;

        if (!this.itemId.equals(that.itemId)) return false;
        return this.orderId.equals(that.orderId);
    }
}
