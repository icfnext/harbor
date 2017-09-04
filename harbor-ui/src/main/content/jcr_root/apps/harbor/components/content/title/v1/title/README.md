# Harbor Title

A presentation of the title of a Page.  Renders as an `h1` in line with semantic 
HTML standards. 

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/title/v1/title`
* `resourceSuperType`: `harbor/components/content/heading/v1/abstractheading`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.heading.Heading` Model interface.

## HTL

Does not override the HTL from Abstract Heading

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.title.v1.Title`

Assumes the following resource properties

* `text` - The text to present within the heading
* `size` - The heading element type to produce
