package com.icfolson.aem.harbor.core.services.rss

import com.icfolson.aem.harbor.api.content.rss.RSSFeed
import com.icfolson.aem.harbor.api.services.rss.RSSFeedGeneratorService
import com.icfolson.aem.harbor.core.content.rss.impl.DefaultRSSFeed
import com.google.common.base.Optional
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.xml.bind.JAXBContext
import javax.xml.bind.Unmarshaller

@Service
@Component(label = "RSS Feed Generator Service", immediate = true)
public class DefaultRSSFeedGeneratorService implements RSSFeedGeneratorService {

	Logger LOG = LoggerFactory.getLogger(DefaultRSSFeedGeneratorService.class)

    private JAXBContext jaxbContext

    public DefaultRSSFeedGeneratorService() {
        jaxbContext = JAXBContext.newInstance(DefaultRSSFeed)
    }

    @Override
    Optional<RSSFeed> getRSSFeed(String feedUrl) {
        //TODO: Exception handling surrounding things like invalid URL - unable to dereference - etc
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller()
        Object unmarshalledRssChannelObject = unmarshaller.unmarshal(feedUrl.toURL())

        if (unmarshalledRssChannelObject) {
            return Optional.of((RSSFeed) unmarshalledRssChannelObject)
        }

        return Optional.absent()
    }
}
