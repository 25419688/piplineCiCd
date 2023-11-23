package com.mycompany.rentCar.Services.Impl;

import com.mycompany.rentCar.Entities.Cars;
import com.mycompany.rentCar.Entities.Image;
import com.mycompany.rentCar.Repositories.CarsRepository;
import com.mycompany.rentCar.Repositories.ImageRepository;
import com.mycompany.rentCar.Services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ImageServiceImp implements ImageService{
    private final ImageRepository imageRepository;
    private final CarsRepository carsRepository;
    @Override
    public Image getImage(String id) {
        return null;
    }
    @Override
    public Image addImage(MultipartFile image, Long carId) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        String fileType = image.getContentType();
        byte[] data = image.getBytes();
        Image img = new Image(fileName, fileType, data);
        Cars car = carsRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("La voiture avec l'ID " + carId + " n'a pas été trouvée"));
        img.setCars(car);
        return imageRepository.save(img);
    }



}



