# Harbor Subtitle

A Page Subtitle rendered as an `h2` element.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/subtitle/v1/subtitle`
* `resourceSuperType`: `harbor/components/content/heading/v1/abstractheading`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.heading.Heading` Model interface.

## HTL

Does not override the HTL from Abstract Heading

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.subtitle.v1.Subtitle`

Assumes the following resource properties

* `text` - The text to present within the heading
* `size` - The heading element type to produce