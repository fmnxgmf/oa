package cn.fmnx.oa.common.fastdfs;

import cn.fmnx.oa.OaApplication;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @ClassName FdFsClientDemo
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/4
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OaApplication.class)
public class FdFsClientDemo {
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;
    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("H:\\sojson.com.png");
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "png", null
        );
        //带分组路径
        System.out.println(storePath.getFullPath());
        //不带分钟路径
        System.out.println(storePath.getPath());
    }
    @Test
    //生成缩略图
    public void testUploadAddThumb() throws FileNotFoundException {
        File file = new File("H:\\图片\\png\\1.png");
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "png", null
        );
        //带分组路径
        System.out.println(storePath.getFullPath());
        //不带分钟路径
        System.out.println(storePath.getPath());
        // 获取缩略图路径
        String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(thumbImagePath);
    }
}
