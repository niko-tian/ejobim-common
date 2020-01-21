package com.ejobim.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 参数验证器
 * 验证参数，如果不符合则抛出参数异常ParameterException
 * @author zch
 */
@Component
public class ParameterValidator {

    /**
     * 参数必填
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void required(@NonNull String parameterName, Object parameterValue, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null) {
            throw new ParameterException(parameterName, parameterDesc + "不能为空");
        }
        if ((parameterValue instanceof String)) {
            this.notEmpty(parameterName, (String) parameterValue, parameterDesc);
        }
        if ((parameterValue instanceof Collection)) {
            this.notEmpty(parameterName, (Collection) parameterValue, parameterDesc);
        }
    }

    /**
     * 参数非空
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void notEmpty(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null) {
            return;
        }
        if (parameterValue.isEmpty()) {
            throw new ParameterException(parameterName, parameterDesc + "不能为空");
        }
    }

    /**
     * 把空值参数转成null
     * @param parameterValue 参数值
     * @return 参数值或null（如果参数值为空）
     */
    public String emptyToNull(String parameterValue) {
        if (StringUtils.isEmpty(parameterValue)) {
            return null;
        } else {
            return parameterValue;
        }
    }

    /**
     * 参数非空白
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void notBlank(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null) {
            return;
        }
        if (StringUtils.isBlank(parameterValue)) {
            throw new ParameterException(parameterName, parameterDesc + "不能为空");
        }
    }

    /**
     * 把空白参数转成null
     * @param parameterValue 参数值
     * @return 参数值或null（如果参数值为空白）
     */
    public String blankToNull(String parameterValue) {
        if (parameterValue == null) {
            return parameterValue;
        }
        if (StringUtils.isBlank(parameterValue)) {
            return null;
        } else {
            return parameterValue;
        }
    }

    /**
     * 把空白参数转成空
     * @param parameterValue 参数值
     * @return 参数值或空字符串（如果参数值为空白）
     */
    public String blankToEmpty(String parameterValue) {
        if (parameterValue == null) {
            return parameterValue;
        }
        if (StringUtils.isBlank(parameterValue)) {
            return StringUtils.EMPTY;
        } else {
            return parameterValue;
        }
    }

    /**
     * 集合非空
     * @param parameterName 参数名
     * @param parameterValue 参数值（集合）
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void notEmpty(@NonNull String parameterName, Collection parameterValue, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null) {
            return;
        }
        if (parameterValue.isEmpty()) {
            throw new ParameterException(parameterName, parameterDesc + "不能为空");
        }
    }

    /**
     * 参数长度在范围内
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param minLength 最小长度（包含）
     * @param maxLength 最大长度（包含）
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void lengthInRange(@NonNull String parameterName, String parameterValue, Integer minLength, Integer maxLength, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null) {
            return;
        }
        if (minLength != null && parameterValue.length() < minLength) {
            throw new ParameterException(parameterName, parameterDesc + "长度超出范围");
        }
        if (maxLength != null && parameterValue.length() > maxLength) {
            throw new ParameterException(parameterName, parameterDesc + "长度超出范围");
        }
    }

    /**
     * 参数正则匹配
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param pattern 正则模式
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void matched(@NonNull String parameterName, String parameterValue, @NonNull String pattern, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return;
        }
        if (!parameterValue.matches(pattern)) {
            throw new ParameterException(parameterName, parameterDesc + "不是有效值");
        }
    }

    /**
     * 参数是email
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void isEmail(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        String pattern = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        this.matched(parameterName, parameterValue, pattern, parameterDesc);
    }

    /**
     * 参数是IP（v4)
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void isIPv4(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        String pattern = "((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}";
        this.matched(parameterName, parameterValue, pattern, parameterDesc);
    }

    /**
     * 参数是域名
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void isDomain(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        String pattern = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
        this.matched(parameterName, parameterValue, pattern, parameterDesc);
    }

    /**
     * 参数是主机地址
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void isHost(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return;
        }
        String ipPattern = "((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}";
        String domainPattern = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
        if (!parameterValue.matches(ipPattern) && !parameterValue.matches(domainPattern)) {
            throw new ParameterException(parameterName, parameterDesc + "不是有效值");
        }
    }

    /**
     * 参数是手机号
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void isMobile(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        String pattern = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
        this.matched(parameterName, parameterValue, pattern, parameterDesc);
    }

    /**
     * 参数是Html颜色值
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public void isHtmlColor(@NonNull String parameterName, String parameterValue, @NonNull String parameterDesc) throws ParameterException {
        String pattern = "^#[0-9a-fA-F]{6}$";
        this.matched(parameterName, parameterValue, pattern, parameterDesc);
    }

    /**
     * 两个参数是一个范围
     * @param <T> 参数类型
     * @param parameter1Name 参数1名
     * @param parameter1Value 参数1值
     * @param parameter1Desc 参数1描述
     * @param parameter2Name 参数2名
     * @param parameter2Value 参数2值
     * @param parameter2Desc 参数2描述
     * @throws ParameterException 参数异常
     */
    public <T extends Comparable> void isRange(@NonNull String parameter1Name, T parameter1Value, @NonNull String parameter1Desc, @NonNull String parameter2Name, T parameter2Value, @NonNull String parameter2Desc) throws ParameterException {
        if (parameter1Value == null || parameter2Value == null) {
            return;
        }
        if (parameter1Value.compareTo(parameter2Value) > 0) {
            ParameterException ex = new ParameterException(parameter1Name, parameter1Desc + "不能大于" + parameter2Desc);
            throw ex.add(parameter2Name, parameter2Desc + "不能小于" + parameter1Desc);
        }
    }

