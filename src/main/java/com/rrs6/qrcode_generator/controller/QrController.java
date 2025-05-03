package com.rrs6.qrcode_generator.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.rrs6.qrcode_generator.dto.QrCodeGenerateResponse;
import com.rrs6.qrcode_generator.dto.QrCodeRequestFormat;
import com.rrs6.qrcode_generator.service.QrCodeConverter;

@RestController
@RequestMapping("/qr-code")
public class QrController {

    private final QrCodeConverter qrCodeConverter;

    public QrController(QrCodeConverter qrCodeConverter) {
        this.qrCodeConverter = qrCodeConverter;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeRequestFormat request) {
        try {
            QrCodeGenerateResponse response = this.qrCodeConverter.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch(WriterException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
