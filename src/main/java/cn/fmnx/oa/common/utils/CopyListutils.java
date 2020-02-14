package cn.fmnx.oa.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName CopyListutils
 * @Description: TODO
 * @Author gmf
 * @Date 2020/2/13
 * @Version V1.0
 **/
public abstract class CopyListutils {

    public static<T> void copyListBeanUtils(Object obj, List<T> list2, Class<T> classObj){
        if ((!Objects.isNull(obj)) && (!Objects.isNull(list2))){
                List list = (List) obj;
                list.forEach(item ->{
                    try {
                        T t = classObj.newInstance();
                        BeanUtils.copyProperties(item,t);
                        list2.add(t);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        }
    }
    public static<T> List<T> copyListJson(Object obj,List<T> list,Class<T> tClass){
        if ((!Objects.isNull(obj)) && (!Objects.isNull(list))) {
            String json = JSON.toJSONString(obj);
            return JSON.parseArray(json, tClass);
        }else {
            return null;
        }
    }
}
