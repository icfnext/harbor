# Harbor Parsys Tab (v1)

A Tab type for use in a Dynamic Tabs component exposing a single paragraph system

## General Information

* `resourceType`: `harbor/components/content/dynamictabs/tabs/parsystab/v1/parsystab`
* `resourceSuperType`: `harbor/components/content/dynamictabs/tab`
* `classifiable`
* `identifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab` 
Model interface.

## HTL

* `parsystab.html` - The primary renderer for the Tab.  Renders 
  a paragraph system with relative path `tabcontent`.
* `authorhelp.html` - A helper message presented above the Tab 
  to provide a clear handle to click on to bring up the instance's toolbar.
  
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.dynamictabs.tabs.parsystab.v1.ParsysTab`

Uses `TagBasedClassification` as the means of classifiability.

## Dialog Fields

* `label` - Text label to be applied to the rendered Tab.
