package com.example.backend.web.mapper.place;

import com.example.backend.model.place.Country;
import com.example.backend.web.dto.place.CountryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    public CountryDTO toDto(Country model) {
        CountryDTO dto = new CountryDTO();
        dto.setId(model.getId());
        dto.setDeleted(model.getDeleted());
        dto.setName(model.getName());
        return dto;
    }

    public List<CountryDTO> getListDtoFromListModel(List<Country> models) {
        return models.stream().map(this::toDto).collect(Collectors.toList());
    }
}
