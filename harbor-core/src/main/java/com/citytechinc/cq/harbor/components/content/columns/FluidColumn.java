package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.ContentProperty;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Listener;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.editconfig.ActionConfig;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.library.components.AbstractComponent;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import org.apache.commons.lang3.StringUtils;

@Component(value = "Fluid Column",
        actions = {"text:Fluid Column", "edit", "delete"},
        contentAdditionalProperties = {@ContentProperty(name="dependencies", value="harbor.components.content.fluidcolumnrow")},
        actionConfigs = {
                @ActionConfig(xtype = "tbseparator"),
                @ActionConfig(text="Move Left", handler="Harbor.Components.FluidColumnRow.moveColumnLeft"),
                @ActionConfig(text="Move Right", handler="Harbor.Components.FluidColumnRow.moveColumnRight")
        },
        listeners = {
                @Listener(name = "afterdelete", value = "REFRESH_PARENT"),
                @Listener(name = "afteredit", value = "REFRESH_PARENT"),
        },
        group = ".hidden"
)
public class FluidColumn extends AbstractComponent{
    private static final String GRID_EXTRA_SMALL = "col-xs-";
    private static final String GRID_SMALL = "col-sm-";
    private static final String GRID_MEDIUM = "col-md-";
    private static final String GRID_LARGE = "col-lg-";

    private static final String OFFSET_EXTRA_SMALL = "col-xs-offset-";
    private static final String OFFSET_SMALL = "col-sm-offset-";
    private static final String OFFSET_MEDIUM = "col-md-offset";
    private static final String OFFSET_LARGE = "col-lg-offset";

    public FluidColumn(ComponentRequest req) {
        super(req);
    }
    public FluidColumn(ComponentNode node) {
        super(node);
    }

