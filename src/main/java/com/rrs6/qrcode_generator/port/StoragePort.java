package com.rrs6.qrcode_generator.port;

public interface StoragePort {
    public String uploadFiles(byte[] fileData, String filename, String contentType);
}
