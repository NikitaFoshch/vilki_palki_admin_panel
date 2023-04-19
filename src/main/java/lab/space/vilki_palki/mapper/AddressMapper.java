package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.model.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper {
    public List<AddressResponse> toSimplifiedListDto(List<Address> addresses) {
        return addresses.stream().map(this::toSimplifiedDto).toList();
    }

    public AddressResponse toSimplifiedDto(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .numberHouse(address.getNumberHouse())
                .apartment(address.getApartment())
                .frontDoor(address.getFrontDoor())
                .doorCode(address.getDoorCode())
                .floor(address.getFloor())
                .notes(address.getNotes())
                .build();
    }
}
