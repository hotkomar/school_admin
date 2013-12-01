/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.hotkomar.service.file;

import cz.cvut.hotkomar.model.entity.FileEntity;
import cz.cvut.hotkomar.model.entity.Student;
import cz.cvut.hotkomar.service.manager.FileMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Maru
 */
@Service
public class Download {

    private FileMan fileMan;

    @Autowired
    public void setFileMan(FileMan fileMan) {
       
        this.fileMan = fileMan;
    }

    public FileEntity saveFotoStudent(CommonsMultipartFile formFile) {
        if (!formFile.isEmpty()) {
            FileEntity file = new FileEntity();
            file.setName(formFile.getOriginalFilename());
            file.setVisible(Boolean.TRUE);
            file.setFileSize(formFile.getSize());
            file.setContentType(formFile.getContentType());
            file.setStream(formFile.getBytes());
            fileMan.add(file, false);
            return file;
        }
        return null;
    }
}
