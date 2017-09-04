package com.icfolson.aem.harbor.core.components.content.list

import com.icfolson.aem.harbor.HarborSpec
import com.icfolson.aem.harbor.api.lists.construction.ListConstructionStrategy
import com.icfolson.aem.harbor.api.lists.rendering.ListRenderingStrategy
import com.icfolson.aem.harbor.api.lists.rendering.RenderableItem
import com.icfolson.aem.harbor.core.lists.rendering.PassthroughListRenderingStrategy
import com.icfolson.aem.library.api.page.PageDecorator
import groovy.transform.TupleConstructor

class AbstractListComponentSpec extends HarborSpec {

    @TupleConstructor
    static class NonRenderableListComponent extends AbstractListComponent<PageDecorator, List<PageDecorator>> {

        PageDecorator rootPage

        @Override
        protected ListConstructionStrategy<PageDecorator> getListConstructionStrategy() {
            new ListConstructionStrategy<PageDecorator>() {
                @Override
                Iterable<PageDecorator> construct() {
                    rootPage.children
                }
            }
        }

        @Override
        protected ListRenderingStrategy<PageDecorator, List<PageDecorator>> getListRenderingStrategy() {
            new PassthroughListRenderingStrategy<PageDecorator>()
        }
    }

    @TupleConstructor
    static class RenderablePage implements RenderableItem {

        PageDecorator page

        @Override
        String render() {
            page.title
        }
    }

    @TupleConstructor
    static class RenderableListComponent extends AbstractListComponent<PageDecorator, List<RenderablePage>> {

        PageDecorator rootPage

        @Override
        protected ListConstructionStrategy<PageDecorator> getListConstructionStrategy() {
            new ListConstructionStrategy<PageDecorator>() {
                @Override
                Iterable<PageDecorator> construct() {
                    rootPage.children
                }
            }
        }

        @Override
        protected ListRenderingStrategy<PageDecorator, List<RenderablePage>> getListRenderingStrategy() {
            new ListRenderingStrategy<PageDecorator, List<RenderablePage>>() {
                @Override
                List<RenderablePage> toRenderableList(Iterable<PageDecorator> pages) {
                    pages.collect { page ->
                        new RenderablePage(page)
                    }
                }
            }
        }
    }

    def setupSpec() {
        pageBuilder.content {
            harbor {
                one("One")
                two("Two")
            }
        }
    }

    def "non-renderable list component"() {
        setup:
        def page = getPage("/content/harbor")
        def component = new NonRenderableListComponent(page)

        expect:
        component.rawListItems.size() == 2

        and:
        component.items.size() == 2

        and:
        component.renderableItems.size() == 0
    }

    def "renderable list component"() {
        setup:
        def page = getPage("/content/harbor")
        def component = new RenderableListComponent(page)

        expect:
        component.rawListItems.size() == 2

        and:
        component.items.size() == 2

        and:
        component.renderableItems.size() == 2

        and:
        component.renderableItems*.rendered == ["One", "Two"]
    }
}
