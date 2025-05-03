package com.rrs6.qrcode_generator.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rrs6.qrcode_generator.port.StoragePort;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3StorageAdapter implements StoragePort {
    private final S3Client s3Client;
    private final String bucketName;
    private final String region;
    

    public S3StorageAdapter(@Value("${aws.s3.region}") String region, @Value("${aws.s3.bucket-name}") String bucketName, S3Client s3Client) {
        this.bucketName = bucketName;
        this.region = region;
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFiles(byte[] fileData, String filename, String contentType) {
        PutObjectRequest request = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(filename)
        .contentType(contentType)
        .build();

        s3Client.putObject(request, RequestBody.fromBytes(fileData));
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, filename);
    }
}
