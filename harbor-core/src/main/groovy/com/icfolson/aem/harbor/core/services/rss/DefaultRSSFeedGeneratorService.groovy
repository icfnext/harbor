package com.icfolson.aem.harbor.core.services.rss

import com.google.common.base.Optional
import com.icfolson.aem.harbor.api.content.rss.RSSFeed
import com.icfolson.aem.harbor.api.services.rss.RSSFeedGeneratorService
import com.icfolson.aem.harbor.core.content.rss.impl.DefaultRSSFeed
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service

import javax.xml.bind.JAXBContext

@Service
@Component(label = "RSS Feed Generator Service", immediate = true)
@Slf4j("LOG")
class DefaultRSSFeedGeneratorService implements RSSFeedGeneratorService {

    private final JAXBContext jaxbContext = JAXBContext.newInstance(DefaultRSSFeed)

    @Override
    Optional<RSSFeed> getRSSFeed(String feedUrl) {
        //TODO: Exception handling surrounding things like invalid URL - unable to dereference - etc
        def unmarshaller = jaxbContext.createUnmarshaller()
        def unmarshalledRssChannelObject = unmarshaller.unmarshal(feedUrl.toURL())

        Optional.fromNullable((RSSFeed) unmarshalledRssChannelObject)
    }
}
