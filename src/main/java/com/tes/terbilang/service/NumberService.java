package com.tes.terbilang.service;

import com.tes.terbilang.model.Angka;
import com.tes.terbilang.repository.NumberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NumberService {

    private final NumberRepository numberRepository;

    public NumberService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public String convertNumberToWords(Integer value) {
        if (value == null) return "";
        long n = value.longValue();
        if (n == 0) return "nol";
        if (n < 0) return "minus " + toWords(-n);
        return toWords(n).trim().replaceAll("\\s+", " ");
    }

    public void saveNumber(Integer value, String terbilang) {
        if (value == null || terbilang == null || terbilang.isBlank()) {
            throw new IllegalArgumentException("Belum ada data yang diinput");
        }
        Angka angka = new Angka();
    
        angka.setValue(value);
        angka.setTerbilang(terbilang);

        numberRepository.save(angka);
    }

    public List<Angka> getAllNumbers() {
        return numberRepository.findAll();
    }

    private static final String[] DASAR = {
            "", "satu", "dua", "tiga", "empat", "lima",
            "enam", "tujuh", "delapan", "sembilan", "sepuluh", "sebelas"
    };

    private String toWords(long n) {
        if (n < 12) {
            return DASAR[(int) n];
        } else if (n < 20) {
            return toWords(n - 10) + " belas";
        } else if (n < 100) {
            long puluh = n / 10;
            long sisa = n % 10;
            return toWords(puluh) + " puluh" + (sisa > 0 ? " " + toWords(sisa) : "");
        } else if (n < 200) {
            return "seratus" + (n % 100 > 0 ? " " + toWords(n - 100) : "");
        } else if (n < 1000) {
            long ratus = n / 100;
            long sisa = n % 100;
            return toWords(ratus) + " ratus" + (sisa > 0 ? " " + toWords(sisa) : "");
        } else if (n < 2000) {
            return "seribu" + (n % 1000 > 0 ? " " + toWords(n - 1000) : "");
        } else if (n < 1_000_000) {
            long ribu = n / 1000;
            long sisa = n % 1000;
            return toWords(ribu) + " ribu" + (sisa > 0 ? " " + toWords(sisa) : "");
        } else {
            return String.valueOf(n);
        }
    }
}
