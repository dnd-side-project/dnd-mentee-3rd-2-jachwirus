package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.repository.*;
import com.jachwirus.documentreadapi.util.MockDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.CollectionModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Slf4j
public class DocumentControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    private TestRestTemplate restTemplate;
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

    @BeforeEach()
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();

        mockDataHandler = new MockDataHandler(
                documentRepository,
                documentVersionRepository,
                documentHashTagRepository,
                hashTagRepository,
                commentRepository
        );
    }

    @Test
    void findList() {
        log.info("######## START : CONTROLLER TEST FIND ALL LIST ########");
        ResponseEntity<CollectionModel> response = restTemplate.getForEntity("/documents", CollectionModel.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        log.info("######## END : CONTROLLER TEST FIND ALL LIST ########");
    }

    @Test
    void findOne() {
        log.info("######## START : CONTROLLER TEST FIND ONE ########");
        ResponseEntity<CollectionModel> response = restTemplate.getForEntity("/documents/1", CollectionModel.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        log.info("######## END : CONTROLLER TEST FIND ONE ########");
    }
}
