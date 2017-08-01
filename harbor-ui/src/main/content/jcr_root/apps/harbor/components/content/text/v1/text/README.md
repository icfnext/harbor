# Harbor Text Component

A simple text component for editing and displaying text. Uses an in-place Rich Text Editor for authoring.

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/text/v1/text`
* `resourceSuperType`: none

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.text.Text` Model interface.

## Authorable Properties

### Dialog Fields
* `content`: The text to present in the Text component.
* `classification`: A list of tag based classifications associated with this text.

### Dialog Field Types
* `content`: Inline Rich Text Editor
* `classification`: Tag Input Field
