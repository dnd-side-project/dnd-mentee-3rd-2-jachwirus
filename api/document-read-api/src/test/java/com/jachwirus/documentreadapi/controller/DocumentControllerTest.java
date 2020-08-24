package com.jachwirus.documentreadapi.controller;

import com.jachwirus.documentreadapi.model.Document;
import com.jachwirus.documentreadapi.model.DocumentHashTag;
import com.jachwirus.documentreadapi.model.DocumentVersion;
import com.jachwirus.documentreadapi.model.HashTag;
import com.jachwirus.documentreadapi.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
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

    @MockBean
    private DocumentRepository documentRepository;

    @BeforeEach()
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    private List<Document> getMockDocumentModel() {
        DocumentVersion version = new DocumentVersion()
                .setId(1L)
                .setDataUrl("https://tmpData")
                .setThumbnailUrl("https://tmpThumbnail");
        DocumentHashTag hashTag = new DocumentHashTag()
                .setId(1L)
                .setHashTag(new HashTag().setId(1L).setTagName("tmpTag"));
        Document doc = new Document()
                .setId(1L)
                .setTitle("First test instance")
                .setLikes(5)
                .setDislikes(1)
                .setViewCount(12)
                .setCategory("test")
                .setVersions(new ArrayList<>(List.of(version)))
                .setComments(new ArrayList<>())
                .setTags(new ArrayList<>(List.of(hashTag)));

        List<Document> list = new ArrayList<>(List.of(doc));
        return list;
    }

    @Test
    void findList() {
        log.info("######## START : CONTROLLER TEST FIND ALL LIST ########");
        List<Document> documentList = getMockDocumentModel();

        given(documentRepository.findAll()).willReturn(documentList);

        ResponseEntity<CollectionModel> response = restTemplate.getForEntity("/documents", CollectionModel.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        log.info("######## END : CONTROLLER TEST FIND ALL LIST ########");
    }

    @Test
    void findOne() {

    }

    @Test
    void getHotDocument() {
        log.info("######## START : CONTROLLER TEST FIND HOT CHART ########");

        ResponseEntity<CollectionModel> response = restTemplate.getForEntity("/documents/hot-chart", CollectionModel.class);
        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
        log.info("######## END : CONTROLLER TEST FIND HOT CHART ########");
    }
}
