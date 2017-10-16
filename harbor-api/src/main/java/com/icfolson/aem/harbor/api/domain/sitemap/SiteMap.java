package com.icfolson.aem.harbor.api.domain.sitemap;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public interface SiteMap {

    List<SiteMapEntry> getSiteMapEntries();

    void marshall(OutputStream stream) throws JAXBException, IOException;

}
