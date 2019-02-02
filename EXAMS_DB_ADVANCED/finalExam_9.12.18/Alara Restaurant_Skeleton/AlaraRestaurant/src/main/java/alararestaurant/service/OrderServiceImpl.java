package alararestaurant.service;

import alararestaurant.domain.dtos.importDtos.importOrders.AllItemsDto;
import alararestaurant.domain.dtos.importDtos.importOrders.ItemOrderImportDto;
import alararestaurant.domain.dtos.importDtos.importOrders.OrderImportDto;
import alararestaurant.domain.dtos.importDtos.importOrders.OrdersRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.Constants;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final static  String ORDERS_FILE_PATH="src\\main\\resources\\files\\orders.xml";

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository,
                            ItemRepository itemRepository, ValidationUtil validationUtil,
                            ModelMapper mapper, FileUtil fileUtil, XmlParser xmlParser) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean ordersAreImported() {
        // TODO : Implement me
        return false;//this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        // TODO : Implement me
        String content = this.fileUtil.readFile(ORDERS_FILE_PATH);
        return content;
    }

    @Override
    public String importOrders() throws JAXBException {
        OrdersRootDto ordersRootDto = this.xmlParser.parseXml(OrdersRootDto.class, ORDERS_FILE_PATH);

        // TODO : Implement me

        StringBuilder res=new StringBuilder();

        for (OrderImportDto orderImportDto : ordersRootDto.getOrderImportDtos()) {
            if(!this.validationUtil.isValid(orderImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.employeeRepository.existsByName(orderImportDto.getEmployee())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.checkIfAllItemsExist(orderImportDto.getAllItemsDto())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Order order = this.mapper.map(orderImportDto, Order.class);
            order.setOrderItems(new ArrayList<>());
            Employee employee = this.employeeRepository.findByName(orderImportDto.getEmployee()).orElse(null);
            order.setEmployee(employee);
           List<OrderItem> orderItems=new ArrayList<>();
            for (ItemOrderImportDto itemOrderImportDto : orderImportDto.getAllItemsDto().getItemOrderImportDtos()) {
                Item item = this.itemRepository.findByName(itemOrderImportDto.getName()).orElse(null);
                OrderItem current=new OrderItem();
                current.setQuantity(itemOrderImportDto.getQuantity());
                current.setItem(item);
                current.setOrder(order);
                orderItems.add(current);
            }
            order.setOrderItems(orderItems);

            SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateString = format.format(order.getDateTime());

            this.orderRepository.saveAndFlush(order);

            res.append(String.format("Order for %s on %s added",
                    order.getCustomer(),dateString))
                    .append(System.lineSeparator());
        }

        return res.toString().trim();
    }

    private boolean checkIfAllItemsExist(AllItemsDto allItemsDto) {
        boolean res=true;
        for (ItemOrderImportDto itemOrderImportDto : allItemsDto.getItemOrderImportDtos()) {
            if(!this.itemRepository.existsByName(itemOrderImportDto.getName())||itemOrderImportDto.getQuantity()<1){
                return false;
            }
        }
        return res;
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        // TODO : Implement me
        List<Employee> employees = this.employeeRepository.query2("Burger Flipper");

        StringBuilder res=new StringBuilder();

        for (Employee employee : employees) {
            res.append(String.format("Name: %s",employee.getName()))
                    .append(System.lineSeparator());

            res.append("Orders:")
                    .append(System.lineSeparator());

            for (Order order : employee.getOrders()) {
                res.append(String.format("\tCustomer: %s",order.getCustomer()))
                        .append(System.lineSeparator());

                res.append("\tItems:")
                        .append(System.lineSeparator());

                for (OrderItem orderItem : order.getOrderItems()) {
                    res.append(String.format("\t\tName: %s",orderItem.getItem().getName()))
                            .append(System.lineSeparator());

                    res.append(String.format("\t\tPrice: %s",
                            String.format("%.2f",orderItem.getItem().getPrice())))
                            .append(System.lineSeparator());

                    Long quantityOfItemPerOrder = this.orderRepository
                            .getItemsQuantityByOrder(order.getId(), orderItem.getItem().getId());
                    int quantity=orderItem.getQuantity();

                    res.append(String.format("\t\tQuantity: %d",
                            quantity))
                            .append(System.lineSeparator());
                    res.append(System.lineSeparator());
                }

            }

   //         res.append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
