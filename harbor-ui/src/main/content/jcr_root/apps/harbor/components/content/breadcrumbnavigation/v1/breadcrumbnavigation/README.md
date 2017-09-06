# Harbor Breadcrumb Navigation Component (v1)

A breadcrumb navigation rendered as an ordered list.  

## General Information

* `group`: Harbor Navigation
* `resourceType`: `harbor/components/content/breadcrumbnavigation/v1/breadcrumbnavigation`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.breadcrumb.BreadcrumbNavigation` 
Model interface.

## HTL

* `breadcrumbnavigation.html` - the primary rendering HTL.  Establishes the breadcrumb list and calls the appropriate 
  link rendering template for each item in the breadcrumb trail.
* `rootpage.html` - HTL Template used to render the trail item representing the root page
* `currentpage.html` - HTL Template used to render the trail item representing the current page
* `intermediarypage.html` - HTL Template used to render the trail items representing all pages 
  which are neither the root page nor the current page.
* `empty.html` - Author helper template which is rendered in Edit mode when the trail is empty. 

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.breadcrumbnavigation.v1.DefaultBreadcrumbNavigation`
