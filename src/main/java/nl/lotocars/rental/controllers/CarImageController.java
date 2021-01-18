package nl.lotocars.rental.controllers;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.exceptions.CarImageNotFoundException;
import nl.lotocars.rental.mapper.CarImageMapper;
import nl.lotocars.rental.entities.CarImage;
import nl.lotocars.rental.services.CarImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("carimage")
public class CarImageController {

    private final CarImageService carImageService;
    private final CarImageMapper carImageMapper;

    @GetMapping("/{numberPlate}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<Long>> getImageIds(@PathVariable String numberPlate){
        Collection<Long> carImageIds = carImageService.getImageIds(numberPlate);
        return new ResponseEntity<Collection<Long>>(carImageIds, HttpStatus.OK);
    }

    @GetMapping(value = "/{numberPlate}/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(
            @PathVariable String numberPlate,
            @PathVariable Long id)
    {
        Optional<CarImage> carImage = carImageService.getCarImageById(id);

        if(!carImage.isPresent()){
            throw new CarImageNotFoundException();
        }

        Byte[] image = carImage.get().getCarImage();
        byte[] convertedImage = new byte[image.length];

        int i = 0;
        for (Byte b: image)
            convertedImage[i++] = b.byteValue();

        return convertedImage;
    }

    @PostMapping("/{numberPlate}")
    public ResponseEntity<CarImage> handleImagePost(
            @PathVariable String numberPlate,
            @RequestParam("imagefile") MultipartFile file
    ) throws IOException {
        CarImage carImage = carImageService.addImageFile(numberPlate, file);
        return new ResponseEntity<CarImage>(carImage, HttpStatus.OK);
    }
}
