package com.trendsmixed.fma.module.file;

import com.trendsmixed.fma.entity.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trendsmixed.fma.module.file.FileRepository;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public List<File> findAll() {
        return fileRepository.findAll();
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public File findOne(int id) {
        return fileRepository.findOne(id);
    }

    public void delete(int id) {
        fileRepository.delete(id);
    }
}