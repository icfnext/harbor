package com.icfolson.aem.harbor.core.components.content.columns;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.Tab;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.component.annotations.widgets.Switch;
import com.citytechinc.cq.component.annotations.widgets.TextField;
import com.google.common.collect.Lists;
import com.icfolson.aem.harbor.api.constants.bootstrap.Bootstrap;
import com.icfolson.aem.harbor.core.components.mixins.classifiable.Classification;
import com.icfolson.aem.library.core.constants.ComponentConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Component(
    value = "Column",
    actions = { "text:Column", "edit", "delete" },
    listeners = {
        @Listener(name = "afteredit", value = "REFRESH_PAGE")
    },
    group = ComponentConstants.GROUP_HIDDEN,
    tabs = {
        @Tab(title = "Column"),
        @Tab(title = "Advanced")
    })
@Model(adaptables = Resource.class)
public class Column {

    @DialogField(fieldLabel = "Extra Small Device Width", ranking = 0)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = "default"),
        @Option(text = "1/12", value = Bootstrap.GRID_EXTRA_SMALL + "1"),
        @Option(text = "2/12", value = Bootstrap.GRID_EXTRA_SMALL + "2"),
        @Option(text = "3/12", value = Bootstrap.GRID_EXTRA_SMALL + "3"),
        @Option(text = "4/12", value = Bootstrap.GRID_EXTRA_SMALL + "4"),
        @Option(text = "5/12", value = Bootstrap.GRID_EXTRA_SMALL + "5"),
        @Option(text = "6/12", value = Bootstrap.GRID_EXTRA_SMALL + "6"),
        @Option(text = "7/12", value = Bootstrap.GRID_EXTRA_SMALL + "7"),
        @Option(text = "8/12", value = Bootstrap.GRID_EXTRA_SMALL + "8"),
        @Option(text = "9/12", value = Bootstrap.GRID_EXTRA_SMALL + "9"),
        @Option(text = "10/12", value = Bootstrap.GRID_EXTRA_SMALL + "10"),
        @Option(text = "11/12", value = Bootstrap.GRID_EXTRA_SMALL + "11"),
        @Option(text = "12/12", value = Bootstrap.GRID_EXTRA_SMALL + "12")
    })
    @Inject
    @Default(values = "default")
    private String xsSize;

    @DialogField(fieldLabel = "Small Device Width", ranking = 10)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = "default"),
        @Option(text = "1/12", value = Bootstrap.GRID_SMALL + "1"),
        @Option(text = "2/12", value = Bootstrap.GRID_SMALL + "2"),
        @Option(text = "3/12", value = Bootstrap.GRID_SMALL + "3"),
        @Option(text = "4/12", value = Bootstrap.GRID_SMALL + "4"),
        @Option(text = "5/12", value = Bootstrap.GRID_SMALL + "5"),
        @Option(text = "6/12", value = Bootstrap.GRID_SMALL + "6"),
        @Option(text = "7/12", value = Bootstrap.GRID_SMALL + "7"),
        @Option(text = "8/12", value = Bootstrap.GRID_SMALL + "8"),
        @Option(text = "9/12", value = Bootstrap.GRID_SMALL + "9"),
        @Option(text = "10/12", value = Bootstrap.GRID_SMALL + "10"),
        @Option(text = "11/12", value = Bootstrap.GRID_SMALL + "11"),
        @Option(text = "12/12", value = Bootstrap.GRID_SMALL + "12")
    })
    @Inject
    @Default(values = "default")
    private String smSize;

    @DialogField(fieldLabel = "Medium Device Width", ranking = 20)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = "default"),
        @Option(text = "1/12", value = Bootstrap.GRID_MEDIUM + "1"),
        @Option(text = "2/12", value = Bootstrap.GRID_MEDIUM + "2"),
        @Option(text = "3/12", value = Bootstrap.GRID_MEDIUM + "3"),
        @Option(text = "4/12", value = Bootstrap.GRID_MEDIUM + "4"),
        @Option(text = "5/12", value = Bootstrap.GRID_MEDIUM + "5"),
        @Option(text = "6/12", value = Bootstrap.GRID_MEDIUM + "6"),
        @Option(text = "7/12", value = Bootstrap.GRID_MEDIUM + "7"),
        @Option(text = "8/12", value = Bootstrap.GRID_MEDIUM + "8"),
        @Option(text = "9/12", value = Bootstrap.GRID_MEDIUM + "9"),
        @Option(text = "10/12", value = Bootstrap.GRID_MEDIUM + "10"),
        @Option(text = "11/12", value = Bootstrap.GRID_MEDIUM + "11"),
        @Option(text = "12/12", value = Bootstrap.GRID_MEDIUM + "12")
    })
    @Inject
    @Default(values = "default")
    private String mdSize;

    @DialogField(fieldLabel = "Large Device Width", ranking = 30)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = "default"),
        @Option(text = "1/12", value = Bootstrap.GRID_LARGE + "1"),
        @Option(text = "2/12", value = Bootstrap.GRID_LARGE + "2"),
        @Option(text = "3/12", value = Bootstrap.GRID_LARGE + "3"),
        @Option(text = "4/12", value = Bootstrap.GRID_LARGE + "4"),
        @Option(text = "5/12", value = Bootstrap.GRID_LARGE + "5"),
        @Option(text = "6/12", value = Bootstrap.GRID_LARGE + "6"),
        @Option(text = "7/12", value = Bootstrap.GRID_LARGE + "7"),
        @Option(text = "8/12", value = Bootstrap.GRID_LARGE + "8"),
        @Option(text = "9/12", value = Bootstrap.GRID_LARGE + "9"),
        @Option(text = "10/12", value = Bootstrap.GRID_LARGE + "10"),
        @Option(text = "11/12", value = Bootstrap.GRID_LARGE + "11"),
        @Option(text = "12/12", value = Bootstrap.GRID_LARGE + "12")
    })
    @Inject
    @Default(values = "default")
    private String lgSize;

    @DialogField(fieldLabel = "Ordering", ranking = 35)
    @Selection(type = Selection.SELECT, options = {
        @Option(text = "Default", value = "default"),
        @Option(text = "Push 1", value = Bootstrap.GRID_MEDIUM + "push-1"),
        @Option(text = "Push 2", value = Bootstrap.GRID_MEDIUM + "push-2"),
        @Option(text = "Push 3", value = Bootstrap.GRID_MEDIUM + "push-3"),
        @Option(text = "Push 4", value = Bootstrap.GRID_MEDIUM + "push-4"),
        @Option(text = "Push 5", value = Bootstrap.GRID_MEDIUM + "push-5"),
        @Option(text = "Push 6", value = Bootstrap.GRID_MEDIUM + "push-6"),
        @Option(text = "Push 7", value = Bootstrap.GRID_MEDIUM + "push-7"),
        @Option(text = "Push 8", value = Bootstrap.GRID_MEDIUM + "push-8"),
        @Option(text = "Push 9", value = Bootstrap.GRID_MEDIUM + "push-9"),
        @Option(text = "Push 10", value = Bootstrap.GRID_MEDIUM + "push-10"),
        @Option(text = "Push 11", value = Bootstrap.GRID_MEDIUM + "push-11"),
        @Option(text = "Pull 1", value = Bootstrap.GRID_MEDIUM + "pull-1"),
        @Option(text = "Pull 2", value = Bootstrap.GRID_MEDIUM + "pull-2"),
        @Option(text = "Pull 3", value = Bootstrap.GRID_MEDIUM + "pull-3"),
        @Option(text = "Pull 4", value = Bootstrap.GRID_MEDIUM + "pull-4"),
        @Option(text = "Pull 5", value = Bootstrap.GRID_MEDIUM + "pull-5"),
        @Option(text = "Pull 6", value = Bootstrap.GRID_MEDIUM + "pull-6"),
        @Option(text = "Pull 7", value = Bootstrap.GRID_MEDIUM + "pull-7"),
        @Option(text = "Pull 8", value = Bootstrap.GRID_MEDIUM + "pull-8"),
        @Option(text = "Pull 9", value = Bootstrap.GRID_MEDIUM + "pull-9"),
        @Option(text = "Pull 10", value = Bootstrap.GRID_MEDIUM + "pull-10"),
        @Option(text = "Pull 11", value = Bootstrap.GRID_MEDIUM + "pull-11"),
    })
    @Inject
    @Default(values = "default")
    private String mdOrdering;

    @DialogField(tab = 1, ranking = 40)
    @DialogFieldSet
    @Self
    @Optional
    private Classification classification;

    @DialogField(fieldLabel = "Inherit Content?",
        fieldDescription = "Inherit column content from parent page. The component layout of the child page must exactly match that of the parent page. This Column looks along the same content path in the parent's tree, and will display content from a Column in the parent page at the same content location. If the component structure does not match, paragraph inheritance will not function.",
        tab = 2)
    @Switch(offText = "No", onText = "Yes")
    @Inject
    @Default(booleanValues = false)
    private boolean inherited;

    @DialogField(
        fieldLabel = "ID",
        fieldDescription = "A unique identifier to apply to the Row element rendered in the page DOM.  If left blank, no id attribute will be applied to the rendered element.",
        tab = 2)
    @TextField
    @Inject
    @Optional
    private String domId;

    @Inject
    @Self
    private Resource self;

    public String getName() {
        return self.getName();
    }

    public String getPath() {
        return self.getPath();
    }

    public String getColumnWidthClasses() {
        List<String> colSizes = Lists.newArrayList();

        if (!"default".equals(xsSize)) {
            colSizes.add(xsSize);
        }
        if (!"default".equals(smSize)) {
            colSizes.add(smSize);
        }
        if (!"default".equals(mdSize)) {
            colSizes.add(mdSize);
        }
        if (!"default".equals(lgSize)) {
            colSizes.add(lgSize);
        }
        if (!"default".equals(mdOrdering)) {
            colSizes.add(mdOrdering);
        }

        if (colSizes.isEmpty()) {
            colSizes.add(Bootstrap.GRID_MEDIUM + "1");
        }

        return StringUtils.join(colSizes, " ");
    }

    public Classification getClassification() {
        return classification;
    }

    public Boolean getIsInherited() {
        return inherited;
    }

    public String getDomId() {
        return domId;
    }

    public boolean isHasDomId() {
        return StringUtils.isNotBlank(getDomId());
    }

    public String getParagraphResourceType() {
        if (getIsInherited()) {
            return "wcm/foundation/components/iparsys";
        }

        return "wcm/foundation/components/parsys";
    }
}