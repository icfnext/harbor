package com.citytechinc.cq.harbor.components.content.title;


import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.harbor.components.content.heading.Heading;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang.StringUtils;

@Component( value = "Title", resourceSuperType = Heading.RESOURCE_TYPE )
public class Title extends Heading {

    public Title(ComponentRequest request) {
        super(request);
    }

    @Override
    @DialogField( fieldLabel = "Title Text", fieldDescription = "The textual content of the rendered title. If left empty, the page title will be rendered." )
    public String getText() {

        String title = get(TEXT_PROPERTY, StringUtils.EMPTY);

        if (StringUtils.isNotBlank(title)) {
            return title;
        }

        if (currentPage.getPageTitleOptional().isPresent()) {
            return currentPage.getPageTitleOptional().get();
        }

        return currentPage.getTitle();

    }

}
