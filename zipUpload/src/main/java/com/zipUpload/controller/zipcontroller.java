package com.zipUpload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class zipcontroller {
	
		//tree 구조 가장 마지막에 test code 짤 예정
		@GetMapping("/zip")
		public String ziplist(Model model) {
			
			return "zipupload/zip";
		}
	
	
	
		//압축해제 view 단
		@GetMapping("/zipcreate")
		public String zipcreate(Model model) {
			
			return "zipupload/zipcreate";
		}
	
		//파일 압축 컨트롤러 
		@PostMapping("/zipcreate")
		public String zipupload(MultipartHttpServletRequest request, MultipartFile file) throws Exception {
			
			
			
			//파일 경로
			String filePath = "C:\\Upload\\";
			
			//압축할 파일 
			String filename;
			
			//파일을 List로 보관
			List<MultipartFile> files = request.getFiles("zipFile");
			
			//파일이 없는 경우 디렉토리 생성
			File drk = new File(filePath);
			
			if(drk.isDirectory()) {
				drk.mkdirs();
			}
			
			
			int t = files.size();
			
			String[] filearray = new String[t];
			
			//  경로의 파일 에 하나씩 담김 
			for (int i=0 ; i< files.size(); i++) {
				log.info(files.get(i).getOriginalFilename() + "업로드");
				filename =  files.get(i).getOriginalFilename();
				drk = new File(filePath + filename);
				files.get(i).transferTo(drk);
				
				filearray[i] = filename;
				
				
			}
			
			int size = 1024;
			byte[] buf = new byte[size];
			String outZip = filePath + "test.zip";
			
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
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			
			return null;
			
			
		}
			
		

		
	}
 
