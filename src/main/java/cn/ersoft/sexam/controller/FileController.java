package cn.ersoft.sexam.controller;


import cn.ersoft.sexam.common.api.ApiResult;
import cn.ersoft.sexam.common.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 通用文件上传接口
 */
@Slf4j
@RestController
public class FileController extends BaseController {

    @Value("${sexam.upload-path}")
    private String upoadPath;

    @Value("${sexam.resources-url}")
    private String resourcesUrl;

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/file/upload")
    @ResponseBody
    public ApiResult upload(@RequestParam(value = "file", required = false) MultipartFile file,
                            HttpServletRequest request) {
        File uploadResult = UploadUtil.upload(upoadPath,file);
        if(null ==uploadResult )
            return error("上传文件异常");
        return success(resourcesUrl+uploadResult.getName());
    }




}
