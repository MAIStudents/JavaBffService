package ru.mai.lessons.rpks.clients;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Клиент для контролера фильтрации
 */
@FeignClient(
    name = "filterClient",
    url = "${feign.client.url.filter}"
)
public interface FilterClient {
  //TODO code here...
}
