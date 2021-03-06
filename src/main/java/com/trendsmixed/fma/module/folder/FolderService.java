package com.trendsmixed.fma.module.folder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FolderService {

    private FolderRepository folderRepository;

    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    public Folder findOne(int id) {
        return folderRepository.findOne(id);
    }

    public void delete(int id) {
        folderRepository.delete(id);
    }

    public List<Folder> findByFolderIsNull() {
        return folderRepository.findByFolderIsNull();
    }
}
