package com.baidu.disconf.client.core.filetype;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.disconf.client.common.constants.SupportFileTypeEnum;
import com.baidu.disconf.client.core.filetype.impl.DisconfPropertiesProcessorImpl;
import com.baidu.disconf.client.core.filetype.impl.DisconfXmlProcessorImpl;

/**
 * 
 * @author knightliao
 * 
 */
public class FileTypeProcessorUtils {

    protected static final Logger LOGGER = LoggerFactory.getLogger(FileTypeProcessorUtils.class);

    /**
     * 输入文件名，返回其相应的k-v数据
     * 
     * @return
     */
    public static Map<String, Object> getKvMap(String fileName) throws Exception {

        SupportFileTypeEnum supportFileTypeEnum = SupportFileTypeEnum.getByFileName(fileName);

        DisconfFileTypeProcessor disconfFileTypeProcessor = null;

        //
        // 获取数据
        //
        Map<String, Object> dataMap = null;

        if (supportFileTypeEnum.equals(SupportFileTypeEnum.PROPERTIES)) {

            disconfFileTypeProcessor = new DisconfPropertiesProcessorImpl();
            dataMap = disconfFileTypeProcessor.getKvMap(fileName);

        } else {

            disconfFileTypeProcessor = new DisconfXmlProcessorImpl();
            dataMap = disconfFileTypeProcessor.getKvMap(fileName);
        }

        if (dataMap == null) {
            dataMap = new HashMap<String, Object>();
        }

        //
        // 进行数据过滤
        //
        for (String key : dataMap.keySet()) {

            if (key == null) {
                continue;
            }

            LOGGER.debug(key + "\t" + dataMap.get(key));
        }

        return dataMap;
    }
}