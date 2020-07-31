package com.ss.ssproj.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ss.ssproj.annotation.*;
import com.ss.ssproj.entity.Admin;
import com.ss.ssproj.entity.Student;
import com.ss.ssproj.entity.Tutor;
import com.ss.ssproj.service.AdminService;
import com.ss.ssproj.service.StudentService;
import com.ss.ssproj.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    StudentService studentService;

    @Autowired
    TutorService tutorService;

    @Autowired
    AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        //如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        if (method.isAnnotationPresent(UserToken.class)) {
            //用户身份，既可以是学生也可以是老师
            UserToken userToken = method.getAnnotation(UserToken.class);
            if (userToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 rid
                String rid;
                try {
                    rid = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                Student student = this.studentService.findDistinctByStudentId(rid);
                Tutor tutor = this.tutorService.findDistinctByTutorId(rid);
                if (student == null && tutor == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                if(student == null) {
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(tutor.getUid())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        throw new RuntimeException("401");
                    }
                } else {
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(student.getUid())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (JWTVerificationException e) {
                        throw new RuntimeException("401");
                    }
                }
                // 若存在，使用用户的uid进行验证
                return true;
            }
        }

        if (method.isAnnotationPresent(StudentLoginToken.class)) {
            //若需要有学生身份的登陆
            StudentLoginToken studentLoginToken = method.getAnnotation(StudentLoginToken.class);
            if (studentLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 rid
                String rid;
                try {
                    rid = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                Student student = this.studentService.findDistinctByStudentId(rid);
                if (student == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 若存在，使用用户的uid进行验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(student.getUid())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        if (method.isAnnotationPresent(TutorLoginToken.class)) {
            //若需要有教师身份的登陆
            TutorLoginToken tutorLoginToken = method.getAnnotation(TutorLoginToken.class);
            if (tutorLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 rid
                String rid;
                try {
                    rid = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                Tutor tutor = this.tutorService.findDistinctByTutorId(rid);
                if (tutor == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 若存在，使用用户的uid进行验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(tutor.getUid())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        if (method.isAnnotationPresent(AdminLoginToken.class)) {
            //若需要有教师身份的登陆
            AdminLoginToken adminLoginToken = method.getAnnotation(AdminLoginToken.class);
            if (adminLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 rid 在admin中userid代替了rid
                String userid;
                try {
                    userid = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                Admin admin = this.adminService.findDistinctByUserid(userid);
                if (admin == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 若存在，使用用户的uid进行验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
