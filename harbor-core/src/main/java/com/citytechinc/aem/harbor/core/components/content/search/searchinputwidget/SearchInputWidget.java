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

@Component(
        value = "Search Widget",
        name = "search/searchinputwidget",
        contentAdditionalProperties = {
                @ContentProperty(name = "dependencies", value = "[harbor.fontawesome,harbor.components.content.search.searchinputwidget]"),  },
        group = ComponentGroups.HARBOR_SEARCH
)
@AutoInstantiate(instanceName = "searchinputwidget")
public class SearchInputWidget extends AbstractComponent {

    public static final String ASYNCHRONOUS_SUBMISSION_TYPE = "asynchronous";
    public static final String SYNCHRONOUS_SUBMISSION_TYPE = "synchronous";

    @DialogField(fieldLabel = "Allow In Place Search", ranking = 30, fieldDescription = "When set to true, an input field will render along with the widget and pressing the search button will execute a search.  When set to false, pressing the search button will simply link to the search page.")
    @Selection(type = Selection.CHECKBOX, options = {@Option(value="true")})
    public boolean isAllowInPlaceSearch() {
        return get("allowInPlaceSearch", false);
    }

    @DialogField(fieldLabel = "Asynchronous Search", ranking = 40, fieldDescription = "When set to true, search results will render asynchronously after the search page has loaded.  When set to false, search results will be rendered as part of the page load.  NOTE, asynchronous searching will not work as expected unless both the Search Widget component and the Search Results component on the search results page are set to use asynchronous searching.")
    @Selection(type = Selection.CHECKBOX, options = {@Option(value="true")})
    public boolean isAsynchronous() {
        return get("asynchronous", false);
    }

    @DialogField(fieldLabel = "Search Button Text", fieldDescription = "Allows for Icon Inputs", ranking = 20)
    public String getSearchButtonText() {
        return IconUtils.iconify(get("searchButtonText", "Search {{icon fa-search}}"));
    }

    @DialogField(fieldLabel = "Search Results Page", ranking = 10)
    @PathField
    public String getSearchPageHref() {
        return getAsHref("searchPageHref").or("#");
    }

    public String getSubmissionType() {
        if (isAsynchronous()) {
            return ASYNCHRONOUS_SUBMISSION_TYPE;
        }

        return SYNCHRONOUS_SUBMISSION_TYPE;
    }

}
