package com.almoxarifado.erp.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.almoxarifado.erp.model.Usuario;

@FacesConverter("converterUsuario")
public class ConverterUsuario implements Converter,Serializable {

private static final long serialVersionUID = 1L;


public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
    if (value != null && !value.isEmpty()) {
        return (Usuario) uiComponent.getAttributes().get(value);
    }
    return null;
}

public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
    if (value instanceof Usuario) {
    	Usuario entity= (Usuario) value;
        if (entity != null && entity instanceof Usuario && entity.getId() != null) {
            uiComponent.getAttributes().put( entity.getId().toString(), entity);
            return entity.getId().toString();
        }
    }
    return "";
    }
}