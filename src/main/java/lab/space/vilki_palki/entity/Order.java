package lab.space.vilki_palki.entity;

import jakarta.persistence.*;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends MappedEntity {
    private String orderCode;
    private Instant birthday;
    private int price;
    private DeliveryStatus deliveryStatus;
    private Instant deliveryTime;
    private boolean carStatus;
    private boolean timeStatus;
    private int common_kit;
    private boolean cardPay;
    private boolean cashPay;
    @Column(length = 10000)
    private String products;
    @Column(length = 10000)
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Getter
    @RequiredArgsConstructor
    public enum DeliveryStatus {
        IN_PROCESS("Готовиться"),
        ON_WAY("В пути"),
        ACCEPT("Принят"),
        CANCELED("Отменен"),
        DONE("Исполнен");
        private final String value;
    }

}

