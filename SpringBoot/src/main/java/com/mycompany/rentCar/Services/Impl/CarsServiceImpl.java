package com.mycompany.rentCar.Services.Impl;

import com.mycompany.rentCar.CarDTO.CarDTO;
import com.mycompany.rentCar.Entities.Cars;
import com.mycompany.rentCar.Entities.Image;
import com.mycompany.rentCar.Repositories.CarsRepository;
import com.mycompany.rentCar.Services.CarsService;
import com.mycompany.rentCar.Services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CarsServiceImpl implements CarsService {
    private final CarsRepository carsRepository;
    private final ImageService imageService;

    @Override
    public Cars addCar(Cars car) {
        return carsRepository.save(car);
    }

    @Override
    public Cars updatecar(Long carId, Cars updatedCar, MultipartFile newImage) throws IOException {
        Cars existingCar = carsRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("La voiture avec l'ID " + carId + " n'a pas été trouvée"));

        existingCar.setName(updatedCar.getName());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setNb_doors(updatedCar.getNb_doors());
        existingCar.setNb_places(updatedCar.getNb_places());
        existingCar.setAddress(updatedCar.getAddress());
        existingCar.setDescription((updatedCar.getDescription()));
        existingCar.setPrice_per_day(updatedCar.getPrice_per_day());
        existingCar.setRegistration_num(updatedCar.getRegistration_num());
        existingCar.setGearbox(updatedCar.getGearbox());

        Image existingImage = existingCar.getImage();
        if (existingImage == null) {
            Image newImageEntity = imageService.addImage(newImage, carId);
            existingCar.setImage(newImageEntity);
        } else {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(newImage.getOriginalFilename()));
            String fileType = newImage.getContentType();
            byte[] data = newImage.getBytes();
            existingImage.setFileName(fileName);
            existingImage.setFileType(fileType);
            existingImage.setData(data);
        }
        return carsRepository.save(existingCar);
    }
     @Override
    public List<CarDTO> getAllCars() {
        List<Cars> allCars = (List<Cars>) carsRepository.findAll();
         List<CarDTO> carsWithImages = new ArrayList<>();

         for (Cars car : allCars) {
             CarDTO carDTO = new CarDTO(car);

             // Récupérez l'image associée à la voiture
             Image image = car.getImage();
             if (image != null) {
                 carDTO.setImageId(image.getId());
                 carDTO.setImageData(image.getData());
                 carDTO.setImageFileType(image.getFileType());
                 // Ajoutez d'autres propriétés d'image si nécessaires
             }

             carsWithImages.add(carDTO);
         }

         return carsWithImages;
    }
    @Override
    public Cars getCarById(Long carId) {
        return carsRepository.findById(carId)
                .orElse(null);
    }

