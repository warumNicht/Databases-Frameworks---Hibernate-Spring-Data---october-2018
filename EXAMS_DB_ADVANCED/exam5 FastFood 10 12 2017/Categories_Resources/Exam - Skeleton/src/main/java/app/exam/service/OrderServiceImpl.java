package app.exam.service;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.export1.ItemExportDto;
import app.exam.domain.dto.xml.export1.OrderExportDto;
import app.exam.domain.dto.xml.export1.OrderItemsWrapperDTO;
import app.exam.domain.dto.xml.import3.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.import3.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.interfaces.ModelParser;
import app.exam.parser.interfaces.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import app.exam.util.OrderComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private ItemsRepository itemsRepository;
    private ValidationUtil validationUtil;
    private ModelParser mapper;
@Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository,
                            ItemsRepository itemsRepository, ValidationUtil validationUtil,
                            ModelParser mapper) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemsRepository = itemsRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        Order order = new Order();
        order.setDate(dto.getDate());
        order.setCustomer(dto.getCustomer());
        Employee employee = this.employeeRepository.getByName(dto.getEmployee()).orElse(null);
        order.setEmployee(employee);
        order.setOrderType(OrderType.valueOf(dto.getType()));
        order.setOrderItems(new LinkedHashSet<>());
        Set<OrderItem> items=new LinkedHashSet<>();
        for (OrderItemXMLImportDTO orderItemXMLImportDTO : dto.getItems()) {
            Item item = this.itemsRepository.getByName(orderItemXMLImportDTO.getName()).orElse(null);
            OrderItem current=new OrderItem();
            current.setItem(item);
            current.setOrder(order);
            current.setQuantity(orderItemXMLImportDTO.getQuantity());

            items.add(current);
        }
        order.setOrderItems(items);

        this.orderRepository.saveAndFlush(order);

    }

    @Override
    public OrderItemsWrapperDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        OrderType orderType1 = OrderType.valueOf(orderType);
        int ordinal = orderType1.ordinal();
        List<Order> allByEmployeeAndOrderType = this.orderRepository.
                query1(employeeName, orderType1);

        for (Order order : allByEmployeeAndOrderType) {
            order.setTotalPrice();
        }

        allByEmployeeAndOrderType.sort(new OrderComparator());

        OrderItemsWrapperDTO res=new OrderItemsWrapperDTO();
        String name = allByEmployeeAndOrderType.get(0).getEmployee().getName();
        res.setEmployeeName(name);

        List<OrderExportDto> orders=new ArrayList<>();

        for (Order order : allByEmployeeAndOrderType) {
            OrderExportDto ord=new OrderExportDto();
            ord.setCustomer(order.getCustomer());
            List<ItemExportDto> items=new ArrayList<>();

            List<OrderItem> collect = order.getOrderItems().stream()
                    .collect(Collectors.toList());

            List<OrderItem> sorted = collect.stream()
                    .sorted((x, y) -> {
                        return Integer.compare(x.getItem().getId(), y.getItem().getId());
                    }).collect(Collectors.toList());


            for (OrderItem orderItem : sorted) {
                ItemExportDto cur=new ItemExportDto();
                cur.setName(orderItem.getItem().getName());
                cur.setPrice(String.format("%.2f", orderItem.getItem().getPrice()));
                cur.setQuantity(orderItem.getQuantity());
                items.add(cur);
            }
            ord.setItems(items);
            orders.add(ord);
        }
        res.setOrders(orders);
        return res;
    }
}
