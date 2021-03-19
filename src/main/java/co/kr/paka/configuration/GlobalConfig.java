package co.kr.paka.configuration;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.annotation.PostConstruct;
import java.util.Properties;

public class GlobalConfig {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private ResourceLoader resourceLoader;

    private String uploadFilePath;
    private String uploadResourcePath;

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init() {
        logger.info("init");
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String activeProfile = "local";

        if (ObjectUtils.isNotEmpty(activeProfiles)) {
            activeProfile = activeProfiles[0];
        }

        String resourcePath = String.format("classpath:globals/global-%s.properties", activeProfile);
        try {
            Resource resource = resourceLoader.getResource(resourcePath);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            uploadFilePath = properties.getProperty("uploadFile.path");
            uploadResourcePath = properties.getProperty("uploadFile.resourcePath");
        } catch (Exception e) {
            logger.error("e", e);
        }
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }
    public String getUploadResourcePath() {
        return uploadResourcePath;
    }
}
