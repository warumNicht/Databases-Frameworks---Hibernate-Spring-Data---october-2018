package user.exam.data_import.orders;

import app.exam.ExamApplication;
import app.exam.config.Config;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.FileIO;
import app.exam.repository.OrderRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExamApplication.class)
public class AllOrdersImportedCount {
    @Autowired
    private OrderRepository orderRepository;

    private static final int EXPECTED_ORDERS_COUNT = 20;

    @Before
    @Transactional
    public void insertEmployeesAndItems(){

    }
    @Test
    public void testItemsImportedCount() throws Exception {
        long count = this.orderRepository
                .findAll()
                .spliterator()
                .getExactSizeIfKnown();
        this.orderRepository.deleteAll();
        this.orderRepository.flush();
        Assert.assertEquals(EXPECTED_ORDERS_COUNT, count);
    }
}
