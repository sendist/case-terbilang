package com.tes.terbilang.controller;

import com.tes.terbilang.model.Angka;
import com.tes.terbilang.service.NumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NumberController {

    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping("/number")
    public ResponseEntity<String> saveNumber(@RequestParam Integer value) {
        if (value == null) {
            return ResponseEntity.badRequest().body("Input tidak boleh kosong.");
        }
        String terbilang = numberService.convertNumberToWords(value);
        numberService.saveNumber(value, terbilang);
        return ResponseEntity.created(URI.create("/api/number/save"))
                .body("Data berhasil disimpan: " + value + " = " + terbilang);
    }

    @GetMapping("/number")
    public ResponseEntity<List<Angka>> getAllNumbers() {
        List<Angka> data = numberService.getAllNumbers();
        return ResponseEntity.ok(data);
    }
}
