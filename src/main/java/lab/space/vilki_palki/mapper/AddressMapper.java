package lab.space.vilki_palki.mapper;

import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.model.address.AddressResponse;
import lab.space.vilki_palki.model.address.AddressResponseByPage;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


public interface AddressMapper {
    static List<AddressResponse> toSimplifiedListDto(List<Address> addresses) {
        return addresses.stream().map(AddressMapper::toSimplifiedDto).collect(Collectors.toList());
    }

    static AddressResponse toSimplifiedDto(Address address) {
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

    static AddressResponseByPage toAddressResponseByPage(Page<Address> addresses) {
        return AddressResponseByPage.builder()
                .data(addresses.stream().map(AddressMapper::toSimplifiedDto).collect(Collectors.toList()))
                .itemsCount(addresses.getTotalElements())
                .pagesCount(addresses.getTotalPages())
                .build();
    }
}
