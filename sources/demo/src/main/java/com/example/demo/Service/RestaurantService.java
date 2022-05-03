package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Model.DTOs.PublicRestaurantDTO;
import com.example.demo.Model.DTOs.RestaurantDTO;
import com.example.demo.Repository.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AdminRepository adminRepository;
    private final BasketRepository basketRepository;
    private final NeighbourhoodRepository neighbourhoodRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    RestaurantService(CategoryRepository categoryRepository,NeighbourhoodRepository neighbourhoodRepository,BasketRepository basketRepository,AdminRepository adminRepository,RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
        this.basketRepository = basketRepository;
        this.neighbourhoodRepository = neighbourhoodRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<PublicRestaurantDTO> getAllRestaurants() {
        return restaurantRepository.getAll()
                .stream()
                .map(x -> new PublicRestaurantDTO.Builder(x).build())
                .collect(Collectors.toList());
    }

    public Restaurant registerRestaurant(RestaurantDTO restaurantDTO) throws ResourceNotFoundException{

        try {
        if(restaurantDTO.name.matches("")) throw new ResourceNotFoundException("No name");
        if(restaurantDTO.location.matches("")) throw new ResourceNotFoundException("no location");
        Admin admin = adminRepository.findById(restaurantDTO.admin_id).orElse(null);
        Restaurant ExistsRestaurant = restaurantRepository.findRestaurantByAdmin(admin);
        if(admin == null) throw new ResourceNotFoundException("Cannot find admin");
        if(ExistsRestaurant != null) throw new ResourceNotFoundException("Restaurant already exists");
        Restaurant restaurant = RestaurantDTO.toRestaurant(restaurantDTO,admin);
            return restaurantRepository.saveAndFlush(restaurant);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Basket registerBasket(int restaurant_id) throws ResourceNotFoundException{
        Restaurant restaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for id " + restaurant_id));
        Basket basket = new Basket();
        basket.setRestaurant(restaurant);

        return basketRepository.save(basket);
    }

    public List<RestaurantDTO> getAllRestaurantsByName(String name) {
        return restaurantRepository.getAllByName(name)
                .stream()
                .map(x -> new RestaurantDTO.Builder(x).build())
                .collect(Collectors.toList());
    }

    public List<Neighbourhood> getAllNeighbourhoods() {
        return neighbourhoodRepository.findAll();
    }

    public HttpStatus addNeighbourhoods(Admin myAdmin, Set<Neighbourhood> neighbourhoodSet) {
        try{
            Restaurant restaurant = restaurantRepository.findRestaurantByAdmin(myAdmin);
            restaurant.setNeighbourhoodList(neighbourhoodSet);
            restaurantRepository.save(restaurant);
            return HttpStatus.ACCEPTED;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }
    }

    public List<String> getAllCategories() {
        try{
            return categoryRepository.findAll().stream().map(x-> x.getCategory()).collect(Collectors.toList());
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public HttpStatus exportMenuAsPDF(Admin admin) {
        String filename = "C:\\Users\\Tudi\\Desktop\\AN 3 SEM 2\\Software Design\\WORKSPACE\\SD_FOOD_DELIVERY\\sources\\demo\\pdf\\myfile.pdf";
        Document document = new Document();
        try{
            PdfWriter.getInstance(document,new FileOutputStream(filename));
            document.open();

            Set<Meal> meals = restaurantRepository.findRestaurantByAdmin(admin).getMeals();
            for(Meal meal : meals){
                Paragraph paragraph = new Paragraph(meal.toString());
                document.add(paragraph);
            }
            document.close();
            return HttpStatus.ACCEPTED;
        }catch (Exception ex){
            System.err.print(ex);
        }
        return HttpStatus.BAD_REQUEST;
    }
}
