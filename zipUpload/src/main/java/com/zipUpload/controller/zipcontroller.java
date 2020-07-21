package com.zipUpload.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

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
			
			String path = "C:/testzip/"; // 파일 경로
			String fileName = ""; //업로드 파일 명
			
			File dir = new File(path);
			
			if(!dir.isDirectory()) {
				
				dir.mkdir();
			}
			
			
			// 코드 분석 다시 하기 
			
			Iterator<String> files = request.getFileNames();
			file = request.getFile(files.next());
			
			List<MultipartFile> fileList = request.getFiles("zipFile");
			
			
			for (MultipartFile filePart : fileList)
			{
				
				fileName = filePart.getOriginalFilename();
				log.info("fileName : " + fileName);
			}
			return null;
		}
			
		

		
	}
 
