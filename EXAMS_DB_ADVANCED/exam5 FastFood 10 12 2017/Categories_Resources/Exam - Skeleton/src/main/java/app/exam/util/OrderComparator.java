package app.exam.util;

import app.exam.domain.entities.Order;

import java.math.BigDecimal;
import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        int first = o2.getTotalPrice().compareTo(o1.getTotalPrice());
        if(first==0){
            return Integer.compare(o2.getOrderItems().size(),o1.getOrderItems().size());
        }
        return first;
    }
}
