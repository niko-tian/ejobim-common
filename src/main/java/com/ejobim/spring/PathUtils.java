package com.ejobim.spring;

import java.io.File;

/**
 *
 * @author zch
 */
public class PathUtils {
    
    public static String concat(String path1, String path2, String separator) {
        if(path1.endsWith(separator)) {
            if(path2.startsWith(separator)) {
               return path1 + path2.substring(1);
            }
            else {
                return path1 + path2;
            }
        }
        else {
            if(path2.startsWith(separator)) {
                return path1 + path2;
            }
            else {
                return path1 + separator + path2;
            }
        }
    }
    
    public static String concatUrl(String url1, String url2) {
        return concat(url1, url2, "/");
    }
    
    public static String concatFilePath(String path1, String path2) {
        return concat(path1, path2, File.separator);
    }
    
    private PathUtils() {}
    
}
