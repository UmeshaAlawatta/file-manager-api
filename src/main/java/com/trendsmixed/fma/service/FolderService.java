package com.trendsmixed.fma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trendsmixed.fma.entity.Folder;
import com.trendsmixed.fma.repository.FolderRepository;

@Service
public class FolderService {

    @Autowired
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
