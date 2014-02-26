package com.citytechinc.cq.harbor.components.content.tree;


import com.citytechinc.cq.library.content.page.PageDecorator;

import java.util.List;

public class NavigationTreeNode  <T> implements  TreeNode <T> {
    private final T value;
    private final List<TreeNode<T>> children;
    private boolean isActive;

    public NavigationTreeNode(T value, List<TreeNode<T>> children) {
        this.value = value;
        this.children = children;
        this.isActive = false;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public boolean isActive(){
        return isActive;
    }

    public static boolean isActive(NavigationTreeNode n){
        return n.isActive();
    }

    public static boolean hasActiveChild(NavigationTreeNode n){
        List<NavigationTreeNode<PageDecorator>> children = n.getChildren();
        for(int i = 0; i < children.size(); i++){
            if(isActive(children.get(i))){
                return true;
            }
        }
        return false;
    }

}
