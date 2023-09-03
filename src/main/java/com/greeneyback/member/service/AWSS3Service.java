package com.greeneyback.member.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AWSS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // s3에 업로드, s3 url 반환.
    // filePath : S3 버킷에서 만든 폴더 이름
    public List<String> uploadFiletToS3(List<MultipartFile> multipartFiles) {

        List<String> imageUrlList = new ArrayList<>();

        for(MultipartFile multipartFile: multipartFiles) {
            String originalFilename = multipartFile.getOriginalFilename();
            long size = multipartFile.getSize(); // 파일 크기

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(size);

            // S3 업로드 전 이름 변경
            String uploadFilename = createFilName(originalFilename);

            // S3 업로드
            try(InputStream inputStream = multipartFile.getInputStream()) {

                amazonS3Client.putObject(
                        new PutObjectRequest(bucket, uploadFilename, inputStream, objectMetadata)
                                .withCannedAcl(CannedAccessControlList.PublicRead)
                );

                String imageUrl = amazonS3Client.getUrl(bucket, uploadFilename).toString();
                imageUrlList.add(imageUrl);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imageUrlList;
    }

    // 추후 수정 예정
    private void removeNewFile(File targetFile) {
        if(targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        }else {
            log.info("파일이 삭제되지 못했습니다.");
        }
    }


    // s3 저장시 동일 파일명 방지 위해 uuid 생성
    private String createFilName(String fileName) {
        return UUID.randomUUID() + fileName;
    }

}
