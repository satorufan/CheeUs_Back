package com.cheeus.firebase;

import java.io.IOException;
//import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

//import com.google.auth.Credentials;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.BlobId;
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.StorageOptions;

@Service
public class ImageGetService {
	
	public ArrayList<byte[]> getImg(String category, String email, int cnt) throws IOException{
		
		ArrayList<byte[]> images = new ArrayList<byte[]>();
		
        for (int i=0 ; i<cnt ; i++) {
        	
        	images.add(null);
        }
        
        
        return images;
	}
	
	public ArrayList<String> getType(String category, String email, int cnt) throws IOException{
		
		ArrayList<String> images = new ArrayList<String>();
		
        // 스토리지에서 Blob 가져오기
        for (int i=0 ; i<cnt ; i++) {
        	String prefixUrl = "https://firebasestorage.googleapis.com/v0/b/cheeusfinal.appspot.com/o/";
        	String fileLocation = category + "%2F" + email + "%2F" + i + "?alt=media";
        	images.add(prefixUrl + fileLocation);
        }
        
        return images;
	}
	
//	// 파일 유형 가져오기
//	public ArrayList<String> getType(String category, String email, int cnt) throws IOException{
//		
//		ArrayList<String> types = new ArrayList<String>();
//		
//		// JSON KEY 경로
//		InputStream inputStream = ImageUploadService.class.getClassLoader().getResourceAsStream("java-firebase-sdk-firebase-adminsdk.json");
//	    Credentials credentials = GoogleCredentials.fromStream(inputStream);
//	    
//		// 파이어베이스 스토리지 접근
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//        
//        // 스토리지에서 Blob 가져오기
//        for (int i=0 ; i<cnt ; i++) {
//        	
//        	BlobId blobId = BlobId.of("cheeusfinal.appspot.com", category + email + "/" + i);
//            Blob blob = storage.get(blobId);
//            String blobTyte = blob.getContentType();
//            
//            types.add(blobTyte);
//        	
//        }
//        
//        
//        return types;
//        //return sendResponse("200", "Successfully Downloaded!");
//	}
}
