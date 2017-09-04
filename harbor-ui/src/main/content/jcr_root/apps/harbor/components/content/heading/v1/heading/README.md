# Harbor Heading

A generic section heading supporting types `h2` - `h6`.  When an `h1` is needed use Title.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/heading/v1/heading`
* `resourceSuperType`: `harbor/components/content/heading/v1/abstractheading`
* `identifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.heading.Heading` Model interface.

## HTL

Does not override the HTL from Abstract Heading

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.heading.v1.DefaultHeading`

Assumes the following resource properties

* `text` - The text to present within the heading
* `size` - The heading element type to produce
