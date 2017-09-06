# Harbor Automated List (v1)

An abstract list renderer for generating straightforward ordered or unordered 
lists of items where the item set can be automatically generated.

## General Information

* `resourceType`: `harbor/components/content/lists/automatedlist/v1/automatedlist`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.list.ListComponent` 
Model interface.

## HTL

* `automatedlist.html` - Instantiates a `ListComponent` and iterates over its items
  calling the `item.html` template for each.  Also responsible for calling 
  `empty.html` when appropriate and deciding what list type (`ul`, `ol`, `dl`) 
  to render.
* `item.html` - HTL template for each list item.  The default implementation simply 
  renders an `li` element with the item's `title` as content.
* `empty.html` - An author help message presented in edit mode when there are no 
  items in the list.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.list.automatedlist.v1.AbstractAutomatedList` 

Does not implement any of the `ListComponent` methods, but does expose its static 
`RESOURCE_TYPE` string.
