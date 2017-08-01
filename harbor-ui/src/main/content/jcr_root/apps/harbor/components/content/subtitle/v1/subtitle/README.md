# Harbor Subtitle

A Page Subtitle rendered as an `h2` element.

## Usage

See [usage video](https://youtu.be/9phpmkQQ0Uw).

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/subtitle/v1/subtitle`
* `resourceSuperType`: `harbor/components/content/heading/v1/abstractheading`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.heading.Heading` Model interface.

## Authorable Properties

* `text`: The text to present in the Subtitle.  Defaults to the Page's subtitle if available.
* `domId`: An identifier written to the subtitle's DOM element.  Used for anchoring and identification.  Defaults to a sanitized version of the subtitle text.

