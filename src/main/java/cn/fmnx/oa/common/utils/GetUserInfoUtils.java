package cn.fmnx.oa.common.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName getUserInfoUtils
 * @Description: 获取用户的信息
 * @Author gmf
 * @Date 2020/2/15
 * @Version V1.0
 **/
@Data
public class GetUserInfoUtils {
    /**
     * @MethodName: getIpAddr
     * @Description: 获取ip地址
     * @Param: []
     * @Return: java.lang.String
     * @Author: gmf
     * @Date: 2020/2/15
    **/
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip!= null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if (ip.equals("0:0:0:0:0:0:0:1")){
                return "127.0.0.1";
            }
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip!= null && !"".equals(ip)  && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                String i = ip.substring(0, index);
                if (i.equals("0:0:0:0:0:0:0:1")){
                    return "127.0.0.1";
                }
                return i;
            } else {
                if (ip.equals("0:0:0:0:0:0:0:1")){
                    return "127.0.0.1";
                }
                return ip;
            }
        } else {
            String remoteAddr = request.getRemoteAddr();
            if (remoteAddr.equals("0:0:0:0:0:0:0:1")){
                return "127.0.0.1";
            }
            return remoteAddr;

        }
    }
    /**
     * 获取来访者的浏览器版本
     * @param
     * @return
     */
    public static String getRequestBrowserInfo(HttpServletRequest request){
        Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
        // 获取浏览器版本号
        Version version = browser.getVersion(request.getHeader("User-Agent"));
        String info = browser.getName() + "/" + version.getVersion();
       // System.out.println("info = " + info);
        return info;
    }
    /**
     * 获取来访者的sessionId
     */
    public static String getSessionId(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        return sessionId;
    }
    /**
     * 获取来访者的userid
     */
    public static Long getUserId(HttpServletRequest request){
        Long uid = (Long) request.getSession().getAttribute("uid");
        return uid;
    }
}
