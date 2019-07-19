package com.neo.reponsitoory;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
@NoRepositoryBean
public interface BaseResp<T,Long> extends PagingAndSortingRepository<T,Long> {
}
