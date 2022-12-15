//package com.shola.kloubasajobs.services;
//
//
//import com.shola.kloubasajobs.model.response.JobResponse;
//import com.shola.kloubasajobs.repository.JobResponseRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.naming.InvalidNameException;
//import java.io.IOException;
//
//public class JobResponseService {
//
//    private static final Logger logger = LoggerFactory.getLogger(JobResponseService.class);
//
//    @Autowired
//    private JobResponseRepository jobResponseRepository;
//
//    public JobResponse storeFile(MultipartFile file){
//
//        //Normalize filename
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try{
//            if(fileName.contains("...")){
//                throw new InvalidNameException("Sorry file name contains invalid sequence."+fileName)
//            }
//            DB
//
//        }catch(IOException ioException){
//            logger.debug("File couldn't be stored. Please try again.");
//
//        }
//    }
//
//}
