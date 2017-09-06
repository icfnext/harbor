# Harbor Dynamic Accordion Component (v1)

Part of the Dynamics component family.  Allows for the creation of an Accordion 
collection containing items of disparate types. 

## Usage

Intended to support the creation and rendering of Accordion Items of disparate 
types.  The default selection option providers rely on the `icf:allowedDynamicTypes` 
design property which should be set to a list of `resouceTypes` of allowed 
Accordion Item implementations.

## General Information

* `group`: Harbor Scaffolding
* `resourceType`: `harbor/components/content/dynamicaccordion/v1/dynamicaccordion`
* `base AccordionItem resourceType`: `harbor/components/content/dynamicaccordion/item`
* `identifiable`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordion` 
Model interface.

Each accordion item uses the `com.icfolson.aem.harbor.api.components.content.dynamicaccordion.DynamicAccordionItem` 
Model interface.

## Authoring API

`Harbor.Components.DynamicAccordion.v1.DynamicAccordion.addItem( editable, dialogPath )`

Opens the dialog indicated by the `dialogPath` parameter and gets it ready to produce 
a new child resource upon submission.  

`Harbor.Components.DynamicAccordion.v1.DynamicAccordion.moveUp( editable )`

Called by passing an editable representing a single Accordion Item.  Causes the 
item to swap places in order with its prior sibling.  

`Harbor.Components.DynamicAccordion.v1.DynamicAccordion.moveDown( editable )`

Called by passing an editable representing a single Accordion Item.  Causes the 
item to swap places in order with its next sibling.  

## HTL

* `dynamicaccordion.html` - The primary renderer for the Dynamic Accordion.  
  Responsible for iterating over all contained `DynamicAccordionItem`s and producing 
  a panel heading and panel body for the item.  
* `authorhelp.html` - A helper message presented above the Accordion collection 
  to provide a clear handle to click on to bring up the instance's toolbar.
* `panelheading.html` - HTL Template producing the panel heading element of an 
  accordion item.  Takes a single `item` parameter intended to be the 
  `DynamicAccordionItem` instance.
* `panelbody.html` - HTL Template producing the panel body element of an accordion 
  item.  Takes a single `item` parameter intended to be the `DynamicAccordionItem`
  instance.  Renders the panel body and then calls `data-sly-resource` to include 
  the item content based on the resource type of the item.
  
## Default Implementation

`com.icfolson.aem.harbor.core.components.content.dynamicaccordion.v1.DefaultDynamicAccordion`

Treats direct child resources as `DynamicAccordionItem`s, ignoring any which can not be 
adapted as such.

Uses `TagBasedClassification` as the means of classifiability.
