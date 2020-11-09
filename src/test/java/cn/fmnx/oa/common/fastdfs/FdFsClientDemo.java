package cn.fmnx.oa.common.fastdfs;

import cn.fmnx.oa.OaApplication;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;

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
    @Test
    public void down() throws Exception {
        String url = "group1/M00/00/00/rBLB7V5SRsyAUI20AABWAMMzhrU113.xls";
        String fileName = "1117124170.xls";
        String group = url.substring(0, url.indexOf("/"));
        System.out.println("group = " + group);
        String path = url.substring(url.indexOf("/") + 1);
        System.out.println("path = " + path);
        byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
        File file = new File("E:\\down\\"+fileName);
        String s = file.toString();
        System.out.println("s = " + s);
        //file.delete();
        FileOutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.close();
    }
    @Test
    public void down1() throws Exception {
        String url = "http://120.79.23.95/group1/M00/00/00/rBLB7V5SRsyAUI20AABWAMMzhrU113.xls";
        String fileName = "1117124170.xls";
        System.out.println(url.indexOf("group1"));
        String group = url.substring(url.indexOf("group1"),url.indexOf("/M00"));
        System.out.println("group = " + group);
        String path = url.substring(url.indexOf("M00/"));
        System.out.println("path = " + path);
        String jar_parent = new File(ResourceUtils.getURL("classpath:").getPath())
                .getParentFile()
                .getParentFile()
                .getParent();
        byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
        System.out.println("jar_parent+fileName = " + jar_parent+File.separator+fileName);
        OutputStream out = new FileOutputStream(jar_parent+File.separator+fileName);
        out.write(bytes);
        out.close();


    }
    @Test
    public void path() throws Exception {
//        String url = "http://120.79.23.95/group1/M00/00/00/rBLB7V5SRsyAUI20AABWAMMzhrU113.xls";
//        String fileName = "1117124170.xls";
//        System.out.println(url.indexOf("group1"));
//        String group = url.substring(url.indexOf("group1"),url.indexOf("/M00"));
//        System.out.println("group = " + group);
//        String path = url.substring(url.indexOf("M00/"));
//        System.out.println("path = " + path);
//        File file = new File("down"+File.separator+fileName);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        byte[] bytes = storageClient.downloadFile(group, path, new DownloadByteArray());
//
//        OutputStream out = new FileOutputStream(file);
//        out.write(bytes);
//        out.close();
       File file = new File(ResourceUtils.getURL("").getPath());


    }

}