//    @Override
//    public List<CarDTO> getCarsByName(String name) {
//        List<Cars> cars = carsRepository.findByName(name);
//        List<CarDTO> carsWithImages = new ArrayList<>();
//        for (Cars car : cars) {
//            CarDTO carDTO = new CarDTO(car);
//
//            // Récupérez l'image associée à la voiture
//            Image image = car.getImage();
//            if (image != null) {
//                carDTO.setImageId(image.getId());
//                carDTO.setImageData(image.getData());
//                carDTO.setImageFileType(image.getFileType());
//                // Ajoutez d'autres propriétés d'image si nécessaires
//            }
//
//            carsWithImages.add(carDTO);
////        return cars.stream()
////                .map(CarDTO::new) // Convertir en DTO si nécessaire
////                .collect(Collectors.toList());
//            return carsWithImages;
//    }
//    }
//@Override
//public List<CarDTO> getCarsByName(String name) {
//    List<Cars> cars = carsRepository.findByName(name);
//    List<CarDTO> carsWithImages = new ArrayList<>();
//
//    for (Cars car : cars) {
//        CarDTO carDTO = new CarDTO(car);
//
//        // Récupérez l'image associée à la voiture
//        Image image = car.getImage();
//        if (image != null) {
//            carDTO.setImageId(image.getId());
//            carDTO.setImageData(image.getData());
//            carDTO.setImageFileType(image.getFileType());
//            // Ajoutez d'autres propriétés d'image si nécessaire
//        }
//
//        carsWithImages.add(carDTO);
//    }
//
//    return carsWithImages; // Retournez la liste complète des voitures trouvées
//}
//public List<CarDTO> getCarsByName(String name) {
//    List<CarDTO> matchingCars = new ArrayList<>();
//
//    // Récupérez toutes les voitures avec leurs images
//    List<CarDTO> allCars = getAllCars();
//
//    // Parcourez la liste de toutes les voitures
//    for (CarDTO car : allCars) {
//        if (car.getName().equalsIgnoreCase(name)) {
//            // Si le nom de la voiture correspond à la recherche, ajoutez-la à la liste de résultats
//            matchingCars.add(car);
//        }
//    }
//
//    return matchingCars;
//}
public List<CarDTO> getCarsByName(String name) {
    List<CarDTO> matchingCars = new ArrayList<>();

    try {
        // Récupérez toutes les voitures avec leurs images
        List<CarDTO> allCars = getAllCars();

        // Parcourez la liste de toutes les voitures
        for (CarDTO car : allCars) {
            if (car != null && car.getName() != null && car.getName().equalsIgnoreCase(name)) {
                // Si le nom de la voiture correspond à la recherche, ajoutez-la à la liste de résultats
                matchingCars.add(car);
            }
        }
    } catch (Exception e) {
        // Gérez l'exception ici, par exemple, en enregistrant l'erreur ou en renvoyant une liste vide
        e.printStackTrace(); // Enregistrez l'exception dans les journaux
        // Vous pouvez également renvoyer une réponse d'erreur ici si nécessaire
    }

    return matchingCars;
}


   @Override
    public List<CarDTO> getCarsByModel(String model) {
        List<CarDTO> matchingCars = new ArrayList<>();
        try {
            // Récupérez toutes les voitures avec leurs images
            List<CarDTO> allCars = getAllCars();
            // Parcourez la liste de toutes les voitures
            for (CarDTO car : allCars) {
                if (car != null && car.getModel() != null && car.getModel().equalsIgnoreCase(model)) {
                    // Si le nom de la voiture correspond à la recherche, ajoutez-la à la liste de résultats
                    matchingCars.add(car);
                }
            }
        } catch (Exception e) {
            // Gérez l'exception ici, par exemple, en enregistrant l'erreur ou en renvoyant une liste vide
            e.printStackTrace(); // Enregistrez l'exception dans les journaux
            // Vous pouvez également renvoyer une réponse d'erreur ici si nécessaire
        }
        return matchingCars;
    }
    @Override
    public List<CarDTO> getCarsByAddress(String address) {
        List<CarDTO> matchingCars = new ArrayList<>();
        try {
            // Récupérez toutes les voitures avec leurs images
            List<CarDTO> allCars = getAllCars();
            // Parcourez la liste de toutes les voitures
            for (CarDTO car : allCars) {
                if (car != null && car.getAddress() != null && car.getAddress().equalsIgnoreCase(address)) {
                    // Si le nom de la voiture correspond à la recherche, ajoutez-la à la liste de résultats
                    matchingCars.add(car);
                }
            }
        } catch (Exception e) {
            // Gérez l'exception ici, par exemple, en enregistrant l'erreur ou en renvoyant une liste vide
            e.printStackTrace(); // Enregistrez l'exception dans les journaux
            // Vous pouvez également renvoyer une réponse d'erreur ici si nécessaire
        }
        return matchingCars;
    }
    @Override
    public List<CarDTO> getCarsByModelAndAddress(String model, String address) {
        List<CarDTO> matchingCars = new ArrayList<>();
        try {
            List<CarDTO> allCars = getAllCars();
            for (CarDTO car : allCars) {
                if (car != null && car.getModel() != null && car.getModel().equalsIgnoreCase(model) &&
                        car.getAddress() != null && car.getAddress().equalsIgnoreCase(address)) {
                    matchingCars.add(car);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchingCars;
    }
//    @Override
//    public List<CarDTO> searchCarsByName(String searchTerm) {
//        List<CarDTO> matchingCars = new ArrayList<>();
//
//        // Récupérez toutes les voitures avec leurs images
//        List<CarDTO> allCars = getAllCars();
//
//        // Parcourez la liste de toutes les voitures
//        for (CarDTO car : allCars) {
//            if (car.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
//                // Si le nom de la voiture contient le terme de recherche (insensible à la casse), ajoutez-la à la liste de résultats
//                matchingCars.add(car);
//            }
//        }
//
//        return matchingCars;
//    }
@Override
public List<CarDTO> searchCarsByName(String searchTerm) {
    List<CarDTO> matchingCars = new ArrayList<>();

    try {
        // Récupérez toutes les voitures avec leurs images
        List<CarDTO> allCars = getAllCars();

        // Parcourez la liste de toutes les voitures
        for (CarDTO car : allCars) {
            if (car != null && car.getName() != null && car.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                // Si le nom de la voiture contient le terme de recherche (insensible à la casse), ajoutez-la à la liste de résultats
                matchingCars.add(car);
            }
        }
    } catch (Exception e) {
        e.printStackTrace(); // Enregistrez l'exception dans les journaux (vous pouvez personnaliser ceci)
        // Vous pouvez également renvoyer une réponse d'erreur appropriée ici si nécessaire
    }

    return matchingCars;
}
    @Override
    public List<CarDTO> searchCarsByModel(String searchTerm) {
        List<CarDTO> matchingCars = new ArrayList<>();

        try {
            // Récupérez toutes les voitures avec leurs images
            List<CarDTO> allCars = getAllCars();

            // Parcourez la liste de toutes les voitures
            for (CarDTO car : allCars) {
                if (car != null && car.getModel() != null && car.getModel().toLowerCase().contains(searchTerm.toLowerCase())) {
                    // Si le nom de la voiture contient le terme de recherche (insensible à la casse), ajoutez-la à la liste de résultats
                    matchingCars.add(car);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Enregistrez l'exception dans les journaux (vous pouvez personnaliser ceci)
            // Vous pouvez également renvoyer une réponse d'erreur appropriée ici si nécessaire
        }

        return matchingCars;
    }

    @Override
    public List<CarDTO> searchCarsByModelAndAddress(String searchTermModel, String searchTermAddress) {
        List<CarDTO> matchingCars = new ArrayList<>();

        try {
            // Récupérez toutes les voitures avec leurs images
            List<CarDTO> allCars = getAllCars();

            // Parcourez la liste de toutes les voitures
            for (CarDTO car : allCars) {
                if (car != null &&
                        (car.getModel() != null && car.getModel().toLowerCase().contains(searchTermModel.toLowerCase()) ||
                                car.getAddress() != null && car.getAddress().toLowerCase().contains(searchTermAddress.toLowerCase()))
                ) {
                    matchingCars.add(car);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Enregistrez l'exception dans les journaux (vous pouvez personnaliser ceci)
            // Vous pouvez également renvoyer une réponse d'erreur appropriée ici si nécessaire
        }

        return matchingCars;
    }


    @Override
    public List<CarDTO> searchCarsBySingleParameter(String searchTerm) {
        List<CarDTO> matchingCars = new ArrayList<>();

        try {
            // Récupérez toutes les voitures avec leurs images
            List<CarDTO> allCars = getAllCars();

            for (CarDTO car : allCars) {
                if (car != null) {
                    if (searchTerm != null && !searchTerm.isEmpty()) {
                        if (searchTerm.matches(".*\\d+.*")) {
                            // Si la chaîne de recherche contient des chiffres, considérez-la comme un prix
                            if (isSearchTermPriceValid(searchTerm, car.getPrice_per_day())) {
                                matchingCars.add(car);
                            }
                        } else if (car.getModel() != null && car.getModel().toLowerCase().contains(searchTerm.toLowerCase())) {
                            // Sinon, considérez-la comme un modèle
                            matchingCars.add(car);
                        } else if (car.getAddress() != null && car.getAddress().toLowerCase().contains(searchTerm.toLowerCase())) {
                            // Sinon, considérez-la comme une adresse
                            matchingCars.add(car);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchingCars;
    }

    private boolean isSearchTermPriceValid(String searchTermPrice, Float carPrice) {
        try {
            Float price = new Float(searchTermPrice);
            // Comparez le prix de la voiture avec le prix recherché
            return carPrice.compareTo(price) == 0;
        } catch (NumberFormatException e) {
            // Gérez l'exception si la conversion du prix échoue
            return false;
        }
    }




}
