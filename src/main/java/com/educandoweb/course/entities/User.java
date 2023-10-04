package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID autoincrementável
    private Long id;
    private String name;
    @Email(message = "Campo de email inválido")
    private String email;
    private String phone;

    @NotNull(message = "Campo não pode ser vazio")
    @NotEmpty(message = "Campo é obrigatório")
    @Length(min = 6, max = 30, message = "O campo de senha deve ter ao menos 6 caracteres e no máximo 30")
    // OBS.: @Pattern abaixo tá bugado, deve tar dando algum erro no incript do password
    // @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,128}$", message="PW_MIN_6_MAX_128_1_UPPER_1_LOWER_1_NUMERIC")
    private String password;

    // Mapeamento JPA -> Definir o 1 pra Muitos, adicionando o atributo da
    // outra classe entre os parenteses.
    // O JsonIgnore irá serializar os objetos do outro lado, por exemplo, se 
    // adicionar o JsonIgnore em Orders, ele irá listar as ordens no endpoint /users
    // Como foi adicionado no users, ele vai serializar o user no endpoint /orders
    @JsonIgnore // Evitar o loop infinito
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
