package com.trendsmixed.fma.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.Folder;
import com.trendsmixed.fma.jsonView.Views;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.FolderService;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@CrossOrigin
@RequestMapping("/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private AppSessionService appSessionService;

    @PostMapping
    public Folder save(@RequestBody Folder folder, @RequestHeader(value = "email", defaultValue = "") String email) {
        AppSession appSession = appSessionService.findOne(email);
        if (appSession == null) {
            throw new Error("Unauthorized access");
        } else {
            try {
                folder = folderService.save(folder);
                return folder;

            } catch (Throwable e) {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new Error(e.getMessage());
            }
        }
    }

    @GetMapping//("/top")
    public @JsonView(Views.FolderOnly.class)
    List<Folder> all() {
        return folderService.findAll();
    }

    @GetMapping("/top")
    public @JsonView(Views.FolderOnly.class)
    List<Folder> top(@RequestHeader(value = "email", defaultValue = "") String email) {
        //System.out.println(email);
        return folderService.findByFolderIsNull();
    }

    @GetMapping("/{id}")
    public @JsonView(Views.FolderOnly.class)
    Folder one(@PathVariable("id") int id) {
        return folderService.findOne(id);
    }

    @GetMapping("/{id}/with-parent")
    public @JsonView(Views.FolderWithParent.class)
    Folder oneWithParent(@PathVariable("id") int id) {
        return folderService.findOne(id);
    }

    @GetMapping("/{id}/with-sub-folders")
    public @JsonView(Views.FolderWithSubFolders.class)
    Folder oneWithSubFolders(@PathVariable("id") int id) {
        return folderService.findOne(id);
    }

    @GetMapping("/{id}/with-files")
    public @JsonView(Views.FolderWithFiles.class)
    Folder oneWithFiles(@PathVariable("id") int id) {
        return folderService.findOne(id);
    }

    @GetMapping("/{id}/with-sub-folders-and-files")
    public @JsonView(Views.FolderWithSubFoldersAndFiles.class)
    Folder oneWithSubFoldersAndFiles(@PathVariable("id") int id) {
        return folderService.findOne(id);
    }

    /*
    @GetMapping("/{id}/with-parent-and-sub-folders-and-files")
    public @JsonView(Views.FolderParentAndWithSubFoldersAndFiles.class)
    Folder oneWithParentAndSubFoldersAndFiles(@PathVariable("id") int id) {
        return folderService.findOne(id);
    }
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        folderService.delete(id);
    }

}