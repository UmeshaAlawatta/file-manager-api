package com.trendsmixed.fma.module.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FileService {

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
