package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.model.address.AddressResponse;
import lab.space.vilki_palki.model.address.AddressResponseByPage;
import org.springframework.data.domain.Page;
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

    public AddressResponseByPage toAddressResponseByPage(Page<Address> addresses) {
        return AddressResponseByPage.builder()
                .data(addresses.stream().map(this::toSimplifiedDto).toList())
                .itemsCount(addresses.getTotalElements())
                .pagesCount(addresses.getTotalPages())
                .build();
    }
}
