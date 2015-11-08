package com.citytechinc.aem.harbor.core.components.content.search.searchinputwidget;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.core.components.AbstractComponent;
import com.citytechinc.aem.harbor.core.constants.groups.ComponentGroups;
import com.citytechinc.aem.harbor.core.util.icon.IconUtils;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
        value = "Search Widget",
        name = "search/searchinputwidget",
        group = ComponentGroups.HARBOR_SEARCH,
        disableTargeting = true,
        layout = "rollover"
)
@AutoInstantiate(instanceName = "searchinputwidget")
@Model(adaptables = Resource.class)
public class SearchInputWidget extends AbstractComponent {


    @DialogField(fieldLabel = "Search Button Text", fieldDescription = "Allows for Icon Inputs", ranking = 20)
    public String getSearchButtonText() {
        return IconUtils.iconify(get("searchButtonText", "Search {{icon fa-search}}"));
    }

    @DialogField(fieldLabel = "Search Results Page", ranking = 10)
    @PathField(rootPath = "/content/")
    public String getSearchPageHref() {
        return getAsHref("searchPageHref").or("#");
    }


    @DialogField(fieldLabel = "Query Parameter Name", fieldDescription = "If you want the query parameter to be named something else, enter it here.  *This is typically used when you are forwarding to a search page not using the out of the box search component*")
    public String getQueryParameterName(){
        return get("queryParameterName","q");
    }
}
