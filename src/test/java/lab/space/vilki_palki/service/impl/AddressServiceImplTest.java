package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.mapper.AddressMapper;
import lab.space.vilki_palki.model.address.AddressRequest;
import lab.space.vilki_palki.model.address.AddressResponse;
import lab.space.vilki_palki.model.address.AddressResponseByPage;
import lab.space.vilki_palki.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {


    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressSpecification addressSpecification;
    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void getAddressById() {
        Long addressId = 1L;
        Address expectedAddress = new Address();
        expectedAddress.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(expectedAddress));

        Address actualAddress = addressService.getAddressById(addressId);

        assertEquals(expectedAddress, actualAddress);
        verify(addressRepository, times(1)).findById(addressId);
    }

    @Test
    void findAllOrdersByUserIdByOrderByCreateAt() {
        Long userId = 1L;
        Address address = new Address();
        address.setId(1L);
        List<Address> expectedAddresses = Collections.singletonList(address);

        when(addressRepository.findAllByUserIdOrderByCreateAt(userId)).thenReturn(expectedAddresses);

        List<AddressResponse> actualResponses = addressService.findAllOrdersByUserIdByOrderByCreateAt(userId);

        assertEquals(expectedAddresses.size(), actualResponses.size());
        assertEquals(address.getId(), actualResponses.get(0).getId());
        verify(addressRepository, times(1)).findAllByUserIdOrderByCreateAt(userId);
    }

    @Test
    void getAddressesByPageByUserId() {

        int pageIndex = 1;
        String query = "";
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setPageIndex(pageIndex);
        addressRequest.setQuery(query);

        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());
        addresses.add(new Address());
        addresses.add(new Address());
        Page<Address> addressPage = new PageImpl<>(addresses);

        when(addressRepository.findAll((Specification<Address>) any(), any(PageRequest.class))).thenReturn(addressPage);

        AddressResponseByPage responseByPage = addressService.getAddressesByPageByUserId(addressRequest);

        assertNotNull(responseByPage);
        assertEquals(addresses.size(), responseByPage.getData().size());
        verify(addressRepository, times(1)).findAll((Specification<Address>) any(), any(PageRequest.class));
    }

    @Test
    void deleteAddress() {
        Long addressId = 1L;
        Address address = new Address();
        address.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));

        addressService.deleteAddress(addressId);

        verify(addressRepository, times(1)).findById(addressId);
        verify(addressRepository, times(1)).delete(address);
    }
}