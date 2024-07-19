package com.cheeus.firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.messaging.SendResponse;

@Service
public class ImageGetService {
	
	public byte[] getImg(String fileName) throws IOException{
		// JSON KEY 경로
		Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\alstk\\Downloads\\sts-4.19.1.RELEASE\\workspace\\cheeus\\src\\main\\resources\\java-firebase-sdk-firebase-adminsdk.json"));
		// 파이어베이스 스토리지 접근
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        // 스토리지에서 Blob 가져오기
        BlobId blobId = BlobId.of("cheeusfinal.appspot.com", "profile/" + "rlaalstkd177@gmail.com" + "0");
        Blob blob = storage.get(blobId);
        byte[] blobByte = blob.getContent();
        System.out.println(blobByte);
        
        return blobByte;
        //return sendResponse("200", "Successfully Downloaded!");
	}
}
