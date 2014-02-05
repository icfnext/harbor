package com.citytechinc.cq.harbor.components.content.demobreadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
import com.citytechinc.cq.harbor.content.page.HomePage;
import com.citytechinc.cq.harbor.content.page.SectionLandingPage;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

@Component("Demo Breadcrumb")
public class DemoBreadcrumb extends AbstractComponent {

    private List<PageDecorator> trail;

    public DemoBreadcrumb(ComponentRequest request) {
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

    @DialogField(fieldLabel = "Root Page Type")
    public String getRootPageType(){
        return get("rootPageType", SectionLandingPage.RDF_TYPE);
    }
}
