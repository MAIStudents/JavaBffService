package ru.mai.lessons.rpks.controllers.impl;

import jakarta.validation.Valid;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mai.lessons.rpks.controllers.EnrichmentController;
import ru.mai.lessons.rpks.dto.request.EnrichmentRequest;
import ru.mai.lessons.rpks.dto.response.EnrichmentResponse;

@Validated
@RestController
@RequestMapping("/enrichment")
public class EnrichmentControllerImpl implements EnrichmentController {

  @Override
  @GetMapping("/findAll")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<EnrichmentResponse> getAllEnrichmentRequests() {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  @GetMapping("/findAll/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public Iterable<EnrichmentResponse> getAllEnrichmentRequestsByEnrichmentRequestId(
      @PathVariable("id") long id) {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  @GetMapping("/find/{enrichmentId}/{ruleId}")
  @ResponseStatus(value = HttpStatus.OK)
  public EnrichmentResponse getEnrichmentRequestById(
      @PathVariable("enrichmentId") long enrichmentId,
      @PathVariable("ruleId") long ruleId) {
    //TODO code here...
    return new EnrichmentResponse();
  }

  @Override
  @DeleteMapping("/delete")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteEnrichmentRequest() {
    //TODO code here...
  }

  @Override
  @DeleteMapping("/delete/{enrichmentId}/{ruleId}")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteEnrichmentRequestById(
      @PathVariable("enrichmentId") long enrichmentId,
      @PathVariable("ruleId") long ruleId) {
    //TODO code here...
  }

  @Override
  @PostMapping("/save")
  @ResponseStatus(value = HttpStatus.CREATED)
  public void save(@RequestBody @Valid EnrichmentRequest enrichment) {
    //TODO code here...
  }
}
