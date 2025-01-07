package ru.mai.lessons.rpks.services.impl;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.dto.request.DeduplicationRequest;
import ru.mai.lessons.rpks.dto.response.DeduplicationResponse;
import ru.mai.lessons.rpks.services.DeduplicationService;

@Service
@RequiredArgsConstructor
public class DeduplicationServiceImpl implements DeduplicationService {

  //TODO use feign client...

  @Override
  public Iterable<DeduplicationResponse> getAllDeduplications() {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  public Iterable<DeduplicationResponse> getAllDeduplicationsByDeduplicationId(long id) {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  public DeduplicationResponse getDeduplicationById(long deduplicationId, long ruleId) {
    //TODO code here...
    return new DeduplicationResponse();
  }

  @Override
  public void deleteDeduplication() {
    //TODO code here...
  }

  @Override
  public void deleteDeduplicationById(long deduplicationId, long ruleId) {
    //TODO code here...
  }

  @Override
  public void save(DeduplicationRequest deduplication) {
    //TODO code here...
  }
}
