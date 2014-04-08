package com.citytechinc.cq.harbor.components.content.list.assets;

import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.Option;
import com.citytechinc.cq.component.annotations.widgets.Selection;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.dam.api.Asset;
import org.apache.commons.lang.StringUtils;

/**
 * Renders a list of assets as links.
 */
public class AssetListRenderingStrategy implements ListRenderingStrategy<Asset> {

    private static final String PARAM_AS_LINKS = "renderAsLinks";
    private static final boolean DEFAULT_AS_LINKS = false;
    @DialogField(
            fieldLabel = "Render as Links",
            fieldDescription = "Check box to render items as links, otherwise items will be rendered as static text."
    )
    @Selection(
            type = Selection.CHECKBOX,
            options = @Option( value = "true" )
    )
    private boolean renderAsLinks;

    public AssetListRenderingStrategy(ComponentNode componentNode) {

        setRenderAsLinks(componentNode.get(PARAM_AS_LINKS, DEFAULT_AS_LINKS));

    }

    /**
     * Render an asset based on provided options.
     *
     * @param item
     * @return
     */
    @Override
    public String renderListItem(Asset item) {

        String rendered = StringUtils.EMPTY;

        if(renderAsLinks) {

            rendered = "<a href=\"" + item.getPath() + "\">" + item.getName() + "</a><br>";

        } else {

            rendered = item.getName();

        }

        return rendered;

    }

    /**
     * Set to true to render items as links to their resources, otherwise render items as static text.
     *
     * @param renderAsLinks
     */
    public void setRenderAsLinks(boolean renderAsLinks) {

        this.renderAsLinks = renderAsLinks;

    }

    public boolean getRenderAsLinks() {

        return this.renderAsLinks;

    }

}
