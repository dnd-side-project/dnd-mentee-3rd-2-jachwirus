package com.jachwirus.documentreadapi.dto;

import com.jachwirus.documentreadapi.dto.model.DocumentDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentDtoTest {
    @Test
    public void getId() {
        final DocumentDto documentDto = new DocumentDto().setId(1L);
        final long id = documentDto.getId();
        final long expect = 1L;
        assertEquals(expect, id);
    }
}
