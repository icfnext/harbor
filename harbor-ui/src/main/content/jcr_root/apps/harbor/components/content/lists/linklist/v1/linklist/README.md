# Harbor Link List

A homogeneous unordered list of link items.    

## Usage

See the [usage video](https://youtu.be/zSTChRO0Kn4).

## General Information

* `group`: Harbor Lists 
* `resourceType`: `harbor/components/content/linklist/v1/linklist`
* `classifiable`
* `list item type`: `harbor/components/content/linklist/v1/listablelink`

## Sling Model

Uses the `com.icfolson.aem.harbor.components.content.list.LinkList` Model interface.

## Authorable Properties

* `Classification`: Exposes the classifiability of the list

### Link Item Authorable Properties

* `Link`: A path to an internal page or a fully qualified external URL
* `Title`: The text title of the link
* `Open in new window`: Indicates whether the link should open in a new window or tab when clicked.  Defaults to `false`