    @DialogField(fieldLabel = "Extra Small Grid Size", ranking = 1)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1/12", value = GRID_EXTRA_SMALL + "1"),
            @Option(text = "2/12", value = GRID_EXTRA_SMALL + "2"),
            @Option(text = "3/12", value = GRID_EXTRA_SMALL + "3"),
            @Option(text = "4/12", value = GRID_EXTRA_SMALL + "4"),
            @Option(text = "5/12", value = GRID_EXTRA_SMALL + "5"),
            @Option(text = "6/12", value = GRID_EXTRA_SMALL + "6"),
            @Option(text = "7/12", value = GRID_EXTRA_SMALL + "7"),
            @Option(text = "8/12", value = GRID_EXTRA_SMALL + "8"),
            @Option(text = "9/12", value = GRID_EXTRA_SMALL + "9"),
            @Option(text = "10/12", value = GRID_EXTRA_SMALL + "10"),
            @Option(text = "11/12", value = GRID_EXTRA_SMALL + "11"),
            @Option(text = "12/12", value = GRID_EXTRA_SMALL + "12")
    })
    public String getExtraSmallColClass() {
        return get("extraSmallColClass", "");
    }

    @DialogField(fieldLabel = "Extra Small Offset", ranking = 2)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = OFFSET_EXTRA_SMALL + "0"),
            @Option(text = "1/12", value = OFFSET_EXTRA_SMALL + "1"),
            @Option(text = "2/12", value = OFFSET_EXTRA_SMALL + "2"),
            @Option(text = "3/12", value = OFFSET_EXTRA_SMALL + "3"),
            @Option(text = "4/12", value = OFFSET_EXTRA_SMALL + "4"),
            @Option(text = "5/12", value = OFFSET_EXTRA_SMALL + "5"),
            @Option(text = "6/12", value = OFFSET_EXTRA_SMALL + "6"),
            @Option(text = "7/12", value = OFFSET_EXTRA_SMALL + "7"),
            @Option(text = "8/12", value = OFFSET_EXTRA_SMALL + "8"),
            @Option(text = "9/12", value = OFFSET_EXTRA_SMALL + "9"),
            @Option(text = "10/12", value = OFFSET_EXTRA_SMALL + "10"),
            @Option(text = "11/12", value = OFFSET_EXTRA_SMALL + "11")
    })
    public String getExtraSmallOffsetClass() {
        return get("extraSmallOffsetClass", "");
    }

    @DialogField(fieldLabel = "Small Grid Size", ranking = 3)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1/12", value = GRID_SMALL + "1"),
            @Option(text = "2/12", value = GRID_SMALL + "2"),
            @Option(text = "3/12", value = GRID_SMALL + "3"),
            @Option(text = "4/12", value = GRID_SMALL + "4"),
            @Option(text = "5/12", value = GRID_SMALL + "5"),
            @Option(text = "6/12", value = GRID_SMALL + "6"),
            @Option(text = "7/12", value = GRID_SMALL + "7"),
            @Option(text = "8/12", value = GRID_SMALL + "8"),
            @Option(text = "9/12", value = GRID_SMALL + "9"),
            @Option(text = "10/12", value = GRID_SMALL + "10"),
            @Option(text = "11/12", value = GRID_SMALL + "11"),
            @Option(text = "12/12", value = GRID_SMALL + "12")
    })
    public String getSmallColClass() {
        return get("smallColClass", "");
    }

    @DialogField(fieldLabel = "Small Offset", ranking = 4)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = OFFSET_SMALL + "0"),
            @Option(text = "1/12", value = OFFSET_SMALL + "1"),
            @Option(text = "2/12", value = OFFSET_SMALL + "2"),
            @Option(text = "3/12", value = OFFSET_SMALL + "3"),
            @Option(text = "4/12", value = OFFSET_SMALL + "4"),
            @Option(text = "5/12", value = OFFSET_SMALL + "5"),
            @Option(text = "6/12", value = OFFSET_SMALL + "6"),
            @Option(text = "7/12", value = OFFSET_SMALL + "7"),
            @Option(text = "8/12", value = OFFSET_SMALL + "8"),
            @Option(text = "9/12", value = OFFSET_SMALL + "9"),
            @Option(text = "10/12", value = OFFSET_SMALL + "10"),
            @Option(text = "11/12", value = OFFSET_SMALL + "11")
    })
    public String getSmallOffsetClass() {
        return get("smallOffsetClass", "");
    }

    @DialogField(fieldLabel = "Medium Grid Size", ranking = 5)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1/12", value = GRID_MEDIUM + "1"),
            @Option(text = "2/12", value = GRID_MEDIUM + "2"),
            @Option(text = "3/12", value = GRID_MEDIUM + "3"),
            @Option(text = "4/12", value = GRID_MEDIUM + "4"),
            @Option(text = "5/12", value = GRID_MEDIUM + "5"),
            @Option(text = "6/12", value = GRID_MEDIUM + "6"),
            @Option(text = "7/12", value = GRID_MEDIUM + "7"),
            @Option(text = "8/12", value = GRID_MEDIUM + "8"),
            @Option(text = "9/12", value = GRID_MEDIUM + "9"),
            @Option(text = "10/12", value = GRID_MEDIUM + "10"),
            @Option(text = "11/12", value = GRID_MEDIUM + "11"),
            @Option(text = "12/12", value = GRID_MEDIUM + "12")
    })
    public String getMediumColClass() {
        return get("mediumColClass", "");
    }

    @DialogField(fieldLabel = "Medium Offset", ranking = 6)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = OFFSET_MEDIUM + "0"),
            @Option(text = "1/12", value = OFFSET_MEDIUM + "1"),
            @Option(text = "2/12", value = OFFSET_MEDIUM + "2"),
            @Option(text = "3/12", value = OFFSET_MEDIUM + "3"),
            @Option(text = "4/12", value = OFFSET_MEDIUM + "4"),
            @Option(text = "5/12", value = OFFSET_MEDIUM + "5"),
            @Option(text = "6/12", value = OFFSET_MEDIUM + "6"),
            @Option(text = "7/12", value = OFFSET_MEDIUM + "7"),
            @Option(text = "8/12", value = OFFSET_MEDIUM + "8"),
            @Option(text = "9/12", value = OFFSET_MEDIUM + "9"),
            @Option(text = "10/12", value = OFFSET_MEDIUM + "10"),
            @Option(text = "11/12", value = OFFSET_MEDIUM + "11")
    })
    public String getMediumOffsetClass() {
        return get("mediumOffsetClass", "");
    }

    @DialogField(fieldLabel = "Large Grid Size", ranking = 7)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "1/12", value = GRID_LARGE + "1"),
            @Option(text = "2/12", value = GRID_LARGE + "2"),
            @Option(text = "3/12", value = GRID_LARGE + "3"),
            @Option(text = "4/12", value = GRID_LARGE + "4"),
            @Option(text = "5/12", value = GRID_LARGE + "5"),
            @Option(text = "6/12", value = GRID_LARGE + "6"),
            @Option(text = "7/12", value = GRID_LARGE + "7"),
            @Option(text = "8/12", value = GRID_LARGE + "8"),
            @Option(text = "9/12", value = GRID_LARGE + "9"),
            @Option(text = "10/12", value = GRID_LARGE + "10"),
            @Option(text = "11/12", value = GRID_LARGE + "11"),
            @Option(text = "12/12", value = GRID_LARGE + "12")
    })
    public String getLargeColClass() {
        return get("largeColClass", "");
    }

    @DialogField(fieldLabel = "Large Offset", ranking = 8)
    @Selection(type = Selection.SELECT, options = {
            @Option(text = "--", value = OFFSET_LARGE + "0"),
            @Option(text = "1/12", value = OFFSET_LARGE + "1"),
            @Option(text = "2/12", value = OFFSET_LARGE + "2"),
            @Option(text = "3/12", value = OFFSET_LARGE + "3"),
            @Option(text = "4/12", value = OFFSET_LARGE + "4"),
            @Option(text = "5/12", value = OFFSET_LARGE + "5"),
            @Option(text = "6/12", value = OFFSET_LARGE + "6"),
            @Option(text = "7/12", value = OFFSET_LARGE + "7"),
            @Option(text = "8/12", value = OFFSET_LARGE + "8"),
            @Option(text = "9/12", value = OFFSET_LARGE + "9"),
            @Option(text = "10/12", value = OFFSET_LARGE + "10"),
            @Option(text = "11/12", value = OFFSET_LARGE + "11")
    })
    public String getLargeOffsetClass() {
        return get("largeOffsetClass", "");
    }
    

    private String buildGridSizeClass(){
        StringBuilder sb = new StringBuilder();
        sb.append(getExtraSmallColClass());
        sb.append(" ");
        sb.append(getSmallColClass());
        sb.append(" ");
        sb.append(getMediumColClass());
        sb.append(" ");
        sb.append(getLargeColClass());
        sb.append(" ");

        return sb.toString();
    }

    public String getColClass(){
        //Init Grid Column Size
        String gridSize = buildGridSizeClass();
        if(StringUtils.isEmpty(gridSize.trim())){
            return GRID_MEDIUM + "1";
        }
        else{
            return gridSize;
        }
    }

    private String buildOffsetClass(){
        StringBuilder sb = new StringBuilder();
        sb.append(getExtraSmallOffsetClass());
        sb.append(" ");
        sb.append(getSmallOffsetClass());
        sb.append(" ");
        sb.append(getMediumOffsetClass());
        sb.append(" ");
        sb.append(getLargeOffsetClass());
        sb.append(" ");

        return sb.toString();
    }

    public String getOffsetClass(){
        return buildOffsetClass().trim();
    }

    public String getName() {
        return this.getResource().getName();
    }
}