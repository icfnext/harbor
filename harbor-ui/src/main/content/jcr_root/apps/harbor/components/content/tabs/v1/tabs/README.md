# Harbor Tabs Component (v1)

A homogeneous collection of Tabs. 

## Usage

Intended to allow for the creation of any number of homogeneous Tabs grouped 
into a collection.  

## General Information

* `group`: Harbor Scaffolding 
* `resourceType`: `harbor/components/content/tabs/v1/tabs`
* `default tab type`: `harbor/components/content/tabs/v1/tab`
* `classifiable`

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.tabs.Tabs` Model interface.

Each tab uses the model `com.icfolson.aem.harbor.api.components.content.tabs.Tab`.

## Authoring API

`Harbor.Components.Tabs.v1.Tabs.addTab( editable, itemResourceType )`

Used to add tabs to a Tabs collection.  This method is intended to be 
associated to an add experience icon in a Tabs Collection edit bar.
Used to add items to a Tabs editable.  This method is intended to be associated 
to an add experience icon in a Tabs's edit bar.

`Harbor.Components.Tabs.v1.Tabs.moveUp( editable )`

Called by passing an editable representing a single Tab.  Causes the item to 
swap places in order with its next sibling.

`Harbor.Components.Tabs.v1.Tabs.moveDown( editable )`

Called by passing an editable representing a single Tab.  Causes the 
item to swap places in order with its next sibling.  

`Harbor.Components.Tabs.v1.Tabs.nextTab( this )`

In a Tabs collection only one Tab is shown at a time and as such only one 
Tab is editable at a time.  This method switches the presently open Tab to 
the next one.  It is intended to be associated to a next tab button in a
Tabs collection.

`Harbor.Components.Tabs.v1.Tabs.previousTab( this )`

In a Tabs collection only one Tab is shown at a time and as such only one 
Tab is editable at a time.  This method switches the presently open Tab to 
the previous one.  It is intended to be associated to a previous tab button in a
Tabs collection.

## HTL 

* `tabs.html` - The principal renderer for the Tabs collection.  Responsible 
  for instantiating the `Tabs` instance, including `empty.html`, and calling 
  upon the `tabsbar.html` and `tabscontent.html` templates. 
* `tabsbar.html` - An HTL template rendering an unordered list of tabs.  
  Expects a single `tabs` parameter to be set to an instance of the `Tabs` 
  interface.
* `tabscontent.html` - An HTL template rendering the contents of each Tab.  
  Uses `data-sly-resource` to include each tab's resource.  Expects a single 
  `tabs` parameter to be set to an instance of the `Tabs` interface.
* `empty.html` - Produces an author helper message displayed if no Tabs are 
  in the current collection.

## Default Implementation

`com.icfolson.aem.harbor.core.components.content.tabs.v1.DefaultTabs`

Implemented such that the immediate children of the Tabs collection Resource 
are treated as individual Tab items.  Each Tab item must be adaptable to 
`com.icfolson.aem.harbor.api.components.content.tabs.Tab`.  Any child resources 
not adaptable to `Tab` are ignored.

Uses `TagBasedClassification` as the means of classifiability.
