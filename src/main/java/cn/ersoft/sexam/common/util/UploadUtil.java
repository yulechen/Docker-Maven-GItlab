package cn.ersoft.sexam.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
public abstract class UploadUtil {

    public static File upload(String uploadPath,MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File targetFile = new File(uploadPath,
                UUID.randomUUID().toString().replaceAll("-",""));
        File parentFile = targetFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.error("", e);
            return null;

        }
        return targetFile;
    }
}
