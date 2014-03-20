package com.citytechinc.cq.harbor.components.content.list.assets;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.components.content.list.AbstractListComponent;
import com.citytechinc.cq.harbor.components.content.list.ListConstructionStrategy;
import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.harbor.constants.lists.ListConstants;
import com.citytechinc.cq.library.components.annotations.AutoInstantiate;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.request.ComponentRequest;
import com.day.cq.dam.api.Asset;

@Component(value = "Asset List", group = "Harbor Lists", resourceSuperType = AbstractListComponent.RESOURCE_TYPE, name = "lists/assetlist")
@AutoInstantiate(instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME)
public class AssetList extends AbstractListComponent<Asset> {

    @DialogField
    @DialogFieldSet(
            title = "List Construction"
    )
    private final AssetListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet(
            title = "List Rendering"
    )
    private final AssetListRenderingStrategy renderingStrategy;

    public AssetList(ComponentRequest request) {
        this(request.getComponentNode());
    }

    public AssetList(ComponentNode componentNode) {
        super(componentNode);

        constructionStrategy = new AssetListConstructionStrategy(componentNode);
        renderingStrategy = new AssetListRenderingStrategy(componentNode);

    }

    @Override
    protected ListConstructionStrategy<Asset> getListConstructionStrategy() {

        return constructionStrategy;

    }

    @Override
    protected ListRenderingStrategy<Asset> getListRenderingStrategy() {

        return renderingStrategy;

    }
}
