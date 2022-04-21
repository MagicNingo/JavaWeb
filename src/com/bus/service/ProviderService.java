package com.bus.service;

import com.bus.entity.Provider;
import com.bus.util.Page;

import java.util.List;

public interface ProviderService {
    int addProvider(Provider pro);

    int removeProviderByID(Integer id);

    int updateProviderById(Provider pro);

    Page<Provider> findProviderByPage(Page<Provider> page);

    Page<Provider> findCategoryByCondition(String name, Page<Provider> page);

    List<Provider> findAllProvider();

    Provider findProviderById(Integer id);

    int selectProviderID(Integer providerID);

    int findAllProviderName(String provider_name);
}
