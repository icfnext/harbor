# Harbor Text Component

A simple text component for editing and displaying text. 

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/text/v1/text`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.text.Text` Model interface.

## HTL

* `text.html` - Principal rendering HTL.  Outputs text content in a classified `div`.
* `empty.html` - Included in edit mode - a help message presented when there is no 
  text content authored yet.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.text.v1.DefaultText`

Expects textual content to be stored under a `text` property.

Uses `TagBasedClassification` as the means of classifiability.

