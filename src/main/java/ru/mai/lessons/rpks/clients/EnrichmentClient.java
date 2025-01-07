package ru.mai.lessons.rpks.clients;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Клиент для контролера обогащения
 */
@FeignClient(
    name = "enrichmentClient",
    url = "${feign.client.url.enrichment}"
)
public interface EnrichmentClient {
  //TODO code here...
}
