package com.cheeus.firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
	
	public Blob getImg(String fileName) throws IOException{
		// JSON KEY 경로
		Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\alstk\\Downloads\\sts-4.19.1.RELEASE\\workspace\\cheeus\\src\\main\\resources\\java-firebase-sdk-firebase-adminsdk.json"));
		// 파이어베이스 스토리지 접근
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        // 스토리지에 있는 파일 불러오기
        Blob blob = storage.get(BlobId.of("cheeusfinal.appspot.com", "profile/rlaalstkd177@gmail.com0"));
        System.out.println(blob);
        return blob;
        //return sendResponse("200", "Successfully Downloaded!");
	}
}
