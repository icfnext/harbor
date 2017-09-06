# Harbor Dynamic Tabs Component (v1)

Part of the Dynamics component family.  Allows for the creation of tabbed 
content collection containing tabs of disparate types.

## Usage

Intended to support the creation and rendering of Tab Items of disparate 
types.  The default selection option providers rely on the `icf:allowedDynamicTypes` 
design property which should be set to a list of `resouceTypes` of allowed 
Dynamic Tab implementations.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/dynamictabs/v1/dynamictabs`
* `base AccordionItem resourceType`: `harbor/components/content/dynamictabs/tab`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTabs` 
Model interface.

Each accordion item uses the `com.icfolson.aem.harbor.api.components.content.dynamictabs.DynamicTab` 
Model interface.

## Authoring API

`Harbor.Components.DynamicTabs.v1.DynamicTabs.addTab( tabsEditable, dialogPath )`

Opens the dialog indicated by the `dialogPath` parameter and gets it ready to produce 
a new child resource upon submission.  

`Harbor.Components.DynamicTabs.v1.DynamicTabs.moveUp( tabEditable )`

Called by passing an editable representing a single Tab.  Causes the 
item to swap places in order with its prior sibling.  

`Harbor.Components.DynamicTabs.v1.DynamicTabs.moveDown( tabEditable )`

Called by passing an editable representing a single Tab.  Causes the 
item to swap places in order with its next sibling.  

`Harbor.Components.DynamicTabs.v1.DynamicTabs.nextTab( tabsEditable )`

Called by passing an editable representing the containing Tabs component instance. 
Sends a `harbor-DynamicTabs-v1-nextTab` command into the content frame.  A listener 
setup in the content frame is subscribed to these commands and is responsible for 
finding the relevant tabs container (based on the `path` data element passed 
with the command) and changing the currently visible tab to the next in the order. 
This allows each tab to be authored individually while still maintaining the 
WYSIWYG feel of the authoring interface.

`Harbor.Components.DynamicTabs.v1.DynamicTabs.previousTab( tabsEditable )`

Performs the same task as `nextTab` but opens the previous tab instead of the 
next. 

## HTL

* `dynamictabs.html` - The primary renderer for the Dynamic Tabs.  
  Responsible for inclusion of `empty`, `tabs`, and `tabcontent`
* `empty.html` - A helper message indicating that the Tabs container has no 
  contents.
* `tabs.html` - HTL template producing the tabs themselves based on the interface 
  exposed by `DynamicTab`.  Takes a single `dynamicTabs` input intended to be 
  the instance of `DynamicTabs` being rendered.
* `tabcontent.html` - HTL template producing the tab pane for each `DynamicTab` and 
  including the tab's content using `data-sly-resource` calling upon the 
  `DynamicTab` instance by type.  Takes a single `dynamicTabs` input intended to be 
  the instance of `DynamicTabs` being rendered.
  
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.dynamictabs.v1.DefaultDynamicTabs`

Treats immediate children as `Tab`s, adapting them to the `Tab` interface.  Any 
child resource which can not be adapted to a `Tab` is ignored. 

Uses the `TagBasedClassification` as the means of classifiability. 
