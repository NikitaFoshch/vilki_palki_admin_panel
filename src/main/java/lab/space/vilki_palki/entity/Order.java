package lab.space.vilki_palki.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public enum DeliveryStatus {
        IN_PROCESS,
        DONE
    }

}

