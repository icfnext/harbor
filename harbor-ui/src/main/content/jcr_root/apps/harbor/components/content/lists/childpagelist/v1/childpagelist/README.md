# Harbor Child Page List (v1)

Renders a list of direct children of the current page as an ordered list.

## General Information

* `group`: Harbor Lists
* `resourceType`: `harbor/components/content/lists/childpagelist/v1/childpagelist`
* `resourceSuperType`: `harbor/components/content/lists/automatedlist/v1/automatedlist`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.list.ListComponent` 
Model interface.

## HTL

* `item.html`: HTL Template overriding the `automatedlist` item template.  Renders 
  an item as a link to the associated page.  

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.list.page.v1.ChildPageList` 

The default implementation looks to child pages of the current page and includes 
all child pages as `DefaultPageListItem`.
  
The following methods are intended for overriding within extensions:

* `List<PageDecorator> getPageItems`: Produces a list of the Pages which will 
  be included.  Defaults to including direct children of the page returned by 
  `getStartingPage` which match the `Predicate` returned by `getInclusionPredicate`
* `PageDecorator getStartingPage`: Defaults to returning the current page.
* `Predicate<PageDecorator> getInclusionPredicate`: Allows for definition of which 
  list items to include. Defaults to including all items.
* `ListableLink transformPageItem(PageDecorator)`: Responsible for transforming 
  a `PageDecorator` into a `ListableLink`.  Defaults to instantiating new 
  `DefaultPageListItem`s for each `PageDecorator`.
* `String getListType`: Defaults to `ListComponent.UNORDERED_LIST_TYPE`


