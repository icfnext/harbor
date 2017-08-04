# Harbor Dynamic Carousel Component

A container used to hold any number of Dynamic Carousel Slides. 
The Dynamic Carousel component differs from the Carousel component in that each 
slide can have varying types of content that are individually authored per slide. 

## General Information

* `group`: Harbor
* `resourceType`: `harbor/components/content/dynamiccarousel/v1/dynamiccarousel`
* `resourceSuperType`: none

## Sling Model

Uses the `com.icfolson.aem.harbor.api.components.content.dynamiccarousel.DynamicCarousel` Model interface.

## Authorable Properties

### Dialog Fields
* `showPreviousAndNextControls`: Flag indicating whether to show previous and next controls.
* `showSlideSelectorControls`: Flag indicating whether to show slide selector controls.
* `interval`: The amount of time to delay between automatically cycling an item.  Defaults to 5000.  If set to 0, automatic cycling will be disabled.
* `pauseOnHover`: Flag indicating whether cycling of the carousel should pause on mouse hover.
* `wrap`: Flag indicating whether the carousel should cycle continuously or have hard stops.
* `keyboard`: Flag indicating whether the carousel should react to keyboard events.
* `wrap`: Flag indicating whether the carousel should cycle continuously or have hard stops.

### Dialog Field Types
* `showPreviousAndNextControls`: Switch
* `showSlideSelectorControls`: Switch
* `interval`: Number Field
* `pauseOnHover`: Switch
* `wrap`: Switch
* `keyboard`: Switch
* `wrap`: Switch
