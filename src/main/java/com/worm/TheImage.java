package com.worm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TheImage implements IFileProcess{
    @Getter @Setter private Path imageLink;

    @Getter private List <String> commentary = new ArrayList<>();

    @Getter private String fileName;

    private static String folder = "D:\\Development\\Java\\webproject\\src\\main\\webapp\\resources\\images\\";

    public void setCommentary(String commentary) {
        this.commentary.add(commentary);
    }


    public void validate(MultipartFile mp){



    }


    @Override
    public void saveFile(MultipartFile theFile, String commentary) throws IOException {

        byte[] bytes = theFile.getBytes();
        Path path = Paths.get(folder + theFile.getOriginalFilename());
        Files.write(path, bytes);

        /*For debugging. Actually this variable is not used in the project.*/
        imageLink = path;

        this.commentary.add(commentary);

        /*this.commentary = commentary;*/
        this.fileName = theFile.getOriginalFilename();

    }
}
