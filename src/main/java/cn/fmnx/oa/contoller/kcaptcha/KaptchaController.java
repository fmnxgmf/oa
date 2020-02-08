package cn.fmnx.oa.contoller.kcaptcha;

import cn.fmnx.oa.common.ResultUtils.ResultModel;
import cn.fmnx.oa.common.captcha.VerifyCodeUtils;
import cn.fmnx.oa.common.redis.RedisUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName KaptchaController 图片验证码
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/6
 * @Version V1.0
 **/
@RestController
@Api(tags = "图片验证码相关接口,不用提交token")
public class KaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @MethodName:
     * @Description: TODO
     * @Param: Long devicedId
     * @Return:
     * @Author: gmf
     * @Date: 2020/2/6
    **/
    @GetMapping("/getCode")
    @ApiOperation("获取图片验证码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String",paramType="header"),
            @ApiImplicitParam(name = "deviceId",value = "该设备的唯一性标识即可" ,defaultValue = "1B380160DB6D8E42B2B081F60849FC36")
            }
    )

    public void getCode(@RequestParam("deviceId") String deviceId,
                               HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        //生产验证码字符串并保存到session中
        //第一中生
        //生成图片
        int w = 125,h = 40;
        String s = VerifyCodeUtils.generateVerifyCode(4);
        //第二种
        String text = defaultKaptcha.createText();
        //验证码存放到redis中，过期时间5分钟,单位秒
        redisUtil.set(deviceId+":"+"code",s,300);
       // httpServletRequest.getSession().setAttribute("vrifyCode", text);
        //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
        BufferedImage image = defaultKaptcha.createImage(text);
        try {
            ImageIO.write(image,"jpg",jpegOutputStream);
        } catch (IOException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream  = httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
////        responseOutputStream.flush();
////        responseOutputStream.close();
        VerifyCodeUtils.outputImage(w, h, httpServletResponse.getOutputStream(), s);
    }
}
