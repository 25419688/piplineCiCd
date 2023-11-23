package com.mycompany.rentCar.Controllers;

import com.mycompany.rentCar.ApiResponse;
import com.mycompany.rentCar.CarDTO.CarDTO;
import com.mycompany.rentCar.Entities.Cars;
import com.mycompany.rentCar.Entities.Image;
import com.mycompany.rentCar.Services.CarsService;
import com.mycompany.rentCar.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cars")
public class CarsController {
    private final CarsService carsService;
    private final ImageService imageService;

    @Autowired
    public CarsController(CarsService carsService, ImageService imageService) {
        this.carsService = carsService;
        this.imageService = imageService;
    }
    @PostMapping("/addCar")
    public ResponseEntity<ApiResponse> addCarWithImage(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute Cars car
    ) {
        try {
            Cars savedCar = carsService.addCar(car);
            Image addedImage = imageService.addImage(file, savedCar.getId());
           // return ResponseEntity.ok("Voiture ajoutée avec succès avec l'ID : " + savedCar.getId());
            ApiResponse response = new ApiResponse("Voiture ajoutée avec succès avec l'ID : " + savedCar.getId(), savedCar.getId());
            return ResponseEntity.ok(response);
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Erreur lors de l'ajout de la voiture : " + e.getMessage(), null));
        }
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<String> update(
            @PathVariable Long carId,
            @ModelAttribute Cars updatedCar,
            @RequestParam("file") MultipartFile newImage
    ) {
        try {
            Cars update = carsService.updatecar(carId, updatedCar, newImage);
            return ResponseEntity.ok("Voiture mise à jour avec succès avec l'ID : " + update.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour de la voiture : " + e.getMessage());
        }
    }
     @GetMapping("/getAllCars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        try {
            List<CarDTO> carsWithImages = carsService.getAllCars();
            System.out.println("carsWithImages");
            return ResponseEntity.ok(carsWithImages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }



    @GetMapping("/getCarById/{carId}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long carId) {
        try {
            Cars car = carsService.getCarById(carId);
            if (car != null) {
                CarDTO carDTO = new CarDTO(car);
                return ResponseEntity.ok(carDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

//    @GetMapping("/searchCarsByName")
//    public ResponseEntity<List<CarDTO>> searchCarsByName(@RequestParam String name) {
//        List<CarDTO> matchingCars = carsService.getCarsByName(name);
//        return ResponseEntity.ok(matchingCars);
//    }
//@GetMapping("/searchCarsByName")
//public ResponseEntity<List<CarDTO>> searchCarsByName(@RequestParam String name) {
//    try {
//        List<CarDTO> matchingCars = carsService.getCarsByName(name);
//        return ResponseEntity.ok(matchingCars);
//    } catch (Exception e) {
//        // En cas d'exception, renvoyez une réponse d'erreur
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//}
//@GetMapping("/searchCarsByName")
//public ResponseEntity<List<CarDTO>> searchCarsByName(@RequestParam String name) {
//    try {
//        List<CarDTO> matchingCars = carsService.getCarsByName(name);
//        return ResponseEntity.ok(matchingCars);
//    } catch (NoResultException e) {
//        // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
//        return ResponseEntity.ok(new ArrayList<>());
//    } catch (Exception e) {
//        // Gérez d'autres exceptions ici, si nécessaire
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }
//}
@GetMapping("/searchCarsByName")
public ResponseEntity<List<CarDTO>> searchCarsByName(@RequestParam String name) {
    try {
        List<CarDTO> matchingCars = carsService.getCarsByName(name);

        if (matchingCars.isEmpty()) {
            // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
            return ResponseEntity.ok(new ArrayList<>());
        } else {
            return ResponseEntity.ok(matchingCars);
        }
    } catch (NoResultException e) {
        // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
        return ResponseEntity.ok(new ArrayList<>());
    } catch (Exception e) {
        // Gérez d'autres exceptions ici, si nécessaire
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
    @GetMapping("/searchCarsByModel")
    public ResponseEntity<List<CarDTO>> searchCarsByModel(@RequestParam String model) {
        try {
            List<CarDTO> matchingCars = carsService.getCarsByModel(model);

            if (matchingCars.isEmpty()) {
                // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(matchingCars);
            }
        } catch (NoResultException e) {
            // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
            return ResponseEntity.ok(new ArrayList<>());
        } catch (Exception e) {
            // Gérez d'autres exceptions ici, si nécessaire
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/searchCarsByAddress")
    public ResponseEntity<List<CarDTO>> searchCarsByAddress(@RequestParam String address) {
        try {
            List<CarDTO> matchingCars = carsService.getCarsByAddress(address);

            if (matchingCars.isEmpty()) {
                // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
                return ResponseEntity.ok(new ArrayList<>());
            } else {
                return ResponseEntity.ok(matchingCars);
            }
        } catch (NoResultException e) {
            // Aucune voiture n'a été trouvée, renvoyez une réponse avec une liste vide
            return ResponseEntity.ok(new ArrayList<>());
        } catch (Exception e) {
            // Gérez d'autres exceptions ici, si nécessaire
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/searchCarsByModelAndAddress")
    public ResponseEntity<List<CarDTO>> searchCarsByModelAndAddress(
            @RequestParam String model,
            @RequestParam String address) {
        try {
            List<CarDTO> matchingCars = carsService.getCarsByModelAndAddress(model, address);
            return ResponseEntity.ok(matchingCars);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/searchCarsName")
    public ResponseEntity<List<CarDTO>> searchCars(@RequestParam String searchTerm) {
        try {
            List<CarDTO> matchingCars = carsService.searchCarsByName(searchTerm);
            return ResponseEntity.ok(matchingCars);
        } catch (Exception e) {
            // Gérez les exceptions ici si nécessaire
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/searchCarsModel")
    public ResponseEntity<List<CarDTO>> searchCarsModel(@RequestParam String searchTerm) {
        try {
            List<CarDTO> matchingCars = carsService.searchCarsByModel(searchTerm);
            return ResponseEntity.ok(matchingCars);
        } catch (Exception e) {
            // Gérez les exceptions ici si nécessaire
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/searchCarsModelAndAddress")
    public ResponseEntity<List<CarDTO>> searchCarsModelAndAddress(@RequestParam String searchTermModel ,@RequestParam String searchTermAddress  ) {
        try {
            List<CarDTO> matchingCars = carsService.searchCarsByModelAndAddress(searchTermModel, searchTermAddress );
            return ResponseEntity.ok(matchingCars);
        } catch (Exception e) {
            // Gérez les exceptions ici si nécessaire
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/searchCarsByModelAndAddressONEParametrs")
    public ResponseEntity<List<CarDTO>> searchCarsByModelAndAddressONEParametrs(@RequestParam String searchTerm) {
        try {
            // Analysez searchTerm pour extraire modèle et adresse
            String model = extractModelFromSearchTerm(searchTerm);
            String address = extractAddressFromSearchTerm(searchTerm);

            // Effectuez la recherche en fonction du modèle et de l'adresse
            List<CarDTO> matchingCars = carsService.searchCarsByModelAndAddress(model, address);
            return ResponseEntity.ok(matchingCars);
        } catch (Exception e) {
            // Gérez les exceptions ici si nécessaire
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    private String extractModelFromSearchTerm(String searchTerm) {
        // Supposons que la chaîne de recherche a un format simple, avec le modèle suivi de l'adresse, séparés par un espace
        String[] parts = searchTerm.split(" ");

        if (parts.length > 0) {
            // Le modèle est la première partie de la chaîne (parts[0])
            return parts[0];
        } else if (parts.length > 1){
            return parts[1];
        }
        else {
            // Si la chaîne de recherche est vide ou ne contient pas d'espace, retournez une valeur par défaut ou gérez l'erreur
            return "Modèle non spécifié";
        }
    }

    private String extractAddressFromSearchTerm(String searchTerm) {
        // Supposons que la chaîne de recherche a un format simple, avec le modèle suivi de l'adresse, séparés par un espace
        String[] parts = searchTerm.split(" ");

        if (parts.length > 1) {
            // L'adresse est la deuxième partie de la chaîne (parts[1])
            return parts[1];
        } else if (parts.length > 0){
            return parts[0];
        }
        else {
            // Si la chaîne de recherche ne contient pas d'espace ou ne spécifie pas d'adresse, retournez une valeur par défaut ou gérez l'erreur
            return "Adresse non spécifiée";
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<CarDTO>> searchCarsBySingleParameter(@RequestParam String searchTerm) {
        try {
            List<CarDTO> matchingCars = carsService.searchCarsBySingleParameter(searchTerm);
            return ResponseEntity.ok(matchingCars);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
