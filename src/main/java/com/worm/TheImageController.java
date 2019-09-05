package com.worm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class TheImageController {
    /*Creating a list to store images*/
    public List<TheImage> imgContainer = new ArrayList<>();


    /*Show upload form*/
    @RequestMapping("/showForm")
    public String showForm(){
        return "upload-page";
    }

    /*Upload image process*/
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("myFile") MultipartFile theFile,
                             @RequestParam("uploadCommentary") @Size(min=4, message = "should be 1+") String theCommentary) {

        /*Creating a new object to store a link to image and list of comments*/
        TheImage theImage = new TheImage();

        try {
            theImage.saveFile(theFile, theCommentary);
            imgContainer.add(theImage);
        }catch (IOException e){
            e.printStackTrace();
        }



        //primitive logger for console :D
        for (TheImage th : imgContainer){
            System.out.println(th.getCommentary() + " " + th.getImageLink());
            System.out.println("-----------------");
        }
        return "success";
    }

    /*Album page with all images*/
    @GetMapping("/album")
    public ModelAndView  toAlbum(){
        //create a model to pass it to JSP page.
        ModelAndView model = new ModelAndView();
        //model named "imgList" contains imgContainer list.
        model.addObject("imgList", imgContainer);
        return model;
    }

    /*Show data about images, data received from GET*/
    @GetMapping("album/full-info")
    public ModelAndView showPic(Map<String, Object> model,
                                @RequestParam(name = "nameImage") String nameImage,
                                @RequestParam(name = "commentImage") List<String> commentImage){

        model.put("nameImage", nameImage);
        model.put("commentImage", commentImage);

        return new ModelAndView("showImage", model);
    }

    /*Add Comment Functional (TODO: release)*/
    @GetMapping("album/addComment")
    public ModelAndView addCommentary(Map <String, Object> model,
                                    @RequestParam(name = "addCommentary") String commentary,
                                    @RequestParam(name = "nameImage") String name){
        for(TheImage th : imgContainer){
            if(name.equals(th.getFileName())){
                //System.out.println("Correct! I am in the image!");
                th.setCommentary(commentary);
            }
        }

       return new ModelAndView("album");
    }

}
