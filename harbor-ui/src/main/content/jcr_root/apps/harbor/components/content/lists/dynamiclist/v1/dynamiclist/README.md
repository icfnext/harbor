# Harbor Dynamic List (v1)

Part of the dynamic family of components.  Produces a list of items of potentially 
disparate types.

## General Information

* `group`: Harbor Lists
* `resourceType`: `harbor/components/content/lists/dynamiclist/v1/dynamiclist`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicList` Model interface.

Each item is expected to implement `com.icfolson.aem.harbor.api.components.content.list.dynamic.DynamicListItem`

## HTL

* `dynamiclist.html` - Principal rendering HTL for the dynamic list.  Includes 
  `empty.html` if the list is empty.  Iterates over all `DynamicListItem`s rendering 
  each based on the type of the item's implementation.
* `empty.html` - Author help message indicating that there are no items in the list

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.list.dynamic.v1.DefaultDynamicList`

Treats the immediate child resources of the component instance as `DynamicListItem`s. 
Any resource which does not adapt as such is ignored.
