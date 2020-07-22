package com.zipUpload.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
			
			String zipFile = "douwnload.zip";
			
			//파일을 List로 보관
			List<MultipartFile> files = request.getFiles("zipFile");
			
			//파일이 없는 경우 디렉토리 생성
			File drk = new File(filePath);
			
			if(drk .exists() == false) {
				drk.mkdirs();
			}
			
			//  경로의 파일 에 하나씩 담김 
			for (int i=0 ; i< files.size(); i++) {
				log.info(files.get(i).getOriginalFilename() + "업로드");
				drk = new File(filePath + files.get(i).getOriginalFilename());
				files.get(i).transferTo(drk);
				
			}
			
			// drk에 업로드한 파일 들을 배열로 담는다 
			String[] entries = drk.list();
			
			
			
			
			return null;
		}
			
		

		
	}
 
