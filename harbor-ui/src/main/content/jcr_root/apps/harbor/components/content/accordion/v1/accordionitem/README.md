# Harbor Accordion Item (v1)

A default item for inclusion in an Accordion Group.

## General Information

* `resourceType`: `harbor/components/content/accordion/v1/accordionitem`
* `identifiable`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.accordion.AccordionItem` Model interface.

## HTL

* `accordionitem.html` - Principal rendering HTL for the accordion item.  
  Calls upon the `panelheading.html` and `panelcontent.html` templates to render 
  the item content.
* `panelheading.html` - HTL template rendering the heading of the item panel.
* `panelcontent.html` - HTL template rendering the content of the item panel.  
  Includes a paragraph system under the content resource `accordionitem-par`.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.accordion.v1.DefaultAccordionItem`

Uses `TagBasedClassification` as the means of classifiability.
