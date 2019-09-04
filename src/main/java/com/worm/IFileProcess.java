package com.worm;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileProcess {
    public void saveFile(MultipartFile theFile, String commentary) throws IOException;
}
