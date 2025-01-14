package com.wikimedia;

import com.wikimedia.entity.WikimediaData;
import com.wikimedia.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);




    private final WikimediaDataRepository wikimediaDataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository wikimediaDataRepository) {
        this.wikimediaDataRepository = wikimediaDataRepository;
    }
@KafkaListener(topics = "wikimedia_recentchange",
        groupId = "myGroup")
public void consume(String eventMessage){
    LOGGER.info(String.format("Event Message received -> %s", eventMessage));
    WikimediaData wikimediaData = new WikimediaData();
    wikimediaData.setWikiEventData(eventMessage);

    wikimediaDataRepository.save(wikimediaData);
}
}
