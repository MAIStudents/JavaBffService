package ru.mai.lessons.rpks.services.impl;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.dto.request.EnrichmentRequest;
import ru.mai.lessons.rpks.dto.response.EnrichmentResponse;
import ru.mai.lessons.rpks.services.EnrichmentService;

@Service
@RequiredArgsConstructor
public class EnrichmentServiceImpl implements EnrichmentService {

  //TODO use feign client...

  @Override
  public Iterable<EnrichmentResponse> getAllEnrichmentRequests() {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  public Iterable<EnrichmentResponse> getAllEnrichmentRequestsByEnrichmentRequestId(long id) {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  public EnrichmentResponse getEnrichmentRequestById(long enrichmentId, long ruleId) {
    //TODO code here...
    return new EnrichmentResponse();
  }

  @Override
  public void deleteEnrichmentRequest() {
    //TODO code here...
  }

  @Override
  public void deleteEnrichmentRequestById(long enrichmentId, long ruleId) {
    //TODO code here...
  }

  @Override
  public void save(EnrichmentRequest enrichment) {
    //TODO code here...
  }
}
