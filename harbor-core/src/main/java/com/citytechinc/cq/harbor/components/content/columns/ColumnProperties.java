package com.citytechinc.cq.harbor.components.content.columns;

import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;

public class ColumnProperties {
    private PageDecorator currentPage;
    private ComponentNode componentNode;

    public ColumnProperties(ComponentNode componentNode, PageDecorator currentPage){
        this.currentPage = currentPage;
        this.componentNode = componentNode;
    }


}
