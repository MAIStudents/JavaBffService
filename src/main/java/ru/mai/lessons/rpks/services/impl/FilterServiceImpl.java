package ru.mai.lessons.rpks.services.impl;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mai.lessons.rpks.dto.request.FilterRequest;
import ru.mai.lessons.rpks.dto.response.FilterResponse;
import ru.mai.lessons.rpks.services.FilterService;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

  //TODO use feign client...

  @Override
  public Iterable<FilterResponse> getAllFilters() {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  public Iterable<FilterResponse> getAllFiltersByFilterId(long id) {
    //TODO code here...
    return Collections.emptyList();
  }

  @Override
  public FilterResponse getFilterByFilterIdAndRuleId(long filterId, long ruleId) {
    //TODO code here...
    return new FilterResponse();
  }

  @Override
  public void deleteFilter() {
    //TODO code here...
  }

  @Override
  public void deleteFilterById(long filterId, long ruleId) {
    //TODO code here...
  }

  @Override
  public void save(FilterRequest filter) {
    //TODO code here...
  }
}
