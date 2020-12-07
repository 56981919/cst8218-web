/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.base0001.presentation;

import java.awt.Color;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Todd Kelley
 */
@FacesConverter("spriteColorConverter")
public class SpriteColorConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //Color value is expected to be of the format #RRGGBB, where RR is the hex red value
        return new Color(Integer.parseInt(value.substring(1,3),16),  //red
                         Integer.parseInt(value.substring(3,5),16),  //green
                         Integer.parseInt(value.substring(5,7),16)); //blue
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return String.format("#%02x%02x%02x",
                       ((Color)arg2).getRed(),
                       ((Color)arg2).getGreen(),
                       ((Color)arg2).getBlue());
    }
    
}
