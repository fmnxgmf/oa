package cn.fmnx.oa.contoller.upload;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.common.fastdfs.UploadProperties;
import cn.fmnx.oa.service.upload.UploadService;
import cn.fmnx.oa.service.upload.impl.UploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UploadController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@RestController
@RequestMapping("/upload")
@Api(tags = "上传图片的接口")
public class UploadController {
    @Autowired
    private UploadServiceImpl uploadService;
    /**
     * @MethodName: uploadImage
     * @Description: 上传图片
     * @Param: [file]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/13
    **/
    @PostMapping("/image")
    @ApiOperation(value = "上传图片的接口，返回一个图片的链接地址imgUrl的值",notes="文件上传图片")
    public ResultModel uploadImage(@RequestParam("file")@ApiParam(value = "上传的文件类型为MultipartFile的格式，大小设置不大于5M" ,required = true) MultipartFile file){
        String imgUrl = uploadService.uploadImage(file);
        Map map = new HashMap(2);
        if (!StringUtils.isEmpty(imgUrl)){
            map.put("imgUrl",imgUrl);
            return ResultModel.ok(map);
        }else {
            throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
        }
    }
}
