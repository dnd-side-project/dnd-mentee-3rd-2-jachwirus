package com.jachwirus.documentreadapi.service;

import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.repository.*;
import com.jachwirus.documentreadapi.util.DefaultValue;
import com.jachwirus.documentreadapi.util.MockDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Slf4j
class DocumentFindServiceTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentVersionRepository documentVersionRepository;
    @Autowired
    private DocumentHashTagRepository documentHashTagRepository;
    @Autowired
    private HashTagRepository hashTagRepository;
    @Autowired
    private CommentRepository commentRepository;

    private MockDataHandler mockDataHandler;
    @Autowired
    DocumentFindService service;

    @BeforeEach()
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
        this.mockDataHandler = new MockDataHandler(
                documentRepository,
                documentVersionRepository,
                documentHashTagRepository,
                hashTagRepository,
                commentRepository
        );
    }

    @Test
    void findDocumentsList() {
        List<Document> result, expect;
        List<Long> resultIds, expectIds;
        String sortTarget;

        log.info("######## START : SERVICE TEST FIND ALL LIST SORT BY LATEST ########");
        sortTarget = DefaultValue.sortTarget;
        result = service.findDocumentsList(DefaultValue.category, sortTarget, DefaultValue.page);
        expect = this.mockDataHandler.getMockDataSortBy(sortTarget);

        resultIds = this.extractIds(result);
        expectIds = this.extractIds(expect);

        assertEquals(expectIds, resultIds);
        log.info("######## END : SERVICE TEST FIND ALL LIST SORT BY LATEST ########");



        log.info("######## START : SERVICE TEST FIND ALL LIST SORT BY POPULARITY ########");
        sortTarget = "popularity";
        result = service.findDocumentsList(DefaultValue.category, sortTarget, DefaultValue.page);
        expect = this.mockDataHandler.getMockDataSortBy(sortTarget);

        resultIds = this.extractIds(result);
        expectIds = this.extractIds(expect);

        assertEquals(expectIds, resultIds);
        log.info("######## END : SERVICE TEST FIND ALL LIST SORT BY POPULARITY ########");
    }

    private List<Long> extractIds(List<Document> documentList) {
        List<Long> list = documentList.stream()
                .map(doc -> doc.getId())
                .collect(Collectors.toList());
        return list;
    }


    @Test
    void findDocumentById() {
        log.info("######## START : SERVICE TEST FIND ONE BY ID ########");

        long id = 1L;
        Document result = service.findDocumentById(id);
        Document expect = this.mockDataHandler.getMockData(id);

        assertEquals(expect.getId(), result.getId());
        log.info("######## END : SERVICE TEST FIND ONE BY ID ########");
    }
}