package com.ejobim.spring;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet工具类
 * @author zch
 */
public class ServletUtils {
    
    /**
     * 获取实际IP
     * @param request servlet请求
     * @return 
     */
    public static String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {
            }
        }
        return ip;
    }
    
    /**
     * 获取格式化请求信息
     * @param request Servlet请求
     * @return 
     */
    public static String getInfo(HttpServletRequest request) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("URL:");
        buffer.append(request.getRequestURL());
        buffer.append("\nMETHOD:");
        buffer.append(request.getMethod());
        buffer.append("\nPARAM:");
        Enumeration<String> names = request.getParameterNames();
        for (int i = 0; names.hasMoreElements(); i++) {
            if (i > 0) {
                buffer.append("&");
            }
            String name = names.nextElement();
            buffer.append(name);
            buffer.append("=");
            String[] values = request.getParameterValues(name);
            for (int j = 0; j < values.length; j++) {
                if (j > 0) {
                    buffer.append(",");
                }
                buffer.append(values[j]);
            }
        }
        buffer.append("\nHEADER:");
        Enumeration<String> keys = request.getHeaderNames();
        for (int i = 0; keys.hasMoreElements(); i++) {
            if (i > 0) {
                buffer.append("&");
            }
            String key = keys.nextElement();
            String value = request.getHeader(key);
            buffer.append(key).append("=").append(value);
        }
        buffer.append("\nIP:");
        buffer.append(getRealIP(request));
        return buffer.toString();
    }
    
}
