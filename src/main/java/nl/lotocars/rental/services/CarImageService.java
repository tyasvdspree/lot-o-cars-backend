package nl.lotocars.rental.services;

import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.CarImage;
import nl.lotocars.rental.reposetories.CarImageRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository carImageRepository;
    private final CarService carService;


    public Collection<Long> getImageIds(String carIdentifier) {
        return carImageRepository.findIdsByNumberPlate(carIdentifier);
    }

    public Long getMainImageId(String carIdentifier) {
        Long result = carImageRepository.findImageIdByCarId(carIdentifier);

        if (result == null)
            return new Long(0);
        else
            return result;
    }

    public Optional<CarImage> getCarImageById(Long id) {
        return carImageRepository.findById(id);
    }


    @Transactional(readOnly = false)
    public CarImage addImageFile(String carIdentifier, MultipartFile file) throws IOException {

        CarImage newImage = new CarImage();
        Byte[] byteObjects = new Byte[file.getBytes().length];
        int i = 0;

        for (byte b : file.getBytes()) {
            byteObjects[i++] = b;
        }

        Optional<Car> targetCar = carService.getCar(carIdentifier);
        if (targetCar.isPresent()) {
            newImage.setCar(targetCar.get());
        }
        newImage.setCarImage(byteObjects);
        carImageRepository.save(newImage);
        return newImage;
    }

    @Transactional(readOnly = false)
    public void addImageFiles(Car car, Collection<MultipartFile> imageFiles) throws IOException {

        imageFiles.forEach(imageFile -> {
            CarImage newImage = new CarImage();
            newImage.setCar(car);

            Byte[] byteObjects = new Byte[0];

            try {
                byteObjects = new Byte[imageFile.getBytes().length];
                int i = 0;

                for (byte b : imageFile.getBytes()) {
                    byteObjects[i++] = b;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            newImage.setCarImage(byteObjects);
            carImageRepository.save(newImage);
        });
    }

    @Transactional(readOnly = false)
    public void deleteImage(String imageId) {
        var carImage = carImageRepository.getOne(Long.parseLong(imageId));
        carImageRepository.delete(carImage);
    }
}
