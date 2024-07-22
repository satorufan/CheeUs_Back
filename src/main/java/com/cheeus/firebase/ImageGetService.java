package com.cheeus.firebase;

<<<<<<< HEAD
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
=======
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
>>>>>>> a50bcc73966243153169d2f6cfb0f4f1893aa249

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
<<<<<<< HEAD
import com.google.firebase.messaging.SendResponse;
=======
>>>>>>> a50bcc73966243153169d2f6cfb0f4f1893aa249

@Service
public class ImageGetService {
	
<<<<<<< HEAD
	public Blob getImg(String fileName) throws IOException{
		// JSON KEY 경로
		Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\alstk\\Downloads\\sts-4.19.1.RELEASE\\workspace\\cheeus\\src\\main\\resources\\java-firebase-sdk-firebase-adminsdk.json"));
		// 파이어베이스 스토리지 접근
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        // 스토리지에 있는 파일 불러오기
        Blob blob = storage.get(BlobId.of("cheeusfinal.appspot.com", "profile/rlaalstkd177@gmail.com0"));
        System.out.println(blob);
        return blob;
=======
	public ArrayList<byte[]> getImg(String category, String email, int cnt) throws IOException{
		
		ArrayList<byte[]> images = new ArrayList<byte[]>();
		
		// JSON KEY 경로
		InputStream inputStream = ImageUploadService.class.getClassLoader().getResourceAsStream("java-firebase-sdk-firebase-adminsdk.json");
	    Credentials credentials = GoogleCredentials.fromStream(inputStream);
	    
		// 파이어베이스 스토리지 접근
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        
        // 스토리지에서 Blob 가져오기
        for (int i=0 ; i<cnt ; i++) {
        	
        	BlobId blobId = BlobId.of("cheeusfinal.appspot.com", category + email + "/" + i);
            Blob blob = storage.get(blobId);
            byte[] blobByte = blob.getContent();
            
            images.add(blobByte);
        	
        }
        
        System.out.println(images);
        
        return images;
        //return sendResponse("200", "Successfully Downloaded!");
	}
	
	// 파일 유형 가져오기
	public ArrayList<String> getType(String category, String email, int cnt) throws IOException{
		
		ArrayList<String> types = new ArrayList<String>();
		
		// JSON KEY 경로
		InputStream inputStream = ImageUploadService.class.getClassLoader().getResourceAsStream("java-firebase-sdk-firebase-adminsdk.json");
	    Credentials credentials = GoogleCredentials.fromStream(inputStream);
	    
		// 파이어베이스 스토리지 접근
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        
        // 스토리지에서 Blob 가져오기
        for (int i=0 ; i<cnt ; i++) {
        	
        	BlobId blobId = BlobId.of("cheeusfinal.appspot.com", category + email + "/" + i);
            Blob blob = storage.get(blobId);
            String blobTyte = blob.getContentType();
            
            types.add(blobTyte);
        	
        }
        
        System.out.println(types);
        
        return types;
>>>>>>> a50bcc73966243153169d2f6cfb0f4f1893aa249
        //return sendResponse("200", "Successfully Downloaded!");
	}
}
