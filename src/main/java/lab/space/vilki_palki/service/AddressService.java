package lab.space.vilki_palki.service;

import lab.space.vilki_palki.model.AddressResponse;

import java.util.List;

public interface AddressService {
    public List<AddressResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);
}
