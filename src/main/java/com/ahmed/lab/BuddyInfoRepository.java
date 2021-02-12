package com.ahmed.lab;

import org.springframework.data.repository.CrudRepository;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    void deleteById(long id);
}
