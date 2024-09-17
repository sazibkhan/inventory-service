package com.inventory.inventoryservice.fileupload;


import com.inventory.inventoryservice.utils.Utils;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.inventory.inventoryservice.exceptions.CustomException;
import com.inventory.inventoryservice.fileupload.model.FileUpload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Builder
public class FileUploadService {

    private final FileUploadRepository fileUploadRepository;
    private final String FOLDER_PATH="src/main/resources/static/";



    public void uploadFile(MultipartFile file) {

        String filePath = new StringBuilder("C:\\FileUpload\\inventory")
                .append("/").toString();

        Path uploadPath = Paths.get(filePath);

        String uuid = UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename();
        String fileExtension = Utils.getExtension(file.getOriginalFilename())
                .orElseThrow(()-> new IllegalArgumentException(String
                        .format("File extension not found with [%s]", originalFilename)));
        String formattedFileName = uuid.concat(uuid).concat(".").concat(fileExtension);
        Path formattedFilePath = uploadPath.resolve(formattedFileName);
        try {
            if (!Files.exists(uploadPath)){
                Files.createDirectory(uploadPath);

            }
            Files.copy(file.getInputStream(), formattedFilePath);
        } catch (IOException e) {
            e.printStackTrace();

       }
    }

    public String downloadFile(String name) {
        try {
            String filePath = new StringBuilder("C:\\FileUpload\\inventory").append("/").append(name).toString();
            byte[] fileContent = Files.readAllBytes(new File(filePath).toPath());
            String base64String = Base64.getEncoder().encodeToString(fileContent);
            return base64String;
        } catch (IOException e) {
            throw new IllegalStateException("Could not read file " ,e);
    }
}




//    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
//        String filePath=FOLDER_PATH+file.getOriginalFilename();
//
//        System.out.println("File Type is "+ file.getOriginalFilename());
//        System.out.println("File Type is "+ file.getContentType());
//        System.out.println("File Type is "+ file.getSize());
//
//        if(!file.getContentType().equals("image/png") ){
//            throw new CustomException("Please insert file type JPG or PNG.", HttpStatus.BAD_REQUEST);
//        }
//
//        if(file.getSize() > 50000){
//            throw new CustomException("Please insert file size less than 5MB.", HttpStatus.BAD_REQUEST);
//        }
//
//  //     file.transferTo(new File(filePath));
//
//        try {
//            //making the directory
//            File f = new File(FOLDER_PATH);
//            f.mkdir();
//
//            InputStream inputStream = file.getInputStream();
//            byte data[] = new byte[inputStream.available()];
//            inputStream.read(data);
//
//            FileOutputStream fos = new FileOutputStream(filePath);
//            fos.write(data);
//
//            fos.flush();
//            fos.close();
//
//            FileUpload fileData=fileUploadRepository.save(FileUpload.builder()
//                    .name(file.getOriginalFilename())
//                    .type(file.getContentType())
//                    .filePath(filePath).build());
//            return "file uploaded successfully : " + filePath;
//
//        }catch (Exception e) {
//            System.out.println("FILE UPLOAD FAIL"+e);
//            return null;
//        }
//
//    }
//
//
//
//    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<com.inventory.inventoryservice.fileupload.model.FileUpload> fileData = fileUploadRepository.findByName(fileName);
//        String filePath=fileData.get().getFilePath();
//        byte[] images = Files.readAllBytes(new File(filePath).toPath());
//        return images;
//    }
//
//
//



}
