package lab.space.vilki_palki.entity;

import jakarta.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class User extends MappedEntity {

    @Column(length = 100)
    private String name;
    @Column(length = 20)
    private String phone;
    private Instant birthday;
    @Column(length = 20)
    private String facebookId;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 500)
    private String address;
    @Column(length = 20)
    private String cardNumber;
    private int bonusPoints;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Address> addresses = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

}
