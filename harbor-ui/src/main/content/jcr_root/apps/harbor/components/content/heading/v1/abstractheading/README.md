# Harbor Abstract Heading

Abstract rendering and implementation for Heading elements

## General Information

* `resourceType`: `harbor/components/content/heading/v1/abstractheading`
* `identifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.heading.Heading` Model interface.

## HTL

* `abstractheading.html` - Responsible for rendering a heading element based on the 
  `getSize()` method of the instantiated `Heading`. 

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.heading.v1.AbstractHeading`

Exposes the following default implementations

* `String getId()` - Sanatizes the text of the heading to be used as an `id` 
  in the rendered DOM.