package com.bcdeproject.global.image.service;

import com.bcdeproject.domain.boast.imgpath.BoastImgPath;
import com.bcdeproject.global.image.exception.ImageException;
import com.bcdeproject.global.image.exception.ImageExceptionType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    @Value("${image.dir}")//application.yml 파일에 있는 file.dir의 내용을 가져옴
    private String imgDir;


    @Override
    public String save(MultipartFile multipartFile)  {


        int extIdx = multipartFile.getOriginalFilename().lastIndexOf(".");
        String extension = multipartFile.getOriginalFilename().substring(extIdx+1);

        String imgPath = imgDir + UUID.randomUUID()+"."+extension;
        try {
            multipartFile.transferTo(new File(imgPath)); // 파일을 지정한 경로에 저장
        }catch (IOException e){
            //이미지 저장 에러!
            throw new ImageException(ImageExceptionType.IMAGE_CAN_NOT_SAVE);
        }


        return imgPath; // 지정한 경로 반환
    }

    @Override
    public void delete(List<BoastImgPath> imgPaths) {
        
        for(BoastImgPath imgPath : imgPaths) {
            String extract_imgPath = imgPath.getImgPath();
            File file = new File(extract_imgPath);
            // file.delete()를 한 순간, 파일이 있으면 삭제되고, 없으면 예외 발생
            if(!file.delete()) throw new ImageException(ImageExceptionType.IMAGE_CAN_NOT_DELETE);
        }





    }
}