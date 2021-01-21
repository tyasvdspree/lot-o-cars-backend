package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValueDto {
    private String key;
    private String value;

    public KeyValueDto(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValueDto(String key, Long value) {
        this.key = key;
        this.value = value.toString();
    }
}
