package com.citytechinc.cq.harbor.proper.core.content.page.impl;


import com.citytechinc.cq.library.content.link.ImageLink;
import com.citytechinc.cq.library.content.link.Link;
import com.citytechinc.cq.library.content.link.NavigationLink;
import com.citytechinc.cq.library.content.link.builders.LinkBuilder;
import com.citytechinc.cq.library.content.node.ComponentNode;
import com.citytechinc.cq.library.content.page.PageDecorator;
import com.citytechinc.cq.library.content.page.PageManagerDecorator;
import com.day.cq.commons.Filter;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.Template;
import com.day.cq.wcm.api.WCMException;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class AbstractHierarchicalPage implements PageDecorator {

    private final PageDecorator page;

    public AbstractHierarchicalPage(PageDecorator page) {
        this.page = page;
    }

    @Override
    public Optional<PageDecorator> findAncestor(Predicate<PageDecorator> pageDecoratorPredicate) {
        return page.findAncestor(pageDecoratorPredicate);
    }

    @Override
    public PageDecorator getAbsoluteParent(int i) {
        return page.getAbsoluteParent(i);
    }

    @Override
    public ValueMap getProperties() {
        return page.getProperties();
    }

    @Override
    public ValueMap getProperties(String s) {
        return page.getProperties(s);
    }

    @Override
    public String getName() {
        return page.getName();
    }

    @Override
    public String getTitle() {
        return page.getTitle();
    }

    @Override
    public String getDescription() {
        return page.getDescription();
    }

    @Override
    public String getPageTitle() {
        return page.getPageTitle();
    }

    @Override
    public String getNavigationTitle() {
        return page.getNavigationTitle();
    }

    @Override
    public boolean isHideInNav() {
        return page.isHideInNav();
    }

    @Override
    public boolean hasContent() {
        return page.hasContent();
    }

    @Override
    public boolean isValid() {
        return page.isValid();
    }

    @Override
    public long timeUntilValid() {
        return page.timeUntilValid();
    }

    @Override
    public Calendar getOnTime() {
        return page.getOnTime();
    }

    @Override
    public Calendar getOffTime() {
        return page.getOffTime();
    }

    @Override
    public String getLastModifiedBy() {
        return page.getLastModifiedBy();
    }

    @Override
    public Calendar getLastModified() {
        return page.getLastModified();
    }

    @Override
    public String getVanityUrl() {
        return page.getVanityUrl();
    }

    @Override
    public Tag[] getTags() {
        return page.getTags();
    }

    @Override
    public void lock() throws WCMException {
        page.lock();
    }

    @Override
    public boolean isLocked() {
        return page.isLocked();
    }

    @Override
    public String getLockOwner() {
        return page.getLockOwner();
    }

    @Override
    public boolean canUnlock() {
        return page.canUnlock();
    }

    @Override
    public void unlock() throws WCMException {
        page.unlock();
    }

    @Override
    public Template getTemplate() {
        return page.getTemplate();
    }

    @Override
    public Locale getLanguage(boolean b) {
        return page.getLanguage(b);
    }

    @Override
    public List<PageDecorator> getChildren() {
        return page.getChildren();
    }

    @Override
    public List<PageDecorator> getChildren(boolean b) {
        return page.getChildren(b);
    }

    @Override
    public List<PageDecorator> getChildren(Predicate<PageDecorator> pageDecoratorPredicate) {
        return page.getChildren(pageDecoratorPredicate);
    }

    @Override
    public List<PageDecorator> getChildren(Predicate<PageDecorator> pageDecoratorPredicate, boolean b) {
        return page.getChildren(pageDecoratorPredicate, b);
    }

    @Override
    public Optional<ComponentNode> getComponentNode() {
        return page.getComponentNode();
    }

    @Override
    public Optional<ComponentNode> getComponentNode(String s) {
        return page.getComponentNode(s);
    }

    @Override
    public ImageLink getImageLink(String s) {
        return page.getImageLink(s);
    }

    @Override
    public NavigationLink getNavigationLink() {
        return page.getNavigationLink();
    }

    @Override
    public NavigationLink getNavigationLink(boolean b) {
        return page.getNavigationLink(b);
    }

    @Override
    public Optional<String> getNavigationTitleOptional() {
        return page.getNavigationTitleOptional();
    }

    @Override
    public String getPath() {
        return page.getPath();
    }

    @Override
    public PageManagerDecorator getPageManager() {
        return page.getPageManager();
    }

    @Override
    public Resource getContentResource() {
        return page.getContentResource();
    }

    @Override
    public Resource getContentResource(String s) {
        return page.getContentResource(s);
    }

    @Override
    public Iterator<Page> listChildren() {
        return page.listChildren();
    }

    @Override
    public Iterator<Page> listChildren(Filter<Page> pageFilter) {
        return page.listChildren(pageFilter);
    }

    @Override
    public Iterator<Page> listChildren(Filter<Page> pageFilter, boolean b) {
        return page.listChildren(pageFilter, b);
    }

    @Override
    public boolean hasChild(String s) {
        return page.hasChild(s);
    }

    @Override
    public int getDepth() {
        return page.getDepth();
    }

    @Override
    public Optional<String> getPageTitleOptional() {
        return page.getPageTitleOptional();
    }

    @Override
    public PageDecorator getParent() {
        return page.getParent();
    }

    @Override
    public PageDecorator getParent(int i) {
        return page.getParent(i);
    }

    @Override
    public String getTemplatePath() {
        return page.getTemplatePath();
    }

    @Override
    public Optional<String> getImageSource() {
        return page.getImageSource();
    }

    @Override
    public Optional<String> getImageSource(int i) {
        return page.getImageSource(i);
    }

    @Override
    public Optional<String> getImageSource(String s) {
        return page.getImageSource(s);
    }

    @Override
    public Optional<String> getImageSource(String s, int i) {
        return page.getImageSource(s, i);
    }

    @Override
    public String getHref() {
        return page.getHref();
    }

    @Override
    public String getHref(boolean b) {
        return page.getHref(b);
    }

    @Override
    public Link getLink() {
        return page.getLink();
    }

    @Override
    public Link getLink(boolean b) {
        return page.getLink(b);
    }

    @Override
    public LinkBuilder getLinkBuilder() {
        return page.getLinkBuilder();
    }

    @Override
    public LinkBuilder getLinkBuilder(boolean b) {
        return page.getLinkBuilder(b);
    }

    @Override
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> adapterTypeClass) {
        return page.adaptTo(adapterTypeClass);
    }
}
