package hu.deik.online_chess.service.impl;

import org.apache.commons.io.IOUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {
    private final ResourceLoader resourceLoader;

    public ImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Cacheable("img")
    public byte[] getCachedImage(String imageName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/img/" + imageName);
        InputStream inputStream = resource.getInputStream();
        byte[] data = IOUtils.toByteArray(inputStream);
        inputStream.close();
        return data;
    }
}
