package ru.mai.lessons.rpks.clients;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Клиент для контролера дедубликации
 */
@FeignClient(
    name = "deduplicationClient",
    url = "${feign.client.url.deduplication}"
)
public interface DeduplicationClient {
  //TODO code here...
}
