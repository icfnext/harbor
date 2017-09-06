# Harbor Parsys Accordion Item (v1)

An Accordion Item for use in a Dynamic Accordion exposing a single Paragraph System.

## General Information

* `resourceType`: `harbor/components/content/dynamicaccordion/items/parsysaccordionitem/v1/parsysaccordionitem`
* `resourceSuperType`: `harbor/components/content/dynamicaccordion/item`
* `identifiable`
* `classifiable`

## Sling Model

Does not make use of a Model interface. 

## HTL

* `parsysaccordionitem.html` - The primary renderer for the Accordion Item.  Renders 
  a paragraph system with relative path `itemcontent`.
* `authorhelp.html` - A helper message presented above the Accordion Item 
  to provide a clear handle to click on to bring up the instance's toolbar.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.dynamicaccordion.items.parsysaccordionitem.v1.ParsysAccordionItem`

Uses `TagBasedClassification` as the means of classifiability.

## Dialog Fields

* `headingText` - Text to be rendered in the heading for the Accordion Item's panel.
