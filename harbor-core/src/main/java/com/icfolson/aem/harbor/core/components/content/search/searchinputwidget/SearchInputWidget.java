package com.icfolson.aem.harbor.core.components.content.search.searchinputwidget;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.PathField;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import com.icfolson.aem.harbor.core.util.icon.IconUtils;
import com.icfolson.aem.library.core.components.AbstractComponent;
import com.icfolson.aem.library.core.constants.PathConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Component(
    value = "Search Widget",
    name = "search/searchinputwidget",
    group = ComponentGroups.HARBOR_SEARCH,
    disableTargeting = true,
    layout = "rollover"
)
@Model(adaptables = Resource.class)
public class SearchInputWidget extends AbstractComponent {

    @DialogField(fieldLabel = "Search Results Page", required = true, ranking = 10)
    @PathField(rootPath = PathConstants.PATH_CONTENT)
    public String getSearchPageHref() {
        return getAsHref("searchPageHref").or("#");
    }

    @DialogField(fieldLabel = "Search Button Text", fieldDescription = "Allows for Icon Inputs", ranking = 20)
    @TextField
    public String getSearchButtonText() {
        return IconUtils.iconify(get("searchButtonText", "Search {{icon fa-search}}"));
    }

    @DialogField(fieldLabel = "Query Parameter Name",
        fieldDescription = "If you want the query parameter to be named something else, enter it here.  *This is typically used when you are forwarding to a search page not using the out of the box search component*", ranking = 30)
    @TextField
    public String getQueryParameterName() {
        return get("queryParameterName", "q");
    }
}
