package com.jachwirus.documentreadapi.dto;

import com.jachwirus.documentreadapi.dto.model.DocumentInfoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentInfoDtoTest {
    @Test
    public void getId() {
        final DocumentInfoDto documentInfoDto = new DocumentInfoDto().setId(1L);
        final long id = documentInfoDto.getId();
        final long expect = 1L;
        assertEquals(expect, id);
    }
}
