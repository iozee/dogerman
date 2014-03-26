package ru.doberman.managedbean;

import ru.doberman.service.DogService;

import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author izerenev
 *         Date: 18.03.2014
 */
@FacesConverter(value = "breedConverter")
public class BreedConverter implements Converter {
  private DogService service;

  @Resource
  public void setService(DogService service) {
    this.service = service;
  }

  public DogService getService() {
    return service;
  }

  public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
    if (submittedValue == null || submittedValue.equals("")) {
      return null;
    }
    DogMB dogMB = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dogMB}", DogMB.class);
    return dogMB.getBreedByName(submittedValue);
  }

  public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
    if (value == null || value.equals("")) {
      return "";
    } else {
      return value.toString();
    }
  }
}
