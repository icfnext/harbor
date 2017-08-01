# Harbor Title

A presentation of the title of a Page.  Renders as an `h1` in line with semantic 
HTML standards. 

## Usage

See [usage video](https://youtu.be/NGEvtXB-atM).

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/title/v1/title`
* `resourceSuperType`: `harbor/components/content/heading/v1/abstractheading`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.heading.Heading` Model interface.

## Authorable Properties

* `text`: The text to present in the Title.  Defaults to the Page's Page Title if available, falling to the Page's Title.
* `domId`: An identifier written to the title's DOM element.  Used for anchoring and identification.  Defaults to a sanitized version of the title text.

