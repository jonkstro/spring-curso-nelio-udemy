/* 
 * CLASSE DE CONFIGURAÇÃO.
 * Iremos usar essa classe para poder realizar o Seeding do banco de dados.
*/

package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        // Tudo que implementar nesse método será executado quando a aplicação for
        // iniciada by default

        User u1 = new User(null, "Maria Brow", "maria@gmail.com", "98888888888", "123456");
        User u2 = new User(null, "Jon Snow", "jon@gmail.com", "989999999999", "123456");

        Order o1 = new Order(null, Instant.parse("2023-10-02T21:14:10Z"), u1, OrderStatus.WAITING_PAYMENT);
        Order o2 = new Order(null, Instant.parse("2023-11-06T22:14:10Z"), u2, OrderStatus.WAITING_PAYMENT);
        Order o3 = new Order(null, Instant.parse("2023-12-22T23:14:10Z"), u1, OrderStatus.WAITING_PAYMENT);

        // Instanciar os objetos by default
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }

}
