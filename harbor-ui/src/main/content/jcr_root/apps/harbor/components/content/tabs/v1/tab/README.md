# Harbor Tab Component (v1)

A single Tab appropriate for inclusion in a Tabs collection

## Usage

The default tab implementation for the Tabs collection component.

## General Information
 
* `resourceType`: `harbor/components/content/tabs/v1/tab`
* `identifiable`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tabs.Tab` Model interface.

## HTL 

* `tab.html` - The principal renderer for the Tab.  Responsible for including 
  `authorhelp.html` and rendering a paragraph system.  The rendered paragraph 
  system stores content under a `tab-par` resource.
* `authorhelp.html` - Presents an author help message providing a clear 
  element for authors to select in order to interact with the tab editable.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.tabs.v1.DefaultTab`

Uses `TagBasedClassification` as the means of classifiability.

### Dialog Fields

* `label` - Text field defining the content of the tab label