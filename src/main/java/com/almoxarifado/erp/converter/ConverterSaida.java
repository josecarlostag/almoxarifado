package com.almoxarifado.erp.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.almoxarifado.erp.model.Saida;

@FacesConverter("converterSaida")
public class ConverterSaida implements Converter,Serializable {

private static final long serialVersionUID = 1L;


public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
    if (value != null && !value.isEmpty()) {
        return (Saida) uiComponent.getAttributes().get(value);
    }
    return null;
}

public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
    if (value instanceof Saida) {
    	Saida entity= (Saida) value;
        if (entity != null && entity instanceof Saida && entity.getId() != null) {
            uiComponent.getAttributes().put( entity.getId().toString(), entity);
            return entity.getId().toString();
        }
    }
    return "";
    }
}