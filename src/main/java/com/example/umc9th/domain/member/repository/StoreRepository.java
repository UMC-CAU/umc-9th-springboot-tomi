package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Food;
import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository  extends JpaRepository<Store, Long> {
}
