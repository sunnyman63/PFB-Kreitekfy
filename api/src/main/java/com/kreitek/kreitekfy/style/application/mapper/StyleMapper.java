package com.kreitek.kreitekfy.style.application.mapper;

import com.kreitek.kreitekfy.style.application.dto.StyleDTO;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StyleMapper extends EntityMapper<StyleDTO, Style> {
    default Style fromId(Long id){
        if(id == null) return null;

        Style style = new Style();
        style.setId(id);
        return style;
    }
}
