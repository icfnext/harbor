package com.citytechinc.cq.harbor.proper.components.content.navigation;

import com.citytechinc.cq.harbor.proper.components.content.tree.TreeNode;
import com.citytechinc.cq.library.content.page.PageDecorator;

public class Util {
    public static String getNodeLinkText(TreeNode<PageDecorator> n){
        PageDecorator d = n.getValue();
        String pageTitle = d.getPageTitle();
        String navigationTitle = d.getNavigationTitle();

        if(navigationTitle != null){
            return navigationTitle;
        }
        else if(pageTitle != null){
            return pageTitle;
        }
        else{
            return d.getTitle();
        }
    }
}
