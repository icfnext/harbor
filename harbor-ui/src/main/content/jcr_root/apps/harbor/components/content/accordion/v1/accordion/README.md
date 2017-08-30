# Harbor Accordion Component

A homogeneous Accordion. 

## Usage

Intended to allow for the creation of any number of homogeneous Accordion Items 
grouped into a collection.  The standard implementation is to include an 
add experience button in the proxying component's edit bar which makes use of 
the Accordion JavaScript Authoring API - see the Authoring API section below.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/accordion/v1/accordion`
* `identifiable`

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

