package cn.fmnx.oa.common.jwt.interceptor;

import cn.fmnx.oa.common.jwt.JwtProperties;
import cn.fmnx.oa.common.jwt.JwtUtils;
import cn.fmnx.oa.common.jwt.config.JwtConfig;
import cn.fmnx.oa.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/21 20:40
 * @version:
 * @modified By:
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /** 地址过滤 */
//        String uri = request.getRequestURI();
//        if(uri.contains("/favicon.ico")){
//            return  true;
//        }
        /** Token 验证 */
        String token = request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtConfig.getHeader());
        }
        if(StringUtils.isEmpty(token)){
            throw new SignatureException(jwtConfig.getHeader()+ "不能为空");
        }
        //解析token
        User user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        request.getSession().setAttribute("uid",user.getUserId());
        return true;
    }
}
