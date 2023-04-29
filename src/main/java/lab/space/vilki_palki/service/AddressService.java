package lab.space.vilki_palki.service;

import lab.space.vilki_palki.model.address.AddressRequest;
import lab.space.vilki_palki.model.address.AddressResponse;
import lab.space.vilki_palki.model.address.AddressResponseByPage;

import java.util.List;

public interface AddressService {
    public List<AddressResponse> findAllOrdersByUserIdByOrderByCreateAt(Long id);
    AddressResponseByPage getAddressesByPageByUserId(AddressRequest addressRequest);
}
