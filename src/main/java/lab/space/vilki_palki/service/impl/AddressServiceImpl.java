package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.mapper.AddressMapper;
import lab.space.vilki_palki.model.AddressResponse;
import lab.space.vilki_palki.repository.AddressRepository;
import lab.space.vilki_palki.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<AddressResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id) {
        log.info("---------------Get All Addresses By UserId Order by create at---------------");
        return addressMapper
                .toSimplifiedListDto(addressRepository.findAllByUserIdOrderByCreateAt(id));
    }

}
