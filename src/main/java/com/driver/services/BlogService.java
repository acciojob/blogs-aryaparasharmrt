package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        Blog blog = new Blog();


        User user = userRepository1.findById(userId).get();

       if(user != null) {
           blog.setContent(content);
           blog.setTitle(title);
           blog.setUser(user);
           List<Blog> userBlogs = user.getBlogList();
           userBlogs.add(blog);
           user.setBlogList(userBlogs);

           userRepository1.save(user); //here we are saving in user repo and due to cascading effect
       }                            // it automatically save the blog(child)
        return blog;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);

    }
}
