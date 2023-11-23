package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Setter
@Slf4j
public class ProfileFileHandler {
//	private final String UP_DIR = "/aj2002/tomcat/webapps/_javaweb/_java/fileUpload/Profile";//231120전경환
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";//231120전경환
	
	public FileVO uploadFiles(MultipartFile pfile, long empNo) {
		String empNumber = Long.toString(empNo);
		File folders = new File(UP_DIR, empNumber);
		// 폴더 생성
		if (!folders.exists()) {
			folders.mkdir(); 
		}
		FileVO fvo = new FileVO();
		fvo.setSaveDir(empNumber); // 2023\11\05
		fvo.setFileSize(pfile.getSize());

		// 실제 파일이름(ex:이벤트.jpg)
		String originalFileName = pfile.getOriginalFilename();
		String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1);
		fvo.setFileName(fileName);

		UUID uuid = UUID.randomUUID();
		fvo.setUuid(uuid.toString());

		String fullFileName = uuid.toString() + "_" + fileName;

		File storeFile = new File(folders, fullFileName);

		try {
			pfile.transferTo(storeFile);

			// 썸네일 생성(이미지 파일만)
			if (isImageFile(storeFile)) {
				// 이미지 파일만 타입에 1설정
				fvo.setFileType(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		fvo.setEmpNo(empNo);

		return fvo;
	}

	// 이미지 파일인지 확인하는 메서드
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); // image/jpg
		log.info("mimeType>>>>" + mimeType);
		return mimeType.startsWith("image") ? true : false;
	}

}