    /**
     * 参数在范围内
     * @param <T> 参数类型
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param min 最小值（包括）
     * @param max 最大值（包括）
     * @param parameterDesc 参数描述
     * @throws ParameterException 参数异常
     */
    public <T extends Comparable> void inRange(@NonNull String parameterName, T parameterValue, T min, T max, @NonNull String parameterDesc) throws ParameterException {
        if (parameterValue == null) {
            return;
        }
        if (min != null && parameterValue.compareTo(min) < 0) {
            throw new ParameterException(parameterName, parameterDesc + "超出范围");
        }
        if (max != null && parameterValue.compareTo(max) > 0) {
            throw new ParameterException(parameterName, parameterDesc + "超出范围");
        }
    }

    /**
     * 参数是排序方式
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param sortableProperties 可排序属性
     * @throws ParameterException 参数异常
     */
    public void isSort(@NonNull String parameterName, String parameterValue, String... sortableProperties) throws ParameterException {
        if (parameterValue == null) {
            return;
        }
        List<String> properties = Arrays.asList(sortableProperties);
        String[] entries = parameterValue.split(",");
        for (String entry : entries) {
            String[] parts = entry.trim().split("\\s");
            if (parts.length > 2) {
                throw new ParameterException(parameterName, "排序格式不正确");
            }
            String property = parts[0].trim();
            if (parts.length > 1) {
                if ("DESC".equalsIgnoreCase(parts[1].trim())) {
                } else if ("ASC".equalsIgnoreCase(parts[1].trim())) {
                } else {
                    throw new ParameterException(parameterName, "排序格式不正确");
                }
            }
            if (!properties.isEmpty() && !properties.contains(property)) {
                throw new ParameterException(parameterName, property + "不是可排序属性");
            }
        }
    }

    /**
     * 解析整数
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @return 整数或null
     * @throws ParameterException 参数异常
     */
    public Integer parseInt(@NonNull String parameterName, String parameterValue) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(parameterValue);
        } catch (NumberFormatException ex) {
            throw new ParameterException(parameterName, "整数格式不正确");
        }
    }

    /**
     * 解析长整数
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @return 长整数或null
     * @throws ParameterException 参数异常
     */
    public Long parseLong(@NonNull String parameterName, String parameterValue) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(parameterValue);
        } catch (NumberFormatException ex) {
            throw new ParameterException(parameterName, "整数格式不正确");
        }
    }

    /**
     * 解析浮点数
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @return 浮点数或null
     * @throws ParameterException 参数异常
     */
    public Float parseFloat(@NonNull String parameterName, String parameterValue) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return null;
        }
        try {
            return Float.parseFloat(parameterValue);
        } catch (NumberFormatException ex) {
            throw new ParameterException(parameterName, "浮点数格式不正确");
        }
    }

    /**
     * 解析双精度数
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @return 双精度数或null
     * @throws ParameterException 参数异常
     */
    public Double parseDouble(@NonNull String parameterName, String parameterValue) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(parameterValue);
        } catch (NumberFormatException ex) {
            throw new ParameterException(parameterName, "浮点数格式不正确");
        }
    }

    /**
     * 解析日期
     * @param parameterName 参数名
     * @param parameterValue 参数值
     * @param dateFormat 日期格式
     * @return 日期
     * @throws ParameterException 参数异常
     */
    public Date parseDate(@NonNull String parameterName, String parameterValue, @NonNull String dateFormat) throws ParameterException {
        if (parameterValue == null || parameterValue.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat(dateFormat).parse(parameterValue);
        } catch (ParseException ex) {
            throw new ParameterException(parameterName, "日期或时间格式不正确");
        }
    }

}
