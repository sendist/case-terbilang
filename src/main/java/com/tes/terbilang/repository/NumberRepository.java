package com.tes.terbilang.repository;

import com.tes.terbilang.model.Angka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumberRepository extends JpaRepository <Angka, Integer> {

    List<Angka> findAll();
}