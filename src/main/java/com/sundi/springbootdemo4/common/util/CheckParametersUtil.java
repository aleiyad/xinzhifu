package com.sundi.springbootdemo4.common.util;

import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.common.exceptions.GlobalException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
public class CheckParametersUtil {

    public static void check(Object object) {
        if (object == null) {
            throw new GlobalException(ResponseEnum.MISSING_PARAMETERS);
        }
        if (object instanceof String) {
            if (StringUtil.isEmpty(object.toString())) {
                throw new GlobalException(ResponseEnum.MISSING_PARAMETERS);
            }
        }
        if (object instanceof Collection) {
            Collection<Object> collection = (Collection) object;
            if (CollectionUtils.isEmpty(collection)) {
                throw new GlobalException(ResponseEnum.MISSING_PARAMETERS);
            }
        }
        if (object instanceof MultipartFile){
            MultipartFile file = (MultipartFile)object;
            if (file.isEmpty()){
                throw new GlobalException(ResponseEnum.MISSING_PARAMETERS);
            }
        }
    }
}
