package ViewController;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component  
@Scope("prototype")   
@RequestMapping("/downloadFile") 
public class DownloadServlet {
	
	    @RequestMapping("download")    
	    public ResponseEntity<byte[]> download(String path,String fileName) throws IOException {    
	        //String path="E:/GitProject/hello.txt";  
	        File file=new File(path);  
	        HttpHeaders headers = new HttpHeaders();    
	      //  String fileName="hello.txt";//为了解决中文名称乱码问题  
	        headers.setContentDispositionFormData("attachment", fileName);   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                                          headers, HttpStatus.CREATED);    
	    }    
}
