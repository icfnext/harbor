# Harbor Page Footer

A content container intended for usage as a page footer.  Exhibits the same behavior 
as the Content Container but renders as a `footer` DOM element for semantic purposes.

All configurations made to the page footer component itself are inherited by 
child pages.  Footer content is only inherited if the `Container Inheritance` 
configuration is set to `true`.

## Usage

See the Content Container [usage video](https://youtu.be/i7fQr5E5op4).

## General Information

* `group`: Harbor Scaffolding 
* `resourceType`: `harbor/components/content/pagefooter/v1/pagefooter`
* `resourceSuperType`: `harbor/components/content/heading/v1/abstractheading`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.container.Container` Model interface.

## Authorable Properties

* `Full Width`: Indicates whether the container should span the entire width of the available space
* `Container Inheritance`: If set to true an inheriting paragraph system will be used in place of a paragraph system
* `Classification`: Exposes the classifiability of the container
* `ID`: Writes an ID to the presented DOM element.  Used for anchoring to the container