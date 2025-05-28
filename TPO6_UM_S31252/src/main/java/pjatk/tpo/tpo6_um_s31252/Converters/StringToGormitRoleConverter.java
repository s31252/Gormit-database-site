package pjatk.tpo.tpo6_um_s31252.Converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pjatk.tpo.tpo6_um_s31252.Models.GormitRole;

@Component
public class StringToGormitRoleConverter implements Converter<String, GormitRole> {

    @Override
    public GormitRole convert(String source) {
        try {
            return GormitRole.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
