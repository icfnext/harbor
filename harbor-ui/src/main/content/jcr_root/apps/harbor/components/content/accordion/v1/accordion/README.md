# Harbor Accordion Group (v1)

A homogeneous Accordion. 

## Usage

Intended to allow for the creation of any number of homogeneous Accordion Items 
grouped into a collection.  The standard implementation is to include an 
add experience button in the proxying component's edit bar which makes use of 
the Accordion JavaScript Authoring API - see the Authoring API section below.

## General Information

* `group`: Harbor Scaffolding 
* `resourceType`: `harbor/components/content/accordion/v1/accordion`
* `identifiable`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.accordion.Accordion` Model interface.

Each accordion item uses the `com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem` Model interface.

## Authoring API

`Harbor.Components.Accordion.v1.Accordion.addItem( editable, itemResourceType )`

Used to add items to an Accordion editable.  This method is intended to be associated 
to an add experience icon in an Accordion's edit bar.

`Harbor.Components.Accordion.v1.Accordion.moveUp( editable )`

Called by passing an editable representing a single Accordion Item.  Causes the 
item to swap places in order with its prior sibling.  

`Harbor.Components.Accordion.v1.Accordion.moveDown( editable )`

Called by passing an editable representing a single Accordion Item.  Causes the 
item to swap places in order with its next sibling.  

## HTL

* `accordion.html` - Principal rendering HTL for the accordion group.  Includes 
  `authorhelp.html` when in edit mode.  Establishes the parameters of the accordion group 
  and calls `accordionitems.html` to include the items.
* `accordionitems.html` - HTL template for rendering the `AccordionItem`s based on the 
  resource type and path defined by the item's implementation.
* `authorhelp.html` - Author help message providing a hook to select in order to bring up 
  the tool bar for the component.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.accordion.v1.DefaultAccordion`

Treats all child resources of the accordion resource as `AccordionItem`s.  Any child 
resource which can not be adapted to an `AccordionItem` is ignored.

Uses `TagBasedClassification` as the means of classifiability.
