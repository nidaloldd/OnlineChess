package hu.deik.online_chess.web;

import hu.deik.online_chess.service.impl.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ImageController {
    private final ImageService imageService;
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/img/figures/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        byte[] data = imageService.getCachedImage(imageName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}

