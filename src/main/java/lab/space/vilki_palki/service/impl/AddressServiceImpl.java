package lab.space.vilki_palki.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.mapper.AddressMapper;
import lab.space.vilki_palki.model.address.AddressRequest;
import lab.space.vilki_palki.model.address.AddressResponse;
import lab.space.vilki_palki.model.address.AddressResponseByPage;
import lab.space.vilki_palki.repository.AddressRepository;
import lab.space.vilki_palki.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressSpecification addressSpecification;

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found by id " + id));
    }

    @Override
    public List<AddressResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id) {
        log.info("---------------Get All Addresses By UserId Order by create at---------------");
        return AddressMapper
                .toSimplifiedListDto(addressRepository.findAllByUserIdOrderByCreateAt(id));
    }

    @Override
    public AddressResponseByPage getAddressesByPageByUserId(AddressRequest addressRequest) {
        final int DEFAULT_PAGE_SIZE = 5;
        return AddressMapper.toAddressResponseByPage(
                addressRepository.findAll(addressSpecification.getAddressesByRequest(addressRequest),
                        PageRequest.of(addressRequest.getPageIndex(), DEFAULT_PAGE_SIZE)));
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = getAddressById(id);
        addressRepository.delete(address);
    }

}
