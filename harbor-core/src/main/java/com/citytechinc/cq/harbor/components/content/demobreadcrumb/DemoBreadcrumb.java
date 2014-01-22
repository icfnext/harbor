package com.citytechinc.cq.harbor.components.content.demobreadcrumb;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.harbor.content.page.HierarchicalPage;
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
        Optional<SectionLandingPage> sectionLandingPage = currentHierarchicalPage.getSectionLandingPage();

        if (sectionLandingPage.isPresent()) {
            PageDecorator currentTrailPage = currentPage;

            while (currentTrailPage != null && !currentTrailPage.getPath().equals(sectionLandingPage.get().getPath())) {

                currentTrailPage = currentTrailPage.getParent();

                if (!currentTrailPage.isHideInNav()) {
                    trail.add(currentTrailPage);
                }

            }

        }

        trail = Lists.reverse(trail);

        return trail;
    }
}
