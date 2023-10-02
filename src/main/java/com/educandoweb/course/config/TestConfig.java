/* 
 * CLASSE DE CONFIGURAÇÃO.
 * Iremos usar essa classe para poder realizar o Seeding do banco de dados.
*/

package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Tudo que implementar nesse método será executado quando a aplicação for iniciada
        User u1 = new User(null, "Maria Brow", "maria@gmail.com", "98888888888", "123456");
        User u2 = new User(null, "Jon Snow", "jon@gmail.com", "989999999999", "123456");

        // Instanciar 2 objetos by default
        userRepository.saveAll(Arrays.asList(u1, u2));
    }


}
