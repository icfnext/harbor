package com.citytechinc.cq.harbor.components.content.list.assets;

import com.citytechinc.cq.harbor.components.content.list.ListRenderingStrategy;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.day.cq.dam.api.Asset;

public class AssetListRenderingStrategy implements ListRenderingStrategy<Asset> {

    public AssetListRenderingStrategy(ComponentNode componentNode) {

// TODO : add options

    }

    @Override
    public String renderListItem(Asset item) {

// TODO : actually implement this

        StringBuffer renderingStringBuffer = new StringBuffer();

        renderingStringBuffer.append("<a href=\"" + item.getPath() + "\">" + item.getName() + "</a><br>");

        return renderingStringBuffer.toString();


    }

}
