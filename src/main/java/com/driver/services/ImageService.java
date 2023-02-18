package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();
        List<Image> blogImages = blog.getImageList();
        blogImages.add(image);
        image.setBlog(blog);

        blogRepository2.save(blog); //here we are saving in blog repo and due to cascading effect
                                    // it automatically save the image(child)
        return image;

    }

    public void deleteImage(Integer id){

        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String imageDimensions = image.getDimensions();

        String[] screen_Dim = screenDimensions.split("X");
        String[] image_Dim = imageDimensions.split("X");

        int screen_d1 = Integer.parseInt(screen_Dim[0]);
        int screen_d2 = Integer.parseInt(screen_Dim[1]);
        int image_d1 = Integer.parseInt(image_Dim[0]);
        int image_d2 = Integer.parseInt(image_Dim[1]);

        return (screen_d1 * screen_d2)/(image_d1 * image_d2);
    }
}
