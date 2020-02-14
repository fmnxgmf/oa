package cn.fmnx.oa.common.fastdfs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UploadProperties
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
@ConfigurationProperties(prefix = "oa.upload")
@Data
public class UploadProperties {
    private String baseUrl;
    private List<String> allowTypes;
}
