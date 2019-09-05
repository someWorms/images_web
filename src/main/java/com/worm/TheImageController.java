package com.worm;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
                             @RequestParam("uploadCommentary") String theCommentary) {


        /*Validation image name*/
        for(TheImage th : imgContainer){
            if(theFile.getOriginalFilename().equals(th.getFileName())){
                return "fail";
            }
        }

        /*You cant get here*/
        if(theFile == null | theFile.getOriginalFilename() == null | theFile.getOriginalFilename() == " "){
            return "fail";
        }

        /*Validation if is image*/
        try {
            InputStream inputStream = theFile.getInputStream();
            if(ImageIO.read(inputStream) == null)
                return "fail";
        } catch (IOException e) {
            System.out.println("error during file validation");
            e.printStackTrace();
        }

        /*Creating a new object to store a link to image and list of comments*/
        TheImage theImage = new TheImage();

        try {
            theImage.saveFile(theFile, theCommentary);
            imgContainer.add(theImage);
        }catch (IOException e){
            e.printStackTrace();
        }



        //primitive logger for console
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

    /*Show data about images, data received from GET req*/
    @GetMapping("full-info")
    public ModelAndView showPic(Map<String, Object> model,
                                @RequestParam(name = "nameImage") String nameImage,
                                @RequestParam(name = "commentImage") List<String> commentImage){

        model.put("nameImage", nameImage);
        model.put("commentImage", commentImage);

        return new ModelAndView("showImage", model);
    }

    /*Add Comment Functional (TODO: release)*/
    @PostMapping("addComment")
    public ModelAndView addCommentary(Map <String, Object> model,
                                    @RequestParam(name = "addCommentary") String commentary,
                                    @RequestParam(name = "nameImage") String name){
        for(TheImage th : imgContainer){
            if(name.equals(th.getFileName())){
                //System.out.println("Correct! I am in the image!");
                th.setCommentary(commentary);
                model.put("nameImage", name);
                model.put("commentImage", th.getCommentary());
            }
        }

        return new ModelAndView("showImage", model);
    }
}
