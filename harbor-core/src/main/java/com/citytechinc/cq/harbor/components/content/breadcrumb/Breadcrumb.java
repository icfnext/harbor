package com.citytechinc.cq.harbor.components.content.breadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.constants.components.ComponentConstants;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

@Component("Breadcrumb")
public class Breadcrumb extends AbstractComponent {

    private List<PageDecorator> trail;

    public Breadcrumb(ComponentRequest request) {
        super(request);
    }

    public List<PageDecorator> getTrail() {
        if (trail != null) {
            return trail;
        }

        trail = Lists.newArrayList();

        HierarchicalPage currentHierarchicalPage = this.currentPage.adaptTo(HierarchicalPage.class);

        Optional<? extends HierarchicalPage> rootPage;
        String rootPageType = getRootPageType();
        if (rootPageType.equals(SectionLandingPage.RDF_TYPE)){
            rootPage = currentHierarchicalPage.getSectionLandingPage();
        } else if (rootPageType.equals(HomePage.RDF_TYPE)){
            rootPage = currentHierarchicalPage.getHomePage();
        } else {
            rootPage = Optional.absent();
        }

        if (rootPage.isPresent()) {
            PageDecorator currentTrailPage = currentPage;
            trail.add(currentTrailPage);

            while (currentTrailPage != null && !currentTrailPage.getPath().equals(rootPage.get().getPath())) {

                currentTrailPage = currentTrailPage.getParent();

                if (!currentTrailPage.isHideInNav()) {
                    trail.add(currentTrailPage);
                }

            }

        }

        trail = Lists.reverse(trail);

        return trail;
    }

    @DialogField(fieldLabel = "Root Page Type", fieldDescription = "The type of page that the breadcrumb will display as the root page.", ranking=2)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "Section Landing Page", value = SectionLandingPage.RDF_TYPE),
            @Option(text = "Home Page", value = HomePage.RDF_TYPE)
    })
    public String getRootPageType(){
        return get("rootPageType", SectionLandingPage.RDF_TYPE);
    }

    @DialogField(fieldLabel = "Divider", fieldDescription = "The icon that will divide the different pages in the breadcrumb.", ranking = 2)
    @Selection(type = Selection.COMBOBOX, optionsUrl = ComponentConstants.FONT_AWESOME_SERVLET_PATH )
    public String getDivider(){
        return get("rootPageType", SectionLandingPage.RDF_TYPE);
    }
}
