# Harbor Listable Link (v1)

A simple link rendering  

## General Information
 
* `resourceType`: `harbor/components/content/linklist/v1/listablelink`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.list.ResourceBasedListableLink` Model interface.

## HTL

* `listablelink.html` - Renders an `a` tag with configured properties

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.list.linklist.v1.DefaultListableLink`

## Dialog Fields

* `href` - Path field to input the page path or full URL of a link.
* `title` - Title of the link - no defaulting provided.
* `openInNewWindow` - Switch field indicating whether the link should open in a new 
  window/tab when invoked.
  