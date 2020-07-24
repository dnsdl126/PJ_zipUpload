package com.zipUpload.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class service {

	public String create(String filePath,  String[] filearray) {
		
		
		//압축결과
		String zipok = "false";
		
		//zip 파일 생성할 경로
		String zipPath = "zip\\";
		
		UUID uid = UUID.randomUUID();
		
		File ziplist = new File(filePath + zipPath);
		
		
		if(!ziplist.exists()) {
			
			try {
				
				ziplist.mkdirs();
				
			} catch (Exception e) {
				e.getStackTrace();
			}
			
		}
		
		
		
		int size = 1024;
		byte[] buf = new byte[size];
		String outZip = filePath +zipPath +uid.toString()+ ".zip"; // 생성할 파일 이름 
		
		FileInputStream fis = null;
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		
		
		try {
			zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outZip)));
			
			for(int i =0; i < filearray.length; i++) {
				
					fis = new FileInputStream(filePath + "/" + filearray[i]);
					bis = new BufferedInputStream(fis, size);
					
					//zip에 넣을다음 entry를 가져온다
					
					zos.putNextEntry(new ZipEntry(filearray[i]));
	
					//압출 레벨 설정
					// 기본값은 8 최대 9
					final int COM_LEVEL =8 ;
					zos.setLevel(COM_LEVEL);
					
					//준비된 버퍼에서 입출력 스트림으로 wirte
					int len;
					while((len = bis.read(buf, 0, size))!= -1) {
						zos.write(buf, 0, len);
					}
					
					zos.closeEntry();
					bis.close();
					fis.close();

			}
			
			zos.close();
			zipok = delete(filePath, zipok);
		
		//	log.info(delete(filePath, zipok));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return zipok;
		
		}
	
	
	// zip 생성후 복사한 파일 삭제 처리 
	private String delete(String filePath, String zipok) {

	
		File deleteList = new File(filePath); // 해당경로 파일객체 생성 
		
		if(deleteList.exists()) { 
			
			File[] fileDelete = deleteList.listFiles(); // drk 에서 담겨있는 file list 가지고 오기
			
			for (int j=0; j<fileDelete.length; j++) {
				
				log.info("파일 담겼나 " + fileDelete[j]); // C:\Upload\zip 
				fileDelete[j].delete();
				
			}
			
			
		}
		
		
		
		zipok = "ok";
		return zipok;
		
	}



	
	
	
	}

