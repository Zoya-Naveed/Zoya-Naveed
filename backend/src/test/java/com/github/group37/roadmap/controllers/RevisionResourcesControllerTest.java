package com.github.group37.roadmap.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.group37.roadmap.other.enums.LevelOfExpertise;
import com.github.group37.roadmap.percistance.models.RevisionResourceDao;
import com.github.group37.roadmap.service.RevisionResourcesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RevisionResourcesController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class RevisionResourcesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevisionResourcesService revisionResourcesService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID validUuid = UUID.randomUUID();

    private List<RevisionResourceDao> revisionResourceDaos = List.of(revisionResourceDao());

    private RevisionResourceDao revisionResourceDao() {
        RevisionResourceDao revisionResourceDao = new RevisionResourceDao();
        revisionResourceDao.setId(validUuid);
        revisionResourceDao.setResourceName("TESTNAME");
        revisionResourceDao.setDescription("TESTDESCRIPTION");
        revisionResourceDao.setTopic(validUuid);
        revisionResourceDao.setLevelOfExpertise(LevelOfExpertise.NOVICE);
        return revisionResourceDao;
    }

    @DisplayName("GET request, invalid uuid, returns 404")
    @Test
    void when_given_invalid_uuid_return_404_error() throws Exception {
        String invalidTopicId = "invalid-uuid";

        mockMvc.perform(get("/revision-recources/" + invalidTopicId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("GET request, valid uuid, returns list")
    @Test
    void when_given_valid_uuid_return_list() throws Exception {
        when(revisionResourcesService.getRevisionResourceUsingTopicId(validUuid))
                .thenReturn(revisionResourceDaos);

        mockMvc.perform(get("/revision-recources/" + validUuid).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(
                        jsonPath("$[0].id").value(revisionResourceDao().getId().toString()))
                .andExpect(jsonPath("$[0].resourceName")
                        .value(revisionResourceDao().getResourceName()));
    }
}