package com.citytechinc.cq.harbor.proper.core.components.content.tests.rss;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang.StringUtils;

@Component("Test RSS Feed")
@AutoInstantiate(instanceName = "testrssfeed")
public class TestRssFeed extends AbstractComponent {

    public TestRssFeed(ComponentRequest request) {
        super(request);
    }

    @DialogField(fieldLabel = "RSS Feed Resource Path")
    public String getRssFeedResourcePath() {
        return get("rssFeedResourcePath", "");
    }

    public boolean getHasRssFeedResourcePath() {
        return StringUtils.isNotBlank(getRssFeedResourcePath());
    }

}
