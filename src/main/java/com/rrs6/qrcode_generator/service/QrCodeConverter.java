package com.rrs6.qrcode_generator.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rrs6.qrcode_generator.adapter.S3StorageAdapter;
import com.rrs6.qrcode_generator.dto.QrCodeGenerateResponse;
import com.rrs6.qrcode_generator.port.StoragePort;

@Service
public class QrCodeConverter {
    
    private final StoragePort storage;

    public QrCodeConverter(S3StorageAdapter storage) {
        this.storage = storage;
    }

    public QrCodeGenerateResponse generateAndUploadQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngQrCodeData = pngOutputStream.toByteArray();

        String url = storage.uploadFiles(pngQrCodeData, UUID.randomUUID().toString(), "image/png");
        return new QrCodeGenerateResponse(url);
    }
}
