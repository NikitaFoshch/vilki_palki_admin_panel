package lab.space.vilki_palki.entity;

import jakarta.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends MappedEntity {
    @Column(length = 30, nullable = false)
    private String orderCode;
    private Instant birthday;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    
    private DeliveryStatus deliveryStatus;
    @Column(nullable = false)
    private Instant deliveryTime;
    @Column(nullable = false)
    private boolean carStatus;
    @Column(nullable = false)
    private boolean timeStatus;
    @Column(nullable = false)
    private int common_kit;
    @Column(nullable = false)
    private boolean cardPay;
    @Column(nullable = false)
    private boolean cashPay;
    @Column(length = 10000, nullable = false)
    private String products;
    @Column(length = 1000, nullable = false)
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @RequiredArgsConstructor
    public enum DeliveryStatus {
        IN_PROCESS("In process"),
        ON_WAY("On way"),
        ACCEPT("Accept"),
        CANCELED("Canceled"),
        DONE("Completed");
        private final String value;
    }

}

