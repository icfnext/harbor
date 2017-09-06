# Harbor Link List (v1)

A homogeneous unordered list of link items.    

## General Information

* `group`: Harbor Lists 
* `resourceType`: `harbor/components/content/linklist/v1/linklist`
* `classifiable`
* `list item type`: `harbor/components/content/linklist/v1/listablelink`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.list.LinkList` Model interface.

## HTL

* `linklist.html` - Primary rendering HTL.  Includes `empty.html` if no items have 
  been configured.  Renders each resource by type using `li` as the decorating tag.
* `empty.html` - Author help message indicating that no list items have been configured.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.list.linklist.v1.DefaultLinkList`

Treats all immediate child resources as list items adapting each to `ResourceBasedListableLink`. 
Any which can not be adapted as such are ignored.

Uses `TagBasedClassification` as the means of classifiability.