package com.almoxarifado.erp.util.imagens;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 17; i++) {
            images.add("natal" + i + ".JPG");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}
