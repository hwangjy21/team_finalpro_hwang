package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@ToString
@Getter
@Component
@Slf4j
public class FileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";//231120전경환
//	private final String UP_DIR = "/aj2002/tomcat/webapps/_javaweb/_java/fileUpload";//231120전경환

	public List<FileVO> uploadFiles(MultipartFile[] files) {
		List<FileVO> flist = new ArrayList<FileVO>();

		LocalDate date = LocalDate.now(); 
		String today = date.toString();
		today = today.replace("-", File.separator);

		File folders = new File(UP_DIR, today);

		if (!folders.exists()) { // folders가 없다면
			folders.mkdirs();
		}

		for (MultipartFile file : files) {
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());

			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());

			String fullFileName = uuid.toString() + "_" + fileName;

			File storeFile = new File(folders, fullFileName);

			try {
				file.transferTo(storeFile);
				if (isImageFile(storeFile)) {
					fvo.setFileType(1); 
					File thumbNail = new File(folders, uuid.toString() + "_th_" + fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.debug(">>> 파일 생성 오류~!!");
				e.printStackTrace();
			}
			flist.add(fvo);
		}

		return flist;
	}

	private boolean isImageFile(File storeFile) throws IOException {
		// detect => tika는 파일 형식을 확인할 때 Detector라는 인터페이스를 구현한 객체의 detect 메서드를 사용
		String mimeType = new Tika().detect(storeFile); // image/jpg
		return mimeType.startsWith("image") ? true : false;

	}
}
