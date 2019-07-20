package com.neo.reponsitory;

import com.neo.model.CofferOrder;
import org.springframework.data.repository.CrudRepository;

public interface CofferOrderResp extends CrudRepository<CofferOrder,Long> {
}
