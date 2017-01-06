package com.icfolson.aem.harbor.core.components.content.list.assets;

import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.day.cq.dam.api.Asset;
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy;
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy;
import com.icfolson.aem.harbor.core.components.content.list.AbstractListComponent;
import com.icfolson.aem.harbor.core.constants.groups.ComponentGroups;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

@Component(value = "Asset List",
    group = ComponentGroups.HARBOR_LISTS,
    name = "lists/assetlist")
@Model(adaptables = Resource.class)
public class AssetList extends AbstractListComponent<Asset, List<AssetListRenderingStrategy.RenderableAsset>> {

    @DialogField
    @DialogFieldSet(title = "List Construction")
    @Inject
    @Self
    private AssetListConstructionStrategy constructionStrategy;

    @DialogField
    @DialogFieldSet(title = "List Rendering")
    @Inject
    @Self
    private AssetListRenderingStrategy renderingStrategy;

    @Override
    protected ListConstructionStrategy<Asset> getListConstructionStrategy() {
        return constructionStrategy;
    }

    @Override
    protected ListRenderingStrategy<Asset, List<AssetListRenderingStrategy.RenderableAsset>> getListRenderingStrategy() {
        return renderingStrategy;
    }
}
