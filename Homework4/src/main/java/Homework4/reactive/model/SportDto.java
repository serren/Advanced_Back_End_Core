package Homework4.reactive.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Data
public class SportDto {

    private String id;
    private String type;
    private Map<String, Object> attributes;

    public Sport toEntity() {
        Sport sport = new Sport();
        sport.setExternalId(Integer.valueOf(id));
        sport.setName(StringUtils.defaultString(
                attributes != null ? (String) attributes.get("name") : null, "NoName"));
        return sport;
    }
}