package cn.fmnx.oa.contoller.attachment;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.enums.ExceptionEnum;
import cn.fmnx.oa.common.exception.OaException;
import cn.fmnx.oa.contoller.attachment.dto.AddAttachmentDTO;
import cn.fmnx.oa.service.attachmentService.impl.AttachmentServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AttachmentController
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/23
 * @Version V1.0
 **/
@RestController
@Api(value = "上传附件的接口",tags = "上传附件的接口")
public class AttachmentController {
    @Autowired
    private AttachmentServiceImpl attachmentService;
    /**
     * @MethodName: uploadAttachment
     * @Description: 上传附件的接口返回mailFileid
     * @Param: [file, userId]
     * @Return: cn.fmnx.oa.common.ResultUtils.ResultModel
     * @Author: gmf
     * @Date: 2020/2/23
    **/
    @ApiOperation(value = "上传附件的接口返回mailFileid",notes = "返回附件id(mailFileid)")
    @ApiImplicitParams({

            @ApiImplicitParam(value = "上传的文件的用户id" ,name = "userId",required = true),
    })
    @PostMapping("/uploadAttachment")
    public ResultModel uploadAttachment(@RequestParam("file")@ApiParam(value = "上传的文件类型为MultipartFile的格式，大小设置不大于20M",required = true)
                                                    MultipartFile file,@RequestParam("userId")Long userId){
        Long mailFileid = attachmentService.uploadAttachment(file,userId);
        Map map = new HashMap<>(2);
        if (mailFileid !=null && mailFileid != 0){
            map.put("mailFileid",mailFileid);
            return ResultModel.ok(map);
        }else {
            throw new OaException(ExceptionEnum.UPLOAD_FAILED_ERROE);
        }

    }

}
