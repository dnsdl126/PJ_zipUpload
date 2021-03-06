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
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zipUpload.service.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class zipcontroller {
	
	@Autowired
	service zipService;
	
		//tree 구조 가장 마지막에 test code 짤 예정
		@GetMapping("/zipOpen")
		public String ziplist(Model model) {
			
			return "zipupload/zipOpen";
		}
		
		//압축해제 진행 
		@PostMapping("/zipOpen")
		public String zipopen(MultipartFile file , MultipartHttpServletRequest request) throws IllegalStateException, IOException {
			
			String openPath = "C:\\Upload\\open";
			
			file = request.getFile("Open");
			
			
			
			log.info("컨트롤러 도착 ");
	
			File newFile = new File(openPath + file.getOriginalFilename());
			  
			 file.transferTo(newFile);
		
			
			log.info("이름이 뭘까요 :" + file.getOriginalFilename());
			
			
			return null;
		}
	
		
		//압축해제 view 단
		@GetMapping("/zipcreate")
		public String zipcreate(Model model) {
			
			return "zipupload/zipcreate";
		}
	
		//파일 압축 컨트롤러 
		@ResponseBody
		@PostMapping("/zipcreate")
		public String zipupload(MultipartHttpServletRequest request, MultipartFile file) throws Exception {
			
		
			//String 
			
			//파일 경로
			String filePath = "C:\\Upload\\";
			
			
			//압축할 파일 
			String filename = null;
			
			//view에서 넘어온 첨부파일 
			List<MultipartFile> files = request.getFiles("zipFile");
			
			//files를 담을 파일 파일이 없는 경우 디렉토리 생성
			File drk = new File(filePath);
			
			if(drk.isDirectory()) {
				drk.mkdirs();
			}
			
			
			int t = files.size();
			
			String[] filearray = new String[t]; // Upload에 있는 파일을 담을 list
			
			
		
				
			for (int i=0 ; i< files.size(); i++) {
				
				//log.info(files.get(i).getOriginalFilename() + "업로드");
				filename =  files.get(i).getOriginalFilename(); //filnam에 files의 파일들을 하나씩 담고 
				drk = new File(filePath+filename); // 생성한 Upload 파일의 경로
				files.get(i).transferTo(drk);// multipartfile 을 file로 변환하여 옮겨 담고 
				
				filearray[i] = filename; // 생성해둔 list에 담는다 
				
			}
			
				
			String zipok = zipService.create(filePath, filearray);
			
			//log.info(zipok);
			
			return zipok;
		}
			

		
	}
 
