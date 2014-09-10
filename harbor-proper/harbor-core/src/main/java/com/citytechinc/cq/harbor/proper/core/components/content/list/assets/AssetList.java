package com.citytechinc.cq.harbor.proper.core.components.content.list.assets;

import java.util.List;

import com.citytechinc.aem.bedrock.api.components.annotations.AutoInstantiate;
import com.citytechinc.aem.bedrock.api.request.ComponentRequest;
import com.citytechinc.cq.component.annotations.Component;
import com.citytechinc.cq.component.annotations.DialogField;
import com.citytechinc.cq.component.annotations.widgets.DialogFieldSet;
import com.citytechinc.cq.harbor.proper.api.constants.lists.ListConstants;
import com.citytechinc.cq.harbor.proper.api.lists.construction.ListConstructionStrategy;
import com.citytechinc.cq.harbor.proper.api.lists.rendering.ListRenderingStrategy;
import com.citytechinc.cq.harbor.proper.core.components.content.list.AbstractListComponent;
import com.day.cq.dam.api.Asset;

@Component(value = "Asset List", group = "Harbor Lists", resourceSuperType = AbstractListComponent.RESOURCE_TYPE, name = "lists/assetlist")
@AutoInstantiate(instanceName = ListConstants.LIST_PAGE_CONTEXT_NAME)
public class AssetList extends AbstractListComponent<Asset, List<AssetListRenderingStrategy.RenderableAsset>> {

	@DialogField
	@DialogFieldSet(title = "List Construction")
	private AssetListConstructionStrategy constructionStrategy;

	@DialogField
	@DialogFieldSet(title = "List Rendering")
	private AssetListRenderingStrategy renderingStrategy;

	@Override
	public void init(ComponentRequest componentRequest) {
		constructionStrategy = new AssetListConstructionStrategy(this);
		renderingStrategy = new AssetListRenderingStrategy(this);

	}

	@Override
	protected ListConstructionStrategy<Asset> getListConstructionStrategy() {
		return constructionStrategy;

	}

	@Override
	protected ListRenderingStrategy<Asset, List<AssetListRenderingStrategy.RenderableAsset>> getListRenderingStrategy() {

		return renderingStrategy;

	}

    @Override
    public Boolean getIsUnorderedList() {
        return true;
    }
}
