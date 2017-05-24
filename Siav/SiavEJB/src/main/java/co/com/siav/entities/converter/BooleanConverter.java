package co.com.siav.entities.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class BooleanConverter implements AttributeConverter<Boolean, String>{
    
	@Override
    public String convertToDatabaseColumn(Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            return "S";
        } else {
            return "N";
        }
    }
    
	@Override
    public Boolean convertToEntityAttribute(String value) {
        return "S".equals(value);
    }
}
